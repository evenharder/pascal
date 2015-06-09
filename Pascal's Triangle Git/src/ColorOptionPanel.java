import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ColorOptionPanel extends JComponent implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ButtonGroup group=new ButtonGroup();
	private JRadioButton btn_yes=new JRadioButton("Enable");
	private JRadioButton btn_no=new JRadioButton("Disable");
	private JLabel l_quotient=new JLabel("Quotient :");
	private JLabel l_remainder=new JLabel("Remainder :");
	private JTextField qField = new JTextField(5);
	private JTextField rField = new JTextField(5);
	
	private JPanel buttonPanel=new JPanel();
	private JPanel textPanel=new JPanel();
	private JPanel colorPanel=new JPanel();
	
	private JButton btn_color=new JButton("Change color?");
	private int index;
	
	private ColorSliderPane csp;
	
	public boolean isYesButtonSelected()
	{
		return btn_yes.isSelected();
	}
	public boolean isNoButtonSelected()
	{
		return btn_no.isSelected();
	}
	public String getQText()
	{
		return qField.getText();
	}
	public String getRText()
	{
		return rField.getText();
	}
	
	ColorOptionPanel(int ind)
	{
		setLayout(new BorderLayout());
		if(ind>=5)
		{
			System.err.println("Wrong index value "+ind);
		}
		
		this.index=ind;
		
		group.add(btn_yes);
		group.add(btn_no);
		
		buttonPanel.add(btn_yes);
		buttonPanel.add(btn_no);
		
		add(buttonPanel,BorderLayout.NORTH);
		btn_yes.addActionListener(this);
		btn_no.addActionListener(this);
		
		if(Static.getIfColorOn(index))
		{
			btn_yes.setSelected(true);
		}
		else
		{
			btn_no.setSelected(true);
		}
		
		update();
		
		Box box = Box.createVerticalBox();
		Box boxq = Box.createHorizontalBox();
		Box boxr = Box.createHorizontalBox();
		
		boxq.add(Box.createHorizontalStrut(14));
		boxq.add(l_quotient);
		boxq.add(Box.createHorizontalStrut(10));
		boxq.add(qField);
		box.add(boxq);
		box.add(Box.createVerticalStrut(10));
		boxr.add(l_remainder);
		boxr.add(Box.createHorizontalStrut(10));
		boxr.add(rField);
		box.add(boxr);
		
		qField.setDocument(new JTextFieldLimit(5));
		rField.setDocument(new JTextFieldLimit(5));
		textPanel.add(box);
		
		if(Static.getQuotient(index)!=0)
		{
			qField.setText(Integer.toString(Static.getQuotient(index)));
			rField.setText(Integer.toString(Static.getRemainder(index)));
		}
		
		add(textPanel,BorderLayout.CENTER);
		
		btn_color.addActionListener(this);
		colorPanel.add(btn_color);
		colorPanel.setBackground(Static.getHighlightColor(index));
		add(colorPanel,BorderLayout.SOUTH);
		
	}

	public void update()
	{
		if(btn_yes.isSelected())
		{
			qField.setEditable(true);
			rField.setEditable(true);
		}
		else
		{
			qField.setEditable(false);
			rField.setEditable(false);
		}
	}
	
	public class HighlightColorMessage{
		Color c;
		int i;
		private HighlightColorMessage(Color c,int i){
			this.c=c;
			this.i=i;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_yes||e.getSource()==btn_no)
		{
			update();
		}
		if(e.getSource()==btn_color)
		{
			csp=new ColorSliderPane(Static.getHighlightColor(index));
			
			UIManager.put("OptionPane.okButtonText", "Confirm");
			UIManager.put("OptionPane.cancelButtonText", "Cancel");
			int result = JOptionPane.showConfirmDialog(null, csp, "Set the color", JOptionPane.OK_CANCEL_OPTION);
			
			if (result == JOptionPane.OK_OPTION){
				Color col=new Color(csp.sl[0].getValue(), csp.sl[1].getValue(), csp.sl[2].getValue());
				Static.setHighlightColor(new HighlightColorMessage(col,index));
				colorPanel.setBackground(col);
			}
		}
	}
}
