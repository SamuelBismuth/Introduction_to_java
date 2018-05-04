public class Node {
	public Node next;
	public int data;
	
	public Node(Node next, int data) {
		this.next = next;
		this.data = data;
	}
	
	public Node(int data) {
		this.next = null;
		this.data = data;
	}
	
	public String toString() {
		return "[" + data + "]";
	}

	public void setNext(Node next) {
		this.next = next;
	}
}
