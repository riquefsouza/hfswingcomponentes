package com.hfswing.util;

public class SistemaPropriedade implements Comparable<Object>, Cloneable {
	private String nome;

	private String valor;

	public SistemaPropriedade() {
		this.nome = "";
		this.valor = "";
	}

	public SistemaPropriedade(String nome, String valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String toString() {
		return nome + "=" + valor;
	}

	public Object clone() {
		if (nome != null && valor != null) {
			return new SistemaPropriedade(nome, valor);
		}
		return new SistemaPropriedade();
	}

	public int compareTo(Object outro) {
		return nome.compareTo(((SistemaPropriedade) outro).nome);
	}
}
