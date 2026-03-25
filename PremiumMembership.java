public class PremiumMembership extends Membership {
   private boolean personalTrainer;
   private boolean dietPlan;

   public PremiumMembership(boolean personalTrainer, boolean dietPlan, int membershipId, double price) {
      super(membershipId, price);
      this.personalTrainer = personalTrainer;
      this.dietPlan = dietPlan;
   }

   public String toString() {
      return super.toString() + ", trainer : " + personalTrainer +
         (dietPlan ? ", diet plan included" : "");
   }

   public double calculatePrice(){
      double total = price;
   
      if(personalTrainer == true)
         total = total + 200;
   
      if(dietPlan == true)
         total = total + 100;
   
      return total;
   }
}