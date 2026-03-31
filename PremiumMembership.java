public class PremiumMembership extends Membership {
    private boolean personalTrainer;
    private boolean dietPlan;

    // constructor
    public PremiumMembership(boolean personalTrainer, boolean dietPlan, int membershipId, String name, double price) {
        super(membershipId, name, price);
        this.personalTrainer = personalTrainer;
        this.dietPlan = dietPlan;
    }

    // calculate price with extra features
    public double calculatePrice() {
        double total = price;

        if (personalTrainer)
            total = total + 200;

        if (dietPlan)
            total = total + 150;

        return total;
    }

    public String toString() {
        return super.toString() + ", personal trainer : " + personalTrainer + ", diet plan : " + dietPlan;
    }
}