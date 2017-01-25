package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestBD {
	private Connection connect;

	public TestBD() {
		connect = ConnexionBD.getInstance();
	}
	
	public void testSQL() {
		String scriptSQL = "SELECT idProduit, nomProduit, quantiteStock, prixUnitaireHT FROM Produits";
		String nom;
		int num, qteStock;
		double prix;
		try {
			Statement st = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
		}
		System.out.println("Fin de l'éxécution");
	}
	
	public static void main(String[] args) {
		TestBD test = new TestBD();
		test.testSQL();
		ConnexionBD.deconnexion();
	}
}
