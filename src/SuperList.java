import java.util.EmptyStackException;

public class SuperList<E>{
	ListNode<E> root;
	ListNode<E> end;
	int size=0;
	public SuperList() {
		root=null;
		end = null;
	}
	public SuperList(E e) {
		root = new ListNode(e);
	}
	public void add(E e) {					//adds to end
		ListNode<E>newNode=new ListNode(e);
		if(root==null) { 
			root=newNode;
			end=root;
		}
		else {
			end.setNext(newNode);
			newNode.setPrevious(end);
			end=newNode;
		}
		size++;
	}
	public void add(int index, E e) {
		if(index==size) {
			add(e);
		}
		else if(index==0) {
			ListNode<E> temp=root;
			ListNode newNode=new ListNode(e);
			temp.setPrevious(newNode);
			newNode.setNext(temp);
			root=newNode;
			size++;
		}
		else{
		
		ListNode<E> temp=root;
		for(int i = 0; i < size+1; i++) {
			if(i==index-1){ 
				ListNode ogtemp=temp;
				ListNode ognext=temp.getNext();
				temp=new ListNode(e);
				ogtemp.setNext(temp);
				temp.setPrevious(ogtemp);
				temp.setNext(ognext);
				ognext.setPrevious(temp);
			}
			if(i<size)
				temp=temp.getNext();
		}
		size++;
		}
	}
	public E remove(int index) {
		E value=null;
		ListNode<E> temp=root;
		if(size==0) {
			throw new IndexOutOfBoundsException("index out of bounds :(");
		}
		else if(index==0) {
			value=poll();
		}
		else if(index==size-1) {
			value=pop();
		}
		else {
			
			for(int i = 0; i < size; i++) {
				if(i==index){
					value=temp.getValue();
					temp.getPrevious().setNext(temp.getNext());
					temp.getNext().setPrevious(temp.getPrevious());
				}
				if(i<size-1)
					temp=temp.getNext();
			}
			size--;
		}
		return value;
	}
	public E get(int index) {
		E value=null;
		ListNode<E> temp=root;
		if(index>size)
			throw new ArrayIndexOutOfBoundsException();
		
		for(int i = 0; i < size; i++) {
			if(i==index){
				value=temp.getValue();
			}
			temp=temp.getNext();
		}
		return value;
	}
	public boolean contains(E e) {
		boolean contains=false;
		ListNode<E> temp=root;
		for(int i = 0; i < size; i++) {
			if(temp.getValue().equals(e))
				contains=true;
			temp=temp.getNext();
		}
		return contains;
	}
	public void push(E e) {					//pushes to end
		ListNode<E>newNode=new ListNode(e);
		if(root==null) { //is empty
			end=newNode;
			root=end;
		}
		else {
			end.setNext(newNode);
			newNode.setPrevious(end);
			end=newNode;
			//root.setPrevious(newNode);
			//newNode.setNext(root);
			//root=newNode;
		}
		size++;
	}
	public E pop() { //gets from end
		
		if(size==0) {
			throw new EmptyStackException();
		}
		E poppedvalue= end.getValue();
		if(size==1) {
			root=null;
			end=null;
		}
		else {
			end=end.getPrevious();
			end.setNext(null);
		}
		size--;
		return poppedvalue;
	}
	public E poll() { //gets from start
		
		if(size==0) {
			return null;
		}
		E polledvalue = root.getValue();
		if(size==1) {
			root=null;
			end=null;
		}
		else {
			root=root.getNext();
			root.setPrevious(null);
		}
		size--;
		return polledvalue;
	}
	public E stackPeek() {
		return end.getValue();
	}
	public E queuePeek() {
		return root.getValue();
	}
	public String toString() {
		String st="[";
		ListNode<E> temp=root;
		for(int i=0;i<size;i++) {
			st+=temp.getValue();
			if(i<size-1) {
				st+=", ";
				temp=temp.getNext();
			}
		}
		st+="]";
		return st;
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		if(root==null&&end==null) 
			return true;
		else
			return false;
	}
	public void clear() {
		root=null;
		end=null;
		size=0;
	}
	public ListNode<E> getRoot() {return root;}
	public ListNode<E> getEnd() {return end;}
	
	public class ListNode<E>{
		E value;
		ListNode<E> next;
		ListNode<E> previous;
		public ListNode(E e) { //new ListNode with root value e
			this.value=e;
			next=null;
			previous=null;
		}
		public E getValue() {
			return value;
		}
		public void setPrevious(ListNode<E> node) {
			previous=node;
		}
		public void setNext(ListNode<E> node) {
			next=node;
		}
		public ListNode<E> getPrevious() {
			return previous;
		}
		public ListNode<E> getNext() {
			return next;
		}
		public boolean hasPrevious(){
			return previous!=null;
		}
		public boolean hasNext() {
			return next!=null;
		}
	}

}
