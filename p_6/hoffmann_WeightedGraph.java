import java.util.*;

public class hoffmann_WeightedGraph implements WeightedGraphFunctions
{
    private final ArrayList<Integer> vertices;
    private final ArrayList<EdgeWithWeight> edges;

    public hoffmann_WeightedGraph()
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
		PriorityQueue<VertexWithWeight> minPriorityQueueByWeight = new PriorityQueue<>(vertices.size(), new VertexWithWeightWeightComparator());
        VertexWithWeight[] verticeCost = new VertexWithWeight[vertices.size()];
        int[] parent = new int[vertices.size()];
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
        while (minPriorityQueueByWeight.size() > 0)
        {
            VertexWithWeight lowestCostElement = minPriorityQueueByWeight.poll();
            int v = lowestCostElement.getVertex();
            if(parent[getIndex(v)] == -1){break;}
            if(v == toVertex){break;}
            for(EdgeWithWeight e : edges)
            {
                if(e.getFromVertex() == v)
                {
                    int u = e.getToVertex();
                    if(verticeCost[getIndex(v)].getWeight() + e.getWeight() < verticeCost[getIndex(u)].getWeight())
                    {
                        minPriorityQueueByWeight.remove(verticeCost[getIndex(u)]);
                        verticeCost[getIndex(u)].setWeight(verticeCost[getIndex(v)].getWeight() + e.getWeight());
                        minPriorityQueueByWeight.add(verticeCost[getIndex(u)]);
                        parent[getIndex(u)] = v;
                    }
                }
            }
        }
        if(parent[getIndex(toVertex)] != -1)
        {
            if(typeOfInfo == WeightedGraphReturnType.HAS_PATH)
            {
                return Boolean.valueOf(true);
            }
            if(typeOfInfo == WeightedGraphReturnType.GET_MINIMUM_WEIGHT)
            {
                return verticeCost[getIndex(toVertex)].getWeight();
            }
            ArrayList<Integer> reversePath = new ArrayList<Integer>();
            ArrayList<Integer> forwardPath = new ArrayList<Integer>();
            int p = toVertex;
            reversePath.add(p);
            while(p != fromVertex)
            {
                p = parent[getIndex(p)];
                reversePath.add(p);
            }
            for(int i = reversePath.size() - 1; i >= 0; i--)
            {
                forwardPath.add(reversePath.get(i));
            }
            EdgeWithWeight[] ewwa = new EdgeWithWeight[forwardPath.size() - 1]; 
            double edgeWeight = 0.0;
            for(int i = 0; i < forwardPath.size() - 1; i++)
            {
                for(EdgeWithWeight e : edges)
                {
                    if(forwardPath.get(i) == e.getFromVertex() && forwardPath.get(i + 1) == e.getToVertex())
                    {
                        edgeWeight = e.getWeight();
                    }
                }
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