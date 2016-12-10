package graphique;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.*;

import application.ControleurProduit;
import metier.I_Produit;
import metier.Produit;
import tools.ProduitRenderer;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<I_Produit> combo;

	public FenetreVente(ArrayList<I_Produit> arrayList) {
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<I_Produit>();
		
		combo.setRenderer(new ProduitRenderer());
		
		for(Iterator<I_Produit> i = arrayList.iterator(); i.hasNext();){
			I_Produit unProduit = i.next();
			combo.addItem(unProduit);
		}
		
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btVente){
			ControleurProduit leControleur = new ControleurProduit();
			leControleur.venteProduit(this, combo, txtQuantite.getText());
		}
	}

}
