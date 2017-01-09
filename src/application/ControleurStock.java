package application;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import metier.I_Produit;
import metier.Produit;

public class ControleurStock extends ControleurPrincipal{

	public ArrayList<String> afficherStock(){
		DecimalFormat f = new DecimalFormat();
		f.setMaximumFractionDigits(2);
		ArrayList<String> affichageStock = new ArrayList<String>();
		for(Iterator<I_Produit> i = catalogue.getLesProduits().iterator(); i.hasNext();){
			I_Produit unProduit = i.next();
			String ligneProduit = unProduit.getNom() + 
					" Prix HT : " + String.valueOf(f.format(unProduit.getPrixUnitaireHT())) + 
					" Prix TTC : " + String.valueOf(f.format(unProduit.getPrixUnitaireTTC())) +
					" Quantité en stock : " + String.valueOf(f.format(unProduit.getQuantite()));
			affichageStock.add(ligneProduit);
		}
		affichageStock.add("Montant total du stock = " +  String.valueOf(f.format(catalogue.getMontantTotalTTC())) + " € (TTC)");
		return affichageStock;
	}
}
