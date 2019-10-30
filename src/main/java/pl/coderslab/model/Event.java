package pl.coderslab.model;

import java.time.LocalDateTime;

public class Event {
    private long id;
    private String name;
    private String bride;
    private String groom;
    private long eventAdminId;
    private LocalDateTime date;
    private char type;// (c)ivil or (r)eligious
    private long venue_id;
}
