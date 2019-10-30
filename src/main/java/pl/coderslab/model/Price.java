package pl.coderslab.model;

public class Price {
    private long id;
    private long task_id;
    private double amount;
    private char type; // (e)stimated, (f)inal
    private boolean guest; // if price per guest
}
