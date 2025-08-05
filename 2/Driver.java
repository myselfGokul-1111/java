import java.util.Scanner;

class MotorVehicle {
    String brand;
    double maxSpeed;
    String colour;
    String vehicleNumber;

    public MotorVehicle(String brand, double maxSpeed, String colour, String vehicleNumber) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
        this.colour = colour;
        this.vehicleNumber = vehicleNumber;
    }

    void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Max Speed: " + maxSpeed + " km/h");
        System.out.println("Colour: " + colour);
        System.out.println("Vehicle Number: " + vehicleNumber);
    }
}

class Car extends MotorVehicle {
    String fuelType;

    public Car(String brand, double maxSpeed, String colour, String vehicleNumber, String fuelType) {
        super(brand, maxSpeed, colour, vehicleNumber); // call MotorVehicle constructor
        this.fuelType = fuelType;
    }

    @Override
    void displayInfo() {
        System.out.println("\nCar Details:");
        super.displayInfo(); // call displayInfo from MotorVehicle
        System.out.println("Fuel Type: " + fuelType);
    }

    void displayInfo(boolean displayNumber) {
        if (displayNumber) {
            System.out.println("Vehicle Number: " + vehicleNumber);
        } else {
            System.out.println("Vehicle Number not displayed.");
        }
    }
}

class ElectricCar extends Car {
    int batteryCapacity;
    int chargeRemaining;

    public ElectricCar(String brand, double maxSpeed, String colour, String vehicleNumber, String fuelType,
                       int batteryCapacity, int chargeRemaining) {
        super(brand, maxSpeed, colour, vehicleNumber, fuelType); // call Car constructor
        this.batteryCapacity = batteryCapacity;
        this.chargeRemaining = chargeRemaining;
    }

    @Override
    void displayInfo() {
        System.out.println("\nElectric Car Details:");
        super.displayInfo(); // call displayInfo from Car
        System.out.println("Battery Capacity: " + batteryCapacity + " kWh");
        System.out.println("Charge Remaining: " + chargeRemaining + "%");
    }

    void displayInfo(boolean displayNumber, int chargeRemaining) {
        if (displayNumber) {
            System.out.println("Vehicle Number: " + vehicleNumber);
        }
        System.out.println("Charge Remaining: " + chargeRemaining + "%");
    }
}

public class Driver{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- MotorVehicle Input ---
        System.out.println("Enter MotorVehicle Details:");
        System.out.print("Brand: ");
        String mvBrand = sc.nextLine();
        System.out.print("Max Speed: ");
        double mvSpeed = sc.nextDouble();
        sc.nextLine();
        System.out.print("Colour: ");
        String mvColour = sc.nextLine();
        System.out.print("Vehicle Number: ");
        String mvNumber = sc.nextLine();
        MotorVehicle mv = new MotorVehicle(mvBrand, mvSpeed, mvColour, mvNumber);

        // --- Car Input ---
        System.out.println("\nEnter Car Details:");
        System.out.print("Brand: ");
        String carBrand = sc.nextLine();
        System.out.print("Max Speed: ");
        double carSpeed = sc.nextDouble();
        sc.nextLine();
        System.out.print("Colour: ");
        String carColour = sc.nextLine();
        System.out.print("Vehicle Number: ");
        String carNumber = sc.nextLine();
        System.out.print("Fuel Type: ");
        String fuelType = sc.nextLine();
        Car car = new Car(carBrand, carSpeed, carColour, carNumber, fuelType);

        // --- ElectricCar Input ---
        System.out.println("\nEnter Electric Car Details:");
        System.out.print("Brand: ");
        String ecBrand = sc.nextLine();
        System.out.print("Max Speed: ");
        double ecSpeed = sc.nextDouble();
        sc.nextLine();
        System.out.print("Colour: ");
        String ecColour = sc.nextLine();
        System.out.print("Vehicle Number: ");
        String ecNumber = sc.nextLine();
        System.out.print("Fuel Type: ");
        String ecFuel = sc.nextLine();
        System.out.print("Battery Capacity (kWh): ");
        int batteryCap = sc.nextInt();
        System.out.print("Charge Remaining (%): ");
        int chargeRemain = sc.nextInt();

        ElectricCar eCar = new ElectricCar(ecBrand, ecSpeed, ecColour, ecNumber, ecFuel, batteryCap, chargeRemain);

        // --- Output Section ---
        System.out.println("\n==================== OUTPUT ====================");

        System.out.println("\n--- MotorVehicle ---");
        mv.displayInfo();

        System.out.println("\n--- Car ---");
        car.displayInfo();
        car.displayInfo(true);

        System.out.println("\n--- ElectricCar ---");
        eCar.displayInfo();
        eCar.displayInfo(true, chargeRemain);

        sc.close();
    }
}
