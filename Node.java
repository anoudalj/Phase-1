
import java.io.Serializable;

public class Node implements Serializable{
   private Object data;
   private Node next;

   public Node(Object obj) {
      data = obj;
      next = null;
   }

   public Node(Object obj, Node node) {
      data = obj;
      next = node;
   }

   public Object getData() {
      return data;
   }

   public Node getNext() {
      return next;
   }

   public void setNext(Node next) {
      this.next = next;
   }

   public String toString() {
      return data.toString();
   }
}
