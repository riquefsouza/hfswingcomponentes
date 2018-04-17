package com.hfsgs.objetosdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hfsgs.comum.Rotinas;
import com.hfsgs.objetos.BaseObjeto;

public abstract class BaseObjetoDAO {

	private Connection conexao;

	private PreparedStatement pstmt;

	private String sqlCriar;
	private String sqlIncluir;
	private String sqlAlterar;
	private String sqlExcluir;
	private String sqlPesquisar;
	private String sqlListar;

	public BaseObjetoDAO(String sqlCriar, String sqlIncluir, String sqlAlterar,
			String sqlExcluir, String sqlPesquisar, String sqlListar) {
		this.sqlCriar = sqlCriar;
		this.sqlIncluir = sqlIncluir;
		this.sqlAlterar = sqlAlterar;
		this.sqlExcluir = sqlExcluir;
		this.sqlPesquisar = sqlPesquisar;
		this.sqlListar = sqlListar;
	}

	public BaseObjetoDAO(String tabela) {
		this(
				"create table IF NOT EXISTS "
						+ tabela
						+ "(codigo integer not null, descricao varchar(60) not null, primary key (codigo))",
				"insert into " + tabela + "(codigo, descricao) values(?,?)",
				"update " + tabela + " set descricao=? where codigo=?",
				"delete from " + tabela + " where codigo=?",
				"select codigo, descricao from " + tabela + " where codigo=?",
				"select codigo, descricao from " + tabela);
	}

	public int criarTabela() throws SQLException {
		int ret = -1;
		conexao = Rotinas.getConexao();
		pstmt = conexao.prepareStatement(sqlCriar);
		ret = pstmt.executeUpdate();
		this.fecharSqlPreparado();
		return ret;
	}

	public int incluir(BaseObjeto obj) throws SQLException {
		int ret = -1;
		conexao = Rotinas.getConexao();
		pstmt = conexao.prepareStatement(sqlIncluir);
		pstmt.setInt(1, obj.getCodigo());
		pstmt.setString(2, obj.getDescricao());
		ret = pstmt.executeUpdate();
		this.fecharSqlPreparado();
		return ret;
	}

	public int alterar(BaseObjeto obj) throws SQLException {
		int ret = -1;
		conexao = Rotinas.getConexao();
		pstmt = conexao.prepareStatement(sqlAlterar);
		pstmt.setString(1, obj.getDescricao());
		pstmt.setInt(2, obj.getCodigo());
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

	private BaseObjeto novoObjeto(ResultSet res) throws SQLException {
		BaseObjeto ret = new BaseObjeto();
		ret.setCodigo(res.getInt(1));
		ret.setDescricao(res.getString(2));
		return ret;
	}

	public BaseObjeto pesquisar(int codigo) throws SQLException {
		BaseObjeto obj = null;
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

	public int atualizar(BaseObjeto obj) throws SQLException {
		if (!existe(obj.getCodigo()))
			return incluir(obj);
		else
			return alterar(obj);
	}

	public ArrayList<BaseObjeto> listarArray() throws SQLException {
		ArrayList<BaseObjeto> lista = new ArrayList<BaseObjeto>();
		conexao = Rotinas.getConexao();
		pstmt = conexao.prepareStatement(sqlListar);
		ResultSet res = pstmt.executeQuery();
		BaseObjeto obj = null;
		while (res.next()) {
			obj = novoObjeto(res);
			lista.add(obj);
		}
		this.fecharSqlPreparado();
		// return lista.toArray(new BaseObjeto[lista.size()]);
		return lista;
	}

	private void fecharSqlPreparado() throws SQLException {
		if (pstmt != null) {
			pstmt.close();
		}
	}

}
