public abstract class Membership {
    private int membershipId;
    private boolean available;
    protected double price;
    protected String name;

    // constructor
    public Membership(int membershipId, String name, double price) {
        this.membershipId = membershipId;
        this.name = name;
        this.price = price;
        this.available = true;
    }

    // abstract method for price calculation
    public abstract double calculatePrice();

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Membership ID : " + membershipId + ", available = " + available +
               ", membership name : " + name;
    }
}