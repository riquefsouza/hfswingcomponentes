package com.hfswingdemo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.hfswing.componentes.HFSFileChooser;
import com.hfswing.componentes.HFSOptionPane;
import com.hfswing.util.HFSUtil;
import com.hfswing.util.SistemaParams;

public class Hfswingdemo {

	private static HFSDemoApp demoApp;

	private static Logger log = Logger.getLogger(HFSUtil.HFSWING);

	public static void main(String[] args) {
		SistemaParams param = new SistemaParams(Hfswingdemo.class,
				"Hfswingdemo", "1.0.0", "metal");

		if (param.getJavaVersao() > 1.4) {
			try {
				Hfswingdemo.iniciarSistema(param);
				HFSUtil.iniciarLogArquivo(log);

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						demoApp = new HFSDemoApp();
						HFSUtil.CentralizaFrm(demoApp);
						//String laf = HFSUtil.getParams().getLookAndFeel();
						//demoApp.setLookAndFeel(demoApp.getLookAndFeel(laf));
						demoApp.setVisible(true);
					}
				});

			} catch (ClassNotFoundException e) {
				HFSOptionPane.mensagemErro(null, "Hfswingdemo.ErroInicial",
						e.getLocalizedMessage());
			} catch (SQLException e) {
				HFSOptionPane.mensagemErro(null, "Hfswingdemo.ErroInicial",
						e.getLocalizedMessage());
			} catch (SAXException e) {
				HFSOptionPane.mensagemErro(null, "Hfswingdemo.ErroInicial",
						e.getLocalizedMessage());
			} catch (IOException e) {
				HFSOptionPane.mensagemErro(null, "Hfswingdemo.ErroInicial",
						e.getLocalizedMessage());
			} catch (ParserConfigurationException e) {
				HFSOptionPane.mensagemErro(null, "Hfswingdemo.ErroInicial",
						e.getLocalizedMessage());
			}
		} else {
			HFSOptionPane.mensagemErro(null, "Hfswingdemo.ValidaJavaVersao");
		}
	}

	public static void iniciarSistema(SistemaParams param) throws SAXException,
			IOException, ParserConfigurationException, SQLException,
			ClassNotFoundException {

		HFSUtil.carregarParametros(param);

		//HFSUtil.Conectar();

		carregarListas(HFSUtil.getParams());
	}

	public static void carregarListas(SistemaParams params) throws SQLException {
		HFSOptionPane.mudarLingua(params.getLocalidadeLingua(),
				params.getLocalidadePais());
		HFSFileChooser.mudarLingua(params.getLocalidadeLingua(),
				params.getLocalidadePais());

	}

	public void mudarLingua(String lingua) {
		try {
			SistemaParams params = HFSUtil.getParams();
			params.setLocalidadeLingua(lingua);
			params.setLocalidadePais("");
			demoApp.mudarLingua();
			carregarListas(params);
		} catch (SQLException e) {
			log.severe(e.getLocalizedMessage());
		}
	}

	public void sombrear(boolean bLinhas, boolean bColunas) {
		// bSombrearLinhas = bLinhas;
		// bSombrearColunas = bColunas;
		// frm.tabelaArquivos.setSombrearLinhas(bSombrearLinhas);
		// frm.tabelaArquivos.setSombrearColunas(bSombrearColunas);
		// frm.tabelaArquivos.repaint();
	}
}
