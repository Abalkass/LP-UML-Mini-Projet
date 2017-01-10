package bd;

import java.sql.*;

public class ConnexionBD {
	private final static String driver = "oracle.jdbc.driver.OracleDriver";
	private final static String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
	private final static String login = "djabiria";
	private final static String mdp = "4305006840L";
	private static Connection instanceConnection = null;
	
	public ConnexionBD() {
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
		
		try {
			instanceConnection = DriverManager.getConnection(url, login, mdp);
			
			System.out.println("Connexion à la base de données réussie !");
			
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	public static void testSQL() {
		/*String scriptSQL = "SELECT idProduit, nomProduit, quantiteStock, prixUnitaireHT FROM Produits";
		String nom;
		int num, qteStock;
		double prix;
		try {
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery(scriptSQL);
			
			if (rs.first()) {
				num = rs.getInt(1);
				nom = rs.getString(2);
				qteStock = rs.getInt(3);
				prix = rs.getDouble(4);
				
				if (qteStock==0 && rs.wasNull()) {
					qteStock = -1;
				}
				System.out.println("num : " + num + "\nnom : " + nom + "\nprix : " + prix + "\nstock : " + qteStock);
			}
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}*/
		System.out.println(instanceConnection.toString());
		System.out.println("Fin de l'éxécution");
	}
	
	public static Connection getInstance() {
		if (instanceConnection == null) {
			new ConnexionBD();
		}
		
		return instanceConnection;
	}
	
	public static void deconnexion() {
		try {
			instanceConnection.close();
			instanceConnection = null;
			System.out.println("Déconnexion à la base de données réussie !");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ConnexionBD.getInstance();
		ConnexionBD.testSQL();
		ConnexionBD.deconnexion();
	}
	
	
}

