package com.hfswing.componentes;

import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.JButton;
import javax.swing.UIManager;

public class HFSButton extends JButton {
	private static final long serialVersionUID = 390266211838123290L;

	public HFSButton() {
		super();
		setPreferredSize(new Dimension(56, 23));
	}

	public HFSButton(String texto) {
		super(texto);
		setPreferredSize(new Dimension(56, 23));
	}
	
	public void setPreferredSize(Dimension preferredSize) {
		if (this.getText().length() > 0) {
			FontMetrics fm = this.getFontMetrics(this.getFont());
			int nlargura = fm.getMaxAscent() + fm.getMaxDescent()
					+ fm.getMaxAdvance();
			nlargura += fm.stringWidth(this.getText());

			// if (nlargura > preferredSize.width)
			if (UIManager.getLookAndFeel().getID().equals("Windows"))
				preferredSize.width = nlargura + 10;
			else if (UIManager.getLookAndFeel().getID().equals("Motif")) {	
				preferredSize.width = nlargura;
				preferredSize.height += 10; 
			} else
				preferredSize.width = nlargura;
		}
		super.setPreferredSize(preferredSize);
	}
}
