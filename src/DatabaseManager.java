import java.io.*;
import java.util.ArrayList;

/**
 * <h1>Database Manager</h1>
 * 
 * Used to locally save and retrieve data.
 */
public class DatabaseManager {

    /**
     * Creates an ArrayList of Vehicles from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * If filePath does not exist, a blank ArrayList will be returned.
     * 
     * @param file CSV File
     * @return ArrayList of vehicles
     */
    public static ArrayList<Vehicle> loadVehicles(File file) {
       //TODO
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> strings = new ArrayList<>();
            for (String line; (line = br.readLine()) != null;) {
                strings.add(line);
            }
            for (int i = 0; i < strings.size(); i++) {
                String[] details = strings.get(i).split(",");
                if (details.length == 2) {
                    if (details[0].equals("Drone")) {
                       vehicles.add(new Drone(details[1], Double.parseDouble(details[2])));
                    } else if (details[0].equals("Truck")) {
                        vehicles.add(new Truck(details[1], Double.parseDouble(details[2])));
                    } else if (details[0].equals("Cargo Plane")) {
                        vehicles.add(new CargoPlane(details[1], Double.parseDouble(details[2])));
                    }
                }
            }
        } catch (IOException e) {

        }
        return vehicles;
    }

    
    
    
    
    /**
     * Creates an ArrayList of Packages from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     * 
     * If filePath does not exist, a blank ArrayList will be returned.
     * 
     * @param file CSV File
     * @return ArrayList of packages
     */
    public static ArrayList<Package> loadPackages(File file) {
    	//TODO
        ArrayList<Package> packages = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> strings = new ArrayList<>();
            for (String line; (line = br.readLine()) != null;) {
                strings.add(line);
            }
            String id = "";
            String productName = "";
            double weight = 0.0;
            String name = "";
            double price = 0.0;
            String address = "";
            String city = "";
            String state = "";
            int zipCode = 0;
            for (int i = 0; i < strings.size(); i++) {
                String[] details = strings.get(i).split(",");
                if (details.length == 9) {
                    id = details[0];
                    productName = details[1];
                    weight = Double.parseDouble(details[2]);
                    name = details[3];
                    price = Double.parseDouble(details[4]);
                    address = details[5];
                    city = details[6];
                    state = details[7];
                    zipCode = Integer.parseInt(details[8]);
                }
                ShippingAddress shippingAddress = new ShippingAddress(name, address, city, state, zipCode);
                packages.add(new Package(id, productName, weight, price, shippingAddress));
            }
        } catch (IOException e) {

        }
        return packages;
    }
    
    
    
    
    

    /**
     * Returns the total Profits from passed text file. If the file does not exist 0
     * will be returned.
     * 
     * @param file file where profits are stored
     * @return profits from file
     */
    public static double loadProfit(File file) {
    	//TODO
        FileReader fr = null;
        double profit = 0.0;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String profit1 = br.readLine();
            profit = Double.parseDouble(profit1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profit;
    }

    
    
    
    
    /**
     * Returns the total number of packages shipped stored in the text file. If the
     * file does not exist 0 will be returned.
     * 
     * @param file file where number of packages shipped are stored
     * @return number of packages shipped from file
     */
    public static int loadPackagesShipped(File file) {
    	//TODO
        int shipped = 0;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            shipped = Integer.parseInt(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shipped;
    }

    
    
    
    /**
     * Returns whether or not it was Prime Day in the previous session. If file does
     * not exist, returns false.
     * 
     * @param file file where prime day is stored
     * @return whether or not it is prime day
     */
    public static boolean loadPrimeDay(File file) {
    	//TODO
    }

    
    
    
    
    /**
     * Saves (writes) vehicles from ArrayList of vehicles to file in CSV format one vehicle per line.
     * Each line (vehicle) has following fields separated by comma in the same order.
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * 
     * @param file     File to write vehicles to
     * @param vehicles ArrayList of vehicles to save to file
     */
    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
    	//TODO
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i) instanceof Truck) {
                    bw.write("Truck," + vehicles.get(i).getLicensePlate() + "," + vehicles.get(i).getMaxWeight());
                }else if (vehicles.get(i) instanceof Drone) {
                    bw.write("Drone," + vehicles.get(i).getLicensePlate() + "," + vehicles.get(i).getMaxWeight());
                }else if (vehicles.get(i) instanceof CargoPlane) {
                    bw.write("Cargo Plane," + vehicles.get(i).getLicensePlate() + "," + vehicles.get(i).getMaxWeight());
                }
            }
            bw.flush();
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    
    /**
     * Saves (writes) packages from ArrayList of package to file in CSV format one package per line.
     * Each line (package) has following fields separated by comma in the same order.
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     * 
     * @param file     File to write packages to
     * @param packages ArrayList of packages to save to file
     */
    public static void savePackages(File file, ArrayList<Package> packages) {
    	//TODO
    }

    
    
    
    /**
     * Saves profit to text file.
     * 
     * @param file   File to write profits to
     * @param profit Total profits
     */

    public static void saveProfit(File file, double profit) {
    	//TODO
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Double.toString(profit));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    /**
     * Saves number of packages shipped to text file.
     * 
     * @param file      File to write profits to
     * @param nPackages Number of packages shipped
     */

    public static void savePackagesShipped(File file, int nPackages) {
    	//TODO
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(nPackages);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    
    /**
     * Saves status of prime day to text file. If it is primeDay "1" will be
     * writtern, otherwise "0" will be written.
     * 
     * @param file     File to write profits to
     * @param primeDay Whether or not it is Prime Day
     */

    public static void savePrimeDay(File file, boolean primeDay) {
    	//TODO
    }
}