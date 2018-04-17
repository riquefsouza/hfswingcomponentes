package com.hfswing.componentes;

public interface IHFSLabel extends IHFSComposite {

	public enum PosicaoRotulo {
		ACIMA, ESQUERDA
	}

	public enum AlinhamentoRotulo {
		PADRAO, ESQUERDA, DIREITA, CENTRO
	}

	public int getLarguraRotulo();

	public void setLarguraRotulo(int larguraRotulo);

	public AlinhamentoRotulo getAlinhamentoRotulo();

	public void setAlinhamentoRotulo(AlinhamentoRotulo alinhamentoRotulo);
	
	public boolean isMostrarRotulo();
	
	public void setMostrarRotulo(boolean mostrarRotulo);
	
	public boolean isObrigatorio();
	
	public void setObrigatorio(boolean obrigatorio);

}
