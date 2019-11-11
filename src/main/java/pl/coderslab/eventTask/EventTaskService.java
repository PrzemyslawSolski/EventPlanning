package pl.coderslab.eventTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.estimate.Estimate;
import pl.coderslab.event.Event;
import pl.coderslab.event.EventRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventTaskService {

    private final EventTaskRepository eventTaskRepository;
    private final EventRepository eventRepository;

    public List<EventTask> getEventTasksByEventIdOrderByCompletedAscDateAsc(long eventId) {
        return eventTaskRepository.findByEventIdOrderByCompletedAscDateAsc(eventId);
    }

    public List<EventTask> getEventTasksByEventId(long eventId) {
        return eventTaskRepository.findByEventId(eventId);
    }

    public void calculateEstimate(HttpSession session, long eventId) {
        List<EventTask> eventTasks = getEventTasksByEventId(eventId)
                .stream()
                .filter(et -> et.getPrice().getAmount() > 0)
                .sorted()
                .collect(Collectors.toList());

        Estimate estimate = new Estimate();
        Event event = eventRepository.getOne(eventId);
        double brideGuestsRatio = 1.0 * event.getBrideGuestsNo() / (event.getBrideGuestsNo() + event.getGroomGuestsNo());
        double groomGuestsRatio = -1.0 * brideGuestsRatio + 1;

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
                        estimate.setBrideSubtotal(estimate.getBrideSubtotal() + amount);
                        estimate.setBrideSubtotalPaid(estimate.getBrideSubtotalPaid() + amountPaid);
                        break;
                    }
                    case 2: {//2 - groom
                        estimate.setGroomSubtotal(estimate.getGroomSubtotal() + amount);
                        estimate.setGroomSubtotalPaid(estimate.getGroomSubtotalPaid() + amountPaid);
                        break;
                    }
                    case 3: {//3 - equal
                        estimate.setBrideSubtotal(estimate.getBrideSubtotal() + amount / 2);
                        estimate.setBrideSubtotalPaid(estimate.getBrideSubtotalPaid() + amountPaid / 2);
                        estimate.setGroomSubtotal(estimate.getGroomSubtotal() + amount / 2);
                        estimate.setGroomSubtotalPaid(estimate.getGroomSubtotalPaid() + amountPaid / 2);
                        break;
                    }
                    case 4: {//4 - guest
                        //TODO uzupełnić strukturę gości
                        estimate.setBrideSubtotal(estimate.getBrideSubtotal() + 1.0* Math.round(amount * brideGuestsRatio * 100) / 100);
                        estimate.setBrideSubtotalPaid(estimate.getBrideSubtotalPaid() + 1.0* Math.round(amountPaid * brideGuestsRatio * 100) / 100);
                        estimate.setGroomSubtotal(estimate.getGroomSubtotal() + 1.0* Math.round(amount * groomGuestsRatio * 100) / 100);
                        estimate.setGroomSubtotalPaid(estimate.getGroomSubtotalPaid() + 1.0* Math.round(amountPaid * groomGuestsRatio * 100) / 100);
                        break;
                    }
                    default: {
                        estimate.setNotSplit(amount);
                    }
                }

            }
        }
        eventTasks = eventTasks
                .stream()
                .filter(et -> et.getPrice().getAmount() - et.getPrice().getAmountPaid() > 0)
//                .sorted()
                .collect(Collectors.toList());
        session.setAttribute("estimateTasks", eventTasks);
        session.setAttribute("estimate", estimate);
    }

    @Autowired
    public EventTaskService(EventTaskRepository eventTaskRepository, EventRepository eventRepository, EventTaskDao eventTaskDao) {
        this.eventTaskRepository = eventTaskRepository;
        this.eventRepository = eventRepository;
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
