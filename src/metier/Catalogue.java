package metier;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import metier.Produit;

public class Catalogue implements I_Catalogue{

	private ArrayList<I_Produit> lesProduits;

	public Catalogue() {
		lesProduits = new ArrayList<I_Produit>();
	}
	

	
	public ArrayList<I_Produit> getLesProduits(){
		return lesProduits;
	}
	
	public String toString() {
		String s = "";
		DecimalFormat df = new DecimalFormat("#0.00");
		
		for (I_Produit p : lesProduits) {
			s += p.toString();
		}
		
		s += "\n" + "Montant total TTC du stock : " + df.format(this.getMontantTotalTTC()) +" €";
		
		return s;
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		boolean estDansCatalogue = false;
		boolean retour = false;
		
		for (Iterator<I_Produit> iterator = lesProduits.iterator(); iterator.hasNext();) {
			Produit p = (Produit) iterator.next();
			if( estDansCatalogue = (p.getNom().equals(produit.getNom().replace("	", " ").trim()) ) ){
				return false;
			}
		}
		
		if (!estDansCatalogue && produit != null) {
			if((produit.getPrixUnitaireHT() != 0) && (produit.getQuantite() >= 0) && (produit.getPrixUnitaireHT() > 0)) {
				this.lesProduits.add((Produit) produit);
				retour = true;
			}
		}

		
			return retour;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		Produit unProduit = new Produit(nom.replace("	", " ").trim(), prix, qte);
		return addProduit(unProduit);
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int nbProduitsAjoutes = 0;
		if (l != null) {
			for (I_Produit p : l) {
				if (this.addProduit(p)) {
					nbProduitsAjoutes++;
				}
			}
		}
		return nbProduitsAjoutes;
	}
	
	/**
	 * @param nom
	 * @return le produit contenu dans le catalogue dont le nom est le même que celui dans le paramètre
	 */
	private I_Produit getProduitByNom(String nom) {
		for (I_Produit p : this.lesProduits) {
			if (p.getNom() == nom) {
				return p;
			}
		}
		return null;
	}
	@Override
	public boolean removeProduit(String nom) {
		I_Produit produitAsupprime = this.getProduitByNom(nom);
		if (produitAsupprime != null) {
			return this.lesProduits.remove(produitAsupprime);
		} else {
			return false;
		}
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		boolean retour = false;
		if(qteAchetee>0){
			for(Iterator<I_Produit> i = lesProduits.iterator(); i.hasNext();){
				I_Produit unProduit = i.next();
				if(unProduit.getNom() == nomProduit){
					unProduit.ajouter(qteAchetee);
					retour = true;
				}
			}
		}
		return retour;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		boolean retour = false;
		for(Iterator<I_Produit> i = lesProduits.iterator(); i.hasNext();){
			I_Produit unProduit = i.next();
			if((unProduit.getNom() == nomProduit)&&(qteVendue >0)){
				if(unProduit.getQuantite() > qteVendue){
					unProduit.enlever(qteVendue);
					retour = true;
				}
			}
		}
		return retour;
	}
	
	@Override
	public String[] getNomProduits() {
		String[] nomProduits = new String[0];
		if (lesProduits != null) {
			nomProduits = new String[lesProduits.size()];
			int index = 0;
			for(Iterator<I_Produit> i = lesProduits.iterator(); i.hasNext();){
				I_Produit unProduit = i.next();
				nomProduits[index] = unProduit.getNom();
				index++;
			}
			Arrays.sort(nomProduits);
		}
		return nomProduits;
	}

	@Override
	public double getMontantTotalTTC() {
		double total = 0;
		for(Iterator<I_Produit> i = lesProduits.iterator(); i.hasNext();){
			I_Produit unProduit = i.next();
			total = total + unProduit.getPrixStockTTC();
		}
		
		BigDecimal bd = new BigDecimal(total);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		total = bd.doubleValue();
		return total;
	}

	@Override
	public void clear() {
		lesProduits = null;
	}
}
