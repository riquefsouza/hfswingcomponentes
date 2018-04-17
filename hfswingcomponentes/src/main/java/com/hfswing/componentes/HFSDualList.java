package com.hfswing.componentes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

public class HFSDualList extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelOrigem;
	private JPanel panelDestino;
	private TitledBorder bordaOrigem;
	private TitledBorder bordaDestino;
	private JPanel botoesPanel;
	private HFSButton btnIncluirTodos;
	private HFSListBox lbOrigem;
	private HFSListBox lbDestino;
	private HFSButton btnIncluir;
	private HFSButton btnRemover;
	private HFSButton btnRemoverTodos;

	public HFSDualList() {
		setSize(new Dimension(300, 100));
		initComponents();
	}

	private void initComponents() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(getPanelOrigem());
		add(getBotoesPanel());
		add(getPanelDestino());
	}

	private JPanel getPanelOrigem() {
		if (panelOrigem == null) {
			panelOrigem = new JPanel();
			panelOrigem.setPreferredSize(new Dimension(100, 10));
			bordaOrigem = new TitledBorder(null, "Origem",
					TitledBorder.LEADING, TitledBorder.TOP, null, null);
			panelOrigem.setBorder(bordaOrigem);
			panelOrigem.setLayout(new BorderLayout(0, 0));
			panelOrigem.add(getLbOrigem());
		}
		return panelOrigem;
	}

	private JPanel getPanelDestino() {
		if (panelDestino == null) {
			panelDestino = new JPanel();
			panelDestino.setPreferredSize(new Dimension(100, 10));
			bordaDestino = new TitledBorder(null, "Destino",
					TitledBorder.LEADING, TitledBorder.TOP, null, null);
			panelDestino.setBorder(bordaDestino);
			panelDestino.setLayout(new BorderLayout(0, 0));
			panelDestino.add(getLbDestino());
		}
		return panelDestino;
	}

	private JPanel getBotoesPanel() {
		if (botoesPanel == null) {
			botoesPanel = new JPanel();
			botoesPanel.setPreferredSize(new Dimension(50, 10));
			botoesPanel.setLayout(new BoxLayout(botoesPanel, BoxLayout.Y_AXIS));
			botoesPanel.add(getBtnIncluirTodos());
			botoesPanel.add(getBtnIncluir());
			botoesPanel.add(getBtnRemover());
			botoesPanel.add(getBtnRemoverTodos());
		}
		return botoesPanel;
	}

	private HFSListBox getLbOrigem() {
		if (lbOrigem == null) {
			lbOrigem = new HFSListBox();
			lbOrigem.setMostrarRotulo(false);
			lbOrigem.getListBox().setSelectionMode(
					ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}
		return lbOrigem;
	}

	private HFSListBox getLbDestino() {
		if (lbDestino == null) {
			lbDestino = new HFSListBox();
			lbDestino.setMostrarRotulo(false);
			lbDestino.getListBox().setSelectionMode(
					ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}
		return lbDestino;
	}

	private HFSButton getBtnIncluirTodos() {
		if (btnIncluirTodos == null) {
			btnIncluirTodos = new HFSButton(">>");
			btnIncluirTodos.setEnabled(false);
			btnIncluirTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					btnIncluirTodosClick();
				}
			});
			btnIncluirTodos.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return btnIncluirTodos;
	}

	private HFSButton getBtnIncluir() {
		if (btnIncluir == null) {
			btnIncluir = new HFSButton(">");
			btnIncluir.setEnabled(false);
			btnIncluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					btnIncluirClick();
				}
			});
			btnIncluir.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return btnIncluir;
	}

	private HFSButton getBtnRemover() {
		if (btnRemover == null) {
			btnRemover = new HFSButton("<");
			btnRemover.setEnabled(false);
			btnRemover.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					btnRemoverClick();
				}
			});
			btnRemover.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return btnRemover;
	}

	private HFSButton getBtnRemoverTodos() {
		if (btnRemoverTodos == null) {
			btnRemoverTodos = new HFSButton("<<");
			btnRemoverTodos.setEnabled(false);
			btnRemoverTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					btnRemoverTodosClick();
				}
			});
			btnRemoverTodos.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return btnRemoverTodos;
	}
	
	public void setRotuloOrigem(String rotulo) {
		bordaOrigem.setTitle(rotulo);
	}

	public String getRotuloOrigem() {
		return bordaOrigem.getTitle();
	}
	
	public void setRotuloDestino(String rotulo) {
		bordaDestino.setTitle(rotulo);
	}

	public String getRotuloDestino() {
		return bordaDestino.getTitle();
	}

	private void desabilitaBotoes() {
		btnIncluir.setEnabled(false);
		btnIncluirTodos.setEnabled(false);
		btnRemover.setEnabled(false);
		btnRemoverTodos.setEnabled(false);
	}

	private void habilitaBotoes() {
		desabilitaBotoes();

		if (lbOrigem.getModelo().size() > 0) {
			btnIncluir.setEnabled(true);
			btnIncluirTodos.setEnabled(true);
		}

		if (lbDestino.getModelo().size() > 0) {
			btnRemover.setEnabled(true);
			btnRemoverTodos.setEnabled(true);
		}
	}
	
	public void removerItems() {
		desabilitaBotoes();
		lbOrigem.getModelo().clear();
		lbDestino.getModelo().clear();
	}
	
	public void setItemsOrigem(ArrayList<HFSItem> item) {
		if (item.size() > 0) {
			lbOrigem.getModelo().clear();
			
			for (HFSItem elemento : item) {
				lbOrigem.getModelo().addElement(elemento);
			}		

			lbOrigem.getListBox().setSelectedIndex(0);
			habilitaBotoes();
		}
	}
	
	public ArrayList<HFSItem> getItemsOrigem(){
		return lbOrigem.getItems();
	}

	public void setItemsDestino(ArrayList<HFSItem> item) {
		if (item.size() > 0) {
			lbDestino.getModelo().clear();
			for (HFSItem elemento : item) {
				lbDestino.getModelo().addElement(elemento);
			}			

			lbDestino.getListBox().setSelectedIndex(0);
			habilitaBotoes();
		}
	}

	public ArrayList<HFSItem> getItemsDestino(){
		return lbDestino.getItems();
	}
	
	private void moverItem(HFSListBox lbxOrigem, HFSListBox lbxDestino) {
		HFSItem elemento;

		for (int i = 0; i < lbxOrigem.getModelo().size(); i++) {
			if (lbxOrigem.getListBox().isSelectedIndex(i)) {
				elemento = lbxOrigem.getModelo().get(i);
				lbxDestino.getModelo().addElement(elemento);
			}
		}
		for (int i = (lbxOrigem.getModelo().size() - 1); i >= 0; i--) {
			if (lbxOrigem.getListBox().isSelectedIndex(i)) {
				lbxOrigem.getModelo().remove(i);
			}
		}
	}

	private void moverTodosItems(HFSListBox lbxOrigem, HFSListBox lbxDestino) {
		HFSItem elemento;

		for (int i = 0; i < lbxOrigem.getModelo().size(); i++) {
			elemento = lbxOrigem.getModelo().get(i);
			lbxDestino.getModelo().addElement(elemento);
		}
		lbxOrigem.getModelo().clear();
	}

	public void btnIncluirClick() {
		moverItem(lbOrigem, lbDestino);
		habilitaBotoes();
	}

	public void btnIncluirTodosClick() {
		moverTodosItems(lbOrigem, lbDestino);
		habilitaBotoes();
	}

	public void btnRemoverTodosClick() {
		moverTodosItems(lbDestino, lbOrigem);
		habilitaBotoes();
	}

	public void btnRemoverClick() {
		moverItem(lbDestino, lbOrigem);
		habilitaBotoes();
	}
	
}
