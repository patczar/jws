package serwlety.przyklady;

public class LogikaKalkulatora {
	
	public static int oblicz(int x, int y, String operacja) {
		switch(operacja) {
		case "+": return x + y;
		case "-": return x - y;
		case "*": return x * y;
		case "/": return x / y;
		default: throw new IllegalArgumentException("Niepoprawna operacja");
		}
	}

}
