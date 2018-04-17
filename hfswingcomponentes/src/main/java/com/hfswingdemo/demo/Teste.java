package com.hfswingdemo.demo;

import org.apache.commons.lang3.StringUtils;

public class Teste {

	public static final String CEDILHA_UPPERCASE = "Ç";

	public static final String ACENTUADO = "´~`¨^";

	public static final String ACENTUADO_LOWERCASE = "áãàäâéèëêíìïîóõòöôúùüû";

	public static final String ACENTUADO_UPPERCASE = "ÁÃÀÄÂÉÈËÊÍÌÏÎÓÕÒÖÔÚÙÜÛ";
	
	public static void main(String[] args) {
		String aceitaChars = ACENTUADO_LOWERCASE;
		
		aceitaChars += ACENTUADO_UPPERCASE;
		System.out.println(aceitaChars);
		
		System.out.println();
		
		System.out.println(StringUtils.capitalize("henrique figueiredo de souza"));
		
		System.out.println();
		
		aceitaChars = StringUtils.remove(aceitaChars, "ç");
		System.out.println(aceitaChars);
	}

}
