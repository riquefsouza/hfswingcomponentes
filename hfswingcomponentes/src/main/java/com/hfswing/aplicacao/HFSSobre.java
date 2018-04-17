package com.hfswing.aplicacao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.hfswing.componentes.HFSButton;
import com.hfswing.util.HFSUtil;
import com.hfswing.util.SistemaPropriedade;

public class HFSSobre extends JDialog {
	private static final long serialVersionUID = 8424792183755933693L;

	private HFSButton btnOk;

	private JLabel labDesenv;

	private JLabel labDireitos;

	private JLabel labFotoRosto;

	private JLabel labGuardaDir;

	private JLabel labPor;

	private JPanel panelNorte;

	private JPanel panelSobre;

	private JPanel panelSul;

	private JPanel panelTexto;

	private JScrollPane panelRolagem;

	private JTable tabelaPropriedades;

	public HFSSobre(Frame parent, boolean modal, String titulo,
			ImageIcon imagemLogo, String sistema, String versao,
			String desenvPor, String direitos) {
		super(parent, modal);
		initComponents(titulo, imagemLogo, sistema, versao, desenvPor, direitos);
	}

	private void initComponents(String titulo, ImageIcon imagemLogo,
			String sistema, String versao, String desenvPor, String direitos) {

		panelNorte = new JPanel();
		panelSobre = new JPanel();
		labFotoRosto = new JLabel();
		panelTexto = new JPanel();
		labGuardaDir = new JLabel();
		labDesenv = new JLabel();
		labPor = new JLabel();
		labDireitos = new JLabel();
		panelRolagem = new JScrollPane();
		tabelaPropriedades = new JTable();
		panelSul = new JPanel();
		btnOk = new HFSButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(titulo);
		setMinimumSize(new Dimension(512, 363));
		setPreferredSize(new Dimension(512, 363));
		setModal(true);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(10, 0));

		panelNorte.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panelNorte.setLayout(new BorderLayout(10, 10));

		panelSobre.setBorder(BorderFactory.createEtchedBorder());
		panelSobre.setLayout(new BorderLayout(10, 10));

		labFotoRosto.setIcon(imagemLogo);
		panelSobre.add(labFotoRosto, BorderLayout.WEST);

		panelTexto.setLayout(new GridLayout(4, 1, 10, 0));

		labGuardaDir.setText(sistema);
		panelTexto.add(labGuardaDir);

		labDesenv.setText(versao);
		panelTexto.add(labDesenv);

		labPor.setText(desenvPor);
		panelTexto.add(labPor);

		labDireitos.setText(direitos);
		panelTexto.add(labDireitos);

		panelSobre.add(panelTexto, BorderLayout.CENTER);

		panelNorte.add(panelSobre, BorderLayout.CENTER);

		getContentPane().add(panelNorte, BorderLayout.NORTH);

		tabelaPropriedades.setModel(new DefaultTableModel(modeloTabelaLinhas(),
				modeloTabelaColunas()) {
			private static final long serialVersionUID = -1354365527378982821L;

			boolean[] canEdit = new boolean[] { false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tabelaPropriedades
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				tabelaPropriedades.getModel());
		tabelaPropriedades.setRowSorter(sorter);

		panelRolagem.setViewportView(tabelaPropriedades);

		getContentPane().add(panelRolagem, java.awt.BorderLayout.CENTER);

		panelSul.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		if (UIManager.getLookAndFeel().getID().equals("Motif"))
			panelSul.setPreferredSize(new Dimension(47, 45));
		else
			panelSul.setPreferredSize(new Dimension(47, 35));
		panelSul.setLayout(new BorderLayout());

		btnOk.setMnemonic(HFSUtil.getMnemonico("HFSSobre.btnOkMnemonico"));
		btnOk.setText(HFSUtil.getRecurso("HFSSobre.btnOk"));
		btnOk.setPreferredSize(new Dimension(80, 23));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnOkActionPerformed(evt);
			}
		});
		panelSul.add(btnOk, BorderLayout.EAST);

		getContentPane().add(panelSul, BorderLayout.SOUTH);

		pack();
	}

	private Vector<String> modeloTabelaColunas() {
		String[] colNomes = new String[] {
				HFSUtil.getRecurso("HFSSobre.Propriedade"),
				HFSUtil.getRecurso("HFSSobre.Valor") };
		Vector<String> ColunasNome = new Vector<String>(colNomes.length);

		for (int coluna = 0; coluna < colNomes.length; coluna++) {
			ColunasNome.addElement(colNomes[coluna]);
		}
		return ColunasNome;
	}

	private Vector<Vector<String>> modeloTabelaLinhas() {
		SistemaPropriedade Campos = new SistemaPropriedade();
		Vector<Vector<String>> Linhas = new Vector<Vector<String>>();
		SistemaPropriedade[] propsSistema = HFSUtil.getPropriedadesSistema();

		for (int reg = 0; reg < propsSistema.length; reg++) {
			Vector<String> novaLinha = new Vector<String>();

			Campos = (SistemaPropriedade) propsSistema[reg];

			novaLinha.addElement(Campos.getNome());
			novaLinha.addElement(Campos.getValor());

			Linhas.addElement(novaLinha);
		}
		return Linhas;
	}

	private void btnOkActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
