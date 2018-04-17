package com.hfsgs.objetoslista;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hfsgs.objetos.Linguagem;
import com.hfsgs.objetosdao.LinguagemDAO;

public class LinguagemLista extends ArrayList<Linguagem> {
	private static final long serialVersionUID = 1L;

	private static LinguagemLista instancia;

	private LinguagemLista() {
		super();
	}

	public static LinguagemLista getInstancia() {
		if (instancia == null) {
			instancia = new LinguagemLista();
		}
		return instancia;
	}

	public void carregar() throws SQLException {
		instancia.clear();
		instancia.addAll(LinguagemDAO.getInstancia().listar());
	}

	public Linguagem[] getArray() {
		return (Linguagem[]) this.toArray(new Linguagem[this.size()]);
	}

	public Linguagem getElemento(int codigo) {		
		for (Linguagem elemento: this) {
			if (elemento.getCodigo() == codigo) {
				return elemento;
			}
		}
		return null;
	}

	public boolean existe(int codigo) {
		return (getElemento(codigo) != null);
	}

	public boolean existe(Linguagem obj) {
		for (Linguagem elemento: this) {
			if (elemento.equals(obj)) {
				return true;
			}
		}
		return false;
	}

	public int getCodigoPorDescricao(String descricao) {
		for (Linguagem elemento: this) {
			if (elemento.getDescricao().equals(descricao)) {
				return elemento.getCodigo();
			}
		}
		return -1;
	}
	
}
