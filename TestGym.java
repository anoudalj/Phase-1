
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
   
      int choice;      do {
         System.out.println("***** Menu *****");
         System.out.println("1- Add new subscription");
         System.out.println("2- View memberships");
         System.out.println("3- Cancel subscription");
         System.out.println("4- Search subscription");
         System.out.println("5- add new membership");
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
               System.out.println("Enter what type you want the membership to be : 1-regular , 2-premium 3- vip");
               int type = input.nextInt();
               System.out.println("Enter an ID to create a new membership");
               int addid = input.nextInt();
               System.out.println("Enter a name for the membership ");
               input.nextLine();
               String membership_name = input.nextLine();
               input.nextLine();
               System.out.println("Enter a price for the membership ");
               double price = input.nextDouble();
               
               Membership new_membership;
               
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

               new_membership = new VIPMembership(guestpasses , personaltranier , dietplan , addid, membership_name, price);
               }
               else{
               System.out.println("the type number you entered doesnt exist");
               break;}
               
               
               if (gym.addMembership(new_membership))
                  System.out.println("addition has been done\n");
               else
                  System.out.println("Can't add\n");
                  
               break;

               case 6:
               System.out.println("Enter membership ID");
               int id = input.nextInt();
               if (gym.deleteMembership(id))
                  System.out.println("Deletion has been done\n");
               else
                  System.out.println("Can't delete\n");
               break;
            case 7:
               System.out.println("**** Goodbye! *****");
               break;
            default:
               System.out.println("Invalid input");
         }
      } while (choice != 7);
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
   
      input.nextLine();
      String name = readValidName (input);
      String phone = readValidPhone(input);
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
     Membership[] list = gym.getAllMemberships();
     printAllMemberships(list, 0);
   }

   // recursion method
  
  public static void printAllMemberships(Membership[] list, int index) {
   if (index == list.length || list[index] == null)
     return;
     
     System.out.println(list[index]);
     printAllMemberships(list, index + 1);
  
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
   

 public static String readValidPhone(Scanner input) {
    String phone;
    boolean valid;
    
    while (true) {
        System.out.print("Enter phone (10 digits): ");
        phone = input.next(); 
        valid = true;

       if (phone.length() != 10) {
        valid = false;
        } else {
        for (int i = 0; i < phone.length(); i++) {
        if (!Character.isDigit(phone.charAt(i))) {
        valid = false;
        break;
        }
}
}

        if (valid) {
        return phone;
         }

      System.out.println("Invalid phone number.");
}
}

public static String readValidName(Scanner input) {
String name;
boolean valid;
boolean hasSpace;

while (true) {
System.out.print("Enter customer's full name: ");
name = input.nextLine();
valid = true;
hasSpace = false;

if (name.length() == 0) {
valid = false;
} else {
for (int i = 0; i < name.length(); i++) {
char ch = name.charAt(i);

if (ch == ' ') {
hasSpace = true;
}

if (!Character.isLetter(ch) && ch != ' ') {
valid = false;
break;
}
}
}

if (valid && hasSpace) {
return name;
 }
 System.out.println("Invalid full name. Enter first and last name.");
}
}
}
