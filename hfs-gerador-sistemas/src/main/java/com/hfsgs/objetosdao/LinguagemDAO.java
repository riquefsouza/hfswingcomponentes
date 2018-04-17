package com.hfsgs.objetosdao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hfsgs.objetos.BaseObjeto;
import com.hfsgs.objetos.Linguagem;

public class LinguagemDAO extends BaseObjetoDAO {
	private static LinguagemDAO instancia;

	private LinguagemDAO() {
		super("Linguagem");
	}

	public static LinguagemDAO getInstancia() {
		if (instancia == null) {
			instancia = new LinguagemDAO();
		}
		return instancia;
	}

	public Linguagem pesquisar(int codigo) throws SQLException {
		return (Linguagem) super.pesquisar(codigo);
	}

	public ArrayList<Linguagem> listar() throws SQLException {
		ArrayList<Linguagem> lista = new ArrayList<Linguagem>();
		for (BaseObjeto item : super.listarArray()) {
			lista.add(new Linguagem(item));
		}
		return lista;
	}
	
	public Linguagem[] listarVetor() throws SQLException {
		ArrayList<Linguagem> lista = this.listar();
		return (Linguagem[]) lista.toArray(new Linguagem[lista.size()]);
	}
}
