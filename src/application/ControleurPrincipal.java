package application;

import java.text.DecimalFormat;

import metier.Catalogue;
import metier.Produit;

public class ControleurPrincipal {
	public static Catalogue catalogue;
	
	public ControleurPrincipal(){
		catalogue = new Catalogue();
		catalogue.addProduit("Mars", 4.5, 50);
		catalogue.addProduit("Raider", 6.78, 50);
		catalogue.addProduit("Twix", 4.99, 50);
		catalogue.addProduit("Treets", 6.99, 50);
		catalogue.addProduit("M&M's", 19.99, 50);
		catalogue.addProduit("Smarties", 12.50, 50);
		catalogue.addProduit("qshgqsf", 12.50, 50);
	}
	
	public static void main(String[] args) {
		Catalogue cat = new Catalogue();
/*
		DecimalFormat df = new DecimalFormat("#0.00");
		double x = 7.00546;
		System.out.println("x=" + df.format(x));

		String s = "Kit Kat jkG ";
		s = s.trim();
		System.out.println(s + s.equals("Kit Kat jkG"));
*/		
		cat.addProduit("Mars	", 10, 5);
		cat.addProduit("	Treets", 10, 4);
		cat.addProduit("Kit	Kat", 1, 10);
		
		System.out.println(cat.toString());
		
		System.out.println("\n");
		
		System.out.println("Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5" + "\n" +
				 "Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4" + "\n" +
				 "Kit Kat - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10" + "\n" +
				 "\n" +
				 "Montant total TTC du stock : 120,00 €");

	}

}