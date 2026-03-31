import java.util.Scanner;

public class TestGym {

   static Scanner input = new Scanner(System.in);
   static Gym gym = new Gym("Power Gym", 1000);

   public static void main(String[] args) {
   
      // create memberships
      RegularMembership reg1 = new RegularMembership(3, 101, "Regular", 250);
      RegularMembership reg2 = new RegularMembership(6, 102, "Regular", 300);
   
      PremiumMembership prem1 = new PremiumMembership(true, false, 201, "Premium", 500);
      PremiumMembership prem2 = new PremiumMembership(true, true, 202, "Premium", 550);
   
      VIPMembership vip1 = new VIPMembership(2, true, true, 301, "VIP", 800);
      VIPMembership vip2 = new VIPMembership(4, true, true, 302, "VIP", 950);
   
      // add to gym
      gym.addMembership(reg1);
      gym.addMembership(reg2);
      gym.addMembership(prem1);
      gym.addMembership(prem2);
      gym.addMembership(vip1);
      gym.addMembership(vip2);
   
      int choice;
      do {
         System.out.println("***** Menu *****");
         System.out.println("1- Add new subscription");
         System.out.println("2- View memberships");
         System.out.println("3- Cancel subscription");
         System.out.println("4- Search subscription");
         System.out.println("5- Delete membership");
         System.out.println("6- Exit");
         choice = input.nextInt();
      
         switch (choice) {
            case 1:
               addNewSubscription();
               break;
            case 2:
               viewMemberships();
               break;
            case 3:
               cancelSubscription();
               break;
            case 4:
               searchSubscription();
               break;
            case 5:
               System.out.println("Enter membership ID");
               int id = input.nextInt();
               if (gym.deleteMembership(id))
                  System.out.println("Deletion has been done");
               else
                  System.out.println("Can't delete");
               break;
            case 6:
               System.out.println("**** Goodbye! *****");
               break;
            default:
               System.out.println("Invalid input");
         }
      } while (choice != 6);
   }

   // add subscription
   public static void addNewSubscription() {
      System.out.println("Enter membership ID: ");
      int id = input.nextInt();
      Membership membershipObj = gym.searchMembership(id);
   
      if (membershipObj == null || membershipObj.isAvailable() == false) {
         System.out.println("This membership is not available, try again");
         return;
      }
   
      System.out.println("Enter customer's full name: ");
      input.nextLine();
      String name = input.nextLine();
      System.out.println("Enter phone: ");
      String phone = input.next();
      System.out.println("Enter customer ID: ");
      String idNum = input.next();
   
      Subscription sub = new Subscription(name, phone, idNum);
   
      System.out.println("Enter number of months: ");
      int months = input.nextInt();
   
      sub.subscribe(membershipObj, months);
      gym.addSubscription(sub);
   
      System.out.println("Subscription has been added successfully");
      System.out.println(sub);
   }

   // view memberships
   public static void viewMemberships() {
      RegularMembership[] list = gym.getRegular();
      printAllRegularRecursion(list, 0);
   }

   // recursion method
   public static void printAllRegularRecursion(RegularMembership[] list, int index) {
      if (index == list.length || list[index] == null)
         return;
   
      System.out.println(list[index]);
      printAllRegularRecursion(list, index + 1);
   }

   // cancel subscription
   public static void cancelSubscription() {
      System.out.println("Enter customer ID: ");
      String id = input.next();
      System.out.println("Enter membership ID: ");
      int no = input.nextInt();
   
      if (gym.cancelSubscription(id, no))
         System.out.println("Cancellation has been done.");
      else
         System.out.println("Sorry, can't cancel.");
   }

   // search subscription
   public static void searchSubscription() {
      System.out.println("Enter customer ID: ");
      String id = input.next();
      System.out.println("Enter membership ID: ");
      int no = input.nextInt();
   
      Subscription sub = gym.searchSubscription(id, no);
   
      if (sub == null)
         System.out.println("Can't find this subscription");
      else
         System.out.println(sub);
   }
}