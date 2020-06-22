package rozwiazane_zadania.zad13;

import java.math.BigDecimal;

public class Tlumacz {

	private static final BigDecimal STO = new BigDecimal(100);

	public static int parse(String kwotaString) {
		BigDecimal kwota = new BigDecimal(kwotaString);
		kwota = kwota.multiply(STO);
		return kwota.intValue();
	}
	
	public static String print(int kwotaInt) {
		BigDecimal kwota = new BigDecimal(kwotaInt);
		kwota = kwota.divide(STO).setScale(2, BigDecimal.ROUND_HALF_UP);
		return kwota.toString();
	}
}
