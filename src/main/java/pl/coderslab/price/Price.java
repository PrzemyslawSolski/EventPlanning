package pl.coderslab.price;

public class Price {
    private long id;
    private long eventTaskId;
    private double amount;
    private byte type; // 0- estimated, 1 - final
    private byte split; // 1 - bride, 2 - groom, 3 - equal, 4 - guest – for costs split between sides
}
