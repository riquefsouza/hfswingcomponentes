package com.hfswing.componentes;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.hfswing.componentes.IHFSField.Caixa;
import com.hfswing.componentes.IHFSField.Formato;
import org.apache.commons.lang.StringUtils;

public class HFSTextField extends JTextField implements IHFSField {

	private static final long serialVersionUID = 1L;

	private FiltraCampo filtraCampo;

	private int maxLength;

	private String filtro;

	private boolean aceitaNumeroNegativo;
	private boolean decimalComVirgula;
	private boolean valido;
	private boolean permitirAcentuacao;
	private boolean permitirCedilha;
	private boolean permitirCaractereEspecial;

	private Formato formato;
	private Caixa caixa;

	public HFSTextField() {
		this.maxLength = Integer.MAX_VALUE;
		this.filtro = "";
		this.aceitaNumeroNegativo = true;
		this.decimalComVirgula = true;
		this.valido = false;
		this.permitirAcentuacao = true;
		this.permitirCedilha = true;
		this.permitirCaractereEspecial = true;
		this.formato = Formato.PADRAO;
		this.caixa = Caixa.PADRAO;
	}

	private void filtrarCampo() {
		filtraCampo = new FiltraCampo(this.maxLength, this.formato, this.caixa);
		filtraCampo.setAceitaNumeroNegativo(this.aceitaNumeroNegativo);
		filtraCampo.setDecimalComVirgula(decimalComVirgula);
		filtraCampo.setPermitirAcentuacao(permitirAcentuacao);
		filtraCampo.setPermitirCaractereEspecial(permitirCaractereEspecial);
		filtraCampo.setPermitirCedilha(permitirCedilha);
		this.setDocument(filtraCampo);
	}

	@Override
	public String getTexto() {
		return this.getText();
	}

	@Override
	public void setTexto(String texto) {
		this.setTexto(texto);
	}

	@Override
	public int getMaxLength() {
		return maxLength;
	}

	@Override
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
		filtrarCampo();
	}

	@Override
	public String getFiltro() {
		return filtro;
	}

	@Override
	public void setFiltro(String filtro) {
		this.filtro = filtro;
		filtrarCampo();
	}

	@Override
	public void setHabilitado(boolean habilitado) {
		this.setEnabled(habilitado);
	}

	@Override
	public boolean isHabilitado() {
		return this.isEnabled();
	}

	@Override
	public void setFocado(boolean focado) {
		this.setFocusable(focado);
	}

	@Override
	public boolean isFocado() {
		return this.isFocusable();
	}

	@Override
	public void setTamanho(int largura, int altura) {
		this.setSize(largura, altura);
	}

	@Override
	public boolean isEditavel() {
		return this.isEditable();
	}

	@Override
	public void setEditavel(boolean editavel) {
		this.setEditable(editavel);
	}

	public boolean isAceitaNumeroNegativo() {
		return aceitaNumeroNegativo;
	}

	public void setAceitaNumeroNegativo(boolean aceitaNumeroNegativo) {
		this.aceitaNumeroNegativo = aceitaNumeroNegativo;
		filtrarCampo();
	}

	public boolean isDecimalComVirgula() {
		return decimalComVirgula;
	}

	public void setDecimalComVirgula(boolean decimalComVirgula) {
		this.decimalComVirgula = decimalComVirgula;
		filtrarCampo();
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public boolean isPermitirAcentuacao() {
		return permitirAcentuacao;
	}

	public void setPermitirAcentuacao(boolean permitirAcentuacao) {
		this.permitirAcentuacao = permitirAcentuacao;
		filtrarCampo();
	}

	public boolean isPermitirCedilha() {
		return permitirCedilha;
	}

	public void setPermitirCedilha(boolean permitirCedilha) {
		this.permitirCedilha = permitirCedilha;
		filtrarCampo();
	}

	public boolean isPermitirCaractereEspecial() {
		return permitirCaractereEspecial;
	}

	public void setPermitirCaractereEspecial(boolean permitirCaractereEspecial) {
		this.permitirCaractereEspecial = permitirCaractereEspecial;
		filtrarCampo();
	}

	public Formato getFormato() {
		return formato;
	}

	public void setFormato(Formato formato) {
		this.formato = formato;
		filtrarCampo();
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
		filtrarCampo();
	}
}

class FiltraCampo extends PlainDocument {
	private static final long serialVersionUID = 3821568943349434438L;

	// public static final String SEM_FILTRO = "SEM_FITRO";

	// public static final String FILTRO_EXCEPT = ":*?\"<>|%";

	public static final String ESPECIAL = "'\"!@#$%&*()-+{}[]?/\\|:,.;<>";

	public static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";

	public static final String CEDILHA_LOWERCASE = "ç";

	public static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String CEDILHA_UPPERCASE = "Ç";

	public static final String ACENTUADO = "´~`¨^";

	public static final String ACENTUADO_LOWERCASE = "áãàäâéèëêíìïîóõòöôúùüû";

	public static final String ACENTUADO_UPPERCASE = "ÁÃÀÄÂÉÈËÊÍÌÏÎÓÕÒÖÔÚÙÜÛ";

	public static final String SPACE = " ";

	public static final String NUMERIC = "0123456789";

	public static final String FLOAT = NUMERIC + ".";

	public static final String ALPHA = LOWERCASE + UPPERCASE + SPACE;

	public static final String ALPHA_NUMERIC = ALPHA + FLOAT;

	private String aceitaChars;

	private boolean aceitaNumeroNegativo;

	private int maxLength;
	private Formato formato;
	private Caixa caixa;

	public FiltraCampo(int maxLength, Formato formato, Caixa caixa) {
		this.maxLength = maxLength;
		this.formato = formato;
		this.caixa = caixa;
		aceitaNumeroNegativo = false;
		aceitaChars = "";

		mudarFormato();
	}

	private void mudarFormato() {
		switch (formato) {
		// case SENHA:
		// this.aceitaChars = ALPHA_NUMERIC;
		// break;
		case LETRA_NUMERO:
			this.aceitaChars = ALPHA_NUMERIC;
			break;
		case LETRA:
			this.aceitaChars = ALPHA;
			break;
		case NUMERO:
			this.aceitaChars = NUMERIC;
			break;
		case DECIMAL:
			this.aceitaChars = FLOAT;
			break;
		case EMAIL:
			this.aceitaChars = LOWERCASE + FLOAT + "@";
			break;
		case MOEDA:
			this.aceitaChars = FLOAT;
			break;
		case DATA:
			this.aceitaChars = NUMERIC + "/";
			break;
		case HORA:
			this.aceitaChars = NUMERIC + ":";
			break;
		case DATAHORA:
			this.aceitaChars = NUMERIC + ":" + "/";
			break;
		case CPF:
			this.aceitaChars = NUMERIC + "." + "-";
			break;
		case CNPJ:
			this.aceitaChars = NUMERIC + "." + "-";
			break;
		case DATA_CALENDARIO:
			this.aceitaChars = NUMERIC + ":" + "/";
			break;
		case DECIMAL_CALCULADORA:
			this.aceitaChars = FLOAT;
			break;
		case CEP:
			this.aceitaChars = NUMERIC + "-";
			break;
		default:
			this.aceitaChars = "";
			break;
		}
	}

	public String getFiltro() {
		return this.aceitaChars;
	}

	public void setAceitaNumeroNegativo(boolean aceitaNumeroNegativo) {
		this.aceitaNumeroNegativo = aceitaNumeroNegativo;

		if (aceitaNumeroNegativo) {
			switch (formato) {
			case LETRA_NUMERO:
			case NUMERO:
			case DECIMAL:
			case MOEDA:
			case DECIMAL_CALCULADORA:
				aceitaChars += "-";
				break;
			// default:
			// aceitaChars = StringUtils.remove(aceitaChars, ",");
			// break;
			}
		} else {
			aceitaChars = StringUtils.remove(aceitaChars, "-");
		}

		// if (aceitaChars.equals(NUMERIC) || aceitaChars.equals(FLOAT)
		// || aceitaChars.equals(ALPHA_NUMERIC)) {
		// this.aceitaNumeroNegativo = aceitaNumeroNegativo;
		// aceitaChars += "-";
		// }
	}

	public void setDecimalComVirgula(boolean decimalComVirgula) {
		// this.decimalComVirgula = decimalComVirgula;

		if (decimalComVirgula) {
			switch (formato) {
			case LETRA_NUMERO:
			case NUMERO:
			case DECIMAL:
			case MOEDA:
			case DECIMAL_CALCULADORA:
				aceitaChars += ",";
				break;
			// default:
			// aceitaChars = StringUtils.remove(aceitaChars, ",");
			// break;
			}
		} else {
			aceitaChars = StringUtils.remove(aceitaChars, ",");
		}
	}

	public void setPermitirAcentuacao(boolean permitirAcentuacao) {
		// this.permitirAcentuacao = permitirAcentuacao;

		if (permitirAcentuacao) {
			switch (formato) {
			case SENHA:
			case LETRA_NUMERO:
			case LETRA:
				aceitaChars += ACENTUADO + ACENTUADO_LOWERCASE
						+ ACENTUADO_UPPERCASE;
				break;
			}
		} else {
			aceitaChars = StringUtils.remove(aceitaChars, ACENTUADO
					+ ACENTUADO_LOWERCASE + ACENTUADO_UPPERCASE);
		}
	}

	public void setPermitirCedilha(boolean permitirCedilha) {
		// this.permitirCedilha = permitirCedilha;

		if (permitirCedilha) {
			switch (formato) {
			case SENHA:
			case LETRA_NUMERO:
			case LETRA:
				aceitaChars += CEDILHA_LOWERCASE + CEDILHA_UPPERCASE;
				break;
			}
		} else {
			aceitaChars = StringUtils.remove(aceitaChars, CEDILHA_LOWERCASE
					+ CEDILHA_UPPERCASE);
		}
	}

	public void setPermitirCaractereEspecial(boolean permitirCaractereEspecial) {
		// this.permitirCaractereEspecial = permitirCaractereEspecial;

		if (permitirCaractereEspecial) {
			switch (formato) {
			case SENHA:
			case LETRA_NUMERO:
			case LETRA:
				aceitaChars += ESPECIAL;
				break;
			}
		} else {
			aceitaChars = StringUtils.remove(aceitaChars, ESPECIAL);
		}
	}

	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= maxLength) {

			str = mudarCaixa(str);

			if (aceitaChars.equals(ESPECIAL)) {
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) == '\\')
						return;
					if (str.charAt(i) == '/')
						return;
					if (aceitaChars.indexOf(String.valueOf(str.charAt(i))) > 0) {
						return;
					}
				}
				// } else if (!aceitaChars.equals(SEM_FILTRO)) {
			} else if (formato != Formato.PADRAO) {
				for (int i = 0; i < str.length(); i++) {
					if (aceitaChars.indexOf(String.valueOf(str.charAt(i))) == -1) {
						return;
					}
				}

				if (aceitaChars.equals(FLOAT)
						|| (aceitaChars.equals(FLOAT + "-") && aceitaNumeroNegativo)) {
					if (str.indexOf(".") != -1) {
						if (getText(0, getLength()).indexOf(".") != -1) {
							return;
						}
					}
				}

				if (aceitaNumeroNegativo && str.indexOf("-") != -1) {
					if (str.indexOf("-") != 0 || offset != 0) {
						return;
					}
				}
			}
			super.insertString(offset, str, attr);
		}
	}

	private String mudarCaixa(String str) {
		// if (aceitaChars.equals(UPPERCASE))
		switch (caixa) {
		case MAIUSCULA:
			str = str.toUpperCase();
			break;
		case MINUSCULA:
			str = str.toLowerCase();
			break;
		case CAPITALIZAR:
			str = StringUtils.capitalize(str);
			break;
		default:
			break;
		}
		return str;
	}
}
