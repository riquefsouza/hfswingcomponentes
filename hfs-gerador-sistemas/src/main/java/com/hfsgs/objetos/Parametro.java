package com.hfsgs.objetos;

import java.util.ArrayList;

import com.hfsgs.HFSGeradorSistemas;
import com.hfsgs.comum.Rotinas;

public class Parametro {

	private String diretorioUsuario;

	private String arquivoConfiguracao;
	
	private String arquivoLog;

	private double javaVersao;

	private String desktop;

	private String lookAndFeel;

	private ConexaoParams banco;

	private ArrayList<ConexaoParams> listaModelos;

	private String localidadeLingua;

	private String localidadePais;

	private String versao;

	public Parametro() {
		this.versao = "2.1.0";
		//this.diretorioUsuario = System.getProperty("user.dir");
		this.diretorioUsuario = Rotinas.pathDaClasse(HFSGeradorSistemas.class);
		int pos = this.diretorioUsuario.indexOf("/HFSGeradorSistemas.jar");
		if (pos > 1)
			this.diretorioUsuario= diretorioUsuario.substring(0, pos);		
		this.arquivoLog = "";
		this.arquivoConfiguracao = this.diretorioUsuario + "/HFSGeradorSistemas.conf";
		this.javaVersao = Double.parseDouble(System
				.getProperty("java.specification.version"));
		this.desktop = System.getProperty("sun.desktop");
		this.lookAndFeel = "metal";
		this.localidadeLingua = System.getProperty("user.language");
		this.localidadePais = System.getProperty("user.country");
		this.banco = new ConexaoParams();
		this.listaModelos = new ArrayList<ConexaoParams>();
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

	public String getLookAndFeel() {
		return lookAndFeel;
	}

	public void setLookAndFeel(String lookAndFeel) {
		this.lookAndFeel = lookAndFeel;
	}

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
		return listaModelos
				.toArray(new ConexaoParams[listaModelos.size()]);
	}

	public ConexaoParams getModelo(int indice) {
		return this.listaModelos.get(indice);
	}

	public void setModelo(ConexaoParams modelo) {
		this.listaModelos.add(modelo);
	}

	public void limparModelo() {
		this.listaModelos.clear();
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String toString() {
		return Rotinas.getRecurso("Parametro.desktop") + " " + desktop + ", "
				+ Rotinas.getRecurso("Parametro.javaVersao") + " " + javaVersao
				+ ", " + Rotinas.getRecurso("Parametro.diretorioUsuario") + " "
				+ diretorioUsuario + ", "
				+ Rotinas.getRecurso("Parametro.arquivoConfiguracao") + " "
				+ arquivoConfiguracao + ", "
				+ Rotinas.getRecurso("Parametro.lookAndFeel") + " "
				+ lookAndFeel + ", [" + banco + "], "
				+ Rotinas.getRecurso("Parametro.localidadeLingua") + " "
				+ localidadeLingua + ", "
				+ Rotinas.getRecurso("Parametro.localidadePais") + " "
				+ localidadePais + ", "
				+ Rotinas.getRecurso("Parametro.versao") + " " + versao;
	}

	public String getArquivoLog() {
		return arquivoLog;
	}

	public void setArquivoLog(String arquivoLog) {
		this.arquivoLog = arquivoLog;
	}

}
