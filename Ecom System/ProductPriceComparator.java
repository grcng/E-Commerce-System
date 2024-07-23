import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product>
{
	public int compare(Product p, Product q)
	{
		return Double.compare(p.getPrice(), q.getPrice());
	}
}
