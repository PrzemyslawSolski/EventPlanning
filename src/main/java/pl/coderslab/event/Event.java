package pl.coderslab.event;

import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.user.User;
import pl.coderslab.venue.Venue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    private String bride;
    private String groom;
    private long eventAdminId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate ceremonyDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime ceremonyTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate partyDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime partyTime;
    private byte type;// (c)ivil or (r)eligious
    @ManyToOne
    private Venue ceremonyVenue;
    @ManyToOne
    private Venue partyVenue;
    @ManyToMany
    @JoinTable(name = "event_user")
    private List<User> users = new ArrayList<>();
    private int brideGuestsNo;
    private int groomGuestsNo;

    public void changeDates(int shift){
        this.setCeremonyDate(this.getCeremonyDate().plusDays(shift));
        this.setPartyDate(this.getPartyDate().plusDays(shift));
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBride() {
        return bride;
    }

    public void setBride(String bride) {
        this.bride = bride;
    }

    public String getGroom() {
        return groom;
    }

    public void setGroom(String groom) {
        this.groom = groom;
    }

    public long getEventAdminId() {
        return eventAdminId;
    }

    public void setEventAdminId(long eventAdminId) {
        this.eventAdminId = eventAdminId;
    }

    public LocalDate getCeremonyDate() {
        return ceremonyDate;
    }

    public void setCeremonyDate(LocalDate ceremonyDate) {
        this.ceremonyDate = ceremonyDate;
    }

    public LocalTime getCeremonyTime() {
        return ceremonyTime;
    }

    public void setCeremonyTime(LocalTime ceremonyTime) {
        this.ceremonyTime = ceremonyTime;
    }

    public LocalDate getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(LocalDate partyDate) {
        this.partyDate = partyDate;
    }

    public LocalTime getPartyTime() {
        return partyTime;
    }

    public void setPartyTime(LocalTime partyTime) {
        this.partyTime = partyTime;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Venue getCeremonyVenue() {
        return ceremonyVenue;
    }

    public void setCeremonyVenue(Venue ceremonyVenue) {
        this.ceremonyVenue = ceremonyVenue;
    }

    public Venue getPartyVenue() {
        return partyVenue;
    }

    public void setPartyVenue(Venue partyVenue) {
        this.partyVenue = partyVenue;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getBrideGuestsNo() {
        return brideGuestsNo;
    }

    public void setBrideGuestsNo(int brideGuestsNo) {
        this.brideGuestsNo = brideGuestsNo;
    }

    public int getGroomGuestsNo() {
        return groomGuestsNo;
    }

    public void setGroomGuestsNo(int groomGuestsNo) {
        this.groomGuestsNo = groomGuestsNo;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bride='" + bride + '\'' +
                ", groom='" + groom + '\'' +
                ", eventAdminId=" + eventAdminId +
                ", ceremonyDate=" + ceremonyDate +
                ", ceremonyTime=" + ceremonyTime +
                ", partyDate=" + partyDate +
                ", partyTime=" + partyTime +
                ", type=" + type +
                ", ceremonyVenue=" + ceremonyVenue +
                ", partyVenue=" + partyVenue +
                ", brideGuestsNo=" + brideGuestsNo +
                ", groomGuestsNo=" + groomGuestsNo +
                '}';
    }
}
