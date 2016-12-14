package application;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControleurProduit extends ControleurPrincipal{
	
	public static boolean ajouterProduit(String nomProduit, String prixProduit, String qteProduit, JFrame maFrame) {
		if (nomProduit==null) {
			JOptionPane.showMessageDialog(maFrame, "Tous les champs doivent être saisi.", "Erreur", JOptionPane.WARNING_MESSAGE);
			return false;
		} else {
			try {
				double prix = Double.parseDouble(prixProduit);
				int qte = Integer.parseInt(qteProduit);

				JOptionPane.showMessageDialog(maFrame, "Le produit " + nomProduit + " a été bien enregistré.", "Ajout produit", JOptionPane.INFORMATION_MESSAGE);
				return catalogue.addProduit(nomProduit, prix, qte);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(maFrame, "La quantité et le prix doivent être un entier positif.", "Erreur", JOptionPane.WARNING_MESSAGE);
				System.err.println("La quantité et le prix doivent être un entier positif.");
				System.out.println("nom="+nomProduit);
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(maFrame, "Tous les champs doivent être saisi.", "Erreur", JOptionPane.WARNING_MESSAGE);
				System.err.println("Tous les champs doivent être saisi.");
				System.out.println("nom="+nomProduit);
			}
		}
		
		
		
		return false;
	}
	
}
