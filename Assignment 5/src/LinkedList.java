public class LinkedList {
	public Node head;
	public int size;

	public LinkedList() {
		this.head = null;
		this.size = 0;
	}

	public void add(int data){
		if(head == null){
			head = new Node(data);
		}
		else{
			Node n = head;
			while(n.next!= null){
				n = n.next;
			}
			n.next = new Node(data);
			size++;
		}
	}

	public String toString() {
		String ans = "[";
		Node n = head;
		if(head == null){
			return "Your list is empty !!" + " []";
		}
		while(n.next != null){
			ans = ans + n.toString() + "; ";
			n = n.next;
		}
		ans = ans + n.toString();
		return ans + "]";
	}

	public void removeK(int k){
		if(k < 1 || k > size){
			System.out.println("Your k is not good !!");
		}
		else{
			Node n  = head;
			if (k == 1){
				head = null;
				n.setNext(n);
				 return;
			}
			for(int i = 1; i <= size; i++){
				if(i % (k - 1) == 0){
					n.setNext(n.next.next);	
					size--;
				}
				n = n.next;
			}
		}
	}

//	public static void main(String[] args) {
//		LinkedList ll = new LinkedList();
//		ll.add(3);
//		ll.add(4);
//		ll.add(5);
//		ll.add(2);
//		ll.add(1);
//		ll.add(0);
//		ll.add(98);
//		ll.add(4);
//		System.out.println(ll);
//		ll.removeK(79);
//		System.out.println(ll);
//	}
	
}
