package application;

import dao.I_DAO;
import graphique.FenetrePrincipale;

import java.util.ArrayList;

import dao.DAOFactory;
import dao.DAOFactory_XML;
import metier.I_Catalogue;
import metier.I_Produit;

@SuppressWarnings("unused")
public class ControleurPrincipal {
	protected static I_Catalogue catalogue;
	protected static I_DAO<I_Produit> produitDao;
	
	public ControleurPrincipal(I_Catalogue catalogueSelectionee){
		catalogue = catalogueSelectionee;
		
		//Ligne à changer pour avoir la BD relationnel
		produitDao = new DAOFactory().createProduitImplementantDAO();
		
		//Ligne à changer pour avoir la BD XML
		//produitDao = new DAOFactory_XML().createProduitImplementantDAO();
		
		catalogue.addProduits(produitDao.find());
		new FenetrePrincipale();
	}
	
	public static String[] getNomsProduits() {
		return ControleurPrincipal.catalogue.getNomProduits();
	}
	public void setCatalogue(String nom){
		
	}
	public static void deconnexion(){
		produitDao.deconnexion();
	}

}