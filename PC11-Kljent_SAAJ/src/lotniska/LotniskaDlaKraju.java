package lotniska;

import javax.swing.JOptionPane;

public class LotniskaDlaKraju {
	public static void main(String[] args) {
		//ObslugaSerwisu.wypiszLotniskaDlaKraju("Poland");
		
		while(true) {
			String kraj = JOptionPane.showInputDialog("Jaki kraj?");
			if(kraj == null)
				break;
			ObslugaSerwisu.wypiszLotniskaDlaKraju(kraj);
		}
	}
}
