package com.hfswing.componentes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.hfswing.util.HFSUtil;

public class HFSFileChooser extends JFileChooser {
	private static final long serialVersionUID = 3644252320738434567L;

	public static final int PREVIEW_OPEN_DIALOG = 3;

	public static final int PREVIEW_SAVE_DIALOG = 4;

	public HFSFileChooser(int tipoDialogo) {
		super();
		if (tipoDialogo == HFSFileChooser.SAVE_DIALOG
				|| tipoDialogo == HFSFileChooser.OPEN_DIALOG) {
			this.setDialogType(tipoDialogo);
			if (tipoDialogo == HFSFileChooser.SAVE_DIALOG)
				this.setDialogTitle(HFSUtil
						.getRecurso("HFSFileChooser.saveButtonText"));
			else if (tipoDialogo == HFSFileChooser.OPEN_DIALOG)
				this.setDialogTitle(HFSUtil
						.getRecurso("HFSFileChooser.openButtonText"));
			adicionarFiltro("HFSFileChooser.filtroPDF", "pdf");
			adicionarFiltro("HFSFileChooser.filtroRTF", "rtf");
			adicionarFiltro("HFSFileChooser.filtroXLS", "xls");
			adicionarFiltro("HFSFileChooser.filtroHTML", "html");
			this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			this.setAcceptAllFileFilterUsed(true);
		} else if (tipoDialogo == HFSFileChooser.PREVIEW_OPEN_DIALOG
				|| tipoDialogo == HFSFileChooser.PREVIEW_SAVE_DIALOG) {
			if (tipoDialogo == HFSFileChooser.PREVIEW_SAVE_DIALOG)
				this.setDialogTitle(HFSUtil
						.getRecurso("HFSFileChooser.saveButtonText"));
			else if (tipoDialogo == HFSFileChooser.PREVIEW_OPEN_DIALOG)
				this.setDialogTitle(HFSUtil
						.getRecurso("HFSFileChooser.openButtonText"));
			adicionarFiltro("HFSFileChooser.filtroGIF", "gif");
			adicionarFiltro("HFSFileChooser.filtroJPG", "jpg");
			this.setAccessory(new FilePreviewer(this));
		} else {
			this.setDialogType(HFSFileChooser.CUSTOM_DIALOG);
			this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			this.setApproveButtonText(HFSUtil
					.getRecurso("HFSFileChooser.ApproveButtonText"));
			this.setApproveButtonMnemonic(HFSUtil.getRecurso(
					"HFSFileChooser.ApproveButtonMnemonic").charAt(0));
			this.setApproveButtonToolTipText(HFSUtil
					.getRecurso("HFSFileChooser.ApproveButtonToolTipText"));
			this.setAcceptAllFileFilterUsed(false);
			this.addChoosableFileFilter(new Filtro());
		}
		this.setControlButtonsAreShown(true);

		this.setMultiSelectionEnabled(false);
		this.setVisible(true);
	}

	private void adicionarFiltro(String descricao, String extensao) {
		FileNameExtensionFilter filtro1 = new FileNameExtensionFilter(HFSUtil
				.getRecurso(descricao), new String[] { extensao });
		this.addChoosableFileFilter(filtro1);
		// this.setFileFilter(filtro1);
	}

	public static void mudarLingua(String localidadeLingua, String localidadePais) {
		HFSUtil.getParams().setLocalidadeLingua(localidadeLingua);
		HFSUtil.getParams().setLocalidadePais(localidadePais);
		
		UIManager.put("FileChooser.lookInLabelText", HFSUtil
				.getRecurso("HFSFileChooser.lookInLabelText"));
		UIManager.put("FileChooser.lookInLabelMnemonic", HFSUtil
				.getRecurso("HFSFileChooser.lookInLabelMnemonic"));
		UIManager.put("FileChooser.saveInLabelMnemonic", HFSUtil
				.getRecurso("HFSFileChooser.saveInLabelMnemonic"));
		UIManager.put("FileChooser.saveInLabelText", HFSUtil
				.getRecurso("HFSFileChooser.saveInLabelText"));
		UIManager.put("FileChooser.fileNameLabelText", HFSUtil
				.getRecurso("HFSFileChooser.fileNameLabelText"));
		UIManager.put("FileChooser.fileNameLabelMnemonic", HFSUtil
				.getRecurso("HFSFileChooser.fileNameLabelMnemonic"));
		UIManager.put("FileChooser.filesOfTypeLabelText", HFSUtil
				.getRecurso("HFSFileChooser.filesOfTypeLabelText"));
		UIManager.put("FileChooser.filesOfTypeLabelMnemonic", HFSUtil
				.getRecurso("HFSFileChooser.filesOfTypeLabelMnemonic"));
		UIManager.put("FileChooser.upFolderToolTipText", HFSUtil
				.getRecurso("HFSFileChooser.upFolderToolTipText"));
		UIManager.put("FileChooser.upFolderAccessibleName", HFSUtil
				.getRecurso("HFSFileChooser.upFolderAccessibleName"));
		UIManager.put("FileChooser.homeFolderToolTipText", HFSUtil
				.getRecurso("HFSFileChooser.homeFolderToolTipText"));
		UIManager.put("FileChooser.homeFolderAccessibleName", HFSUtil
				.getRecurso("HFSFileChooser.homeFolderAccessibleName"));
		UIManager.put("FileChooser.newFolderToolTipText", HFSUtil
				.getRecurso("HFSFileChooser.newFolderToolTipText"));
		UIManager.put("FileChooser.newFolderAccessibleName", HFSUtil
				.getRecurso("HFSFileChooser.newFolderAccessibleName"));
		UIManager.put("FileChooser.listViewButtonToolTipText", HFSUtil
				.getRecurso("HFSFileChooser.listViewButtonToolTipText"));
		UIManager.put("FileChooser.listViewButtonAccessibleName", HFSUtil
				.getRecurso("HFSFileChooser.listViewButtonAccessibleName"));
		UIManager.put("FileChooser.detailsViewButtonToolTipText", HFSUtil
				.getRecurso("HFSFileChooser.detailsViewButtonToolTipText"));
		UIManager.put("FileChooser.detailsViewButtonAccessibleName", HFSUtil
				.getRecurso("HFSFileChooser.detailsViewButtonAccessibleName"));
		UIManager.put("FileChooser.cancelButtonText", HFSUtil
				.getRecurso("HFSFileChooser.cancelButtonText"));
		UIManager.put("FileChooser.cancelButtonToolTipText", HFSUtil
				.getRecurso("HFSFileChooser.cancelButtonToolTipText"));
		UIManager.put("FileChooser.cancelButtonMnemonic", HFSUtil
				.getRecurso("HFSFileChooser.cancelButtonMnemonic"));

		UIManager.put("FileChooser.fileNameHeaderText", HFSUtil
				.getRecurso("HFSFileChooser.fileNameHeaderText"));
		UIManager.put("FileChooser.fileSizeHeaderText", HFSUtil
				.getRecurso("HFSFileChooser.fileSizeHeaderText"));
		UIManager.put("FileChooser.fileTypeHeaderText", HFSUtil
				.getRecurso("HFSFileChooser.fileTypeHeaderText"));
		UIManager.put("FileChooser.fileDateHeaderText", HFSUtil
				.getRecurso("HFSFileChooser.fileDateHeaderText"));
		UIManager.put("FileChooser.fileAttrHeaderText", HFSUtil
				.getRecurso("HFSFileChooser.fileAttrHeaderText"));

		UIManager.put("FileChooser.openButtonText", HFSUtil
				.getRecurso("HFSFileChooser.openButtonText"));
		UIManager.put("FileChooser.openButtonMnemonic", HFSUtil
				.getRecurso("HFSFileChooser.openButtonMnemonic"));
		UIManager.put("FileChooser.openButtonToolTipText", HFSUtil
				.getRecurso("HFSFileChooser.openButtonToolTipText"));

		UIManager.put("FileChooser.saveButtonText", HFSUtil
				.getRecurso("HFSFileChooser.saveButtonText"));
		UIManager.put("FileChooser.saveButtonMnemonic", HFSUtil
				.getRecurso("HFSFileChooser.saveButtonMnemonic"));
		UIManager.put("FileChooser.saveButtonToolTipText", HFSUtil
				.getRecurso("HFSFileChooser.saveButtonToolTipText"));

		UIManager.put("FileChooser.updateButtonText", HFSUtil
				.getRecurso("HFSFileChooser.updateButtonText"));
		UIManager.put("FileChooser.updateButtonMnemonic", HFSUtil
				.getRecurso("HFSFileChooser.updateButtonMnemonic"));
		UIManager.put("FileChooser.updateButtonToolTipText", HFSUtil
				.getRecurso("HFSFileChooser.updateButtonToolTipText"));

		UIManager.put("FileChooser.helpButtonText", HFSUtil
				.getRecurso("HFSFileChooser.helpButtonText"));
		UIManager.put("FileChooser.helpButtonMnemonic", HFSUtil
				.getRecurso("HFSFileChooser.helpButtonMnemonic"));
		UIManager.put("FileChooser.helpButtonToolTipText", HFSUtil
				.getRecurso("HFSFileChooser.helpButtonToolTipText"));

		UIManager.put("FileChooser.acceptAllFileFilterText", HFSUtil
				.getRecurso("HFSFileChooser.acceptAllFileFilterText"));
	}

	public int mostrar(Component pai) {
		return this.showDialog(pai, null);
	}

	public int mostrarAbrir(Component pai) {
		return this.showOpenDialog(pai);
	}

	public int mostrarSalvar(Component pai) {
		return this.showSaveDialog(pai);
	}
}

class Filtro extends FileFilter {
	public boolean accept(File arquivo) {
		// String nomeArquivo = arquivo.getName();
		// return nomeArquivo.endsWith("*.java");
		return true;
	}

	public String getDescription() {
		return HFSUtil.getRecurso("HFSFileChooser.todosDiretorios");
		// return ".java";
	}
}

class FilePreviewer extends JComponent implements PropertyChangeListener {
	private static final long serialVersionUID = 419111212721417789L;
	ImageIcon thumbnail = null;

	public FilePreviewer(JFileChooser fc) {
		setPreferredSize(new Dimension(100, 50));
		fc.addPropertyChangeListener(this);
		setBorder(new BevelBorder(BevelBorder.LOWERED));
	}

	public void loadImage(File f) {
		if (f == null) {
			thumbnail = null;
		} else {
			ImageIcon tmpIcon = new ImageIcon(f.getPath());
			if (tmpIcon.getIconWidth() > 90) {
				thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(
						90, -1, Image.SCALE_DEFAULT));
			} else {
				thumbnail = tmpIcon;
			}
		}
	}

	public void propertyChange(PropertyChangeEvent e) {
		String prop = e.getPropertyName();
		if (prop == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
			if (isShowing()) {
				loadImage((File) e.getNewValue());
				repaint();
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (thumbnail != null) {
			int x = getWidth() / 2 - thumbnail.getIconWidth() / 2;
			int y = getHeight() / 2 - thumbnail.getIconHeight() / 2;
			if (y < 0) {
				y = 0;
			}

			if (x < 5) {
				x = 5;
			}
			thumbnail.paintIcon(this, g, x, y);
		}
	}
}
