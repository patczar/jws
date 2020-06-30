package ogloszenia.model;

public class Silnik {
	private Float moc;
	private Float pojemnosc;
	private Paliwo paliwo;
	
	public Silnik() {
	}

	public Silnik(Float moc, Float pojemnosc, Paliwo paliwo) {
		this.moc = moc;
		this.pojemnosc = pojemnosc;
		this.paliwo = paliwo;
	}

	public Float getMoc() {
		return moc;
	}

	public Float getPojemnosc() {
		return pojemnosc;
	}

	public Paliwo getPaliwo() {
		return paliwo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((moc == null) ? 0 : moc.hashCode());
		result = prime * result + ((paliwo == null) ? 0 : paliwo.hashCode());
		result = prime * result + ((pojemnosc == null) ? 0 : pojemnosc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Silnik other = (Silnik) obj;
		if (moc == null) {
			if (other.moc != null)
				return false;
		} else if (!moc.equals(other.moc))
			return false;
		if (paliwo != other.paliwo)
			return false;
		if (pojemnosc == null) {
			if (other.pojemnosc != null)
				return false;
		} else if (!pojemnosc.equals(other.pojemnosc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%.1f %s, %.0f KM", pojemnosc, paliwo, moc);
	}
}
