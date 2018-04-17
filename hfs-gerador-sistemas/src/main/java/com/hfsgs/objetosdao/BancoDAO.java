package com.hfsgs.objetosdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hfsgs.comum.Rotinas;
import com.hfsgs.objetos.Banco;

public class BancoDAO {
	private static BancoDAO instancia;

	private Connection conexao;

	private PreparedStatement pstmt;

	private static String sqlCriar = "create table IF NOT EXISTS Banco(codigo integer not null, "
			+ "descricao varchar(60) not null, driver varchar(60) not null, url varchar(60) not null, "
			+ "porta integer, primary key (codigo))";
	private static String sqlIncluir = "insert into Banco(codigo, descricao, driver, url, porta) values(?,?,?,?,?)";
	private static String sqlAlterar = "update Banco set descricao=?, driver=?, url=?, porta=? where codigo=?";
	private static String sqlExcluir = "delete from Banco where codigo=?";
	private static String sqlPesquisar = "select codigo, descricao, driver, url, porta from Banco where codigo=?";
	private static String sqlListar = "select codigo, descricao, driver, url, porta from Banco";

	public static BancoDAO getInstancia() {
		if (instancia == null) {
			instancia = new BancoDAO();
		}
		return instancia;
	}

	public int criarTabela() throws SQLException {
		int ret = -1;
		conexao = Rotinas.getConexao();
		pstmt = conexao.prepareStatement(sqlCriar);
		ret = pstmt.executeUpdate();
		this.fecharSqlPreparado();
		return ret;
	}

	public int incluir(Banco obj) throws SQLException {
		int ret = -1;
		conexao = Rotinas.getConexao();
		pstmt = conexao.prepareStatement(sqlIncluir);
		pstmt.setInt(1, obj.getCodigo());
		pstmt.setString(2, obj.getDescricao());
		pstmt.setString(3, obj.getDriver());
		pstmt.setString(4, obj.getUrl());
		pstmt.setInt(5, obj.getPorta());
		ret = pstmt.executeUpdate();
		this.fecharSqlPreparado();
		return ret;
	}

	public int alterar(Banco obj) throws SQLException {
		int ret = -1;
		conexao = Rotinas.getConexao();
		pstmt = conexao.prepareStatement(sqlAlterar);
		pstmt.setString(1, obj.getDescricao());
		pstmt.setString(2, obj.getDriver());
		pstmt.setString(3, obj.getUrl());
		pstmt.setInt(4, obj.getPorta());
		pstmt.setInt(5, obj.getCodigo());
		ret = pstmt.executeUpdate();
		this.fecharSqlPreparado();
		return ret;
	}

	public int excluir(int codigo) throws SQLException {
		int ret = -1;
		conexao = Rotinas.getConexao();
		pstmt = conexao.prepareStatement(sqlExcluir);
		pstmt.setInt(1, codigo);
		ret = pstmt.executeUpdate();
		this.fecharSqlPreparado();
		return ret;
	}

	public boolean existe(int codigo) throws SQLException {
		return (pesquisar(codigo) != null);
	}

	private Banco novoObjeto(ResultSet res) throws SQLException {
		Banco ret = new Banco();
		ret.setCodigo(res.getInt(1));
		ret.setDescricao(res.getString(2));
		ret.setDriver(res.getString(3));
		ret.setUrl(res.getString(4));
		ret.setPorta(res.getInt(5));
		return ret;
	}

	public Banco pesquisar(int codigo) throws SQLException {
		Banco obj = null;
		conexao = Rotinas.getConexao();
		pstmt = conexao.prepareStatement(sqlPesquisar);
		pstmt.setInt(1, codigo);
		ResultSet res = pstmt.executeQuery();
		if (res.next()) {
			obj = novoObjeto(res);
		}
		this.fecharSqlPreparado();

		return obj;
	}

	public int atualizar(Banco obj) throws SQLException {
		if (!existe(obj.getCodigo()))
			return incluir(obj);
		else
			return alterar(obj);
	}

	public ArrayList<Banco> listar() throws SQLException {
		ArrayList<Banco> lista = new ArrayList<Banco>();
		conexao = Rotinas.getConexao();
		pstmt = conexao.prepareStatement(sqlListar);
		ResultSet res = pstmt.executeQuery();
		Banco obj = null;
		while (res.next()) {
			obj = novoObjeto(res);
			lista.add(obj);
		}
		this.fecharSqlPreparado();
		return lista;		
	}
	
	public Banco[] listarVetor() throws SQLException {
		ArrayList<Banco> lista = this.listar();
		return lista.toArray(new Banco[lista.size()]);
	}

	private void fecharSqlPreparado() throws SQLException {
		if (pstmt != null) {
			pstmt.close();
		}
	}

}
