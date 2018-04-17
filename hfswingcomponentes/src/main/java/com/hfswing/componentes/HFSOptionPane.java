package com.hfswing.componentes;

import java.awt.Component;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.hfswing.util.HFSUtil;

public class HFSOptionPane extends JOptionPane {
	private static final long serialVersionUID = 8344900276967442817L;

	private static Logger log = Logger.getLogger(HFSUtil.HFSWING);
	
	public HFSOptionPane() {
		super();
	}

	public static void mudarLingua(String localidadeLingua, String localidadePais) {
		HFSUtil.getParams().setLocalidadeLingua(localidadeLingua);
		HFSUtil.getParams().setLocalidadePais(localidadePais);
		
		UIManager.put("OptionPane.yesButtonText", HFSUtil
				.getRecurso("HFSOptionPane.yesButtonText"));
		UIManager.put("OptionPane.yesButtonMnemonic", HFSUtil
				.getRecurso("HFSOptionPane.yesButtonMnemonic"));
		UIManager.put("OptionPane.cancelButtonText", HFSUtil
				.getRecurso("HFSOptionPane.cancelButtonText"));
		UIManager.put("OptionPane.cancelButtonMnemonic", HFSUtil
				.getRecurso("HFSOptionPane.cancelButtonMnemonic"));
		UIManager.put("OptionPane.noButtonText", HFSUtil
				.getRecurso("HFSOptionPane.noButtonText"));
		UIManager.put("OptionPane.noButtonMnemonic", HFSUtil
				.getRecurso("HFSOptionPane.noButtonMnemonic"));
		UIManager.put("OptionPane.okButtonText", HFSUtil
				.getRecurso("HFSOptionPane.okButtonText"));
		UIManager.put("OptionPane.okButtonMnemonic", HFSUtil
				.getRecurso("HFSOptionPane.okButtonMnemonic"));
	}

	public static void mensagemErro(Component pai, String recurso, String texto) {
		HFSOptionPane.showMessageDialog(pai, HFSUtil.getRecurso(recurso) + " "
				+ texto, HFSUtil.getRecurso("HFSOptionPane.erro"),
				HFSOptionPane.ERROR_MESSAGE);
		log.warning(HFSUtil.getRecurso(recurso) + " " + texto);
	}

	public static void mensagemErro(Component pai, String recurso) {
		HFSOptionPane.mensagemErro(pai, recurso, "");
	}

	public static void mensagemInfo(Component pai, String recurso) {
		HFSOptionPane.showMessageDialog(pai, HFSUtil.getRecurso(recurso), HFSUtil
				.getRecurso("HFSOptionPane.info"), HFSOptionPane.INFORMATION_MESSAGE);
		log.info(HFSUtil.getRecurso(recurso));
	}

	public static int confirma(Component pai, String recurso) {
		return HFSOptionPane.showConfirmDialog(pai, HFSUtil.getRecurso(recurso),
				HFSUtil.getRecurso("HFSOptionPane.confirma"), HFSOptionPane.YES_NO_OPTION);
	}

	public static String entrada(Component pai, String recurso) {
		return HFSOptionPane
				.showInputDialog(pai, HFSUtil.getRecurso(recurso), HFSUtil
						.getRecurso("HFSOptionPane.entrada"),
						HFSOptionPane.QUESTION_MESSAGE);
	}

	public static String entrada(Component pai, String recurso,
			String valorInicial) {
		return (String) HFSOptionPane.showInputDialog(pai, HFSUtil
				.getRecurso(recurso), HFSUtil.getRecurso("HFSOptionPane.entrada"),
				HFSOptionPane.QUESTION_MESSAGE, (Icon) UIManager
						.get("OptionPane.inputIcon"), null, valorInicial);
	}
}
