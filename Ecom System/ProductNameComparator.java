import java.util.Comparator;

public class ProductNameComparator implements Comparator<Product>
{
	public int compare(Product p, Product q)
	{
		return p.getName().compareTo(q.getName());
	}
}
