package com.hfswing.componentes;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class HFSComboBox extends HFSLabel {

	private static final long serialVersionUID = 1L;
	private JComboBox<HFSItem> comboBox;
	private DefaultComboBoxModel<HFSItem> modelo;

	public HFSComboBox() {
		super();
		setSize(new Dimension(100, 35));
		this.modelo = new DefaultComboBoxModel<HFSItem>();
		add(getComboBox());
	}

	public HFSComboBox(PosicaoRotulo posicao, String rotulo, int largura,
			boolean obrigatorio) {
		super(posicao, rotulo, largura, obrigatorio);		
		this.modelo = new DefaultComboBoxModel<HFSItem>();
		initComponents();
	}

	private void initComponents() {
		add(getComboBox());
	}

	public JComboBox<HFSItem> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<HFSItem>();
			comboBox.addFocusListener(mudarCorFoco());
			comboBox.setModel(modelo);
		}
		return comboBox;
	}

	public void setHabilitado(boolean habilitado) {
		super.setHabilitado(habilitado);
		this.getComboBox().setEnabled(habilitado);
	}

	public void setFocado(boolean focado) {
		super.setFocado(focado);
		this.getComboBox().setFocusable(focado);
	}

	public void setItems(ArrayList<HFSItem> items) {
		if (items.size() > 0) {
			modelo.removeAllElements();
			for (HFSItem item : items) {
				modelo.addElement(item);
			}
			this.getComboBox().setSelectedIndex(0);
		}
	}

	public ArrayList<HFSItem> getItems() {
		ArrayList<HFSItem> items = new ArrayList<HFSItem>();

		for (int i = 0; i < modelo.getSize(); i++) {
			items.add(modelo.getElementAt(i));
		}

		return items;
	}

	public HFSItem getItemSelecionado() {
		int indice = this.getComboBox().getSelectedIndex();
		if (indice == -1)
			return new HFSItem();
		else
			return this.modelo.getElementAt(indice);
	}

	public int getIndiceSelecionado() {
		return this.getComboBox().getSelectedIndex();
	}

	public void setIndiceSelecionado(int indice) {
		this.getComboBox().setSelectedIndex(indice);
	}

	public void limpar() {
		modelo.removeAllElements();
	}

	public void addItem(HFSItem item) {
		modelo.addElement(item);
	}

	public boolean existe(String valor) {
		if (modelo.getSize() > 0) {
			for (int i = 0; i < modelo.getSize(); i++) {
				if (modelo.getElementAt(i).getValor().equals(valor)) {
					return true;
				}
			}
		}
		return false;
	}

	public void removeItem(HFSItem item) {
		for (int i = (this.modelo.getSize() - 1); i >= 0; i--) {
			if (this.modelo.getElementAt(i).getId().equals(item.getId())) {
				modelo.removeElementAt(i);
			}
		}
	}

	public void removeItem(int indice) {
		modelo.removeElementAt(indice);
	}

	public void removeSelecionados() {
		for (int i = (this.getComboBox().getModel().getSize() - 1); i >= 0; i--) {
			if (this.getComboBox().getSelectedIndex() == i) {
				modelo.removeElementAt(i);
			}
		}
	}

	public DefaultComboBoxModel<HFSItem> getModelo() {
		return (DefaultComboBoxModel<HFSItem>) this.getComboBox().getModel();
	}
}
