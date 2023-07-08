//Name: Diep Nguyen
//ID: 501132181
import java.util.ArrayList;

public class Cart 
{
	
	private ArrayList<CartItem> items;
	
	public Cart()
	{
		items = new ArrayList<CartItem>();
	}
	public void addToCart(CartItem item)
	{
		items.add(item);
	}

	public void remCartItem(CartItem item)
	{
		items.remove(item);
	}
	public void emptyCart()
	{
		items.clear();
	}
	public ArrayList<CartItem> getCart()
	{
		return items;
	}
	public void printAll()
	{
		for (CartItem i : items)
		{
			i.print();
		}
	}
}
