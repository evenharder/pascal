import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.math.BigInteger;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class PascalPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	private int panelWidth;
	private int panelHeight;
	
	private Point curPoint;
	private Vector<Pointdata> curPointVector=new Vector<Pointdata>();
	
	private static double vertexN=0.0;
	private static double vertexR=0.0;
	private static int blockSize=10;
	private static boolean ifToggleLock=false;
	private static boolean ifPointingValid=false;
	
	public static double getVertexN() {
		return vertexN;
	}

	public static double getVertexR() {
		return vertexR;
	}

	public static int getBlockSize() {
		return blockSize;
	}

	public static boolean isIfToggleLock() {
		return ifToggleLock;
	}

	public static boolean isIfPointingValid() {
		return ifPointingValid;
	}
	public static void setDestination(CalcTrack.DestinationMessage m)
	{
		vertexN=m.n;
		vertexR=m.r;
	}
	class ThreadTimer extends Thread{
		public void run()
		{
			while(true)
			{
				getPanelSize();
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static class WriteDataMessage
	{
		int n;
		int r;
		private WriteDataMessage(int n, int r)
		{
			this.n=n;
			this.r=r;
		}
	};
	
	public void getPanelSize()
	{
		panelWidth=this.getWidth();
		panelHeight=this.getHeight();
	}
	
	static void init()
	{
		vertexN=0.0;
		vertexR=0.0;
		blockSize=10;
		ifToggleLock=false;
	}
	
	private static final long serialVersionUID = 1L;

	PascalPanel()
	{
		if(Static.ifPascalPanelBorder)setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED),"Pascal's Triangle"));
		setLayout(new BorderLayout());
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		
		ThreadTimer threadTimer=new ThreadTimer();
		threadTimer.start();
	}
	
	void drawRect(Graphics g, int x, int y, int width, int height)
	{
		g.drawLine(x, y, x+width-1, y);
		g.drawLine(x+width, y, x+width, y+height-1);
		g.drawLine(x, y+height, x+width-1, y+height);
		g.drawLine(x, y, x, y+height-1);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		super.setBackground(Static.getBackgroundColor());
		
		getPanelSize();
		
		if(Static.isIfClearingTable())
		{
			return;
		}
		ifPointingValid=false;
		
		int halfSize=0;
		int cur_x=0;
		int cur_y=0;
		if(Static.isIfUnderControl())
		{
			cur_x=panelWidth/2;
			cur_y=panelHeight/2;
			curPoint=new Point(cur_x,cur_y);
			blockSize=Static.getWantedSize();
			vertexN=Static.getWantedN();
			vertexR=Static.getWantedR();
			
			curPointVector=getPointVector(panelWidth, panelHeight, blockSize);
			halfSize=blockSize/2;
		}
		else
		{
			cur_x=Static.unableToReach;
			cur_y=Static.unableToReach;
			if(curPoint!=null)
			{
				cur_x=curPoint.x;
				cur_y=curPoint.y;
			}
			curPointVector=getPointVector(panelWidth, panelHeight, blockSize);
			halfSize=blockSize/2;
		}
		
		BigInteger[] b_q=new BigInteger[5];
		BigInteger[] b_r=new BigInteger[5];
		
		for(int index=0;index<Static.max_tab;index++)
		{
			b_q[index]=new BigInteger(String.valueOf(Static.getQuotient(index)));
			b_r[index]=new BigInteger(String.valueOf(Static.getRemainder(index)));
		}
		
		for(int i=0;i<curPointVector.size();i++)
		{
			Pointdata p=curPointVector.elementAt(i);
			BigInteger v=PascalData.getInteger(p.n, p.r);
			
			if(0<=p.r&&p.r<=p.n)
			{
				g.setColor(Static.getBasicColor());
				g.fillRect(p.x-halfSize,p.y-halfSize,blockSize,blockSize);
				
				for(int index=Static.max_tab-1;index>=0;index--)
				{
					if(Static.getIfColorOn(index)&&b_q[index]!=BigInteger.ZERO&&v.remainder(b_q[index]).equals(b_r[index]))
					{
						g.setColor(Static.getHighlightColor(index));
						g.fillRect(p.x-halfSize,p.y-halfSize,blockSize,blockSize);
					}
				}		
				
				if(p.x-halfSize<=cur_x&&cur_x<=p.x+halfSize&&p.y-halfSize<=cur_y&&cur_y<=p.y+halfSize)
				{
					g.setColor(Static.getCursorColor());
					g.fillRect(p.x-halfSize,p.y-halfSize,blockSize,blockSize);
					
					ifPointingValid=true;
					InfoPanel.writeData(new WriteDataMessage(p.n,p.r));
				}
			}
			else
			{
				if(Static.isIfAbleToDisplayOffBoard())
				{
					if(halfSize!=0)
					{
						g.setColor(Static.getBasicColor());
						drawRect(g,p.x-halfSize,p.y-halfSize,blockSize-1,blockSize-1);
					}
					
					if(p.x-halfSize<=cur_x&&cur_x<=p.x+halfSize&&p.y-halfSize<=cur_y&&cur_y<=p.y+halfSize)
					{
						g.setColor(Static.getCursorColor());
						g.fillRect(p.x-halfSize,p.y-halfSize,blockSize,blockSize);
						
						ifPointingValid=true;
						InfoPanel.writeData(new WriteDataMessage(p.n,p.r));
						
					}
				}
				else if(p.x-halfSize<=cur_x&&cur_x<=p.x+halfSize&&p.y-halfSize<=cur_y&&cur_y<=p.y+halfSize)
				{
					ifPointingValid=true;
					InfoPanel.writeData(new WriteDataMessage(p.n,p.r));
					
				}
			}
		}
	}
	
	private class Pointdata
	{
		int n;
		int r;
		int x;
		int y;
		Pointdata(int n,int r,int x,int y)
		{
			this.n=n;
			this.r=r;
			this.x=x;
			this.y=y;
		}
	}
	
	Vector<Pointdata> getPointVector(int width, int height, int size)
	{
		/* Bug? */
		int n=(int)vertexN;
		int r=(int)vertexR;
		int xPos=width/2+(int)((double)(size+2)*Static.sqrt_quarter*(vertexN%1.00)-(size+2)*(vertexR%1.00));
		int yPos=height/2+(int)(-(double)(size+2)*(vertexN%1.00));
		
		Vector<Pointdata> pVec=new Vector<Pointdata>();
		for(int i=0;;i++)
		{
			int y=yPos+(size+2)*i;
			if(-size<=y && y<=height+size)
			{
				for(int j=0;;j++)
				{
					int x=xPos+(size+2)*j-(size+2)*i/2;
					if(-size<=x && x<=width+size)
					{
						pVec.add(new Pointdata(n+i,r+j,x,y));
					}
					else break;
				}
				for(int j=-1;;j--)
				{
					int x=xPos+(size+2)*j-(size+2)*i/2;
					if(-size<=x && x<=width+size)
					{
						pVec.add(new Pointdata(n+i,r+j,x,y));
					}
					else break;
				}
			}
			else break;
		}
		for(int i=-1;;i--)
		{
			int y=yPos+(size+2)*i;
			if(-size<=y && y<=height+size)
			{
				for(int j=0;;j++)
				{
					int x=xPos+(size+2)*j-(size+2)*i/2;
					if(-size<=x && x<=width+size)
					{
						pVec.add(new Pointdata(n+i,r+j,x,y));
					}
					else break;
				}
				for(int j=-1;;j--)
				{
					int x=xPos+(size+2)*j-(size+2)*i/2;
					if(-size<=x && x<=width+size)
					{
						pVec.add(new Pointdata(n+i,r+j,x,y));
					}
					else break;
				}
			}
			else break;
		}
		return pVec;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		if(ifToggleLock) return;
		int prev_blockSize=blockSize;
		blockSize-=e.getWheelRotation();
		Point p=e.getPoint();
		if(blockSize<=Static.blockSizeMin)blockSize=Static.blockSizeMin;
		if(blockSize>=Static.blockSizeMax)blockSize=Static.blockSizeMax;
		int dx=p.x-panelWidth/2;
		int dy=p.y-panelHeight/2;
		double tempVertexN=vertexN+(double)dy/((double)(prev_blockSize+2));
		double tempVertexR=vertexR+(double)dy/(2.00*(double)(prev_blockSize+2))+(double)dx/(double)(prev_blockSize+2);
		double newVertexN=tempVertexN+(vertexN-tempVertexN)*((double)(prev_blockSize+2)/(double)(blockSize+2));
		double newVertexR=tempVertexR+(vertexR-tempVertexR)*((double)(prev_blockSize+2)/(double)(blockSize+2));
		vertexN=newVertexN;
		vertexR=newVertexR;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(ifToggleLock) return;
		
		Point newPoint=e.getPoint();
		int dx=newPoint.x-curPoint.x;
		int dy=newPoint.y-curPoint.y;
		curPoint=newPoint;
		
		double tempVertexN=vertexN-(double)dy/((double)(blockSize+2));
		double tempVertexR=vertexR-(double)dy/(2.00*(double)(blockSize+2))-(double)dx/(double)(blockSize+2);
		vertexN=tempVertexN;
		vertexR=tempVertexR;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(!ifToggleLock)
		curPoint=e.getPoint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if( SwingUtilities.isRightMouseButton(e)&&
				SwingUtilities.isLeftMouseButton(e)==false&&
				SwingUtilities.isMiddleMouseButton(e)==false)
			{
				ifToggleLock=!ifToggleLock;
			}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!ifToggleLock)
		curPoint=e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
