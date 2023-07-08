//Name: Diep Nguyen

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface 
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();
			try
			{
				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
	
				else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
				{
					amazon.printAllProducts(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
				{
					amazon.printAllBooks(); 
				}
				else if (action.equalsIgnoreCase("SHOES"))	// List all shoes for sale
				{
					amazon.printAllShoes();
				}
				else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
				{
					amazon.printCustomers();	
				}
				else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
				{
					amazon.printAllOrders();	
				}
				else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
				{
					amazon.printAllShippedOrders();	
				}
				else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
				{
					String name = "";
					String address = "";
					
					System.out.print("Name: ");
					if (scanner.hasNextLine())
					{
						name = scanner.nextLine();
					}
					
					System.out.print("\nAddress: ");
					if (scanner.hasNextLine())
					{
						address = scanner.nextLine();
					}
					
					amazon.createCustomer(name, address);
					
				}
				else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
				{
						String orderNumber = "";
	        
						System.out.print("Order Number: ");
						// Get order number from scanner
						if (scanner.hasNextLine())
						{
							orderNumber = scanner.nextLine();
						}
						
						// Ship order to customer (see ECommerceSystem for the correct method to use
						amazon.shipOrder(orderNumber);
						// if order doesn't exist print errMsg
						
				}
				else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
				{
					String customerId = "";
	
					System.out.print("Customer Id: ");
					// Get customer Id from scanner
					if (scanner.hasNextLine())
					{
						customerId = scanner.nextLine();
					}
					// Print all current orders and all shipped orders for this customer
					// if customer doesn't exist print errMsg
					amazon.printOrderHistory(customerId);
					
				}
				else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
				{
					String productId = "";
					String customerId = "";
	
					System.out.print("Product Id: ");
				  // Get product Id from scanner
					if (scanner.hasNextLine())
					{
						productId = scanner.nextLine();
	
					}
					System.out.print("\nCustomer Id: ");
				  // Get customer Id from scanner
					if (scanner.hasNextLine())
					{
						customerId = scanner.nextLine();
					}
					// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
					System.out.println("Order #"+ amazon.orderProduct(productId, customerId, ""));
					// Print Order Number string returned from method in ECommerceSystem
					
				}
				else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
				{
					String productId = "";
					String customerId = "";
					String options = "";
	
					
					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine())
					{
						productId = scanner.nextLine();
					}
					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine())
					{
						customerId = scanner.nextLine();
					}
					System.out.print("\nFormat [Paperback Hardcover EBook]: ");
					// get book format and store in options string
					if (scanner.hasNextLine())
					{
						options = scanner.nextLine();
					}
					
					// Order product. Check for error message set in ECommerceSystem
					
					System.out.println("Order #"+amazon.orderProduct(productId, customerId, options));
				}
				else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
				{
					String productId = "";
					String customerId = "";
					String options = "";
					
					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine())
					{
						productId = scanner.nextLine();
					}
					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine())
					{
						customerId = scanner.nextLine();
					}
					System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
					// get shoe size and store in options	
					if (scanner.hasNextLine())
					{
						options = scanner.nextLine();
					}
					System.out.print("\nColor: \"Black\" \"Brown\": ");
					// get shoe color and append to options
					if (scanner.hasNextLine())
					{
						options += " " + scanner.nextLine();
					}
					//order shoes
					System.out.println("Order #"+ amazon.orderProduct(productId, customerId, options));

					
				}
				
				
				else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
				{
					String orderNumber = "";
	
					System.out.print("Order Number: ");
					// get order number from scanner
					if (scanner.hasNextLine())
					{
						orderNumber = scanner.nextLine();
					}
					amazon.cancelOrder(orderNumber);
					// cancel order. Check for error
					
				}
				else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort products by price
				{
					amazon.sortByPrice();
				}
				else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort products by name (alphabetic)
				{
					amazon.sortByName();
				}
				else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
				{
					amazon.sortCustomersByName();
				}
				else if (action.equalsIgnoreCase("BOOKSBYAUTHOR"))
				{
					String author = "";
					System.out.print("Author: ");
					if (scanner.hasNextLine())
					{
						author = scanner.nextLine();
					}
	
					amazon.booksByAuthor(author);
					
				}
				else if (action.equalsIgnoreCase("ADDTOCART"))
				{
					String productId = "";
					String customerId = "";
					String options = "";
	
					System.out.print("Product Id: ");
				  // Get product Id from scanner
					if (scanner.hasNextLine())
					{
						productId = scanner.nextLine();
	
					}
					System.out.print("\nCustomer Id: ");
				  // Get customer Id from scanner
					if (scanner.hasNextLine())
					{
						customerId = scanner.nextLine();
					}
					System.out.print("\nProduct Options: ");
					if (scanner.hasNextLine())
					{
						options = scanner.nextLine();
					}
					amazon.addToCart(productId, customerId, options);
					
	
				
				}
				else if (action.equalsIgnoreCase("REMCARTITEM"))
				{
					String productId = "";
					String customerId = "";
	
					System.out.print("Product Id: ");
				  // Get product Id from scanner
					if (scanner.hasNextLine())
					{
						productId = scanner.nextLine();
	
					}
					System.out.print("\nCustomer Id: ");
				  // Get customer Id from scanner
					if (scanner.hasNextLine())
					{
						customerId = scanner.nextLine();
					}
					amazon.remCartItem(productId, customerId);
					
				}
				else if (action.equalsIgnoreCase("PRINTCART"))
				{
					String customerId = "";
					System.out.print("Customer Id: ");
					if (scanner.hasNextLine())
					{
						customerId = scanner.nextLine();
					}
					amazon.printCart(customerId);
				}
				else if (action.equalsIgnoreCase("ORDERITEMS"))
				{
					String customerId = "";
					System.out.print("Customer Id: ");

					if (scanner.hasNextLine())
					{
						customerId = scanner.nextLine();
					}
					amazon.orderItems(customerId);
				}
				else if (action.equalsIgnoreCase("STATS"))
				{
					amazon.getStat();
				}
				else if (action.equalsIgnoreCase("RATE"))
				{
					String orderNumber = "";
					int rate = 0;
					System.out.print("Order Number: ");
					if (scanner.hasNextLine())
					{
						orderNumber = scanner.nextLine();
					}
					System.out.print("\nRating (1-5): ");
					if (scanner.hasNext())
					{
						rate = scanner.nextInt();
					}
					amazon.rateProduct(orderNumber, rate);
				}
				else if (action.equalsIgnoreCase("SHOWRATE"))
				{
					String category = "";
					int min = 0;
					System.out.print("Category: ");
					if (scanner.hasNextLine())
					{
						category = scanner.nextLine();
					}
					System.out.print("\nMin Rating of (1-5): ");
					if (scanner.hasNextLine())
					{
						min = scanner.nextInt();
					}
					amazon.printRate(category, min);
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			System.out.print("\n>");
		}
	}
}
