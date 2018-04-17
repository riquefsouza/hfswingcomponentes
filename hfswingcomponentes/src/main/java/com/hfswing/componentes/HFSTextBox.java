package com.hfswing.componentes;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.hfswing.util.HFSConst;
import com.hfswing.util.HFSUtil;
import com.hfswing.util.HFSValida;
import com.toedter.calendar.JDateChooser;

public class HFSTextBox extends HFSLabel {

	private static final long serialVersionUID = 1L;
	private HFSTextField caixaTexto;
	private HFSPasswordField caixaSenha;
	private HFSCalculatorChooser calculadora;
	private JDateChooser calendario;

	public enum Caixa {
		PADRAO, MAIUSCULA, MINUSCULA, CAPITALIZAR
	}

	public enum Formato {
		PADRAO, SENHA, LETRA_NUMERO, LETRA, NUMERO, DECIMAL, EMAIL, MOEDA, DATA, HORA, DATAHORA, CPF, CNPJ, DATA_CALENDARIO, DECIMAL_CALCULADORA, CEP
	}

	private boolean somenteLeitura;
	private boolean decimalComVirgula;
	private boolean valido;
	private boolean permitirAcentuacao;
	private boolean permitirCedilha;
	private boolean permitirCaractereEspecial;

	private Formato formato;
	private Caixa caixa;

	private String nomeCaixaTexto;
	private String valorFormatado;
	private String valor;
	private Date dataHora;
	private long numero;
	private double decimal;
	private double moeda;
	private int maxLength;

	private KeyAdapter keyDownListener;

	public HFSTextBox() {
		super();
		setSize(new Dimension(100, 35));
		add(getCaixaTexto());
	}

	public HFSTextBox(PosicaoRotulo posicao, String rotulo, int largura,
			boolean obrigatorio, Formato formato, int tamanhoMaximoTexto) {
		super(posicao, rotulo, largura, obrigatorio);
		this.valido = true;
		this.nomeCaixaTexto = "";
		this.permitirAcentuacao = false;
		this.permitirCedilha = false;
		this.permitirCaractereEspecial = false;

		this.formato = formato;
		this.caixa = HFSTextBox.Caixa.PADRAO;
		this.decimalComVirgula = true;
		this.valor = "";
		this.valorFormatado = "";

		initComponents();

		if (formato == Formato.SENHA)
			estiloPadrao(false);
		else
			estiloPadrao(true);

		alinhaTexto();
		atribuiTamanho(tamanhoMaximoTexto, largura);
	}

	private void initComponents() {
		add(getCaixaTexto());
		// if (formato == HFSTextBox.Formato.SENHA)
		// add(getCaixaSenha());
		// else
		// add(getCaixaTexto());
		//
		// if (formato == HFSTextBox.Formato.DATA_CALENDARIO) {
		// add(getBtnCalendario());
		// }
		// if (formato == HFSTextBox.Formato.DECIMAL_CALCULADORA) {
		// add(getBtnCalculadora());
		// }
	}

	public HFSTextField getCaixaTexto() {
		if (caixaTexto == null) {
			caixaTexto = new HFSTextField();
			caixaTexto.setColumns(10);
			caixaTexto.addFocusListener(mudarCorFoco());
			caixaTexto.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent evt) {
					caixaTextoKeyDown(evt);
				}

				@Override
				public void keyReleased(KeyEvent evt) {
					caixaTextoKeyUp(evt);
				}

				@Override
				public void keyTyped(KeyEvent evt) {
				}
			});
		}
		return caixaTexto;
	}

	public HFSPasswordField getCaixaSenha() {
		if (caixaSenha == null) {
			caixaSenha = new HFSPasswordField();
			caixaSenha.setColumns(10);
			caixaSenha.addFocusListener(mudarCorFoco());
			caixaSenha.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent evt) {
					if (keyDownListener != null) {
						keyDownListener.keyPressed(evt);
					}
				}

				@Override
				public void keyReleased(KeyEvent evt) {
				}

				@Override
				public void keyTyped(KeyEvent evt) {
				}
			});
		}
		return caixaSenha;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
		if (formato == HFSTextBox.Formato.SENHA)
			this.getCaixaTexto().setEditable(somenteLeitura);
		else
			this.getCaixaSenha().setEditable(somenteLeitura);
	}

	public boolean isSomenteLeitura() {
		return this.somenteLeitura;
	}

	public void setHabilitado(boolean habilitado) {
		super.setHabilitado(habilitado);
		if (formato == HFSTextBox.Formato.SENHA)
			this.getCaixaSenha().setEnabled(habilitado);
		else
			this.getCaixaTexto().setEnabled(habilitado);
	}

	public void setFocado(boolean focado) {
		super.setFocado(focado);
		if (formato == HFSTextBox.Formato.SENHA)
			this.getCaixaSenha().setFocusable(focado);
		else
			this.getCaixaTexto().setFocusable(focado);
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public boolean isDecimalComVirgula() {
		return decimalComVirgula;
	}

	public void setDecimalComVirgula(boolean decimalComVirgula) {
		this.decimalComVirgula = decimalComVirgula;
	}

	public String getTextoFormatado() {
		return valorFormatado;
	}

	public String getTexto() {
		String svalor = this.valor;

		switch (this.formato) {
		case DECIMAL:
			svalor = this.valor.replace(",", ".");
			break;
		case DECIMAL_CALCULADORA:
			svalor = this.valor.replace(",", ".");
			break;
		case MOEDA:
			svalor = this.valor.replace(",", ".");
			break;
		case CPF:
			svalor = HFSUtil.desformataCPF(this.valor);
			break;
		case CNPJ:
			svalor = HFSUtil.desformataCNPJ(this.valor);
			break;
		case CEP:
			svalor = HFSUtil.desformataCEP(this.valor);
			break;
		case SENHA:
			svalor = this.getCaixaSenha().getPassword().toString();
			break;
		}
		return svalor;
	}

	public void setTexto(String valor) {
		this.valor = valor;
		this.valorFormatado = valor;
		if (formato == HFSTextBox.Formato.SENHA)
			this.getCaixaSenha().setText(valor);
		else {
			this.getCaixaTexto().setText(valor);
			formataTexto();
			// mudaCaixa();
		}
	}

	private void alinhaTexto() {
		switch (formato) {
		case NUMERO:
			this.getCaixaTexto().setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		case DECIMAL:
			this.getCaixaTexto().setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		case DECIMAL_CALCULADORA:
			this.getCaixaTexto().setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		case MOEDA:
			this.getCaixaTexto().setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		default:
			break;
		}
	}

	private void atribuiTamanho(int tamanhoMaximoTexto, int largura) {

		if (tamanhoMaximoTexto == 0)
			tamanhoMaximoTexto = 5;
		if (largura == 0)
			largura = 5;

		switch (this.formato) {
		case DATA:
			// this.getCaixaTexto().setVisibleLength(8);
			this.getCaixaTexto().setMaxLength(10);
			break;
		case DATA_CALENDARIO:
			// this.getCaixaTexto().setVisibleLength(8);
			this.getCaixaTexto().setMaxLength(10);
			break;
		case HORA:
			// this.getCaixaTexto().setVisibleLength(5);
			this.getCaixaTexto().setMaxLength(5);
			break;
		case DATAHORA:
			// this.getCaixaTexto().setVisibleLength(16);
			this.getCaixaTexto().setMaxLength(16);
			break;
		case CPF:
			// this.getCaixaTexto().setVisibleLength(12);
			this.getCaixaTexto().setMaxLength(14);
			break;
		case CNPJ:
			// this.getCaixaTexto().setVisibleLength(15);
			this.getCaixaTexto().setMaxLength(18);
			break;
		case CEP:
			// this.getCaixaTexto().setVisibleLength(9);
			this.getCaixaTexto().setMaxLength(9);
			break;
		case SENHA:
			// this.getCaixaSenha().setVisibleLength(largura);
			this.getCaixaSenha().setMaxLength(tamanhoMaximoTexto);
			break;
		default:
			// this.getCaixaTexto().setVisibleLength(largura);
			this.getCaixaTexto().setMaxLength(tamanhoMaximoTexto);
			break;
		}
	}

	private void mudaEstilo(boolean bCaixaTexto, boolean bFocado) {
		String estilo;

		if (caixa == HFSTextBox.Caixa.MAIUSCULA) {
			if (bFocado)
				estilo = "HFSTextBox-focado-uppercase";
			else
				estilo = "HFSTextBox-erro-uppercase";
		} else if (caixa == HFSTextBox.Caixa.MINUSCULA) {
			if (bFocado)
				estilo = "HFSTextBox-focado-lowercase";
			else
				estilo = "HFSTextBox-erro-lowercase";
		} else if (caixa == HFSTextBox.Caixa.CAPITALIZAR) {
			if (bFocado)
				estilo = "HFSTextBox-focado-capitalize";
			else
				estilo = "HFSTextBox-erro-capitalize";
		} else {
			if (bFocado)
				estilo = "HFSTextBox-focado";
			else
				estilo = "HFSTextBox-erro";
		}
		if (bCaixaTexto)
			this.getCaixaTexto().setFiltro("UPPERCASE");
		else
			this.getCaixaSenha().setBackground(HFSConst.COR_FOCADO);
	}

	private void estiloPadrao(boolean bCaixaTexto) {
		if (bCaixaTexto)
			this.getCaixaTexto().setBackground(UIManager.getColor(getCaixaTexto().getClass().getSimpleName()+ ".background"));
		else
			this.getCaixaSenha().setBackground(UIManager.getColor(getCaixaTexto().getClass().getSimpleName()+ ".background"));
	}

	private void caixaTextoFocusIn(FocusEvent evento) {
		this.getCaixaTexto().setText(this.valor);
		mudaEstilo(true, true);
	}

	private void caixaSenhaFocusIn(FocusEvent evento) {
		mudaEstilo(false, true);
	}

	private boolean formataDataHora(String sData) {
		Date dvalor;

		try {
			switch (this.formato) {
			case DATA:
				if (HFSValida.validaDataSimples(sData)) {
					dvalor = HFSUtil.StringToDate(sData, HFSConst.MASCARA_DATA);
					this.valorFormatado = HFSUtil.formataDate(
							HFSConst.MASCARA_DATA, dvalor);
					if (HFSValida.validaAnoBisexto(this.valorFormatado)) {
						return true;
					}
				}
				break;
			case DATA_CALENDARIO:
				if (HFSValida.validaDataSimples(sData)) {
					dvalor = HFSUtil.StringToDate(sData, HFSConst.MASCARA_DATA);
					this.valorFormatado = HFSUtil.formataDate(
							HFSConst.MASCARA_DATA, dvalor);
					if (HFSValida.validaAnoBisexto(this.valorFormatado)) {
						return true;
					}
				}
				break;
			case HORA:
				if (HFSValida.validaHoraH24M(sData)) {
					dvalor = HFSUtil.StringToDate(sData, HFSConst.MASCARA_HORA);
					this.valorFormatado = HFSUtil.formataDate(
							HFSConst.MASCARA_HORA, dvalor);
					return true;
				}
				break;
			case DATAHORA:
				if (HFSValida.validaDataHora(sData)) {
					dvalor = HFSUtil.StringToDate(sData,
							HFSConst.MASCARA_DATAHORA);
					this.valorFormatado = HFSUtil.formataDate(
							HFSConst.MASCARA_DATAHORA, dvalor);
					if (HFSValida.validaAnoBisexto(this.valorFormatado)) {
						return true;
					}
				}
				break;
			}
		} catch (Exception ex) {
			mudaEstilo(true, false);
			return false;
		}

		mudaEstilo(true, false);
		return false;
	}

	private boolean formataNumero(String sValor, Formato formato) {
		double nvalor = 0;
		try {
			if (this.decimalComVirgula) {
				nvalor = Double.parseDouble(sValor.replace(',', '.'));
				if (formato == Formato.DECIMAL)
					this.valorFormatado = HFSUtil.FormatDouble(
							HFSConst.MASCARA_DECIMAL, nvalor);
				else
					this.valorFormatado = HFSUtil.FormatDouble(
							HFSConst.MASCARA_MOEDA, nvalor);
				this.valorFormatado = HFSUtil
						.pontoParaVirgula(this.valorFormatado);
			} else {
				if (formato == Formato.DECIMAL)
					this.valorFormatado = HFSUtil.FormatDouble(
							HFSConst.MASCARA_DECIMAL, nvalor);
				else
					this.valorFormatado = HFSUtil.FormatDouble(
							HFSConst.MASCARA_MOEDA, nvalor);
			}
			return true;
		} catch (Exception e) {
			mudaEstilo(true, false);
			return false;
		}
	}

	private boolean formataCPF(String sValor) {
		if (HFSValida.validaCPF(sValor, true)) {
			// this.valorFormatado = HFSValida.formataCPF(sValor);
			this.valorFormatado = sValor;
			return true;
		} else {
			mudaEstilo(true, false);
			return false;
		}
	}

	private boolean formataCNPJ(String sValor) {
		if (HFSValida.validaCNPJ(sValor, true)) {
			// this.valorFormatado = HFSValida.formataCNPJ(sValor);
			this.valorFormatado = sValor;
			return true;
		} else {
			mudaEstilo(true, false);
			return false;
		}
	}

	private boolean formataEmail(String sValor) {
		if (HFSValida.validaEmailLivre(sValor)) {
			this.valorFormatado = sValor;
			return true;
		} else {
			mudaEstilo(true, false);
			return false;
		}
	}

	private boolean formataCEP(String sValor) {
		if (HFSValida.validaCEP(sValor)) {
			this.valorFormatado = sValor;
			return true;
		} else {
			mudaEstilo(true, false);
			return false;
		}
	}

	private void formataTexto() {
		estiloPadrao(true);
		this.valorFormatado = "";

		if (this.valor.trim().length() > 0) {
			switch (formato) {
			case DECIMAL:
				this.valido = formataNumero(this.valor, Formato.DECIMAL);
				break;
			case DECIMAL_CALCULADORA:
				this.valido = formataNumero(this.valor, Formato.DECIMAL);
				break;
			case MOEDA:
				this.valido = formataNumero(this.valor, Formato.MOEDA);
				break;
			case EMAIL:
				this.valido = formataEmail(this.valor);
				break;
			case DATA:
				this.valido = formataDataHora(this.valor);
				break;
			case DATA_CALENDARIO:
				this.valido = formataDataHora(this.valor);
				break;
			case HORA:
				this.valido = formataDataHora(this.valor);
				break;
			case DATAHORA:
				this.valido = formataDataHora(this.valor);
				break;
			case CPF:
				this.valido = formataCPF(this.valor);
				break;
			case CNPJ:
				this.valido = formataCNPJ(this.valor);
				break;
			case CEP:
				this.valido = formataCEP(this.valor);
				break;
			default:
				break;
			}

			if (this.valorFormatado.trim().length() > 0) {
				this.getCaixaTexto().setText(this.valorFormatado);
			} else {
				this.valorFormatado = this.valor;
			}
		}
	}

	private void caixaTextoFocusOut(FocusEvent evento) {
		formataTexto();
	}

	private void caixaSenhaFocusOut(FocusEvent evento) {
		estiloPadrao(false);
	}

	private void addChar(int npos, char caractere) {
		if (this.valor.indexOf(caractere) != npos)
			this.valor += caractere;
	}

	private void adicionaCaractere(int tecla) {
		this.valor = this.getCaixaTexto().getText();

		if (this.valor.trim().length() > 0) {
			if (!isTeclaEspecial(tecla)) {
				int npos = this.getCaixaTexto().getCaretPosition();

				switch (this.formato) {
				// case MOEDA:
				// if (npos == 1) {
				// this.valor = "R$" + this.valor;
				// }
				// break;
				case DATA:
					if (npos == 2) {
						addChar(npos, '/');
					} else if (npos == 5) {
						addChar(npos, '/');
					}
					break;
				case DATA_CALENDARIO:
					if (npos == 2) {
						addChar(npos, '/');
					} else if (npos == 5) {
						addChar(npos, '/');
					}
					break;
				case HORA:
					if (npos == 2) {
						addChar(npos, ':');
						// } else if (npos == 5) {
						// addChar(npos, ':');
					}
					break;
				case DATAHORA:
					if (npos == 2) {
						addChar(npos, '/');
					} else if (npos == 5) {
						addChar(npos, '/');
					} else if (npos == 10) {
						addChar(npos, ' ');
					} else if (npos == 13) {
						addChar(npos, ':');
						// } else if (npos == 16) {
						// addChar(npos, ':');
					}
					break;
				case CPF:
					if (npos == 3) {
						addChar(npos, '.');
					} else if (npos == 7) {
						addChar(npos, '.');
					} else if (npos == 11) {
						addChar(npos, '-');
					}
					break;
				case CNPJ:
					if (npos == 2) {
						addChar(npos, '.');
					} else if (npos == 6) {
						addChar(npos, '.');
					} else if (npos == 10) {
						addChar(npos, '/');
					} else if (npos == 15) {
						addChar(npos, '-');
					}
					break;
				case CEP:
					if (npos == 5) {
						addChar(npos, '-');
					}
					break;
				}

				this.getCaixaTexto().setText(this.valor);
			}
		}
		// mudaCaixa();
	}

	private void caixaTextoKeyUp(KeyEvent evento) {
		adicionaCaractere(evento.getKeyCode());
	}

	private boolean isTeclaEspecial(int tecla) {
		boolean bEspecial = (tecla == KeyEvent.VK_BACK_SPACE
				|| tecla == KeyEvent.VK_TAB || tecla == KeyEvent.VK_ENTER
				|| tecla == KeyEvent.VK_DELETE || tecla == KeyEvent.VK_SHIFT
				|| tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_RIGHT
				|| tecla == KeyEvent.VK_HOME || tecla == KeyEvent.VK_END);
		return bEspecial;
	}
	
	private boolean isTeclaAcentuacao(int tecla, boolean isShiftKey) {
		boolean bAcentua = (tecla == HFSConst.TECLA_ACENTO_AGUDO
				|| tecla == HFSConst.TECLA_ACENTO_TIL
				|| (tecla == HFSConst.TECLA_ACENTO_CRASE && isShiftKey)
				|| (tecla == HFSConst.TECLA_ACENTO_TREMA && isShiftKey) || (tecla == HFSConst.TECLA_ACENTO_CIRCUNFLEXO && isShiftKey));
		return bAcentua;
	}

	
	private boolean isTeclaCaractereEspecial(int tecla, boolean isShiftKey) {
		boolean bCaracEspecial = (tecla == HFSConst.TECLA_ASPAS
				|| (tecla == HFSConst.TECLA_EXCLAMACAO && isShiftKey)
				|| (tecla == HFSConst.TECLA_ARROBA && isShiftKey)
				|| (tecla == HFSConst.TECLA_CERQUILHA && isShiftKey)
				|| (tecla == HFSConst.TECLA_CIFRAO && isShiftKey)
				|| (tecla == HFSConst.TECLA_PERCENTE && isShiftKey)
				|| (tecla == HFSConst.TECLA_ECOMERCIAL && isShiftKey)
				|| (tecla == HFSConst.TECLA_ASTERISTICO && isShiftKey)
				|| (tecla == HFSConst.TECLA_ABREPARENTESES && isShiftKey)
				|| (tecla == HFSConst.TECLA_FECHAPARENTESES && isShiftKey)
				|| tecla == HFSConst.TECLA_MENOS
				|| tecla == HFSConst.TECLA_MAIS
				|| tecla == HFSConst.TECLA_ABRECHAVES
				|| tecla == HFSConst.TECLA_FECHACHAVES
				|| tecla == HFSConst.TECLA_INTERROGACAO
				|| tecla == HFSConst.TECLA_BARRAINVERTIDA
				|| tecla == HFSConst.TECLA_DOISPONTOS
				|| tecla == HFSConst.TECLA_VIRGULA || tecla == HFSConst.TECLA_PONTO);
		return bCaracEspecial;
	}

	private boolean permitirOutraTeclas(boolean bTeclar, int tecla,
			KeyEvent evento) {
		boolean res = bTeclar;

		if (!this.permitirAcentuacao) {
			if (isTeclaAcentuacao(tecla, evento.isShiftDown()))
				res = false;
		}
		if (!this.permitirCedilha) {
			if (tecla == HFSConst.TECLA_CEDILHA)
				res = false;
		}
		if (!this.permitirCaractereEspecial) {
			if (isTeclaCaractereEspecial(tecla, evento.isShiftDown()))
				res = false;
		}

		return res;
	}

	private void caixaTextoKeyDown(KeyEvent evento) {
		int tecla = evento.getKeyCode();

		boolean bEspecial = isTeclaEspecial(tecla);
		boolean bLetraNumero = permitirOutraTeclas(true, tecla, evento);

		// 32 é barra de espaço
		boolean bLetrasSemEspaco = ((tecla >= 65 && tecla <= 90) || bEspecial
				|| tecla == HFSConst.TECLA_CEDILHA
				|| isTeclaAcentuacao(tecla, evento.isShiftDown()) || isTeclaCaractereEspecial(
				tecla, evento.isShiftDown()));

		bLetrasSemEspaco = permitirOutraTeclas(bLetrasSemEspaco, tecla, evento);

		boolean bLetras = (bLetrasSemEspaco || tecla == HFSConst.TECLA_ESPACO);
		boolean bNumerosKeypad = (tecla >= 96 && tecla <= 105);
		boolean bNumeros = ((tecla >= 48 && tecla <= 57)
				|| tecla == HFSConst.TECLA_DIMINUIR || bNumerosKeypad || bEspecial);

		boolean bDecimal = false;
		if (this.decimalComVirgula)
			bDecimal = (bNumeros || tecla == HFSConst.TECLA_VIRGULA || tecla == HFSConst.TECLAKEYPAD_VIRGULA); // virgula
		else
			// ponto decimal
			bDecimal = (bNumeros || tecla == HFSConst.TECLA_PONTO || tecla == HFSConst.TECLAKEYPAD_PONTO);

		boolean bDecimalCalculadora = bDecimal;
		// boolean bDecimalCalculadora = (bDecimal || tecla ==
		// HFSConst.TECLA_SOMAR || tecla == HFSConst.TECLA_DIMINUIR ||
		// tecla == HFSConst.TECLA_MULTIPLICAR || tecla ==
		// HFSConst.TECLA_DIVIDIR);

		boolean bMoeda = bDecimal;
		// ponto decimal e barra de menos
		boolean bCPF = (bNumeros);
		// || tecla == TECLA_PONTO || tecla == TECLAKEYPAD_PONTO || tecla ==
		// TECLA_DIMINUIR);
		boolean bCNPJ = (bCPF); // || tecla == TECLA_BARRA);
		boolean bData = (bNumeros); // || tecla == TECLA_BARRA); // barra é 193

		// dois pontos é 191 com shift antes é 16
		boolean bHora = (bNumeros); // || tecla == TECLA_DOISPONTOS);

		// 32 é barra de espaço
		boolean bDataHora = (bData || bHora); // || tecla == TECLA_ESPACO);

		boolean bEmail = (bLetrasSemEspaco
				|| (tecla == HFSConst.TECLA_ARROBA && evento.isShiftDown()) || tecla == HFSConst.TECLA_PONTO);

		boolean bCEP = (bNumeros); // || tecla == TECLA_DIMINUIR);

		switch (formato) {
		case LETRA_NUMERO:
			if (!bLetraNumero) {
				evento.consume();
			}
			break;
		case LETRA:
			if (!bLetras) {
				evento.consume();
			}
			break;
		case EMAIL:
			if (!bEmail) {
				evento.consume();
			}
			break;
		case NUMERO:
			if (!bNumeros) {
				evento.consume();
			}
			break;
		case DECIMAL:
			if (!bDecimal) {
				evento.consume();
			}
			break;
		case DECIMAL_CALCULADORA:
			if (!bDecimalCalculadora) {
				evento.consume();
			}
			break;
		case MOEDA:
			if (!bMoeda) {
				evento.consume();
			}
			break;
		case DATA:
			if (!bData) {
				evento.consume();
			}
			break;
		case DATA_CALENDARIO:
			if (!bData) {
				evento.consume();
			}
			break;
		case HORA:
			if (!bHora) {
				evento.consume();
			}
			break;
		case DATAHORA:
			if (!bDataHora) {
				evento.consume();
			}
			break;
		case CPF:
			if (!bCPF) {
				evento.consume();
			}
			break;
		case CNPJ:
			if (!bCNPJ) {
				evento.consume();
			}
			break;
		case CEP:
			if (!bCEP) {
				evento.consume();
			}
			break;
		default:
			break;
		}

		if (keyDownListener != null) {
			keyDownListener.keyPressed(evento);
		}

	}

}
