package com.phuoc.userinterface;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.phuoc.Sever.DataReq;
import com.phuoc.Sever.DataResp;
import com.phuoc.Sever.Req;
import com.phuoc.data.CacheData;
import com.phuoc.models.ChiNhanh;
import com.phuoc.models.UserAccount;

public class WaterServerLogin extends JFrame {

	public static void main(String[] args) {
		JFrame jf = new WaterServerLogin();
		jf.setVisible(true);
	}

	public WaterServerLogin() {

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 950, 591);
		getContentPane().setLayout(null);

		JLabel lblusername = new JLabel("username:");
		lblusername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblusername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblusername.setBounds(565, 78, 125, 32);
		add(lblusername);

		JLabel lblpassword = new JLabel("password:");
		lblpassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblpassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblpassword.setBounds(565, 134, 125, 32);
		add(lblpassword);

		CacheData cacheData = new CacheData();
	

	    JComboBox<String> cb = new JComboBox<String>(cacheData.getThongTinCNs());
	    
		JTextField textFieldUsername = new JTextField();
		JTextField textFieldPassword = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(719, 72, 158, 32);
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(719, 134, 158, 32);
		cb.setBounds(719, 10, 158, 32);
		

		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int selectedIndex = cb.getSelectedIndex();
				//System.out.println(selectedIndex);
				cacheData.setThongTinCn(cb.getSelectedItem().toString());
				cacheData.setMaCN(cacheData.getMaCNs()[selectedIndex]);
				cacheData.setPost(cacheData.getPorts()[selectedIndex]);

				cacheData.setUseraccount(new UserAccount(textFieldUsername.getText(), textFieldPassword.getText()));

				Req req = new Req(new DataReq(DataReq.Login, cacheData.getUseraccount(), null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));

				if (req.getDataResp().getState() == DataResp.Success) {
					JOptionPane.showMessageDialog(null, "Login successfull");
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
                                cacheData.setMaCN(req.getDataResp().getObject()+"");
								JFrame frame = new AppFrame(cacheData);		
								frame.setVisible(true);
								setVisible(false);			
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} else {
					JOptionPane.showMessageDialog(null, "Sai thông tin!");;
				}

			}
		});

		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(719, 200, 158, 32);
		getContentPane().add(btnLogin);
		getContentPane().add(textFieldUsername);
		getContentPane().add(textFieldPassword);
		getContentPane().add(cb);

	}

}
