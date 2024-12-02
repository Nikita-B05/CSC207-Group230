package use_case.manage_home;

public class ManageHomeInputData {

    private double newHome;
    private boolean isBuying;

    public ManageHomeInputData(double newHome, boolean isBuying) {
        this.newHome = newHome;
        this.isBuying = isBuying;
    }

    public double getNewHome() {
        return newHome;
    }

    public void setNewHome(double newHome) {
        this.newHome = newHome;
    }

    public boolean isBuying() {
        return isBuying;
    }

    public void setBuying(boolean buying) {
        isBuying = buying;
    }
}
