package graphique;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.*;

import application.ControleurAchat;
import metier.I_Produit;
import metier.Produit;
import tools.ProduitRenderer;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<I_Produit> combo;

	public FenetreAchat(ArrayList<I_Produit> arrayList) {

		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
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
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);
		
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAchat){
			ControleurAchat leControleur = new ControleurAchat();
			leControleur.achatProduit(this, combo, txtQuantite.getText());
			}
		}
	}