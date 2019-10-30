package pl.coderslab.model;

public class Task {
    private long id;
    private String description;
    private long event_id; // – empty if general task, available for all; specific id if added by event’s admin
    private long group_id;
}
