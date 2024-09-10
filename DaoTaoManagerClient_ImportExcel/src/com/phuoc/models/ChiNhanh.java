package com.phuoc.models;

import java.io.Serializable;
import java.sql.Date;

public class ChiNhanh implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String MaCN;
	private String TenCN;
	private String DiaChi;
	private String SDT;
	
	public ChiNhanh(String MaCN, String TenCN, String DiaChi, String SDT) {
		this.MaCN = MaCN;
		this.TenCN = TenCN;
		this.DiaChi = DiaChi;
		this.SDT = SDT;
	}

	public String getMaCN() {
		return MaCN;
	}

	public void setMaCN(String maCN) {
		MaCN = maCN;
	}

	public String getTenCN() {
		return TenCN;
	}

	public void setTenCN(String tenCN) {
		TenCN = tenCN;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}
	
	

	
	

	
	
	

}

