package list;

public class LockDList extends DList{

	protected LockDListNode head;
	protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
    return new LockDListNode(item, prev, next);
  }
  	public LockDList(){
  		head = newNode(Integer.MIN_VALUE,head,head);
	    //head.next = head;
	    //head.prev = head;
	    size = 0;
  	}

	public void lockNode(DListNode d){
		((LockDListNode)d).is_locked = true;
	}

	public LockDListNode front() {
		// if(head.next==head)
		// 	return null;
		// else{
		// 	return head.next;
		// }
		return (LockDListNode)(super.front());
	}

	public LockDListNode back(){
		// if(head.next == head)
		// 	return null;
		// else{
		// 	return (LockDListNode)(head.prev);
		// }
		return (LockDListNode)(super.back());
	}
	public LockDListNode next(DListNode node){
		return (LockDListNode)(super.next(node));
	}
	public LockDListNode prev(DListNode node){
		return (LockDListNode)(super.prev(node));
	}

	public void remove(DListNode node) {
		if(node==null||node==head||((LockDListNode)node).is_locked==true) return;
		else {node.prev.next = node.next;
		node.next.prev = node.prev;
		node.next = null;
		node.prev = null;
		size--;
		}
	}



}