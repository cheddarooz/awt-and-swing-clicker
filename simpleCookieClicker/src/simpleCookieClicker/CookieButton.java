package simpleCookieClicker;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CookieButton 
{
	static int currentClicks;
	static int frameXAxis = 500;
	static int frameYAxis = 300;
	static int grandmaWantsThisMany = 50;
	
	static Button incrementClicks  = new Button("Click me");
	static Button resetClicks  = new Button("Reset Clicks");
	static Button hireGrandma  = new Button("Hire Grandma");
	
	static JFrame frame  = new JFrame("Cookie Clicker");
	static JLabel label  = new JLabel();
	static JLabel grandmasLabel  = new JLabel();
	static JPanel panel = new JPanel();
	
	static Color Hotpink = new Color(255, 105, 180);
	
	static Rectangle defaultRectangle = new Rectangle(200,40);
	
	public static void main(String[] args) 
	{
		panelAdjustments();
		frameDimensions();
		addToCount();
		knowWhenClickIsClicked();

	}


	private static void addToCount() 
	{
		label.setText("Clicked " + currentClicks + " times");
	}

	private static void panelAdjustments() 
	{
	    panel.setBackground(Hotpink);
		frame.add(panel);
		label.setLayout(null);
		label.setBounds(defaultRectangle);
	//    label.setPreferredSize(new Dimension(200,30));
		panel.add(label);
		panel.add(incrementClicks);
		panel.add(resetClicks);
	    
		//grandmasLabel.setPreferredSize(new Dimension(400, 60));
	    //frame.add(grandmasLabel);
		//grandmasLabel.add(hireGrandma);

	}

	private static void frameDimensions() 
	{

		//frame.setSize(frameXAxis, frameYAxis);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(true);
	}

	private static void knowWhenClickIsClicked()
	{
		incrementClicks.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					currentClicks++;
					addToCount();
				}
				
			}
		);
		resetClicks.addActionListener(new ActionListener()
			{
			   @Override
			   public void actionPerformed(ActionEvent e)
			   {
				   currentClicks = 0;
				   addToCount();
			   }
			
			}
				
		);
	}

}



