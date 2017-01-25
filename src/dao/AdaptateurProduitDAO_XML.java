package dao;

import java.util.List;

import metier.I_Produit;

public class AdaptateurProduitDAO_XML implements I_DAO<I_Produit> {
	
	private ProduitDAO_XML prod_XML;

	public AdaptateurProduitDAO_XML() {
		super();
		prod_XML = new ProduitDAO_XML();
	}

	@Override
	public boolean create(I_Produit obj) {
		return prod_XML.creer((I_Produit) obj);
	}

	@Override
	public boolean update(String nom, int qte) throws QuantiteeStock_Exception {
		I_Produit p = prod_XML.lire(nom);
		if((p.getQuantite() + qte)<0){
			throw (new QuantiteeStock_Exception("Pas assez de stock."));
		}
		p.ajouter(qte);
		return prod_XML.maj(p);
	}

	@Override
	public boolean delete(String nomT) {
		I_Produit p = prod_XML.lire(nomT);
		return prod_XML.supprimer(p);
	}

	@Override
	public List<I_Produit> find() {
		return prod_XML.lireTous();
	}

	@Override
	public void deconnexion() {
		return;
	}

}
