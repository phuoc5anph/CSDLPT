package com.phuoc.userinterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.phuoc.data.CacheData;
import com.phuoc.models.ChiNhanh;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppFrame extends JFrame {

	private CacheData cacheData;
	
	private JPanel panel;
	private JLabel lblTitle;

	/**
	 * Create the frame.
	 */
	public AppFrame(CacheData cacheData) {
		this.cacheData = cacheData;
		setTitle(cacheData.getThongTinCn());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 599);
		getContentPane().setLayout(null);
		
		JButton btnKhoa = new JButton("Khoa");
		btnKhoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(panel);
				panel=new KhoaPanel(cacheData);
				panel.setBounds(20, 71, 950, 459);
				getContentPane().add(panel);
				lblTitle.setText("Quan Ly Khoa");
				panel.updateUI();
				
				
			}
		});
		btnKhoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnKhoa.setBounds(20, 11, 74, 49);
		getContentPane().add(btnKhoa);
		
		JButton btnHocPhan = new JButton("HocPhan");
		btnHocPhan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(panel);
				panel=new HocPhanPanel(cacheData);
				panel.setBounds(20, 71, 950, 459);
				getContentPane().add(panel);
				lblTitle.setText("Quan Ly Hoc Phan");
				panel.updateUI();
			}
		});
		btnHocPhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHocPhan.setBounds(433, 11, 98, 49);
		getContentPane().add(btnHocPhan);
		
		JButton btnChuyenNganh = new JButton("ChuyenNganh");
		btnChuyenNganh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(panel);
				panel=new ChuyenNganhPanel(cacheData);
				panel.setBounds(20, 71, 950, 459);
				getContentPane().add(panel);
				lblTitle.setText("QuanLyChuyenNganh");
				panel.updateUI();
			}
		});
		btnChuyenNganh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChuyenNganh.setBounds(188, 11, 127, 49);
		getContentPane().add(btnChuyenNganh);
		
		panel = new KhoaPanel(cacheData);
		panel.setBounds(20, 71, 950, 464);
		getContentPane().add(panel);
		
	    lblTitle = new JLabel("Quan Ly Khoa");
		lblTitle.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitle.setBounds(647, 7, 306, 49);
		getContentPane().add(lblTitle);
		
		JButton btnHocPhanTrongKhoaHoc = new JButton("HocPhanTKhoaHoc");
		btnHocPhanTrongKhoaHoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(panel);
				panel=new HocPhanTrongKhoaHocPanel(cacheData);
				panel.setBounds(20, 71, 950, 459);
				getContentPane().add(panel);
				lblTitle.setText("QLHocPhanTKhoaHoc");
				panel.updateUI();
			}
		});
		btnHocPhanTrongKhoaHoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHocPhanTrongKhoaHoc.setBounds(541, 11, 161, 49);
		getContentPane().add(btnHocPhanTrongKhoaHoc);
		
		JButton btnKhoaHoc = new JButton("KhoaHoc");
		btnKhoaHoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(panel);
				panel=new KhoaHocPanel(cacheData);
				panel.setBounds(20, 71, 950, 459);
				getContentPane().add(panel);
				lblTitle.setText("Quan Ly Khoa Hoc");
				panel.updateUI();
			}
		});
		btnKhoaHoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnKhoaHoc.setBounds(325, 11, 98, 49);
		getContentPane().add(btnKhoaHoc);
		
		JButton btnNganh = new JButton("Nganh");
		btnNganh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove(panel);
				panel=new NganhPanel(cacheData);
				panel.setBounds(20, 71, 950, 459);
				getContentPane().add(panel);
				lblTitle.setText("Quan Ly Nganh");
				panel.updateUI();
			}
		});
		btnNganh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNganh.setBounds(104, 11, 74, 49);
		getContentPane().add(btnNganh);
		
	
		
	}
}
