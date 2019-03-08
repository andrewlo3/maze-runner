package mazePD;

public class Stack {

	private class Node {
	
			Node next;
			int data;
			
			public Node()
			{
				data = 0;
				next = null;
			}
			
			public Node(int data)
			{
				next = null;
				this.data = data;
			}
		}

	private Node top;
	private int size;
	
	public Stack()
	{
		top = new Node();
		size = 0;
	}
	
	public void push(int data)
	{
		Node node = new Node(data);
		
		if (isEmpty())
			top.next = node;
		else
		{
			node.next = top.next;
			top.next = node;
		}
		size++;
	}
	
	public int pop()
	{
		int tempData;
		
		if (isEmpty())
		{
			System.out.println("Error handling!");
			return 0;
		}
		else
		{
			tempData = top.next.data;
			top.next = top.next.next;
		}
		size--;
		
		return tempData;
	}
	
	public int top()
	{
		return top.next.data;
	}
	
	public boolean isEmpty()
	{
		return top.next == null;
	}
	
	public int size()
	{
		return size;
	}
}