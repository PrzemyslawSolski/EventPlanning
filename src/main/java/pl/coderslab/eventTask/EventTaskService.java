package pl.coderslab.eventTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.estimate.Estimate;
import pl.coderslab.event.Event;
import pl.coderslab.event.EventRepository;
import pl.coderslab.price.Price;
import pl.coderslab.price.PriceRepository;
import pl.coderslab.task.Task;
import pl.coderslab.task.TaskRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventTaskService {

    private final EventTaskRepository eventTaskRepository;
    private final EventRepository eventRepository;
    private final PriceRepository priceRepository;
    private final TaskRepository taskRepository;

    public List<EventTask> getEventTasksByEventIdOrderByDateAscCompletedAsc(long eventId) {
        return eventTaskRepository.findByEventIdOrderByDateAscCompletedAsc(eventId);
    }

    public List<EventTask> getEventTasksByEventId(long eventId) {
        return eventTaskRepository.findByEventId(eventId);
    }

    public double getBrideGuestsRatio(Event event){
        if (event.getGroomGuestsNo() == 0 && event.getBrideGuestsNo() == 0) {
            return 0.5;
        }
        return 1.0 * event.getBrideGuestsNo() / (event.getBrideGuestsNo() + event.getGroomGuestsNo());
    }

    public void calculateEstimate(HttpSession session, long eventId) {
        List<EventTask> eventTasks = getEventTasksByEventId(eventId)
                .stream()
                .filter(et -> et.getPrice().getAmount() > 0)
                .sorted()
                .collect(Collectors.toList());

        Estimate estimate = new Estimate();
        Event event = eventRepository.getOne(eventId);

        if (eventTasks != null && !eventTasks.isEmpty()) {
            double amount = 0;
            double amountPaid = 0;
            for (EventTask eventTask : eventTasks) {
                amount = eventTask.getPrice().getAmount();
                estimate.setTotal(estimate.getTotal() + amount);
                amountPaid = eventTask.getPrice().getAmountPaid();
                estimate.setTotalPaid(estimate.getTotalPaid() + amountPaid);

                if (eventTask.getPrice().getType() == 1) {
                    estimate.setTotalConfirmed(estimate.getTotalConfirmed() + amount);
                }
                double brideRatio=0;
                switch (eventTask.getPrice().getSplit()) {
                    case 1: {//1 - bride
                        brideRatio=1.0;
                        break;
                    }
                    case 2: {//2 - groom
                        brideRatio=0.0;
                        break;
                    }
                    case 3: {//3 - equal
                        brideRatio=0.5;
                        break;
                    }
                    case 4: {//4 - guest
                        brideRatio=1.0*getBrideGuestsRatio(event);
                        break;
                    }
                    default: {
                        estimate.setNotSplit(estimate.getNotSplit() + amount);
                        amount=0;
                        amountPaid=0;
                    }
                }

                if(amount>0) {
                    setBrideGroomSubtotals(estimate, amount, brideRatio);
                }
                if(amountPaid>0){
                    setBrideGroomSubtotalsPaid(estimate, amountPaid, brideRatio);
                }
            }
        }
        roundEstimate(estimate);

        session.setAttribute("estimateTasks", removeFullyPaid(eventTasks));
        session.setAttribute("estimate", estimate);
    }

    public List<EventTask> removeFullyPaid(List<EventTask> eventTasks){
        return eventTasks
                .stream()
                .filter(et -> et.getPrice().getAmount() - et.getPrice().getAmountPaid() > 0)
                .collect(Collectors.toList());
    }

    public void setBrideGroomSubtotals(Estimate estimate, double amount, double brideRatio){
        estimate.setBrideSubtotal(estimate.getBrideSubtotal() + amount*brideRatio);
        estimate.setGroomSubtotal(estimate.getGroomSubtotal() + amount*(1-brideRatio));
    }

    public void setBrideGroomSubtotalsPaid(Estimate estimate, double amountPaid, double brideRatio){
        estimate.setBrideSubtotalPaid(estimate.getBrideSubtotalPaid() + amountPaid*brideRatio);
        estimate.setGroomSubtotalPaid(estimate.getGroomSubtotalPaid() + amountPaid*(1-brideRatio));
    }

    public void roundEstimate(Estimate estimate){
        estimate.setTotal(1.0*Math.round(estimate.getTotal()*100)/100);
        estimate.setTotalPaid(1.0*Math.round(estimate.getTotalPaid()*100)/100);
        estimate.setTotalConfirmed(1.0*Math.round(estimate.getTotalConfirmed()*100)/100);
        estimate.setNotSplit(1.0*Math.round(estimate.getNotSplit()*100)/100);
        estimate.setBrideSubtotal(1.0*Math.round(estimate.getBrideSubtotal()*100)/100);
        estimate.setGroomSubtotal(estimate.getTotal()-estimate.getBrideSubtotal()-estimate.getNotSplit());

    }

    public List<EventTask> getEventTasksSorted(long eventId){
        List<EventTask> eventTasks = getEventTasksByEventIdOrderByDateAscCompletedAsc(eventId);
        eventTasks = putEmptyDatesAtEnd(eventTasks);
        eventTasks = putCompletedTasksAtEnd(eventTasks);
        return eventTasks;
    }

    public List<EventTask> putEmptyDatesAtEnd(List<EventTask> eventTasks) {
        List<EventTask> eventTasksEmptyDate = eventTasks
                .stream()
                .filter(et -> et.getDate() == null).collect(Collectors.toList());
        eventTasks.removeAll(eventTasksEmptyDate);
        eventTasks.addAll(eventTasksEmptyDate);
        return eventTasks;
    }

    public List<EventTask> putCompletedTasksAtEnd(List<EventTask> eventTasks){
        List<EventTask> eventTasksCompleted = eventTasks
                .stream()
                .filter(EventTask::isCompleted).collect(Collectors.toList());
        eventTasks.removeAll(eventTasksCompleted);
        eventTasks.addAll(eventTasksCompleted);
        return eventTasks;
    }

    public void saveNewTask(HttpSession session, EventTask eventTask) {
        Price price = eventTask.getPrice();
        priceRepository.save(price);
        eventTask.setPrice(price);

        Task task = eventTask.getTask();
        Event event = eventRepository.findById((Long) session.getAttribute("eventId")).orElse(null);
        task.setEvent(event);
        taskRepository.save(task);
        eventTask.setTask(task);
        eventTask.setEvent(event);

        eventTaskRepository.save(eventTask);
    }

    public void saveWithNewPrice(EventTask eventTask) {
        Price price = new Price();
        priceRepository.save(price);
        eventTask.setPrice(price);
        eventTaskRepository.save(eventTask);
    }


    @Autowired
    public EventTaskService(EventTaskRepository eventTaskRepository, EventRepository eventRepository, PriceRepository priceRepository, TaskRepository taskRepository, EventTaskDao eventTaskDao) {
        this.eventTaskRepository = eventTaskRepository;
        this.eventRepository = eventRepository;
        this.priceRepository = priceRepository;
        this.taskRepository = taskRepository;
        this.eventTaskDao = eventTaskDao;
    }

    public EventTask getOne(long id) {
        return eventTaskRepository.getOne(id);
    }

    public EventTask getOneByIdAndEventId(long taskEventId, long eventId) {
        return eventTaskRepository.findByIdAndEventId(taskEventId, eventId);
    }

    public void save(EventTask eventTask) {
        eventTaskRepository.save(eventTask);
    }

    public List<EventTask> findAll() {
        return eventTaskRepository.findAll();
    }

    public void delete(long id) {
        eventTaskRepository.delete(getOne(id));
    }


    private final EventTaskDao eventTaskDao;


    public void create(EventTask eventTask) {
        eventTaskDao.create(eventTask);
    }

    public void update(EventTask eventTask) {
        eventTaskDao.update(eventTask);
    }

    public EventTask findOne(long id) {
        return eventTaskDao.findOne(id);
    }

//    public List<EventTask> findAll(){
//        return eventTaskDao.findAll();
//    }

//    public void delete(Long id){
////        eventTaskDao.deleteTaskRelations(id);
//        eventTaskDao.delete(id);
//    }
}
