package com.hfswing.componentes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.hfswing.componentes.HFSCalculator.BotaoTamanho;
import com.hfswing.util.HFSUtil;

public class HFSCalculatorChooser extends HFSLabel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	private JButton btnCalculadora;
	private HFSCalculator calculadora;
	private JPopupMenu popup;
	protected boolean bCalcSelecionada;

	public HFSCalculatorChooser() {
		super();
		setSize(new Dimension(100, 35));
		add(getTextField());
		add(getBtnCalculadora(), BorderLayout.EAST);
	}

	public HFSCalculatorChooser(PosicaoRotulo posicao, String rotulo,
			int largura, boolean obrigatorio) {
		super(posicao, rotulo, largura, obrigatorio);
		
		initComponents();
	}

	private void initComponents() {
		add(getTextField());
		add(getBtnCalculadora(), BorderLayout.EAST);
	}

	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.RIGHT);
			textField.setColumns(10);
			textField.addFocusListener(mudarCorFoco());
		}
		return textField;
	}

	public void setHabilitado(boolean habilitado) {
		super.setHabilitado(habilitado);
		this.getTextField().setEnabled(habilitado);
		this.getBtnCalculadora().setEnabled(habilitado);
	}

	public void setFocado(boolean focado) {
		super.setFocado(focado);
		this.getTextField().setFocusable(focado);
	}

	private JButton getBtnCalculadora() {
		if (btnCalculadora == null) {
			btnCalculadora = new JButton() {
				private static final long serialVersionUID = -1913767779079949668L;

				public boolean isFocusable() {
					return false;
				}
			};
			btnCalculadora.setIcon(HFSUtil
					.getImagem("HFSTextBox-Calculadora.png"));
			// btnCalculadora.setIcon(new
			// ImageIcon(HFSCalculatorChooser.class.getResource("/com/hfswing/recursos/imagens/HFSTextBox-Calculadora.png")));
			btnCalculadora.setMargin(new Insets(0, 0, 0, 0));
			btnCalculadora.addActionListener(this);
			btnCalculadora.setMnemonic(KeyEvent.VK_C);
			btnCalculadora.setMargin(new Insets(0, 0, 0, 0));
		}
		return btnCalculadora;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		int x = btnCalculadora.getWidth()
				- (int) getCalculadora().getPopup(bCalcSelecionada).getPreferredSize().getWidth();
		int y = btnCalculadora.getY();// + btnCalculadora.getHeight();
		
		if (textField.getText().length() > 0) {
			double valor = Double.parseDouble(textField.getText());		
			calculadora.setValor(valor);
		}
		
		getCalculadora().getPopup(bCalcSelecionada).show(btnCalculadora, x, y);
		bCalcSelecionada = false;
	}

	private HFSCalculator getCalculadora() {
		if (calculadora == null) {
			calculadora = new HFSCalculator(BotaoTamanho.PEQUENO);
			//calculadora.setDecimalComVirgula(this.decimalComVirgula);
			calculadora.addBtnClickListener(new HFSCalculator.BtnClickListener() {
				@Override
				public void onBtnIgualClick() {
					//getCalculadora().getPopup(false).setVisible(false);
				}

				@Override
				public void onBotoesClick(double valor, String valorFormatado) {
					getTextField().setText(Double.toString(valor));
				}
			});

			if (getTextField().getText().trim().length() == 0)
				calculadora.setValor(0);
			else
				calculadora.setValor(Double.parseDouble(this.getTextField().getText()));
		}
		return calculadora;
	}

	public void updateUI() {
		super.updateUI();
		setEnabled(isEnabled());

		if (calculadora != null) {
			SwingUtilities.updateComponentTreeUI(popup);
		}
	}

	public void setLocale(Locale l) {
		super.setLocale(l);
		calculadora.setLocale(l);
	}
}
