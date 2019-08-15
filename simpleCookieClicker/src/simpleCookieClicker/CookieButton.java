package cookieClicker;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CookieGUI 
{
    static double currentClicks = 0;   // AKA CURRENT COOKIES 
    
    // rewards for clicks and parameters 
    static double grandmaPrice = 5;
    static long grandmaDelay = 2000L;
    static long gmaDelayDecreaser = 100L;
    static boolean gmaScheduleReset;
    
    static int futureCancel = 0;
    
    static int frameX = 1000;
    static int frameY = 400;
	
    static JFrame frame = new JFrame("Click Frame");
	
    static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private static volatile ScheduledFuture<?> grandmasWorkshift;
    private static Runnable grandma = new grandmasBakery();
    
    static JButton incrementClicks = new JButton ("Add Cookies");
    static JButton resetClicks = new JButton("Reset Cookies");
    static JButton grandmaPerk = new JButton("Grandma");
	
	
	 // add a label to the JPanel 
    static JLabel labelCount = new JLabel();

    public static void main(String[] args) 
    {
	CreateFrame();
	incrementClicks.addActionListener(new incrementClicksButtonListener());
	grandmaPerk.addActionListener(new grandmaPerkButtonListener());
	updateClicks(currentClicks);
    }
	
     private static void CreateFrame()
     {
				
	setWindowProperties(frame, frameX, frameY);
	// creates a JPanel to be displayed on the JFrame 
	JPanel panel = new JPanel();
	panel.setBackground(Color.PINK);
	//add a panel to the JFrame
	frame.getContentPane().add(panel); 
				
	labelCount.setPreferredSize(new Dimension(200, 200));
	panel.add(labelCount);
	updateClicks(currentClicks);
	panel.add(resetClicks);
	panel.add(incrementClicks); // add buttons to the JLabel
	panel.add(grandmaPerk); //add button to the JLabel
	//	grandmaPerk.setIcon(arg0);
	resetClicks.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			currentClicks = 0;
			updateClicks(currentClicks);
						
		}
  	 }
     );
				
}
  
     private static void setWindowProperties(JFrame frame, int frameX, int frameY)
     {
	frame.setSize(new Dimension(frameX, frameY));
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setResizable(true);
	//	cookieHouse.pack(); //this will pack the buttons together, the window will be smaller
     }
	


    static class incrementClicksButtonListener implements ActionListener
    {
	 
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		 currentClicks++;
	 	 updateClicks(currentClicks);		
	}
		
    }
	  static class grandmaPerkButtonListener implements ActionListener
	{
	   @Override
	   public void actionPerformed(ActionEvent e)
	   {
	       if(futureCancel == 2)
	       {
		  gmaRunnableService();
	       }
				  
	       if(currentClicks >= grandmaPrice)
	       {
		  currentClicks = currentClicks - grandmaPrice;
		  updateClicks(currentClicks);
					  
		  gmaRunnableService();
					  
		  grandmaPrice = Math.ceil(grandmaPrice * 1.5);
		  grandmaDelay = grandmaDelay - 50;
					  
		  System.out.println("The current price for grandma is: " + grandmaPrice );
		  System.out.println("The current delay for grandma is: " + grandmaDelay );
		  System.out.println("The coordinates for grandmaButton is " + grandmaPerk.getX() + "," + grandmaPerk.getY() );
	       }				  
	       else if(currentClicks < grandmaPrice) 
	       {
	         currentClicks = currentClicks;
	       }
	       else
	       {
	         currentClicks = currentClicks;
	       }
				  
	    }
	}
	  
	private static void gmaRunnableService() // Grandma's runnable 
	{
	
	   if(futureCancel == 0)
	   {
		System.out.println("1st condition for grandma ");
		grandmasWorkshift = executorService.scheduleWithFixedDelay(grandma, 2000, grandmaDelay, TimeUnit.MILLISECONDS);
		futureCancel++; // is equal 1
	  }
	  else if(futureCancel == 1)
	  {
	 	System.out.println("2nd condition for grandma ");
	 	grandmasWorkshift.cancel(true);
		grandmasWorkshift = executorService.scheduleWithFixedDelay(grandma, 2000, grandmaDelay, TimeUnit.MILLISECONDS);
		futureCancel++;//is equal 2
		System.out.println("futureCancel value is  " + futureCancel);
	   }
	  else 
	   {
		System.out.println("resetting");
		grandmasWorkshift.cancel(true);
		futureCancel = 0;
	   }
		
       }
	    
	private static double updateClicks(double currentClicks) // refreshes cookies, should be called every time a change to the count is expected
	{
	  labelCount.setText("Cookies: "+ currentClicks);
	  return currentClicks;
	}
	
	private static final class grandmasBakery implements Runnable
	{
	 @Override public void run() 
		{
		 currentClicks++;
		 updateClicks(currentClicks);
		}
	}
		
	  
}

	

