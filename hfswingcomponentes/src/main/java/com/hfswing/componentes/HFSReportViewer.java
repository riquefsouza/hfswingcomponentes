package com.hfswing.componentes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.UIManager;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

import com.hfswing.util.HFSUtil;

public class HFSReportViewer extends JRViewer {
	private static final long serialVersionUID = 2556525314124951123L;

	private JButton btnSalvarPDF;

	private JasperPrint jrPrint;

	public HFSReportViewer(JasperPrint jrPrint, String localidadeLingua,
			String localidadePais) throws JRException {
		super(jrPrint);
		this.jrPrint = jrPrint;
		initComponents(localidadeLingua, localidadePais);
	}

	private void initComponents(String localidadeLingua, String localidadePais) {
		btnSalvarPDF = new JButton();
		btnSalvarPDF.setIcon(HFSUtil.getImagem("pdf.gif"));
		btnSalvarPDF.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.btnSalvarPDF"));
		btnSalvarPDF.setMinimumSize(new Dimension(28, 23));
		if (UIManager.getLookAndFeel().getID().equals("Motif")) {
			Dimension dim = new Dimension(38, 38);
			btnSalvarPDF.setPreferredSize(dim);
						
			for (Component item : tlbToolBar.getComponents()) {
				if (item.getName().equals("btnFirst")){
					item.setPreferredSize(dim);		
				}
			}
			/*
			btnLast.setPreferredSize(dim);
			btnNext.setPreferredSize(dim);
			btnPrevious.setPreferredSize(dim);
			btnPrint.setPreferredSize(dim);
			btnReload.setPreferredSize(dim);
			btnSave.setPreferredSize(dim);
			btnZoomIn.setPreferredSize(dim);
			btnZoomOut.setPreferredSize(dim);
			*/
		} else
			btnSalvarPDF.setPreferredSize(new Dimension(28, 23));
		btnSalvarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSalvarPDFActionPerformed(evt);
			}
		});

		tlbToolBar.add(btnSalvarPDF);

		mudarLingua(localidadeLingua, localidadePais);
	}

	private void mudarLingua(String localidadeLingua, String localidadePais) {
		HFSUtil.getParams().setLocalidadeLingua(localidadeLingua);
		HFSUtil.getParams().setLocalidadePais(localidadePais);

		/*
		btnActualSize.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.TamanhoAtual"));
		btnFirst.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.PrimeiraPagina"));
		btnFitPage.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.CaberPagina"));
		btnFitWidth.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.CaberComprimento"));
		btnLast.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.UltimaPagina"));
		btnNext.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.ProximaPagina"));
		btnPrevious.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.PaginaAnterior"));
		btnPrint.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.Imprimir"));
		btnReload.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.Recarregar"));
		btnSave.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.Salvar"));
		btnZoomIn.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.AumentarZoom"));
		btnZoomOut.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.DiminuirZoom"));
		cmbZoom.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.PercentualZoom"));
		txtGoTo.setToolTipText(HFSUtil
				.getRecurso("HFSReportViewer.VaParaPagina"));
		*/		
		textoStatus();
	}

	private void textoStatus() {
		String[] pedacos = lblStatus.getText().split(" ");
		String status = "";
		for (int i = 0; i < pedacos.length; i++) {
			if (pedacos[i].equals("Page"))
				pedacos[i] = HFSUtil.getRecurso("HFSReportViewer.Pagina");
			if (pedacos[i].equals("of"))
				pedacos[i] = HFSUtil.getRecurso("HFSReportViewer.de");
			status += pedacos[i] + " ";
		}
		lblStatus.setText(status);
	}

	protected void refreshPage() {
		super.viewerContext.refreshPage();
		textoStatus();
	}

	private void btnSalvarPDFActionPerformed(ActionEvent evt) {
		HFSFileChooser escolha = new HFSFileChooser(HFSFileChooser.SAVE_DIALOG);
		escolha.setDialogTitle(HFSUtil
				.getRecurso("HFSReportViewer.TituloSalvarPDF"));
		int retval = escolha.mostrarSalvar(this);
		if (retval == HFSFileChooser.APPROVE_OPTION) {
			File arquivo = escolha.getSelectedFile();
			if (arquivo != null) {
				try {
					JasperExportManager.exportReportToPdfFile(jrPrint,
							arquivo.getPath());
				} catch (JRException e) {
					HFSOptionPane.mensagemErro(this,
							"HFSReportViewer.ErroSalvarPDF",
							e.getLocalizedMessage());
				}
			}
		}
	}
}
