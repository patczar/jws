package serwlety.beans;

public class KalkulatorBean {
	private double x, y;
	private String operacja;
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String getOperacja() {
		return operacja;
	}
	public void setOperacja(String operacja) {
		this.operacja = operacja;
	}
	
	public double getWynik() {
		if(operacja == null) {
			return 0;
		} else switch(operacja.trim()) {
		case "+" : return x + y;
		case "-" : return x - y;
		case "*" : return x * y;
		case "/" : return x / y;
		
		default  : return 0;
		}
	}
}
