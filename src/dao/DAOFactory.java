package dao;

import java.sql.Connection;

import bd.ConnexionBD;

public class DAOFactory {
	protected static final Connection connect = ConnexionBD.getInstance();

	/**
	 * @return un objet Produit interagissant avec la BD
	 */
	public static DAO createProduitImplementantDAO(){
		return new ProduitDAO(connect);
	}

}
