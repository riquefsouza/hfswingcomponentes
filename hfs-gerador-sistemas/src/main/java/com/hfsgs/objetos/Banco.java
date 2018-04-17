package com.hfsgs.objetos;

public class Banco extends BaseObjeto {
	private String driver;

	private String url;

	private int porta;

	public Banco() {
		super();
		this.limparDados();
	}

	public Banco(int codigo, String descricao, String driver, String url,
			int porta) {
		super(codigo, descricao);
		this.driver = driver;
		this.url = url;
		this.porta = porta;
	}

	public Banco(BaseObjeto obj, String driver, String url, int porta) {
		super(obj);
		this.driver = driver;
		this.url = url;
		this.porta = porta;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public void limparDados() {
		super.limparDados();
		this.driver = "";
		this.url = "";
		this.porta = 0;
	}

	public boolean equals(Banco obj) {
		if (super.equals(obj) && obj.getDriver().equals(this.getDriver())
				&& obj.getUrl().equals(this.getUrl())
				&& obj.getPorta() == this.getPorta())
			return true;
		else
			return false;
	}

	public String toStringPares() {
		return "codigo = " + getCodigo() + ", descricao = " + getDescricao()
				+ ", driver = " + driver + ", url = " + url + ", porta = " + porta;
	}
	
	public String getText() {
		return "codigo = " + getCodigo() + "\ndescricao = " + getDescricao()
				+ "\ndriver = " + driver + "\nurl = " + url + "\nporta = " + porta + "\n";		
	}

}
