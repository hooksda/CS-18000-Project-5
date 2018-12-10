import java.io.*;
import java.util.ArrayList;

/**
 * @author Jaewook Lee, Daniel Hooks, lab sec 11
 * @version 12/9/18
 */

public class DatabaseManager {
    public static ArrayList<Vehicle> loadVehicles(File file) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> strings = new ArrayList<>();
            for (String line; (line = br.readLine()) != null; ) {
                strings.add(line);
            }
            for (int i = 0; i < strings.size(); i++) {
                String[] details = strings.get(i).split(",");
                if (details.length == 3) {
                    if (details[0].equals("Drone")) {
                        vehicles.add(new Drone(details[1], Double.parseDouble(details[2])));
                    } else if (details[0].equals("Truck")) {
                        vehicles.add(new Truck(details[1], Double.parseDouble(details[2])));
                    } else if (details[0].equals("Cargo Plane")) {
                        vehicles.add(new CargoPlane(details[1], Double.parseDouble(details[2])));
                    }
                }
            }
            br.close();
            return vehicles;
        } catch (IOException e) {
            int c;
        }
        return vehicles;
    }

    public static ArrayList<Package> loadPackages(File file) {
        ArrayList<Package> packages = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> strings = new ArrayList<>();
            for (String line; (line = br.readLine()) != null; ) {
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
                    price = Double.parseDouble(details[3]);
                    name = details[4];
                    address = details[5];
                    city = details[6];
                    state = details[7];
                    zipCode = Integer.parseInt(details[8]);
                }
                ShippingAddress shippingAddress = new ShippingAddress(name, address, city, state, zipCode);
                packages.add(new Package(id, productName, weight, price, shippingAddress));
            }
            br.close();
        } catch (IOException e) {
            int c;
        }
        return packages;
    }


    public static double loadProfit(File file) {
        FileReader fr = null;
        double profit = 0.0;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String profit1 = br.readLine();
            profit = Double.parseDouble(profit1);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profit;
    }


    public static int loadPackagesShipped(File file) {
        int shipped = 0;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            shipped = Integer.parseInt(line);
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return shipped;
    }


    public static boolean loadPrimeDay(File file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            if (line.equals("0")) {
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            return false;
        }
    }

    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i) instanceof Truck) {
                    bw.write("Truck," + vehicles.get(i).getLicensePlate() + "," +
                            vehicles.get(i).getMaxWeight() + "\n");
                } else if (vehicles.get(i) instanceof Drone) {
                    bw.write("Drone," + vehicles.get(i).getLicensePlate() + "," +
                            vehicles.get(i).getMaxWeight() + "\n");
                } else if (vehicles.get(i) instanceof CargoPlane) {
                    bw.write("Cargo Plane," + vehicles.get(i).getLicensePlate() + "," +
                            vehicles.get(i).getMaxWeight() + "\n");
                }
            }
            bw.flush();
            fw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void savePackages(File file, ArrayList<Package> packages) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < packages.size(); i++) {
                bw.write(packages.get(i).getID() + "," + packages.get(i).getProduct() +
                        "," + packages.get(i).getWeight() + "," + packages.get(i).getPrice() + "," +
                        packages.get(i).getDestination().getName() + ","
                        + packages.get(i).getDestination().getAddress() + "," +
                        packages.get(i).getDestination().getCity() + "," + packages.get(i).getDestination().getState() +
                        "," + packages.get(i).getDestination().getZipCode() + "\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void saveProfit(File file, double profit) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Double.toString(profit));
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void savePackagesShipped(File file, int nPackages) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(Integer.toString(nPackages));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void savePrimeDay(File file, boolean primeDay) {
        try {
            FileWriter fw = new FileWriter(file);
            if (primeDay) {
                fw.write("1");
            } else {
                fw.write("0");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}