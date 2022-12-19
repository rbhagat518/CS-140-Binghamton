interface CarFunctions
{
	// return the fuel economy in miles per gallon of the car
	default public int getFuelEconomyInMilesPerGallon()
	{
		return 0;
	}
	
	// return the fuel capacity in gallons of the car
	default public int getFuelCapacityInGallons()
	{
		return 0;
	}
	
	// return the current gallons of fuel of the car
	default public double getCurrentFuelInGallons()
	{
		return 0.0;
	}
	
	// return the id of the car
	default public String getId()
	{
		return "";
	}
	
	// return the string represention of the car 
	public String toString();
	
	// set the current gallons of fuel of the car
	default public void setCurrentFuelInGallons(double v)
	{
	}
	
	// get the total range of the car in miles 
	default public double getTotalRangeInMiles()
	{
		return getFuelCapacityInGallons()*getFuelEconomyInMilesPerGallon();
	}
	
	// get the remaining range of the car in miles
	default public double getRemainingRangeInMiles()
	{
		return getCurrentFuelInGallons()*getFuelEconomyInMilesPerGallon();
	}
}
