import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.math.BigInteger;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class InfoPanel extends JPanel{
	
	private boolean ifStateValid=false;
	class ThreadTimer extends Thread{
		public void run()
		{
			while(true)
			{
				if(PascalPanel.isIfToggleLock()==false)
				{
					ifStateValid=true;
					repaint();
				}
				else
				{
					if(ifStateValid==true)
					{
						repaint();
						ifStateValid=false;
					}
				}
				action();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea result;
	InfoPanel()
	{
		setPreferredSize(new Dimension(Static.getFrameWidth(),150));
		if(Static.ifInfoPanelBorder)setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED),"Info"));
		setLayout(new BorderLayout());
		setMaximumSize( this.getPreferredSize() );
		
		ThreadTimer threadTimer=new ThreadTimer();
		threadTimer.start();
		
		result=new JTextArea();
		
		result.setEditable(false);
		result.setLineWrap(true);
		result.setBackground(null);
		add(result);
		
	}
	
	public static class CurrentValueMessage
	{
		int n;
		int r;
		BigInteger v;
		private CurrentValueMessage(int n, int r, BigInteger v)
		{
			this.n=n;
			this.r=r;
			this.v=v;
		}
	};
	
	public static void writeData(PascalPanel.WriteDataMessage message)
	{
		message.hashCode();
		CurrentValueMessage currentValueMessage=new CurrentValueMessage(message.n, message.r, PascalData.getInteger(message.n, message.r));
		Static.setCurrentValues(currentValueMessage);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		StringBuffer str=new StringBuffer();
		
		if(PascalPanel.isIfPointingValid()==false)
		{
			str.append("Invalid space");
			if(PascalPanel.isIfToggleLock()==true)
			{
				str.append(" (Toggling Locked)");
			}
			str.append(". Total BitCount in HashMap : "+String.valueOf(Static.getBigIntegerSizeCount()));
		}
		else
		{
			str.append("n : "+String.valueOf(Static.getCurrentN()));
			str.append("        r : "+String.valueOf(Static.getCurrentR()));
			str.append("        Total BitCount in HashMap : "+String.valueOf(Static.getBigIntegerSizeCount()));
			if(PascalPanel.isIfToggleLock()==true)
			{
				str.append(" (Toggling Locked)");
			}
			str.append("\n");
			if(Static.getCurrentV()!=BigInteger.ZERO)
			{
				str.append("n C r is "+Static.getCurrentVstring()+"\n");
			}
			else
			{
				str.append("n C r is undefined");
			}
		}
		
		result.setText(str.toString());
	}
	
	public void action()
	{
		setPreferredSize(new Dimension(Static.getFrameWidth(),150));
		setMaximumSize( this.getPreferredSize() );
	}
}
