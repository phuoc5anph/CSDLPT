package com.phuoc.userinterface;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import com.phuoc.Sever.DataReq;
import com.phuoc.Sever.DataResp;
import com.phuoc.Sever.Req;
import com.phuoc.data.CacheData;
import com.phuoc.models.ImportHocPhansByExcel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class ImportExcelHocPhansPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldLinkFileExcel;
	private CacheData cacheData;
	private JProgressBar progressBar;
	private JTextArea textArea;
	private JLabel lblTienDo;

	/**
	 * Create the panel.
	 */
	public ImportExcelHocPhansPanel(CacheData cacheData) {
		this.cacheData = cacheData;
		setLayout(null);
		
		progressBar = new JProgressBar(0 , 100);
		progressBar.setBounds(30, 62, 513, 30);
		add(progressBar);
		
		textArea = new JTextArea();
		textArea.setBounds(30, 147, 513, 181);
		add(textArea);
		
		lblTienDo = new JLabel("");
		lblTienDo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTienDo.setBounds(447, 97, 96, 14);
		add(lblTienDo);
		
		JLabel lblThongBao = new JLabel("Thông Báo:");
		lblThongBao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblThongBao.setBounds(30, 103, 117, 29);
		add(lblThongBao);
		
		textFieldLinkFileExcel = new JTextField();
		textFieldLinkFileExcel.setBounds(30, 11, 402, 30);
		add(textFieldLinkFileExcel);
		textFieldLinkFileExcel.setColumns(10);
		
		JButton btnImportExcel = new JButton("ImportExcel");
		btnImportExcel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ImportHocPhansByExcel  ip =  new ImportHocPhansByExcel(textFieldLinkFileExcel.getText(), progressBar,textArea, lblTienDo, cacheData);
			}
		});
		btnImportExcel.setBounds(442, 13, 101, 26);
		add(btnImportExcel);

	}

	public JTextField getTextFieldLinkFileExcel() {
		return textFieldLinkFileExcel;
	}

	public void setTextFieldLinkFileExcel(JTextField textFieldLinkFileExcel) {
		this.textFieldLinkFileExcel = textFieldLinkFileExcel;
	}

	public CacheData getCacheData() {
		return cacheData;
	}

	public void setCacheData(CacheData cacheData) {
		this.cacheData = cacheData;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JLabel getLblTienDo() {
		return lblTienDo;
	}

	public void setLblTienDo(JLabel lblTienDo) {
		this.lblTienDo = lblTienDo;
	}
	
	
}
