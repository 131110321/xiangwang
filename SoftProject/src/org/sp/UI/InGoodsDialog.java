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
import org.sp.DAO.StockDAO;
import org.sp.model.Goods;
import org.sp.model.Stock;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InGoodsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			InGoodsDialog dialog = new InGoodsDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public InGoodsDialog(final String goodsName) {
		
		setTitle("\u8FDB\u8D27\u4FE1\u606F");
		setBounds(100, 100, 450, 284);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\u5546\u54C1\u540D\u79F0\uFF1A");
			lblNewLabel.setBounds(67, 54, 96, 18);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel label = new JLabel("\u8FDB\u5165\u5355\u4EF7\uFF1A");
			label.setBounds(67, 100, 96, 18);
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(159, 51, 202, 24);
			contentPanel.add(textField);
			textField.setColumns(10);
			textField.setText(goodsName);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(159, 97, 202, 24);
			contentPanel.add(textField_1);
		}
		{
			JLabel label = new JLabel("\u8FDB\u8D27\u6570\u91CF\uFF1A");
			label.setBounds(67, 140, 96, 18);
			contentPanel.add(label);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(159, 137, 202, 24);
			contentPanel.add(textField_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u8BA4");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						StockDAO stockDAO = new StockDAO();
						GoodsDAO goodsDAO = new GoodsDAO();
						String s1 = textField_1.getText().trim();
						int inprice = Integer.parseInt(s1);
						String s2 = textField_2.getText().trim();
						int quantity = Integer.parseInt(s2);
						stockDAO.save(new Stock((Goods)goodsDAO.findByGoodsName(goodsName).get(0),(float)inprice,quantity));
						setVisible(false);
						new MainFrame().setVisible(true);
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
