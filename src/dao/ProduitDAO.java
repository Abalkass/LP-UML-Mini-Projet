package dao;

import java.sql.*;
import java.util.ArrayList;

import metier.I_Produit;
import metier.Produit;

public class ProduitDAO extends DAO<I_Produit> {

	public ProduitDAO(Connection connect) {
		super(connect);
		// TODO Stub du constructeur généré automatiquement
	}

	@Override
	public boolean create(I_Produit obj) {
		String nomProduit = obj.getNom();
		int qteStock = obj.getQuantite();
		double prixHT = obj.getPrixUnitaireHT();
		
		try {
			CallableStatement cst = connect.prepareCall("{call nouveauProduit(?,?,?)}");
			cst.setString(1, nomProduit);
			cst.setInt(2, qteStock);
			cst.setDouble(3, prixHT);
			return cst.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(I_Produit obj) {
		// TODO Stub de la méthode généré automatiquement
		return false;
	}

	@Override
	public boolean delete(I_Produit obj) {
		String nomProduit = obj.getNom();
		int qteStock = obj.getQuantite();
		double prixHT = obj.getPrixUnitaireHT();
		
		try {
			PreparedStatement pst = connect.prepareStatement("DELETE FROM Produits WHERE nomProduit = ? AND quantiteStock = ? AND prixUnitaireHT = ? ;");
			pst.setString(1, nomProduit);
			pst.setInt(2, qteStock);
			pst.setDouble(3, prixHT);
			return pst.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ArrayList<I_Produit> find() {
		ArrayList<I_Produit> listProduits = new ArrayList<I_Produit>();
		I_Produit p;
		String nomProduit;
		int qteStock;
		double prixHT;
		
		try {
			PreparedStatement pst = connect.prepareStatement("SELECT nomProduit, quantiteStock, prixUnitaireHT FROM Produits;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				nomProduit = rs.getString(1);
				qteStock = rs.getInt(2);
				prixHT = rs.getDouble(3);
				p = new Produit(nomProduit, prixHT, qteStock);
				listProduits.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listProduits;
	}

}
