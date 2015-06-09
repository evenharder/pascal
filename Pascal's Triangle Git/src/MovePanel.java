import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MovePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel l_n=new JLabel("n :");
	private JLabel l_r=new JLabel("r :");
	private JTextField t_n = new JTextField(5);
	private JTextField t_r = new JTextField(5);
	
	public String getText_n()
	{
		return t_n.getText();
	}
	
	public String getText_r()
	{
		return t_r.getText();
	}
	
	MovePanel()
	{
		t_n.setDocument(new JTextFieldLimit(5));
		t_r.setDocument(new JTextFieldLimit(5));
		
		Box box = Box.createVerticalBox();
		Box boxn = Box.createHorizontalBox();
		Box boxr = Box.createHorizontalBox();
		
		boxn.add(l_n);
		boxn.add(Box.createHorizontalStrut(10));
		boxn.add(t_n);
		box.add(boxn);
		box.add(Box.createVerticalStrut(10));
		boxr.add(l_r);
		boxr.add(Box.createHorizontalStrut(10));
		boxr.add(t_r);
		box.add(boxr);
		
		add(box);
	}
}
