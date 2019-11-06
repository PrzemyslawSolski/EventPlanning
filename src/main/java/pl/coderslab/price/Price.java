package pl.coderslab.price;

import javax.persistence.*;

@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    private long eventTaskId;
    private double amount;
    private byte type; // 0- estimated, 1 - final
    private byte split; // 1 - bride, 2 - groom, 3 - equal, 4 - guest – for costs split between sides

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getSplit() {
        return split;
    }

    public void setSplit(byte split) {
        this.split = split;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", amount=" + amount +
                ", type=" + type +
                ", split=" + split +
                '}';
    }
}
