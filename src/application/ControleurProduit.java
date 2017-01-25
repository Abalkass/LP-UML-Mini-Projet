package application;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class ControleurProduit extends ControleurPrincipal{
	
	public ControleurProduit(I_Catalogue catalogueSelectionee) {
		super(catalogueSelectionee);
	}

	public static boolean ajouterProduit(String nomProduit, String prixProduit, String qteProduit, JFrame maFrame) {
		if (nomProduit==null) {
			JOptionPane.showMessageDialog(maFrame, "Tous les champs doivent être saisi.", "Erreur", JOptionPane.WARNING_MESSAGE);
			return false;
		} else {
			try {
				double prix = Double.parseDouble(prixProduit);
				int qte = Integer.parseInt(qteProduit);
				I_Produit p = new Produit(nomProduit, prix, qte);
				
				catalogue.addProduit(p);
				
				JOptionPane.showMessageDialog(maFrame, "Le produit " + nomProduit + " a été bien enregistré.", "Ajout produit", JOptionPane.INFORMATION_MESSAGE);
				return produitDao.create(p);
				
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
	
	public static boolean supprimerProduit(String nomProduitASupprimer) {
		if (nomProduitASupprimer==null) {
			System.err.println("Aucun produit sélectionner");
			return false;
		} else {
			return produitDao.delete(nomProduitASupprimer) && catalogue.removeProduit(nomProduitASupprimer);
		}
	}
	
}
