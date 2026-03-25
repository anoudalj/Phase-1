public class RegularMembership extends Membership implements Services {
   private int sessionsPerWeek;

   public RegularMembership(int sessionsPerWeek, int membershipId, double price) {
      super(membershipId, price);
      this.sessionsPerWeek = sessionsPerWeek;
   }

   public String toString() {
      return super.toString() + ", sessions per week : " + sessionsPerWeek;
   }

   public double calculatePrice(){
      double total = 0;
      if(sessionsPerWeek > 3)
         total = price + 60;
      else
         total = price;
      return total;
   }

   public int getServices(){
      if(sessionsPerWeek > 3)
         return 2;
      else
         return 1;
   }
}