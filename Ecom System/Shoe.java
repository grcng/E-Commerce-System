public class Shoe extends Product
{
	//new variables to set stock for different productOptions
	int black6;
	int black7;
	int black8;
	int black9;
	int black10;
	
	int brown6;
	int brown7;
	int brown8;
	int brown9;
	int brown10;

	
	public Shoe(String name, String id, double price, int black6, int black7,int black8, int black9, int black10, int brown6, int brown7, int brown8, int brown9, int brown10, Category category)
	{
		super(name, id, price, black6+black7+black8+black9+black10+brown6+brown7+brown8+brown9+brown10, Category.SHOES); 
		
		this.black6 = black6;
		this.black7 = black7;
		this.black8 = black8;
		this.black9 = black9;
		this.black10 = black10;
		
		this.brown6 = brown6;
		this.brown7 = brown7;
		this.brown8 = brown8;
		this.brown9 = brown9;
		this.brown10 = brown10;
	}
	//check valid options
	public boolean validOptions(String productOptions)
	{
		if (productOptions.equalsIgnoreCase("6 Brown")||productOptions.equalsIgnoreCase("7 Brown")||productOptions.equalsIgnoreCase("8 Brown")||productOptions.equalsIgnoreCase("9 Brown")||productOptions.equalsIgnoreCase("10 Brown")||productOptions.equalsIgnoreCase("6 Black")||productOptions.equalsIgnoreCase("7 Black")||productOptions.equalsIgnoreCase("8 Black")||productOptions.equalsIgnoreCase("9 Black")||productOptions.equalsIgnoreCase("10 Black"))
		{
			return true;
		}
		return false;
	}
	
	//get stock for productOptions
	public int getStockCount(String productOptions)
	{
		switch (productOptions.toLowerCase())
		{
		case "6 brown":
			return brown6;
		case "7 brown":
			return brown7;
		case "8 brown":
			return brown8;
		case "9 brown":
			return brown9;
		case "10 brown":
			return brown10;
		case "6 black":
			return black6;
		case "7 black":
			return black7;
		case "8 black":
			return black8;
		case "9 black":
			return black9;
		case "10 black":
			return black10;
		default:
			return 1;
		}
	}
	//Use the productOptions to check for (and set) the number of stock
	public void setStockCount(int stockCount, String productOptions)
	{	
		switch (productOptions.toLowerCase())
		{
		case "6 brown":
			brown6 = stockCount;
			break;
		case "7 brown":
			brown7 = stockCount;
			break;
		case "8 brown":
			brown8 = stockCount;
			break;
		case "9 brown":
			brown9 = stockCount;
			break;
		case "10 brown":
			brown10 = stockCount;
			break;
		case "6 black":
			black6 = stockCount;
			break;
		case "7 black":
			black7 = stockCount;
			break;
		case "8 black":
			black8 = stockCount;
			break;
		case "9 black":
			black9 = stockCount;
			break;
		case "10 black":
			black10 = stockCount;
			break;
		default:
			break;
		}
	}
	//Use the productOptions to check for (and reduce) the number of stock
	public void reduceStockCount(String productOptions)
	{
		switch (productOptions.toLowerCase())
		{
		case "6 brown":
			brown6 --;
			break;
		case "7 brown":
			brown7 --;
			break;
		case "8 brown":
			brown8 --;
			break;
		case "9 brown":
			brown9 --;
			break;
		case "10 brown":
			brown10 --;
			break;
		case "6 black":
			black6 --;
			break;
		case "7 black":
			black7 --;
			break;
		case "8 black":
			black8 --;
			break;
		case "9 black":
			black9 --;
			break;
		case "10 black":
			black10 --;
			break;
		default:
			break;
		}
	}
	// make use of superclass Product and print()
	public void print()
	{
		super.print();
	}
}
