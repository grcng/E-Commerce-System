//Name: Diep Nguyen
//ID: 501132181


public class Book extends Product 
{
  private String author;
  private String title;
  private int year;
 
  
  // Stock related information NOTE: inherited stockCount variable is used for EBooks
  int paperbackStock;
  int hardcoverStock;

  
  public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title, String author, int year)
  {
  	 // Make use of the constructor in the super class Product. Initialize additional Book instance variables. 
  	 // Set category to BOOKS 
	  super(name, id, price, paperbackStock+hardcoverStock, Category.BOOKS); 
	  this.author = author;
	  this.title = title;
	  this.year = year;
	  this.hardcoverStock = hardcoverStock;
	  this.paperbackStock = paperbackStock;
  }
  // 2 new methods for booksByAuthor() in ECommerceSystem
  public String getAuthor()
  {
	  return author;
  }
  public int getYear()
  {
	  return year;
  }

  // Check if a valid format  
  public boolean validOptions(String productOptions)
  {
  	// check productOptions for "Paperback" or "Hardcover" or "EBook"
  	// if it is one of these, return true, else return false
	  if (productOptions.equalsIgnoreCase("Paperback")||productOptions.equalsIgnoreCase("Hardcover")||productOptions.equalsIgnoreCase("EBook"))
	  {
		  return true;
	  }
	  return false;
  }
  
  // Override getStockCount() in super class.
  public int getStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and return) the number of stock for "Paperback" etc
  	// Use the variables paperbackStock and hardcoverStock at the top. 
  	// For "EBook", use the inherited stockCount variable.
	  if (productOptions.equalsIgnoreCase("Paperback"))
	  {
		  return paperbackStock;
	  }
	  else if (productOptions.equalsIgnoreCase("Hardcover"))
	  {
		  return hardcoverStock;
	  }
	  else if (productOptions.equalsIgnoreCase("EBook"))
	  {
		  return super.getStockCount("EBook");
	  }
	  return 1;
	}
  
  public void setStockCount(int stockCount, String productOptions)
	{
    // Use the productOptions to check for (and set) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
	  if (productOptions.equalsIgnoreCase("Paperback"))
	  {
		  this.paperbackStock = stockCount;
	  }
	  else if (productOptions.equalsIgnoreCase("Hardcover"))
	  {
		  this.hardcoverStock = stockCount;
	  }
	  else if (productOptions.equalsIgnoreCase("EBook"))
	  {
		  super.setStockCount(stockCount, "EBook");
	  }
	}
  
  /*
   * When a book is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
	  if (productOptions.equalsIgnoreCase("Paperback"))
	  {
		  paperbackStock --;
	  }
	  else if (productOptions.equalsIgnoreCase("Hardcover"))
	  {
		  hardcoverStock --;
	  }
	  else if (productOptions.equalsIgnoreCase("EBook"))
	  {
		  super.reduceStockCount("EBook");
		  
	  }
	}
  /*
   * Print product information in super class and append Book specific information title and author
   */
  public void print()
  {
  	// Replace the line below.
  	// Make use of the super class print() method and append the title and author info. See the video
	  super.print();
	  System.out.printf("   Book Title: %-35s Author: %-20s Year Published: %d", title, author, year);
  }
}
