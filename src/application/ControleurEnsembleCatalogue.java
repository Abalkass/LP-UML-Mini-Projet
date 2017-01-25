package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import metier.Catalogue;
import metier.I_Catalogue;

public class ControleurEnsembleCatalogue {
	private static  List<I_Catalogue> lesCatalogues;
	private static ControleurEnsembleCatalogue instance = null;
	
	private ControleurEnsembleCatalogue(){
		lesCatalogues = new ArrayList<I_Catalogue>();
		I_Catalogue formacia = new Catalogue("Formacia");
		formacia.addProduit("Mars", 4.5, 50);
		formacia.addProduit("Raider", 6.78, 50);
		formacia.addProduit("Twix", 4.99, 50);
		
		I_Catalogue redoutable = new Catalogue("redoutable");
		redoutable.addProduit("Treets", 6.99, 50);
		redoutable.addProduit("M&M's", 19.99, 50);
		redoutable.addProduit("Smarties", 12.50, 50);
		redoutable.addProduit("qshgqsf", 12.50, 50);
		lesCatalogues.add(formacia);
		lesCatalogues.add(redoutable);
	}
	
	public static synchronized ControleurEnsembleCatalogue getInstance(){
		if(instance == null){
			instance = new ControleurEnsembleCatalogue();
		}
		return instance;
	}
	public String[] getLesNomsCatalogues(){
		String[] nomsCatalogues = new String[0];
		if (lesCatalogues != null) {
			nomsCatalogues = new String[lesCatalogues.size()];
			int index = 0;
			for(Iterator<I_Catalogue> i = lesCatalogues.iterator(); i.hasNext();){
				I_Catalogue unCata = i.next();
				nomsCatalogues[index] = unCata.getNomCatalogue();
				index++;
			}
			Arrays.sort(nomsCatalogues);
		}
		return nomsCatalogues;
	}
	public String[] getLesDetailsCatalogues(){
		String[] detailsCatalogues = new String[0];
		if (lesCatalogues != null) {
			detailsCatalogues = new String[lesCatalogues.size()];
			int index = 0;
			for(Iterator<I_Catalogue> i = lesCatalogues.iterator(); i.hasNext();){
				I_Catalogue unCata = i.next();
				detailsCatalogues[index] = unCata.getNomCatalogue() + " : " + unCata.getNomProduits().length + " produits.";
				index++;
			}
			Arrays.sort(detailsCatalogues);
		}
		return detailsCatalogues;
	}
	public int getNbCatalogues(){
		return lesCatalogues.size();
	}
	public List<I_Catalogue> getLesCatalogues(){
		return lesCatalogues;
	}
	private I_Catalogue getUnCatalogue(String nomCat){
		for (Iterator<I_Catalogue> i = lesCatalogues.iterator(); i.hasNext();) {
			I_Catalogue i_Catalogue = (I_Catalogue) i.next();
			if(i_Catalogue.getNomCatalogue() == nomCat){
				return i_Catalogue;
			}
		}
		return null;
	}
	public boolean addCatalogue(String nomCat){
		if(!estDansEnsembleCatalogue(nomCat)){
			return lesCatalogues.add(new Catalogue(nomCat));
		}
		return false;
	}

	private boolean estDansEnsembleCatalogue(String nomCat) {
		for (Iterator<I_Catalogue> i = lesCatalogues.iterator(); i.hasNext();) {
			I_Catalogue i_Catalogue = (I_Catalogue) i.next();
			if(i_Catalogue.getNomCatalogue() == nomCat){
				return true;
			}
		}
		return false;
	}
	public boolean deleteCatalogue(String nomCat){
		I_Catalogue cat = getUnCatalogue(nomCat);
		if(cat != null){
			return lesCatalogues.remove(cat);
		}
		return false;
	}

	public void afficheFenetre(String texteSelection) {
		I_Catalogue c = getUnCatalogue(texteSelection);
		if(c != null){
			new ControleurPrincipal(c);
		}
	}
}
