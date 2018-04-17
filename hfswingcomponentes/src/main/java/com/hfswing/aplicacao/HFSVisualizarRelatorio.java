package com.hfswing.aplicacao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import com.hfswing.componentes.HFSReportViewer;

public class HFSVisualizarRelatorio extends JFrame {
	private static final long serialVersionUID = 8961429020108095621L;

	private HFSReportViewer panelPrincipal;

	private JasperPrint jrPrint;

	public HFSVisualizarRelatorio(JasperPrint jrPrint, String titulo,
			String localidadeLingua, String localidadePais) throws JRException {
		this.jrPrint = jrPrint;
		initComponents(titulo, localidadeLingua, localidadePais);
	}

	private void initComponents(String titulo, String localidadeLingua,
			String localidadePais) throws JRException {
		panelPrincipal = new HFSReportViewer(jrPrint, localidadeLingua,
				localidadePais);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(titulo);
		setMinimumSize(new Dimension(500, 500));
		setPreferredSize(new Dimension(500, 500));

		getContentPane().add(panelPrincipal, BorderLayout.CENTER);

		pack();
	}

}
