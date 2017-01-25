package dao;

import metier.I_Produit;

public class DAOFactory_XML implements I_DAOFactory {

	public DAOFactory_XML() {
		
	}
	
	@Override
	public I_DAO<I_Produit> createProduitImplementantDAO() {
		return new AdaptateurProduitDAO_XML();
	}

}
