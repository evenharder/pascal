import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ColorSliderPane extends JPanel implements ChangeListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel colorLabel;
	JSlider[] sl = new JSlider[3];
	ColorSliderPane()
	{
		this(Color.BLACK);
	}
	ColorSliderPane(Color col) {
		
		setLayout(new FlowLayout());
		colorLabel = new JLabel(" Color Arrange ");

		sl[0] = new JSlider(JSlider.VERTICAL, 0, 255, col.getRed());
		sl[1] = new JSlider(JSlider.VERTICAL, 0, 255, col.getGreen());
		sl[2] = new JSlider(JSlider.VERTICAL, 0, 255, col.getBlue());
		
		for (int i = 0; i < sl.length; i++) {
			sl[i].setPaintLabels(true);
			sl[i].setPaintTicks(true);
			sl[i].setPaintTrack(true);
			sl[i].setMajorTickSpacing(51);
			sl[i].setMinorTickSpacing(10);
			sl[i].addChangeListener(this);
			add(sl[i]);
		}

		sl[0].setForeground(Color.RED);
		sl[1].setForeground(Color.GREEN);
		sl[2].setForeground(Color.BLUE);
		
		colorLabel.setOpaque(true);  //ºÒÅõ¸í
		colorLabel.setBackground(new Color(sl[0].getValue(), sl[1].getValue(),
				sl[2].getValue()));
		
		add(colorLabel);
		setSize(300, 300);
		setVisible(true);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		colorLabel.setBackground(new Color(sl[0].getValue(), sl[1].getValue(), sl[2].getValue()));
	}
}
