package com.phuoc.serverinterface;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.phuoc.models.MonitoringModel;
import com.phuoc.models.ThongTinChiNhanh;




public class MonitoringFrame extends JFrame {
	private MonitoringPanel monitoringPanel;
	private JLabel lblTitle;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new MonitoringFrame();		
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public MonitoringFrame() {
		setTitle(ThongTinChiNhanh.TenCN + " " + ThongTinChiNhanh.DiaChi);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 591);
		getContentPane().setLayout(null);
		

		JButton btnMonitoring = new JButton("Monitoring");
		btnMonitoring.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//remove(panel);
				//panel = new MonitoringPanel(monitoringModels);
				//panel.setBounds(20, 71, 900, 459);
				//getContentPane().add(panel);
				//lblTitle.setText("Quan Ly Bien Lai");
				//panel.updateUI();
				
				
			}
		});
		btnMonitoring.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMonitoring.setBounds(41, 11, 145, 49);
		getContentPane().add(btnMonitoring);
		
		monitoringPanel = new MonitoringPanel();
		monitoringPanel.setBounds(20, 71, 900, 459);
		getContentPane().add(monitoringPanel);
		
	    lblTitle = new JLabel("Monitoring System");
		lblTitle.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitle.setBounds(593, 11, 306, 49);
		getContentPane().add(lblTitle);
		
	}


	public MonitoringPanel getMonitoringPanel() {
		return monitoringPanel;
	}


	public void setMonitoringPanel(MonitoringPanel monitoringPanel) {
		this.monitoringPanel = monitoringPanel;
	}
	




}
