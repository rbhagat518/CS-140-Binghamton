class Transaction
{
	final private String type;
	final private java.util.Date date;
	final private double amount;
	
	Transaction(String type, java.util.Date date, double amount)
	{
		this.type = type;
		this.date = date;
		this.amount = amount;
	}
	
	public java.util.Date date()
	{
		return date;
	}
	
	public double amount()
	{
		return amount;
	}
	
	public String type()
	{
		return type;
	}
	
	public String toString()
	{
		return type + " " + date.toString() + " " + amount;
	}
}
