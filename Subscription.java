
import java.io.Serializable;

public class Subscription implements Serializable {
    private String fullName;
    private String phone;
    private String id;
    private Membership membership;
    private int numOfMonths;
    private double totalPrice;

    // constructor
    public Subscription(String fullName, String phone, String id) {
        this.fullName = fullName;
        this.phone = phone;
        this.id = id;
    }

    // copy constructor
    public Subscription(Subscription obj) {
        this.fullName = obj.fullName;
        this.phone = obj.phone;
        this.id = obj.id;
        this.numOfMonths = obj.numOfMonths;
        this.membership = obj.membership;
        this.totalPrice = obj.totalPrice;
    }

    // subscribe to membership
    public void subscribe(Membership membership, int months) {
        numOfMonths = months;
        this.membership = membership;
        membership.setAvailable(false);
        totalPrice = numOfMonths * membership.calculatePrice();
    }

    // cancel subscription
    public void cancelSubscription() {
        if (membership.isAvailable())
            System.out.println("This subscription was already canceled.");
        else {
            membership.setAvailable(true);
            System.out.println("Subscription is canceled.");
        }
    }

    public String toString() {
        return "Full Name : " + fullName + ", phone = " + phone +
               "\n ID = " + id + "\n membership = " + membership +
               "\n Num Of Months = " + numOfMonths +
               "\n Total Price : " + totalPrice;
    }

    public String getID() {
        return id;
    }

    public Membership getMembership() {
        return membership;
    }
}
