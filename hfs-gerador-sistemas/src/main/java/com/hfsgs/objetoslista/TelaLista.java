package com.hfsgs.objetoslista;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hfsgs.objetos.Tela;
import com.hfsgs.objetosdao.TelaDAO;

public class TelaLista extends ArrayList<Tela> {
	private static final long serialVersionUID = 1L;

	private static TelaLista instancia;

	private TelaLista() {
		super();
	}

	public static TelaLista getInstancia() {
		if (instancia == null) {
			instancia = new TelaLista();
		}
		return instancia;
	}

	public void carregar() throws SQLException {
		instancia.clear();
		instancia.addAll(TelaDAO.getInstancia().listar());
	}

	public Tela[] getArray() {
		return (Tela[]) this.toArray(new Tela[this.size()]);
	}

	public Tela getElemento(int codigo) {		
		for (Tela elemento: this) {
			if (elemento.getCodigo() == codigo) {
				return elemento;
			}
		}
		return null;
	}

	public boolean existe(int codigo) {
		return (getElemento(codigo) != null);
	}

	public boolean existe(Tela obj) {
		for (Tela elemento: this) {
			if (elemento.equals(obj)) {
				return true;
			}
		}
		return false;
	}

	public int getCodigoPorDescricao(String descricao) {
		for (Tela elemento: this) {
			if (elemento.getDescricao().equals(descricao)) {
				return elemento.getCodigo();
			}
		}
		return -1;
	}
	
}
