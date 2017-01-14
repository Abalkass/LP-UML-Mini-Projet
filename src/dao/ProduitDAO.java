package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import metier.I_Produit;
import metier.Produit;

public class ProduitDAO extends DAO<I_Produit> {
	
	private Connection connect;
	
	public ProduitDAO() {
		super();
		this.connect = null;
	}
	
	public ProduitDAO(Connection connect) {
		super();
		this.connect = connect;
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
			cst.execute();
			return true;
			
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
	public boolean delete(String nomProduit) {
		
		try {
			PreparedStatement pst = connect.prepareStatement("DELETE FROM Produits WHERE nomProduit = ?");
			pst.setString(1, nomProduit);
			pst.execute();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<I_Produit> find() {
		List<I_Produit> listProduits = new ArrayList<I_Produit>();
		I_Produit p;
		String nomProduit;
		int qteStock;
		double prixHT;
		
		try {
			PreparedStatement pst = connect.prepareStatement("SELECT nomProduit, quantiteStock, prixUnitaireHT FROM Produits");
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
