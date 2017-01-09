package BD;

import java.sql.*;

public class ConnexionBD {
	private final static String driver = "oracle.jdbc.driver.OracleDriver";
	private final static String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
	private final static String login = "djabiria";
	private final static String mdp = "4305006840L";
	private Connection cn;
	
	private static ConnexionBD instance = null;
	
	public ConnexionBD() {
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
		
		try {
			cn = DriverManager.getConnection(url, login, mdp);
			
			System.out.println("Connexion à la base de données réussie !");
			
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	public synchronized final static ConnexionBD getInstance() {
		if (ConnexionBD.instance == null) {
			ConnexionBD.instance = new ConnexionBD();
		}
		
		return ConnexionBD.instance;
	}
	
	public void deconnexion() {
		try {
			cn.close();
			System.out.println("Déconnexion à la base de données réussie !");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}

