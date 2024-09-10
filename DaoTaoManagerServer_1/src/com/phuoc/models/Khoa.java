package com.phuoc.models;

import java.io.Serializable;

/**
 *
 * @author Hide in home
 */
public class Khoa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String KhoaID, TenKhoa, Mota;

	public Khoa(String khoaID, String tenKhoa, String mota) {
		KhoaID = khoaID;
		TenKhoa = tenKhoa;
		Mota = mota;
	}
	
	public String getKhoaID() {
		return KhoaID;
	}

	public void setKhoaID(String khoaID) {
		KhoaID = khoaID;
	}

	public String getTenKhoa() {
		return TenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		TenKhoa = tenKhoa;
	}

	public String getMota() {
		return Mota;
	}

	public void setMota(String mota) {
		Mota = mota;
	}

	@Override
	public String toString() {
		return "Khoa [KhoaID=" + KhoaID + ", TenKhoa=" + TenKhoa + ", Mota=" + Mota + "]";
	}
	
}
