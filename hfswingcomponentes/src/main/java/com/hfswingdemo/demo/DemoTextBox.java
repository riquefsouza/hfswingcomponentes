package com.hfswingdemo.demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hfswing.componentes.HFSTextBox;

public class DemoTextBox extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private HFSTextBox textBox;
	private HFSTextBox textBox2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoTextBox frame = new DemoTextBox();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DemoTextBox() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Demo TextBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextBox());
		contentPane.add(getTextBox2());
	}

	private HFSTextBox getTextBox() {
		if (textBox == null) {
			textBox = new HFSTextBox();
			textBox.setBounds(10, 24, 148, 34);
		}
		return textBox;
	}

	private HFSTextBox getTextBox2() {
		if (textBox2 == null) {
			textBox2 = new HFSTextBox();
			textBox2.setBounds(10, 77, 148, 34);
		}
		return textBox2;
	}
}
