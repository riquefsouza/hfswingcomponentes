package com.hfswing.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

import com.hfswing.componentes.HFSOptionPane;

public final class HFSUtil {

	public static final String HFSWING = "hfswing";

	private static Connection db;

	private static SistemaParams params;

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

	public static SistemaParams getParams() {
		return params;
	}

	public static void setParams(SistemaParams params) {
		HFSUtil.params = params;
	}

	public static String getRecurso(String chave) {
		Locale localidade = new Locale(params.getLocalidadeLingua(),
				params.getLocalidadePais());
		return ResourceBundle.getBundle("com/hfswing/recursos/hfswing",
				localidade).getString(chave);
	}

	public static ImageIcon getImagem(String arquivo) {
		String caminho = "/com/hfswing/recursos/imagens/" + arquivo;
		return new ImageIcon(HFSUtil.class.getResource(caminho));
	}

	public static InputStream getImagemStream(String arquivo) {
		String caminho = "/com/hfswing/recursos/imagens/" + arquivo;
		return HFSUtil.class.getResourceAsStream(caminho);
	}

	public static char getMnemonico(String chave) {
		return (HFSUtil.getRecurso(chave)).charAt(0);
	}

	public static SistemaPropriedade[] getPropriedadesSistema() {
		List<SistemaPropriedade> lista = new ArrayList<SistemaPropriedade>();
		Properties props = System.getProperties();
		SistemaPropriedade propSis = null;
		Enumeration<?> enum1 = props.propertyNames();
		for (; enum1.hasMoreElements();) {
			String propName = (String) enum1.nextElement();
			String propValue = (String) props.get(propName);
			propSis = new SistemaPropriedade(propName, propValue);
			lista.add(propSis);
		}
		Collections.sort(lista);
		return lista.toArray(new SistemaPropriedade[lista.size()]);
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
		db = DriverManager.getConnection(cp.getUrl(), cp.getLogin(),
				cp.getSenha());
	}

	public static void Desconectar() throws SQLException {
		Desconectar(db);
	}

	public static void Desconectar(Connection con) throws SQLException {
		if (con != null)
			con.close();
	}

	public static String pontoParaVirgula(String texto) {
		String resultado = texto.replace(',', '.');

		char[] vtexto = resultado.toCharArray();
		for (int i = (vtexto.length - 1); i >= 0; i--) {
			if (vtexto[i] == '.') {
				vtexto[i] = ',';
				break;
			}
		}
		return new String(vtexto);
	}

	public static String removeAcento(String str) {
		String resultado = "";
		if (str.trim().length() > 0) {
			String acento = "´`¨^~";
			String com_acento = "áàäâãÁÀÄÂÃéèëêÉÈËÊíìïîÍÌÏÎóòöôõÓÒÖÔÕúùüûÚÙÜÛ";
			String sem_acento = "aaaaaAAAAAeeeeEEEEiiiiIIIIoooooOOOOOuuuuUUUU";
			char ch[] = str.toCharArray();
			int index;
			for (int i = 0; i < ch.length; i++) {
				if ((index = com_acento.indexOf(ch[i])) > -1) {
					ch[i] = sem_acento.charAt(index);
				}
			}

			String temp = String.valueOf(ch);
			for (int i = 0; i < ch.length; i++) {
				if (acento.indexOf(ch[i]) == -1) {
					resultado += temp.charAt(i);
				}
			}
		}
		return resultado;
	}

	public static String Copy(String texto, int pos, int tam) {
		if ((pos + tam - 1) > texto.length())
			tam = texto.length();
		else
			tam = pos + tam - 1;
		return texto.substring(pos - 1, tam);
	}

	public static String formataCPF(String cpf) {
		if (cpf != null) {
			if (cpf.length() == 11) {
				return Copy(cpf, 1, 3) + "." + Copy(cpf, 4, 3) + "."
						+ Copy(cpf, 7, 3) + "-" + Copy(cpf, 10, 2);
			}
		}
		return "";
	}

	public static String desformataCPF(String cpf) {
		if (cpf != null) {
			if (cpf.length() == 14) {
				return Copy(cpf, 1, 3) + Copy(cpf, 5, 3) + Copy(cpf, 9, 3)
						+ Copy(cpf, 13, 2);
			}
		}
		return "";
	}

	public static String formataCNPJ(String cnpj) {
		if (cnpj != null) {
			if (cnpj.length() == 14) {
				return Copy(cnpj, 1, 2) + "." + Copy(cnpj, 3, 3) + "."
						+ Copy(cnpj, 6, 3) + "/" + Copy(cnpj, 9, 4) + "-"
						+ Copy(cnpj, 13, 2);
			}
		}
		return "";
	}

	public static String desformataCNPJ(String cnpj) {
		if (cnpj != null) {
			if (cnpj.length() == 18) {
				return Copy(cnpj, 1, 2) + Copy(cnpj, 4, 3) + Copy(cnpj, 8, 3)
						+ Copy(cnpj, 12, 4) + Copy(cnpj, 17, 2);
			}
		}
		return "";
	}

	public static String formataCEP(String cep) {
		if (cep != null) {
			if (cep.length() == 8) {
				return Copy(cep, 1, 5) + "-" + Copy(cep, 6, 3);
			}
		}
		return "";
	}

	public static String desformataCEP(String cep) {
		if (cep != null) {
			if (cep.length() == 9) {
				return Copy(cep, 1, 5) + Copy(cep, 7, 3);
			}
		}
		return "";
	}

	public static String removeEspacoDuplo(String nome) {
		int cont, ContEspaco = 0;
		String x, texto = "";

		for (cont = 0; cont < nome.length(); cont++) {
			x = nome.substring(cont, cont + 1);
			if (x.equals(" ")) {
				ContEspaco++;
			} else {
				ContEspaco = 0;
			}

			if (ContEspaco < 2) {
				texto += x;
			}
		}
		return texto.trim();
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
					s = s.substring("jar:file:/".length(),
							pos + ".jar".length());
				else
					s = s.substring("jar:file:".length(), pos + ".jar".length());
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
			// else if (nodePai.getNodeName().equals("lookAndFeel"))
			// params.setLookAndFeel(node.getNodeValue());
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

	public static void carregarParametros(SistemaParams param)
			throws SAXException, IOException, ParserConfigurationException {
		HFSUtil.params = param;
		File arq = new File(param.getArquivoConfiguracao());
		if (arq.exists()) {
			Document doc = carregarXml(param.getArquivoConfiguracao(), false);
			visitaNodesDOM(doc, 0, false);
		} else
			HFSOptionPane.mensagemErro(null, "Hfswingdemo.ValidaArquivoConf");
	}

	public static String formataDecimal(double precoDouble) {
		/* Transformando um double em 2 casas decimais */
		DecimalFormat fmt = new DecimalFormat("0.00"); // limita o número de
														// casas decimais
		String string = fmt.format(precoDouble);
		String[] part = string.split("[,]");
		String preco = part[0] + "." + part[1];
		return preco;
	}

	public static double formataDecimalDouble(double precoDouble) {
		DecimalFormat fmt = new DecimalFormat("0.00");
		String string = fmt.format(precoDouble);
		String[] part = string.split("[,]");
		String string2 = part[0] + "." + part[1];
		double preco = Double.parseDouble(string2);
		return preco;
	}
}
