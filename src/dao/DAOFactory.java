package dao;

import java.sql.Connection;

import bd.ConnexionBD;

public class DAOFactory implements I_DAOFactory {
	private static final Connection connect = ConnexionBD.getInstance();
	
	public DAOFactory() {}

	/**
	 * @return un objet Produit interagissant avec la BD
	 */
	public DAO createProduitImplementantDAO(){
		return new ProduitDAO(connect);
	}

}
