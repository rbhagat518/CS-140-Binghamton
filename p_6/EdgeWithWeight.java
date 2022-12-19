public class EdgeWithWeight implements EdgeWithWeightFunctions
{
    private final Integer fromVertex;
    private final Integer toVertex;
    private final Double weight;
    EdgeWithWeight(int fromVertex, int toVertex, double w)
    {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.weight = w;
    }
    public int getFromVertex() 
    {
        return fromVertex;
    }
    public int getToVertex()
    {
        return toVertex;
    }
    public double getWeight() 
    {
        return weight;
    }
    public String toString()
    {
        return "(" + fromVertex + "," + toVertex + "," + weight +")";
    }

    
}
