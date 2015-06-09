import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SettingsPanel extends JPanel implements ActionListener, ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTabbedPane tabbedPane=new JTabbedPane();
	
	private JPanel colorPanel=new JPanel();
	private JPanel tweakPanel=new JPanel();
	private JPanel[] panel=new JPanel[6];
	private JLabel[] value=new JLabel[2];
	private JButton[] btn=new JButton[3];
	private JSlider[] slider=new JSlider[2];
	private JCheckBox check=new JCheckBox();
	private String[] name={"Background Color","Basic Color","Selection Color","Moving delay","Max Difference","Display off-board case"};
	SettingsPanel()
	{
		Box box1 = Box.createVerticalBox();
		Box box2 = Box.createVerticalBox();
		
		for(int i=0;i<6;i++)
		{
			panel[i]=new JPanel();
			panel[i].setBorder(new TitledBorder(name[i]));
		}
		
		for(int i=0;i<3;i++)
		{
			panel[i].setBorder(new TitledBorder(name[i]));
			btn[i]=new JButton("Change Color");
			panel[i].add(btn[i]);
			btn[i].addActionListener(this);
		}
		panel[0].setBackground(Static.getBackgroundColor());
		panel[1].setBackground(Static.getBasicColor());
		panel[2].setBackground(Static.getCursorColor());
		
		box1.add(panel[0]);
		box1.add(Box.createVerticalStrut(10));
		box1.add(panel[1]);
		box1.add(Box.createVerticalStrut(10));
		box1.add(panel[2]);
		box1.add(Box.createVerticalStrut(10));
		
		colorPanel.add(box1);
		
		tabbedPane.addTab("Color Adjust", colorPanel);
		
		slider[0] = new JSlider(JSlider.HORIZONTAL, 10, 200, Static.getMovingDelay());
		slider[1] = new JSlider(JSlider.HORIZONTAL, 100, 2000, Static.getMaxDiff());
		value[0] = new JLabel(Integer.toString(Static.getMovingDelay()));
		value[1] = new JLabel(Integer.toString(Static.getMaxDiff()));
		
		for(int i=3;i<5;i++)
		{
			slider[i-3].setPaintLabels(true);
			slider[i-3].setPaintTicks(true);
			slider[i-3].setPaintTrack(true);
			slider[i-3].addChangeListener(this);
			panel[i].add(slider[i-3]);
			panel[i].add(value[i-3]);
		}
		
		check=new JCheckBox(name[5]);
		check.addActionListener(this);
		panel[5].add(check);

		check.setSelected(Static.isIfAbleToDisplayOffBoard());
		
		box2.add(panel[3]);
		box2.add(Box.createVerticalStrut(10));
		box2.add(panel[4]);
		box2.add(Box.createVerticalStrut(10));
		box2.add(panel[5]);
		box2.add(Box.createVerticalStrut(10));
		
		tweakPanel.add(box2);
		
		tabbedPane.addTab("Value Adjust", tweakPanel);
		
		UIManager.put("OptionPane.okButtonText", "Close");
		JOptionPane.showMessageDialog(null, tabbedPane,"Change settings",JOptionPane.QUESTION_MESSAGE);
	}
	public class IfAbleToDisplayOffBoardMessage
	{
		private IfAbleToDisplayOffBoardMessage() {}
	}
	public class ColorValuesMessage
	{
		Color c;
		int i;//0 : Background, 1 : Basic, 2 : Cursor
		private ColorValuesMessage(Color c, int i)
		{
			this.c=c;
			this.i=i;
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btn[0])
		{
			ColorSliderPane csp=new ColorSliderPane(Static.getBackgroundColor());
			UIManager.put("OptionPane.okButtonText", "Confirm");
			UIManager.put("OptionPane.cancelButtonText", "Cancel");
			int result = JOptionPane.showConfirmDialog(null, csp, "Set the color", JOptionPane.OK_CANCEL_OPTION);
			
			if (result == JOptionPane.OK_OPTION){
				Color col=new Color(csp.sl[0].getValue(), csp.sl[1].getValue(), csp.sl[2].getValue());
				Static.setColorValues(new ColorValuesMessage(col,0));
				panel[0].setBackground(col);
			}
		}
		if(arg0.getSource()==btn[1])
		{
			ColorSliderPane csp=new ColorSliderPane(Static.getBasicColor());
			UIManager.put("OptionPane.okButtonText", "Confirm");
			UIManager.put("OptionPane.cancelButtonText", "Cancel");
			
			int result = JOptionPane.showConfirmDialog(null, csp, "Set the color", JOptionPane.OK_CANCEL_OPTION);
			
			if (result == JOptionPane.OK_OPTION){
				Color col=new Color(csp.sl[0].getValue(), csp.sl[1].getValue(), csp.sl[2].getValue());
				Static.setColorValues(new ColorValuesMessage(col,1));
				panel[1].setBackground(col);
			}
		}
		if(arg0.getSource()==btn[2])
		{
			ColorSliderPane csp=new ColorSliderPane(Static.getCursorColor());
			UIManager.put("OptionPane.okButtonText", "Confirm");
			UIManager.put("OptionPane.cancelButtonText", "Cancel");
			int result = JOptionPane.showConfirmDialog(null, csp, "Set the color", JOptionPane.OK_CANCEL_OPTION);
			
			if (result == JOptionPane.OK_OPTION){
				Color col=new Color(csp.sl[0].getValue(), csp.sl[1].getValue(), csp.sl[2].getValue());
				Static.setColorValues(new ColorValuesMessage(col,2));
				panel[1].setBackground(col);
			}
		}
		if(arg0.getSource()==check)
		{
			check.setSelected(!Static.isIfAbleToDisplayOffBoard());
			
			Static.setIfAbleToDisplayOffBoard(new IfAbleToDisplayOffBoardMessage());
		}
	}
	public class MovingDelayMessage
	{
		int d;
		private MovingDelayMessage(int d)
		{
			this.d=d;
		}
	}
	public class MaxDiffMessage
	{
		int d;
		private MaxDiffMessage(int d)
		{
			this.d=d;
		}
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		value[0].setText(Integer.toString(slider[0].getValue()));
		Static.setMovingDelay(new MovingDelayMessage(Integer.parseInt(value[0].getText())));
		value[1].setText(Integer.toString(slider[1].getValue()));
		Static.setMaxDiff(new MaxDiffMessage(Integer.parseInt(value[1].getText())));
	}
}
