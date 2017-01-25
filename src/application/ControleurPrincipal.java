package application;

import dao.I_DAO;
import dao.DAOFactory;
import dao.DAOFactory_XML;
import metier.Catalogue;
import metier.I_Produit;

@SuppressWarnings("unused")
public class ControleurPrincipal {
	protected static Catalogue catalogue;
	protected static I_DAO<I_Produit> produitDao;
	
	public ControleurPrincipal(){
		catalogue = new Catalogue();
		
		//Ligne à changer pour avoir la BD relationnel
		produitDao = new DAOFactory().createProduitImplementantDAO();
		
		//Ligne à changer pour avoir la BD XML
		//produitDao = new DAOFactory_XML().createProduitImplementantDAO();
		
		catalogue.addProduits(produitDao.find());
	}
	
	public static String[] getNomsProduits() {
		return ControleurPrincipal.catalogue.getNomProduits();
	}

}