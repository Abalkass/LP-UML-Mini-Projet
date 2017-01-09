package dao;

import java.sql.Connection;

public abstract class DAO<T> {
	protected Connection connect = null;

	public DAO(Connection connect) {
		this.connect = connect;
	}

	/**
	 * Enregistre l'objet obj dans la BD
	 * @param obj
	 * @return boolean 
	 */
	public abstract boolean create(T obj);

	/**
	 * Modifie les données de l'objet obj dans la BD
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean update(T obj);

	/**
	 * Supprime l'objet obj dans la BD
	 * @param obj
	 * @return boolean 
	 */
	public abstract boolean delete(T obj);

	/**
	 * Recherche des informations d'un objet à partir de ID
	 * @param id
	 * @return T
	 */
	public abstract T find(int id);	
	
}
