public class Node implements NodeFunctions
{
	private final int key;
	private Node parent;
	private Node left;
	private Node right;
	private Object data;
	
	public Node(int key, Object data)
	{
		this.key = key;
		parent = null;
		left = null;
		right = null;
		this.data = data;
	}
	
	public Node(int key)
	{
		this(key, null);
	}
	
	public int getKey()
	{
		return key;
	}
	
	public Node getParent()
	{
		return parent;
	}
	
	public Node getLeft()
	{
		return left;
	}
	
	public Node getRight()
	{
		return right;
	}
	
	public void setLeft(Node n)
	{
		left = n;
	}
	
	public void setRight(Node n)
	{
		right = n;
	}
	
	public void setParent(Node n)
	{
		parent = n;
	}
	
	public String toString()
	{
		//(key, parent key, left key, right key)
		String x = "(";
		x += Integer.toString(key);
		x += ",";
		if(getParent() != null)
		{
			x += Integer.toString(getParent().getKey());
		}
		x += ",";
		if(getLeft() != null)
		{
			x += Integer.toString(getLeft().getKey());
		}
		x += ",";
		if(getRight() != null)
		{
			x += Integer.toString(getRight().getKey());
		}
		x += ")";
		return x;
	}
	
	public boolean equals(Object o)
	{
		if(this.key == ((Node) o).getKey())
		{
			return true;
		}
		return false;
	}
	
	public Object getData()
	{
		return data;
	}
	
	public void setData(Object o)
	{
		data = o;
	}
}
