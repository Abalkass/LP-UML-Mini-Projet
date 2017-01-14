package dao;

public class DAOFactory_XML implements I_DAOFactory {

	public DAOFactory_XML() {
		
	}
	
	@Override
	public DAO createProduitImplementantDAO() {
		// TODO Stub de la méthode généré automatiquement
		return new AdaptateurProduitDAO_XML();
	}

}
