import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;


public class ColorOptionTotalPane extends JOptionPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public class ColorOptionMessage{
		int q;
		int r;
		boolean b;
		int i;
		private ColorOptionMessage(int q, int r, boolean b,int i)
		{
			this.q=q;
			this.r=r;
			this.b=b;
			this.i=i;
		}
	}
	private ColorOptionPanel[] vop=new ColorOptionPanel[Static.max_tab];
	private JTabbedPane tabbedPane=new JTabbedPane();
	ColorOptionTotalPane()
	{
		//http://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog
		for(int i=0;i<5;i++)
		{
			vop[i]=new ColorOptionPanel(i);
			tabbedPane.addTab("Tab "+(i+1), vop[i]);
		}
		
		UIManager.put("OptionPane.okButtonText", "Confirm");
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		int result = JOptionPane.showConfirmDialog(null, tabbedPane, "Set the values", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String[] str=new String[Static.max_tab];
			boolean ifError=false;
			for(int i=0;i<Static.max_tab;i++)
			{
				if(vop[i].isYesButtonSelected())
				{
					boolean ifqFieldValid=true;
					boolean ifrFieldValid=true;
					int q=0;
					int r=0;
					try{
						q=Integer.parseInt(vop[i].getQText());
					} catch (NumberFormatException e) {
						ifqFieldValid=false;
					}
					
					try{
						r=Integer.parseInt(vop[i].getRText());
					} catch (NumberFormatException e) {
						ifrFieldValid=false;
					}
					
					if(ifqFieldValid==false&&ifrFieldValid==false)
					{
						str[i]="Error occured on Tab "+(i+1)+" : "+"Invalid inputs on quotient and remainder";
						ifError=true;
					}
					else if(ifqFieldValid==false)
					{
						str[i]="Error occured on Tab "+(i+1)+" : "+"Invalid input on quotient";
						ifError=true;
					}
					else if(ifrFieldValid==false)
					{
						str[i]="Error occured on Tab "+(i+1)+" : "+"Invalid input on remainder";
						ifError=true;
					}
					else
					{
						boolean ifqValueValid=true;
						boolean ifrValueValid=true;
						if(q<=0)
						{
							ifqValueValid=false;
						}
						if(r<0||(q>0&&r>=q))
						{
							ifrValueValid=false;
						}
						
						if(ifqValueValid==false&&ifrValueValid==false)
						{
							str[i]="Error occured on Tab "+(i+1)+" : "+"Invalid values on quotient and remainder";
							ifError=true;
						}
						else if(ifqValueValid==false)
						{
							str[i]="Error occured on Tab "+(i+1)+" : "+"Invalid value on quotient";
							ifError=true;
						}
						else if(ifrValueValid==false)
						{
							str[i]="Error occured on Tab "+(i+1)+" : "+"Invalid value on remainder";
							ifError=true;
						}
						else
						{
							Static.setColorOption(new ColorOptionMessage(q,r,true,i));
							str[i]="";
						}
					}
				}
				else
				{
					Static.setColorOption(new ColorOptionMessage(0,0,false,i));
				}
			}
			if(ifError)
			{
				JPanel errPanel=new JPanel();
				JTextArea errText=new JTextArea();
				errPanel.add(errText);
				errText.setEditable(false);
				errText.setLineWrap(true);
				errText.setBackground(null);
				
				int cnt=0;
				for(int i=0;i<Static.max_tab;i++)
				{
					if(str[i]!=null)
					{
						cnt++;
						errText.setText(errText.getText()+'\n'+str[i]);
					}
				}
				errText.setPreferredSize(new Dimension(400,40+cnt*15));
				UIManager.put("OptionPane.okButtonText", "Close");
				JOptionPane.showMessageDialog(null, errPanel, "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				UIManager.put("OptionPane.okButtonText", "Close");
				JOptionPane.showMessageDialog(null, "Committed changes successfully.");
			}
		}
	}
}
