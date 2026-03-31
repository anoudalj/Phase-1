public class RegularMembership extends Membership implements Services {
    private int daysPerWeek;

    // constructor
    public RegularMembership(int daysPerWeek, int membershipId, String name, double price) {
        super(membershipId, name, price);
        this.daysPerWeek = daysPerWeek;
    }

    // calculate price based on days
    public double calculatePrice() {
        double total = price;

        if (daysPerWeek >= 5)
            total = price + 100;

        return total;
    }

    // return number of services
    public int getServices() {
        return 1;
    }

    public String toString() {
        return super.toString() + ", days per week : " + daysPerWeek;
    }
}