package com.hfswingdemo;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.hfswing.aplicacao.HFSAplicacao;
import com.hfswing.aplicacao.HFSSobre;
import com.hfswing.util.HFSUtil;

public class HFSDemoApp extends HFSAplicacao {
	private static final long serialVersionUID = 1L;

	private JMenu menuCadastro;

	private JMenuItem menuProjeto;

	private JMenu menuRelatorio;

	private JMenuItem menuVisualizarRel;

	public HFSDemoApp() {
		// currentLookAndFeel = metal;

		// setTitle(HFSUtil.getRecurso("HFSDemoApp.Titulo"));
		setTitle("HFS SWING Componentes");
		btnSobre.setIcon(HFSUtil.getImagem("Sobre.gif"));
		// btnSobre.setToolTipText(HFSUtil.getRecurso("HFSDemoApp.BtnSobre"));
		btnSobre.setToolTipText("Sobre o Sistema");

		menuCadastro = new JMenu();
		menuProjeto = new JMenuItem();
		menuRelatorio = new JMenu();
		menuVisualizarRel = new JMenuItem();

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent evt) {
				formWindowOpened(evt);
			}

			public void windowClosing(WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSobreActionPerformed(evt);
			}
		});

		labStatus1.setText(" ");
		labStatus2.setText(" ");

		menuCadastro.setText("Cadastro");

		menuProjeto.setText("Projeto");
		menuProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuProjetoActionPerformed(evt);
			}
		});
		menuCadastro.add(menuProjeto);

		menuVisualizarRel.setText("Visualizar Rel");
		menuVisualizarRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuVisualizarRelActionPerformed(evt);
			}
		});
		menuRelatorio.add(menuVisualizarRel);

		menuBarra.add(menuCadastro);

		menuSemSombrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuSemSombrearActionPerformed(evt);
			}
		});
		menuSombrearLinhas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuSombrearLinhasActionPerformed(evt);
			}
		});
		menuSombrearColunas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuSombrearColunasActionPerformed(evt);
			}
		});
		menuRelatorio.setText("Relatorio");

		menuBarra.add(menuRelatorio);

		menuPortugues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuPortuguesActionPerformed(evt);
			}
		});
		menuIngles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuInglesActionPerformed(evt);
			}
		});
		menuEspanhol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuEspanholActionPerformed(evt);
			}
		});
		menuFrances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuFrancesActionPerformed(evt);
			}
		});
		menuItaliano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuItalianoActionPerformed(evt);
			}
		});
		menuAlemao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuAlemaoActionPerformed(evt);
			}
		});
		menuJapones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuJaponesActionPerformed(evt);
			}
		});
		menuCoreano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuCoreanoActionPerformed(evt);
			}
		});
		menuChinesTradicional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuChinesTradicionalActionPerformed(evt);
			}
		});

		super.initComponents();

	}

	public void mudarLingua() {
		// setTitle(HFSUtil.getRecurso("HFSDemoApp.Titulo"));
		// btnSobre.setToolTipText(HFSUtil.getRecurso("HFSDemoApp.BtnSobre"));
		// menuCadastro.setText(HFSUtil.getRecurso("HFSDemoApp.MenuCadastro"));
		// menuProjeto.setText(HFSUtil.getRecurso("HFSDemoApp.MenuProjeto"));
		// menuVisualizarRel.setText(Rotinas
		// .getRecurso("HFSDemoApp.menuVisualizarRel"));
		// menuRelatorio.setText(HFSUtil.getRecurso("HFSDemoApp.menuRelatorio"));
	}

	private void formWindowOpened(WindowEvent evt) {
		//
	}

	private void formWindowClosing(WindowEvent evt) {
		//
	}

	private void btnSobreActionPerformed(ActionEvent evt) {
		HFSSobre frmSobre = new HFSSobre(this, true,
				HFSUtil.getRecurso("frmSobre.Titulo"),
				HFSUtil.getImagem("FotoRosto.jpg"),
				HFSUtil.getRecurso("frmSobre.Gerador"),
				HFSUtil.getRecurso("frmSobre.Desenv") + ", "
						+ HFSUtil.getRecurso("SistemaParams.versao") + " "
						+ HFSUtil.getParams().getVersao(), 
						HFSUtil.getRecurso("frmSobre.Por"),
				HFSUtil.getRecurso("frmSobre.Direitos"));
		HFSUtil.CentralizaDlg(frmSobre);
		frmSobre.setVisible(true);
	}

	private void menuProjetoActionPerformed(ActionEvent evt) {
		// try {
		// catalogador.ExcluirItemRaiz();
		// } catch (SQLException e) {
		// Dialogo.mensagemErro(this, "FrmPrincipal.ErroExcluirItem", e
		// .getLocalizedMessage());
		// }

	}

	private void menuVisualizarRelActionPerformed(ActionEvent evt) {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//gerador.visualizarRelatorio();
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	private void menuSemSombrearActionPerformed(ActionEvent evt) {
		// gerador.sombrear(false, false);
	}

	private void menuSombrearLinhasActionPerformed(ActionEvent evt) {
		// gerador.sombrear(true, false);
	}

	private void menuSombrearColunasActionPerformed(ActionEvent evt) {
		// gerador.sombrear(false, true);
	}

	private void menuPortuguesActionPerformed(ActionEvent evt) {
		// gerador.mudarLingua("pt");
	}

	private void menuInglesActionPerformed(ActionEvent evt) {
		// gerador.mudarLingua("en");
	}

	private void menuEspanholActionPerformed(ActionEvent evt) {
		// gerador.mudarLingua("es");
	}

	private void menuFrancesActionPerformed(ActionEvent evt) {
		// gerador.mudarLingua("fr");
	}

	private void menuItalianoActionPerformed(ActionEvent evt) {
		// gerador.mudarLingua("it");
	}

	private void menuAlemaoActionPerformed(ActionEvent evt) {
		// gerador.mudarLingua("de");
	}

	private void menuJaponesActionPerformed(ActionEvent evt) {
		// gerador.mudarLingua("ja");
	}

	private void menuCoreanoActionPerformed(ActionEvent evt) {
		// gerador.mudarLingua("ko");
	}

	private void menuChinesTradicionalActionPerformed(ActionEvent evt) {
		// gerador.mudarLingua("zh");
	}

}
