package com.hfsgs;

import java.sql.SQLException;

import com.hfsgs.comum.Rotinas;
import com.hfsgs.objetos.ConexaoParams;
import com.hfsgs.objetos.Projeto;
import com.hfsgs.objetoslista.ProjetoLista;

public class Teste {

	public static void main(String[] args) {

		try {
			ConexaoParams cp = new ConexaoParams();
			cp.setDriver("org.sqlite.JDBC");
			cp.setUrl("jdbc:sqlite:gerador.sqlite");
			cp.setLogin("");
			cp.setSenha("");
			Rotinas.Conectar(cp);

			ProjetoLista.getInstancia().carregar();
			
			for (Projeto item : ProjetoLista.getInstancia()) {
				System.out.println(item);
			}

			Rotinas.Desconectar(Rotinas.getConexao());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
