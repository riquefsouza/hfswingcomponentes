package com.hfsgs.objetosdao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hfsgs.objetos.BaseObjeto;
import com.hfsgs.objetos.Projeto;

public class ProjetoDAO extends BaseObjetoDAO {
	private static ProjetoDAO instancia;

	private ProjetoDAO() {
		super("Projeto");
	}

	public static ProjetoDAO getInstancia() {
		if (instancia == null) {
			instancia = new ProjetoDAO();
		}
		return instancia;
	}

	public Projeto pesquisar(int codigo) throws SQLException {
		return (Projeto) super.pesquisar(codigo);
	}

	public ArrayList<Projeto> listar() throws SQLException {
		ArrayList<Projeto> lista = new ArrayList<Projeto>();
		for (BaseObjeto item : super.listarArray()) {
			lista.add(new Projeto(item));
		}
		return lista;
	}
	
	public Projeto[] listarVetor() throws SQLException {
		ArrayList<Projeto> lista = this.listar();
		return (Projeto[]) lista.toArray(new Projeto[lista.size()]);
	}
}
