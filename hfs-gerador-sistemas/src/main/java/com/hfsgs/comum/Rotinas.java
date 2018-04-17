package com.hfsgs.comum;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hfsgs.objetos.ConexaoParams;
import com.hfsgs.objetos.Parametro;
import com.hfsgs.objetos.PropriedadeSistema;
import com.hfswing.componentes.HFSOptionPane;

public final class Rotinas {
	private static Connection db;

	public static final String GERADOR = "HFSGeradorSistemas";

	private static Parametro params = new Parametro();

	private static String modeloName = "";

	private static String modeloDriver = "";

	private static String modeloURL = "";

	public static void iniciarLogArquivo(Logger logger) {
		logger.setLevel(Level.ALL);
		logger.setUseParentHandlers(false);
		ConsoleHandler ch = new ConsoleHandler();
		ch.setFormatter(new LogFormato());
		logger.addHandler(ch);
		if (params.getArquivoLog().trim().length() > 0) {
			try {
				// String pattern = "HFSGeradorSistemas%g.log";
				// int limit = 1000000; // em bytes
				// int numLogFiles = 3;
				// FileHandler fh = new FileHandler(pattern, limit, numLogFiles,
				// true);
				FileHandler fh = new FileHandler(params.getArquivoLog(), true);
				fh.setFormatter(new LogFormato());
				// fh.setFormatter(new SimpleFormatter());
				// fh.setFormatter(new XMLFormatter());
				// fh.setFormatter(new HtmlFormatter());
				logger.addHandler(fh);
			} catch (IOException e) {
				logger.severe(e.getLocalizedMessage());
			}
		}
	}

	public static Parametro getParams() {
		return params;
	}

	public static String getRecurso(String chave) {
		Locale localidade = new Locale(params.getLocalidadeLingua(), params
				.getLocalidadePais());
		return ResourceBundle.getBundle("com/hfsgs/recursos/gerador",
				localidade).getString(chave);
	}

	public static ImageIcon getImagem(String arquivo) {
		String caminho = "/com/hfsgs/recursos/imagens/" + arquivo;
		return new ImageIcon(Rotinas.class.getResource(caminho));
	}

	public static InputStream getImagemStream(String arquivo) {
		String caminho = "/com/hfsgs/recursos/imagens/" + arquivo;
		return Rotinas.class.getResourceAsStream(caminho);
	}

	public static char getMnemonico(String chave) {
		return (Rotinas.getRecurso(chave)).charAt(0);
	}

	public static String Copy(String texto, int pos, int tam) {
		if ((pos + tam - 1) > texto.length())
			tam = texto.length();
		else
			tam = pos + tam - 1;
		return texto.substring(pos - 1, tam);
	}

	public static String retiraDrive(String arquivo) {
		File[] drives = File.listRoots();
		int i = 0;
		boolean bAchou = false;
		String sArq = Rotinas.testaNull(arquivo);

		if (sArq.length() > 0) {
			for (i = 0; i < drives.length; i++) {
				if (sArq.toUpperCase().indexOf(
						drives[i].getPath().toUpperCase()) >= 0) {
					bAchou = true;
					break;
				}
			}
		}
		sArq = Rotinas.trocaSeparador(sArq);
		if (bAchou)
			return sArq.substring(drives[i].getPath().length());
		else
			return sArq;
	}

	public static String formataDate(String formato, java.util.Date dt) {
		String ds = "";
		if (dt != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			ds = sdf.format(dt);
		}
		return ds;
	}

	public static String formataDate(java.util.Date dt) {
		return formataDate("dd/MM/yyyy", dt);
	}

	public static Date StringToDate(String sData, String formatoEntrada) {
		Date data = null;
		if (sData.trim().length() > 0) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(formatoEntrada);
				data = sdf.parse(sData);
			} catch (Exception ex) {
				System.err.println("Erro na rotina StringToDate: "
						+ ex.getMessage());
			}
		}
		return data;
	}

	public static Timestamp DateToTimestamp(Date data) {
		Timestamp ts = null;
		if (data != null) {
			ts = new Timestamp(data.getTime());
		}
		return ts;
	}

	public static String FormatLong(String formato, long valor) {
		DecimalFormat fmt = new DecimalFormat(formato);
		return fmt.format(valor);
	}

	public static String FormatDouble(String formato, double valor) {
		DecimalFormat fmt = new DecimalFormat(formato);
		return fmt.format(valor);
	}

	public static String testaNull(String res) {
		return (res == null ? "" : res.trim());
	}

	public static String MontaTamanho(long nTam) {
		BigDecimal nbTam = new BigDecimal(nTam);
		BigDecimal nUmKilo = new BigDecimal(1024);
		double nUmMega = 1024 * 1024;
		double nUmGiga = 1024 * 1024 * 1024;
		// double nUmTera = 1024 * 1024 * 1024 * 1024;
		BigDecimal nUmTera = new BigDecimal(nUmGiga);
		nUmTera = nUmTera.multiply(nUmKilo);

		if (nTam < 1024)
			return Long.toString(nTam) + " Byte(s)";
		else if (nTam > 1024 && nTam < nUmMega)
			return FormatDouble("#,##0.00", nTam / 1024f) + " KByte(s)";
		else if (nTam > nUmMega && nTam < nUmGiga)
			return FormatDouble("#,##0.00", nTam / nUmMega) + " MByte(s)";
		else if (nTam > nUmGiga && nbTam.compareTo(nUmTera) == -1)
			return FormatDouble("#,##0.00", nTam / nUmGiga) + " GByte(s)";
		else
			// return FormatDouble("#,##0.00", nTam / nUmTera) + " TByte(s)";
			return nbTam.divide(nUmTera).toString() + " TByte(s)";
	}

	public static InputStream getTextoDentroJar(Object obj, String str) {
		return obj.getClass().getResourceAsStream(str);
	}

	public static StringList lerTextoDentroJar(Object obj, String str) {
		String thisLine;
		StringList sl = new StringList();
		try {
			InputStream is = getTextoDentroJar(obj, str);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((thisLine = br.readLine()) != null) {
				sl.add(thisLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sl;
	}

	public static void CentralizaDlg(JFrame frm1, JDialog dlg1) {
		Dimension dlgTam = dlg1.getPreferredSize();
		Dimension frmTam = frm1.getSize();
		Point loc = frm1.getLocation();
		dlg1.setLocation(((frmTam.width - dlgTam.width) / 2) + loc.x,
				((frmTam.height - dlgTam.height) / 2)
						+ (loc.y + (dlgTam.height / 4)));
	}

	public static void CentralizaDlg(JPanel pnl1, JDialog dlg1) {
		Dimension dlgTam = dlg1.getPreferredSize();
		Dimension pnlTam = pnl1.getPreferredSize();
		Point loc = pnl1.getLocationOnScreen();
		dlg1.setLocation(((pnlTam.width - dlgTam.width) / 2) + loc.x,
				((pnlTam.height - dlgTam.height) / 2) + loc.y);
	}

	public static void CentralizaDlg(JDialog dlg1) {
		Dimension TamTela = Toolkit.getDefaultToolkit().getScreenSize();
		dlg1.setLocation((TamTela.width - dlg1.getPreferredSize().width) / 2,
				(TamTela.height - dlg1.getPreferredSize().height) / 2);
	}

	public static void CentralizaFrm(JFrame frm1) {
		Dimension TelaTam = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameTam = frm1.getSize();
		if (frameTam.height > TelaTam.height)
			frameTam.height = TelaTam.height;
		if (frameTam.width > TelaTam.width)
			frameTam.width = TelaTam.width;
		frm1.setLocation((TelaTam.width - frameTam.width) / 2,
				(TelaTam.height - frameTam.height) / 2);
		frm1.setVisible(true);
	}

	public static void CentralizaPanel(JPanel pnl) {
		Dimension TamTela = Toolkit.getDefaultToolkit().getScreenSize();
		pnl.setLocation((TamTela.width - pnl.getPreferredSize().width) / 2,
				(TamTela.height - pnl.getPreferredSize().height) / 2);
	}

	public static void CentralizaWindow(JWindow janela) {
		Dimension TamTela = Toolkit.getDefaultToolkit().getScreenSize();
		janela.setLocation(
				(TamTela.width - janela.getPreferredSize().width) / 2,
				(TamTela.height - janela.getPreferredSize().height) / 2);
	}

	public static Connection getConexao() {
		return db;
	}

	public static void Conectar() throws ClassNotFoundException, SQLException {
		ConexaoParams cp = new ConexaoParams();
		cp.setDriver(params.getBanco().getDriver());
		cp.setUrl(params.getBanco().getUrl());
		cp.setLogin(params.getBanco().getLogin());
		cp.setSenha(params.getBanco().getSenha());
		Conectar(cp);
	}

	public static void Conectar(ConexaoParams cp)
			throws ClassNotFoundException, SQLException {
		Class.forName(cp.getDriver());
		db = DriverManager.getConnection(cp.getUrl(), cp.getLogin(), cp
				.getSenha());
	}

	public static void Desconectar() throws SQLException {
		Desconectar(db);
	}

	public static void Desconectar(Connection con) throws SQLException {
		if (con != null)
			con.close();
	}

	public static PropriedadeSistema[] getPropriedadesSistema() {
		List<PropriedadeSistema> lista = new ArrayList<PropriedadeSistema>();
		Properties props = System.getProperties();
		PropriedadeSistema propSis = null;
		Enumeration<?> enum1 = props.propertyNames();
		for (; enum1.hasMoreElements();) {
			String propName = (String) enum1.nextElement();
			String propValue = (String) props.get(propName);
			propSis = new PropriedadeSistema(propName, propValue);
			lista.add(propSis);
		}
		Collections.sort(lista);
		return lista
				.toArray(new PropriedadeSistema[lista.size()]);
	}

	private static Document carregarXml(boolean lerArquivo, String arquivoXML,
			InputStream streamXML, boolean validarDTD) throws SAXException,
			IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(validarDTD);
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		Document doc = null;
		if (lerArquivo) {
			doc = factory.newDocumentBuilder().parse(new File(arquivoXML));
		} else {
			doc = factory.newDocumentBuilder().parse(streamXML);
		}
		return doc;
	}

	public static Document carregarXml(String arquivoXML, boolean validarDTD)
			throws SAXException, IOException, ParserConfigurationException {
		return carregarXml(true, arquivoXML, null, validarDTD);
	}

	public static Document carregarXml(InputStream streamXML, boolean validarDTD)
			throws SAXException, IOException, ParserConfigurationException {
		return carregarXml(false, "", streamXML, validarDTD);
	}

	public static void visitaNodesDOM(Node node, int level, boolean bModelo) {
		Node nodePai = node.getParentNode();
		if (nodePai != null) {
			if (nodePai.getNodeName().equals("localeLanguage"))
				params.setLocalidadeLingua(node.getNodeValue());
			else if (nodePai.getNodeName().equals("localeCountry"))
				params.setLocalidadePais(node.getNodeValue());
			else if (nodePai.getNodeName().equals("lookAndFeel"))
				params.setLookAndFeel(node.getNodeValue());
			else if (nodePai.getNodeName().equals("logFile"))
				params.setArquivoLog(node.getNodeValue());			
			else if (nodePai.getNodeName().equals("databaseName"))
				params.getBanco().setNome(node.getNodeValue());
			else if (nodePai.getNodeName().equals("databaseDriver"))
				params.getBanco().setDriver(node.getNodeValue());
			else if (nodePai.getNodeName().equals("databaseURL"))
				params.getBanco().setUrl(node.getNodeValue());
			else if (nodePai.getNodeName().equals("databaseLogin"))
				params.getBanco().setLogin(node.getNodeValue());
			else if (nodePai.getNodeName().equals("databasePassword"))
				params.getBanco().setSenha(node.getNodeValue());
			else if (nodePai.getNodeName().equals("modelConnection"))
				bModelo = true;
			else if (nodePai.getNodeName().equals("modelName"))
				modeloName = node.getNodeValue();
			else if (nodePai.getNodeName().equals("modelDriver"))
				modeloDriver = node.getNodeValue();
			else if (nodePai.getNodeName().equals("modelURL"))
				modeloURL = node.getNodeValue();

			if (bModelo) {
				if (modeloName.trim().length() > 0
						&& modeloDriver.trim().length() > 0
						&& modeloURL.trim().length() > 0) {
					ConexaoParams cp = new ConexaoParams();
					cp.setNome(modeloName);
					cp.setDriver(modeloDriver);
					cp.setUrl(modeloURL);
					params.setModelo(cp);

					bModelo = false;
					modeloName = "";
					modeloDriver = "";
					modeloURL = "";
				}
			}
		}

		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node childNode = list.item(i);
			visitaNodesDOM(childNode, level + 1, bModelo);
		}
	}

	public static void carregarParametros(String arquivo) throws SAXException,
			IOException, ParserConfigurationException {
		File arq = new File(arquivo);
		if (arq.exists()) {
			Document doc = carregarXml(arquivo, false);
			visitaNodesDOM(doc, 0, false);
		} else
			HFSOptionPane.mensagemErro(null, "Gerador.ValidaArquivoConf");
	}

	public static String trocaSeparador(String texto) {
		String res = "";
		if (texto != null && texto.length() > 0) {
			char[] pedacos = texto.toCharArray();
			for (int i = 0; i < pedacos.length; i++) {
				if (pedacos[i] == '\\') {
					pedacos[i] = '/';
				}
			}
			res = new String(pedacos);
		}
		return res;
	}

	public static String retiraSeparador(String texto) {
		String res = texto;
		int i = 0;
		if (texto != null && texto.length() > 0) {
			char[] pedacos = texto.toCharArray();
			for (i = 0; i < pedacos.length; i++) {
				if (pedacos[i] == '\\') {
					break;
				}
			}
			if (i == (texto.length() - 1))
				res = texto.substring(0, i);
		}
		return res;
	}

	public static StringList lerArquivo(String arquivo) throws IOException {
		StringList lista = new StringList();
		BufferedReader in = new BufferedReader(new FileReader(arquivo));
		String str;
		while ((str = in.readLine()) != null) {
			lista.add(str);
		}
		in.close();
		return lista;
	}

	public static void salvaArquivo(String arquivo, StringList lista,
			boolean bAppend) throws IOException {
		BufferedWriter out = new BufferedWriter(
				new FileWriter(arquivo, bAppend));

		for (int i = 0; i < lista.size(); i++) {
			out.write(lista.getString(i));
			out.flush();
		}
		out.close();
	}

	public static String toHTML(boolean bCabecalho, boolean bRodape) {
		String ret = "";
		if (bCabecalho) {
			ret = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n"
					+ "<html>\n"
					+ "<head>\n"
					+ "  <meta content=\"text/html; charset=ISO-8859-1\" http-equiv=\"content-type\">\n"
					+ "  <title></title>\n" + "</head>\n" + "<body>\n";
		} else if (bRodape) {
			ret = "</body>\n" + "</html>\n";
		}
		return ret;
	}

	public static String toXML(boolean bCabecalho) {
		String ret = "";
		if (bCabecalho) {
			ret = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
					+ "<Gerador>\n";
		} else {
			ret = "</Gerador>\n";
		}
		return ret;
	}

	public static InputStream ByteArrayToStream(byte[] bytes) {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		return bais;
	}

	public static byte[] StreamToByteArray(InputStream itStrm)
			throws IOException {
		byte[] bytes = new byte[itStrm.available()];
		itStrm.read(bytes, 0, itStrm.available());
		return bytes;
	}
	
	public static String pathDaClasse(Class<?> klass) {
		String className = "/" + klass.getName().replace('.', '/') + ".class";
		URL u = klass.getResource(className);
		String s = u.toString();
		if (s.startsWith("jar:file:/")) {
			// Deve ser algo como
			// "jar:file:/C:/Program%20Files/Java/jre1.5.0_08/jre/lib/rt.jar!/java/lang/String.class"
			int pos = s.indexOf(".jar!/");
			if (pos != -1) {
				if (File.separator.equals("\\"))
					s = s.substring("jar:file:/".length(), pos
							+ ".jar".length());
				else
					s = s
							.substring("jar:file:".length(), pos
									+ ".jar".length());
				s = s.replaceAll("%20", " ");
			} else {
				s = "?";
			}
		} else if (s.startsWith("file:/")) {
			// Algo como "file:/C:/temp2/java/TestePathDaClasse.class"
			if (File.separator.equals("\\"))
				s = s.substring("file:/".length());
			else
				s = s.substring("file:".length());
			s = s.substring(0, s.lastIndexOf(className)).replaceAll("%20", " ");
		} else {
			s = "?";
		}
		return s;
	}
	
}
