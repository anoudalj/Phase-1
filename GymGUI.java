import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GymGUI extends JFrame implements ActionListener {

   Gym gym;

   JButton customerButton;
   JButton membershipsButton;
   JButton searchButton;
   JButton deleteButton;
   JButton cancelButton;
   JButton exitButton;

   JLabel titleLabel;
   JLabel imageLabel;

   public GymGUI() {
   
      gym = new Gym("Power Gym", 1000);
   
      File f = new File("Memberships.dat");
      File f2 = new File("Subscriptions.dat");
   
      if (f.exists() && f2.exists()) {
         gym.readAllData();
      } 
      else {
         gym.addMembership(
            new RegularMembership(3, 101, "Regular", 250)
            );
      
         gym.addMembership(
            new RegularMembership(6, 102, "Regular", 300)
            );
      
         gym.addMembership(
            new PremiumMembership(
                true, false, 201, "Premium", 500
            )
            );
      
         gym.addMembership(
            new VIPMembership(
                2, true, true, 301, "VIP", 800
            )
            );
      }
   
      setTitle("Gym Subscription System");
      setSize(1250, 700);
      setLayout(null);
      getContentPane().setBackground(Color.white);
   
      titleLabel = new JLabel("Welcome To Our Gym System");
      titleLabel.setBounds(280, 50, 1200, 100);
      titleLabel.setFont(
         new Font("Arial", Font.BOLD, 30)
         );
      add(titleLabel);
   
      customerButton = new JButton("Add Subscription");
      membershipsButton = new JButton("View Memberships");
      searchButton = new JButton("Search Subscription");
      deleteButton = new JButton("Delete Membership");
      cancelButton = new JButton("Cancel Subscription");
      exitButton = new JButton("Exit");
   
      customerButton.setBounds(160, 190, 220, 45);
      membershipsButton.setBounds(160, 270, 220, 45);
      searchButton.setBounds(160, 350, 220, 45);
      deleteButton.setBounds(160, 430, 220, 45);
      cancelButton.setBounds(160, 510, 220, 45);
      exitButton.setBounds(160, 590, 220, 45);
   
      add(customerButton);
      add(membershipsButton);
      add(searchButton);
      add(deleteButton);
      add(cancelButton);
      add(exitButton);
   
      customerButton.addActionListener(this);
      membershipsButton.addActionListener(this);
      searchButton.addActionListener(this);
      deleteButton.addActionListener(this);
      cancelButton.addActionListener(this);
      exitButton.addActionListener(this);
   
      ImageIcon icon =
         new ImageIcon("gym-ladies-banner.png");
   
      Image img = icon.getImage();
   
      Image scaledImg =
         img.getScaledInstance(
             400,
             300,
             Image.SCALE_SMOOTH
         );
   
      ImageIcon gymImage =
         new ImageIcon(scaledImg);
   
      imageLabel = new JLabel(gymImage);
      imageLabel.setBounds(470, 170, 400, 300);
      add(imageLabel);
   
      setVisible(true);
   
      setDefaultCloseOperation(
         JFrame.EXIT_ON_CLOSE
         );
   }

   public void actionPerformed(ActionEvent e) {
   
      // ADD SUBSCRIPTION
      if (e.getSource() == customerButton) {
      
         try {
            String membershipId =
               JOptionPane.showInputDialog(
                   "Enter Membership ID"
               );
         
            if (
               membershipId == null ||
               membershipId.isEmpty()
            ) {
               return;
            }
         
            Membership membership =
               gym.searchMembership(
                   Integer.parseInt(
                       membershipId
                   )
               );
         
            if (
               membership == null ||
               !membership.isAvailable()
            ) {
               JOptionPane.showMessageDialog(
                  this,
                  "Membership Not Available"
                  );
               return;
            }
         
            String name =
               JOptionPane.showInputDialog(
                   "Enter Full Name"
               );
         
            if (
               name == null ||
               name.isEmpty()
            ) {
               return;
            }
         
            for (
               int i = 0;
               i < name.length();
               i++
            ) {
               char ch = name.charAt(i);
            
               if (
                  !Character.isLetter(ch)
                  && ch != ' '
               ) {
                  throw new InvalidNameException(
                     "Name must contain letters only"
                     );
               }
            }
         
            String phone =
               JOptionPane.showInputDialog(
                   "Enter Phone Number"
               );
         
            if (
               phone == null ||
               phone.isEmpty()
            ) {
               return;
            }
         
            if (phone.length() != 10) {
               throw new InvalidPhoneException(
                  "Phone must be 10 digits"
                  );
            }
         
            for (
               int i = 0;
               i < phone.length();
               i++
            ) {
               if (
                  !Character.isDigit(
                      phone.charAt(i)
                  )
               ) {
                  throw new InvalidPhoneException(
                     "Phone must contain digits only"
                     );
               }
            }
         
            String customerId =
               JOptionPane.showInputDialog(
                   "Enter Customer ID"
               );
         
            if (
               customerId == null ||
               customerId.isEmpty()
            ) {
               return;
            }
         
            String monthsText =
               JOptionPane.showInputDialog(
                   "Enter Number Of Months"
               );
         
            if (
               monthsText == null ||
               monthsText.isEmpty()
            ) {
               return;
            }
         
            int months =
               Integer.parseInt(
                   monthsText
               );
         
            if (months <= 0) {
               throw new ArithmeticException(
                  "Months must be positive"
                  );
            }
         
            Subscription sub =
               new Subscription(
                   name,
                   phone,
                   customerId
               );
         
            sub.subscribe(
               membership,
               months
               );
         
            gym.addSubscription(sub);
            gym.saveAllInfo();
         
            JTextArea resultArea =
               new JTextArea(
                   sub.toString()
               );
         
            resultArea.setEditable(false);
         
            JOptionPane.showMessageDialog(
               this,
               new JScrollPane(resultArea),
               "Subscription Details",
               JOptionPane.INFORMATION_MESSAGE
               );
         }
         
         catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
               this,
               "Enter digits only"
               );
         }
         
         catch (InvalidNameException ex) {
            JOptionPane.showMessageDialog(
               this,
               ex.getMessage()
               );
         }
         
         catch (InvalidPhoneException ex) {
            JOptionPane.showMessageDialog(
               this,
               ex.getMessage()
               );
         }
         
         catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(
               this,
               ex.getMessage()
               );
         }
      }
   
      // VIEW MEMBERSHIPS
      if (e.getSource() == membershipsButton) {
      
         JFrame frame =
            new JFrame("Memberships");
      
         JTextArea area =
            new JTextArea();
      
         List list =
            gym.getAllMemberships();
      
         Node current =
            list.getHead();
      
         while (current != null) {
            area.append(
               current.getData().toString()
               + "\n\n"
               );
         
            current =
               current.getNext();
         }
      
         area.setEditable(false);
      
         frame.add(
            new JScrollPane(area)
            );
      
         frame.setSize(400, 400);
         frame.setVisible(true);
      }
   
      // SEARCH SUBSCRIPTION
      if (e.getSource() == searchButton) {
      
         try {
            String searchCustomerId =
               JOptionPane.showInputDialog(
                   "Enter Customer ID"
               );
         
            if (
               searchCustomerId == null ||
               searchCustomerId.isEmpty()
            ) {
               return;
            }
         
            String searchMembershipId =
               JOptionPane.showInputDialog(
                   "Enter Membership ID"
               );
         
            if (
               searchMembershipId == null ||
               searchMembershipId.isEmpty()
            ) {
               return;
            }
         
            Subscription foundSub =
               gym.searchSubscription(
                   searchCustomerId,
                   Integer.parseInt(
                       searchMembershipId
                   )
               );
         
            if (foundSub == null) {
               JOptionPane.showMessageDialog(
                  this,
                  "Subscription Not Found"
                  );
            }
            else {
               JTextArea searchArea =
                  new JTextArea(
                      foundSub.toString()
                  );
            
               searchArea.setEditable(false);
            
               JOptionPane.showMessageDialog(
                  this,
                  new JScrollPane(searchArea),
                  "Subscription Details",
                  JOptionPane.INFORMATION_MESSAGE
                  );
            }
         }
         
         catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
               this,
               "Enter digits only"
               );
         }
      }
   
      // DELETE MEMBERSHIP
      if (e.getSource() == deleteButton) {
      
         try {
            String deleteMembershipId =
               JOptionPane.showInputDialog(
                   "Enter Membership ID"
               );
         
            if (
               deleteMembershipId == null ||
               deleteMembershipId.isEmpty()
            ) {
               return;
            }
         
            boolean deleted =
               gym.deleteMembership(
                   Integer.parseInt(
                       deleteMembershipId
                   )
               );
         
            if (deleted) {
               gym.saveAllInfo();
            
               JOptionPane.showMessageDialog(
                  this,
                  "Membership Deleted"
                  );
            }
            else {
               JOptionPane.showMessageDialog(
                  this,
                  "Cannot Delete Membership"
                  );
            }
         }
         
         catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
               this,
               "Enter digits only"
               );
         }
      }
   
      // CANCEL SUBSCRIPTION
      if (e.getSource() == cancelButton) {
      
         try {
            String cancelCustomerId =
               JOptionPane.showInputDialog(
                   "Enter Customer ID"
               );
         
            if (
               cancelCustomerId == null ||
               cancelCustomerId.isEmpty()
            ) {
               return;
            }
         
            String cancelMembershipId =
               JOptionPane.showInputDialog(
                   "Enter Membership ID"
               );
         
            if (
               cancelMembershipId == null ||
               cancelMembershipId.isEmpty()
            ) {
               return;
            }
         
            boolean canceled =
               gym.cancelSubscription(
                   cancelCustomerId,
                   Integer.parseInt(
                       cancelMembershipId
                   )
               );
         
            if (canceled) {
               gym.saveAllInfo();
            
               JOptionPane.showMessageDialog(
                  this,
                  "Subscription Canceled"
                  );
            }
            else {
               JOptionPane.showMessageDialog(
                  this,
                  "Subscription Not Found"
                  );
            }
         }
         
         catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
               this,
               "Enter digits only"
               );
         }
      }
   
      // EXIT
      if (e.getSource() == exitButton) {
      
         JOptionPane.showMessageDialog(
            this,
            "Thank you for using Gym System"
            );
      
         System.exit(0);
      }
   }

   public static void main(String[] args) {
      new GymGUI();
   }
}
///////////////////////////////////