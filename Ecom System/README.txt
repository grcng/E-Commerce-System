ECommerceSystem
	I added publish years for book products.
	I also added 1 book and 3 shoes to the products list.
	Replaced ArrayList<Product> with TreeMap<String, Product> where key is the Product Id

Class Product
	I added an ArrayList for ratings
	When the customer rates the product it will be added to the ArrayList

Action BOOKSBYAUTHOR:
	I created a Comparator to sort the books by years.
	I also created 2 new methods getYear() and getAuthor() in class BOOK.

Action SHOES:
	Print all shoes

Action ADDTOCART
	Will add Product of any Category to Cart
	If the Product doesn't have a product option, leave the input blank
	
Action RATE
	This is for customers to rate shipped orders
	If the order hasn't been shipped, there will be an exception

Action SHOWRATE
	Input the Product Category (i.e. "books")
		If Category doesn't exist, there will be an exception
	Input the minimum Rating Score (i.e. 2)
		If the score is <1 or >5, there will be an exception
	Action will print every product in the Category with ratings of similar or bigger values
		The rating of the Product is the average of all ratings

Action PRINTBYNAME, PRINTBYPRICE
	Print products in required order only on command
	If run Action PRODS afterward, the order will be back to by Product Id
	

	
