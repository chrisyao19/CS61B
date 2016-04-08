package list;

public class LockDListNode extends DListNode{
	protected boolean is_locked;
	protected LockDListNode prev;
	protected LockDListNode next;

	public LockDListNode(Object i, DListNode p, DListNode n){
		super(i,p,n);
		is_locked = false;
		prev = (LockDListNode)p;
		next = (LockDListNode)n;
	}
}