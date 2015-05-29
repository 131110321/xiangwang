package org.sp.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;

import org.sp.DAO.GoodsDAO;
import org.sp.DAO.SaleDAO;
import org.sp.DAO.StockDAO;
import org.sp.model.Goods;
import org.sp.model.Sale;
import org.sp.model.Stock;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("\u5546\u5E97\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 598, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 78, 534, 282);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 13, 506, 256);
		panel.add(scrollPane);
		
		table = new JTable();
		
		GoodsDAO goodsDAO = new GoodsDAO();
		SaleDAO saleDAO = new SaleDAO();
		StockDAO stockDAO = new StockDAO();
		
		Iterator<Goods> goodIterator = goodsDAO.findAll().iterator();//找出所有商品记录
		
		//数据库里的数据传送到这里，之后再传入二维数组
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		while(goodIterator.hasNext()){
			int ID = goodIterator.next().getGoodsId();
			Goods goods = goodsDAO.findById(ID);
			Sale sale = (Sale)saleDAO.findByProperty("goods", goods).get(0);
			Stock stock = (Stock)stockDAO.findByProperty("goods", goods).get(0);
			Float f = sale.grossSale() - stock.totalInCost();
					
			list.add(new String[]{
				goods.getGoodsId().toString(),
				goods.getGoodsName(),
				goods.getUnitPrice().toString(),
				goods.getGoodsQuantity().toString(),
				stock.getTotalQuantity().toString(),
				sale.getTotalQuantity().toString(),
				f.toString()});
		}
		
		
		Iterator<String[]> iterator = list.iterator();//迭代器
		
		//动态获得数组，以免浪费空间
		final String[][] strings = new String[list.size()][7];
	
		int i = 0;
		while(iterator.hasNext()){
			strings[i] = iterator.next();
			i++;
		}
		
		table.setModel(new DefaultTableModel(
			strings,
			new String[] {
				"  ID", " \u5546\u54C1\u540D\u79F0", " \u5355\u4EF7", " \u5E93\u5B58", " \u8FDB\u8D27\u603B\u6570", " \u5356\u51FA\u603B\u6570", "\u603B\u76C8\u5229"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(127);
		table.getColumnModel().getColumn(4).setPreferredWidth(119);
		table.getColumnModel().getColumn(5).setPreferredWidth(128);
		table.getColumnModel().getColumn(6).setPreferredWidth(98);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("\u65B0\u589E\u5546\u54C1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddGoodsDialog().setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(31, 38, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u5220\u9664\u5546\u54C1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				new DeleteDialog(strings[i][1],strings[i][3],strings[i][6]).setVisible(true);
				setVisible(false);
			}
		});
		button.setBounds(158, 38, 113, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u8FDB\u8D27");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				new InGoodsDialog(strings[i][1]).setVisible(true);
				setVisible(false);
			}
		});
		button_1.setBounds(285, 38, 113, 27);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u5356\u51FA");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				new SaleDialog(strings[i][1],strings[i][2]).setVisible(true);
				setVisible(false);
			}
		});
		button_2.setBounds(417, 38, 113, 27);
		contentPane.add(button_2);
	}
	
	public MainFrame(final String[][] strings) {
		setTitle("\u5546\u5E97\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 598, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 78, 534, 282);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 13, 506, 256);
		panel.add(scrollPane);
		
		table = new JTable();
		
		GoodsDAO goodsDAO = new GoodsDAO();
		SaleDAO saleDAO = new SaleDAO();
		StockDAO stockDAO = new StockDAO();
		
		Iterator<Goods> goodIterator = goodsDAO.findAll().iterator();//找出所有商品记录
		
		//数据库里的数据传送到这里，之后再传入二维数组
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		while(goodIterator.hasNext()){
			int ID = goodIterator.next().getGoodsId();
			Goods goods = goodsDAO.findById(ID);
			Sale sale = (Sale)saleDAO.findByProperty("goods", goods).get(0);
			Stock stock = (Stock)stockDAO.findByProperty("goods", goods).get(0);
			Float f = sale.grossSale() - stock.totalInCost();
					
			list.add(new String[]{
				goods.getGoodsId().toString(),
				goods.getGoodsName(),
				goods.getUnitPrice().toString(),
				goods.getGoodsQuantity().toString(),
				stock.getTotalQuantity().toString(),
				sale.getTotalQuantity().toString(),
				f.toString()});
		}
		
		
		Iterator<String[]> iterator = list.iterator();//迭代器
		
		//动态获得数组，以免浪费空间
//		final String[][] strings = new String[list.size()][7];
	
		int i = 0;
		while(iterator.hasNext()){
			strings[i] = iterator.next();
			i++;
		}
		
		table.setModel(new DefaultTableModel(
			strings,
			new String[] {
				"  ID", " \u5546\u54C1\u540D\u79F0", " \u5355\u4EF7", " \u5E93\u5B58", " \u8FDB\u8D27\u603B\u6570", " \u5356\u51FA\u603B\u6570", "\u603B\u76C8\u5229"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(127);
		table.getColumnModel().getColumn(4).setPreferredWidth(119);
		table.getColumnModel().getColumn(5).setPreferredWidth(128);
		table.getColumnModel().getColumn(6).setPreferredWidth(98);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("\u65B0\u589E\u5546\u54C1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddGoodsDialog().setVisible(true);
			}
		});
		btnNewButton.setBounds(31, 38, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u5220\u9664\u5546\u54C1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				new DeleteDialog(strings[i][1],strings[i][3],strings[i][6]).setVisible(true);
				setVisible(false);
			}
		});
		button.setBounds(158, 38, 113, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u8FDB\u8D27");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				new InGoodsDialog(strings[i][1]).setVisible(true);
				setVisible(false);
			}
		});
		button_1.setBounds(285, 38, 113, 27);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u5356\u51FA");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				new SaleDialog(strings[i][1],strings[i][2]).setVisible(true);
				setVisible(false);
			}
		});
		button_2.setBounds(417, 38, 113, 27);
		contentPane.add(button_2);
	}
}
