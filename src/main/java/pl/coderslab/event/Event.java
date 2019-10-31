package pl.coderslab.event;

import java.time.LocalDateTime;

public class Event {
    private long id;
    private String name;
    private long brideId;
    private long groomId;
    private long eventAdminId;
    private LocalDateTime ceremonyDateTime;
    private LocalDateTime partyDateTime;
    private byte type;// (c)ivil or (r)eligious
    private long ceremonyVenueId;
    private long partyVenueId;
}
