import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Car{
    private String carId;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double pricePerDay){
        this.carId=carId;
        this.brand=brand;
        this.model=model;
        this.pricePerDay=pricePerDay;
        this.isAvailable= true;
    }

    public String getCarId(){
        return carId;
    }
    public String getBrand(){
        return brand;
    }
    public String getModel(){
        return model;
    }

    public double calculatePrice(int rentalDays){
        return pricePerDay*rentalDays;
    }
    public boolean isAvailable(){
        return isAvailable;
    }

    public void rent(){
        isAvailable=false;
    }
public void returnCar(){
        isAvailable=true;
    }
}

class Customer{
    private String customerId;
    private String name;

    Customer(String customerId, String name){
        this.customerId=customerId;
        this.name=name;
    }
    public String getCustomerId(){
        return customerId;
    }

    public String getName(){
        return name;
    }
}

class Rental{
private Car car;
private Customer customer;
private int days;

public Rental(Car car, Customer customer, int days){
    this.car=car;
    this.customer=customer;
    this.days=days;
    }

   public Car getCar(){
    return car;
   }
   public Customer getCustomer(){
    return customer;
   }
    public int getDays(){
        return days;
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("Car is Not Available For Rent!");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
            System.out.println("Car Returned Successfully!");
        } else {
            System.out.println("Car Was Not Rented!");
        }
    }
    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===== CAR RENTAL SYSTEM! =====");
            System.out.println("1. RENT A CAR");
            System.out.println("2. RETURN A CAR");
            System.out.println("3. EXIT");
            System.out.println("Enter Your Choice");

            int choice = sc.nextInt();
            sc.nextLine();

                if (choice == 1) {
                    System.out.println("\n==== RENT A CAR ====\n");
                    System.out.print("Enter Your Name: ");
                    String customerName = sc.nextLine();

                    System.out.println("\nAvailable Cars: ");
                    for (Car car : cars) {
                        if (car.isAvailable()) {
                            System.out.println(car.getCarId() + " - " + car.getBrand() + " - " + car.getModel());
                        }
                    }
                    System.out.print("\n Enter the car ID you Want to Rent: ");
                    String carId = sc.nextLine();

                    System.out.print("Enter The Number of Days for Rental: ");
                    int rentalDays = sc.nextInt();
                    sc.nextLine();

                    Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                    addCustomer(newCustomer);

                    Car selectCar = null;
                    for (Car car : cars) {
                        if (car.getCarId().equals(carId) && car.isAvailable()) {
                            selectCar = car;
                            break;
                        }
                    }
                    if (selectCar != null) {
                        double totalPrice = selectCar.calculatePrice(rentalDays);
                        System.out.println("\n== RENTAL INFORMATION ==\n");
                        System.out.println("Customer ID : " + newCustomer.getCustomerId());
                        System.out.println("Customer Name : " + newCustomer.getName());
                        System.out.println("Car : " + selectCar.getBrand() + " " + selectCar.getModel());
                        System.out.println("Rental Days : " + rentalDays);
                        System.out.println("Total Price : " + totalPrice); // something wrong

                        System.out.print("\n Confirm Rental (Y/N): ");
                        String confirm = sc.nextLine();

                        if (confirm.equalsIgnoreCase("Y")) {
                            rentCar(selectCar, newCustomer, rentalDays);
                            System.out.println("\n Car Rented Successfully ✅");
                        } else {
                            System.out.println(" Rental Cancelled ❌ \n");
                        }
                    } else {
                        System.out.println("\n Invalid Car Selection ❌ Car Not Available For Rent.\n");
                    }
                } else if (choice == 2) {
                    System.out.println("\n == Return a Car == ");
                    System.out.print("Enter The Car ID You Want To Return : ");
                    String carID = sc.nextLine();

                    Car carToReturn = null;
                    for (Car car : cars) {
                        if (car.getCarId().equals(carID) && !car.isAvailable()) {
                            carToReturn = car;
                            break;
                        }
                    }
                    if (carToReturn != null) {
                        Customer customer = null;
                        for (Rental rental : rentals) {
                            if (rental.getCar() == carToReturn) {
                                customer = rental.getCustomer();
                                break;
                            }
                        }
                        if (customer != null) {
                            returnCar((carToReturn));
                            System.out.println("Car Returned Successfully by " + customer.getName());
                        } else {
                            System.out.println("Car Was not rented or rental Information missing!");
                        }
                    } else {
                        System.out.println("Invalid Car ID or Car is Not Rented.");
                    }
                } else if (choice == 3) {
                    break;
                } else {
                    System.out.println("Invalid Choice ❌ Please Enter a Valid option.");
                }
            }
            System.out.println("THANK YOU FOR USING THE CAR RENTAL SYSTEM \uD83D\uDE0A"); // EMOJI
        }


    public static class Car_Rental_System {
        public static void main(String[] args) {
            CarRentalSystem rentalSystem = new CarRentalSystem();

            Car car1 = new Car("C001", "Toyota", "Camry", 60.0);
            Car car2 = new Car("C002", "Honda", "Accord", 70.0);
            Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
            Car car4 = new Car("C004", "Ford", "F-150", 100.0);
            Car car5 = new Car("C005", "Toyota", "Fortuner", 200.0);

            rentalSystem.addCar(car1);
            rentalSystem.addCar(car2);
            rentalSystem.addCar(car3);
            rentalSystem.addCar(car4);
            rentalSystem.addCar(car5);

            rentalSystem.menu();

        }
    }
}
