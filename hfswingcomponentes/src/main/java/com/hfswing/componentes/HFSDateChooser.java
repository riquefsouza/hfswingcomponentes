package com.hfswing.componentes;

import java.awt.Dimension;

import com.toedter.calendar.JDateChooser;

public class HFSDateChooser extends HFSLabel {

	private static final long serialVersionUID = 1L;
	private JDateChooser dateChooser;

	public HFSDateChooser() {
		super();
		setSize(new Dimension(100, 35));
		add(getDateChooser());
	}

	public HFSDateChooser(PosicaoRotulo posicao, String rotulo, int largura,
			boolean obrigatorio) {
		super(posicao, rotulo, largura, obrigatorio);
		initComponents();
	}

	private void initComponents() {
		add(getDateChooser());
	}

	public JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.addFocusListener(mudarCorFoco());
		}
		return dateChooser;
	}

	public void setHabilitado(boolean habilitado) {
		super.setHabilitado(habilitado);
		this.getDateChooser().setEnabled(habilitado);
	}

	public void setFocado(boolean focado) {
		super.setFocado(focado);
		this.getDateChooser().setFocusable(focado);
	}

}
