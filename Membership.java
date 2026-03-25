public abstract class Membership {
private int membershipId;
private boolean available;
protected double price;

public Membership(int membershipId, double price) {
this.membershipId = membershipId;
this.price = price;
available = true;
}

public abstract double calculatePrice();

public int getMembershipId() {
return membershipId;
}

public void setMembershipId(int membershipId) {
this.membershipId = membershipId;
}

public boolean isAvailable() {
return available;
}

public void setAvailable(boolean available) {
this.available = available;
}

public double getPrice() {
return price;
}

public void setPrice(double price) {
this.price = price;
}

public String toString() {
return "Membership ID : " + membershipId + ", available = " + available;
}
}