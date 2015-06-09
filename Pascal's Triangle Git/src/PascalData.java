import java.awt.Point;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Vector;

public class PascalData {
	
	public static class BigIntegerSizeCountMessage
	{
		int add;
		private BigIntegerSizeCountMessage(int add)
		{
			this.add=add;
		}
	};
	private static HashMap<Point, BigInteger> table = new HashMap<Point, BigInteger>();
	private static Vector<Point> vector = new Vector<Point>();
	public static int getSize()
	{
		return vector.size();
	}
	public static void removeLastElement(InitProcessPanel.RemoveLastElementMessage m)
	{
		Point p=vector.lastElement();
		//System.out.println(PascalData.vector.size());
		PascalData.vector.removeElementAt((int)(getSize()-1));;
		PascalData.table.remove(p);
	}
	static BigInteger getInteger(int n, int r)
	{
		if(0>r||r>n)return BigInteger.ZERO;
		
		Point point=new Point(n,r);
		
		if(table.containsKey(point))
		{
			return table.get(point);
		}
		else
		{
			if(n==0)
			{
				table.put(point, BigInteger.ONE);
				return BigInteger.ONE;
			}
			else if(r==0)
			{
				table.put(point, BigInteger.ONE);
				return BigInteger.ONE;
			}
			else if(n==r)
			{
				table.put(point, BigInteger.ONE);
				return BigInteger.ONE;
			}
			else
			{
				BigInteger ret=getInteger(n-1,r);
				ret=ret.add(getInteger(n-1,r-1));
				table.put(point, ret);
				vector.addElement(point);
				Static.setBigIntegerSizeCount(new BigIntegerSizeCountMessage(ret.bitCount()));
				return ret;
			}
		}
	}
}
