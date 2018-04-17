package com.hfsgs.objetos;

public class BaseObjeto implements Comparable<Object>, Cloneable {
	private int codigo;

	private String descricao;

	public BaseObjeto() {
		this.limparDados();
	}

	public BaseObjeto(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public BaseObjeto(BaseObjeto obj) {
		this(obj.getCodigo(), obj.getDescricao());
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void limparDados() {
		this.codigo = -1;
		this.descricao = "";
	}

	public boolean equals(BaseObjeto obj) {
		if (obj.getCodigo() == this.getCodigo()
				&& obj.getDescricao().equals(this.getDescricao()))
			return true;
		else
			return false;
	}

	public String toString() {
		return this.descricao;
	}

	public Object clone() {
		if (descricao != null) {
			return new BaseObjeto(codigo, descricao);
		}
		return new BaseObjeto();
	}

	public int compareTo(Object outro) {
		return descricao.compareTo(((BaseObjeto) outro).descricao);
	}	
}
