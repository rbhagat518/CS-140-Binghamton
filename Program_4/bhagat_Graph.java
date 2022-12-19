import java.util.ArrayList;

public class bhagat_Graph implements ConnectedGraphFunctions {
	private final ArrayList<Integer> vertices;
	private final ArrayList<Edge> edges;
	private final boolean isDirected;

	bhagat_Graph() {
		this(false);
	}

	bhagat_Graph(boolean isDirected) {
		this.isDirected = isDirected;
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
	}

	public int getNumberOfVertices() {
		return vertices.size();
	}

	public int getNumberOfEdges() {
		return edges.size();
	}

	public boolean isDirected() {
		return isDirected;
	}

	public void addVertex(int v) throws GraphException {
		if (!vertices.contains(v)) {
			vertices.add(v);
		} else {
			throw new GraphException();
		}

	}

	public void addEdge(int from, int to) throws GraphException {
		Edge temp = new Edge(from, to);
		Edge reversed = new Edge(to, from);
		if (isDirected == false) {
			if (!edges.contains(temp) && !edges.contains(reversed) && vertices.contains(from)
					&& vertices.contains(to)) {
				edges.add(temp);
			} else {
				throw new GraphException();
			}
		} else if (isDirected == true) {
			if (!edges.contains(temp) && vertices.contains(from) && vertices.contains(to)) {
				edges.add(temp);
			} else {
				throw new GraphException();
			}
		}
	}

	public String toString() {
		java.lang.StringBuilder st = new StringBuilder();
		st.append("G = (V, E) \n");
		st.append("V = {");
		for (int i = 0; i < vertices.size(); i++) {
			st.append((vertices.get(i)).toString());
			if (i != vertices.size() - 1) {
				st.append(",");
			}
		}
		st.append("}\n");
		st.append("E = {");
		for (int i = 0; i < edges.size(); i++) {
			Edge x = edges.get(i);
			st.append("(" + (x.fromVertex()).toString() + "," + x.toVertex().toString() + ")");
			if (i != edges.size() - 1) {
				st.append(",");
			}
		}
		st.append("}\n");
		String val = st.toString();
		return val;
	}

	private boolean isConnected(ArrayList<Edge> edge) {
		java.util.HashSet<Integer> connectedSubset = new java.util.HashSet<>();
		java.util.ArrayDeque<Integer> newlyAddedVertices = new java.util.ArrayDeque<>();
		connectedSubset.add(vertices.get(0));
		newlyAddedVertices.add(vertices.get(0));
		while (newlyAddedVertices.size() >= 1) {
			int currentVertex = newlyAddedVertices.pollFirst();
			if (isDirected()) {
				for (Edge x : edge) {
					if (currentVertex == x.fromVertex()) {
						if (!connectedSubset.contains(x.toVertex())) {
							connectedSubset.add(x.toVertex());
							newlyAddedVertices.add(x.toVertex());
						}
					}
				}
			} else {
				for (Edge x : edge) {
					if (currentVertex == x.fromVertex()) {
						if (!connectedSubset.contains(x.toVertex())) {
							connectedSubset.add(x.toVertex());
							newlyAddedVertices.add(x.toVertex());
						}
					}
					if (currentVertex == x.toVertex()) {
						if (!connectedSubset.contains(x.fromVertex())) {
							connectedSubset.add(x.fromVertex());
							newlyAddedVertices.add(x.fromVertex());
						}
					}
				}
			}
		}
		return (vertices.size() == connectedSubset.size());

	}

	public boolean isConnected() {
		if (isDirected == true) {
			ArrayList<Edge> reversedEdges = new ArrayList<>();
			for (Edge x : edges) {
				int from = x.fromVertex();
				int to = x.toVertex();
				Edge temp = new Edge(to, from);
				reversedEdges.add(temp);
			}
			return (isConnected(edges) && isConnected(reversedEdges));

		} else {
			return (isConnected(edges));
		}

	}
}
