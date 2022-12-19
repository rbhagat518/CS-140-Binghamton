import java.util.*;

public class bhagat_WeightedGraph implements WeightedGraphFunctions
{
    private final ArrayList<Integer> vertices;
    private final ArrayList<EdgeWithWeight> edges;

    public bhagat_WeightedGraph()
    {
        vertices = new ArrayList<Integer>();
        edges = new ArrayList<EdgeWithWeight>();
    }

    public int getIndex(Integer i)
    {
        return vertices.indexOf(i);
    }

    public boolean hasPath(int fromVertex, int toVertex)
    {
        return ((Boolean) getPath(fromVertex, toVertex, WeightedGraphReturnType.HAS_PATH)).booleanValue();
    }

	public double getMinimumWeight(int fromVertex, int toVertex)
    {
        return ((Double) getPath(fromVertex, toVertex, WeightedGraphReturnType.GET_MINIMUM_WEIGHT)).doubleValue();
    }

	public EdgeWithWeight[] getPath(int fromVertex, int toVertex)
    {   
        return ((EdgeWithWeight[]) getPath(fromVertex, toVertex, WeightedGraphReturnType.GET_PATH));
    }

	private Object getPath(int fromVertex, int toVertex, WeightedGraphReturnType typeOfInfo)
	{
        // 1. from vertex is the source vertex and to vertex is the destination vertex

        // 2. Create a PriorityQueue<VertexWithWeight>
        // 2.a.
		PriorityQueue<VertexWithWeight> minPriorityQueueByWeight = new PriorityQueue<>(vertices.size(), new VertexWithWeightWeightComparator());
        // 3. VertexWithWeight[] is used to keep copies of the objects in minPriorityQueueByWeight
        // 3. a
        VertexWithWeight[] verticeCost = new VertexWithWeight[vertices.size()];
        // 3. b When removing the VertexWithWeight associated with a vertex, 
        // I use the VertexWithWeight stored in verticeCost array associated with the vertex and the 
        // minPriorityQueueByWeight.remove() method
        // 3. b. verticeCost is an VertexWithWeight array that contains VertexWithWeights that are removed in minPriorityQueues

        // 4. Create an int[] to keep the parent(predecessor/vertex which points to something) of each vertex in a path from the source vertex
        int[] parent = new int[vertices.size()];

        // 5. Initialize the above data
        // 5. a Set weight of each VertexWithWeight to Double.POSITIVE_INFINITY, except
        // for the source vertex, set it's weight to 0 and put them in the verticeCost array
        // 5. b. Remember the vertex numbers and the vertex index in the vertices
        // ArrayList<Integer> are not guaranteed to be the same
        // 5. c. Set the parent of each vertex to -1 (unassigned), except for the source vertex, set it to itself
        // 5. e. Add all of the elements of verticeCost to minPriorityQueueByWeight

        // Initialize verticeCost, parent, and minPriorityQueueByWeight

        for(int i = 0; i < vertices.size(); i++)
        {
            parent[i] = -1;
            verticeCost[i] = new VertexWithWeight(vertices.get(i), Double.POSITIVE_INFINITY);
        }
        parent[getIndex(fromVertex)] = fromVertex;
        verticeCost[getIndex(fromVertex)] = new VertexWithWeight(vertices.get(getIndex(fromVertex)), 0.0);
        for(int i = 0; i < vertices.size(); i++)
        {
            minPriorityQueueByWeight.add(verticeCost[i]);
        }
        // 6. 
        while (minPriorityQueueByWeight.size() > 0)
        {
            // 6. a. use minPriorityQueueByWeight.poll() to get the lowest cost element out of minPriorityQueueByWeight
            VertexWithWeight lowestCostElement = minPriorityQueueByWeight.poll();
            // 6. b. Let v be the vertex of the element of minPriorityQueueByWeight returned by poll() ?
            int v = lowestCostElement.getVertex();
            // 6. c. if the parent[v] == -1, that means that all of the remaining vertices in
            // minPriorityQueueByWeight are not reachable from the source vertex, so we can
            // exit the while loop
            if(parent[getIndex(v)] == -1){break;}
            // 6. d. If v is the destination vertex, we can also exit the while loop
            if(v == toVertex){break;}
            // 6. e. loop over the edges of the graph
            for(EdgeWithWeight e : edges)
            {
                // 1. If the current edge's from vertex == v
                // e represents the current edge
                if(e.getFromVertex() == v)
                {
                    // 2. Let u be the to vertex of the current edge
                    int u = e.getToVertex();
                    // if the cost of v + edge weight of the current edge < current cost to u
                    if(verticeCost[getIndex(v)].getWeight() + e.getWeight() < verticeCost[getIndex(u)].getWeight())
                    {
                        // 2. 1. Remove the VertexWithWeight for u from minPriorityQueueByWeight
                        minPriorityQueueByWeight.remove(verticeCost[getIndex(u)]);
                        // 3. Update the weight/cost for u to cost of v + edge weight of the current edge
                        verticeCost[getIndex(u)].setWeight(verticeCost[getIndex(v)].getWeight() + e.getWeight());
                        // 4. Add the updated VertexWithWeight for u back into minPriorityQueueByWeight
                        minPriorityQueueByWeight.add(verticeCost[getIndex(u)]);
                        //  5. update the parent of u to be v
                        parent[getIndex(u)] = v;
                    }
                }
            }
        }
        // 7. Check to see if there is a path to the destination vertex

        // a. If the parent array value for the destination vertex is not -1
        // then there is a path from the source vertex to the destination vertex

        // if parent array destination vertex val is -1, there is not a path from source to destination 
        if(parent[getIndex(toVertex)] != -1)
        {
            if(typeOfInfo == WeightedGraphReturnType.HAS_PATH)
            {
                return Boolean.valueOf(true);
            }
            // 7. b The weight of a minimum cost path from the source vertex to the destination vertex
            // is the VertexWithWeight element in verticeCost at the index associated with the
            // destination vertex

            // the index asscociated with the destination vertex in verticeCost is the minimum cost path
            if(typeOfInfo == WeightedGraphReturnType.GET_MINIMUM_WEIGHT)
            {
                return verticeCost[getIndex(toVertex)].getWeight();
            }

            // 7. c. To construct a path from the source vertex to the destination vertex we need to use
            // the parent array to first get a reverse path from the destination to the source vertex,
            // and then create a path from the source vertex to the destination vertex

            ArrayList<Integer> reversePath = new ArrayList<Integer>();
            ArrayList<Integer> forwardPath = new ArrayList<Integer>();
            // 7. 1. Let p be the destination vertex
            int p = toVertex;
            // Add p to reversePath
            reversePath.add(p);
            // while ( p != source vertex )
            while(p != fromVertex)
            {
                // Let p be the parent of the current p

                // Get the index of p, and then set p to parent[indexOfP]
                p = parent[getIndex(p)];
                // Add p to reversePath
                reversePath.add(p);
            }
            // Now create a forward path from the source vertex to the destination vertex
            for(int i = reversePath.size() - 1; i >= 0; i--)
            {
                forwardPath.add(reversePath.get(i));
            }
            EdgeWithWeight[] ewwa = new EdgeWithWeight[forwardPath.size() - 1]; 
            double edgeWeight = 0.0;
            // At this point, we have the sequence of vertices that take us from the source
            // vertex to the destination vertex, we need a list of edges
            // 1. We want to create an EdgeWithWeight array of size forwardPath.size()-1

            // The i-th element of the EdgeWithWeight array will be the edge from the
            // forwardPath element at index i to the element at i+1
            for(int i = 0; i < forwardPath.size() - 1; i++)
            {
                for(EdgeWithWeight e : edges)
                {
                    // get the edge weight by searching the edges for a edge which matches the specified from and to vertices
                    if(forwardPath.get(i) == e.getFromVertex() && forwardPath.get(i + 1) == e.getToVertex())
                    {
                        edgeWeight = e.getWeight();
                    }
                }
                // The i-th element of the EdgeWithWeight array will be the edge from the
                // forwardPath element at index i to the element at i+1
                ewwa[i] = new EdgeWithWeight(forwardPath.get(i), forwardPath.get(i + 1), edgeWeight);
            }
            if(typeOfInfo == WeightedGraphReturnType.GET_PATH)
            {
                return ewwa;
            }
        }
        switch(typeOfInfo)
        {
            case HAS_PATH:
                return Boolean.valueOf(false);

            case GET_MINIMUM_WEIGHT:
                return Double.NaN;

            case GET_PATH:
                return new EdgeWithWeight[0];
            
            default:
                return null;
        }
	}

	public boolean addVertex(int v)
	{
		if(!vertices.contains(v))
		{
			vertices.add(v);
            return true;
		}
		return false;
	}

	public boolean addWeightedEdge(int from, int to, double weight)
    {
        if(vertices.contains(from) && vertices.contains(to))
        {
            for(EdgeWithWeight e : edges) 
            {
                if((e.getFromVertex() == from && e.getToVertex() == to))
                {
                    return false;
                }
            }
            edges.add(new EdgeWithWeight(from, to, weight));
            return true;
        }
       return false;
    }

	public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("G = (V,E)\nV = {");
        for(int i = 0; i < vertices.size(); i++)
        {
            sb.append(vertices.get(i));
            if(i < vertices.size() - 1)
            {
                sb.append(",");
            }
        }
        sb.append("}\nE = {");
        for(int i = 0; i < edges.size(); i++)
        {
            sb.append(edges.get(i).toString());
            if(i < edges.size() - 1)
            {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}