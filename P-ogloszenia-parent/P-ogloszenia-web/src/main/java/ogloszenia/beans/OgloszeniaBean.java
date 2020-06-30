package ogloszenia.beans;

import java.math.BigDecimal;
import java.util.List;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.Samochodowe;

public class OgloszeniaBean {
	private BigDecimal cenaMinimalna, cenaMaksymalna;
	
	public String getCenaMinimalna() {
		if(cenaMinimalna == null)
			return null;
		else
			return cenaMinimalna.toString();
	}

	public void setCenaMinimalna(String min) {
		if(min == null)
			this.cenaMinimalna = null;
		else
			try {
				this.cenaMinimalna = new BigDecimal(min);
			} catch (Exception e) {
				this.cenaMinimalna = null;
			}
	}

	public String getCenaMaksymalna() {
		if(cenaMaksymalna == null)
			return null;
		else
			return cenaMaksymalna.toString();
	}

	public void setCenaMaksymalna(String max) {
		if(max == null)
			this.cenaMaksymalna = null;
		else
			try {
				this.cenaMaksymalna = new BigDecimal(max);
			} catch (Exception e) {
				this.cenaMaksymalna = null;
			}
	}

	public List<Samochodowe> getWszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWszystkie();
		}
	}
	
	public List<Samochodowe> getOgloszeniaWgCeny() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			
			return dao.odczytajWedlugCeny(cenaMinimalna, cenaMaksymalna);
		}
	}

}
