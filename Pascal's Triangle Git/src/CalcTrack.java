public class CalcTrack extends Thread{
	
	private double startN;
	private double endN;
	private double startR;
	private double endR;
	private int startS;
	private int endS;
	private int delay;
	public static class IfUnderControlMessage
	{
		boolean b;
		private IfUnderControlMessage(boolean b)
		{
			this.b=b;
		}
	};
	public static class WantedValueMessage
	{
		double n;
		double r;
		int s;
		private WantedValueMessage(double n, double r, int s)
		{
			this.n=n;
			this.r=r;
			this.s=s;
		}
	}
	public static class DestinationMessage
	{
		double n;
		double r;
		private DestinationMessage(double n, double r)
		{
			this.n=n;
			this.r=r;
		}
	}
	public void run()
	{
		Static.setIfUnderControl(new IfUnderControlMessage(true));
		double total_diff=(double)(((delay+1)/2)*((delay+3)/2)/2+(delay/2)*((delay+2)/2)/2);
		double diff=0.0;
		for(int i=1;i<=delay;i++)
		{
			try {
				
				if(Static.isIfUnderControl()==false)
				{
					return;
				}
				WantedValueMessage wantedValueMessage=
						new WantedValueMessage(
							startN+(endN-startN)*diff/total_diff,
							startR+(endR-startR)*diff/total_diff,
							startS+(endS-startS)*(int)(diff/total_diff));
				
				diff+=(double)Math.min(i,delay-i+1);
				Static.setWantedValues(wantedValueMessage);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		PascalPanel.setDestination(new DestinationMessage(endN, endR));
		Static.setIfUnderControl(new IfUnderControlMessage(false));
	}
	CalcTrack(double startN, double endN, double startR, double endR, int delay)
	{
		this.startN=startN;
		this.startR=startR;
		this.endN=endN;
		this.endR=endR;
		this.startS=PascalPanel.getBlockSize();
		this.endS=PascalPanel.getBlockSize();
		this.delay=delay;
	}
	CalcTrack(double startN, double endN, double startR, double endR, int startS, int endS, int delay)
	{
		this.startN=startN;
		this.startR=startR;
		this.endN=endN;
		this.endR=endR;
		this.startS=startS;
		this.endS=endS;
		this.delay=delay;
	}
}
