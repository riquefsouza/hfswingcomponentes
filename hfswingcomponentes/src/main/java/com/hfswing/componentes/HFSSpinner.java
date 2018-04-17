package com.hfswing.componentes;

import java.awt.Dimension;

import javax.swing.JSpinner;

public class HFSSpinner extends HFSLabel {

	private static final long serialVersionUID = 1L;
	private JSpinner spinner;

	public HFSSpinner() {
		super();
		setSize(new Dimension(100, 35));
		add(getSpinner());
	}

	public HFSSpinner(PosicaoRotulo posicao, String rotulo, int largura,
			boolean obrigatorio) {
		super(posicao, rotulo, largura, obrigatorio);

		initComponents();
	}

	private void initComponents() {
		add(getSpinner());
	}

	public JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.addFocusListener(mudarCorFoco());
		}
		return spinner;
	}

	public void setHabilitado(boolean habilitado) {
		super.setHabilitado(habilitado);
		this.getSpinner().setEnabled(habilitado);
	}

	public void setFocado(boolean focado) {
		super.setFocado(focado);
		this.getSpinner().setFocusable(focado);
	}

}
