package fruz.udemy.model;

public class Order {
    private int id;

    private int petId;

    private boolean complete;

    private String status;

    private int quantity;

    private String shipDate;

    public Order(int id, int petId, int quantity, String shipDate, String status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public Order setId(int id) {
        this.id = id;
        return this;
    }

    public int getPetId() {
        return petId;
    }

    public Order setPetId(int petId) {
        this.petId = petId;
        return this;
    }

    public boolean getComplete() {
        return complete;
    }

    public Order setComplete(boolean complete) {
        this.complete = complete;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Order setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getShipDate() {
        return shipDate;
    }

    public Order setShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", petId = " + petId + ", complete = " + complete + ", status = " + status + ", quantity = " + quantity + ", shipDate = " + shipDate + "]";
    }
}