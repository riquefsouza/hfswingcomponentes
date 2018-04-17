package com.hfswingdemo.demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hfswing.componentes.HFSTextField;

public class DemoTextField extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private HFSTextField textField;
	private HFSTextField textField2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoTextField frame = new DemoTextField();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DemoTextField() {
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

	private HFSTextField getTextBox() {
		if (textField == null) {
			textField = new HFSTextField();
			textField.setMaxLength(10);
			textField.setBounds(10, 24, 148, 34);
		}
		return textField;
	}

	private HFSTextField getTextBox2() {
		if (textField2 == null) {
			textField2 = new HFSTextField();
			textField2.setBounds(10, 77, 148, 34);
		}
		return textField2;
	}
}
