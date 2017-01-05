package graphique;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.*;

import application.ControleurAchatVente;
import application.ControleurProduit;
import metier.I_Produit;
import metier.Produit;
import tools.ProduitRenderer;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;

	public FenetreAchat(String[] strings) {

		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");
		
		combo = new JComboBox<String>();
		for (int i = 0; i < strings.length; i++) {
			combo.addItem(strings[i]);
			
		}
		
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� achet�e"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);
		
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAchat){
			ControleurAchatVente.achatProduit(this, combo.getSelectedItem().toString(), txtQuantite.getText());
			}
		}
	}