package com.hfswingdemo.demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hfswing.componentes.HFSCalculator;
import com.hfswing.componentes.HFSCalculator.BotaoTamanho;
import com.hfswing.componentes.HFSCalculatorChooser;

public class DemoCalculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private HFSCalculator calculator;
	private HFSCalculatorChooser calculatorChooser;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoCalculator frame = new DemoCalculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DemoCalculator() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 527, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCalculator());
		contentPane.add(getCalculatorChooser());
	}

	private HFSCalculator getCalculator() {
		if (calculator == null) {
			calculator = new HFSCalculator(BotaoTamanho.PEQUENO);
			calculator.setBounds(5, 5, 216, 226);
		}
		return calculator;
	}

	private HFSCalculatorChooser getCalculatorChooser() {
		if (calculatorChooser == null) {
			calculatorChooser = new HFSCalculatorChooser();
			calculatorChooser.setBounds(292, 5, 216, 34);
		}
		return calculatorChooser;
	}
}
