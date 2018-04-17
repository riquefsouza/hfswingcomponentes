package com.hfswing.componentes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.hfswing.util.HFSConst;

public class HFSLabel extends JPanel implements IHFSLabel {

	private static final long serialVersionUID = 1L;
	private JLabel rotulo;

	private boolean habilitado;
	private boolean focado;
	private boolean mostrarRotulo;
	private boolean obrigatorio;
	private int larguraRotulo;
	private AlinhamentoRotulo alinhamentoRotulo;
	private PosicaoRotulo posicaoRotulo;

	public HFSLabel() {
		setSize(new Dimension(100, 35));
		this.habilitado = true;
		this.focado = true;
		this.mostrarRotulo = true;

		this.obrigatorio = false;
		this.posicaoRotulo = PosicaoRotulo.ACIMA;
		this.alinhamentoRotulo = AlinhamentoRotulo.PADRAO;

		setLayout(new BorderLayout(0, 0));
		add(getRotulo(), BorderLayout.NORTH);

		this.larguraRotulo = this.getRotulo().getWidth();
	}

	public HFSLabel(PosicaoRotulo posicao, String rotulo, int largura,
			boolean obrigatorio) {
		if (largura == 0)
			largura = 100;
		this.setSize(largura, 35);

		this.habilitado = true;
		this.focado = true;
		this.mostrarRotulo = true;
		this.obrigatorio = obrigatorio;
		this.posicaoRotulo = posicao;
		this.alinhamentoRotulo = AlinhamentoRotulo.PADRAO;

		initComponents(posicao);

		this.setRotulo(rotulo);
		this.larguraRotulo = this.getRotulo().getWidth();
	}

	private void initComponents(PosicaoRotulo posicao) {
		setLayout(new BorderLayout(0, 0));
		if (posicao == PosicaoRotulo.ACIMA) {
			add(getRotulo(), BorderLayout.NORTH);
		} else if (posicao == PosicaoRotulo.ESQUERDA) {
			add(getRotulo(), BorderLayout.WEST);
		}
	}

	private JLabel getRotulo() {
		if (rotulo == null) {
			rotulo = new JLabel("Rótulo:");
		}
		return rotulo;
	}

	private void setRotulo(String rotulo) {
		if (this.obrigatorio) {
			rotulo = "<html><b>" + rotulo
					+ "</b><span style='color: rgb(255, 0, 0);'>*</span>";
		}
		getRotulo().setText(rotulo);
	}

	@Override
	public int getLarguraRotulo() {
		return larguraRotulo;
	}

	@Override
	public void setLarguraRotulo(int larguraRotulo) {
		this.larguraRotulo = larguraRotulo;
		this.getRotulo().setSize(larguraRotulo, this.getRotulo().getHeight());
	}

	@Override
	public AlinhamentoRotulo getAlinhamentoRotulo() {
		return alinhamentoRotulo;
	}

	@Override
	public void setAlinhamentoRotulo(AlinhamentoRotulo alinhamentoRotulo) {
		this.alinhamentoRotulo = alinhamentoRotulo;
		switch (alinhamentoRotulo) {
		case ESQUERDA:
			if (posicaoRotulo == PosicaoRotulo.ACIMA)
				getRotulo().setHorizontalAlignment(SwingConstants.LEFT);
			else
				getRotulo().setVerticalAlignment(SwingConstants.TOP);
			break;
		case CENTRO:
			if (posicaoRotulo == PosicaoRotulo.ACIMA)
				getRotulo().setHorizontalAlignment(SwingConstants.CENTER);
			else
				getRotulo().setVerticalAlignment(SwingConstants.CENTER);
			break;
		case DIREITA:
			if (posicaoRotulo == PosicaoRotulo.ACIMA)
				getRotulo().setHorizontalAlignment(SwingConstants.RIGHT);
			else
				getRotulo().setVerticalAlignment(SwingConstants.BOTTOM);
			break;
		case PADRAO:
			break;
		}
	}

	@Override
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	@Override
	public boolean isHabilitado() {
		return this.habilitado;
	}

	@Override
	public void setFocado(boolean focado) {
		this.focado = focado;
	}

	@Override
	public boolean isFocado() {
		return this.focado;
	}

	@Override
	public boolean isMostrarRotulo() {
		return mostrarRotulo;
	}

	@Override
	public void setMostrarRotulo(boolean mostrarRotulo) {
		this.mostrarRotulo = mostrarRotulo;
		this.getRotulo().setVisible(this.mostrarRotulo);
	}

	@Override
	public void setTamanho(int largura, int altura) {
		this.setSize(largura, altura);
	}

	@Override
	public boolean isObrigatorio() {
		return obrigatorio;
	}

	@Override
	public void setObrigatorio(boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
		this.setRotulo(getRotulo().getText());
	}

	protected FocusAdapter mudarCorFoco() {
		return new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent evt) {
				evt.getComponent().setBackground(HFSConst.COR_FOCADO);
			}

			@Override
			public void focusLost(FocusEvent evt) {
				String nome = evt.getComponent().getClass().getSimpleName().substring(1)
						+ ".background";
				evt.getComponent().setBackground(UIManager.getColor(nome));
			}
		};
	}
}
