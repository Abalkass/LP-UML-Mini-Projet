package metier;

import java.util.List;

public interface I_Catalogue {

	public abstract boolean addProduit(I_Produit produit);
	
	public abstract int getIdCatalogue();
	public abstract void setIdCatalogue(int idCatalogue);
	
	public abstract boolean addProduit(String nom, double prix, int qte);
	public abstract int addProduits(List<I_Produit> l);
	public abstract boolean removeProduit(String nom);
	public abstract I_Produit getProduitByNom(String nom);
	public abstract boolean acheterStock(String nomProduit, int qteAchetee);
	public abstract boolean vendreStock(String nomProduit, int qteVendue);
	public abstract String[] getNomProduits();
	public abstract String getNomCatalogue();
	public abstract void setNomCatalogue(String nom);
	public abstract double getMontantTotalTTC();
	public abstract String toString();

	public abstract void clear();

}