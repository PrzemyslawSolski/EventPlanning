package pl.coderslab.model;

public class Guest {
    private long id;
    private String name;
    private String surname;
    private String address;
    private long eventId;
    private String invitationType; // – mail, meeting, email, sms, etc.
    private char status; //(i)nvited -> (c)onfirmed or (r)ejected by guest; (n)ot ot be invited
    private char related; // (b)ride, (g)room guest, (f)riend
    private long coupleGuest; //couple guest id; empty - single quest
}
