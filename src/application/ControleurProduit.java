package application;

import graphique.FenetreAchat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import metier.I_Produit;

public class ControleurProduit extends ControleurPrincipal{
	
	public ControleurProduit(){}
	
	public ArrayList<I_Produit> getLesProduits(){
		return catalogue.getLesProduits();
	}
	public void afficherAchatVente(){
		new FenetreAchat(catalogue.getNomProduits());
	}
	private boolean testEntreeUtilisateur(I_Produit selectedProduit, String txtQte) throws Exception{
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
	
	public void achatProduit(JFrame laFrame, JComboBox jcmb, String txtQte){
		I_Produit selectedProduit = (I_Produit) jcmb.getSelectedItem();
		try {
			if(testEntreeUtilisateur(selectedProduit, txtQte) == true){
				int quantitee = Integer.parseInt(txtQte);
				catalogue.acheterStock(selectedProduit.getNom(), quantitee);
				throw new Exception("Votre achat de : "+selectedProduit.getNom()+" (x" + quantitee + ")a bien été enregistré.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(laFrame, e.getMessage(), "Information(s) !", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void venteProduit(JFrame laFrame, JComboBox jcmb, String txtQte){
		I_Produit selectedProduit = (I_Produit) jcmb.getSelectedItem();
		try {
			if(testEntreeUtilisateur(selectedProduit, txtQte) == true){
				int quantitee = Integer.parseInt(txtQte);
				catalogue.vendreStock(selectedProduit.getNom(), quantitee);
				throw new Exception("Votre vente de : "+selectedProduit.getNom()+" (x" + quantitee + ")a bien été enregistré.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(laFrame, e.getMessage(), "Information(s) !", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
