package org.sp.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.sp.DAO.GoodsDAO;
import org.sp.DAO.SaleDAO;
import org.sp.DAO.StockDAO;
import org.sp.model.Goods;
import org.sp.model.Sale;
import org.sp.model.Stock;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class AddGoodsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddGoodsDialog dialog = new AddGoodsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddGoodsDialog() {
		setBounds(100, 100, 450, 200);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\u5546\u54C1\u540D\u79F0\uFF1A");
			lblNewLabel.setBounds(44, 29, 90, 18);
			contentPanel.add(lblNewLabel);
		}
		
		JLabel lblNewLabel_1 = new JLabel("\u5546\u54C1\u5355\u4EF7\uFF1A");
		lblNewLabel_1.setBounds(44, 73, 90, 18);
		contentPanel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(134, 26, 245, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(134, 70, 245, 24);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GoodsDAO goodsDAO = new GoodsDAO();
						SaleDAO saleDAO = new SaleDAO();
						StockDAO stockDAO = new StockDAO();
						
						//存新增的商品
						String s = textField_1.getText().trim();
						Float f1 = Float.parseFloat(s);
						goodsDAO.save(new Goods(textField.getText(),f1,0));
						Goods good = (Goods)goodsDAO.findByProperty("goodsName", textField.getText()).get(0);
						Integer integer = good.getGoodsId();
						saleDAO.save(new Sale(good,0));
						stockDAO.save(new Stock(good,(float)0,0));
						
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
						new MainFrame(strings).setVisible(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
