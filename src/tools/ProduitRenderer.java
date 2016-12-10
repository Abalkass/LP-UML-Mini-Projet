package tools;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import metier.Produit;

public class ProduitRenderer implements ListCellRenderer {
	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
	private final static Dimension preferredSize = new Dimension(0,20);

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, 
				cellHasFocus);
		if(value instanceof Produit){
			renderer.setText(((Produit)value).getNom());
		}
		renderer.setPreferredSize(preferredSize);
		return renderer;
	}

}
