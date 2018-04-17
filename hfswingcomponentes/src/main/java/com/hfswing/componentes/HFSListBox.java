package com.hfswing.componentes;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

public class HFSListBox extends HFSLabel {

	private static final long serialVersionUID = 1L;
	private JList<HFSItem> listBox;
	private DefaultListModel<HFSItem> modelo;
	private JScrollPane scrollPane;
	
	private boolean selecaoMultipla;

	public HFSListBox() {
		super();
		setSize(new Dimension(100, 100));
		this.modelo = new DefaultListModel<HFSItem>();
		add(getScrollPane());
	}

	public HFSListBox(PosicaoRotulo posicao, String rotulo, int largura,
			boolean selecaoMultipla, boolean obrigatorio) {
		super(posicao, rotulo, largura, obrigatorio);
		this.modelo = new DefaultListModel<HFSItem>();
		this.selecaoMultipla = selecaoMultipla;
		initComponents();
	}

	private void initComponents() {
		add(getScrollPane());
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getListBox());
		}
		return scrollPane;
	}

	public JList<HFSItem> getListBox() {
		if (listBox == null) {
			listBox = new JList<HFSItem>();
			listBox.addFocusListener(mudarCorFoco());
			// listBox.addListSelectionListener(new ListSelectionListener() {
			// public void valueChanged(ListSelectionEvent e) {
			// }
			// });
			if (selecaoMultipla)
				listBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			else
				listBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listBox.setModel(modelo);
		}
		return listBox;
	}

	public void valorSelecionadoMudou(ListSelectionEvent evt) {
		if (!evt.getValueIsAdjusting()) {
			int sel = this.getListBox().getSelectedIndex();
			if (sel != -1) {
				scrollRectToVisible(this.getListBox().getCellBounds(sel, sel));
			}
		}
	}

	public void setHabilitado(boolean habilitado) {
		super.setHabilitado(habilitado);
		this.getListBox().setEnabled(habilitado);
	}

	public void setFocado(boolean focado) {
		super.setFocado(focado);
		this.getListBox().setFocusable(focado);
	}

	public void setItems(ArrayList<HFSItem> items) {
		if (items.size() > 0) {
			modelo.clear();
			for (HFSItem item : items) {
				modelo.addElement(item);
			}
			this.getListBox().setSelectedIndex(0);
		}
	}

	public ArrayList<HFSItem> getItems() {
		ArrayList<HFSItem> items = new ArrayList<HFSItem>();

		while (modelo.elements().hasMoreElements()) {
			items.add(modelo.elements().nextElement());
		}

		return items;
	}

	public HFSItem getItemSelecionado() {
		int indice = this.getListBox().getSelectedIndex();
		if (indice == -1)
			return new HFSItem();
		else
			return this.modelo.get(indice);
	}

	public int getIndiceSelecionado() {
		return this.getListBox().getSelectedIndex();
	}

	public void setIndiceSelecionado(int indice) {
		this.getListBox().setSelectedIndex(indice);
	}

	public void limpar() {
		modelo.clear();
	}

	public void addItem(HFSItem item) {
		modelo.addElement(item);
	}

	public boolean existe(String valor) {
		if (modelo.size() > 0) {
			while (modelo.elements().hasMoreElements()) {
				if (modelo.elements().nextElement().getValor().equals(valor)) {
					return true;
				}
			}
		}
		return false;
	}

	public void removeItem(HFSItem item) {
		for (int i = (this.modelo.size() - 1); i >= 0; i--) {
			if (this.modelo.get(i).getId().equals(item.getId())) {
				modelo.remove(i);
			}
		}
	}

	public void removeItem(int indice) {
		modelo.remove(indice);
	}

	public void removeSelecionados() {
		for (int i = (this.getListBox().getModel().getSize() - 1); i >= 0; i--) {
			if (this.getListBox().getSelectedIndex() == i) {
				modelo.remove(i);
			}
		}
	}

	public int getQtdItemsVisiveis() {
		return listBox.getVisibleRowCount();
	}

	public void setQtdItemsVisiveis(int qtdItemsVisiveis) {
		listBox.setVisibleRowCount(qtdItemsVisiveis);
	}

	public DefaultListModel<HFSItem> getModelo() {
		return (DefaultListModel<HFSItem>) this.getListBox().getModel();
	}
}
