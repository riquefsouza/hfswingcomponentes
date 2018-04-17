package com.hfswingdemo.demo;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hfswing.componentes.HFSComboBox;
import com.hfswing.componentes.HFSItem;

public class DemoComboBox extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private ArrayList<HFSItem> comboBox_items;
	private JLabel label1;
	private JLabel label2;
	private HFSComboBox comboBox1;
	private HFSComboBox comboBox2;

	public DemoComboBox() {
		comboBox_items = new ArrayList<HFSItem>();
		comboBox_items.add(new HFSItem("1", "lista item 1"));
		comboBox_items.add(new HFSItem("2", "lista item 2"));
		comboBox_items.add(new HFSItem("3", "lista item 3"));
		comboBox_items.add(new HFSItem("4", "lista item 4"));
		comboBox_items.add(new HFSItem("5", "lista item 5"));
		comboBox_items.add(new HFSItem("6", "lista item 6"));
		comboBox_items.add(new HFSItem("7", "lista item 7"));
		comboBox_items.add(new HFSItem("8", "lista item 8"));
		comboBox_items.add(new HFSItem("9", "lista item 9"));
		comboBox_items.add(new HFSItem("10", "lista item 10"));
		initComponents();
	}

	private void initComponents() {
		setTitle("Demo HFSComboBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 143);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 0, 0));
		contentPane.add(getComboBox1());
		contentPane.add(getLabel1());
		contentPane.add(getComboBox2());
		contentPane.add(getLabel2());
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoComboBox frame = new DemoComboBox();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel getLabel1() {
		if (label1 == null) {
			label1 = new JLabel("New label");
		}
		return label1;
	}

	private JLabel getLabel2() {
		if (label2 == null) {
			label2 = new JLabel("New label");
		}
		return label2;
	}
	private HFSComboBox getComboBox1() {
		if (comboBox1 == null) {
			comboBox1 = new HFSComboBox();
			comboBox1.getComboBox().addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					getLabel1().setText(
							"código: "
									+ comboBox1.getItemSelecionado()
											.getId());
				}
			});
			comboBox1.setItems(comboBox_items);
		}
		return comboBox1;
	}
	private HFSComboBox getComboBox2() {
		if (comboBox2 == null) {
			comboBox2 = new HFSComboBox();
			comboBox2.getComboBox().addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					getLabel2().setText(
							"código: "
									+ comboBox2.getItemSelecionado()
											.getId());
				}
			});
			comboBox2.setItems(comboBox_items);
		}
		return comboBox2;
	}
}
