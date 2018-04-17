package com.hfsgs.objetos;

public class Sistema {

	private Projeto projeto;
	private Linguagem linguagem;
	private Framework framework;
	private Tela tela;
	private Banco banco;

	public Sistema() {
		super();
	}
	
	public Sistema(Projeto projeto, Linguagem linguagem, Framework framework,
			Tela tela, Banco banco) {
		super();
		this.projeto = projeto;
		this.linguagem = linguagem;
		this.framework = framework;
		this.tela = tela;
		this.banco = banco;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Linguagem getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(Linguagem linguagem) {
		this.linguagem = linguagem;
	}

	public Framework getFramework() {
		return framework;
	}

	public void setFramework(Framework framework) {
		this.framework = framework;
	}

	public Tela getTela() {
		return tela;
	}

	public void setTela(Tela tela) {
		this.tela = tela;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
}
