//Name: Diep Nguyen
//ID: 501132181

import java.util.Comparator;

public class CustomerNameComparator implements Comparator<Customer>
{
	public int compare(Customer c, Customer d)
	{
		return c.getName().toLowerCase().compareTo(d.getName().toLowerCase());
	}
}
