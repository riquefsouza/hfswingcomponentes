package com.hfsgs.comum;

import java.util.ArrayList;

/**
 * Classe que representa uma lista de strings em memória.
 * 
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 01/09/2011
 */
public class StringList extends ArrayList<String> {
	private static final long serialVersionUID = 7733886264192899676L;

	public StringList() {
		super();
	}

	public StringList(int capacidadeInicial) {
		super(capacidadeInicial);
	}

	public StringList(String str, char separador) {
		super();
		
		if (str.indexOf(separador) > 0) {
			char[] partes = str.toCharArray();
			String pedaco = "";
			for (int i = 0; i < partes.length; i++) {
				pedaco += partes[i];
				if (partes[i] == separador) {
					super.add(pedaco.substring(0,pedaco.length()-1));
					pedaco = "";
				}
			}
			super.add(pedaco);
		}
	}

	/**
	 * Adiciona uma string dentro da lista de strings.
	 * 
	 * @param str
	 *            objeto do mesmo tipo de classe
	 * @return boolean
	 */
	public boolean add(String str) {
		return super.add(str);
	}

	/**
	 * Pega uma string dentro da lista de strings.
	 * 
	 * @param index
	 *            o indice da string dentro da lista
	 * @return a string dentro da lista
	 */
	public String getString(int index) {
		return (String) super.get(index);
	}

	/**
	 * Pega todas as strings dentro da lista de strings e transforma num texto
	 * Único separados por quebra de linha.
	 * 
	 * @return o texto da união das strings separadas por quebra de linha
	 */
	public String getText() {
		String ret = "";

		for (int i = 0; i < super.size(); i++) {
			ret += this.getString(i) + "\n";
		}

		return ret;
	}

	/**
	 * @return a representação em texto puro da lista.
	 */
	public String toString() {
		String ret = "";

		for (int i = 0; i < super.size(); i++) {
			ret += this.getString(i);
		}

		return ret;
	}
	
	public String[] toStringArray(){
		return (String[]) super.toArray(new String[super.size()]);
	}
}