package pl.coderslab.guest;

public class Guest {
    private long id;
    private String name;
    private String surname;
    private String address;
    private long coupleGuestId; // id of a couple, -1 for undefined accompanying person, 0 - single
    private long eventId; // lista!!!!!!!!!!!!!!
    private byte invitationType; // 1 mail, 2 sms, 3 meeting, etc.
    private byte status; //1 (i)invited-> 2 (c)onfirmed or 3 (r)efuse, 4 (n)ot to be invited
    private byte related; // 1(b)ride, 2(g)room, 3(f)riend - used to costs split
}
