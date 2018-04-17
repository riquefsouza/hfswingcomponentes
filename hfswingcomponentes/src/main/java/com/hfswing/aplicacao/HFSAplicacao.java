package com.hfswing.aplicacao;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

import com.hfswing.util.HFSConst;
import com.hfswing.util.HFSUtil;

public class HFSAplicacao extends JFrame {
	private static final long serialVersionUID = 1L;

	protected String currentLookAndFeel;

	protected JButton btnSobre;

	protected JLabel labStatus1;

	protected JLabel labStatus2;

	protected JMenuBar menuBarra;

	protected JMenu menuTabela;

	protected ButtonGroup grupoSombrear;

	protected ButtonGroup grupoIdioma;

	protected JRadioButtonMenuItem menuSemSombrear;

	protected JRadioButtonMenuItem menuSombrearColunas;

	protected JRadioButtonMenuItem menuSombrearLinhas;

	protected JPanel panelBarraBotoes;

	protected JPanel panelInferior;

	protected JPanel panelStatus1;

	protected JPanel panelStatus2;

	protected JPanel panelSuperior;

	protected JMenu menuLafLabel;

	protected JMenu lafMenu;

	protected ButtonGroup lafMenuGroup;

	protected JMenu menuIdioma;

	protected JRadioButtonMenuItem menuPortugues;

	protected JRadioButtonMenuItem menuIngles;

	protected JRadioButtonMenuItem menuEspanhol;

	protected JRadioButtonMenuItem menuFrances;

	protected JRadioButtonMenuItem menuItaliano;

	protected JRadioButtonMenuItem menuAlemao;

	protected JRadioButtonMenuItem menuJapones;

	protected JRadioButtonMenuItem menuCoreano;

	protected JRadioButtonMenuItem menuChinesTradicional;

	public HFSAplicacao() {
		currentLookAndFeel = HFSConst.METAL;

		lafMenuGroup = new ButtonGroup();
		menuLafLabel = new JMenu();
		panelSuperior = new JPanel();
		panelBarraBotoes = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBarraBotoes.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		btnSobre = new JButton();
		panelInferior = new JPanel();
		panelStatus1 = new JPanel();
		labStatus1 = new JLabel();
		panelStatus2 = new JPanel();
		labStatus2 = new JLabel();
		menuBarra = new JMenuBar();
		menuTabela = new JMenu();
		grupoSombrear = new ButtonGroup();
		grupoIdioma = new ButtonGroup();
		menuSemSombrear = new JRadioButtonMenuItem();
		menuSombrearLinhas = new JRadioButtonMenuItem();
		menuSombrearColunas = new JRadioButtonMenuItem();
		menuIdioma = new JMenu();
		menuPortugues = new JRadioButtonMenuItem();
		menuIngles = new JRadioButtonMenuItem();
		menuEspanhol = new JRadioButtonMenuItem();
		menuFrances = new JRadioButtonMenuItem();
		menuItaliano = new JRadioButtonMenuItem();
		menuAlemao = new JRadioButtonMenuItem();
		menuJapones = new JRadioButtonMenuItem();
		menuCoreano = new JRadioButtonMenuItem();
		menuChinesTradicional = new JRadioButtonMenuItem();
		
		//initComponents();
	}	
	
	protected void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 550));
		setPreferredSize(new Dimension(800, 550));

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent evt) {
				formWindowOpened(evt);
			}
		});

		panelSuperior.setMinimumSize(new Dimension(675, 75));
		panelSuperior.setPreferredSize(new Dimension(675, 75));
		panelSuperior.setLayout(new BorderLayout());

		btnSobre.setPreferredSize(new Dimension(32, 25));
		panelBarraBotoes.add(btnSobre);
		panelSuperior.add(panelBarraBotoes, BorderLayout.NORTH);

		getContentPane().add(panelSuperior, BorderLayout.CENTER);

		panelInferior.setLayout(new BorderLayout());

		panelStatus1.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		panelStatus1.setMinimumSize(new Dimension(320, 14));
		panelStatus1.setPreferredSize(new Dimension(350, 14));
		panelStatus1.setLayout(new BorderLayout());

		panelStatus1.add(labStatus1, BorderLayout.CENTER);

		panelInferior.add(panelStatus1, BorderLayout.WEST);

		panelStatus2.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.RAISED));
		panelStatus2.setLayout(new BorderLayout());

		panelStatus2.add(labStatus2, BorderLayout.CENTER);

		panelInferior.add(panelStatus2, BorderLayout.CENTER);

		getContentPane().add(panelInferior, BorderLayout.SOUTH);

		menuTabela.setText(HFSUtil.getRecurso("HFSAplicacao.menuTabela"));

		grupoSombrear.add(menuSemSombrear);
		menuSemSombrear.setSelected(true);
		menuSemSombrear.setText(HFSUtil
				.getRecurso("HFSAplicacao.menuSemSombrear"));
		menuTabela.add(menuSemSombrear);

		grupoSombrear.add(menuSombrearLinhas);
		menuSombrearLinhas.setText(HFSUtil
				.getRecurso("HFSAplicacao.menuSombrearLinhas"));
		menuTabela.add(menuSombrearLinhas);

		grupoSombrear.add(menuSombrearColunas);
		menuSombrearColunas.setText(HFSUtil
				.getRecurso("HFSAplicacao.menuSombrearColunas"));
		menuTabela.add(menuSombrearColunas);

		menuBarra.add(menuTabela);

		menuIdioma.setText(HFSUtil.getRecurso("HFSAplicacao.menuIdioma"));

		grupoIdioma.add(menuPortugues);
		menuPortugues.setSelected(true);
		menuPortugues.setText(HFSUtil.getRecurso("HFSAplicacao.menuPortugues"));
		menuIdioma.add(menuPortugues);

		grupoIdioma.add(menuIngles);
		menuIngles.setText(HFSUtil.getRecurso("HFSAplicacao.menuIngles"));
		menuIdioma.add(menuIngles);

		grupoIdioma.add(menuEspanhol);
		menuEspanhol.setText(HFSUtil.getRecurso("HFSAplicacao.menuEspanhol"));
		menuIdioma.add(menuEspanhol);

		grupoIdioma.add(menuFrances);
		menuFrances.setText(HFSUtil.getRecurso("HFSAplicacao.menuFrances"));
		menuIdioma.add(menuFrances);

		grupoIdioma.add(menuItaliano);
		menuItaliano.setText(HFSUtil.getRecurso("HFSAplicacao.menuItaliano"));
		menuIdioma.add(menuItaliano);

		grupoIdioma.add(menuAlemao);
		menuAlemao.setText(HFSUtil.getRecurso("HFSAplicacao.menuAlemao"));
		menuIdioma.add(menuAlemao);

		grupoIdioma.add(menuJapones);
		menuJapones.setText(HFSUtil.getRecurso("HFSAplicacao.menuJapones"));
		menuIdioma.add(menuJapones);

		grupoIdioma.add(menuCoreano);
		menuCoreano.setText(HFSUtil.getRecurso("HFSAplicacao.menuCoreano"));
		menuIdioma.add(menuCoreano);

		grupoIdioma.add(menuChinesTradicional);
		menuChinesTradicional.setText(HFSUtil
				.getRecurso("HFSAplicacao.menuChinesTradicional"));
		menuIdioma.add(menuChinesTradicional);

		menuBarra.add(menuIdioma);

		// ***** cria o menu Look & Feel
		menuLafLabel.setText(HFSUtil.getRecurso("LafMenu.laf_label"));
		lafMenu = (JMenu) menuBarra.add(menuLafLabel);
		lafMenu.setMnemonic(HFSUtil.getMnemonico("LafMenu.laf_mnemonic"));
		lafMenu.getAccessibleContext().setAccessibleDescription(
				HFSUtil.getRecurso("LafMenu.laf_accessible_description"));

		JMenuItem mi = createLafMenuItem(lafMenu, "LafMenu.java_label",
				"LafMenu.java_mnemonic", "LafMenu.java_accessible_description",
				HFSConst.METAL);
		mi.setSelected(true);

		UIManager.LookAndFeelInfo[] lafInfo = UIManager
				.getInstalledLookAndFeels();

		for (int counter = 0; counter < lafInfo.length; counter++) {
			String className = lafInfo[counter].getClassName();
			if (className == HFSConst.MOTIF) {
				createLafMenuItem(lafMenu, "LafMenu.motif_label",
						"LafMenu.motif_mnemonic",
						"LafMenu.motif_accessible_description", HFSConst.MOTIF);
			} else if (className == HFSConst.WINDOWS) {
				createLafMenuItem(lafMenu, "LafMenu.windows_label",
						"LafMenu.windows_mnemonic",
						"LafMenu.windows_accessible_description", HFSConst.WINDOWS);
			} else if (className == HFSConst.GTK) {
				createLafMenuItem(lafMenu, "LafMenu.gtk_label",
						"LafMenu.gtk_mnemonic",
						"LafMenu.gtk_accessible_description", HFSConst.GTK);
			} else if (className == HFSConst.NIMBUS) {
				createLafMenuItem(lafMenu, "LafMenu.nimbus_label",
						"LafMenu.nimbus_mnemonic",
						"LafMenu.nimbus_accessible_description", HFSConst.NIMBUS);
			}
		}

		setJMenuBar(menuBarra);

		pack();
	}

	private void setIdiomaPadrao(String lingua) {
		if (lingua.equals("pt"))
			menuPortugues.setSelected(true);
		else if (lingua.equals("en"))
			menuIngles.setSelected(true);
		else if (lingua.equals("es"))
			menuEspanhol.setSelected(true);
		else if (lingua.equals("fr"))
			menuFrances.setSelected(true);
		else if (lingua.equals("it"))
			menuItaliano.setSelected(true);
		else if (lingua.equals("de"))
			menuAlemao.setSelected(true);
		else if (lingua.equals("ja"))
			menuJapones.setSelected(true);
		else if (lingua.equals("ko"))
			menuCoreano.setSelected(true);
		else if (lingua.equals("zh"))
			menuChinesTradicional.setSelected(true);
	}

	protected void mudarLingua() {
		setTitle(HFSUtil.getRecurso("HFSAplicacao.Titulo"));
		menuTabela.setText(HFSUtil.getRecurso("HFSAplicacao.menuTabela"));
		menuSemSombrear.setText(HFSUtil
				.getRecurso("HFSAplicacao.menuSemSombrear"));
		menuSombrearLinhas.setText(HFSUtil
				.getRecurso("HFSAplicacao.menuSombrearLinhas"));
		menuSombrearColunas.setText(HFSUtil
				.getRecurso("HFSAplicacao.menuSombrearColunas"));
		menuIdioma.setText(HFSUtil.getRecurso("HFSAplicacao.menuIdioma"));
		menuPortugues.setText(HFSUtil.getRecurso("HFSAplicacao.menuPortugues"));
		menuIngles.setText(HFSUtil.getRecurso("HFSAplicacao.menuIngles"));
		menuEspanhol.setText(HFSUtil.getRecurso("HFSAplicacao.menuEspanhol"));
		menuFrances.setText(HFSUtil.getRecurso("HFSAplicacao.menuFrances"));
		menuItaliano.setText(HFSUtil.getRecurso("HFSAplicacao.menuItaliano"));
		menuAlemao.setText(HFSUtil.getRecurso("HFSAplicacao.menuAlemao"));
		menuJapones.setText(HFSUtil.getRecurso("HFSAplicacao.menuJapones"));
		menuCoreano.setText(HFSUtil.getRecurso("HFSAplicacao.menuCoreano"));
		menuChinesTradicional.setText(HFSUtil
				.getRecurso("HFSAplicacao.menuChinesTradicional"));
		menuLafLabel.setText(HFSUtil.getRecurso("LafMenu.laf_label"));
		lafMenu.setMnemonic(HFSUtil.getMnemonico("LafMenu.laf_mnemonic"));
		lafMenu.getAccessibleContext().setAccessibleDescription(
				HFSUtil.getRecurso("LafMenu.laf_accessible_description"));
	}

	private void formWindowOpened(WindowEvent evt) {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// this.update(this.getGraphics());

		this.setIdiomaPadrao(HFSUtil.getParams().getLocalidadeLingua());
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public JMenuItem createLafMenuItem(JMenu menu, String label,
			String mnemonic, String accessibleDescription, String laf) {
		JMenuItem mi = (JRadioButtonMenuItem) menu
				.add(new JRadioButtonMenuItem(HFSUtil.getRecurso(label)));
		lafMenuGroup.add(mi);
		mi.setMnemonic(HFSUtil.getMnemonico(mnemonic));
		mi.getAccessibleContext().setAccessibleDescription(
				HFSUtil.getRecurso(accessibleDescription));
		mi.addActionListener(new ChangeLookAndFeelAction(this, laf));

		mi.setEnabled(isAvailableLookAndFeel(laf));

		return mi;
	}

	private boolean isAvailableLookAndFeel(String laf) {
		try {
			Class<?> lnfClass = Class.forName(laf);
			LookAndFeel newLAF = (LookAndFeel) (lnfClass.newInstance());
			return newLAF.isSupportedLookAndFeel();
		} catch (Exception e) {
			return false;
		}
	}

	public String getLookAndFeel(String laf) {
		if (laf.equals("mac"))
			return HFSConst.MAC;
		else if (laf.equals("metal"))
			return HFSConst.METAL;
		else if (laf.equals("motif"))
			return HFSConst.MOTIF;
		else if (laf.equals("windows"))
			return HFSConst.WINDOWS;
		else if (laf.equals("gtk"))
			return HFSConst.GTK;
		else if (laf.equals("nimbus"))
			return HFSConst.NIMBUS;
		else
			return HFSConst.METAL;
	}

	public void setLookAndFeel(String laf) {
		if (currentLookAndFeel != laf) {
			currentLookAndFeel = laf;
			String lafName = null;
			// panelSuperior.setPreferredSize(new Dimension(675, 75));
			// edtPesquisa.setPreferredSize(new Dimension(200, 20));
			// menuSombrearLinhas.setEnabled(true);
			mudarDimensaoBotoes(32, 25);
			if (laf == HFSConst.MAC) {
				lafName = HFSUtil.getRecurso("LafMenu.mac_label");
				//HFSUtil.getParams().setLookAndFeel("mac");
			}
			if (laf == HFSConst.METAL) {
				lafName = HFSUtil.getRecurso("LafMenu.java_label");
				//HFSUtil.getParams().setLookAndFeel("metal");
			}
			if (laf == HFSConst.MOTIF) {
				lafName = HFSUtil.getRecurso("LafMenu.motif_label");
				mudarDimensaoBotoes(38, 35);
				//HFSUtil.getParams().setLookAndFeel("motif");
			}
			if (laf == HFSConst.WINDOWS) {
				lafName = HFSUtil.getRecurso("LafMenu.windows_label");
				//HFSUtil.getParams().setLookAndFeel("windows");
			}
			if (laf == HFSConst.GTK) {
				lafName = HFSUtil.getRecurso("LafMenu.gtk_label");
				//HFSUtil.getParams().setLookAndFeel("gtk");
			}
			if (laf == HFSConst.NIMBUS) {
				lafName = HFSUtil.getRecurso("LafMenu.nimbus_label");
				// panelSuperior.setPreferredSize(new Dimension(675, 90));
				// edtPesquisa.setPreferredSize(new Dimension(200, 28));
				// menuSemSombrear.setSelected(true);
				// menuSombrearLinhas.setEnabled(false);
				//HFSUtil.getParams().setLookAndFeel("nimbus");
			}

			try {
				UIManager.setLookAndFeel(currentLookAndFeel);

				Icon folhaIcon = (Icon) UIManager.get("Tree.closedIcon");
				UIManager.put("Tree.leafIcon", folhaIcon);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(this);
			for (int i = 0; i < lafMenu.getItemCount(); i++) {
				JMenuItem item = lafMenu.getItem(i);
				if (item.getText() == lafName) {
					item.setSelected(true);
				} else {
					item.setSelected(false);
				}
			}
		}
	}

	private void mudarDimensaoBotoes(int largura, int altura) {
		Dimension dim = new Dimension(largura, altura);
		btnSobre.setPreferredSize(dim);
	}

}

class ChangeLookAndFeelAction extends AbstractAction {
	private static final long serialVersionUID = 8947296140794376812L;

	HFSAplicacao form;

	String laf;

	protected ChangeLookAndFeelAction(HFSAplicacao form, String laf) {
		super();
		this.form = form;
		this.laf = laf;
	}

	public void actionPerformed(ActionEvent e) {
		form.setLookAndFeel(laf);
	}
}
