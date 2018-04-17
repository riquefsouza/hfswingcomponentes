package com.hfsgs.objetoslista;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hfsgs.objetos.Banco;
import com.hfsgs.objetosdao.BancoDAO;

public class BancoLista extends ArrayList<Banco> {
	private static final long serialVersionUID = 1L;

	private static BancoLista instancia;

	private BancoLista() {
		super();
	}

	public static BancoLista getInstancia() {
		if (instancia == null) {
			instancia = new BancoLista();
		}
		return instancia;
	}

	public void carregar() throws SQLException {
		instancia.clear();
		instancia.addAll(BancoDAO.getInstancia().listar());
	}

	public Banco[] getArray() {
		return (Banco[]) this.toArray(new Banco[this.size()]);
	}

	public Banco getElemento(int codigo) {		
		for (Banco elemento: this) {
			if (elemento.getCodigo() == codigo) {
				return elemento;
			}
		}
		return null;
	}

	public boolean existe(int codigo) {
		return (getElemento(codigo) != null);
	}

	public boolean existe(Banco obj) {
		for (Banco elemento: this) {
			if (elemento.equals(obj)) {
				return true;
			}
		}
		return false;
	}

	public int getCodigoPorDescricao(String descricao) {
		for (Banco elemento: this) {
			if (elemento.getDescricao().equals(descricao)) {
				return elemento.getCodigo();
			}
		}
		return -1;
	}
	
}
