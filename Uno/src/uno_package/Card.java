package uno_package;

public class Card {
	private String color;
	private String value;
	
	Card(String c, String v){
		setColor(c);
		setValue(v);
	}
	
	public void setColor(String c) { color = c; }
	public void setValue(String v) { value = v; }
	
	public String getColor() { return color; }
	public String getValue() { return value; }
	
	@Override
	public String toString() {
		return (getColor() + " " + getValue());
	}
	
	public int getScore() { 
		if (value.equals("0")) return 0;
		else if (value.equals("1")) return 1;
		else if (value.equals("2")) return 2;
		else if (value.equals("3")) return 3;
		else if (value.equals("4")) return 4;
		else if (value.equals("5")) return 5;
		else if (value.equals("6")) return 6;
		else if (value.equals("7")) return 7;
		else if (value.equals("8")) return 8;
		else if (value.equals("9")) return 9;
		else if (value.equals("SKIP") || value.equals("REVERSE") || 
				value.equals("DRAW2")) 
			return 20;
		else return 50; // Wilds
	}
}
