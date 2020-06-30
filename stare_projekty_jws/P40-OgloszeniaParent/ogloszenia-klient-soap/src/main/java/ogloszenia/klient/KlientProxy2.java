package ogloszenia.klient;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ogloszenia.exn.BladAplikacji;
import ogloszenia.model.OgloszenieSamochodowe;
import ogloszenia.soap.ISerwisOgloszeniowy;

public class KlientProxy2 {

	public static void main(String[] args) {
		
		int id = Integer.parseInt(JOptionPane.showInputDialog("Podaj ID ogłoszenia"));
		
		SerwisOgloszeniowyService service = new SerwisOgloszeniowyService();
		ISerwisOgloszeniowy port = service.getSerwisOgloszeniowyPort();

		try {
			OgloszenieSamochodowe ogloszenie = port.jednoOgloszenie(id);
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			JLabel label = new JLabel(ogloszenie.getMarka() + " " + ogloszenie.getModel() + " rocznik " + ogloszenie.getRocznik());
			label.setFont(new Font("Arial", Font.BOLD, 28));
			label.setForeground(Color.BLUE);
			panel.add(label);
			panel.add(Box.createVerticalStrut(10));
			label = new JLabel(ogloszenie.getTytul());
			label.setFont(new Font("Arial", Font.BOLD, 28));
			panel.add(label);
			panel.add(Box.createVerticalStrut(10));
			label = new JLabel(ogloszenie.getOpis());
			label.setFont(new Font("Arial", Font.PLAIN, 22));
			label.setForeground(Color.RED);
			panel.add(label);
			panel.add(Box.createVerticalStrut(10));
			
			try {
				byte[] foto = port.foto(id);
				BufferedImage image = ImageIO.read(new ByteArrayInputStream(foto));
				ImageIcon icon = new ImageIcon(image);
				panel.add(new JLabel(icon));
				
			} catch (Exception e) {
				System.err.println("Nie da rady wczytać zdjęcia, " + e);
			}
			JOptionPane.showMessageDialog(null, panel);
			
			
		} catch (BladAplikacji e) {
			e.printStackTrace();
		}
	}
}
