//Name: Diep Nguyen
//ID: 501132181

import java.util.Comparator;

public class BookYearComparator implements Comparator<Book>
{
	public int compare(Book b, Book c)
	{
		return Integer.compare(b.getYear(), c.getYear());
	}
}
