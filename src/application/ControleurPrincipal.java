package application;

import java.text.DecimalFormat;

import dao.DAO;
import dao.DAOFactory;
import dao.ProduitDAO;
import metier.Catalogue;
import metier.I_Produit;
import metier.Produit;

public class ControleurPrincipal {
	protected static Catalogue catalogue;
	protected static DAO<I_Produit> produitDao;
	
	public ControleurPrincipal(){
		catalogue = new Catalogue();
		/*catalogue.addProduit("Mars", 4.51651, 50);
		catalogue.addProduit("Raider", 6.78, 50);
		catalogue.addProduit("Twix", 4.99, 50);
		catalogue.addProduit("Treets", 6.99, 50);
		catalogue.addProduit("M&M's", 19.99, 50);
		catalogue.addProduit("Smarties", 12.50, 50);
		catalogue.addProduit("qshgqsf", 12.50, 50);*/
		
		produitDao = new DAOFactory().createProduitImplementantDAO();
		
		catalogue.addProduits(produitDao.find());
	}
	
	public static String[] getNomsProduits() {
		return ControleurPrincipal.catalogue.getNomProduits();
	}

}