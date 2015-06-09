import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	
	public class FrameSizeMessage{
		int w;
		int h;
		private FrameSizeMessage(int w, int h)
		{
			this.w=w;
			this.h=h;
		}
	}
	private class ThreadTimer extends Thread{
		public void run()
		{
			while(true)
			{
				Static.setFrameSize(new FrameSizeMessage(getWidth(),getHeight()));
				try {
					Thread.sleep(10);
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
	
	PascalPanel pascalPanel=new PascalPanel();
	InfoPanel infoPanel=new InfoPanel();
	OptionPanel optionPanel=new OptionPanel();
	MyFrame()
	{
		super("Pascal's Triangle!");

		add(pascalPanel,BorderLayout.CENTER);
		add(infoPanel,BorderLayout.SOUTH);
		add(optionPanel,BorderLayout.EAST);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ThreadTimer threadTimer=new ThreadTimer();
		threadTimer.start();
		
		setSize(500,600);
		setLocation(100,100);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame();
	}
	
}
