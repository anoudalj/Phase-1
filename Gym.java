public class Gym {
   private String gymName;
   Membership[] membershipList;
   Subscription[] subscriptions;
   int numMembership, numSub;

   // constructor
   public Gym(String gymName, int maxSubscription) {
      this.gymName = gymName;
      membershipList = new Membership[100];
      numMembership = 0;
      subscriptions = new Subscription[maxSubscription];
      numSub = 0;
   }

   // add membership to array
   public boolean addMembership(Membership membership) {
      if (numMembership < membershipList.length) {
         membershipList[numMembership] = membership;
         numMembership++;
         return true;
      }
      return false;
   }

   // delete membership
   public boolean deleteMembership(int id) {
      for (int i = 0; i < numMembership; i++)
         if (membershipList[i].getMembershipId() == id) {
            if (membershipList[i].isAvailable()) {
               for (int j = i; j < numMembership - 1; j++)
                  membershipList[j] = membershipList[j + 1];
               numMembership--;
               membershipList[numMembership] = null;
               return true;
            } else
               System.out.println("This membership is already taken by another person");
            return false;
         }
      return false;
   }

   // add subscription
   public boolean addSubscription(Subscription sub) {
      if (numSub < subscriptions.length) {
         subscriptions[numSub] = new Subscription(sub);
         numSub++;
         return true;
      }
      return false;
   }

   // cancel subscription
   public boolean cancelSubscription(String id, int membershipId) {
      for (int i = 0; i < numSub; i++) {
         if (subscriptions[i].getID().equals(id) &&
            subscriptions[i].getMembership().getMembershipId() == membershipId) {
            subscriptions[i].cancelSubscription();
            subscriptions[i] = subscriptions[numSub - 1];
            numSub--;
            subscriptions[numSub] = null;
            return true;
         }
      }
      return false;
   }

   // search subscription
   public Subscription searchSubscription(String id, int membershipId) {
      for (int i = 0; i < numSub; i++)
         if (subscriptions[i].getID().equals(id) &&
            subscriptions[i].getMembership().getMembershipId() == membershipId)
            return subscriptions[i];
      return null;
   }

   // search membership
   public Membership searchMembership(int id) {
      for (int i = 0; i < numMembership; i++)
         if (membershipList[i].getMembershipId() == id)
            return membershipList[i];
      return null;
   }

   // get all regular memberships
   public RegularMembership[] getRegular() {
      RegularMembership[] list = new RegularMembership[numMembership];
      int j = 0;
      for (int i = 0; i < numMembership; i++)
         if (membershipList[i] instanceof RegularMembership)
            list[j++] = (RegularMembership) membershipList[i];
      return list;
   }

   // get all premium memberships
   public PremiumMembership[] getAllPremium() {
      PremiumMembership[] list = new PremiumMembership[numMembership];
      int j = 0;
      for (int i = 0; i < numMembership; i++)
         if (membershipList[i] instanceof PremiumMembership)
            list[j++] = (PremiumMembership) membershipList[i];
      return list;
   }

   // get VIP with condition
   public VIPMembership[] getAllPremium(int guestPasses) {
      VIPMembership[] list = new VIPMembership[numMembership];
      int j = 0;
      for (int i = 0; i < numMembership; i++)
         if (membershipList[i] instanceof VIPMembership &&
            ((VIPMembership) membershipList[i]).getGuestPasses() >= guestPasses)
            list[j++] = (VIPMembership) membershipList[i];
      return list;
   }
  
public Membership[] getAllMemberships() {
    return membershipList;
}

   // get available memberships
   public Membership[] getAllAvailable() {
      Membership[] list = new Membership[numMembership];
      int j = 0;
      for (int i = 0; i < numMembership; i++)
         if (membershipList[i].isAvailable())
            list[j++] = membershipList[i];
      return list;
   }
}
