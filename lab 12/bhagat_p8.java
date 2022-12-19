import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

class bhagat_p8 extends BinarySearchTree
{
	private static int width = 700;
	private static int width2 = 350;
	private static int height = 350;
	
	// data and buttons gets declared here to remain universal as it gets passed among several methods   
	
	// data storage declarations
	// int[] sortValues contains the list of values read from the “sort file”
	private static int[] sortValues;
	// int[] searchValues contains the list of values read from the “search file”
	private static int[] searchValues;
	// int[] sortedValues is the array that is sorted in selectionSort()
	private static int[] sortedValues;
	// treeSetValues<Integer> is the tree set used for adding to and searching
	private static java.util.TreeSet<Integer> treeSetValues = new java.util.TreeSet<>();
	// hashSetValues<Integer> is the hash set used for adding to and searching
	private static java.util.HashSet<Integer> hashSetValues = new java.util.HashSet<>();
	// priorityQueueValues<Integer> is the priority queue used for adding to and searching
	private static java.util.PriorityQueue<Integer> priorityQueueValues = new java.util.PriorityQueue<>();
	// arrayListValues<Integer> is the unsorted ArrayList used for adding to and searching
	private static java.util.ArrayList<Integer> arrayListValues = new java.util.ArrayList<>();
	// sortedArrayListValues<Integer> is the sorted ArrayList used for adding to and searching
	private static java.util.ArrayList<Integer> sortedArrayListValues = new java.util.ArrayList<>();
	// int[] unsortedValues is the unsorted int[] used for adding to and searching
	private static int[] unsortedValues;
	// bst is the binary search tree used for adding to and searching
	private static BinarySearchTree bst = new BinarySearchTree();
	
	private static String sortFileName;
	private static String searchFileName;
	
	// booleans to help determine whether to enable the button after a button has been pressed
	private static boolean enableSearchButton;
	private static boolean readInts;
	private static boolean readBst;
	private static boolean readTreeSet;
	private static boolean readPriorityQueue;
	private static boolean readHashSet;
	private static boolean readArrayList;
	private static boolean readSortedArrayList;
	private static boolean readArray;
	
	// button and label declarations
	
	// sort Buttons and Labels
	
	private static javax.swing.JButton sortIntsButton;
	private static javax.swing.JButton addToBstButton;
	private static javax.swing.JButton addToTreeSetButton;
	private static javax.swing.JButton addToPriorityQueueButton;
	private static javax.swing.JButton addToHashSetButton;
	private static javax.swing.JButton addToArrayListButton;
	private static javax.swing.JButton addToSortedArrayListButton;
	private static javax.swing.JButton addToArrayButton;

	private static javax.swing.JLabel sortIntsLabel;
	private static javax.swing.JLabel addToBstLabel;
	private static javax.swing.JLabel addToTreeSetLabel;
	private static javax.swing.JLabel addToPriorityQueueLabel;
	private static javax.swing.JLabel addToHashSetLabel;
	private static javax.swing.JLabel addToArrayListLabel;
	private static javax.swing.JLabel addToSortedArrayListLabel;
	private static javax.swing.JLabel addToArrayLabel;
	
	// search buttons and Labels
	
	private static javax.swing.JButton searchIntsButton;
	private static javax.swing.JButton searchBstButton;
	private static javax.swing.JButton searchTreeSetButton;
	private static javax.swing.JButton searchPriorityQueueButton;
	private static javax.swing.JButton searchHashSetButton;
	private static javax.swing.JButton searchArrayListButton;
	private static javax.swing.JButton searchSortedArrayListButton;
	private static javax.swing.JButton searchArrayButton;

	private static javax.swing.JLabel searchIntsLabel;
	private static javax.swing.JLabel searchBstLabel;
	private static javax.swing.JLabel searchTreeSetLabel;
	private static javax.swing.JLabel searchPriorityQueueLabel;
	private static javax.swing.JLabel searchHashSetLabel;
	private static javax.swing.JLabel searchArrayListLabel;
	private static javax.swing.JLabel searchSortedArrayListLabel;
	private static javax.swing.JLabel searchArrayLabel;
	
	public static void main(String[] args)
	{
		sortFileName = args[0];
		searchFileName = args[1];
		
		//
		// create the window and specify the size and what to do when the window is closed
		javax.swing.JFrame f = new javax.swing.JFrame();
		f.setPreferredSize(new java.awt.Dimension(width, height));
		f.setMinimumSize(new java.awt.Dimension(width, height));
		f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		
		// create the menu bar
		javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
		
		// create the two menus
		javax.swing.JMenu fileMenu = new javax.swing.JMenu("File");		
		// create the menu items for the two menus
		javax.swing.JMenuItem fileExit = new javax.swing.JMenuItem("Exit");
		javax.swing.JMenuItem fileReadSortFile = new javax.swing.JMenuItem("Read Sort File");
		javax.swing.JMenuItem fileSearchFile = new javax.swing.JMenuItem("Read Search File");

		// add the two menus to the menu bar
		menuBar.add(fileMenu);
		
		// add the two menu items to the two menus
		fileMenu.add(fileReadSortFile);
		fileMenu.add(fileSearchFile);
		fileMenu.add(fileExit);

		// create the panel to hold the buttons
		javax.swing.JPanel leftButtonPanel = new javax.swing.JPanel();
		leftButtonPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255, 255), 2));
		leftButtonPanel.setPreferredSize(new java.awt.Dimension(width2, height));
		leftButtonPanel.setMinimumSize(new java.awt.Dimension(width2, height));
		java.awt.GridBagLayout leftGridBagLayout = new java.awt.GridBagLayout();
		leftButtonPanel.setLayout(leftGridBagLayout);
		
		javax.swing.JPanel rightButtonPanel = new javax.swing.JPanel();
		rightButtonPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 255, 255), 2));
		rightButtonPanel.setPreferredSize(new java.awt.Dimension(width2, height));
		rightButtonPanel.setMinimumSize(new java.awt.Dimension(width2, height));
		java.awt.GridBagLayout rightGridBagLayout = new java.awt.GridBagLayout();
		rightButtonPanel.setLayout(rightGridBagLayout);
		java.awt.GridBagConstraints buttonPanelConstraints = new java.awt.GridBagConstraints();
		
		java.awt.GridBagConstraints leftButtonPanelConstraints = new java.awt.GridBagConstraints();
		java.awt.GridBagConstraints rightButtonPanelConstraints = new java.awt.GridBagConstraints();


		sortIntsButton = new javax.swing.JButton("sort ints");
		sortIntsLabel = new javax.swing.JLabel("no result");
		addToBstButton = new javax.swing.JButton("add to bst");
		addToBstLabel = new javax.swing.JLabel("no result");
		addToTreeSetButton = new javax.swing.JButton("add to TreeSet");
		addToTreeSetLabel = new javax.swing.JLabel("no result");
		addToPriorityQueueButton = new javax.swing.JButton("add to PriorityQueue");
		addToPriorityQueueLabel = new javax.swing.JLabel("no result");
		addToHashSetButton = new javax.swing.JButton("add to HashSet");
		addToHashSetLabel = new javax.swing.JLabel("no result");
		addToArrayListButton = new javax.swing.JButton("add to ArrayList");
		addToArrayListLabel = new javax.swing.JLabel("no result");
		addToSortedArrayListButton = new javax.swing.JButton("add to SortedArrayList");
		addToSortedArrayListLabel = new javax.swing.JLabel("no result");
		addToArrayButton = new javax.swing.JButton("add to Array");
		addToArrayLabel = new javax.swing.JLabel("no result");
		
		searchIntsButton = new javax.swing.JButton("search sorted ints");
		searchIntsLabel = new javax.swing.JLabel("no result");
		searchBstButton = new javax.swing.JButton("search bst");
		searchBstLabel = new javax.swing.JLabel("no result");
		searchTreeSetButton = new javax.swing.JButton("search TreeSet");
		searchTreeSetLabel = new javax.swing.JLabel("no result");
		searchPriorityQueueButton = new javax.swing.JButton("search PriorityQueue");
		searchPriorityQueueLabel = new javax.swing.JLabel("no result");
		searchHashSetButton = new javax.swing.JButton("search HashSet");
		searchHashSetLabel = new javax.swing.JLabel("no result");
		searchArrayListButton = new javax.swing.JButton("search ArrayList");
		searchArrayListLabel = new javax.swing.JLabel("no result");
		searchSortedArrayListButton = new javax.swing.JButton("search SortedArrayList");
		searchSortedArrayListLabel = new javax.swing.JLabel("no result");
		searchArrayButton = new javax.swing.JButton("search Array");
		searchArrayLabel = new javax.swing.JLabel("no result");
		
		leftButtonPanelConstraints.weightx = 1;
		leftButtonPanelConstraints.weighty = 1;
		leftButtonPanelConstraints.fill = java.awt.GridBagConstraints.NONE;
		leftButtonPanelConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		
		// sortInts button constraints
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 0;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(sortIntsButton, leftButtonPanelConstraints);
		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 0;
		leftButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(sortIntsLabel, leftButtonPanelConstraints);
		
		// sortBST button constraints
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 1;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToBstButton, leftButtonPanelConstraints);
		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 1;
		leftButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToBstLabel, leftButtonPanelConstraints);
		
		// sortTreeSet button constraints
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 2;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToTreeSetButton, leftButtonPanelConstraints);
		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 2;
		leftButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToTreeSetLabel, leftButtonPanelConstraints);
		
		// sort PriorityQueue
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 3;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToPriorityQueueButton, leftButtonPanelConstraints);
		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 3;
		leftButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToPriorityQueueLabel, leftButtonPanelConstraints);
		
		// sort HashSet
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 4;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToHashSetButton, leftButtonPanelConstraints);
		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 4;
		leftButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToHashSetLabel, leftButtonPanelConstraints);
		
		// sort ArrayList
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 5;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToArrayListButton, leftButtonPanelConstraints);
		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 5;
		leftButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToArrayListLabel, leftButtonPanelConstraints);
		
		// sort SortedArrayList Button
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 6;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToSortedArrayListButton, leftButtonPanelConstraints);
		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 6;
		leftButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToSortedArrayListLabel, leftButtonPanelConstraints);
		
		// sort array button
		leftButtonPanelConstraints.gridx = 0;
		leftButtonPanelConstraints.gridy = 7;
		leftButtonPanelConstraints.gridwidth = 1;
		leftGridBagLayout.setConstraints(addToArrayButton, leftButtonPanelConstraints);
		leftButtonPanelConstraints.gridx = 1;
		leftButtonPanelConstraints.gridy = 7;
		leftButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		leftGridBagLayout.setConstraints(addToArrayLabel, leftButtonPanelConstraints);
		
		// add to panel buttons
		leftButtonPanel.add(sortIntsButton);
		leftButtonPanel.add(sortIntsLabel);
		leftButtonPanel.add(addToBstButton);
		leftButtonPanel.add(addToBstLabel);
		leftButtonPanel.add(addToTreeSetButton);
		leftButtonPanel.add(addToTreeSetLabel);
		leftButtonPanel.add(addToPriorityQueueButton);
		leftButtonPanel.add(addToPriorityQueueLabel);
		leftButtonPanel.add(addToHashSetButton);
		leftButtonPanel.add(addToHashSetLabel);
		leftButtonPanel.add(addToArrayListButton);
		leftButtonPanel.add(addToArrayListLabel);
		leftButtonPanel.add(addToSortedArrayListButton);
		leftButtonPanel.add(addToSortedArrayListLabel);
		leftButtonPanel.add(addToArrayButton);
		leftButtonPanel.add(addToArrayLabel);
		
		sortIntsButton.setEnabled(false);
		addToBstButton.setEnabled(false);
		addToTreeSetButton.setEnabled(false);
		addToPriorityQueueButton.setEnabled(false);
		addToHashSetButton.setEnabled(false);
		addToArrayListButton.setEnabled(false);
		addToSortedArrayListButton.setEnabled(false);
		addToArrayButton.setEnabled(false);

		rightButtonPanelConstraints.weightx = 1;
		rightButtonPanelConstraints.weighty = 1;
		rightButtonPanelConstraints.fill = java.awt.GridBagConstraints.NONE;
		rightButtonPanelConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		
		// search sorted ints 
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 0;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchIntsButton, rightButtonPanelConstraints);
		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 0;
		rightButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchIntsLabel, rightButtonPanelConstraints);
		
		// search Bst
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 1;
		leftButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchBstButton, rightButtonPanelConstraints);
		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 1;
		rightButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchBstLabel, rightButtonPanelConstraints);
		
		// search tree set
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 2;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchTreeSetButton, rightButtonPanelConstraints);
		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 2;
		rightButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchTreeSetLabel, rightButtonPanelConstraints);

		// search priority queue
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 3;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchPriorityQueueButton, rightButtonPanelConstraints);
		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 3;
		rightButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchPriorityQueueLabel, rightButtonPanelConstraints);
		
		// search hash set
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 4;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchHashSetButton, rightButtonPanelConstraints);
		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 4;
		rightButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchHashSetLabel, rightButtonPanelConstraints);
		
		// search ArrayList
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 5;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchArrayListButton, rightButtonPanelConstraints);
		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 5;
		rightButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchArrayListLabel, rightButtonPanelConstraints);
		
		// search SortedArrayListButton
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 6;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchSortedArrayListButton, rightButtonPanelConstraints);
		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 6;
		rightButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchSortedArrayListLabel, rightButtonPanelConstraints);
		
		// search searchArrayButton
		rightButtonPanelConstraints.gridx = 0;
		rightButtonPanelConstraints.gridy = 7;
		rightButtonPanelConstraints.gridwidth = 1;
		rightGridBagLayout.setConstraints(searchArrayButton, rightButtonPanelConstraints);
		rightButtonPanelConstraints.gridx = 1;
		rightButtonPanelConstraints.gridy = 7;
		rightButtonPanelConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		rightGridBagLayout.setConstraints(searchArrayLabel, rightButtonPanelConstraints);
		
		rightButtonPanel.add(searchIntsButton);
		rightButtonPanel.add(searchIntsLabel);
		rightButtonPanel.add(searchBstButton);
		rightButtonPanel.add(searchBstLabel);
		rightButtonPanel.add(searchTreeSetButton);
		rightButtonPanel.add(searchTreeSetLabel);
		rightButtonPanel.add(searchPriorityQueueButton);
		rightButtonPanel.add(searchPriorityQueueLabel);
		rightButtonPanel.add(searchHashSetButton);
		rightButtonPanel.add(searchHashSetLabel);
		rightButtonPanel.add(searchArrayListButton);
		rightButtonPanel.add(searchArrayListLabel);
		rightButtonPanel.add(searchSortedArrayListButton);
		rightButtonPanel.add(searchSortedArrayListLabel);
		rightButtonPanel.add(searchArrayButton);
		rightButtonPanel.add(searchArrayLabel);
		
		searchIntsButton.setEnabled(false);
		searchBstButton.setEnabled(false);
		searchTreeSetButton.setEnabled(false);
		searchPriorityQueueButton.setEnabled(false);
		searchHashSetButton.setEnabled(false);
		searchArrayListButton.setEnabled(false);
		searchSortedArrayListButton.setEnabled(false);
		searchArrayButton.setEnabled(false);

		javax.swing.JPanel mainButtonPanel = new javax.swing.JPanel();
		mainButtonPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 0, 255), 2));
		javax.swing.BoxLayout boxLayout = new javax.swing.BoxLayout(mainButtonPanel, javax.swing.BoxLayout.X_AXIS);
		mainButtonPanel.setLayout(boxLayout);
		mainButtonPanel.add(leftButtonPanel);
		mainButtonPanel.add(rightButtonPanel);

		
		// make the weights non zero so that the components spread out 
		buttonPanelConstraints.weightx = 1;
		buttonPanelConstraints.weighty = 1;
		
		// have the components fill all of the cells that they occupy 
		buttonPanelConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		
								
		// create the panel
		javax.swing.JPanel mainPanel = new javax.swing.JPanel();
		mainPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0, 255), 2));
		mainPanel.setLayout(new java.awt.BorderLayout());
		mainPanel.add(menuBar, java.awt.BorderLayout.NORTH);
		mainPanel.add(mainButtonPanel, java.awt.BorderLayout.CENTER);
		
		// create the action listeners for the menu items
		MenuItemActionListener fileExitMenuItemActionListener = new MenuItemActionListener(fileExit);
		MenuItemActionListener fileReadSortExitMenuItemActionListener = new MenuItemActionListener(fileReadSortFile);
		MenuItemActionListener fileSearchFileExitMenuItemActionListener = new MenuItemActionListener(fileSearchFile);

		
		// add the action listeners to the menu items
		fileExit.addActionListener(fileExitMenuItemActionListener);
		fileReadSortFile.addActionListener(fileReadSortExitMenuItemActionListener);
		fileSearchFile.addActionListener(fileSearchFileExitMenuItemActionListener);
		
		// create the button listeners for the button items
		ButtonActionListener sortIntsActionListener = new ButtonActionListener(sortIntsButton);
		ButtonActionListener addToBstActionListener = new ButtonActionListener(addToBstButton);
		ButtonActionListener addToTreeSetActionListener = new ButtonActionListener(addToTreeSetButton);
		ButtonActionListener addToPriorityQueueActionListener = new ButtonActionListener(addToPriorityQueueButton);
		ButtonActionListener addToHashSetActionListener = new ButtonActionListener(addToHashSetButton);
		ButtonActionListener addToArrayListActionListener = new ButtonActionListener(addToArrayListButton);
		ButtonActionListener addToSortedArrayListActionListener = new ButtonActionListener(addToSortedArrayListButton);
		ButtonActionListener addToArrayActionListener = new ButtonActionListener(addToArrayButton);

		ButtonActionListener searchSortedIntsActionListener = new ButtonActionListener(searchIntsButton);
		ButtonActionListener searchBstActionListener = new ButtonActionListener(searchBstButton);
		ButtonActionListener searchTreeSetActionListener = new ButtonActionListener(searchTreeSetButton);
		ButtonActionListener searchPriorityQueueActionListener = new ButtonActionListener(searchPriorityQueueButton);
		ButtonActionListener searchHashSetActionListener = new ButtonActionListener(searchHashSetButton);
		ButtonActionListener searchArrayListActionListener = new ButtonActionListener(searchArrayListButton);
		ButtonActionListener searchSortedArrayListActionListener = new ButtonActionListener(searchSortedArrayListButton);
		ButtonActionListener searchArrayActionListener = new ButtonActionListener(searchArrayButton);

		// add the action listeners to the button items
		sortIntsButton.addActionListener(sortIntsActionListener);
		addToBstButton.addActionListener(addToBstActionListener);
		addToTreeSetButton.addActionListener(addToTreeSetActionListener);
		addToPriorityQueueButton.addActionListener(addToPriorityQueueActionListener);
		addToHashSetButton.addActionListener(addToHashSetActionListener);
		addToArrayListButton.addActionListener(addToArrayListActionListener);
		addToSortedArrayListButton.addActionListener(addToSortedArrayListActionListener);
		addToArrayButton.addActionListener(addToArrayActionListener);
		
		searchIntsButton.addActionListener(searchSortedIntsActionListener);
		searchBstButton.addActionListener(searchBstActionListener);
		searchTreeSetButton.addActionListener(searchTreeSetActionListener);
		searchPriorityQueueButton.addActionListener(searchPriorityQueueActionListener);
		searchHashSetButton.addActionListener(searchHashSetActionListener);
		searchArrayListButton.addActionListener(searchArrayListActionListener);
		searchSortedArrayListButton.addActionListener(searchSortedArrayListActionListener);
		searchArrayButton.addActionListener(searchArrayActionListener);
		
		f.setContentPane(mainPanel);
		
		f.validate();
		f.setVisible(true);
	}
	
	// action listener for the buttons
	static class ButtonActionListener implements java.awt.event.ActionListener
	{
		// the button associated with the action listener, so that we can
		// share this one class with multiple buttons
		private javax.swing.JButton b;
		
		ButtonActionListener(javax.swing.JButton b)
		{
			this.b = b;
		}
		
		public void actionPerformed(java.awt.event.ActionEvent e)
		{
			
			System.out.println("action performed on " + b.getText() + " button");
			String event = b.getText().toLowerCase();
			System.out.println(event);
			long time = System.currentTimeMillis();
			
			if(event.equals("sort ints"))
			{
				selectionSort();
				sortIntsLabel.setText(System.currentTimeMillis() - time + "ms");
				if (enableSearchButton) searchIntsButton.setEnabled(true);
				readInts = true;
			}
			if(event.equals("add to bst"))
			{
				addToBst();
				addToBstLabel.setText(System.currentTimeMillis() - time + "ms");
				if (enableSearchButton) searchBstButton.setEnabled(true);
				readBst = true;
			}
			if(event.equals("add to treeset"))
			{
				addToTreeSet();
				// System.out.println(time);
				// System.out.println(System.currentTimeMillis());
				addToTreeSetLabel.setText(System.currentTimeMillis() - time + "ms");
				if (enableSearchButton) searchTreeSetButton.setEnabled(true);
				readTreeSet = true;
			}
			if(event.equals("add to priorityqueue"))
			{
				addToPriorityQueue();
				addToPriorityQueueLabel.setText(System.currentTimeMillis() - time + "ms");
				if (enableSearchButton) searchPriorityQueueButton.setEnabled(true);
				readPriorityQueue = true;	
			}
			if(event.equals("add to hashset"))
			{
				addToHashSet();
				addToHashSetLabel.setText(System.currentTimeMillis() - time + "ms");
				if (enableSearchButton) searchHashSetButton.setEnabled(true);
				readHashSet = true;	
			}
			if(event.equals("add to arraylist"))
			{
				addToArrayList();
				addToArrayListLabel.setText(System.currentTimeMillis() - time + "ms");
				if (enableSearchButton) searchArrayListButton.setEnabled(true);
				readArrayList = true;
			}
			if(event.equals("add to sortedarraylist"))
			{
				addToSortedArrayList();
				addToSortedArrayListLabel.setText(System.currentTimeMillis() - time + "ms");
				if (enableSearchButton) searchSortedArrayListButton.setEnabled(true);
				readSortedArrayList = true;
			}
			if(event.equals("add to array"))
			{
				addToArray();
				addToArrayLabel.setText(System.currentTimeMillis() - time + "ms");
				if(enableSearchButton) searchArrayButton.setEnabled(true);
				readArray = true;
			}
			if(event.equals("search sorted ints"))
			{
				searchIntsLabel.setText(searchSortedInts() + " / " + (System.currentTimeMillis() - time) + "ms");
			}
			if(event.equals("search bst"))
			{
				searchBstLabel.setText(searchBinarySearchTree() + " / " + (System.currentTimeMillis() - time) + "ms");
			}
			if(event.equals("search treeset"))
			{
				searchTreeSetLabel.setText(searchTreeSet() + " / " + (System.currentTimeMillis() - time) + "ms");
			}
			if(event.equals("search priorityqueue"))
			{
				searchPriorityQueueLabel.setText(searchPriorityQueue() + " / " + (System.currentTimeMillis() - time) + "ms");
			}
			if(event.equals("search hashset"))
			{
				searchHashSetLabel.setText(searchHashSet() + " / " + (System.currentTimeMillis() - time) + "ms");
			}
			if(event.equals("search arraylist"))
			{
				searchArrayListLabel.setText(searchArrayList() + " / " + (System.currentTimeMillis() - time) + "ms");
			}
			if(event.equals("search sortedarraylist"))
			{
				searchSortedArrayListLabel.setText(searchSortedArrayList() + " / " + (System.currentTimeMillis() - time) + "ms");
			}
			if(event.equals("search array"))
			{
				searchArrayLabel.setText(searchArray() + " / " + (System.currentTimeMillis() - time) + "ms");
			}
			
		}
	}
	private static void selectionSort()
	{
		sortedValues = sortValues.clone();
		for(int i = 0; i < sortedValues.length; i++)
		{
			int minInd = i;
			for(int j = i + 1; j < sortedValues.length; j++)
			{
				// get the lowest value index
				if(sortedValues[minInd] > sortedValues[j])
				{
					minInd = j;
				}
			}
			// swap the values
			int temp = sortedValues[i];
			sortedValues[i] = sortedValues[minInd];
			sortedValues[minInd] = temp;
		}
	}
	private static void addToBst()
	{
		for(int x : sortValues)
		{
			bst.insertNode(new Node(x));
		}
	}
	private static void addToTreeSet()
	{
		for(int x : sortValues)
		{
			treeSetValues.add(x);
		}
	}
	private static void addToPriorityQueue()
	{
		for(int x : sortValues)
		{
			priorityQueueValues.add(x);
		}
	}
	private static void addToHashSet()
	{
		for(int x : sortValues)
		{
			hashSetValues.add(x);
		}
	}
	private static void addToArrayList()
	{
		for(int x : sortValues)
		{
			arrayListValues.add(x);
		}
	}
	private static void addToSortedArrayList()
	{
		for (int n : sortValues) 
		{ 
			sortedArrayListValues.add(n); 
		}
		Collections.sort(sortedArrayListValues);
	}
	private static void addToArray()
	{
		unsortedValues = new int[sortValues.length];
		for (int i = 0; i < unsortedValues.length; i++) 
		{ 
			unsortedValues[i] = sortValues[i]; 
		}
	}
	private static int searchSortedInts()
	{
		// search sorted ints implements binarySearch()
		int count = 0;
		for(int searchValue : searchValues)
		{
			int bottom = 0;
			int top = sortedValues.length -1;
			while(bottom <= top)
			{
				int middle = (bottom+top)/2;
				if(searchValue < sortedValues[middle])
				{
					top = middle - 1;
				}
				else 
				{
					if(searchValue > sortedValues[middle])
					{
						bottom = middle + 1;
					}
					else 
					{
						count++;
						break;
					}
					
				}
			}
		}
		return count;
	}
	private static int searchBinarySearchTree()
	{
		int count = 0;
		for(int searchValue : searchValues)
		{
			if(bst.getNode(bst.getRoot(), searchValue) != null)
			{
				count++;
			}
		}
		return count;
	}
	private static int searchTreeSet()
	{
		int count = 0;
		for (int searchValue : searchValues) { 
			if (treeSetValues.contains(searchValue)) count++; 
		}
		return count; 
	}
	private static int searchPriorityQueue()
	{
		int count = 0;
		for (int searchValue : searchValues) { 
			if (priorityQueueValues.contains(searchValue)) count++; 
		}
		return count;
	}
	private static int searchHashSet()
	{
		int count = 0;
		for (int searchValue : searchValues) { 
			if (hashSetValues.contains(searchValue)) count++; 
		}
		return count;
	}
	private static int searchArrayList()
	{
		int count = 0;
		for (int searchValue : searchValues) { 
			if (arrayListValues.contains(searchValue)) count++; 
		}
		return count;
	}
	private static int searchSortedArrayList()
	{
		int count = 0;
		// implements binary search
		for (int searchValue : searchValues) 
		{ 
			int bottom = 0;
			int top = sortedArrayListValues.size() - 1;
			while(bottom <= top)
			{
				int middle = (bottom+top)/2;
				if(searchValue < sortedArrayListValues.get(middle))
				{
					top = middle - 1;
				}
				else 
				{
					if(searchValue > sortedArrayListValues.get(middle))
					{
						bottom = middle + 1;
					}
					else 
					{
						count++;
						break;
					}
					
				}
			}
			
		}
		return count; 
	}
	
	private static int searchArray()
	{
		// linear search through array
		int count = 0;
		for (int i = 0; i < searchValues.length; i++) 
		{ 
			for (int j = 0; j < unsortedValues.length; j++)
			{
				if (searchValues[i] == unsortedValues[j])
				{
					count++;
					break;
				}
			}
		}
		return count;
	}
	
	// action listener for the menu items
	static class MenuItemActionListener implements java.awt.event.ActionListener
	{
		// the menu item associated with the action listener, so that we can
		// share this one class with multiple menu items
		private javax.swing.JMenuItem m;
		
		MenuItemActionListener(javax.swing.JMenuItem m)
		{
			this.m = m;
		}
		
		public void actionPerformed(java.awt.event.ActionEvent e)
		{
			System.out.println("action performed on " + m.getText() + " menu item");
			
			// if exit is selected from the file menu, exit the program
			if( m.getText().toLowerCase().equals("exit") )
			{
				System.exit(0);
			}
			// if read sort file is selected from the file menu, print in system
			if( m.getText().toLowerCase().equals("read sort file") )
			{
				System.out.println("read sort file");
				readSort(sortFileName);
				
				sortIntsButton.setEnabled(true);
				addToBstButton.setEnabled(true);
				addToTreeSetButton.setEnabled(true);
				addToPriorityQueueButton.setEnabled(true);
				addToHashSetButton.setEnabled(true);
				addToArrayListButton.setEnabled(true);
				addToSortedArrayListButton.setEnabled(true);
				addToArrayButton.setEnabled(true);

			}
			// if read search file is selected from the file menu, print in system
			if( m.getText().toLowerCase().equals("read search file") )
			{
				System.out.println("read search file");
				readSearch(searchFileName);
				enableSearchButton = true;
				if(readInts)
				{
					searchIntsButton.setEnabled(true);
				}
				if(readBst)
				{
					searchBstButton.setEnabled(true);
				}
				if(readTreeSet)
				{
					searchTreeSetButton.setEnabled(true);
				}
				if(readPriorityQueue)
				{
					searchPriorityQueueButton.setEnabled(true);
				}
				if(readHashSet)
				{
					searchHashSetButton.setEnabled(true);
				}
				if(readArrayList)
				{
					searchArrayListButton.setEnabled(true);
				}
				if(readSortedArrayList)
				{
					searchSortedArrayListButton.setEnabled(true);
				}
				if(readArray)
				{
					searchArrayButton.setEnabled(true);
				}
				
			}
		}
		private static void readSort(String file)
		{
			try 
			{
				java.util.ArrayList<Integer> inputData = new java.util.ArrayList<>(); 
				java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(file)));
				String inn;
				while( (inn = input.readLine()) != null )
				{
					inputData.add(Integer.valueOf(inn.trim()));
				}
				input.close();
				sortValues = new int[inputData.size()];
				for(int i = 0; i < inputData.size(); i++)
				{
					sortValues[i] = inputData.get(i);
				}
				
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
				System.exit(0);
			}
		}
		private static void readSearch(String file)
		{
			try 
			{
				java.util.ArrayList<Integer> inputData = new java.util.ArrayList<>(); 
				java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(file)));
				String inn;
				while( (inn = input.readLine()) != null )
				{
					inputData.add(Integer.valueOf(inn.trim()));
				}
				input.close();
				searchValues = new int[inputData.size()];
				for(int i = 0; i < inputData.size(); i++)
				{
					searchValues[i] = inputData.get(i);
				}
				
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
				System.exit(0);
			}
		}
		
	}
	
}
