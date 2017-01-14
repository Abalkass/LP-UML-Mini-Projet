package dao;

import java.util.ArrayList;
import java.util.List;

import metier.I_Produit;

public class AdaptateurProduitDAO_XML extends DAO {
	
	private ProduitDAO_XML prod_XML;

	public AdaptateurProduitDAO_XML() {
		super();
		prod_XML = new ProduitDAO_XML();
	}

	@Override
	public boolean create(Object obj) {
		// TODO Stub de la méthode généré automatiquement
		return prod_XML.creer((I_Produit) obj);
	}

	@Override
	public boolean update(Object obj) {
		// TODO Stub de la méthode généré automatiquement
		return prod_XML.maj((I_Produit) obj);
	}

	@Override
	public boolean delete(String nomT) {
		// TODO Stub de la méthode généré automatiquement
		return false;
	}

	@Override
	public List<I_Produit> find() {
		// TODO Stub de la méthode généré automatiquement
		return prod_XML.lireTous();
	}

}
