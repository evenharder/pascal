import java.awt.Color;
import java.math.BigInteger;

public class Static {
	
	static void init()
	{
		frameWidth=500;
		frameHeight=500;
		
		currentN=0;
		currentR=0;
		currentV=BigInteger.ZERO;
		bigIntegerSizeCount=0;
		
		ifUnderControl=false;
		
		movingDelay=100;
		maxDiff=1000;
		ifAbleToDisplayOffBoard=false;
		
		for(int i=0;i<5;i++)
		{
			ifColorOn[i]=false;
			quotient[i]=0;
			remainder[i]=0;
		}
		highlightColor[0]=Color.GREEN;
		highlightColor[1]=Color.CYAN;
		highlightColor[2]=Color.YELLOW;
		highlightColor[3]=Color.RED;
		highlightColor[4]=Color.BLACK;
		
		backgroundColor=Color.WHITE;
		basicColor=Color.GRAY;
		cursorColor=Color.BLUE;
		
	}
	/** MyFrame */
	private static int frameWidth=500;
	private static int frameHeight=500;
	
	public static int getFrameWidth() {
		return frameWidth;
	}
	public static int getFrameHeight() {
		return frameHeight;
	}
	public static void setFrameSize(MyFrame.FrameSizeMessage m)
	{
		frameWidth=m.w;
		frameHeight=m.h;
	}
	/** PascalPanel, when scrolling */
	static final int blockSizeMin=1;
	static final int blockSizeMax=100;
	static final int unableToReach=-10000;
	
	/** InfoPanel */
	private static int currentN=0;
	private static int currentR=0;
	private static BigInteger currentV=BigInteger.ZERO;
	private static long bigIntegerSizeCount=0;
	
	
	public static int getCurrentN() {
		return currentN;
	}
	public static int getCurrentR() {
		return currentR;
	}
	public static BigInteger getCurrentV() {
		return currentV;
	}
	public static String getCurrentVstring() {
		return currentV.toString();
	}
	public static long getBigIntegerSizeCount() {
		return bigIntegerSizeCount;
	}
	
	public static void setCurrentValues(InfoPanel.CurrentValueMessage m)
	{
		currentN=m.n;
		currentR=m.r;
		currentV=currentV.subtract(currentV);
		currentV=currentV.add(m.v);
	}
	public static void setBigIntegerSizeCount(PascalData.BigIntegerSizeCountMessage m)
	{
		bigIntegerSizeCount+=m.add;
	}
	/** When moving automatically */
	private static boolean ifUnderControl=false;
	private static double wantedN;
	private static double wantedR;
	private static int wantedSize;
	
	private static int movingDelay=100;
	private static int maxDiff=1000;
	private static boolean ifAbleToDisplayOffBoard=false;
	
	public static boolean isIfUnderControl() {
		return ifUnderControl;
	}
	public static double getWantedN() {
		return wantedN;
	}
	public static double getWantedR() {
		return wantedR;
	}
	public static int getWantedSize() {
		return wantedSize;
	}
	public static int getMovingDelay() {
		return movingDelay;
	}
	public static int getMaxDiff() {
		return maxDiff;
	}
	public static boolean isIfAbleToDisplayOffBoard() {
		return ifAbleToDisplayOffBoard;
	}
	
	public static void setIfUnderControl(CalcTrack.IfUnderControlMessage m)
	{
		ifUnderControl=m.b;
	}
	public static void setIfUnderControl(OptionPanel.IfUnderControlMessage m)
	{
		ifUnderControl=m.b;
	}
	public static void setWantedValues(CalcTrack.WantedValueMessage m)
	{
		wantedN=m.n;
		wantedR=m.r;
		wantedSize=m.s;
	}
	public static void setMovingDelay(SettingsPanel.MovingDelayMessage m)
	{
		movingDelay=m.d;
	}
	public static void setMaxDiff(SettingsPanel.MaxDiffMessage m)
	{
		maxDiff=m.d;
	}
	
	public static void setIfAbleToDisplayOffBoard(SettingsPanel.IfAbleToDisplayOffBoardMessage m)
	{
		ifAbleToDisplayOffBoard=!ifAbleToDisplayOffBoard;
	}
	static final double sqrt_quarter=Math.sqrt(0.25);
	
	/** setBorder */
	static final boolean ifInfoPanelBorder=true;
	static final boolean ifPascalPanelBorder=false;
	
	/** ColorOptionTotalPane */
	static final int max_tab=5;
	private static boolean[] ifColorOn={false,false,false,false,false};
	private static int[] quotient={0,0,0,0,0};
	private static int[] remainder={0,0,0,0,0};
	
	private static Color backgroundColor=Color.WHITE;
	private static Color basicColor=Color.GRAY;
	private static Color cursorColor=Color.BLUE;
	private static Color[] highlightColor={Color.GREEN, Color.CYAN, Color.YELLOW, Color.RED, Color.BLACK};
	
	private static boolean ifClearingTable=false;
	
	public static boolean getIfColorOn(int index) {
		return ifColorOn[index];
	}
	public static int getQuotient(int index) {
		return quotient[index];
	}
	public static int getRemainder(int index) {
		return remainder[index];
	}
	public static Color getBackgroundColor() {
		return backgroundColor;
	}
	public static Color getBasicColor() {
		return basicColor;
	}
	public static Color getCursorColor() {
		return cursorColor;
	}
	public static boolean isIfClearingTable() {
		return ifClearingTable;
	}
	public static Color getHighlightColor(int index)
	{
		return highlightColor[index];
	}
	public static void setColorOption(ColorOptionTotalPane.ColorOptionMessage m)
	{
		ifColorOn[m.i]=m.b;
		quotient[m.i]=m.q;
		remainder[m.i]=m.r;
	}
	public static void setColorValues(SettingsPanel.ColorValuesMessage m)
	{
		switch(m.i)
		{
			case 0:
				backgroundColor=m.c;
				break;
			case 1:
				basicColor=m.c;
				break;
			case 2:
				cursorColor=m.c;
				break;
		}
	}
	public static void setHighlightColor(ColorOptionPanel.HighlightColorMessage m)
	{
		highlightColor[m.i]=m.c;
	}
	public static void setIfClearingTable(InitProcessPanel.IfClearingTableMessage m)
	{
		ifClearingTable=m.b;
	}
	static final int maxDist=3000;
	
	static final int headerSize=100;
	
}


