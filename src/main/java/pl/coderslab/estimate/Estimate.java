package pl.coderslab.estimate;

public class Estimate {
    private double total;
    private double totalPaid;
    private double brideSubtotal;
    private double brideSubtotalPaid;
    private double groomSubtotal;
    private double groomSubtotalPaid;

    public double getNotSplit() {
        return notSplit;
    }

    public void setNotSplit(double notSplit) {
        this.notSplit = notSplit;
    }

    private double notSplit;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public double getBrideSubtotal() {
        return brideSubtotal;
    }

    public void setBrideSubtotal(double brideSubtotal) {
        this.brideSubtotal = brideSubtotal;
    }

    public double getBrideSubtotalPaid() {
        return brideSubtotalPaid;
    }

    public void setBrideSubtotalPaid(double brideSubtotalPaid) {
        this.brideSubtotalPaid = brideSubtotalPaid;
    }

    public double getGroomSubtotal() {
        return groomSubtotal;
    }

    public void setGroomSubtotal(double groomSubtotal) {
        this.groomSubtotal = groomSubtotal;
    }

    public double getGroomSubtotalPaid() {
        return groomSubtotalPaid;
    }

    public void setGroomSubtotalPaid(double groomSubtotalPaid) {
        this.groomSubtotalPaid = groomSubtotalPaid;
    }
}
