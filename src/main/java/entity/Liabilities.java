package entity;

public class Liabilities {
    private final double loan;
    private final double creditCard;

    public Liabilities() {
        this.loan = 0;
        this.creditCard = 0;
    }

    public Liabilities(double loan, double creditCard) {
        this.loan = loan;
        this.creditCard = creditCard;
    }

    public double getLoan() {
        return loan;
    }

    public double getCreditCard() {
        return creditCard;
    }

    public double getTotal() {
        return loan + creditCard;
    }
}
