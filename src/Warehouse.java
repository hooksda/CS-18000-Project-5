import java.io.File;
import java.util.*;

/**
 * @author Jaewook Lee, Daniel Hooks
 * @version 12/9/18
 */

public class Warehouse {
    final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File("files/Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;

    public static void printStatisticsReport(double profits, int packagesShipped, int numberOfPackages) {
        System.out.print("==========Statistics==========\n");
        String p = String.format("%s ", "Profits: " + profits + "\n");
        String ps = String.format("%s ", "Packages Shipped: " + packagesShipped + "\n");
        String np = String.format("%s ", "Packages in Warehouse: " + numberOfPackages + "\n");
        System.out.println(p + ps + np);
        System.out.println("==============================");
    }

    public static void main(String[] args) {
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
        while (true) {
            if (primeDayYeet) {
                System.out.println(firstMenu);
            } else {
                System.out.println(secondMenu);
            }
            switch (s.nextInt()) {
                case 1:
                    System.out.println("Enter Package ID:");
                    s.nextLine();
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
                    System.out.println(packed.shippingLabel());
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
                                s.nextLine();
                                String licensePlate = s.nextLine();
                                System.out.println("Enter Maximum Carry Weight:");
                                Double carryWeight = s.nextDouble();
                                Truck truck = new Truck(licensePlate, carryWeight);
                                vehicles.add(truck);
                                something = false;
                                break;
                            case 2:
                                System.out.println("Enter License Plate No.:");
                                s.nextLine();
                                licensePlate = s.nextLine();
                                System.out.println("Enter Maximum Carry Weight:");
                                carryWeight = s.nextDouble();
                                Drone drone = new Drone(licensePlate, carryWeight);
                                vehicles.add(drone);
                                something = false;
                                break;
                            case 3:
                                System.out.println("Enter License Plate No.:");
                                s.nextLine();
                                licensePlate = s.nextLine();
                                System.out.println("Enter Maximum Carry Weight:");
                                carryWeight = s.nextDouble();
                                CargoPlane cargoPlane = new CargoPlane(licensePlate, carryWeight);
                                vehicles.add(cargoPlane);
                                something = false;
                                break;
                            default:
                                System.out.println(errorMessage);

                        }
                    }
                    break;
                case 3:
                    if (primeDayYeet) {
                        primeDayYeet = false;
                    } else {
                        primeDayYeet = true;
                    }
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
                                System.out.println("ZIP Code Options:\n" +
                                        "1) Send to first ZIP Code\n" +
                                        "2) Send to mode of ZIP Codes");
                                switch (s.nextInt()) {
                                    case 1:
                                        for (int i = 0; i < vehicles.size(); i++) {
                                            if (vehicles.get(i) instanceof Truck) {
                                                vehicles.get(i)
                                                        .setZipDest(packages.get(0).getDestination().getZipCode());
                                                vehicles.get(i).fill(packages);
                                                System.out.println(((Truck) vehicles.get(i)).report());
                                                profitingOffSlaveLabor += ((Truck) vehicles.get(i)).getProfit();
                                                packagesShipped += vehicles.get(i).getPackages().size();
                                                vehicles.get(i).empty();
                                                break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        int c;
                                        ArrayList<Integer> a = new ArrayList<>();
                                        for (int i = 0; i < vehicles.size(); i++) {
                                            if (vehicles.get(i) instanceof Truck) {
                                                for (int k = 0; k < vehicles.get(i).getPackages().size(); k++) {
                                                    c = 0;
                                                    for (int j = 0; j < vehicles.get(i).getPackages().size(); j++) {
                                                        if (vehicles.get(i).getPackages().get(k).getDestination()
                                                                .getZipCode()
                                                                == vehicles.get(i).getPackages().get(j)
                                                                .getDestination().getZipCode()) {
                                                            c++;
                                                        }
                                                    }
                                                    a.add(c);
                                                }
                                                int max = 0;
                                                int maxy = 0;
                                                for (int l = 0; l < a.size(); l++) {
                                                    if (a.get(l) > max) {
                                                        max = a.get(l);
                                                        maxy = l;
                                                    }
                                                }
                                                vehicles.get(i).setZipDest(vehicles.get(i).getPackages()
                                                        .get(i).getDestination().getZipCode());
                                                vehicles.get(i).fill(packages);
                                                System.out.println(((Truck) vehicles.get(i)).report());
                                                profitingOffSlaveLabor += ((Truck) vehicles.get(i)).getProfit();
                                                packagesShipped += vehicles.get(i).getPackages().size();
                                                vehicles.get(i).empty();
                                                break;
                                            }
                                        }
                                        break;
                                }

                            case 2:
                                System.out.println("ZIP Code Options:\n" +
                                        "1) Send to first ZIP Code\n" +
                                        "2) Send to mode of ZIP Codes");
                                switch (s.nextInt()) {
                                    case 1:
                                        for (int i = 0; i < vehicles.size(); i++) {
                                            if (vehicles.get(i) instanceof Drone) {
                                                vehicles.get(i)
                                                        .setZipDest(packages.get(0).getDestination().getZipCode());
                                                vehicles.get(i).fill(packages);
                                                System.out.println(((Drone) vehicles.get(i)).report());
                                                profitingOffSlaveLabor += ((Drone) vehicles.get(i)).getProfit();
                                                packagesShipped += vehicles.get(i).getPackages().size();
                                                vehicles.get(i).empty();
                                                break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        int c;
                                        ArrayList<Integer> a = new ArrayList<>();
                                        for (int i = 0; i < vehicles.size(); i++) {
                                            if (vehicles.get(i) instanceof Drone) {
                                                for (int k = 0; k < vehicles.get(i).getPackages().size(); k++) {
                                                    c = 0;
                                                    for (int j = 0; j < vehicles.get(i).getPackages().size(); j++) {
                                                        if (vehicles.get(i).getPackages().get(k).getDestination()
                                                                .getZipCode()
                                                                == vehicles.get(i).getPackages().get(j)
                                                                .getDestination().getZipCode()) {
                                                            c++;
                                                        }
                                                    }
                                                    a.add(c);
                                                }
                                                int max = 0;
                                                int maxy = 0;
                                                for (int l = 0; l < a.size(); l++) {
                                                    if (a.get(l) > max) {
                                                        max = a.get(l);
                                                        maxy = l;
                                                    }
                                                }
                                                vehicles.get(i).setZipDest(vehicles.get(i).getPackages()
                                                        .get(i).getDestination().getZipCode());
                                                vehicles.get(i).fill(packages);
                                                System.out.println(((Drone) vehicles.get(i)).report());
                                                profitingOffSlaveLabor += ((Drone) vehicles.get(i)).getProfit();
                                                packagesShipped += vehicles.get(i).getPackages().size();
                                                vehicles.get(i).empty();
                                                break;
                                            }
                                        }
                                }
                                break;
                            case 3:
                                System.out.println("ZIP Code Options:\n" +
                                        "1) Send to first ZIP Code\n" +
                                        "2) Send to mode of ZIP Codes");
                                switch (s.nextInt()) {
                                    case 1:
                                        for (int i = 0; i < vehicles.size(); i++) {
                                            if (vehicles.get(i) instanceof CargoPlane) {
                                                vehicles.get(i)
                                                        .setZipDest(packages.get(0).getDestination().getZipCode());
                                                vehicles.get(i).fill(packages);
                                                System.out.println(((CargoPlane) vehicles.get(i)).report());
                                                profitingOffSlaveLabor += ((CargoPlane) vehicles.get(i)).getProfit();
                                                packagesShipped += vehicles.get(i).getPackages().size();
                                                vehicles.get(i).empty();
                                                break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        int c;
                                        ArrayList<Integer> a = new ArrayList<>();
                                        for (int i = 0; i < vehicles.size(); i++) {
                                            if (vehicles.get(i) instanceof CargoPlane) {
                                                for (int k = 0; k < vehicles.get(i).getPackages().size(); k++) {
                                                    c = 0;
                                                    for (int j = 0; j < vehicles.get(i).getPackages().size(); j++) {
                                                        if (vehicles.get(i).getPackages().get(k).getDestination()
                                                                .getZipCode()
                                                                == vehicles.get(i).getPackages().get(j)
                                                                .getDestination().getZipCode()) {
                                                            c++;
                                                        }
                                                    }
                                                    a.add(c);
                                                }
                                                int max = 0;
                                                int maxy = 0;
                                                for (int l = 0; l < a.size(); l++) {
                                                    if (a.get(l) > max) {
                                                        max = a.get(l);
                                                        maxy = l;
                                                    }
                                                }
                                                vehicles.get(i).setZipDest(vehicles.get(i).getPackages()
                                                        .get(i).getDestination().getZipCode());
                                                vehicles.get(i).fill(packages);
                                                System.out.println(((CargoPlane) vehicles.get(i)).report());
                                                profitingOffSlaveLabor += ((CargoPlane) vehicles.get(i)).getProfit();
                                                packagesShipped += vehicles.get(i).getPackages().size();
                                                vehicles.get(i).empty();
                                                break;
                                            }
                                        }
                                }
                                break;
                            case 4:
                                System.out.println("ZIP Code Options:\n" +
                                        "1) Send to first ZIP Code\n" +
                                        "2) Send to mode of ZIP Codes");
                                switch (s.nextInt()) {
                                    case 1:
                                        for (int i = 0; i < vehicles.size(); i++) {
                                            if (vehicles.get(i) instanceof Truck) {
                                                vehicles.get(i)
                                                        .setZipDest(packages.get(0).getDestination().getZipCode());
                                                vehicles.get(i).fill(packages);
                                                System.out.println(((Truck) vehicles.get(i)).report());
                                                profitingOffSlaveLabor += ((Truck) vehicles.get(i)).getProfit();
                                                packagesShipped += vehicles.get(i).getPackages().size();
                                                vehicles.get(i).empty();
                                                break;
                                            } else if (vehicles.get(i) instanceof Drone) {
                                                vehicles.get(i)
                                                        .setZipDest(packages.get(0).getDestination().getZipCode());
                                                vehicles.get(i).fill(packages);
                                                System.out.println(((Drone) vehicles.get(i)).report());
                                                profitingOffSlaveLabor += ((Drone) vehicles.get(i)).getProfit();
                                                packagesShipped += vehicles.get(i).getPackages().size();
                                                vehicles.get(i).empty();
                                            } else if (vehicles.get(i) instanceof CargoPlane) {
                                                vehicles.get(i)
                                                        .setZipDest(packages.get(0).getDestination().getZipCode());
                                                vehicles.get(i).fill(packages);
                                                System.out.println(((CargoPlane) vehicles.get(i)).report());
                                                profitingOffSlaveLabor += ((CargoPlane) vehicles.get(i)).getProfit();
                                                packagesShipped += vehicles.get(i).getPackages().size();
                                                vehicles.get(i).empty();
                                            }
                                        }
                                        break;
                                    case 2:
                                        int c;
                                        ArrayList<Integer> a = new ArrayList<>();
                                        for (int i = 0; i < vehicles.size(); i++) {
                                            if (vehicles.get(i) instanceof Truck) {
                                                for (int k = 0; k < vehicles.get(i).getPackages().size(); k++) {
                                                    c = 0;
                                                    for (int j = 0; j < vehicles.get(i).getPackages().size(); j++) {
                                                        if (vehicles.get(i).getPackages().get(k).getDestination()
                                                                .getZipCode()
                                                                == vehicles.get(i).getPackages().get(j)
                                                                .getDestination().getZipCode()) {
                                                            c++;
                                                        }
                                                    }
                                                    a.add(c);
                                                }
                                                int max = 0;
                                                int maxy = 0;
                                                for (int l = 0; l < a.size(); l++) {
                                                    if (a.get(l) > max) {
                                                        max = a.get(l);
                                                        maxy = l;
                                                    }
                                                }
                                                vehicles.get(i).setZipDest(vehicles.get(i).getPackages()
                                                        .get(i).getDestination().getZipCode());
                                                vehicles.get(i).fill(packages);
                                                System.out.println(((Truck) vehicles.get(i)).report());
                                                profitingOffSlaveLabor += ((Truck) vehicles.get(i)).getProfit();
                                                packagesShipped += vehicles.get(i).getPackages().size();
                                                vehicles.get(i).empty();
                                                break;
                                            } else if (vehicles.get(i) instanceof Drone) {
                                                for (int k = 0; k < vehicles.get(i).getPackages().size(); k++) {
                                                    c = 0;
                                                    for (int j = 0; j < vehicles.get(i).getPackages().size(); j++) {
                                                        if (vehicles.get(i).getPackages().get(k).getDestination()
                                                                .getZipCode()
                                                                == vehicles.get(i).getPackages().get(j)
                                                                .getDestination().getZipCode()) {
                                                            c++;
                                                        }
                                                    }
                                                    a.add(c);
                                                }
                                                int max = 0;
                                                int maxy = 0;
                                                for (int l = 0; l < a.size(); l++) {
                                                    if (a.get(l) > max) {
                                                        max = a.get(l);
                                                        maxy = l;
                                                    }
                                                }
                                                vehicles.get(i).setZipDest(vehicles.get(i).getPackages()
                                                        .get(i).getDestination().getZipCode());
                                                vehicles.get(i).fill(packages);
                                                System.out.println(((Drone) vehicles.get(i)).report());
                                                profitingOffSlaveLabor += ((Truck) vehicles.get(i)).getProfit();
                                                packagesShipped += vehicles.get(i).getPackages().size();
                                                vehicles.get(i).empty();
                                                break;
                                            } else if (vehicles.get(i) instanceof CargoPlane) {
                                                for (int k = 0; k < vehicles.get(i).getPackages().size(); k++) {
                                                    c = 0;
                                                    for (int j = 0; j < vehicles.get(i).getPackages().size(); j++) {
                                                        if (vehicles.get(i).getPackages().get(k).getDestination()
                                                                .getZipCode()
                                                                == vehicles.get(i).getPackages().get(j)
                                                                .getDestination().getZipCode()) {
                                                            c++;
                                                        }
                                                    }
                                                    a.add(c);
                                                }
                                                int max = 0;
                                                int maxy = 0;
                                                for (int l = 0; l < a.size(); l++) {
                                                    if (a.get(l) > max) {
                                                        max = a.get(l);
                                                        maxy = l;
                                                    }
                                                }
                                                vehicles.get(i).setZipDest(vehicles.get(i).getPackages()
                                                        .get(i).getDestination().getZipCode());
                                                vehicles.get(i).fill(packages);
                                                System.out.println(((CargoPlane) vehicles.get(i)).report());
                                                profitingOffSlaveLabor += ((CargoPlane) vehicles.get(i)).getProfit();
                                                packagesShipped += vehicles.get(i).getPackages().size();
                                                vehicles.get(i).empty();
                                                break;
                                            }
                                        }
                                        break;
                                }
                                break;
                        }
                    }
                    break;
                case 5:
                    printStatisticsReport(profitingOffSlaveLabor, packagesShipped,
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