package dao;

import java.sql.Connection;

import bd.ConnexionBD;
import metier.I_Produit;

public class DAOFactory implements I_DAOFactory {
	private static final Connection connect = ConnexionBD.getInstance();
	
	public DAOFactory() {}

	/**
	 * @return un objet Produit interagissant avec la BD
	 */
	public I_DAO<I_Produit> createProduitImplementantDAO(){
		return new ProduitDAO(connect);
	}

}
