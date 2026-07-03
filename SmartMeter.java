public class SmartMeter {
    private String meterId;
    private double creditBalance;
    private boolean valveOpen;

    public SmartMeter(String meterId, double creditBalance) {
        this.meterId = meterId;
        this.creditBalance = creditBalance;
        this.valveOpen = true;
    }

    public double loadToken(double amount) {
        creditBalance += amount;
        if (!valveOpen) {
            valveOpen = true;
        }
        return creditBalance;
    }

    public boolean recordConsumption(double litres) {
        if (!valveOpen) {
            return false;
        }
        double cost = litres * 50;
        if (creditBalance <= 0) {
            return false;
        }
        creditBalance -= cost;
        if (creditBalance <= 0) {
            creditBalance = 0;
            valveOpen = false;
        }
        return true;
    }

    public String getMeterId() { return meterId; }
    public double getCreditBalance() { return creditBalance; }
    public boolean isValveOpen() { return valveOpen; }
}
