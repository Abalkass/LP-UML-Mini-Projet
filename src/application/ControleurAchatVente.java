package application;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import metier.I_Catalogue;
import metier.I_Produit;

public class ControleurAchatVente{

	public ControleurAchatVente(I_Catalogue catalogue) {
		//reste à faire de même dans tous les controleurs
		//mettre en surcharge le catalogue dans chaque appel
		//créer la bdd et faire les "fabrique" les factory/dao
		//
	}

	private static boolean testEntreeUtilisateur(String txtQte) throws Exception{
		boolean retour;
		if(txtQte.isEmpty()){
			throw new Exception("Veuillez entrer une valeur !");
		}else if(!Pattern.matches("\\d+", txtQte)){
			throw new Exception("Veuillez entrer une valeur correcte (Numérique seuleument) !");
		}else{
			if(Integer.parseInt(txtQte) <= 0){
				throw new Exception("Veuillez entrer une quantité supérieur à zéro !");
			}else if(Integer.parseInt(txtQte) > 0){
				retour = true;
			}else{
				throw new Exception("Une erreur innatendue s'est produite, veuillez réessayer...");
			}
		}
		return retour;
	}
	
	public static void achatProduit(JFrame laFrame, String nomProd, String txtQte){
		try {
			if(testEntreeUtilisateur(txtQte) == true){
				int quantitee = Integer.parseInt(txtQte);
				//CatalogueSingleton.getInstance().acheterStock(nomProd, quantitee);
				throw new Exception("Votre achat de : "+ nomProd +" (x" + quantitee + ")a bien été enregistré.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(laFrame, e.getMessage(), "Information(s) !", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void venteProduit(JFrame laFrame, String nomProd, String txtQte){
		/*try {
			if(testEntreeUtilisateur(txtQte) == true){
				int quantitee = Integer.parseInt(txtQte);
				CatalogueSingleton.getInstance();
				if(CatalogueSingleton.getInstance().vendreStock(nomProd, quantitee)){
					throw new Exception("Votre vente de : "+nomProd+" (x" + quantitee + ")a bien été enregistré.");
				}else{
					throw new Exception("Vente impossible, quantité en stock non suffisante.");
				}
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(laFrame, e.getMessage(), "Information(s) !", JOptionPane.INFORMATION_MESSAGE);
		}*/
	}
}
	