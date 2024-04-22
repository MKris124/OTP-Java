public class Customer {
    private int webshopId;
    private int customerId;
    private String name;
    private String address;
    //<editor-fold desc="getters-setters">
    public void setWebshopId(int webshopId) {
        this.webshopId = webshopId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWebshopId() {
        return webshopId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
//</editor-fold>
    public Customer(int webshopId, int customerId, String name, String address) {
        this.webshopId = webshopId;
        this.customerId = customerId;
        this.name = name;
        this.address = address;
    }


}
