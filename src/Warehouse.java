import java.io.File;
import java.util.*;

/**
 * <h1>Warehouse</h1>
 */

public class Warehouse {
    final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File(folderPath + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;

    /**
     * Main Method
     *
     * @param args list of command line arguements
     */
    public static void main(String[] args) {
        //TODO
        Boolean prime = true;
        Scanner s = new Scanner(System.in);
        String firstMenu = "==========Options==========\n" +
                "1) Add Package\n" +
                "2) Add Vehicle\n" +
                "3) Activate Prime Day\n" +
                "4) Send Vehicle\n" +
                "5) Print Statistics\n" +
                "6) Exit\n" +
                "===========================\n";
        String secondMenu = "==========Options==========\n" +
                "1) Add Package\n" +
                "2) Add Vehicle\n" +
                "3) Deactivate Prime Day\n" +
                "4) Send Vehicle\n" +
                "5) Print Statistics\n" +
                "6) Exit\n" +
                "===========================\n";
        String errorMessage = "Error: Option not available.";
        String packageID = "";
        String productName = "";
        String weight = "";
        String price = "";
        String buyerName = "";
        String address = "";
        String city = "";
        String state = "";
        String zipCode = "";
        //1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager


        //2) Show menu and handle user inputs
        asdf:
        while (prime) {
            System.out.println(firstMenu);
            switch (s.nextInt()) {
                case 1:
                    System.out.println("Enter Package ID:");
                    packageID = s.nextLine();
                    System.out.println("Enter Product Name:");
                    productName = s.nextLine();
                    System.out.println("Enter weight:");
                    weight = s.nextLine();
                    System.out.println("Enter Price:");
                    price = s.nextLine();
                    System.out.println("Enter Buyer Name:");
                    buyerName = s.nextLine();
                    System.out.println("Enter Address:");
                    address = s.nextLine();
                    System.out.println("Enter City:");
                    city = s.nextLine();
                    System.out.println("Enter State:");
                    state = s.nextLine();
                    System.out.println("Enter Zip Code:");
                    zipCode = s.nextLine();
                    System.out.println();
                    System.out.println();
                    ShippingAddress destination = new ShippingAddress(buyerName, address, city, state,
                            Integer.parseInt(zipCode));
                    Package packed = new Package(packageID, productName, Double.parseDouble(weight),
                            Double.parseDouble(price), destination);
                    packed.shippingLabel();
                    break;
                case 2:
                    System.out.println("Vehicle Options: \n" +
                            "1) Truck\n" +
                            "2) Drone\n" +
                            "3) Cargo Plane");
                    while (true) {
                        switch (s.nextInt()) {
                            case 1:
                                System.out.println("Enter License Plate No.:");
                                String licensePlate = s.nextLine();
                                System.out.println("Enter Maximum Carry Weight:");
                                int carryWeight = s.nextInt();
                                break;
                            case 2:
                                System.out.println("Enter License Plate No.:");
                                licensePlate = s.nextLine();
                                System.out.println("Enter Maximum Carry Weight:");
                                carryWeight = s.nextInt();
                                break;
                            case 3:
                                System.out.println("Enter License Plate No.:");
                                licensePlate = s.nextLine();
                                System.out.println("Enter Maximum Carry Weight:");
                                carryWeight = s.nextInt();
                                break;
                            default:
                                System.out.println(errorMessage);
                                System.out.println("Vehicle Options: \n" +
                                        "1) Truck\n" +
                                        "2) Drone\n" +
                                        "3) Cargo Plane");
                        }
                        break;
                    }
                case 3:
                    prime = false;
                    break;
                case 4:
                case 5:
                case 6:
                    break asdf;
                default:
                    System.out.println(errorMessage);
            }
            while (!prime) {
                System.out.println(secondMenu);
                switch (s.nextInt()) {
                    case 1:
                        System.out.println("Enter Package ID:");
                        packageID = s.nextLine();
                        System.out.println("Enter Product Name:");
                        productName = s.nextLine();
                        System.out.println("Enter weight:");
                        weight = s.nextLine();
                        System.out.println("Enter Price:");
                        price = s.nextLine();
                        System.out.println("Enter Buyer Name:");
                        buyerName = s.nextLine();
                        System.out.println("Enter Address:");
                        address = s.nextLine();
                        System.out.println("Enter City:");
                        city = s.nextLine();
                        System.out.println("Enter State:");
                        state = s.nextLine();
                        System.out.println("Enter Zip Code:");
                        zipCode = s.nextLine();
                        System.out.println();
                        System.out.println();
                        ShippingAddress destination = new ShippingAddress(buyerName, address, city, state,
                                Integer.parseInt(zipCode));
                        Package packed = new Package(packageID, productName, Double.parseDouble(weight),
                                Double.parseDouble(price), destination);
                        packed.shippingLabel();
                        break;
                    case 2:
                        System.out.println("Vehicle Options: \n" +
                                "1) Truck\n" +
                                "2) Drone\n" +
                                "3) Cargo Plane");
                        while (true) {
                            switch (s.nextInt()) {
                                case 1:
                                    System.out.println("Enter License Plate No.:");
                                    String licensePlate = s.nextLine();
                                    System.out.println("Enter Maximum Carry Weight:");
                                    int carryWeight = s.nextInt();
                                    break;
                                case 2:
                                    System.out.println("Enter License Plate No.:");
                                    licensePlate = s.nextLine();
                                    System.out.println("Enter Maximum Carry Weight:");
                                    carryWeight = s.nextInt();
                                    break;
                                case 3:
                                    System.out.println("Enter License Plate No.:");
                                    licensePlate = s.nextLine();
                                    System.out.println("Enter Maximum Carry Weight:");
                                    carryWeight = s.nextInt();
                                    break;
                                default:
                                    System.out.println(errorMessage);
                                    System.out.println("Vehicle Options: \n" +
                                            "1) Truck\n" +
                                            "2) Drone\n" +
                                            "3) Cargo Plane");
                            }
                            break;
                        }
                    case 3:
                        prime = true;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        break asdf;
                    default:
                        System.out.println(errorMessage);
                }
            }


            //3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager


        }

    }
}