public class VIPMembership extends PremiumMembership implements Services {
   private int guestPasses;

   public VIPMembership(int guestPasses, boolean personalTrainer, boolean dietPlan,
   int membershipId, double price) {
   
      super(personalTrainer, dietPlan, membershipId, price);
      this.guestPasses = guestPasses;
   }

   public String toString() {
      return super.toString() + "\n number of guest passes : " + guestPasses;
   }

   public double calculatePrice(){
      double total = super.calculatePrice();
      total = total + 50 * guestPasses;
      return total;
   }

   public int getServices() {
      return guestPasses + 2;
   }
}