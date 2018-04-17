package com.hfswing.util;

import java.util.ArrayList;

public class SistemaParams {

	private String diretorioUsuario;

	private String arquivoConfiguracao;
	
	private String arquivoLog;

	private double javaVersao;

	private String desktop;

	//private String lookAndFeel;

	private ConexaoParams banco;

	private ArrayList<ConexaoParams> listaModelosConexao;

	private String localidadeLingua;

	private String localidadePais;

	private String versao;

	public SistemaParams(Class<?> klass, String nomeSistema, String versaoSistema, String lookAndFeel) {
		this.versao = versaoSistema;
		//this.diretorioUsuario = System.getProperty("user.dir");
		this.diretorioUsuario = HFSUtil.pathDaClasse(klass);
		int pos = this.diretorioUsuario.indexOf("/"+nomeSistema+".jar");
		if (pos > 1)
			this.diretorioUsuario= diretorioUsuario.substring(0, pos);		
		this.arquivoLog = "";
		this.arquivoConfiguracao = this.diretorioUsuario + "/"+nomeSistema+".conf";
		this.javaVersao = Double.parseDouble(System
				.getProperty("java.specification.version"));
		this.desktop = System.getProperty("sun.desktop");
		//this.lookAndFeel = lookAndFeel;
		this.localidadeLingua = System.getProperty("user.language");
		this.localidadePais = System.getProperty("user.country");
		this.banco = new ConexaoParams();
		this.listaModelosConexao = new ArrayList<ConexaoParams>();
	}

	public SistemaParams(String diretorioUsuario, String nomeSistema, String versaoSistema, String lookAndFeel) {
		this.versao = versaoSistema;
		this.diretorioUsuario = diretorioUsuario;
		int pos = this.diretorioUsuario.indexOf("/"+nomeSistema+".jar");
		if (pos > 1)
			this.diretorioUsuario= diretorioUsuario.substring(0, pos);		
		this.arquivoLog = "";
		this.arquivoConfiguracao = this.diretorioUsuario + "/"+nomeSistema+".conf";
		this.javaVersao = Double.parseDouble(System
				.getProperty("java.specification.version"));
		this.desktop = System.getProperty("sun.desktop");
		//this.lookAndFeel = lookAndFeel;
		this.localidadeLingua = System.getProperty("user.language");
		this.localidadePais = System.getProperty("user.country");
		this.banco = new ConexaoParams();
		this.listaModelosConexao = new ArrayList<ConexaoParams>();
	}
	
	public ConexaoParams getBanco() {
		return banco;
	}

	public void setBanco(ConexaoParams banco) {
		this.banco = banco;
	}

	public String getLocalidadeLingua() {
		return localidadeLingua;
	}

	public void setLocalidadeLingua(String localidadeLingua) {
		this.localidadeLingua = localidadeLingua;
	}

	public String getLocalidadePais() {
		return localidadePais;
	}

	public void setLocalidadePais(String localidadePais) {
		this.localidadePais = localidadePais;
	}

//	public String getLookAndFeel() {
//		return lookAndFeel;
//	}
//
//	public void setLookAndFeel(String lookAndFeel) {
//		this.lookAndFeel = lookAndFeel;
//	}

	public String getDesktop() {
		return desktop;
	}

	public void setDesktop(String desktop) {
		this.desktop = desktop;
	}

	public double getJavaVersao() {
		return javaVersao;
	}

	public void setJavaVersao(double javaVersao) {
		this.javaVersao = javaVersao;
	}

	public String getDiretorioUsuario() {
		return diretorioUsuario;
	}

	public void setDiretorioUsuario(String diretorioUsuario) {
		this.diretorioUsuario = diretorioUsuario;
	}

	public String getArquivoConfiguracao() {
		return arquivoConfiguracao;
	}

	public void setArquivoConfiguracao(String arquivoConfiguracao) {
		this.arquivoConfiguracao = arquivoConfiguracao;
	}

	public ConexaoParams[] getModelos() {
		return listaModelosConexao
				.toArray(new ConexaoParams[listaModelosConexao.size()]);
	}

	public ConexaoParams getModelo(int indice) {
		return this.listaModelosConexao.get(indice);
	}

	public void setModelo(ConexaoParams modelo) {
		this.listaModelosConexao.add(modelo);
	}

	public void limparModelo() {
		this.listaModelosConexao.clear();
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String toString() {
		return HFSUtil.getRecurso("SistemaParams.desktop") + " " + desktop + ", "
				+ HFSUtil.getRecurso("SistemaParams.javaVersao") + " " + javaVersao
				+ ", " + HFSUtil.getRecurso("SistemaParams.diretorioUsuario") + " "
				+ diretorioUsuario + ", "
				+ HFSUtil.getRecurso("SistemaParams.arquivoConfiguracao") + " "
				+ arquivoConfiguracao + ", "
				//+ HFSUtil.getRecurso("SistemaParams.lookAndFeel") + " " + lookAndFeel 
				+ ", [" + banco + "], "
				+ HFSUtil.getRecurso("SistemaParams.localidadeLingua") + " "
				+ localidadeLingua + ", "
				+ HFSUtil.getRecurso("SistemaParams.localidadePais") + " "
				+ localidadePais + ", "
				+ HFSUtil.getRecurso("SistemaParams.versao") + " " + versao;
	}

	public String getArquivoLog() {
		return arquivoLog;
	}

	public void setArquivoLog(String arquivoLog) {
		this.arquivoLog = arquivoLog;
	}

}
