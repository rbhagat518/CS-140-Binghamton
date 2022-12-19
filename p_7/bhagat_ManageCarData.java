import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.StringTokenizer;

class bhagat_ManageCarData implements ManageCarDataFunctions
{
    private final ArrayList<CarFunctions> carList;
    private final PriorityQueue<CarFunctions> carListByTotalRange;
    private final PriorityQueue<CarFunctions> carListByRemainingRange;
    bhagat_ManageCarData()
    {
        carList = new ArrayList<CarFunctions>();
        carListByTotalRange = new PriorityQueue<>(new TotalRangeComparator());
        carListByRemainingRange = new PriorityQueue<>(new RemainingRangeComparator());
    }
    public ArrayList<CarFunctions> getCarList()
    {
        ArrayList<CarFunctions> tempCarList = new ArrayList<>();
        for( int i = 0; i < carList.size(); i++ )
        {
            tempCarList.add(carList.get(i));
        }
        return tempCarList;
    }
    public void readData(String filename)
    {
        try
        {
            java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(filename)));
            String inn;
            while( (inn = input.readLine()) != null)
            {
                java.util.StringTokenizer st = new java.util.StringTokenizer(inn, "\t");
                int i = 1;
                String id_temp = "";
                int fuelEconomy_temp = 0;
                int fuelCapacity_temp = 0;
                double remainingFuel_temp = 0.0;
                while(st.hasMoreTokens())
                {
                    // One string, two ints, one double
                    String s = st.nextToken();
                    if (i == 1)
                    {
                        id_temp = s;
                    }
                    if(i == 2)
                    {
                        fuelEconomy_temp = Integer.parseInt(s);
                    }
                    if(i == 3)
                    {
                        fuelCapacity_temp = Integer.parseInt(s);

                    }
                    if (i == 4)
                    {
                        remainingFuel_temp = Double.parseDouble(s);
                    }
                    i++;
                }
                CarFunctions c = new bhagat_Car(id_temp, fuelEconomy_temp, fuelCapacity_temp, remainingFuel_temp);
                carList.add(c);
                carListByRemainingRange.add(c);
                carListByTotalRange.add(c);
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
            System.exit(0);
        }
    }
    public java.util.PriorityQueue<CarFunctions> getCarListByTotalRange() 
    {   
        PriorityQueue<CarFunctions> copyOfCarListByTotalRange = new PriorityQueue<>(new TotalRangeComparator());
        for (CarFunctions x : carList)
        {
            copyOfCarListByTotalRange.add(x);
        }
        return copyOfCarListByTotalRange;
    }
    public java.util.ArrayList<CarFunctions> getCarListByTotalRangeUsingIterator() 
    {
        ArrayList<CarFunctions> tempList = new ArrayList<CarFunctions>();
        Iterator iterator = carListByTotalRange.iterator();
        while(iterator.hasNext())
        {
            CarFunctions temp = ((CarFunctions) iterator.next());
            tempList.add(temp);
        }
        return tempList;
    }
    public java.util.PriorityQueue<CarFunctions> getCarListByRemainingRange() 
    {
        PriorityQueue<CarFunctions> copyOfCarListByRemainingRange = new PriorityQueue<>(new RemainingRangeComparator());
        for (CarFunctions x : carList)
        {
            copyOfCarListByRemainingRange.add(x);
        }
        return copyOfCarListByRemainingRange;
    }
    public java.util.ArrayList<CarFunctions> getCarListByRemainingRangeUsingIterator() 
    {
        ArrayList<CarFunctions> tempList = new ArrayList<CarFunctions>();
        Iterator iterator = carListByRemainingRange.iterator();
        while(iterator.hasNext())
        {
            tempList.add((CarFunctions) iterator.next());
        }
        return tempList;
    }
    public java.util.ArrayList<String> getCarListByRemainingRangeViaPoll(double minRemainingRange, double maxRemainingRange) 
    {
        ArrayList<String> tempList = new ArrayList<String>();
        PriorityQueue<CarFunctions> copyOfCarListByRemainingRange = getCarListByRemainingRange();
        while(copyOfCarListByRemainingRange.size() > 0)
        {
            CarFunctions temp = copyOfCarListByRemainingRange.poll();
            if((minRemainingRange <= temp.getRemainingRangeInMiles()) && (temp.getRemainingRangeInMiles() <= maxRemainingRange))
            {
                CarFunctions currentCar = new bhagat_Car(temp.getId(), temp.getFuelEconomyInMilesPerGallon(), temp.getFuelCapacityInGallons(), temp.getCurrentFuelInGallons());
                String currentCarString = temp.toString();
                currentCarString += "\t"+ (carList.indexOf(temp));
                int count = 0;
                for(CarFunctions x : carList)
                {
                    if(x.getFuelEconomyInMilesPerGallon() == temp.getFuelEconomyInMilesPerGallon())
                    {
                        currentCarString += "\t" + count;
                    }
                    count++;
                }
                tempList.add(currentCarString);
            }
        }
        return tempList;
    }
    public java.util.ArrayList<String> getCarListByTotalRangeViaPoll(double minTotalRange, double maxTotalRange) 
    {
        ArrayList<String> tempList = new ArrayList<String>();
        PriorityQueue<CarFunctions> copyOfCarListByTotalRange = getCarListByTotalRange();
        while(copyOfCarListByTotalRange.size() > 0)
        {
            CarFunctions temp = copyOfCarListByTotalRange.poll();
            if((minTotalRange <= temp.getTotalRangeInMiles()) && (temp.getTotalRangeInMiles() <= maxTotalRange))
            {
                String currentCarString = temp.toString();
                currentCarString += "\t"+ (carList.indexOf(temp));
                int count = 0;
                for(CarFunctions x : carList)
                {
                    if(x.getFuelEconomyInMilesPerGallon() == temp.getFuelEconomyInMilesPerGallon())
                    {
                        currentCarString += "\t" + count;
                    }
                    count++;
                }
                tempList.add(currentCarString);
            }
        }
        return tempList;
    }

    /* 
    public default java.util.PriorityQueue<CarFunctions> getCarListByRemainingRange() {};
    public default java.util.ArrayList<CarFunctions> getCarListByRemainingRangeUsingIterator() {};
    public default java.util.ArrayList<String> getCarListByRemainingRangeViaPoll(double minRemainingRange, double maxRemainingRange) {};
    public default java.util.PriorityQueue<CarFunctions> getCarListByTotalRange() {};
    public default java.util.ArrayList<CarFunctions> getCarListByTotalRangeUsingIterator() {};
    public default java.util.ArrayList<String> getCarListByTotalRangeViaPoll(double minTotalRange, double maxTotalRange) {};
    */



}
