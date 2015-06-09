import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;


public class HelpPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String programVersion="1.0.0.3.";
	
	private JTabbedPane jtp=new JTabbedPane();
	StringBuffer str_help=new StringBuffer();
	JScrollPane sp_help=new JScrollPane();
	JTextArea ta_help=new JTextArea();
	StringBuffer str_update=new StringBuffer();
	JScrollPane sp_update=new JScrollPane();
	JTextArea ta_update=new JTextArea();
	private void updatePanelInit()
	{
		str_update.append("Update Log\n"
				+ "\n");
		/** Version 1.0.0.3.*/
		str_update.append(""
				+ "Version 1.0.0.3.\n"
				+ "\n"
				+ "Forgot to make a renewal the ObjectAid UML Diagram and its snapshot since 1.0.0.0. "
				+ "Now it's refreshed.\n"
				+ "\n");
		/** Version 1.0.0.2.*/
		str_update.append(""
				+ "Version 1.0.0.2.\n"
				+ "\n"
				+ "Renamed the total project name to \"Pascal's Triangle\", "
				+ "which was \"Pascal's Triangle Git\" before.\n"
				+ "\n");
		/** Version 1.0.0.1.*/
		str_update.append(""
				+ "Version 1.0.0.1.\n"
				+ "\n"
				+ "Added GitHub link on the \"Help\" panel.\n"
				+ "\n");
		/** Version 1.0.0.0.*/
		str_update.append(""
				+ "Version 1.0.0.0.\n"
				+ "\n"
				+ "The initial version. Completed at 2015 June 9th, 21:47, GMT +9\n");
		
		ta_update.setWrapStyleWord(true);
		ta_update.setText(str_update.toString());
		ta_update.setEditable(false);
		ta_update.setLineWrap(true);
		ta_update.setFont(new Font("Consolas", Font.PLAIN, 12));
		sp_update=new JScrollPane(ta_update);
		ta_update.setCaretPosition(0);
		sp_update.setPreferredSize(new Dimension(450,300));
		jtp.addTab("Update Log",sp_update);
	}
	private void helpPanelInit()
	{
		str_help.append(  "Pascal's Triangle\n"
				+ "\n"
				+ "1. Abstract\n"
				+ "\n"
				+ "This program is intended to visualize and calculate the values of the Pascal's Triangle. "
				+ "Pascal's Triangle, which is known as to represent the binomial coefficients, has variety of secrets inside. "
				+ "Why don't you find out the beautiful symmetry by yourself by coloring the blocks?\n"
				+ "\n"
				+ "For those who does not know the Pascal's Triangle, here's a simple instruction. "
				+ "Consider a large, very large equilateral triangle. "
				+ "Now, set the value of the vertex and the edges through this vertex as 1. "
				+ "Then, fill the other blanks by the sum of the values above it. "
				+ "That's it! It's this simple. But it's that marvelous.\n"
				+ "\n"
				+ "2. Panel Information\n"
				+ "\n"
				+ "This program is consisted of 3 major panels:\n"
				+ "\n"
				+ "PascalPanel, the panel which displays the Pascal's Triangle itself.\n"
				+ "\n"
				+ "InfoPanel, which displays the location of the cursor "
				+ "and the value of the specific block the cursor is indicating.\n"
				+ "\n"
				+ "OptionPanel, the panel which has buttons which adjust the visual and constants.\n"
				+ "\n"
				+ "Let us take a deeper manual of each panels.\n"
				+ "\n"
				+ "2.1. PascalPanel\n"
				+ "\n"
				+ "PascalPanel displays the Pascal's Triangle. "
				+ "Each coefficients are shown as a gray (in default) block. "
				+ "You can perform three operations with your Mouse.\n"
				+ "\n"
				+ "2.1.1. Scrolling.\n"
				+ "\n"
				+ "You can scroll by your center mouse wheel or your mouse pad. "
				+ "It can change the size of each blocks from 1px to 100px. "
				+ "The default size of the block is 10px."
				+ "Note that the scrolling depends on the cursor's position. "
				+ "Also, note that the smaller the size is, the more blocks are displayed, "
				+ "which can cause lags and, if worse, program shutdown.\n"
				+ "\n"
				+ "2.1.2. Dragging.\n"
				+ "\n"
				+ "You can drag by either of your mouse buttons, wheels, etc.\n"
				+ "\n"
				+ "2.1.3. Lock Toggling\n"
				+ "\n"
				+ "You can toggle whether you'll enable the mentioned methods "
				+ "by clicking your right mouse button alone. "
				+ "Default value is false - which means scrolling and dragging is not locked.\n"
				+ "\n"
				+ "Besides from the operation, you can freely move your cursor. "
				+ "Also, the blocks you indicate with your cursor is highlighted in blue in default.\n"
				+ "\n"
				+ "2.2. InfoPanel\n"
				+ "\n"
				+ "InfoPanel just shows the values or message, so you cannot directly tamper with it. "
				+ "However, executing operations in the PascalPanel changes message of the panel. "
				+ "InfoPanel can show the followings:\n\n"
				+ "2.2.1. (n,r)\n"
				+ "\n"
				+ "The location, consisted of two integers n, and r, "
				+ "indicating what block your cursor is pointing. "
				+ "It is displayed if and only if the cursor is not indicating an empty space. "
				+ "Which means it could have a negative value.\n\n"
				+ "2.2.2. BitCounts\n"
				+ "\n"
				+ "The quantity of estimated bits stored in the HashMap of the program. "
				+ "Note that this can differ from the real bits the program is occupying, "
				+ "however it is reasonably dangerous if it exceeds the range or int(2147483647).\n"
				+ "\n"
				+ "2.2.3. (Toggling Locked)\n"
				+ "\n"
				+ "A string, appears if and only if the PascalPanel is 'locked'. "
				+ "It is possible to scroll the text of the InfoPanel while in this state."
				+ "Also note that InfoPanel does not update itself when the PascalPanel is 'locked'.\n"
				+ "\n"
				+ "2.2.4. Value\n"
				+ "\n"
				+ "The value - n C r - of the block which your cursor is indicating. "
				+ "It is displayed if and only if the cursor is not indicating an empty space.\n"
				+ "\n"
				+ "2.3. OptionPanel\n"
				+ "\n"
				+ "OptionPanel mostly has buttons, but it's fully functional.\n"
				+ "\n"
				+ "2.3.1. Set Values\n"
				+ "\n"
				+ "This button enables PascalPanel to check the quotient and its remainder. "
				+ "If a block displayed in a PascalPanel and has a same 'remainder' at modulus 'quotient', "
				+ "then it is colored with a specific color.\n"
				+ "The panel pops up when you click the button, and has 5 tabs with each subpanel. "
				+ "Each subpanel has a radiobutton, two textfield, and a button.\n"
				+ "\n"
				+ "2.3.1.1. Radiobutton\n"
				+ "\n"
				+ "It determines if such coloring occurs. Default is 'disable'. "
				+ "By setting the state enable, you can enable the textfields below it.\n"
				+ "\n"
				+ "2.3.1.2. Textfields\n"
				+ "\n"
				+ "Two textfield receives a number, each corresponds to a quotient and remainder, respectively. "
				+ "The textfield cannot hold no more than 5 letters.\n"
				+ "The textfield is blank at default, but is saved when you enable it and commit changes. "
				+ "However, the following criterion should be fulfilled to successfully color the blocks;\n"
				+ "\n"
				+ "- You must only enter numerical characters.\n"
				+ "- You must not leave the textfield blank.\n"
				+ "- The quotient should be a natural number.\n"
				+ "- The remainder should be at least 0.\n"
				+ "- The remainder should be less than the quotient.\n"
				+ "\n"
				+ "If the conditions are not satisfied, then the error message is popped up.\n"
				+ "\n"
				+ "2.3.1.3. Change Color?\n"
				+ "\n"
				+ "The current color of the specific tab is filled as a background. "
				+ "Additionally, there is a button which can modify the color. "
				+ "Clicking button triggers an another panel - "
				+ "ColorSliderPane(csp in short), with 3 sliders for each RGB factors. "
				+ "By clicking commit button on the csp, "
				+ "the change is committed instantly.\n"
				+ "\n"
				+ "2.3.1.4. Commit\n"
				+ "\n"
				+ "If you click the \"Commit\" button, only the proper changes are submitted. \n"
				+ "\n"
				+ "The tab which has the lower number has a higher priority. For example, "
				+ "if Tab 1 and Tab 2 are both enabled with a same quotient and remainder, "
				+ "the color of Tab 2 is ignored because the color of Tab 1 overwrites it.\n"
				+ "\n"
				+ "2.3.2. Move\n"
				+ "\n"
				+ "Dragging manually is often interesting, but usually it is time-wasting, to be honest. "
				+ "This method implements automatic dragging within a second in default.\n"
				+ "When you click the \"Move\" button, a panel with two textfields and four labels appears. "
				+ "The left two labels indicate the name of each variables: n, r. "
				+ "The right two labels indicate the range of each variables. "
				+ "The minimum and maximum range is calculated by the following method:\n"
				+ "min_n = min(n-d, 0)\n"
				+ "max_n = min(max(n+d, 0),d)\n"
				+ "min_r = min(r-d, 0)\n"
				+ "max_r = min(max(r+d, 0),d)\n"
				+ "If \"Display off-board case\"(at \"2.3.3.2 Value Adjust\") is disabled, "
				+ "additional calculation is executed;\n"
				+ "min_n = 0\n"
				+ "min_r = 0\n"
				+ "max_n = max(max_n, d)\n"
				+ "max_r = max(max_r, d)\n"
				+ "The moving is only executed if each textfields contains proper values. "
				+ "Also, If \"Display off-board case\" is off, the inequality "
				+ "0 <= r'(new r) <= n'(new n) must hold, "
				+ "even though the range value can suggest unwanted combinations.\n"
				+ "\n"
				+ "The value 'd' can be changed, (at \"2.3.3.2 Value Adjust\"), "
				+ "the cap 3000 is constant to prevent your computer!\n"
				+ "\n"
				+ "2.3.3. Settings\n"
				+ "\n"
				+ "Miscellaneous settings within two tab, total six options. "
				+ "\n"
				+ "2.3.3.1. Color Adjust\n"
				+ "\n"
				+ "This tab contains the same method with \"2.3.1.3 Change Color?\". "
				+ "But this time we can tweak the following:\n"
				+ "\n"
				+ "- (PascalPanel) Background Color, default as white\n"
				+ "- (PascalPanel) Basic Block Color, default as gray\n"
				+ "- (PascalPanel) Cursor-Pointing Color, default as blue\n"
				+ "\n"
				+ "2.3.3.2. Value Adjust\n"
				+ "\n"
				+ "This tab contains two sliders and one checkbox. "
				+ "The following options are instantly saved as modified. "
				+ "We can tweak the following:\n"
				+ "\n"
				+ "- Moving delay, the smaller, the faster.\n"
				+ "- Max Difference, the value 'd' on \"2.3.2 Move\"\n"
				+ "- Display off-board case, by rectangle grids\n"
				+ "\n"
				+ "2.3.4. Reset\n"
				+ "\n"
				+ "So dizzy for what you have done? You can make it new with this button. "
				+ "The 'Reset' process executes the following:\n"
				+ "\n"
				+ "- Initializes all data of \"2.3.1. Set Values\"\n"
				+ "- Initializes all data of \"2.3.3. Settings\"\n"
				+ "- Initializes your position to (0,0)\n"
				+ "- Set the size of the block to 10px\n"
				+ "- Disable locking of PascalPanel\n"
				+ "- Clear the HashMap of PascalPanel\n"
				+ "\n"
				+ "Others can be initialized in a tick, "
				+ "but clearing HashMap could consume quite a lot of time, "
				+ "depending on how hard you played with it. "
				+ "Also, it looks like Java does not let the occupied space free to our RAM......\n"
				+ "\n"
				+ "2.3.5. Help\n"
				+ "\n"
				+ "The text what you are reading right now. Thanks.\n"
				+ "\n"
				+ "2.3.6. Exit\n"
				+ "\n"
				+ "Enjoyed it? When you are bored, then happily click this exit button.\n"
				+ "\n"
				+ "3. Conclusion\n"
				+ "\n"
				+ "Well, I think that's it! This would be enough. "
				+ "I hope that this program gives you mathematical stimulus and ecstacy. "
				+ "Thank you for reading this long tale about this program. \n"
				+ "\n"
				+ "Please, enjoy."
				+ "\n"
				+ "Original Developer : SangHeon Lee\n"
				+ "\n"
				+ "Program Version : "+programVersion+"\n"
				+ "\n"
				+ "GitHub Link : https://github.com/evenharder/pascal\n"
				+ "\n"
				+ "Any opinion is welcome."
			);
	
		ta_help.setWrapStyleWord(true);
		ta_help.setText(str_help.toString());
		ta_help.setEditable(false);
		ta_help.setLineWrap(true);
		ta_help.setFont(new Font("Consolas", Font.PLAIN, 12));
		sp_help=new JScrollPane(ta_help);
		ta_help.setCaretPosition(0);
		sp_help.setPreferredSize(new Dimension(450,300));
		jtp.addTab("Program Info",sp_help);
	}
	HelpPanel()
	{
		helpPanelInit();
		updatePanelInit();
		add(jtp);
	}
}
