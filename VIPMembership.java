public class VIPMembership extends PremiumMembership implements Services {
   private int guestPasses;

   // constructor
   public VIPMembership(int guestPasses, boolean personalTrainer, boolean dietPlan,
                        int membershipId, String name, double price) {
      super(personalTrainer, dietPlan, membershipId, name, price);
      this.guestPasses = guestPasses;
   }

   // calculate price including guest passes
   public double calculatePrice() {
      double total = super.calculatePrice();
      total = total + (guestPasses * 50);
      return total;
   }

   // return number of guest passes
   public int getGuestPasses() {
      return guestPasses;
   }

   // return number of services
   public int getServices() {
      return guestPasses + 2;
   }

   public String toString() {
      return super.toString() + ", guest passes : " + guestPasses;
   }
}