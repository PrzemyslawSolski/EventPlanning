package pl.coderslab.model;

import java.time.LocalDate;

public class EventTask {
    private long id;
    private long eventId;
    private LocalDate date; // date of completion, firstly estimated, once completed – completion date
    private boolean completed;
    private long priceId;
}
