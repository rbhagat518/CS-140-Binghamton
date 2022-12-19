class VertexWithWeight implements VertexWithWeightFunctions
{
    private final Integer vertex;
    private Double weight = Double.POSITIVE_INFINITY;
    public double getWeight() 
    {
        return weight;
    }
    public int getVertex() 
    {
        return vertex;
    }
    public void setWeight(double w) 
    {
        weight = w;
    }
    public boolean equals(Object o) 
    {
        if (o == null) 
        {
            return false;
        }
        if (o == this) 
        {
            return true;
        }     
        if (getClass() == o.getClass()) 
        {
            if (vertex == ((VertexWithWeight) o).getVertex()) 
            {
                return true;
            }
        }
        return false;
    }
    public String toString() 
    {
        return "(" + vertex + "," + weight + ")";
    }
    VertexWithWeight(int v, double w) 
    {
        this.vertex = v;
        this.weight = w;
    }
    
}
