package com.hfsgs.objetosdao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hfsgs.objetos.BaseObjeto;
import com.hfsgs.objetos.Tela;

public class TelaDAO extends BaseObjetoDAO {
	private static TelaDAO instancia;

	private TelaDAO() {
		super("Tela");
	}

	public static TelaDAO getInstancia() {
		if (instancia == null) {
			instancia = new TelaDAO();
		}
		return instancia;
	}

	public ArrayList<Tela> listar() throws SQLException {
		ArrayList<Tela> lista = new ArrayList<Tela>();
		for (BaseObjeto item : super.listarArray()) {
			lista.add(new Tela(item));
		}
		return lista;
	}
	
	public Tela[] listarVetor() throws SQLException {
		ArrayList<Tela> lista = this.listar();
		return (Tela[]) lista.toArray(new Tela[lista.size()]);
	}
}
