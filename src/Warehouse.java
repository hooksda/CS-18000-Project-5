import javax.xml.crypto.Data;
import java.awt.image.DataBuffer;
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

    public static void printStatisticReport(double profits, int packagesShipped, int numberOfPackages) {
        System.out.print("==========Statistics==========\n");
        String.format("%s ", "Profits: " + profits + "\n");
        String.format("%s ", "Packages Shipped: " + packagesShipped + "\n");
        String.format("%s ", "Packages in Warehouse: " + numberOfPackages + "\n");
        System.out.println("==============================");

    }

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
        ArrayList<Package> packages = DatabaseManager.loadPackages(PACKAGE_FILE);
        ArrayList<Vehicle> vehicles = DatabaseManager.loadVehicles(VEHICLE_FILE);
        Double profitingOffSlaveLabor = DatabaseManager.loadProfit(PROFIT_FILE);
        int packagesShipped = DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
        Boolean primeDayYeet = DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);

        //2) Show menu and handle user inputs
        asdf:
        while (prime) {
            if (primeDayYeet) {
                System.out.println(firstMenu);
            } else {
                System.out.println(secondMenu);
            }
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
                    packages.add(packed);
                    packed.shippingLabel();
                    break;
                case 2:
                    boolean something = true;
                    while (something) {
                        System.out.println("Vehicle Options: \n" +
                                "1) Truck\n" +
                                "2) Drone\n" +
                                "3) Cargo Plane");
                        switch (s.nextInt()) {
                            case 1:
                                System.out.println("Enter License Plate No.:");
                                String licensePlate = s.nextLine();
                                System.out.println("Enter Maximum Carry Weight:");
                                int carryWeight = s.nextInt();
                                Truck truck = new Truck(licensePlate, carryWeight);
                                vehicles.add(truck);
                                something = false;
                                break;
                            case 2:
                                System.out.println("Enter License Plate No.:");
                                licensePlate = s.nextLine();
                                System.out.println("Enter Maximum Carry Weight:");
                                carryWeight = s.nextInt();
                                Drone drone = new Drone(licensePlate, carryWeight);
                                vehicles.add(drone);
                                something = false;
                                break;
                            case 3:
                                System.out.println("Enter License Plate No.:");
                                licensePlate = s.nextLine();
                                System.out.println("Enter Maximum Carry Weight:");
                                carryWeight = s.nextInt();
                                CargoPlane cargoPlane = new CargoPlane(licensePlate, carryWeight);
                                vehicles.add(cargoPlane);
                                something = false;
                                break;
                            default:
                                System.out.println(errorMessage);

                        }
                    }
                case 3:

                    break;
                case 4:
                    if (vehicles.size() == 0) {
                        System.out.println("Error: No vehicles available.");
                    } else if (packages.size() == 0) {
                        System.out.println("Error: No packages available.");
                    } else {
                        System.out.println("Options:\n" +
                                "1) Send Truck\n" +
                                "2) Send Drone\n" +
                                "3) Send Cargo Plane\n" +
                                "4) Send First Available");
                        switch (s.nextInt()) {
                            case 1:
                                for (int i = 0; i < vehicles.size(); i++) {
                                    if (vehicles.get(i) instanceof Truck) {
                                        profitingOffSlaveLabor += ((Truck) vehicles.get(i)).getProfit();
                                        packagesShipped += vehicles.get(i).getPackages().size();
                                        vehicles.remove(i);
                                        break;
                                    }
                                }
                            case 2:
                                for (int i = 0; i < vehicles.size(); i++) {
                                    if (vehicles.get(i) instanceof Drone) {
                                        profitingOffSlaveLabor += ((Drone) vehicles.get(i)).getProfit();
                                        packagesShipped += vehicles.get(i).getPackages().size();
                                        vehicles.remove(i);
                                        break;
                                    }
                                }
                            case 3:
                                for (int i = 0; i < vehicles.size(); i++) {
                                    if (vehicles.get(i) instanceof CargoPlane) {
                                        profitingOffSlaveLabor += ((CargoPlane) vehicles.get(i)).getProfit();
                                        packagesShipped += vehicles.get(i).getPackages().size();
                                        vehicles.remove(i);
                                        break;
                                    }
                                }
                            case 4:
                                for (int i = 0; i < vehicles.size(); i++) {
                                    if (vehicles.get(i) != null) {
                                        if (vehicles.get(i) instanceof CargoPlane) {
                                            profitingOffSlaveLabor += ((CargoPlane) vehicles.get(i)).getProfit();
                                            packagesShipped += vehicles.get(i).getPackages().size();
                                            vehicles.remove(i);
                                            break;
                                        } else if (vehicles.get(i) instanceof Drone) {
                                            profitingOffSlaveLabor += ((Drone) vehicles.get(i)).getProfit();
                                            packagesShipped += vehicles.get(i).getPackages().size();
                                            vehicles.remove(i);
                                            break;
                                        } else if (vehicles.get(i) instanceof Truck) {
                                            profitingOffSlaveLabor += ((Truck) vehicles.get(i)).getProfit();
                                            packagesShipped += vehicles.get(i).getPackages().size();
                                            vehicles.remove(i);
                                            break;
                                        }
                                    }
                                }
                        }
                    }

                case 5:
                    printStatisticReport(profitingOffSlaveLabor, packagesShipped,
                            packages.size() - packagesShipped);
                    break;
                case 6:
                    break asdf;
                default:
                    System.out.println(errorMessage);
            }


            //3) save data (vehicle, packages, profits, packages shipped and primeDay) to files (overwriting them) using DatabaseManager
            DatabaseManager.saveVehicles(VEHICLE_FILE, vehicles);
            DatabaseManager.savePackages(PACKAGE_FILE, packages);
            DatabaseManager.savePackagesShipped(N_PACKAGES_FILE, packagesShipped);
            DatabaseManager.saveProfit(PROFIT_FILE, profitingOffSlaveLabor);
        }

    }
}