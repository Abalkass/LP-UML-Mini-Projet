package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class ProduitDAO implements I_DAO<I_Produit> {
	
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
		String nomCatalogueProduit = obj.getCatalogue().getNomCatalogue();
		
		try {
			CallableStatement cst = connect.prepareCall("{call nouveauProduit(?,?,?,?)}");
			cst.setString(1, nomProduit);
			cst.setInt(2, qteStock);
			cst.setDouble(3, prixHT);
			cst.setString(4, nomCatalogueProduit);
			cst.execute();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(I_Produit produit) throws QuantiteeStock_Exception {
		I_Produit p = findByAttribute("nomProduit", produit.getNom());
		
		if((p.getQuantite() + produit.getQuantite())<0){
			throw (new QuantiteeStock_Exception("Pas assez de stock."));
		}
		p.ajouter(produit.getQuantite());
		try {
			PreparedStatement pst = connect.prepareStatement("UPDATE Produits SET quantiteStock = ?  WHERE nomProduit = ?");
			pst.setInt(1, produit.getQuantite());
			pst.setString(2, produit.getNom());
			pst.execute();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(I_Produit produit) {
		String nomProduit = produit.getNom();
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
	public List<I_Produit> findAll(Integer idCat) {
		List<I_Produit> listProduits = new ArrayList<I_Produit>();
		I_Produit p;
		String nomProduit;
		int qteStock;
		double prixHT;
		I_Catalogue catalogue;
		
		try {
			PreparedStatement pst = connect.prepareStatement("SELECT nomProduit, quantiteStock, prixUnitaireHT, idCatalogue FROM Produits where idCatalogue = ?");
			pst.setInt(1, idCat);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				nomProduit = rs.getString(1);
				qteStock = rs.getInt(2);
				prixHT = rs.getDouble(3);
				catalogue = this.findCatalogue(rs.getInt(4));
				p = new Produit(nomProduit, prixHT, qteStock, catalogue);
				listProduits.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listProduits;
	}
	@Override
	public I_Produit findByAttribute(String colonne, Object valeur){
		String nomProduit;
		int qteStock;
		double prixHT;
		I_Catalogue catalogue;
		try {
			PreparedStatement pst = connect.prepareStatement("SELECT nomProduit, quantiteStock, prixUnitaireHT, idCatalogue FROM Produits WHERE ? = ?");
			pst.setString(1, colonne);
			pst.setObject(2, valeur);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				nomProduit = rs.getString(1);
				qteStock = rs.getInt(2);
				prixHT = rs.getInt(3);
				catalogue = this.findCatalogue(rs.getInt(4));
				return new Produit(nomProduit, prixHT, qteStock, catalogue);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	private I_Catalogue findCatalogue(int id){
		int idCatalogue;
		String nomCatalogue;
		try {
			PreparedStatement pst = connect.prepareStatement("SELECT idCatalogue, nomCatalogue FROM Catalogue WHERE idCatalogue = ?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				idCatalogue = rs.getInt(1);
				nomCatalogue = rs.getString(2);
				I_Catalogue c = new Catalogue(nomCatalogue);
				c.setIdCatalogue(idCatalogue);
				return c;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * @param le nom du catalogue concerné
	 * @return le nombre de produits contenus dans un catalogue
	 */
	@Override
	public int getNbTuples(String nomCatalogue) {
		try {
			PreparedStatement pst = connect.prepareStatement("SELECT count(*) FROM Produits p JOIN Catalogue c ON c.idCatalogue = p.idCatalogue WHERE nomCatalogue = ?");
			pst.setString(1, nomCatalogue);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
