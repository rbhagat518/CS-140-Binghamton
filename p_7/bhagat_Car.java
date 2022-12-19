import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;

class bhagat_Car implements CarFunctions
{
	private final String id;
	private final int fuelEconomyInMilesPerGallon;
	private final int fuelCapacityInGallons;
	private double currentFuelInGallons;

	bhagat_Car(String id, int fuelEconomyInMilesPerGallon, int fuelCapacityInGallons, double currentFuelInGallons)
	{
		this.id = id;
		this.fuelEconomyInMilesPerGallon = fuelEconomyInMilesPerGallon;
		this.fuelCapacityInGallons = fuelCapacityInGallons;
		this.currentFuelInGallons = currentFuelInGallons;
	}

	public double getCurrentFuelInGallons() 
	{
		return currentFuelInGallons;

	}
	public int getFuelCapacityInGallons() 
	{
		return fuelCapacityInGallons;
	}
	public int getFuelEconomyInMilesPerGallon() 
	{
		return fuelEconomyInMilesPerGallon;
	}
	public double getRemainingRangeInMiles() 
	{
		return getCurrentFuelInGallons()*getFuelEconomyInMilesPerGallon();

	}
	public String getId() 
	{
		return id;
	}
	public double getTotalRangeInMiles() 
	{
		return getFuelCapacityInGallons()*getFuelEconomyInMilesPerGallon();
	}
	public void setCurrentFuelInGallons(double v) 
	{
		currentFuelInGallons = v;
	}
	public String toString()
	{
		return getId() + "\t" + getFuelEconomyInMilesPerGallon() + "\t" + getFuelCapacityInGallons() + "\t" + getCurrentFuelInGallons() + "\t" + getTotalRangeInMiles() + "\t" + getRemainingRangeInMiles();
	}
	
}
