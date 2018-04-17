package com.hfsgs.gui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.hfsgs.comum.Rotinas;
import com.hfsgs.gerador.Gerador;
import com.hfswing.aplicacao.HFSAplicacao;
import com.hfswing.aplicacao.HFSSobre;
import com.hfswing.componentes.HFSOptionPane;

public class FrmPrincipal extends HFSAplicacao {
	private static final long serialVersionUID = 1L;

	private Gerador gerador;

	private JMenu menuCadastro;

	private JMenuItem menuProjeto;

	private JMenu menuRelatorio;

	private JMenuItem menuVisualizarRel;

	public FrmPrincipal() {
		//currentLookAndFeel = metal;
		
		setTitle(Rotinas.getRecurso("FrmPrincipal.Titulo"));
		btnSobre.setIcon(Rotinas.getImagem("Sobre.gif"));
		btnSobre.setToolTipText(Rotinas.getRecurso("FrmPrincipal.BtnSobre"));

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

		menuCadastro.setText(Rotinas.getRecurso("FrmPrincipal.MenuCadastro"));

		menuProjeto.setText(Rotinas.getRecurso("FrmPrincipal.MenuProjeto"));
		menuProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuProjetoActionPerformed(evt);
			}
		});
		menuCadastro.add(menuProjeto);

		menuVisualizarRel.setText(Rotinas
				.getRecurso("FrmPrincipal.menuVisualizarRel"));
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
		menuRelatorio.setText(Rotinas.getRecurso("FrmPrincipal.menuRelatorio"));

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
		
		gerador = new Gerador(this);
	}

	public void mudarLingua() {
		setTitle(Rotinas.getRecurso("FrmPrincipal.Titulo"));
		btnSobre.setToolTipText(Rotinas.getRecurso("FrmPrincipal.BtnSobre"));		
		menuCadastro.setText(Rotinas.getRecurso("FrmPrincipal.MenuCadastro"));
		menuProjeto.setText(Rotinas.getRecurso("FrmPrincipal.MenuProjeto"));
		menuVisualizarRel.setText(Rotinas
				.getRecurso("FrmPrincipal.menuVisualizarRel"));
		menuRelatorio.setText(Rotinas.getRecurso("FrmPrincipal.menuRelatorio"));
	}

	private void formWindowOpened(WindowEvent evt) {
		//
	}

	private void formWindowClosing(WindowEvent evt) {
		try {
			Rotinas.Desconectar();
		} catch (SQLException e) {
			HFSOptionPane.mensagemErro(this, "FrmPrincipal.ErroDesconectar",
					e.getLocalizedMessage());
		}
	}

	private void btnSobreActionPerformed(ActionEvent evt) {
		HFSSobre frmSobre = new HFSSobre(this, true,
				Rotinas.getRecurso("FrmSobre.Titulo"),
				Rotinas.getImagem("FotoRosto.jpg"),
				Rotinas.getRecurso("FrmSobre.Gerador"),
				Rotinas.getRecurso("FrmSobre.Desenv") + ", "
						+ Rotinas.getRecurso("Parametro.versao") + " "
						+ Rotinas.getParams().getVersao(),
				Rotinas.getRecurso("FrmSobre.Por"),
				Rotinas.getRecurso("FrmSobre.Direitos"));
		Rotinas.CentralizaDlg(frmSobre);
		frmSobre.setVisible(true);
	}

	private void menuProjetoActionPerformed(ActionEvent evt) {
		FrmCadastro frm = new FrmCadastro();
		frm.setVisible(true);
	}

	private void menuVisualizarRelActionPerformed(ActionEvent evt) {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//gerador.visualizarRelatorio();
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	private void menuSemSombrearActionPerformed(ActionEvent evt) {
		gerador.sombrear(false, false);
	}

	private void menuSombrearLinhasActionPerformed(ActionEvent evt) {
		gerador.sombrear(true, false);
	}

	private void menuSombrearColunasActionPerformed(ActionEvent evt) {
		gerador.sombrear(false, true);
	}

	private void menuPortuguesActionPerformed(ActionEvent evt) {
		gerador.mudarLingua("pt");
	}

	private void menuInglesActionPerformed(ActionEvent evt) {
		gerador.mudarLingua("en");
	}

	private void menuEspanholActionPerformed(ActionEvent evt) {
		gerador.mudarLingua("es");
	}

	private void menuFrancesActionPerformed(ActionEvent evt) {
		gerador.mudarLingua("fr");
	}

	private void menuItalianoActionPerformed(ActionEvent evt) {
		gerador.mudarLingua("it");
	}

	private void menuAlemaoActionPerformed(ActionEvent evt) {
		gerador.mudarLingua("de");
	}

	private void menuJaponesActionPerformed(ActionEvent evt) {
		gerador.mudarLingua("ja");
	}

	private void menuCoreanoActionPerformed(ActionEvent evt) {
		gerador.mudarLingua("ko");
	}

	private void menuChinesTradicionalActionPerformed(ActionEvent evt) {
		gerador.mudarLingua("zh");
	}

}

