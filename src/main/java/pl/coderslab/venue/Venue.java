package pl.coderslab.venue;

import javax.persistence.*;

@Entity
@Table(name = "venues")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String venueName;
    private String city;
    private String street;
    private String number;
    private String zip;

}
