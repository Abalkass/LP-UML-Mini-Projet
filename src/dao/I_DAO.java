package dao;

import java.util.List;

public interface I_DAO<T> {

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
	public abstract boolean update(String nomT, int qte) throws QuantiteeStock_Exception;

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
	
	/**
	 * Deconnexion de la BD.
	 */
	public abstract void deconnexion();
	
}
