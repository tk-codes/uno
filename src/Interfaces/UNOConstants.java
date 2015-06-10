package Interfaces;
import java.awt.Color;

public interface UNOConstants {
	
	//Colors
	public static Color RED = new Color(192,80,77);
	public static Color BLUE = new Color(31,73,125);
	public static Color GREEN = new Color(0,153,0);
	public static Color YELLOW = new Color(255,204,0);
	
	public static Color BLACK = new Color(0,0,0);
	
	//Types
	public static int NUMBERS = 1;
	public static int ACTION = 2;
	public static int WILD = 3;
	
	//ActionCard Characters
	Character charREVERSE = (char) 8634;							//Decimal
	Character charSKIP    = (char) Integer.parseInt("2718",16); 	//Unicode
	
	//ActionCard Functions
	String REVERSE = charREVERSE.toString();
	String SKIP	= charSKIP.toString();
	String DRAW2PLUS = "2+";
	
	//Wild card functions
	String W_COLORPICKER = "W";
	String W_DRAW4PLUS = "4+";	
}
