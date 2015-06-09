import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;


public class OptionPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	private JButton btn_calc=new JButton("Set values");
	private JButton btn_move=new JButton("Move");
	private JButton btn_sett=new JButton("Settings");
	private JButton btn_clr=new JButton("Reset");
	private JButton btn_help=new JButton("Help");
	private JButton btn_exit=new JButton("Exit");
	
	JButton[] btn={btn_calc,btn_move,btn_sett,btn_clr,btn_help,btn_exit};
	JPanel[] panel=new JPanel[btn.length];
	JScrollPane jsp=new JScrollPane();
	private InitProcessPanel ipp;
	private HelpPanel hp;
	@SuppressWarnings("unused")
	private SettingsPanel sp;
	private MovePanel mp;
	ColorOptionTotalPane cotp;
	
	private class ThreadTimer extends Thread{
		public void run()
		{
			while(true)
			{
				//repaint();
				if(Static.isIfUnderControl())
				{
					btn_move.setText("Abort");
				}
				else
				{
					btn_move.setText("Move");
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	OptionPanel()
	{
		Box box = Box.createVerticalBox();
		
		for(int i=0;i<btn.length;i++)
		{
			panel[i]=new JPanel(new GridLayout());
			panel[i].add(btn[i]);
			btn[i].addActionListener(this);
			box.add(panel[i]);
			box.add(Box.createVerticalStrut(10));
		}
		
		add(box);
		ThreadTimer threadTimer=new ThreadTimer();
		threadTimer.start();
	}

	public static class IfUnderControlMessage
	{
		boolean b;
		private IfUnderControlMessage(boolean b)
		{
			this.b=b;
		}
	};
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btn_calc)
		{
			cotp=new ColorOptionTotalPane();
		}
		if(arg0.getSource()==btn_move)
		{
			if(Static.isIfUnderControl()==true)
			{
				Static.setIfUnderControl(new IfUnderControlMessage(false));//Aborting
			}
			else
			{
				mp=new MovePanel();
				String str;
				boolean ifError=false;
				
				int cur_n=(int)PascalPanel.getVertexN();
				int cur_r=(int)PascalPanel.getVertexR();
				int min_n=Math.min(cur_n-Static.getMaxDiff(), 0);
				int max_n=Math.min(Math.max(cur_n+Static.getMaxDiff(),0),Static.maxDist);
				int min_r=Math.min(cur_r-Static.getMaxDiff(), 0);
				int max_r=Math.min(Math.max(cur_r+Static.getMaxDiff(),0),Static.maxDist);
				if(Static.isIfAbleToDisplayOffBoard()==false)
				{
					min_n=Math.min(0, Math.max(min_n, 0));
					min_r=Math.min(0, Math.max(min_r, 0));
					max_n=Math.max(max_n, Static.getMaxDiff());
					max_r=Math.max(max_r, Static.getMaxDiff());
				}
				String jp_str1="The range of the input of n : "+Integer.toString(min_n)+" ~ "+Integer.toString(max_n);
				String jp_str2="The range of the input of r : "+Integer.toString(min_r)+" ~ "+Integer.toString(max_r);
				
				Box box = Box.createVerticalBox();
				
				box.add(new JLabel(jp_str1));
				box.add(Box.createVerticalStrut(15));
				box.add(new JLabel(jp_str2));
				mp.add(box);
				
				UIManager.put("OptionPane.yesButtonText", "Confirm");
				UIManager.put("OptionPane.noButtonText", "Cancel");
				int result = JOptionPane.showConfirmDialog(null, mp, "Where to go?",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(result == JOptionPane.YES_OPTION)
				{
					boolean ifnFieldValid=true;
					boolean ifrFieldValid=true;
					int n=0;
					int r=0;
					try{
						n=Integer.parseInt(mp.getText_n());
					} catch (NumberFormatException e) {
						ifnFieldValid=false;
					}
					
					try{
						r=Integer.parseInt(mp.getText_r());
					} catch (NumberFormatException e) {
						ifrFieldValid=false;
					}
					
					if(ifnFieldValid==false&&ifrFieldValid==false)
					{
						str="Invalid inputs on n and r";
						ifError=true;
					}
					else if(ifnFieldValid==false)
					{
						str="Invalid input on n";
						ifError=true;
					}
					else if(ifrFieldValid==false)
					{
						str="Invalid input on r";
						ifError=true;
					}
					else
					{
						boolean ifnValueValid=true;
						boolean ifrValueValid=true;
						if(Static.isIfAbleToDisplayOffBoard()==false)
						{
							if(n<0)
							{
								ifnValueValid=false;
							}
							if(r<0||(n>=0&&r>n))
							{
								ifrValueValid=false;
							}
						}
						if(min_n>n||max_n<n)
						{
							ifnValueValid=false;
						}
						if(min_r>r||max_r<r)
						{
							ifrValueValid=false;
						}
						
						if(ifnValueValid==false&&ifrValueValid==false)
						{
							str="Invalid values on quotient and remainder";
							ifError=true;
						}
						else if(ifnValueValid==false)
						{
							str="Invalid value on quotient";
							ifError=true;
						}
						else if(ifrValueValid==false)
						{
							str="Invalid value on remainder";
							ifError=true;
						}
						else
						{
							str="";
							action(n,r);
						}
					}
					if(ifError)
					{
						this.setEnabled(false);
						JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		if(arg0.getSource()==btn_sett)
		{
			UIManager.put("OptionPane.okButtonText", "Close");
			sp=new SettingsPanel();
		}
		if(arg0.getSource()==btn_clr)
		{
			UIManager.put("OptionPane.yesButtonText", "Confirm");
			UIManager.put("OptionPane.noButtonText", "Cancel");
			int result = JOptionPane.showConfirmDialog(null, "Reset all perferences?", "Reset?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(result==JOptionPane.YES_OPTION)
			{
				ipp=new InitProcessPanel();
				JOptionPane.showMessageDialog(null, ipp, "Initialzing......", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if(arg0.getSource()==btn_help)
		{
			UIManager.put("OptionPane.okButtonText", "Close");
			hp=new HelpPanel();
			JOptionPane.showMessageDialog(null, hp, "Information", JOptionPane.INFORMATION_MESSAGE);
		}
		if(arg0.getSource()==btn_exit)
		{
			UIManager.put("OptionPane.yesButtonText", "Yes");
			UIManager.put("OptionPane.noButtonText", "No");
			int result = JOptionPane.showConfirmDialog(null, "Terminate Program?", "Exit?",  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(result==JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
	}
	public void action(int n, int r)
	{
		CalcTrack calc=new CalcTrack
				(PascalPanel.getVertexN(),(double)n,
				PascalPanel.getVertexR(),(double)r,
				Static.getMovingDelay());
		calc.start();
	}

}
