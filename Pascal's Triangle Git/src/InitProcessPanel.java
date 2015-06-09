import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class InitProcessPanel extends JPanel implements ActionListener{

	public static class RemoveLastElementMessage
	{
		private RemoveLastElementMessage(){}
	};
	public static class IfClearingTableMessage
	{
		boolean b;
		private IfClearingTableMessage(boolean b)
		{
			this.b=b;
		}
	};
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JProgressBar jpb=new JProgressBar(0,100);
	InitProcessPanel()
	{
		add(jpb);
		jpb.setValue(0);
		jpb.setStringPainted(true);
		jpb.setString("Reset start");
		Static.init();
		PascalPanel.init();
		class ThreadTask extends Thread{
			long init_size;
			long cur_size;
			long removed;
			long percent;
			ThreadAction ta=new ThreadAction();
			ThreadTask()
			{
				init_size=PascalData.getSize();
				cur_size=init_size;
				removed=0;
				percent=0;
			}
			public void run()
			{
				Static.setIfClearingTable(new IfClearingTableMessage(true));
				ta.start();
				while(cur_size>0)
				{
					try {
						jpb.setValue((int)percent);
						jpb.setString("Hashmap "+Long.toString(percent)+"% cleared.");
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				jpb.setValue((int)percent);
				jpb.setString("Hashmap "+Long.toString(percent)+"% cleared.");
				Static.setIfClearingTable(new IfClearingTableMessage(false));
			}
			class ThreadAction extends Thread{
				
				public void run()
				{
					while(cur_size>0)
					{
						PascalData.removeLastElement(new RemoveLastElementMessage());
						++removed;
						--cur_size;
						percent=removed*100/init_size;
					}
				}
			}
		}
		ThreadTask tt=new ThreadTask();
		tt.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
