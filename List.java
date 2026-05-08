public class List {
   private Node head;
   private Node tail;
   private String name;

   public List() {
      head = tail = null;
      name = "No Name";
   }

   public List(String name) {
      head = tail = null;
      this.name = name;
   }

   public boolean isEmpty() {
      return head == null;
   }

   public void insertAtBack(Object obj) {
      Node newnode = new Node(obj);
   
      if (isEmpty())
         head = tail = newnode;
      else {
         tail.setNext(newnode);
         tail = newnode;
      }
   }

   public Node getHead() {
      return head;
   }

   public Node getTail() {
      return tail;
   }

   public void setHead(Node head) {
      this.head = head;
   }

   public void setTail(Node tail) {
      this.tail = tail;
   }

   public void print() {
      if (isEmpty()) {
         System.out.println("The list " + name + " is empty");
         return;
      }
   
      Node current = head;
   
      while (current != null) {
         System.out.println(current.getData());
         current = current.getNext();
      }
   }
}