class TotalRangeComparator implements java.util.Comparator<CarFunctions>
{
	public int compare(CarFunctions o1, CarFunctions o2)
	{
		if( o1.getTotalRangeInMiles() < o2.getTotalRangeInMiles() )
		{
			return -1;
		}
		
		if( o1.getTotalRangeInMiles() > o2.getTotalRangeInMiles() )
		{
			return 1;
		}
		
		return o1.getId().compareTo(o2.getId());
	}
}
