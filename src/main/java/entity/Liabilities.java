package entity;

public class Liabilities {
    private final int loan;
    private final int creditCard;

    public Liabilities(int loan, int creditCard) {
        this.loan = loan;
        this.creditCard = creditCard;
    }

    public int getLoan() {
        return loan;
    }

    public int getCreditCard() {
        return creditCard;
    }

    public int getTotal() {
        return loan + creditCard;
    }
}
