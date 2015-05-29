package org.sp.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JRootPane jRootPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setTitle("\u767B\u9646");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 402, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		MyPanel panel = new MyPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(50, 244, 72, 18);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 15));
		label.setForeground(new Color(255, 0, 0));
		label.setBounds(50, 277, 72, 18);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(122, 242, 178, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(122, 275, 178, 24);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string = textField.getText();
				@SuppressWarnings("deprecation")
				String string2 = passwordField.getText();
				if(string.equals("yangyuke") && string2.equals(new String("5201314"))){
					setVisible(false);
					new MainFrame().setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(null, "’À∫≈ªÚ√‹¬Î¥ÌŒÛ£°");
				}		
			}
		});
		btnNewButton.setBounds(119, 308, 77, 27);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(223, 308, 77, 27);
		panel.add(btnNewButton_1);
		
		jRootPane = getRootPane();
		jRootPane.setDefaultButton(btnNewButton);
	}
}

class MyPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image image;
	public MyPanel(){
		image = new ImageIcon("src/org/sp/pic/login.jpg").getImage();
	}
	protected void paintComponent(Graphics g) {
		Graphics2D graphics2d = (Graphics2D)g;
		graphics2d.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);		
	}
}
