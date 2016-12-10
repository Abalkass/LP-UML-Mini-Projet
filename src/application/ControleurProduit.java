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
	
	public void achatProduit(JFrame laFrame, JComboBox jcmb, String txtQte){
		I_Produit jcmbProduit = (I_Produit) jcmb.getSelectedItem();
		try {
			if(txtQte.isEmpty()){
				throw new Exception("Veuillez entrer une valeur !");
			}else if(!Pattern.matches("\\d+", txtQte)){
				throw new Exception("Veuillez entrer une valeur correcte (Numérique seuleument) !");
			}else{
				int quantitee = Integer.parseInt(txtQte);
				if(quantitee <= 0){
					throw new Exception("Veuillez entrer une quantité supérieur à zéro !");
				}else if(quantitee > 0){
					I_Produit unProduit = (I_Produit) jcmbProduit;
					catalogue.acheterStock(unProduit.getNom(), quantitee);
					System.out.println("Achat : "+unProduit.getNom()+" -- En quantité : " + quantitee);
					JOptionPane.showMessageDialog(laFrame, "Votre achat à bien était enregistré.", "Suite du tp à venir !", JOptionPane.INFORMATION_MESSAGE);
				}else{
					throw new Exception("Une erreur innatendue s'est produite, veuillez réessayer...");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(laFrame, e.getMessage(), "Error !", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void venteProduit(JFrame laFrame, JComboBox jcmb, String txtQte){
		I_Produit jcmbProduit = (I_Produit) jcmb.getSelectedItem();
		try {
			if(txtQte.isEmpty()){
				throw new Exception("Veuillez entrer une valeur !");
			}else if(!Pattern.matches("\\d+", txtQte)){
				throw new Exception("Veuillez entrer une valeur correcte (Numérique seuleument) !");
			}else{
				int quantitee = Integer.parseInt(txtQte);
				if(quantitee <= 0){
					throw new Exception("Veuillez entrer une quantité supérieur à zéro !");
				}else if(quantitee > 0){
					I_Produit unProduit = (I_Produit) jcmbProduit;
					catalogue.vendreStock(unProduit.getNom(), quantitee);
					System.out.println("Achat : "+unProduit.getNom()+" -- En quantité : " + quantitee);
					JOptionPane.showMessageDialog(laFrame, "Votre achat à bien était enregistré.", "Suite du tp à venir !", JOptionPane.INFORMATION_MESSAGE);
				}else{
					throw new Exception("Une erreur innatendue s'est produite, veuillez réessayer...");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(laFrame, e.getMessage(), "Error !", JOptionPane.ERROR_MESSAGE);
		}
	}
	public boolean venteProduit2(I_Produit unProduit, int qte){
		return catalogue.vendreStock(unProduit.getNom(), qte);
	}

}
