class drawBinarySearchTree
{
	private static int defaultWidth = 600;
	private static int defaultHeight = 600;
	
	public static void main(String[] args)
	{
		if( args.length < 1 )
		{
			System.out.println("format: drawBinarySearchTree \"input file\" \"<width>\" \"<height>\"");
			System.exit(0);
		}
		
		DrawingArea da;
		javax.swing.JFrame f;
		javax.swing.JPanel daPanel;
		javax.swing.JPanel mainPanel;
		javax.swing.JScrollPane scrollPane;
		
		int width = defaultWidth;
		int height = defaultHeight;
		
		if( args.length > 1 )
		{
			width = Integer.parseInt(args[1]);
		}
		
		if( args.length > 2 )
		{
			height = Integer.parseInt(args[2]);
		}

		
		f = new javax.swing.JFrame();
		da = new DrawingArea(args[0]);
		
		daPanel = new javax.swing.JPanel();
		daPanel.setLayout(new java.awt.BorderLayout());
		mainPanel = new javax.swing.JPanel();
		mainPanel.setLayout(new java.awt.BorderLayout());
		daPanel.add(da, java.awt.BorderLayout.CENTER);
		daPanel.validate();
		daPanel.setVisible(true);
		f.addKeyListener(da);
		
		scrollPane = new javax.swing.JScrollPane(daPanel);
		scrollPane.validate();
		scrollPane.setVisible(true);
		mainPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
		
		f.setPreferredSize(new java.awt.Dimension(width, height));
		f.setMinimumSize(new java.awt.Dimension(width, height));
		f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		
		f.setContentPane(mainPanel);
		f.validate();
		f.setVisible(true);
	}
}
