package com.hfswingdemo.demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hfswing.componentes.HFSDualList;
import com.hfswing.componentes.HFSItem;

public class DemoDualList extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private HFSDualList dualList;
	private ArrayList<HFSItem> listbox_items;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoDualList frame = new DemoDualList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DemoDualList() {
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
		setTitle("Demo HFSDualList");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getDualList(), BorderLayout.CENTER);
	}

	private HFSDualList getDualList() {
		if (dualList == null) {
			dualList = new HFSDualList();
			dualList.setItemsOrigem(listbox_items);
		}
		return dualList;
	}
}
