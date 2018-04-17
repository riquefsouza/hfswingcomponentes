package com.hfsgs.objetoslista;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hfsgs.objetos.Projeto;
import com.hfsgs.objetosdao.ProjetoDAO;

public class ProjetoLista extends ArrayList<Projeto> {
	private static final long serialVersionUID = 1L;

	private static ProjetoLista instancia;

	private ProjetoLista() {
		super();
	}

	public static ProjetoLista getInstancia() {
		if (instancia == null) {
			instancia = new ProjetoLista();
		}
		return instancia;
	}

	public void carregar() throws SQLException {
		instancia.clear();
		instancia.addAll(ProjetoDAO.getInstancia().listar());
	}

	public Projeto[] getArray() {
		return (Projeto[]) this.toArray(new Projeto[this.size()]);
	}

	public Projeto getElemento(int codigo) {		
		for (Projeto elemento: this) {
			if (elemento.getCodigo() == codigo) {
				return elemento;
			}
		}
		return null;
	}

	public boolean existe(int codigo) {
		return (getElemento(codigo) != null);
	}

	public boolean existe(Projeto obj) {
		for (Projeto elemento: this) {
			if (elemento.equals(obj)) {
				return true;
			}
		}
		return false;
	}

	public int getCodigoPorDescricao(String descricao) {
		for (Projeto elemento: this) {
			if (elemento.getDescricao().equals(descricao)) {
				return elemento.getCodigo();
			}
		}
		return -1;
	}
	
}
