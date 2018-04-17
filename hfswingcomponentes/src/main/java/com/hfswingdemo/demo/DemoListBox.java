package com.hfswingdemo.demo;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.hfswing.componentes.HFSItem;
import com.hfswing.componentes.HFSListBox;

public class DemoListBox extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private ArrayList<HFSItem> listbox_items;
	private HFSListBox listBox1;
	private JLabel label1;
	private HFSListBox listBox2;
	private JLabel label2;

	public DemoListBox() {
		listbox_items = new ArrayList<HFSItem>();
		listbox_items.add(new HFSItem("1", "lista item 1"));
		listbox_items.add(new HFSItem("2", "lista item 2"));
		listbox_items.add(new HFSItem("3", "lista item 3"));
		listbox_items.add(new HFSItem("4", "lista item 4"));
		listbox_items.add(new HFSItem("5", "lista item 5"));
		listbox_items.add(new HFSItem("6", "lista item 6"));
		listbox_items.add(new HFSItem("7", "lista item 7"));
		listbox_items.add(new HFSItem("8", "lista item 8"));
		listbox_items.add(new HFSItem("9", "lista item 9"));
		listbox_items.add(new HFSItem("10", "lista item 10"));
		initComponents();
	}

	private void initComponents() {
		setTitle("Demo HFSListBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 0, 0));
		contentPane.add(getListBox1());
		contentPane.add(getLabel1());
		contentPane.add(getListBox2());
		contentPane.add(getLabel2());
	}

	private HFSListBox getListBox1() {
		if (listBox1 == null) {
			listBox1 = new HFSListBox();
			listBox1.getListBox().addListSelectionListener(
					new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent evt) {
							getLabel1().setText(
									"código: "
											+ listBox1.getItemSelecionado()
													.getId());
							// listBox.valorSelecionadoMudou(evt);
						}
					});
			listBox1.setItems(listbox_items);
		}
		return listBox1;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoListBox frame = new DemoListBox();
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

	private HFSListBox getListBox2() {
		if (listBox2 == null) {
			listBox2 = new HFSListBox();
			listBox2.getListBox().addListSelectionListener(
					new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent evt) {
							getLabel2().setText(
									"código: "
											+ listBox2.getItemSelecionado()
													.getId());
							// listBox.valorSelecionadoMudou(evt);
						}
					});
			listBox2.setItems(listbox_items);
		}
		return listBox2;
	}

	private JLabel getLabel2() {
		if (label2 == null) {
			label2 = new JLabel("New label");
		}
		return label2;
	}
}
