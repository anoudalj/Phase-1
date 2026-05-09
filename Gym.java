import java.io.*;

public class Gym implements Serializable{
   private String gymName;
   List membershipList;
   List subscriptions;

   // constructor
   public Gym(String gymName, int maxSubscription) {
      this.gymName = gymName;
      membershipList = new List("membership list");
      subscriptions = new List("subscription list");
   }

   // add membership
   public boolean addMembership(Membership membership) {
      membershipList.insertAtBack(membership);
      return true;
   }

   // delete membership
   public boolean deleteMembership(int id) {
      Node current = membershipList.getHead();
      Node previous = null;

      while (current != null) {
         Membership m = (Membership) current.getData();

         if (m.getMembershipId() == id) {
            if (m.isAvailable()) {

               if (previous == null)
                  membershipList.setHead(current.getNext());
               else
                  previous.setNext(current.getNext());

               if (current == membershipList.getTail())
                  membershipList.setTail(previous);

               return true;
            }
            else {
               System.out.println("This membership is already taken by another person");
               return false;
            }
         }

         previous = current;
         current = current.getNext();
      }

      return false;
   }

   // add subscription
   public boolean addSubscription(Subscription sub) {
      subscriptions.insertAtBack(new Subscription(sub));
      return true;
   }

   // cancel subscription
   public boolean cancelSubscription(String id, int membershipId) {
      Node current = subscriptions.getHead();
      Node previous = null;

      while (current != null) {
         Subscription sub = (Subscription) current.getData();

         if (sub.getID().equals(id) &&
             sub.getMembership().getMembershipId() == membershipId) {

            sub.cancelSubscription();

            if (previous == null)
               subscriptions.setHead(current.getNext());
            else
               previous.setNext(current.getNext());

            if (current == subscriptions.getTail())
               subscriptions.setTail(previous);

            return true;
         }

         previous = current;
         current = current.getNext();
      }

      return false;
   }

   // search subscription
   public Subscription searchSubscription(String id, int membershipId) {
      Node current = subscriptions.getHead();

      while (current != null) {
         Subscription sub = (Subscription) current.getData();

         if (sub.getID().equals(id) &&
             sub.getMembership().getMembershipId() == membershipId)
            return sub;

         current = current.getNext();
      }

      return null;
   }

   // search membership
   public Membership searchMembership(int id) {
      Node current = membershipList.getHead();

      while (current != null) {
         Membership m = (Membership) current.getData();

         if (m.getMembershipId() == id)
            return m;

         current = current.getNext();
      }

      return null;
   }

   // get all memberships
   public List getAllMemberships() {
      return membershipList;
   }
   
   
   // SAVE ALL DATA

   public void saveAllInfo() {

      try {

         // save memberships
         File out = new File("Memberships.dat");

         FileOutputStream fos = new FileOutputStream(out);

         ObjectOutputStream oos =
               new ObjectOutputStream(fos);

         oos.writeObject(membershipList);

         oos.close();

         // save subscriptions
         File out2 = new File("Subscriptions.dat");

         FileOutputStream fos2 =
               new FileOutputStream(out2);

         ObjectOutputStream oos2 =
               new ObjectOutputStream(fos2);

         oos2.writeObject(subscriptions);

         oos2.close();

      }

      catch (IOException e) {

         System.out.println(e.toString());

      }
   }


   // READ ALL DATA


   public void readAllData() {

      try {

         // read memberships
         File f = new File("Memberships.dat");

         FileInputStream ff =
               new FileInputStream(f);

         ObjectInputStream in =
               new ObjectInputStream(ff);

         membershipList = (List) in.readObject();

         in.close();

         // read subscriptions
         File f2 = new File("Subscriptions.dat");

         FileInputStream ff2 =
               new FileInputStream(f2);

         ObjectInputStream in2 =
               new ObjectInputStream(ff2);

         subscriptions =
               (List) in2.readObject();

         in2.close();

         System.out.println(
               "All data in files are loaded.");

      }

      catch (ClassNotFoundException ex) {

         System.out.println(ex.toString());

      }

      catch (IOException e) {

         System.out.println(e.toString());

      }
      
}

}
