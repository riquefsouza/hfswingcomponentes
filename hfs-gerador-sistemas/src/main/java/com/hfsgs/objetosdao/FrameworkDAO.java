package com.hfsgs.objetosdao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hfsgs.objetos.BaseObjeto;
import com.hfsgs.objetos.Framework;

public class FrameworkDAO extends BaseObjetoDAO {
	private static FrameworkDAO instancia;

	private FrameworkDAO() {
		super("Framework");
	}

	public static FrameworkDAO getInstancia() {
		if (instancia == null) {
			instancia = new FrameworkDAO();
		}
		return instancia;
	}

	public Framework pesquisar(int codigo) throws SQLException {
		return (Framework) super.pesquisar(codigo);
	}

	public ArrayList<Framework> listar() throws SQLException {
		ArrayList<Framework> lista = new ArrayList<Framework>();
		for (BaseObjeto item : super.listarArray()) {
			lista.add(new Framework(item));
		}
		return lista;
	}
	
	public Framework[] listarVetor() throws SQLException {
		ArrayList<Framework> lista = this.listar();
		return (Framework[]) lista.toArray(new Framework[lista.size()]);
	}
}
