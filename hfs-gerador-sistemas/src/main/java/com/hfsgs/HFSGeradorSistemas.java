package com.hfsgs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.hfsgs.comum.Rotinas;
import com.hfsgs.gerador.Gerador;
import com.hfsgs.gui.FrmPrincipal;
import com.hfsgs.objetos.Parametro;
import com.hfswing.componentes.HFSOptionPane;
import com.hfswing.util.HFSUtil;
import com.hfswing.util.SistemaParams;

public class HFSGeradorSistemas {

	public static void main(String[] args) {
		Parametro param = Rotinas.getParams();
		HFSUtil.setParams(new SistemaParams(param.getDiretorioUsuario(), "HFSGeradorSistemas", "1.0.0", "metal"));
		Logger log = Logger.getLogger(Rotinas.GERADOR);	

		if (param.getJavaVersao() > 1.4) {
			try {
				Gerador.iniciarSistema(param);
				Rotinas.iniciarLogArquivo(log);

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						FrmPrincipal frmPrincipal = new FrmPrincipal();
						Rotinas.CentralizaFrm(frmPrincipal);
						String laf = Rotinas.getParams().getLookAndFeel();
						frmPrincipal.setLookAndFeel(frmPrincipal
								.getLookAndFeel(laf));
						frmPrincipal.setVisible(true);
					}
				});

			} catch (ClassNotFoundException e) {
				HFSOptionPane.mensagemErro(null, "Gerador.ErroInicial", e
						.getLocalizedMessage());
			} catch (SQLException e) {
				HFSOptionPane.mensagemErro(null, "Gerador.ErroInicial", e
						.getLocalizedMessage());
			} catch (SAXException e) {
				HFSOptionPane.mensagemErro(null, "Gerador.ErroInicial", e
						.getLocalizedMessage());
			} catch (IOException e) {
				HFSOptionPane.mensagemErro(null, "Gerador.ErroInicial", e
						.getLocalizedMessage());
			} catch (ParserConfigurationException e) {
				HFSOptionPane.mensagemErro(null, "Gerador.ErroInicial", e
						.getLocalizedMessage());
			}
		} else {
			HFSOptionPane.mensagemErro(null, "Gerador.ValidaJavaVersao");
		}
	}
}
