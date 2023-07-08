//Name: Diep Nguyen
//ID: 501132181
public class CartItem
{
	private Product 	product;
	private String    productOptions;
	private Customer 	customer;
	
	public CartItem(Product product, Customer customer, String productOptions)
	{
		this.product = product;
		this.customer = customer;
		this.productOptions = productOptions;
	}
	public Product getProduct()
	{
		return product;
	}
	public void setProduct(Product product)
	{
		this.product = product;
	}
	public Customer getCustomer()
	{
		return customer;
	}
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	public String getOption()
	{
		return productOptions;
	}
	public void setOption(String productOptions)
	{
		this.productOptions = productOptions;
	}
	public String toString()
	{
		return "Product "+ product.getId() +" Customer "+customer.getId() +" Options: "+productOptions+" Added to Cart!";
	}
	public void print()
	{
		System.out.printf("\nCustomer Id: %3s Product Id: %3s Product Name: %12s Options: %8s", customer.getId(), product.getId(), product.getName(), 
				               productOptions);
	}
}
