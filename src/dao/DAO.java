package dao;

import java.util.List;

import metier.Produit;

public abstract class DAO<T> {

	public DAO() {
		// TODO Stub du constructeur généré automatiquement
	}

	/**
	 * Enregistre l'objet obj dans la BD
	 * @param obj
	 * @return TRUE ssi l'objet T a été créé
	 */
	public abstract boolean create(T obj);

	/**
	 * Modifie les données de l'objet obj dans la BD
	 * @param obj
	 * @return  TRUE ssi l'objet T a été mis à jour
	 */
	public abstract boolean update(T obj);

	/**
	 * Supprime l'objet dont le nom = nomT dans la BD
	 * @param obj
	 * @return  TRUE ssi l'objet T a été supprimé 
	 */
	public abstract boolean delete(String nomT);

	/**
	 * @param id
	 * @return une liste de T éléments contenu dans la BD
	 */
	public abstract List<T> find();
	
}
