public class bhagat_BinarySearchTree implements BinarySearchTreeFunctions
{
	private Node root;
	
	public bhagat_BinarySearchTree()
	{
		root = null;
	}
	
	public Node getRoot()
	{
		return root;
	}
	
	public void setRoot(Node root)
	{
		this.root = root;
	}
		
	public void insertNode(Node z)
	{
		Node x = getRoot();
		Node y = null;
		while(x != null)
		{
			y = x;
			if(z.getKey() < y.getKey())
			{
				x = x.getLeft();
			}
			else
			{
				x = x.getRight();
			}
		}
		z.setParent(y);
		if ( y == null)
		{
			setRoot(z);
		}
		else
		{
			if(z.getKey() < y.getKey())
			{
				y.setLeft(z);
			}
			else
			{
				y.setRight(z);
			}
		}
	}
	
	public void updateNode(Node z)
	{
		Node pointer = getNode(root, z.getKey());
		if(pointer != null)
		{
			pointer.setData(z.getData());
		}
	}
	
	public void preOrderWalk(Node x)
	{
		if( x != null )
		{
			System.out.println(x.toString());
			preOrderWalk(x.getLeft());
			preOrderWalk(x.getRight());
		}
	}
	
	public void preOrderWalk(Node x, java.util.ArrayList<String> list)
	{
		if( x != null )
		{
			list.add(x.toString());
			preOrderWalk(x.getLeft(), list);
			preOrderWalk(x.getRight(), list);
		}
	}
	
	public void preOrderWalk(Node x, String id, java.util.ArrayList<String> result)
	{
		if( x != null )
		{
			System.out.println(x.toString() + " " + id);
			result.add(x.getKey() + " " + id);
			preOrderWalk(x.getLeft(), "0"+id, result);
			preOrderWalk(x.getRight(), "1"+id, result);
		}
	}
	
	public void inOrderWalk(Node x)
	{
		if( x != null )
		{
			inOrderWalk(x.getLeft());
			System.out.println(x.toString());
			inOrderWalk(x.getRight());
		}
	}
	
	public void inOrderWalk(Node x, java.util.ArrayList<String> list)
	{
		if(x != null)
		{
			inOrderWalk(x.getLeft(), list);
			list.add(x.toString());
			inOrderWalk(x.getRight(), list);

		}
	}
	
	public void postOrderWalk(Node x)
	{
		if( x != null )
		{
			postOrderWalk(x.getLeft());
			postOrderWalk(x.getRight());
			System.out.println(x.toString());
		}
	}
	
	public void postOrderWalk(Node x, java.util.ArrayList<String> list)
	{
		if(x != null)
		{
			postOrderWalk(x.getLeft(), list);
			postOrderWalk(x.getRight(), list);
			list.add(x.toString());

		}
	}
		
	public Node getMax(Node x)
	{
		while(x.getRight() != null)
		{
			x = x.getRight();
		}
		return x; 
	}
	
	public Node getMin(Node x)
	{
		while(x.getLeft() != null)
		{
			x = x.getLeft();
		}
		return x; 	
	}
	
	public Node getSuccessor(Node x)
	{
		if(x.getRight() != null)
		{
			return getMin(x.getRight());
		}
		Node y = x.getParent();
		while((y != null) && x == y.getRight())
		{
			x = y;
			y = y.getParent();
		}
		return y;
	}
	
	public Node getPredecessor(Node x)
	{
		if(x.getLeft() != null)
		{
			return getMax(x.getLeft());
		}
		Node y = x.getParent();
		while((y != null) && x == y.getLeft())
		{
			x = y;
			y = y.getParent();
		}
		return y;	
	}
	
	public Node getNode(Node x, int key)
	{
		if( (x == null) || (x.getKey() == key))
		{
			return x;
		}
		if(x.getKey() < key)
		{
			return getNode(x.getRight(),key);
		}
		else
		{
			return getNode(x.getLeft(),key);
		}
	}
	
	public int getHeight(Node x)
	{
		if(x == null)
		{
			return -1;
		}
		int leftHeight = getHeight(x.getLeft());
		int rightHeight = getHeight(x.getRight());
		if(leftHeight > rightHeight)
		{
			return leftHeight + 1;
		}
		return rightHeight + 1;
	}
	
	public void shiftNode(Node u, Node v)
	{
		if(u.getParent() == null)
		{
			setRoot(v);
		}
		else 
		{
			if( u == u.getParent().getLeft() )
			{
				u.getParent().setLeft(v);
			}
			else
			{
				u.getParent().setRight(v);
			}
		}
		if(v != null)
		{
			v.setParent(u.getParent());
		}
	}
	
	public void deleteNode(Node z)
	{
		if( z.getLeft() == null )
		{
			shiftNode(z, z.getRight());
		}
		else
		{
			if( z.getRight() == null )
			{
				shiftNode(z, z.getLeft());
			}
			else 
			{
				Node y = getSuccessor(z);
				if( y.getParent() != z )
				{
					shiftNode(y, y.getRight());
					y.setRight(z.getRight());
					y.getRight().setParent(y);
				}
				shiftNode(z, y);
				y.setLeft(z.getLeft());
				y.getLeft().setParent(y);
			}
		}
	}
}
