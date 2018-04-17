package com.hfswing.componentes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class HFSCalculator extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel edtPainel;
	private JLabel edtPainelFormatado;
	private JPanel gridPanel;
	private HFSButton btnC;
	private HFSButton btnMemoryRead;
	private HFSButton btnMemorySub;
	private HFSButton btnMemoryAdd;
	private HFSButton btn7;
	private HFSButton btn8;
	private HFSButton btn9;
	private HFSButton btnDivide;
	private HFSButton btn4;
	private HFSButton btn5;
	private HFSButton btn6;
	private HFSButton btnMultiplica;
	private HFSButton btn1;
	private HFSButton btn2;
	private HFSButton btn3;
	private HFSButton btnSubtracao;
	private HFSButton btn0;
	private HFSButton btnPonto;
	private HFSButton btnIgual;
	private HFSButton btnSoma;

	private String sText1, sText2;
	private double dReg1, dReg2, dMem;
	private String sOperator;
	private boolean isFixReg;

	private boolean decimalComVirgula;

	public enum BotaoTamanho {
		GRANDE, PEQUENO
	}

	private BotaoTamanho btamanho;

	public interface BtnClickListener extends EventListener {
		void onBtnIgualClick();

		void onBotoesClick(double valor, String valorFormatado);
	}

	private BtnClickListener btnClickListener;
	
	public HFSCalculator() {
		this(BotaoTamanho.GRANDE);
	}
	
	public HFSCalculator(BotaoTamanho botaoTamanho) {
		this.decimalComVirgula = true;
		this.btamanho = botaoTamanho;
		initComponents();

		dReg1 = 0.0d;
		dReg2 = 0.0d;
		dMem = 0.0d;
		sOperator = "";
		isFixReg = true;
	}

	private void initComponents() {
		setSize(new Dimension(210, 180));
		setLayout(new BorderLayout(0, 0));
		add(getEdtPainel(), BorderLayout.NORTH);
		add(getGridPanel(), BorderLayout.CENTER);
	}

	private JLabel getEdtPainel() {
		if (edtPainel == null) {
			edtPainel = new JLabel("0");
			edtPainel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
					null, null, null));
			edtPainel.setHorizontalAlignment(SwingConstants.RIGHT);
			edtPainel.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return edtPainel;
	}
	
	private JLabel getEdtPainelFormatado() {
		if (edtPainelFormatado == null) {
			edtPainelFormatado = new JLabel("0");
			edtPainelFormatado.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
					null, null, null));
			edtPainelFormatado.setHorizontalAlignment(SwingConstants.RIGHT);
			edtPainelFormatado.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return edtPainelFormatado;
	}

	private JPanel getGridPanel() {
		if (gridPanel == null) {
			gridPanel = new JPanel();
			gridPanel.setLayout(new GridLayout(5, 4, 0, 0));
			gridPanel.add(getBtnC());
			gridPanel.add(getBtnMemoryRead());
			gridPanel.add(getBtnMemorySub());
			gridPanel.add(getBtnMemoryAdd());
			gridPanel.add(getBtn7());
			gridPanel.add(getBtn8());
			gridPanel.add(getBtn9());
			gridPanel.add(getBtnDivide());
			gridPanel.add(getBtn4());
			gridPanel.add(getBtn5());
			gridPanel.add(getBtn6());
			gridPanel.add(getBtnMultiplica());
			gridPanel.add(getBtn1());
			gridPanel.add(getBtn2());
			gridPanel.add(getBtn3());
			gridPanel.add(getBtnSubtracao());
			gridPanel.add(getBtn0());
			gridPanel.add(getBtnPonto());
			gridPanel.add(getBtnIgual());
			gridPanel.add(getBtnSoma());
		}
		return gridPanel;
	}

	private void mudarTamanho(HFSButton btn) {
		if (this.btamanho == BotaoTamanho.PEQUENO)
			btn.setSize(35, 26);
		else
			btn.setSize(44, 28);
	}
	
	private HFSButton getBtnC() {
		if (btnC == null) {
			btnC = new HFSButton();
			btnC.setFont(btnC.getFont().deriveFont(
					btnC.getFont().getStyle() | Font.BOLD));
			btnC.setText("C");
			btnC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("C");
				}
			});
			mudarTamanho(btnC);
		}
		return btnC;
	}



	private HFSButton getBtnMemoryRead() {
		if (btnMemoryRead == null) {
			btnMemoryRead = new HFSButton();
			btnMemoryRead.setText("MR");
			btnMemoryRead.setFont(btnMemoryRead.getFont().deriveFont(
					btnMemoryRead.getFont().getStyle() | Font.BOLD));
			btnMemoryRead.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("MR");
				}
			});
			mudarTamanho(btnMemoryRead);
		}
		return btnMemoryRead;
	}

	private HFSButton getBtnMemorySub() {
		if (btnMemorySub == null) {
			btnMemorySub = new HFSButton();
			btnMemorySub.setFont(btnMemorySub.getFont().deriveFont(
					btnMemorySub.getFont().getStyle() | Font.BOLD));
			btnMemorySub.setText("M-");
			btnMemorySub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("M-");
				}
			});
			mudarTamanho(btnMemorySub);
		}
		return btnMemorySub;
	}

	private HFSButton getBtnMemoryAdd() {
		if (btnMemoryAdd == null) {
			btnMemoryAdd = new HFSButton();
			btnMemoryAdd.setFont(btnMemoryAdd.getFont().deriveFont(
					btnMemoryAdd.getFont().getStyle() | Font.BOLD));
			btnMemoryAdd.setText("M+");
			btnMemoryAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("M+");
				}
			});
			mudarTamanho(btnMemoryAdd);
		}
		return btnMemoryAdd;
	}

	private HFSButton getBtn7() {
		if (btn7 == null) {
			btn7 = new HFSButton();
			btn7.setFont(btn7.getFont().deriveFont(
					btn7.getFont().getStyle() | Font.BOLD));
			btn7.setText("7");
			btn7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("7");
				}
			});
			mudarTamanho(btn7);
		}
		return btn7;
	}

	private HFSButton getBtn8() {
		if (btn8 == null) {
			btn8 = new HFSButton();
			btn8.setFont(btn8.getFont().deriveFont(
					btn8.getFont().getStyle() | Font.BOLD));
			btn8.setText("8");
			btn8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("8");
				}
			});
			mudarTamanho(btn8);
		}
		return btn8;
	}

	private HFSButton getBtn9() {
		if (btn9 == null) {
			btn9 = new HFSButton();
			btn9.setFont(btn9.getFont().deriveFont(
					btn9.getFont().getStyle() | Font.BOLD));
			btn9.setText("9");
			btn9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("9");
				}
			});
			mudarTamanho(btn9);
		}
		return btn9;
	}

	private HFSButton getBtnDivide() {
		if (btnDivide == null) {
			btnDivide = new HFSButton();
			btnDivide.setFont(btnDivide.getFont().deriveFont(
					btnDivide.getFont().getStyle() | Font.BOLD));
			btnDivide.setText("/");
			btnDivide.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("/");
				}
			});
			mudarTamanho(btnDivide);
		}
		return btnDivide;
	}

	private HFSButton getBtn4() {
		if (btn4 == null) {
			btn4 = new HFSButton();
			btn4.setFont(btn4.getFont().deriveFont(
					btn4.getFont().getStyle() | Font.BOLD));
			btn4.setText("4");
			btn4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("4");
				}
			});
			mudarTamanho(btn4);
		}
		return btn4;
	}

	private HFSButton getBtn5() {
		if (btn5 == null) {
			btn5 = new HFSButton();
			btn5.setFont(btn5.getFont().deriveFont(
					btn5.getFont().getStyle() | Font.BOLD));
			btn5.setText("5");
			btn5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("5");
				}
			});
			mudarTamanho(btn5);
		}
		return btn5;
	}

	private HFSButton getBtn6() {
		if (btn6 == null) {
			btn6 = new HFSButton();
			btn6.setFont(btn6.getFont().deriveFont(
					btn6.getFont().getStyle() | Font.BOLD));
			btn6.setText("6");
			btn6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("6");
				}
			});
			mudarTamanho(btn6);
		}
		return btn6;
	}

	private HFSButton getBtnMultiplica() {
		if (btnMultiplica == null) {
			btnMultiplica = new HFSButton();
			btnMultiplica.setFont(btnMultiplica.getFont().deriveFont(
					btnMultiplica.getFont().getStyle() | Font.BOLD));
			btnMultiplica.setText("*");
			btnMultiplica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("*");
				}
			});
			mudarTamanho(btnMultiplica);
		}
		return btnMultiplica;
	}

	private HFSButton getBtn1() {
		if (btn1 == null) {
			btn1 = new HFSButton();
			btn1.setFont(btn1.getFont().deriveFont(
					btn1.getFont().getStyle() | Font.BOLD));
			btn1.setText("1");
			btn1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("1");
				}
			});
			mudarTamanho(btn1);
		}
		return btn1;
	}

	private HFSButton getBtn2() {
		if (btn2 == null) {
			btn2 = new HFSButton();
			btn2.setFont(btn2.getFont().deriveFont(
					btn2.getFont().getStyle() | Font.BOLD));
			btn2.setText("2");
			btn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("2");
				}
			});
			mudarTamanho(btn2);
		}
		return btn2;
	}

	private HFSButton getBtn3() {
		if (btn3 == null) {
			btn3 = new HFSButton();
			btn3.setFont(btn3.getFont().deriveFont(
					btn3.getFont().getStyle() | Font.BOLD));
			btn3.setText("3");
			btn3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("3");
				}
			});
			mudarTamanho(btn3);
		}
		return btn3;
	}

	private HFSButton getBtnSubtracao() {
		if (btnSubtracao == null) {
			btnSubtracao = new HFSButton();
			btnSubtracao.setFont(btnSubtracao.getFont().deriveFont(
					btnSubtracao.getFont().getStyle() | Font.BOLD));
			btnSubtracao.setText("-");
			btnSubtracao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("-");
				}
			});
			mudarTamanho(btnSubtracao);
		}
		return btnSubtracao;
	}

	private HFSButton getBtn0() {
		if (btn0 == null) {
			btn0 = new HFSButton();
			btn0.setFont(btn0.getFont().deriveFont(
					btn0.getFont().getStyle() | Font.BOLD));
			btn0.setText("0");
			btn0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("0");
				}
			});
			mudarTamanho(btn0);
		}
		return btn0;
	}

	private HFSButton getBtnPonto() {
		if (btnPonto == null) {
			btnPonto = new HFSButton();
			btnPonto.setFont(btnPonto.getFont().deriveFont(
					btnPonto.getFont().getStyle() | Font.BOLD));
			
			if (decimalComVirgula)
				btnPonto.setText(",");
			else
				btnPonto.setText(".");
			
			btnPonto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao(".");
				}
			});
			mudarTamanho(btnPonto);
		}
		return btnPonto;
	}

	private HFSButton getBtnIgual() {
		if (btnIgual == null) {
			btnIgual = new HFSButton();
			btnIgual.setFont(btnIgual.getFont().deriveFont(
					btnIgual.getFont().getStyle() | Font.BOLD));
			btnIgual.setText("=");
			btnIgual.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("=");
					if (btnClickListener != null) {
						btnClickListener.onBtnIgualClick();
					}
				}
			});
			mudarTamanho(btnIgual);
		}
		return btnIgual;
	}

	private HFSButton getBtnSoma() {
		if (btnSoma == null) {
			btnSoma = new HFSButton();
			btnSoma.setFont(btnSoma.getFont().deriveFont(
					btnSoma.getFont().getStyle() | Font.BOLD));
			btnSoma.setText("+");
			btnSoma.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					acao("+");
				}
			});
			mudarTamanho(btnSoma);
		}
		return btnSoma;
	}
	
	private void acao(String tecla) {
		//
		// tecla numerica
		//
		if ("C".equals(tecla)) {
			dReg1 = 0.0d;
			dReg2 = 0.0d;
			sOperator = "";
			this.getEdtPainel().setText("0");			
			isFixReg = true;
		} else if (("0".equals(tecla)) | ("1".equals(tecla))
				| ("2".equals(tecla)) | ("3".equals(tecla))
				| ("4".equals(tecla)) | ("5".equals(tecla))
				| ("6".equals(tecla)) | ("7".equals(tecla))
				| ("8".equals(tecla)) | ("9".equals(tecla))
				| (".".equals(tecla))) {
			if (isFixReg)
				sText2 = (String) tecla;
			else
				sText2 = this.getEdtPainel().getText() + tecla;
			this.getEdtPainel().setText(sText2);
			isFixReg = false;
		}
		//
		// operacoes
		//
		else if (("+".equals(tecla)) | ("-".equals(tecla))
				| ("*".equals(tecla)) | ("/".equals(tecla))
				| ("=".equals(tecla))) {
			sText1 = this.getEdtPainel().getText();
			dReg2 = (Double.valueOf(sText1)).doubleValue();
			dReg1 = realizarCalculo(sOperator, dReg1, dReg2);
			Double dTemp = new Double(dReg1);
			sText2 = dTemp.toString();
			this.getEdtPainel().setText(sText2);
			sOperator = (String) tecla;
			isFixReg = true;
		}
		//
		// operacao memory read
		//
		else if ("MR".equals(tecla)) {
			Double dTemp = new Double(dMem);
			sText2 = dTemp.toString();
			this.getEdtPainel().setText(sText2);
			sOperator = "";
			isFixReg = true;
		}
		//
		// operacao memory add
		//
		else if ("M+".equals(tecla)) {
			sText1 = this.getEdtPainel().getText();
			dReg2 = (Double.valueOf(sText1)).doubleValue();
			dReg1 = realizarCalculo(sOperator, dReg1, dReg2);
			Double dTemp = new Double(dReg1);
			sText2 = dTemp.toString();
			this.getEdtPainel().setText(sText2);
			dMem = dMem + dReg1;
			sOperator = "";
			isFixReg = true;
		}
		//
		// operacao memory sub
		//
		else if ("M-".equals(tecla)) {
			sText1 = this.getEdtPainel().getText();
			dReg2 = (Double.valueOf(sText1)).doubleValue();
			dReg1 = realizarCalculo(sOperator, dReg1, dReg2);
			Double dTemp = new Double(dReg1);
			sText2 = dTemp.toString();
			this.getEdtPainel().setText(sText2);
			dMem = dMem - dReg1;
			sOperator = "";
			isFixReg = true;
		}

		formataNumero(this.getEdtPainel().getText());
	}

	private boolean formataNumero(String sValor) {
		double nvalor = 0;
		String valorFormatado = "";
		try {
			if (this.decimalComVirgula) {
				nvalor = Double.parseDouble(sValor.replace(',', '.'));								
				valorFormatado = String.format("%.2f", nvalor).replace('.', ','); 
				this.getEdtPainelFormatado().setText(valorFormatado);
				
				if (btnClickListener != null) {
					nvalor = Double.parseDouble(valorFormatado.replace(',', '.'));
					btnClickListener.onBotoesClick(nvalor, valorFormatado);
				}			
				
			} else {
				nvalor = Double.parseDouble(sValor);
				valorFormatado =  String.format("%.2f", nvalor);
				this.getEdtPainelFormatado().setText(valorFormatado);
				
				if (btnClickListener != null) {
					btnClickListener.onBotoesClick(nvalor, valorFormatado);
				}			
				
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// -------------
	// Calculo
	// -------------
	private double realizarCalculo(String sOperator, double dReg1, double dReg2) {
		if ("+".equals(sOperator))
			dReg1 = dReg1 + dReg2;
		else if ("-".equals(sOperator))
			dReg1 = dReg1 - dReg2;
		else if ("*".equals(sOperator))
			dReg1 = dReg1 * dReg2;
		else if ("/".equals(sOperator))
			dReg1 = dReg1 / dReg2;
		else
			dReg1 = dReg2;
		return dReg1;
	}

	public boolean isDecimalComVirgula() {
		return decimalComVirgula;
	}

	public void setDecimalComVirgula(boolean decimalComVirgula) {
		this.decimalComVirgula = decimalComVirgula;
	}

	public void addBtnClickListener(BtnClickListener listener) {
		this.btnClickListener = listener;
	}

	public JPopupMenu getPopup(final boolean bCalcSelecionada) {
		JPopupMenu popup = new JPopupMenu() {
			private static final long serialVersionUID = -6078272560337577761L;

			public void setVisible(boolean b) {
				Boolean isCanceled = (Boolean) getClientProperty("JPopupMenu.firePopupMenuCanceled");
				if (b
						|| (!b && bCalcSelecionada)
						|| ((isCanceled != null) && !b && isCanceled
								.booleanValue())) {
					super.setVisible(b);
				}
			}
		};
		popup.setLightWeightPopupEnabled(true);
		popup.add(getGridPanel());
		return popup;
	}

	public void setValor(double valor) {
		this.getEdtPainel().setText(Double.toString(valor));
	}

}
