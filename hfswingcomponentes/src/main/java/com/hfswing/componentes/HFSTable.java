package com.hfswing.componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class HFSTable extends JTable {
	private static final long serialVersionUID = 7875156411342009309L;

	private boolean sombrearLinhas;

	private boolean sombrearColunas;

	public HFSTable(DefaultTableModel dtm, JProgressBar barraProgresso) {
		super();
		this.sombrearLinhas = false;
		this.sombrearColunas = false;

		this.setModel(dtm);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setFillsViewportHeight(true);
		acomodaColunas(this, 10);
		acomodaLinhas(this, 0);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = -1139006797853979939L;

			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {

				// if (column == 0) {
				// setVisible(false);
				// String nome = (String) getValueAt(row, 0);
				// String tipo = (String) getValueAt(row, 2);
				// //setIconePorExtensao(this, row, nome.toLowerCase(), tipo);
				// }
				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
			}

		};
		this.getColumnModel().getColumn(0).setCellRenderer(dtcr);

		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mouseClicou(e);
			}
		});

		this.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent evt) {
						selecaoValorMudou(evt);
					}
				});

		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(
				dtm);
		this.setRowSorter(sorter);
	}

	public boolean isCellEditable(int rowIndex, int vColIndex) {
		return false;
	}

	private void selecaoValorMudou(ListSelectionEvent evt) {
		// this.scrollRectToVisible(this.getCellRect(this.getSelectedRow(), 0,
		// true));
		scrollToCenter(this.getSelectedRow(), 0);
	}

	private void mouseClicou(MouseEvent evt) {
		JTable tabela = (JTable) evt.getSource();
		// TableModel modelo = tabela.getModel();
		if (tabela.getSelectedRow() >= 0) {
			// String nome = (String) modelo
			// .getValueAt(tabela.getSelectedRow(), 0);
			// String tipo = (String) modelo
			// .getValueAt(tabela.getSelectedRow(), 2);
			//
			// if (bTabelaDir) {
			// if (evt.getClickCount() == 2) {
			// //catalogador.DuploCliqueLista(nome, tipo);
			// }
			// } else {
			// if (evt.getClickCount() == 1) {
			// String caminho = (String) modelo.getValueAt(tabela
			// .getSelectedRow(), 3);
			// //catalogador.EncontrarItemLista(nome, caminho);
			// }
			// }
		}
	}

	public int encontrarLinha(String nome) {
		String slinha = "";
		int nlinha = 0;
		for (int i = 0; i < this.getRowCount(); i++) {
			slinha = (String) this.getValueAt(i, 0);
			if (slinha.equals(nome)) {
				nlinha = i;
				break;
			}
		}
		return nlinha;
	}

	private void acomodaColuna(JTable table, int vColIndex, int margin) {
		DefaultTableColumnModel colModel = (DefaultTableColumnModel) table
				.getColumnModel();
		TableColumn col = colModel.getColumn(vColIndex);
		int width = 0;

		// pega a largura do cabeçalho da coluna
		TableCellRenderer renderer = col.getHeaderRenderer();
		if (renderer == null) {
			renderer = table.getTableHeader().getDefaultRenderer();
		}
		Component comp = renderer.getTableCellRendererComponent(table,
				col.getHeaderValue(), false, false, 0, 0);
		width = comp.getPreferredSize().width;

		// pega a largura maxima do dado da coluna
		for (int r = 0; r < table.getRowCount(); r++) {
			renderer = table.getCellRenderer(r, vColIndex);
			comp = renderer.getTableCellRendererComponent(table,
					table.getValueAt(r, vColIndex), false, false, r, vColIndex);
			width = Math.max(width, comp.getPreferredSize().width);
		}

		// Adiciona margem
		width += 2 * margin;

		// atribui a largura
		col.setPreferredWidth(width);
	}

	private void acomodaColunas(JTable table, int margin) {
		for (int c = 0; c < table.getColumnCount(); c++) {
			acomodaColuna(table, c, margin);
		}
	}

	// retorna a altura preferencial de uma linha.
	// o resultado é igual a mais alta celula na linha.
	private int AlturaLinhaPreferencial(JTable table, int rowIndex, int margin) {
		// pega a altura padrao para todas as linhas
		int height = table.getRowHeight();

		// Determina a mais alta celula na linha
		for (int c = 0; c < table.getColumnCount(); c++) {
			TableCellRenderer renderer = table.getCellRenderer(rowIndex, c);
			Component comp = table.prepareRenderer(renderer, rowIndex, c);
			int h = comp.getPreferredSize().height + 2 * margin;
			height = Math.max(height, h);
		}
		return height;
	}

	// Para cada linha >= ao inicio e < do que o fim, a altura de uma linha
	// É atribuida para a altura preferencial da mais alta celula na linha.
	private void acomodaLinhas(JTable table, int start, int end, int margin) {
		for (int r = 0; r < table.getRowCount(); r++) {
			// pega a altura preferencial
			int h = AlturaLinhaPreferencial(table, r, margin);

			// atribui a altura da linha usando a altura preferencial
			if (table.getRowHeight(r) != h) {
				table.setRowHeight(r, h);
			}
		}
	}

	// A altura da cada linha é atribuida para a altura preferencial da mais
	// alta celula na linha;
	private void acomodaLinhas(JTable table, int margin) {
		acomodaLinhas(table, 0, table.getRowCount(), margin);
	}
/*
	private DefaultTableModel modeloTabela(Sistema[] sistemas,
			JProgressBar barraProgresso) {
		DefaultTableModel dtm = new DefaultTableModel();
		String[] colNomes;

		colNomes = new String[] { Rotinas.getRecurso("Tabela.projeto"),
				Rotinas.getRecurso("Tabela.linguagem"),
				Rotinas.getRecurso("Tabela.framework"),
				Rotinas.getRecurso("Tabela.tela") };

		Vector<String> ColunasNome = new Vector<String>(colNomes.length);
		Sistema Campos = new Sistema();
		Vector<Vector<String>> Linhas = new Vector<Vector<String>>();

		for (int coluna = 0; coluna < colNomes.length; coluna++) {
			ColunasNome.addElement(colNomes[coluna]);
		}

		if (sistemas != null) {
			barraProgresso.setMinimum(0);
			barraProgresso.setMaximum(sistemas.length);
			barraProgresso.setValue(0);

			for (int reg = 0; reg < sistemas.length; reg++) {
				Vector<String> novaLinha = new Vector<String>();

				Campos = (Sistema) sistemas[reg];

				novaLinha.addElement(Campos.getProjeto().getDescricao());
				novaLinha.addElement(Campos.getLinguagem().getDescricao());
				novaLinha.addElement(Campos.getFramework().getDescricao());
				novaLinha.addElement(Campos.getTela().getDescricao());

				Linhas.addElement(novaLinha);

				barraProgresso.setValue(barraProgresso.getValue() + 1);
				barraProgresso.getUI().update(barraProgresso.getGraphics(),
						barraProgresso);
			}
			barraProgresso.setValue(0);
		}
		dtm.setDataVector(Linhas, ColunasNome);

		return dtm;
	}
*/
	// Assume que a tabela está contida num JScrollPane. Rola a
	// celula (rowIndex, vColIndex) até que fique visivel no centro do viewport.
	public void scrollToCenter(int rowIndex, int vColIndex) {
		if (!(this.getParent() instanceof JViewport)) {
			return;
		}
		JViewport viewport = (JViewport) this.getParent();

		// Este retangulo é relativo a tabela onde o
		// a beirada noroeste da celula (0,0) é sempre (0,0).
		Rectangle rect = this.getCellRect(rowIndex, vColIndex, true);

		// A localização da view relativa para a tabela
		Rectangle viewRect = viewport.getViewRect();

		// Traduz a localização da celula para aquela que é relativa
		// a view, assumindo que a beirada noroeste da view é (0,0).
		rect.setLocation(rect.x - viewRect.x, rect.y - viewRect.y);

		// Calcula a localização do retangulo se está no cetro da view
		int centerX = (viewRect.width - rect.width) / 2;
		int centerY = (viewRect.height - rect.height) / 2;

		// Faz a localização da célula para que
		// scrollRectToVisible mova para o centro da célula
		if (rect.x < centerX) {
			centerX = -centerX;
		}
		if (rect.y < centerY) {
			centerY = -centerY;
		}
		rect.translate(centerX, centerY);

		// Rola para a area dentro da view.
		viewport.scrollRectToVisible(rect);
	}

	public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,
			int vColIndex) {
		Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
		if (this.sombrearLinhas)
			c = sombrearLinhas(renderer, rowIndex, vColIndex);
		else if (this.sombrearColunas)
			c = sombrearColunas(renderer, rowIndex, vColIndex);

		return c;
	}

	private Component sombrearLinhas(TableCellRenderer renderer, int rowIndex,
			int vColIndex) {
		Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
		if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
			c.setBackground(new Color(232, 232, 232));
		} else if (!isCellSelected(rowIndex, vColIndex)) {
			c.setBackground(getBackground());
		}
		return c;
	}

	private Component sombrearColunas(TableCellRenderer renderer, int rowIndex,
			int vColIndex) {
		Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
		if (vColIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
			c.setBackground(new Color(232, 232, 232));
		} else if (!isCellSelected(rowIndex, vColIndex)) {
			c.setBackground(getBackground());
		}
		return c;
	}

	public boolean isSombrearLinhas() {
		return sombrearLinhas;
	}

	public void setSombrearLinhas(boolean sombrearLinhas) {
		this.sombrearLinhas = sombrearLinhas;
	}

	public boolean isSombrearColunas() {
		return sombrearColunas;
	}

	public void setSombrearColunas(boolean sombrearColunas) {
		this.sombrearColunas = sombrearColunas;
	}

}
