package org.sp.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import org.sp.DAO.GoodsDAO;
import org.sp.model.Goods;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			DeleteDialog dialog = new DeleteDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public DeleteDialog(final String goodsName,String storeNum,String earn) {
		setTitle("\u5220\u9664\u4FE1\u606F");
		setBounds(100, 100, 450, 259);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(189, 47, 140, 24);
			contentPanel.add(textField);
			textField.setColumns(10);
			textField.setText(goodsName);
		}
		{
			JLabel lblNewLabel = new JLabel("\u5546\u54C1\u540D\u79F0\uFF1A");
			lblNewLabel.setBounds(91, 50, 116, 18);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel label = new JLabel("\u5546\u54C1\u5E93\u5B58\uFF1A");
			label.setBounds(91, 87, 116, 18);
			contentPanel.add(label);
		}
		{
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setColumns(10);
			textField_1.setBounds(189, 84, 140, 24);
			contentPanel.add(textField_1);
			textField_1.setText(storeNum);
		}
		{
			JLabel label = new JLabel("\u5546\u54C1\u76C8\u5229\uFF1A");
			label.setBounds(91, 121, 116, 18);
			contentPanel.add(label);
		}
		{
			textField_2 = new JTextField();
			textField_2.setEditable(false);
			textField_2.setColumns(10);
			textField_2.setBounds(189, 118, 140, 24);
			contentPanel.add(textField_2);
			textField_2.setText(earn);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GoodsDAO goodsDAO = new GoodsDAO();
						goodsDAO.delete((Goods)goodsDAO.findByGoodsName(goodsName).get(0));
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
