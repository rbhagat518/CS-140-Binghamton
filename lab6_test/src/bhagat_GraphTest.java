import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class bhagat_GraphTest {
	bhagat_Graph g;

	// sample_directed_graph_1.txt
	String directedAndConnectedVertics = "{1,3,2,4,5,1,2}";
	String directedAndConnectedEdges = "{(1,4),(2,1),(2,3),(3,5),(4,5),(5,2)}";

	// sample_directed_graph_2.txt
	String directedAndNotConnectedVertics = "{1,3,2,4,5,1,2}";
	String directedAndNotConnectedEdges = "{(1,4),(2,1),(2,3),(3,5),(4,5),(0,1),(0,7),(1,7)}";

	// sample_undirected_graph_1.txt
	String undirectedAndConnectedVertics = "{0,1,3,2,4,5,6,7,8,9,0,2}";
	String undirectedAndConnectedEdges = "{(0,5),(1,7),(2,4),(3,6),(4,9),(5,8),(6,9),(7,9),(8,9),(5,0)}";

	// sample_undirected_graph_2.txt
	String undirectedAndNotConnectedVertics = "{0,1,3,2,4,5,6,7,8,9,0,2}";
	String undirectedAndNotConnectedEdges = "{(0,5),(1,7),(2,4),(4,9),(5,8),(6,9),(7,9),(8,9),(5,0)}";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		g = new bhagat_Graph();
	}

	// add 100 vertices twice and verify that the number of vertices
	// is correct each time one is added
	@Test
	void testAddVertex() {
		int[] numberOfVertices = new int[101];
		int[] expectedNumberOfVertices = new int[numberOfVertices.length];
		numberOfVertices[0] = g.getNumberOfVertices();
		expectedNumberOfVertices[0] = 0;
		for (int i = 0; i < numberOfVertices.length - 1; i++) {
			expectedNumberOfVertices[i + 1] = i + 1;
			try {
				g.addVertex(i);
				numberOfVertices[i + 1] = g.getNumberOfVertices();
				g.addVertex(i);
			} catch (Exception e) {
			}
		}

		assertArrayEquals(numberOfVertices, expectedNumberOfVertices);
	}

	// add 100 vertices twice and verify there are the correct number of vertices
	// each time one is added
	@Test
	void testGetNumberOfVertices() {
		int[] getNumberOfVertices = new int[101];
		int[] expectedResultGetNumberOfVertices = new int[getNumberOfVertices.length];
		getNumberOfVertices[0] = g.getNumberOfVertices();
		System.out.println("g.getNumberOfVertices() " + g.getNumberOfVertices());
		expectedResultGetNumberOfVertices[0] = 0;
		for (int i = 1; i < getNumberOfVertices.length; i++) {
			expectedResultGetNumberOfVertices[i] = i;
			try {
				g.addVertex(i - 1);
				getNumberOfVertices[i] = g.getNumberOfVertices();
			} catch (Exception e) {
			}
		}
		assertArrayEquals(getNumberOfVertices, expectedResultGetNumberOfVertices);
	}

	@Test
	void testAddEdge() {
		int[] numberOfEdges = new int[101];
		int[] expectedNumberOfEdges = new int[numberOfEdges.length];
		numberOfEdges[0] = g.getNumberOfVertices();
		expectedNumberOfEdges[0] = 0;
		for (int i = 0; i < numberOfEdges.length - 1; i++) {
			expectedNumberOfEdges[i + 1] = i + 1;
			try {
				if (i == 0) {
					g.addVertex(i);
				}
				g.addVertex(i + 1);
				g.addEdge(i, i + 1);
				numberOfEdges[i + 1] = g.getNumberOfEdges();
				g.addEdge(i, i + 1);

			} catch (Exception e) {
			}
		}
		assertArrayEquals(numberOfEdges, expectedNumberOfEdges);
	}

	// test a 0 paramater for being an undirected graph
	@Test
	void testIsDirectedForUndirectedGraph() {
		assertFalse(g.isDirected());
	}

	// test a 1 paramater for being a directed graph
	@Test
	void testIsDirectedForDirectedGraph() {
		g = new bhagat_Graph(true);
		assertTrue(g.isDirected());
	}

	// test a 1 paramater for being an undirected graph
	@Test
	void testIsDirectedForUndirectedGraph2() {
		g = new bhagat_Graph(false);
		assertFalse(g.isDirected());
	}

	// try to add vertex 0 twice, verify there is an exception on the second one
	@Test
	void testGraphExceptionForDuplicateVertex() {
		try {
			g.addVertex(0);
		} catch (GraphException e) {
		}
		assertThrows(GraphException.class, () -> g.addVertex(0));
	}

	// try to add edge(0,1) twice, verify there is an exception on the second one
	@Test
	void testGraphExceptionForDuplicateEdge() {
		try {
			g.addVertex(0);
			g.addVertex(1);
			g.addEdge(0, 1);

		} catch (GraphException e) {

		}
		assertThrows(GraphException.class, () -> g.addEdge(0, 1));
	}

	// try to add edge (1,0) after adding edge (0,1), verify there is an exception
	// when there is a repeat
	@Test
	void testGraphExceptionForDuplicateEdge2() {
		try {
			g.addVertex(0);
			g.addVertex(1);
			g.addEdge(0, 1);

		} catch (GraphException e) {

		}
		assertThrows(GraphException.class, () -> g.addEdge(1, 0));
	}

	// try to add edge (0,1) wihtout adding vertices 0 & 1,
	// verify there is an exception
	@Test
	void testGraphExceptionForInvalidEdge() {
		assertThrows(GraphException.class, () -> g.addEdge(0, 1));
	}

	// in a directed graph, verify that we can add (0,1) and (1,0)
	@Test
	void testGraphExceptionForDuplicateEdge3b() {
		g = new bhagat_Graph(true);
		try {
			g.addVertex(0);
			g.addVertex(1);
			g.addEdge(0, 1);
			assertDoesNotThrow(() -> g.addEdge(1, 0));

		} catch (GraphException e) {

		}

	}

	// add vertices 0 - 9, and edges (i,i+1), i = 0 ... 8
	// verify toString() comes out correct
	@Test
	void testToString() {
		String expectedToString = "G = (V, E) \n";
		expectedToString = expectedToString + "V = {0,1,2,3,4,5,6,7,8,9}\n";
		expectedToString = expectedToString + "E = {(0,1),(1,2),(2,3),(3,4),(4,5),(5,6),(6,7),(7,8),(8,9)}\n";

		for (int i = 0; i < 10; i++) {
			try {
				g.addVertex(i);
			} catch (Exception e) {
			}
		}

		for (int i = 0; i < 9; i++) {
			try {
				g.addEdge(i, i + 1);
			} catch (Exception e) {
			}
		}
		assertEquals(expectedToString, g.toString());

	}

	// add vertices 0 - 100, then add edges (i,i+1) i = 0, ..., 99,
	// verify the number of edges is correct after each one is added
	@Test
	void testGetNumberOfEdges() {
		int[] getNumberOfEdges = new int[101];
		int[] expectedResultGetNumberOfEdges = new int[getNumberOfEdges.length];
		getNumberOfEdges[0] = g.getNumberOfEdges();
		expectedResultGetNumberOfEdges[0] = 0;
		for (int i = 0; i < getNumberOfEdges.length; i++) {
			try {
				g.addVertex(i);
			} catch (GraphException e) {
			}
		}

		for (int i = 1; i < getNumberOfEdges.length; i++) {
			expectedResultGetNumberOfEdges[i] = i;
			try {
				g.addEdge(i - 1, i);
				getNumberOfEdges[i] = g.getNumberOfEdges();

			} catch (GraphException e) {
			}
		}

		assertArrayEquals(getNumberOfEdges, expectedResultGetNumberOfEdges);
	}

	// test isConnected() recognizes sample_undirected_graph_1.txt is connected
	@Test
	void testIsConnectedUndirectedAndConnected() {
		g = new bhagat_Graph(false);
		java.util.StringTokenizer st = new java.util.StringTokenizer(undirectedAndConnectedVertics, "{},");
		while (st.hasMoreTokens()) {
			int newVertex = Integer.parseInt(st.nextToken());

			try {
				g.addVertex(newVertex);
			} catch (GraphException e) {
			}
		}

		st = new java.util.StringTokenizer(undirectedAndConnectedEdges, "{}");
		String inn = st.nextToken();
		st = new java.util.StringTokenizer(inn, "(),");
		while (st.hasMoreTokens()) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			try {
				g.addEdge(from, to);
			} catch (GraphException e) {
			}
		}

		assertEquals(true, g.isConnected());
	}

	// test isConnected() recognizes sample_undirected_graph_2.txt is not connected
	@Test
	void testIsConnectedUndirectedAndNotConnected() {
		g = new bhagat_Graph(false);
		java.util.StringTokenizer st = new java.util.StringTokenizer(undirectedAndNotConnectedVertics, "{},");
		while (st.hasMoreTokens()) {
			int newVertex = Integer.parseInt(st.nextToken());

			try {
				g.addVertex(newVertex);
			} catch (GraphException e) {
			}
		}

		st = new java.util.StringTokenizer(undirectedAndNotConnectedEdges, "{}");
		String inn = st.nextToken();
		st = new java.util.StringTokenizer(inn, "(),");
		while (st.hasMoreTokens()) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			try {
				g.addEdge(from, to);
			} catch (GraphException e) {
			}
		}

		assertEquals(false, g.isConnected());
	}

	// test isConnected() recognizes sample_directed_graph_1.txt is connected
	@Test
	void testIsConnectedDirectedAndConnected() {
		g = new bhagat_Graph(true);
		java.util.StringTokenizer st = new java.util.StringTokenizer(directedAndConnectedVertics, "{},");
		while (st.hasMoreTokens()) {
			int newVertex = Integer.parseInt(st.nextToken());

			try {
				g.addVertex(newVertex);
			} catch (GraphException e) {
			}
		}

		st = new java.util.StringTokenizer(directedAndConnectedEdges, "{}");
		String inn = st.nextToken();
		st = new java.util.StringTokenizer(inn, "(),");
		while (st.hasMoreTokens()) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			try {
				g.addEdge(from, to);
			} catch (GraphException e) {
			}
		}

		assertEquals(true, g.isConnected());
	}

	// test isConnected() recognizes sample_directed_graph_2.txt is not connected
	@Test
	void testIsConnectedDirectedAndNotConnected() {
		g = new bhagat_Graph(true);
		java.util.StringTokenizer st = new java.util.StringTokenizer(directedAndNotConnectedVertics, "{},");
		while (st.hasMoreTokens()) {
			int newVertex = Integer.parseInt(st.nextToken());

			try {
				g.addVertex(newVertex);
			} catch (GraphException e) {
			}
		}

		st = new java.util.StringTokenizer(directedAndNotConnectedEdges, "{}");
		String inn = st.nextToken();
		st = new java.util.StringTokenizer(inn, "(),");
		while (st.hasMoreTokens()) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			try {
				g.addEdge(from, to);
			} catch (GraphException e) {
			}
		}

		assertEquals(false, g.isConnected());
	}

}
