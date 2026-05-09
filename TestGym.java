
import java.util.*;
import java.io.*;

public class TestGym {

   static Scanner input = new Scanner(System.in);
   static Gym gym = new Gym("Power Gym", 1000);

public static void main(String[] args) {

      File f = new File("Memberships.dat");

      File f2 = new File("Subscriptions.dat");

      if (f.exists() && f2.exists()) {

         gym.readAllData();}
         
      else{
      // create memberships
      RegularMembership reg1 =
         new RegularMembership(3, 101, "Regular", 250);

      RegularMembership reg2 =
         new RegularMembership(6, 102, "Regular", 300);

      PremiumMembership prem1 =
         new PremiumMembership(true, false,201, "Premium", 500);

      PremiumMembership prem2 =
         new PremiumMembership(true, true, 202, "Premium", 550);

      VIPMembership vip1 =
         new VIPMembership(2, true, true, 301, "VIP", 800);

      VIPMembership vip2 =
         new VIPMembership(4, true, true, 302, "VIP", 950);

      // add to gym
      gym.addMembership(reg1);
      gym.addMembership(reg2);
      gym.addMembership(prem1);
      gym.addMembership(prem2);
      gym.addMembership(vip1);
      gym.addMembership(vip2);
    }

      int choice = 0;

   // menu starts here           
        do {
         
         
         try {
        
         System.out.println("***** Menu *****");
         System.out.println("1- Add new subscription");
         System.out.println("2- View memberships");
         System.out.println("3- Cancel subscription");
         System.out.println("4- Search subscription");
         System.out.println("5- Add new membership");
         System.out.println("6- Delete membership");
         System.out.println("7- Exit");
         
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
               addNewMembership();
               break;
            case 6:
               deleteMembership();
               break;
            case 7:
               System.out.println("**** Goodbye! *****");
               break;
            default:
               System.out.println("Invalid input");
         }
         
         }catch(InputMismatchException e) {

         System.out.println("Enter digits only");
         input.next();}
         
         
      } while(choice != 7);
   }

   // add subscription
   public static void addNewSubscription() {
      System.out.println("Enter membership ID: ");
      
      int id = 0;
      
      try {
      
         id = input.nextInt();
            
      }catch(InputMismatchException e) {

         System.out.println("Enter digits only");

         input.next();

         return;}     
            
           Membership membershipObj = gym.searchMembership(id);
   
      if (membershipObj == null || membershipObj.isAvailable() == false) {
         System.out.println("This membership is not available, try again");
         return;
      }
   
      input.nextLine();
      
      String name = "";
      String phone = "";
      
      try {

         name = readValidName();
         phone = readValidPhone();

      }catch (InvalidNameException e) {

         System.out.println(e.getMessage());
         return;}

       catch (InvalidPhoneException e) {

         System.out.println(e.getMessage());
         return; }      
      
      System.out.println("Enter customer ID: ");
      String idNum = input.next();
   
      Subscription sub = new Subscription(name, phone, idNum);
   
      int months = 0;

      try {

         System.out.println("Enter number of months:");

         months = input.nextInt();

      if (months <= 0)throw new ArithmeticException("Months must be positive");
         
         }catch (ArithmeticException e) {
         
         System.out.println(e.getMessage());

         return;}  
             
      sub.subscribe(membershipObj, months);

      gym.addSubscription(sub);

      gym.saveAllInfo();

      System.out.println("Subscription has been added successfully");
      System.out.println(sub);  

   }

   // add membership
   public static void addNewMembership() {
      System.out.println("Enter what type you want the membership to be : 1-regular , 2-premium 3- vip");
      int type = input.nextInt();
      
      System.out.println("Enter an ID to create a new membership");
      int addid = input.nextInt();

      System.out.println("Enter a name for the membership ");
      input.nextLine();
      String membership_name = input.nextLine();

      double price = 0;
      
      try {

      System.out.println("Enter membership price:");

      price = input.nextDouble();

      if (price <= 0)

      throw new ArithmeticException("Price must be positive");
      
      
      }catch (ArithmeticException e) {

      System.out.println(e.getMessage());
      return;}               
      
      Membership new_membership = null;
         
      try {

      if (type < 1 || type > 3) throw new IllegalArgumentException("Invalid membership type");

      }catch (IllegalArgumentException e) {
       System.out.println(e.getMessage());
       return;}
         
      if (type == 1) {
         System.out.println("how many days per week the person can come to the gym in this membership?");
         int daysperweek = input.nextInt();
         new_membership = new RegularMembership(daysperweek, addid, membership_name, price);
      }
      else if (type == 2) {
         System.out.println("do you want this membership to have personal trainer ?\nplease chose true or false");
         boolean personaltranier = input.nextBoolean();
         System.out.println("do you want this membership to have a customised diet plan ?\nplease chose true or false");
         boolean dietplan = input.nextBoolean();

         new_membership = new PremiumMembership(personaltranier, dietplan, addid, membership_name, price);
      } 
      else if (type == 3) {
         System.out.println("how many guest passes you want in this membership?");
         int guestpasses = input.nextInt();
         System.out.println("do you want this membership to have personal trainer ?\nplease chose true or false");
         boolean personaltranier = input.nextBoolean();
         System.out.println("do you want this membership to have a customised diet plan ?\nplease chose true or false");
         boolean dietplan = input.nextBoolean();

         new_membership = new VIPMembership(guestpasses, personaltranier, dietplan, addid, membership_name, price);
      }
        
        if (new_membership != null) {
               
         gym.addMembership(new_membership);

         gym.saveAllInfo();

         System.out.println("addition has been done\n");
      }
      else
         System.out.println("Can't add\n");   }

   // view memberships
   public static void viewMemberships() {
      List list = gym.getAllMemberships();
      list.print();
   }
   
   
   
   // delete membership
   public static void deleteMembership() {

   System.out.println("Enter membership ID");
   int id = input.nextInt();

   if (gym.deleteMembership(id)) {

      gym.saveAllInfo();

      System.out.println("Deletion has been done\n");
   }
   else
      System.out.println("Can't delete\n");
}

   // cancel subscription
   public static void cancelSubscription() {
      System.out.println("Enter customer ID: ");
      String id = input.next();
      System.out.println("Enter membership ID: ");
      int no = input.nextInt();

      if (gym.cancelSubscription(id, no)) {

      gym.saveAllInfo();

      System.out.println("Cancellation has been done.");
      }
      else
      System.out.println("Sorry, can't cancel.");   
   }

   // search subscription
   public static void searchSubscription() {
      System.out.println("Enter customer ID: ");
      String id = input.next();
      System.out.println("Enter membership ID: ");
      int no = input.nextInt();
   
      try {

         Subscription sub = gym.searchSubscription(id, no);

         if (sub == null)

         throw new NullPointerException("Subscription not found");
         
         System.out.println(sub);
         
         }catch (NullPointerException e) {
         System.out.println(e.getMessage());}   
          
         }
   
public static String readValidPhone()
       throws InvalidPhoneException {

   String phone;

   System.out.print("Enter phone number: ");
   phone = input.next();

   if (phone.length() != 10)
      throw new InvalidPhoneException(
            "Phone must be 10 digits");

   for (int i = 0; i < phone.length(); i++) {

      if (!Character.isDigit(phone.charAt(i)))
         throw new InvalidPhoneException(
               "Phone must contain digits only");
   }

   return phone;
}

public static String readValidName()
       throws InvalidNameException {

   String name;

   System.out.print("Enter full name: ");
   name = input.nextLine();

   for (int i = 0; i < name.length(); i++) {

      char ch = name.charAt(i);

      if (!Character.isLetter(ch) && ch != ' ')
         throw new InvalidNameException(
               "Name must contain letters only");
   }

   return name;
}

}
