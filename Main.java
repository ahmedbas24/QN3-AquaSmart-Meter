public class Main {
    public static void main(String[] args) {
        System.out.println("===== QN3: AquaSmart Meter =====\n");

        // Create a meter with 500 UGX credit
        SmartMeter meter = new SmartMeter("M-001", 500);
        System.out.println("Meter " + meter.getMeterId() + " created.");
        System.out.println("Credit: UGX " + meter.getCreditBalance());
        System.out.println("Valve open: " + meter.isValveOpen());

        // Consume 5 litres (5 * 50 = 250 UGX)
        System.out.println("\n--- Consuming 5 litres (cost: UGX 250) ---");
        boolean dispensed = meter.recordConsumption(5);
        System.out.println("Water dispensed: " + dispensed);
        System.out.println("Credit remaining: UGX " + meter.getCreditBalance());
        System.out.println("Valve open: " + meter.isValveOpen());

        // Consume 6 litres (6 * 50 = 300 UGX, but only 250 left)
        System.out.println("\n--- Consuming 6 litres (cost: UGX 300, exceeds credit) ---");
        dispensed = meter.recordConsumption(6);
        System.out.println("Water dispensed: " + dispensed);
        System.out.println("Credit remaining: UGX " + meter.getCreditBalance());
        System.out.println("Valve open: " + meter.isValveOpen());

        // Try to consume with closed valve
        System.out.println("\n--- Trying to consume with closed valve ---");
        dispensed = meter.recordConsumption(1);
        System.out.println("Water dispensed: " + dispensed);

        // Load a new token
        System.out.println("\n--- Loading token of UGX 1000 ---");
        double newBalance = meter.loadToken(1000);
        System.out.println("New credit balance: UGX " + newBalance);
        System.out.println("Valve open: " + meter.isValveOpen());

        // ===== JUnit-style manual tests =====
        System.out.println("\n===== Running Manual JUnit-style Tests =====");

        // Test 1: Loading a token re-opens a closed valve
        SmartMeter testMeter1 = new SmartMeter("TEST-01", 100);
        testMeter1.recordConsumption(2); // 2 * 50 = 100 UGX, exhausts credit
        assert !testMeter1.isValveOpen() : "FAIL: Valve should be closed";
        testMeter1.loadToken(500);
        assert testMeter1.isValveOpen() : "FAIL: Valve should re-open after token";
        System.out.println("TEST 1 PASSED: Loading token re-opens closed valve.");

        // Test 2: Consumption beyond credit closes the valve
        SmartMeter testMeter2 = new SmartMeter("TEST-02", 200);
        testMeter2.recordConsumption(5); // 5 * 50 = 250 > 200
        assert !testMeter2.isValveOpen() : "FAIL: Valve should be closed";
        assert testMeter2.getCreditBalance() == 0 : "FAIL: Balance should be 0";
        System.out.println("TEST 2 PASSED: Consumption beyond credit closes valve.");
    }
}
