package pl.coderslab.event;

import pl.coderslab.user.User;
import pl.coderslab.venue.Venue;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String bride;//long brideId;
    private String groom; //long groomId;
    private long eventAdminId;
    private LocalDateTime ceremonyDateTime;
    private LocalDateTime partyDateTime;
    private byte type;// (c)ivil or (r)eligious
    @ManyToOne
    private Venue ceremonyVenue;
    @ManyToOne
    private Venue partyVenue;
    @ManyToMany
    @JoinTable(name = "event_user")
    private List<User> users;
}
