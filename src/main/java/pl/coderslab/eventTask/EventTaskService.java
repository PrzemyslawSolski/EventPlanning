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
        double brideGuestsRatio = getBrideGuestsRatio(event);
//        if (event.getGroomGuestsNo() == 0 && event.getBrideGuestsNo() == 0) {
//            brideGuestsRatio = 0.5;
//        } else {
//            brideGuestsRatio = 1.0 * event.getBrideGuestsNo() / (event.getBrideGuestsNo() + event.getGroomGuestsNo());
//        }
//        double groomGuestsRatio = -1.0 * brideGuestsRatio + 1;

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
                switch (eventTask.getPrice().getSplit()) {
                    case 1: {//1 - bride
//                        estimate.setBrideSubtotal(estimate.getBrideSubtotal() + amount);
                        setBrideGroomSubtotals(estimate, amount, 1);
//                        estimate.setBrideSubtotalPaid(estimate.getBrideSubtotalPaid() + amountPaid);
                        setBrideGroomSubtotalsPaid(estimate, amountPaid, 1);
                        break;
                    }
                    case 2: {//2 - groom
//                        estimate.setGroomSubtotal(estimate.getGroomSubtotal() + amount);
                        setBrideGroomSubtotals(estimate, amount, 0);
//                        estimate.setGroomSubtotalPaid(estimate.getGroomSubtotalPaid() + amountPaid);
                        setBrideGroomSubtotalsPaid(estimate, amountPaid, 0);
                        break;
                    }
                    case 3: {//3 - equal
                        setBrideGroomSubtotals(estimate, amount, 0.5);
                        setBrideGroomSubtotalsPaid(estimate, amountPaid, 0.5);
//                        estimate.setBrideSubtotal(estimate.getBrideSubtotal() + amount / 2);
//                        estimate.setBrideSubtotalPaid(estimate.getBrideSubtotalPaid() + amountPaid / 2);
//                        estimate.setGroomSubtotal(estimate.getGroomSubtotal() + amount / 2);
//                        estimate.setGroomSubtotalPaid(estimate.getGroomSubtotalPaid() + amountPaid / 2);
                        break;
                    }
                    case 4: {//4 - guest
                        setBrideGroomSubtotals(estimate, amount, brideGuestsRatio);
                        setBrideGroomSubtotalsPaid(estimate, amountPaid, brideGuestsRatio);
//                        estimate.setBrideSubtotal(estimate.getBrideSubtotal() + 1.0 * Math.round(amount * brideGuestsRatio * 100) / 100);
//                        estimate.setBrideSubtotalPaid(estimate.getBrideSubtotalPaid() + 1.0 * Math.round(amountPaid * brideGuestsRatio * 100) / 100);
//                        estimate.setGroomSubtotal(estimate.getGroomSubtotal() + 1.0 * Math.round(amount * groomGuestsRatio * 100) / 100);
//                        estimate.setGroomSubtotalPaid(estimate.getGroomSubtotalPaid() + 1.0 * Math.round(amountPaid * groomGuestsRatio * 100) / 100);
                        break;
                    }
                    default: {
                        estimate.setNotSplit(estimate.getNotSplit() + amount);
                    }
                }
            }
        }
        roundEstimate(estimate);
//        estimate.setTotal(1.0*Math.round(estimate.getTotal()*100)/100);
//        estimate.setTotalPaid(1.0*Math.round(estimate.getTotalPaid()*100)/100);
//        estimate.setTotalConfirmed(1.0*Math.round(estimate.getTotalConfirmed()*100)/100);
//        estimate.setNotSplit(1.0*Math.round(estimate.getNotSplit()*100)/100);
//        estimate.setBrideSubtotal(1.0*Math.round(estimate.getBrideSubtotal()*100)/100);
//        estimate.setGroomSubtotal(estimate.getTotal()-estimate.getBrideSubtotal()-estimate.getNotSplit());

        eventTasks = eventTasks
                .stream()
                .filter(et -> et.getPrice().getAmount() - et.getPrice().getAmountPaid() > 0)
//                .sorted()
                .collect(Collectors.toList());
        session.setAttribute("estimateTasks", eventTasks);
        session.setAttribute("estimate", estimate);
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
                .filter(et -> et.isCompleted()).collect(Collectors.toList());
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
