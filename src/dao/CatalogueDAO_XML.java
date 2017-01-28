package dao;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import metier.Catalogue;
import metier.I_Catalogue;

public class CatalogueDAO_XML implements I_DAO<I_Catalogue> {
	private String uri = "Catalogues.xml";
	private Document doc;

	public CatalogueDAO_XML() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM");
		}
	}

	@Override
	public boolean create(I_Catalogue cat) {
		try {
			Element root = doc.getRootElement();
			Element catalogue = new Element("catalogue");
			catalogue.setAttribute("idCatalogue", String.valueOf(cat.getIdCatalogue()));
			Element nomCat = new Element("nomCatalogue");
			catalogue.addContent(nomCat.setText(cat.getNomCatalogue()));
			root.addContent(catalogue);
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer catalogue");
			return false;
		}
	}

	@Override
	public boolean update(I_Catalogue cat){
		return false;
	}

	@Override
	public boolean delete(I_Catalogue cat) {
		try {
			Element root = doc.getRootElement();
			Element catalogue = chercheCatalogue(cat.getIdCatalogue());
			if (catalogue != null) {
				root.removeContent(catalogue);
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur supprimer catalogue");
			return false;
		}
	}

	@Override
	public List<I_Catalogue> findAll(Integer idCat) {

		List<I_Catalogue> l = new ArrayList<I_Catalogue>();
		try {
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> lCatalogue = root.getChildren("catalogue");

			for (Element cat : lCatalogue) {
				I_Catalogue catalogue = new Catalogue(cat.getChildText("nomCatalogue"));
				catalogue.setIdCatalogue(Integer.parseInt(cat.getAttributeValue("id")));
				l.add(catalogue);
			}
		} catch (Exception e) {
			System.out.println("erreur lire Tous tous les catalogues");
		}
		return l;
	}

	private boolean sauvegarde() {
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(doc, new PrintWriter(uri));
			return true;
		} catch (Exception e) {
			System.out.println("erreur sauvegarde dans fichier XML");
			return false;
		}
	}

	private Element chercheCatalogue(int id) {
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> lCat = root.getChildren("catalogue");
		int i = 0;
		while (i < lCat.size() && !lCat.get(i).getAttributeValue("idCatalogue").equals(id))
			i++;
		if (i < lCat.size())
			return lCat.get(i);
		else
			return null;
	}

	@Override
	public I_Catalogue findByAttribute(String colonne, Object valeur) {
		Element e = chercheCatalogue(Integer.parseInt((String) valeur));
		I_Catalogue cat = new Catalogue(e.getChildText("nomCatalogue")); 
		cat.setIdCatalogue(Integer.parseInt(String.valueOf(e.getAttribute("idCatalogue"))));
		return cat;
	}

	@Override
	public int getNbTuples(String nomCat) {
		Element root = doc.getRootElement();
		return root.getChildren().size();
	}

}
