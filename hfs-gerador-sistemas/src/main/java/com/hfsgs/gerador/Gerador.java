package com.hfsgs.gerador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.hfsgs.comum.Rotinas;
import com.hfsgs.gui.FrmPrincipal;
import com.hfsgs.objetos.Parametro;
import com.hfsgs.objetoslista.BancoLista;
import com.hfsgs.objetoslista.FrameworkLista;
import com.hfsgs.objetoslista.LinguagemLista;
import com.hfsgs.objetoslista.ProjetoLista;
import com.hfsgs.objetoslista.TelaLista;
import com.hfswing.componentes.HFSFileChooser;
import com.hfswing.componentes.HFSOptionPane;

public class Gerador {

	private FrmPrincipal frm;

	private Logger log = Logger.getLogger(Rotinas.GERADOR);

	public Gerador(FrmPrincipal form) {
		this.frm = form;
	}

	public static void iniciarSistema(Parametro param) throws SAXException,
			IOException, ParserConfigurationException, SQLException,
			ClassNotFoundException {

		Rotinas.carregarParametros(param.getArquivoConfiguracao());

		Rotinas.Conectar();

		carregarListas(Rotinas.getParams());
	}

	public static void carregarListas(Parametro params) throws SQLException {
		HFSOptionPane.mudarLingua(params.getLocalidadeLingua(),
				params.getLocalidadePais());
		HFSFileChooser.mudarLingua(params.getLocalidadeLingua(),
				params.getLocalidadePais());

		ProjetoLista.getInstancia().carregar();
		BancoLista.getInstancia().carregar();
		FrameworkLista.getInstancia().carregar();
		LinguagemLista.getInstancia().carregar();
		TelaLista.getInstancia().carregar();
	}

	public void mudarLingua(String lingua) {
		try {
			Parametro params = Rotinas.getParams();
			params.setLocalidadeLingua(lingua);
			params.setLocalidadePais("");
			frm.mudarLingua();
			Gerador.carregarListas(params);
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
