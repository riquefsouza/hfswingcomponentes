package com.hfswing.componentes;

public interface IHFSField extends IHFSComposite {

	public enum Caixa {
		PADRAO, MAIUSCULA, MINUSCULA, CAPITALIZAR
	}

	public enum Formato {
		PADRAO, SENHA, LETRA_NUMERO, LETRA, NUMERO, DECIMAL, EMAIL, MOEDA, DATA, HORA, DATAHORA, CPF, CNPJ, DATA_CALENDARIO, DECIMAL_CALCULADORA, CEP
	}
	
	public String getTexto();

	public void setTexto(String texto);

	public int getMaxLength();

	public void setMaxLength(int maxLength);

	public String getFiltro();

	public void setFiltro(String filtro);
	
	public boolean isEditavel();
	
	public void setEditavel(boolean editavel);

}
