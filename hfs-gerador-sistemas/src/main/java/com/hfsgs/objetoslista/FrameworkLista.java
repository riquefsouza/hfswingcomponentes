package com.hfsgs.objetoslista;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hfsgs.objetos.Framework;
import com.hfsgs.objetosdao.FrameworkDAO;

public class FrameworkLista extends ArrayList<Framework> {
	private static final long serialVersionUID = 1L;

	private static FrameworkLista instancia;

	private FrameworkLista() {
		super();
	}

	public static FrameworkLista getInstancia() {
		if (instancia == null) {
			instancia = new FrameworkLista();
		}
		return instancia;
	}

	public void carregar() throws SQLException {
		instancia.clear();
		instancia.addAll(FrameworkDAO.getInstancia().listar());
	}

	public Framework[] getArray() {
		return (Framework[]) this.toArray(new Framework[this.size()]);
	}

	public Framework getElemento(int codigo) {		
		for (Framework elemento: this) {
			if (elemento.getCodigo() == codigo) {
				return elemento;
			}
		}
		return null;
	}

	public boolean existe(int codigo) {
		return (getElemento(codigo) != null);
	}

	public boolean existe(Framework obj) {
		for (Framework elemento: this) {
			if (elemento.equals(obj)) {
				return true;
			}
		}
		return false;
	}

	public int getCodigoPorDescricao(String descricao) {
		for (Framework elemento: this) {
			if (elemento.getDescricao().equals(descricao)) {
				return elemento.getCodigo();
			}
		}
		return -1;
	}
	
}
