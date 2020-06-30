package waluty.konwersje;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import wersja_jaxb.model.Rate;
import wersja_jaxb.model.Rates;

public class RatesAdapter extends XmlAdapter<Rates, Map<String,Rate>> {

	@Override
	public Map<String, Rate> unmarshal(Rates rates) throws Exception {
		Map<String, Rate> mapa = new TreeMap<>();
		for(Rate rate : rates.rates) {
			mapa.put(rate.getCode(), rate);
		}
		return mapa;
	}

	@Override
	public Rates marshal(Map<String, Rate> mapa) throws Exception {
		Rates rates = new Rates();
		rates.rates = new ArrayList<>(mapa.values());
		return rates;
	}
}
