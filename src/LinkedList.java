class ListNode {
	// Constructors
	ListNode(Object theElement) {
		this(theElement, null);
	}

	ListNode(Object theElement, ListNode n) {
		data = theElement;
		nextNode = n;
	}

	// Friendly data; accessible by other package routines
	Object data;
	ListNode nextNode;
}

public class LinkedList {
	ListNode header;

	public LinkedList() {
		header = new ListNode(null);
	}

	public boolean isEmpty() {
		return header.nextNode == null;
	}

	/** make the list empty. */
	public void makeEmpty() {
		header.nextNode = null;
	}

	public void insert(Object value, Iterator p) throws Exception {
		if (p == null || !(p instanceof MyListIterator))
			throw new Exception();
		MyListIterator p2 = (MyListIterator) p;
		if (p2.currentNode == null)
			throw new Exception();
		ListNode n = new ListNode(value, p2.currentNode.nextNode);
		p2.currentNode.nextNode = n;
	}

	public int find(Object value) throws Exception {
		Iterator itr = new MyListIterator(header);
		int index = -1;
		while (itr.hasNext()) {
			Object v = itr.next();
			index++;
			if (v.equals(value))
				return index; // return the position of value.
		}
		return -1;
	}

	public Iterator findPrevious(Object value) throws Exception {
		Iterator itr1 = new MyListIterator(header);
		Iterator itr2 = new MyListIterator(header);
		if (!itr2.hasNext())
			return null;
		Object currentData = itr2.next();
		while (!currentData.equals(value) && itr2.hasNext()) {
			currentData = itr2.next();
			itr1.next();
		}
		if (currentData.equals(value))
			return itr1;
		return null;

	}
	
	public void remove(Iterator p) {
		if (p == null || !(p instanceof MyListIterator))
			return;
		MyListIterator p2 = (MyListIterator) p;
		if (p2.currentNode == null || p2.currentNode.nextNode == null)
			return;
		p2.currentNode.nextNode = p2.currentNode.nextNode.nextNode;
	}

	public void remove(Object value) throws Exception {
		Iterator p = findPrevious(value);
		if (p == null)
			return;
		remove(p);
	}

	// Returns the number of data stored in this list.
	// To be completed by students.
	public int size() throws Exception{
		MyListIterator itr = new MyListIterator(header);
		int size = 0;
		while (itr.hasNext()) {
			itr.next();
			size++;
		}
		return size;

	}
	
	// Print each contact out, one by one.
	// To be completed by students.
	public void printList() throws Exception{
		MyListIterator itr = new MyListIterator(header);
		while (itr.hasNext()) {
			Contact contact = (Contact) itr.currentNode.nextNode.data;
			System.out.print("name:"+contact.name+" email:"+contact.email+" phone:"+contact.phone+"\n");
			itr.next();
		}
	}

	//Return iterator pointing to value that stores a given name, or null otherwise.
	//To be completed by students.
	public Iterator findPosition(String name) throws Exception{
		MyListIterator itr1 = new MyListIterator(header);
		Iterator itr2 = new MyListIterator(header);
		while (itr1.hasNext()) {
			itr1.next();
			itr2.next();
			Contact contact = (Contact) itr1.currentNode.data;
			if (name.equals(contact.name)) 
				return itr2;
		}
		return null;
	}
	
	//add a new contact to the list the contact must be added in such a way that each contact is sorted by name, in alphabetical order.
	
	public void add(Object c) throws Exception{
		MyListIterator itr = new MyListIterator(header);
		Contact contact1 = (Contact) c;
		while (itr.hasNext()) {
			Contact contact2 = (Contact) itr.currentNode.nextNode.data;
			if (contact2.name.compareTo(contact1.name) > 0) {
				break;
			}
			itr.next();		
		}
		
		if (itr.currentNode.nextNode == null) {
			ListNode newListNode = new ListNode(c);
			itr.currentNode.nextNode = newListNode;
		} else { 
			ListNode newListNode = new ListNode(c, itr.currentNode.nextNode);
			itr.currentNode.nextNode = newListNode;
		}
		
			
	}
	
	//Remove the contact pointed by the iterator, i, and then returns an iterator pointing to the next contact. 
	//If the removed contact is the last one, return the iterator pointing to the first contact 
	//   (if there is no first contact, make the iterator point to header). 
	//If i is marking an illegal position that cannot be removed, just return null. 
	//To be completed by students.
	public Iterator removeAt(Iterator i) throws Exception{
		MyListIterator itr1 = (MyListIterator) i;
		if ( itr1 == null 
			|| itr1.currentNode == null
			|| itr1.currentNode.data == null)
			return null;
		MyListIterator itr2 = (MyListIterator) findPrevious(itr1.currentNode.data);
		if ( itr2 == null 
			|| itr2.currentNode == null
			|| itr2.currentNode.nextNode == null)
			return null;
		remove(itr2);
		if (itr2.currentNode.nextNode == null) 
			itr2.currentNode = header;
		if (itr2.hasNext())
			itr2.next();
		return itr2;
		
	}
	

}