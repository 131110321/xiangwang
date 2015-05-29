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
import org.sp.model.Goods;
import org.sp.model.Sale;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			SaleDialog dialog = new SaleDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public SaleDialog(final String goodsName,String quantity) {
		setTitle("\u51FA\u552E\u4FE1\u606F");
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\u5546\u54C1\u540D\u79F0\uFF1A");
			lblNewLabel.setBounds(69, 46, 99, 18);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel label = new JLabel("\u51FA\u552E\u5355\u4EF7\uFF1A");
			label.setBounds(69, 98, 99, 18);
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(154, 43, 219, 24);
			contentPanel.add(textField);
			textField.setColumns(10);
			textField.setText(goodsName);
		}
		{
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setColumns(10);
			textField_1.setBounds(154, 95, 219, 24);
			contentPanel.add(textField_1);
			textField_1.setText(quantity);
		}
		{
			JLabel label = new JLabel("\u51FA\u552E\u6570\u91CF\uFF1A");
			label.setBounds(69, 143, 99, 18);
			contentPanel.add(label);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(154, 140, 219, 24);
			contentPanel.add(textField_2);
//			textField_2.setText(quantity);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SaleDAO saleDAO = new SaleDAO();
						GoodsDAO goodsDAO = new GoodsDAO();
						String s1 = textField_2.getText().trim();
						double outnumd = Double.parseDouble(s1);
						int outnum = (int)outnumd;
						System.out.println(outnum);
						saleDAO.save(new Sale((Goods)goodsDAO.findByGoodsName(goodsName).get(0),outnum));
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
