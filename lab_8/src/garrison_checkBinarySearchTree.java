import java.util.ArrayList;
import java.util.HashSet;

class garrison_checkBinarySearchTree
{	
	public static void main(String[] args)
	{
		boolean outputTreeWalks = false;
		
		if( args.length != 1 )
		{
			System.out.println("format: checkBinarySearchTree \"test file\"");
			System.exit(0);
		}
		
		System.out.println("testing garrison");
		
		// change "new BinarySearchTree()" to the appropriate 
		BinarySearchTreeFunctions bst = new bhagat_BinarySearchTree();
		
		double[] testResults = new double[9]; 
			
		ArrayList<ArrayList<String>> testData = new ArrayList<>();
		for( int i = 0; i < 8; i++ )
		{
			testData.add(new ArrayList<String>());
		}
		
		try
		{
			System.out.println("reading " + args[0]);
			java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(args[0])));
			for( int i = 0; i < testData.size(); i++ )
			{
				ArrayList<String> currentTestData = testData.get(i); 
				String inn;
				input.readLine();
				while( (inn = input.readLine()) != null )
				{
					if( inn.length() > 0 )
					{
						currentTestData.add(inn);
					}
					else
					{
						break;
					}
				}
				testData.set(i, currentTestData);
				System.out.println("testData.get(" + i + ").size() = " + testData.get(i).size());
			}
			input.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
		
		// test 1 insert 2000 keys into the empty bst
		ArrayList<String> insertList1 = testData.get(0);
		HashSet<Integer> setOfKeys = new HashSet<>();
		for( int i = 0; i < insertList1.size(); i++ )
		{
			int key = Integer.parseInt(insertList1.get(i));
			Node n = new Node(key);
			bst.insertNode(n);
			setOfKeys.add(Integer.valueOf(key));
		}
		System.out.println("bst.getHeight(bst.getRoot()) = " + bst.getHeight(bst.getRoot()));
		System.out.println("bst.getMax(bst.getRoot()) = " + bst.getMax(bst.getRoot()));
		System.out.println("bst.getMin(bst.getRoot()) = " + bst.getMin(bst.getRoot()));
		System.out.println("bst.getRoot() = " + bst.getRoot());
		System.out.println("bst.getSuccessor(bst.getRoot()) = " + bst.getSuccessor(bst.getRoot()));
		System.out.println("bst.getPredecessor(bst.getRoot()) = " + bst.getPredecessor(bst.getRoot()));
		System.out.println("setOfKeys.size() = " + setOfKeys.size());
		
		testResults[0] = 0.0;
		if( bst.getHeight(bst.getRoot()) != 22 )
		{
			testResults[0] = testResults[0]+0.2;
		}
		
		if( (bst.getMax(bst.getRoot()) == null) || (bst.getMax(bst.getRoot()).getKey() != 8996) )
		{
			testResults[0]= testResults[0]+0.2;
		}
		
		if( (bst.getMin(bst.getRoot()) == null) || (bst.getMin(bst.getRoot()).getKey() != 0) )
		{
			testResults[0]= testResults[0]+0.2;
		}
		
		if( (bst.getRoot() == null) || (bst.getRoot().getKey() != 1721) )
		{
			testResults[0]= testResults[0]+0.2;
		}
		
		if( (bst.getSuccessor(bst.getRoot()) == null) || (bst.getSuccessor(bst.getRoot()).getKey() != 1724) )
		{
			testResults[0]= testResults[0]+0.2;
		}
		
		if( (bst.getPredecessor(bst.getRoot()) == null) || (bst.getPredecessor(bst.getRoot()).getKey() != 1720) )
		{
			testResults[0]= testResults[0]+0.2;
		}
		testResults[0] = Math.min(1.0, testResults[0]);
		System.out.println("testResults[0] = " + testResults[0]);
		
		// test 2 preOrderWalk
		ArrayList<String> preOrderWalk = new ArrayList<>();
		bst.preOrderWalk(bst.getRoot(), preOrderWalk);
		System.out.println("preOrderWalk.size() = " + preOrderWalk.size());
		if( outputTreeWalks )
		{
			for( int i = 0; i < preOrderWalk.size(); i++ )
			{
				System.out.println(preOrderWalk.get(i));
			}
		}
		testResults[1] = compare(preOrderWalk, testData.get(5));
		System.out.println("testResults[1] = " + testResults[1]);
		
		// test 3 search for 2000 keys
		testResults[2] = 0.0;
		ArrayList<String> searchList1 = testData.get(1);
		int foundCountBst = 0;
		int foundCountHashSet = 0;
		for( int i = 0; i < searchList1.size(); i++ )
		{
			try
			{
				int key = Integer.parseInt(searchList1.get(i));
				if( setOfKeys.contains(Integer.valueOf(key)) )
				{
					foundCountHashSet = foundCountHashSet+1;
				}
				
				if( bst.getNode(bst.getRoot(), key) != null )
				{
					foundCountBst = foundCountBst+1;
				}
			}
			catch(Exception e)
			{
				testResults[2] = testResults[2]+0.3;
				System.out.println("exception searching for key " + Integer.parseInt(searchList1.get(i)));
			}
		}
		System.out.println("foundCountBst = " + foundCountBst);
		System.out.println("foundCountHashSet = " + foundCountHashSet);
		
		if( foundCountBst != 460 )
		{
			testResults[2] = 1.0;
		}
		System.out.println("testResults[2] = " + testResults[2]);
		
		// test 4 delete 1000 keys
		testResults[3] = 0.0;
		ArrayList<String> deleteList1 = testData.get(2);
		for( int i = 0; i < deleteList1.size(); i++ )
		{
			try
			{
				int key = Integer.parseInt(deleteList1.get(i));
				Node n = bst.getNode(bst.getRoot(), key);
				bst.deleteNode(n);
				setOfKeys.remove(Integer.valueOf(key));
			}
			catch(Exception e)
			{
				testResults[3] = testResults[3]+0.5;
				System.out.println("exception deleting node with key " + Integer.parseInt(deleteList1.get(i)));
			}
		}
		
		System.out.println("done with deletes");

		System.out.println("bst.getHeight(bst.getRoot()) = " + bst.getHeight(bst.getRoot()));
		System.out.println("bst.getMax(bst.getRoot()) = " + bst.getMax(bst.getRoot()));
		System.out.println("bst.getMin(bst.getRoot()) = " + bst.getMin(bst.getRoot()));
		System.out.println("bst.getRoot() = " + bst.getRoot());
		try
		{
			System.out.println("bst.getSuccessor(bst.getRoot()) = " + bst.getSuccessor(bst.getRoot()));
		}
		catch(Exception e)
		{
			
		}
		try
		{
			System.out.println("bst.getPredecessor(bst.getRoot()) = " + bst.getPredecessor(bst.getRoot()));
		}
		catch(Exception e)
		{
			
		}
		System.out.println("setOfKeys.size() = " + setOfKeys.size());
		
		if( bst.getHeight(bst.getRoot()) != 20 )
		{
			testResults[3] = testResults[3]+0.2;
		}
		
		if( (bst.getMax(bst.getRoot()) == null) || (bst.getMax(bst.getRoot()).getKey() != 8995) )
		{
			testResults[3]= testResults[3]+0.2;
		}
		
		if( (bst.getMin(bst.getRoot()) == null) || (bst.getMin(bst.getRoot()).getKey() != 2) )
		{
			testResults[3]= testResults[3]+0.2;
		}
		
		if( (bst.getRoot() == null) || (bst.getRoot().getKey() != 1745) )
		{
			testResults[3]= testResults[3]+0.2;
		}
		
		try
		{
			if( (bst.getSuccessor(bst.getRoot()) == null) || (bst.getSuccessor(bst.getRoot()).getKey() != 1757) )
			{
				testResults[3]= testResults[3]+0.2;
			}
		}
		catch(Exception e)
		{
			testResults[3]= testResults[3]+0.2;
			System.out.println("exception getting successor");
		}
		
		try
		{
			if( (bst.getPredecessor(bst.getRoot()) == null) || (bst.getPredecessor(bst.getRoot()).getKey() != 1720) )
			{
				testResults[3]= testResults[3]+0.2;
			}
		}
		catch(Exception e)
		{
			testResults[3]= testResults[3]+0.2;
			System.out.println("exception getting predecessor");
		}
		
		testResults[3] = Math.min(1.0, testResults[3]);
		System.out.println("testResults[3] = " + testResults[3]);
		
		// test 5 inOrderWalk
		ArrayList<String> inOrderWalk = new ArrayList<>();
		bst.inOrderWalk(bst.getRoot(), inOrderWalk);
		System.out.println("inOrderWalk.size() = " + inOrderWalk.size());
		if( outputTreeWalks )
		{
			for( int i = 0; i < inOrderWalk.size(); i++ )
			{
				System.out.println(inOrderWalk.get(i));
			}
		}
		testResults[4] = compare(inOrderWalk, testData.get(6));
		System.out.println("testResults[4] = " + testResults[4]);
		
		// test 6 insert 1000 keys
		ArrayList<String> insertList2 = testData.get(3);
		for( int i = 0; i < insertList2.size(); i++ )
		{
			int key = Integer.parseInt(insertList2.get(i));
			Node n = new Node(key);
			bst.insertNode(n);
			setOfKeys.add(Integer.valueOf(key));
		}
		System.out.println("bst.getHeight(bst.getRoot()) = " + bst.getHeight(bst.getRoot()));
		System.out.println("bst.getMax(bst.getRoot()) = " + bst.getMax(bst.getRoot()));
		System.out.println("bst.getMin(bst.getRoot()) = " + bst.getMin(bst.getRoot()));
		System.out.println("bst.getRoot() = " + bst.getRoot());
		System.out.println("bst.getSuccessor(bst.getRoot()) = " + bst.getSuccessor(bst.getRoot()));
		System.out.println("bst.getPredecessor(bst.getRoot()) = " + bst.getPredecessor(bst.getRoot()));
		System.out.println("setOfKeys.size() = " + setOfKeys.size());
		
		testResults[5] = 0.0;
		if( bst.getHeight(bst.getRoot()) != 23 )
		{
			testResults[5] = testResults[5]+0.2;
		}
		
		if( (bst.getMax(bst.getRoot()) == null) || (bst.getMax(bst.getRoot()).getKey() != 8995) )
		{
			testResults[5]= testResults[5]+0.2;
		}
		
		if( (bst.getMin(bst.getRoot()) == null) || (bst.getMin(bst.getRoot()).getKey() != 2) )
		{
			testResults[5]= testResults[5]+0.2;
		}
		
		if( (bst.getRoot() == null) || (bst.getRoot().getKey() != 1745) )
		{
			testResults[5]= testResults[5]+0.2;
		}
		
		if( (bst.getSuccessor(bst.getRoot()) == null) || (bst.getSuccessor(bst.getRoot()).getKey() != 1754) )
		{
			testResults[5]= testResults[5]+0.2;
		}
		
		if( (bst.getPredecessor(bst.getRoot()) == null) || (bst.getPredecessor(bst.getRoot()).getKey() != 1738) )
		{
			testResults[5]= testResults[5]+0.2;
		}
		testResults[5] = Math.min(1.0, testResults[5]);
		System.out.println("testResults[5] = " + testResults[5]);
		
		// test 7 postOrderWalk
		ArrayList<String> postOrderWalk = new ArrayList<>();
		try
		{
			bst.postOrderWalk(bst.getRoot(), postOrderWalk);
		}
		catch(Exception e)
		{
			System.out.println("exception in postOrderWalk");
			postOrderWalk = new ArrayList<>();
		}
		System.out.println("postOrderWalk.size() = " + postOrderWalk.size());
		if( outputTreeWalks )
		{
			for( int i = 0; i < postOrderWalk.size(); i++ )
			{
				System.out.println(postOrderWalk.get(i));
			}
		}
		testResults[6] = compare(postOrderWalk, testData.get(7));
		System.out.println("testResults[6] = " + testResults[6]);
		
		// test 8 search for 2000 keys
		testResults[7] = 0.0;
		ArrayList<String> searchList2 = testData.get(4);
		int foundCountBst2 = 0;
		int foundCountHashSet2 = 0;
		for( int i = 0; i < searchList2.size(); i++ )
		{
			try
			{
				int key = Integer.parseInt(searchList2.get(i));
				if( setOfKeys.contains(Integer.valueOf(key)) )
				{
					foundCountHashSet2 = foundCountHashSet2+1;
				}
				
				if( bst.getNode(bst.getRoot(), key) != null )
				{
					foundCountBst2 = foundCountBst2+1;
				}
			}
			catch(Exception e)
			{
				testResults[7] = testResults[7]+0.3;
				System.out.println("exception searching for key " + Integer.parseInt(searchList2.get(i)));
			}
		}
		System.out.println("foundCountBst2 = " + foundCountBst2);
		System.out.println("foundCountHashSet2 = " + foundCountHashSet2);
		
		if( foundCountBst2 != 448 )
		{
			testResults[7] = 1.0;
		}
		System.out.println("testResults[7] = " + testResults[7]);
		
		if( false )
		{
			System.err.print("testResults prior to updateNode test = ");
			for( int i = 0; i < testResults.length-1; i++ )
			{
				System.err.print("\t" + testResults[i]);
			}
			System.err.print("\t" + 1.0);
			System.err.println();
		}
		System.out.println("passes the if statement");
		// test updatedNode
		testResults[8] = 0.0;

		String dataForN5507 = new String("node with key 5507 data");
		String updatedDataForN5510 = new String("updated data for node with key 5510");
		
		// insert key 5507 with data
		Node n5507 = new Node(5507, dataForN5507);
		bst.insertNode(n5507);
		System.out.println("success in inserting node with data");

		
		// insert key 5510 without data
		Node n5510 = new Node(5510);
		bst.insertNode(n5510);
		System.out.println("success in inserting node without data");

		
		// update data for node with key 5510
		Node n5510WithData = new Node(5510, updatedDataForN5510);	
		try
		{
			bst.updateNode(n5510WithData);
			System.out.println("success in updaying node with a key");
		}
		catch(Exception e)
		{
			testResults[8] = testResults[8]+0.8;
			System.out.println("exception updating node");
		}
		
		// retrieve the two nodes
		Node n = bst.getNode(bst.getRoot(), 5507);
		Node n2 = bst.getNode(bst.getRoot(), 5510);
		
		// check the data for the node with key 5507
		if( n == null )
		{
			testResults[8] = testResults[8]+0.5;
		}
		else
		{
			if( n.getData() == null )
			{
				testResults[8] = testResults[8]+0.3;
			}
			else
			{
				if( n.getData() != dataForN5507 )
				{
					testResults[8] = testResults[8]+0.2;
				}
			}
		}
		
		// check the data for the node with key 5510
		if( n2 == null )
		{
			testResults[8] = testResults[8]+0.5;
		}
		else
		{
			if( n2.getData() == null )
			{
				testResults[8] = testResults[8]+0.3;
			}
			else
			{
				if( n2.getData() != updatedDataForN5510 )
				{
					testResults[8] = testResults[8]+0.2;
				}
			}
		}
		
		testResults[8] = Math.min(1.0, testResults[8]);
		
		System.out.println("testResults[8] = " + testResults[8]);
		
		System.err.print("garrison ");
		for( int i = 0; i < testResults.length; i++ )
		{
			System.err.print("\t" + testResults[i]);
		}
		System.err.println();
	}
	
	private static double compare(ArrayList<String> treeWalk, ArrayList<String> checkTreeWalk)
	{
		double result = 0.0;
		double delta = 0.05;
		ArrayList<String> tempTreeWalk = new ArrayList<>();
		ArrayList<String> tempCheckTreeWalk = new ArrayList<>();
				
		if( treeWalk.size() != checkTreeWalk.size() )
		{
			result = result + (Math.abs(treeWalk.size()-checkTreeWalk.size())*delta);
		}
		
		for( String s : treeWalk )
		{
			if( !checkTreeWalk.contains(s) )
			{
				result = result + delta;
			}
			else
			{
				tempTreeWalk.add(s);
			}
		}
		
		for( String s : checkTreeWalk )
		{
			if( !treeWalk.contains(s) )
			{
				result = result + delta;
			}
			else
			{
				tempCheckTreeWalk.add(s);
			}
		}
		
		int minSize = (int) Math.min(tempTreeWalk.size(), tempCheckTreeWalk.size());
		
		for( int i = 0; i < minSize; i++ )
		{
			if( !tempTreeWalk.get(i).equals(tempCheckTreeWalk.get(i)) )
			{
				result = result + delta;
			}
		}
		
		return Math.min(1.0, result);
	}
}
