import java.text.NumberFormat;

/**
 * <h1>Package</h1> Represents a package
 *
 * @author Jaewook Lee, Daniel Hooks, lab sec 11
 * @version 12/9/18
 */
public class Package {
    private String id;
    private String product;
    private double weight;
    private double price;
    private ShippingAddress destination;

    //============================================================================
    public Package() {
        this.id = "";
        this.product = "";
        this.weight = 0;
        this.price = 0;
        this.destination = new ShippingAddress();
    }

    public Package(String id, String product, double weight, double price, ShippingAddress destination) {
        this.id = id;
        this.product = product;
        this.weight = weight;
        this.price = price;
        this.destination = destination;
    }
    //============================================================================

    public String getID() {
        return id;
    }


    public String getProduct() {
        return product;
    }


    public void setProduct(String product) {
        this.product = product;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ShippingAddress getDestination() {
        return destination;
    }


    public void setDestination(ShippingAddress destination) {
        this.destination = destination;
    }


    public String shippingLabel() {
        NumberFormat fm = NumberFormat.getCurrencyInstance();
        return "====================\n" +
                "TO: " + destination.getName() + "\n" + destination.getAddress() + "\n" +
                destination.getCity() + ", " + destination.getState() + ", " + destination.getZipCode() + "\n" +
                String.format("Weight: %.2f", weight) + "\n" +
                "Price: " + fm.format(price) + "\n" +
                "Product: " + product + "\n" + "====================";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}