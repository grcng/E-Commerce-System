import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;



/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem 
{
	private TreeMap<String, Product> products = new TreeMap<String, Product>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    private ArrayList<CartItem> cartitems = new ArrayList<CartItem>();
    
    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    
    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
    
    // Random number generator
    Random random = new Random();
    
    public ECommerceSystem()
    {
    	// NOTE: do not modify or add to these objects!! - the TAs will use for testing
    	// If you do the class Shoes bonus, you may add shoe products
    	
    	// Create some products. Notice how generateProductId() method is used
    	try
    	{
	    	File file = new File("products.txt");
	    	Scanner in = new Scanner(file);

	    	while (in.hasNext())
	    	{
	    		String s = in.nextLine();
	    		if (s.equals("BOOKS"))
	    		{
	    			String name = in.nextLine();
	    			double price = Double.parseDouble(in.nextLine());
	    			String[] st = in.nextLine().split(" ");
	    			int paperst = Integer.parseInt(st[0]);
	    			int hardst = Integer.parseInt(st[1]);
	    			String line = in.nextLine();
	    			String[] l = line.split(":");
	    			String title = l[0];
	    			String author = l[1];
	    			int year = Integer.parseInt(l[2]);
	    			String id = generateProductId();
	    			
	    			products.put(id,new Book(name,id,price,paperst,hardst,title,author,year));
	    		}
	    		else if (s.equals("FURNITURE")||s.equals("CLOTHING")||s.equals("GENERAL")||s.equals("COMPUTERS"))
	    		{
	    			String name = in.nextLine();
	    			double price = Double.parseDouble(in.nextLine());
	    			int stock = Integer.parseInt(in.next());
	    			String id = generateProductId();

	    			products.put(id,new Product(name,id,price,stock,Product.Category.valueOf(s)));
	    		}

	    	}
    	}
    	catch (FileNotFoundException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	// Create some customers. Notice how generateCustomerId() method is used
    	customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin",new Cart()));
    	customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin",new Cart()));
    	customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine",new Cart()));
    	customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach",new Cart()));
    }
    
    private String generateOrderNumber()
    {
    	return "" + orderNumber++;
    }

    private String generateCustomerId()
    {
    	return "" + customerId++;
    }
    
    private String generateProductId()
    {
    	return "" + productId++;
    }
    
//    public String getErrorMessage()
//    {
//    	return errMsg;
//    }
    
    public void printAllProducts()
    {
    	for (String p : products.keySet())
    	{
    		products.get(p).print();
    	}
    }
    
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {
    	for (String b : products.keySet())
    	{
    		if(products.get(b).getCategory()==Product.Category.BOOKS)
    		{
    			products.get(b).print();
    		}
    	}
    }
    public void printAllShoes()
    {
    	for (String s : products.keySet())
    	{
    		if(products.get(s).getCategory()==Product.Category.SHOES)
    		{
    			products.get(s).print();
    		}
    	}
    }
    // Print all current orders
    public void printAllOrders()
    {
    	for (ProductOrder o : orders)
    	{
    		o.print();
    	}
    }
    // Print all shipped orders
    public void printAllShippedOrders()
    {
    	for (ProductOrder s : shippedOrders)
    	{
    		s.print();
    	}
    }
    
    // Print all customers
    public void printCustomers()
    {
    	for (Customer c : customers)
    	{
    		c.print();
    	}
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public void printOrderHistory(String customerId) throws UnknownCustomerException
    {
      // Make sure customer exists - check using customerId
    	// If customer does not exist, set errMsg String and return false
    	// see video for an appropriate error message string
    	// ... code here
    	int i = customers.indexOf(new Customer(customerId, "", "",null));
    	if (i == -1)
    	{
    		throw new UnknownCustomerException(customerId);
    	}
    	// Print current orders of this customer 
    	System.out.println("Current Orders of Customer " + customerId);
    	// enter code here
    	for (ProductOrder o : orders)
    	{
    		if (o.getCustomer().getId().equals(customerId))
    		{
    			o.print();
    		}
    	}
    	// Print shipped orders of this customer 
    	System.out.println("\nShipped Orders of Customer " + customerId);
    	//enter code here
    	for (ProductOrder s : shippedOrders)
    	{
    		if (s.getCustomer().getId().equals(customerId))
    		{
    			s.print();
    		}
    	}
    }
    
    public String orderProduct(String productId, String customerId, String productOptions)throws UnknownCustomerException, UnknownProductException, InvalidOptionException
    {
    	// First check to see if customer object with customerId exists in array list customers
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Customer object
    	int i = customers.indexOf(new Customer(customerId, "", "",null));
    	if (i == -1)
    	{
    		throw new UnknownCustomerException();
    	}
        	Customer c = customers.get(i);
    	// Check to see if product object with productId exists in array list of products
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Product object 
        String idP = "";
        for (String id : products.keySet())
        {
        	if(id.equals(productId))
        	{
        		idP = productId;
        	}
        }
    	if (idP=="")
    	{
    		throw new UnknownProductException();
    	}
    		Product p = products.get(productId);    			
    	
    	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	// See class Product and class Book for the method vaidOptions()
    	// If options are not valid, set errMsg string and return null;
    	if (p.getCategory().equals(Product.Category.BOOKS))
    	{
    		if (!p.validOptions(productOptions))
        	{
        		throw new InvalidOptionException();
        	}
    	}
    	// Check if the options are valid for this product (e.g. Black 6 etc.)
    	else if (p.getCategory().equals(Product.Category.SHOES))
    	{
    		if (!p.validOptions(productOptions))
    		{
    			throw new InvalidOptionException();
    		}
    	}
    	// Check if the product has stock available (i.e. not 0)
    	// See class Product and class Book for the method getStockCount()
    	// If no stock available, set errMsg string and return null
    	if (p.getStockCount(productOptions)==0)
    	{
    		throw new OutOfStockException();
    	}
      // Create a ProductOrder, (make use of generateOrderNumber() method above)
    	// reduce stock count of product by 1 (see class Product and class Book)
    	// Add to orders list and return order number string
    	ProductOrder a = new ProductOrder(generateOrderNumber(), p, c, productOptions);
    	p.reduceStockCount(productOptions);
    	orders.add(a);
    	return a.getOrderNumber();
    }
    
    /*
     * Create a new Customer object and add it to the list of customers
     */
    
    public void createCustomer(String name, String address) throws InvalidAddressException, InvalidCustomerNameException
    {
    	// Check name parameter to make sure it is not null or ""
    	// If it is not a valid name, set errMsg (see video) and return false
    	if (name == "" || name == null)
    	{
    		throw new InvalidCustomerNameException();
    	}
    	// Repeat this check for address parameter
    	if (address == "" || address == null)
    	{
    		throw new InvalidAddressException();
    	}
    	// Create a Customer object and add to array list
    	Customer c = new Customer(generateCustomerId(), name, address,new Cart());
    	customers.add(c);
    }
    
    public void shipOrder(String orderNumber) throws InvalidOrderNumberException
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	int i = orders.indexOf(new ProductOrder(orderNumber, null, null, ""));
    	if (i == -1)
    	{
    		throw new InvalidOrderNumberException();
    	}
    	// and return false
    	// Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
    	ProductOrder order = orders.get(i);
    	orders.remove(order);
    	shippedOrders.add(order);
    	// return a reference to the order
    }
    
    /*
     * Cancel a specific order based on order number
     */
    public void cancelOrder(String orderNumber) throws InvalidOrderNumberException
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false
    	int i = orders.indexOf(new ProductOrder(orderNumber, null, null, ""));
    	if (i == -1)
    	{
    		throw new InvalidOrderNumberException();

    	}
    	orders.remove(i);

    }
    
    // Sort products by increasing price
    public void sortByPrice()
    {
    	ArrayList<Product> prods = new ArrayList<Product>(products.values());
    	Collections.sort(prods, new ProductPriceComparator());
    	for (Product p : prods)
    	{
    		p.print();
    	}
    }
    
    
    // Sort products alphabetically by product name
    public void sortByName()
    {
    	ArrayList<Product> prods = new ArrayList<Product>(products.values());
    	// I created a class ProductNameComparator() to sort
    	Collections.sort(prods, new ProductNameComparator());
    	for (Product p : prods)
    	{
    		p.print();
    	}
    }
    
        
    // Sort products alphabetically by product name
    public void sortCustomersByName()
    {
    	// I created a class CustomerNameComparator() to sort
    	Collections.sort(customers, new CustomerNameComparator());
    	for (Customer c : customers)
    	{
    		c.print();
    	}
    }

    public void booksByAuthor(String author)
    {
    	ArrayList<Product> prods = new ArrayList<Product>(products.values());

    	// create an empty arraylist to store all products that are books
    	ArrayList<Book> books = new ArrayList<Book>();

    	for (Product p : prods)
    	{
    		if (p.getCategory().equals(Product.Category.BOOKS))
    		{
    			books.add((Book)p);
    		}
    	}
    	// sort books by year
    	Collections.sort(books, new BookYearComparator());
    	// create an empty arraylist to store the books by the specified author
    	ArrayList<Book> by = new ArrayList<Book>();
    	for (Book b : books)
    	{
    		if (b.getAuthor().equalsIgnoreCase(author))
    		{
    			by.add(b);
    		}
    	}
    	// if list by is empty, the author doesn't exist
    	if (by.isEmpty())
    	{
    		System.out.println("Author: "+author+" Not Found");
    	}
    	// else, print all of the author's books
    	else
    	{
    		for(Book a : by)
    		{
    			a.print();
    		}
    	}
    }
    
    public void addToCart(String productId, String customerId, String productOptions) throws UnknownCustomerException,UnknownProductException,InvalidOptionException,OutOfStockException
    {
    	// First check to see if customer object with customerId exists in array list customers
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Customer object
    	int i = customers.indexOf(new Customer(customerId, "", "",null));
    	if (i == -1)
    	{
    		throw new UnknownCustomerException();
    	}
        	Customer c = customers.get(i);
    	// Check to see if product object with productId exists in array list of products
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Product object 
    	 
        	 String idP = "";
             for (String id : products.keySet())
             {
             	if(id.equals(productId))
             	{
             		idP = productId;
             	}
             }
         	if (idP=="")
         	{
         		throw new UnknownProductException();
         	}
        	
        		Product p = products.get(productId); 
    	
    	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	// See class Product and class Book for the method vaidOptions()
    	// If options are not valid, set errMsg string and return null;
    	if (p.getCategory().equals(Product.Category.BOOKS))
    	{
    		if (!p.validOptions(productOptions))
        	{
        		throw new InvalidOptionException();

        	}
    	}
    	// Check if the options are valid for this product (e.g. Black 6 etc.)
    	else if (p.getCategory().equals(Product.Category.SHOES))
    	{
    		if (!p.validOptions(productOptions))
    		{
        		throw new InvalidOptionException();

    		}
    	}
    	// Check if the product has stock available (i.e. not 0)
    	// See class Product and class Book for the method getStockCount()
    	// If no stock available, set errMsg string and return null
    	if (p.getStockCount(productOptions)==0)
    	{
    		throw new OutOfStockException();

    	}
    	CartItem item = new CartItem(p,c,productOptions);
    	cartitems.add(item);
    	c.getCart().addToCart(item);
    }
   
    public void remCartItem(String productId, String customerId) throws UnknownCustomerException, UnknownProductException
    {
    	// First check to see if customer object with customerId exists in array list customers
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Customer object
    	int i = customers.indexOf(new Customer(customerId, "", "",null));
    	if (i == -1)
    	{
    		throw new UnknownCustomerException();

    	}
        	Customer c = customers.get(i);
    	// Check to see if product object with productId exists in array list of products
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Product object 
        	 String idP = "";
             for (String id : products.keySet())
             {
             	if(id.equals(productId))
             	{
             		idP = productId;
             	}
             }
         	if (idP=="")
         	{
         		throw new UnknownProductException();
         	}
        	
        		Product p = products.get(productId); 
    		
    	for(CartItem item : cartitems)
    	{
    		if (item.getProduct().equals(p)&&item.getCustomer().equals(c))
    		{
    			cartitems.remove(item); 
    			c.getCart().remCartItem(item);
    		}
    	}
 
    }
    
    public void printCart(String customerId) throws UnknownCustomerException
    {
    	int i = customers.indexOf(new Customer(customerId, "", "",null));
    	if (i == -1)
    	{
    		throw new UnknownCustomerException();
    	}
        	Customer c = customers.get(i);
        for (CartItem item : cartitems)
        {
        	if(item.getCustomer().equals(c))
        	{
        		item.print();
        	}
        }
    }
    
    public void orderItems(String customerId) throws UnknownCustomerException
    {
    	int i = customers.indexOf(new Customer(customerId, "", "",null));
    	if (i == -1)
    	{
    		throw new UnknownCustomerException();
    	}
        	Customer c = customers.get(i);
        for (CartItem item : cartitems)
        {
        	if (item.getCustomer().equals(c))
        	{
        		if (item.getProduct().getCategory().equals(Product.Category.BOOKS))
        		{
        			
        		}
        		ProductOrder o = new ProductOrder(generateOrderNumber(),item.getProduct(),item.getCustomer(),item.getOption());
        		orders.add(o);
        		item.getProduct().reduceStockCount(item.getOption());
        	}
        }
    }
    public void getStat() 
    {
    	HashMap<Product, Integer> stat = new HashMap<Product,Integer>();
    	//sort stat then add each pair of the sorted map to LinkedHashMap sorted
    	LinkedHashMap<Product, Integer> sorted = new LinkedHashMap<Product, Integer>();
    	for(ProductOrder o : orders)
    	{
    		
    		if (!stat.containsKey(o.getProduct()))
    		{
    			stat.put(o.getProduct(),1);
    		}
    		else
    		{
    			int count = stat.get(o.getProduct())+1;
    			
    			stat.put(o.getProduct(), count);
    		}
    	}
    	for(ProductOrder o : shippedOrders)
    	{
    		
    		if (!stat.containsKey(o.getProduct()))
    		{
    			stat.put(o.getProduct(),1);
    		}
    		else
    		{
    			int count = stat.get(o.getProduct())+1;
    			stat.put(o.getProduct(), count);
    		}
    	}
    	stat.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(x->sorted.put(x.getKey(), x.getValue()));

    		for (Product p : sorted.keySet())
    		{
    				System.out.printf("\nId: %-5s Name: %-20s Statistics: %d", p.getId(),p.getName(),sorted.get(p));
    				
    			
    		}
    		
    	
    	
    }
    public void rateProduct(String orderNumber, int r) throws InvalidOrderNumberException, InvalidRatingException
    {
    	//check shipped orders for orderNumber
    	int i = shippedOrders.indexOf(new ProductOrder(orderNumber, null, null, ""));
    	
    	if (i == -1)
    	{
    			throw new InvalidOrderNumberException();
    		
    	}
    	ProductOrder order = shippedOrders.get(i);
    	
    	if (r<1||r>5)
    	{
    		throw new InvalidRatingException();
    	}
    	order.getProduct().addRate(r);
    }
    public void printRate(String category, int r) throws InvalidCategoryException, InvalidRatingException
    {
    	if (r<1||r>5)
    	{
    		throw new InvalidRatingException();
    	}
    	for (String id : products.keySet())
    	{
    		Product p = products.get(id);
    		if (p.getCategory().equals(Product.Category.valueOf(category.toUpperCase())))
    		{
    			if (p.getRate()>r||p.getRate()==r)
    			{
    				p.print();
    			}
    			
    		}
    	}
    }
}
class InvalidAddressException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidAddressException()
	{
		super("Invalid Address");
	}
	public InvalidAddressException(String message)
	{
		super(message);
	}
}
class InvalidCustomerNameException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidCustomerNameException()
	{
		super("Invalid Customer Name");
	}
	public InvalidCustomerNameException(String message)
	{
		super(message);
	}
}
class InvalidOptionException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidOptionException()
	{
		super("Invalid Product Option");
	}
	public InvalidOptionException(String message)
	{
		super(message);
	}
}
class InvalidOrderNumberException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidOrderNumberException()
	{
		super("Invalid Order ");
	}
	public InvalidOrderNumberException(String message)
	{
		super(message);
	}
}
class OutOfStockException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OutOfStockException()
	{
		super("Product Out Of Stock");
	}
	public OutOfStockException(String message)
	{
		super(message);
	}
}
class UnknownCustomerException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UnknownCustomerException()
	{
		super("Customer Does Not Exist");
	}
	public UnknownCustomerException(String message)
	{
		super(message);
	}
}
class UnknownProductException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UnknownProductException()
	{
		super("Product Does Not Exist");
	}
	public UnknownProductException(String message)
	{
		super(message);
	}
}
class InvalidRatingException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidRatingException()
	{
		super("Invalid Rating Stars");
	}
	public InvalidRatingException(String message)
	{
		super(message);
	}
}
class InvalidCategoryException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidCategoryException()
	{
		super("Invalid Category");
	}
	public InvalidCategoryException(String message)
	{
		super(message);
	}
}