package com.phuoc.models;

import java.io.Serializable;

/**
 *
 * @author Hide in home
 */
public class Nganh implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String NganhID, TenNganh, Mota, KhoaID;
	public Nganh(String nganhID, String tenNganh, String mota, String khoaID) {
		NganhID = nganhID;
		TenNganh = tenNganh;
		Mota = mota;
		KhoaID = khoaID;
	}
	public String getNganhID() {
		return NganhID;
	}
	public void setNganhID(String nganhID) {
		NganhID = nganhID;
	}
	public String getTenNganh() {
		return TenNganh;
	}
	public void setTenNganh(String tenNganh) {
		TenNganh = tenNganh;
	}
	public String getMota() {
		return Mota;
	}
	public void setMota(String mota) {
		Mota = mota;
	}
	public String getKhoaID() {
		return KhoaID;
	}
	public void setKhoaID(String khoaID) {
		KhoaID = khoaID;
	}
	@Override
	public String toString() {
		return "Nganh [NganhID=" + NganhID + ", TenNganh=" + TenNganh + ", Mota=" + Mota + ", KhoaID=" + KhoaID + "]";
	}
	
}
