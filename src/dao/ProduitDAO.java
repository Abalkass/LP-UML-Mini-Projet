package dao;

import java.sql.Connection;

import metier.Produit;

public class ProduitDAO extends DAO<Produit> {

	public ProduitDAO(Connection connect) {
		super(connect);
		// TODO Stub du constructeur généré automatiquement
	}

	@Override
	public boolean create(Produit obj) {
		// TODO Stub de la méthode généré automatiquement
		return false;
	}

	@Override
	public boolean update(Produit obj) {
		// TODO Stub de la méthode généré automatiquement
		return false;
	}

	@Override
	public boolean delete(Produit obj) {
		// TODO Stub de la méthode généré automatiquement
		return false;
	}

	@Override
	public Produit find(int id) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

}
