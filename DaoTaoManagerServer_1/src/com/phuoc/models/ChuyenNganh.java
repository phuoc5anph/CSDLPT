package com.phuoc.models;

import java.io.Serializable;

/**
 *
 * @author Hide in home
 */
public class ChuyenNganh implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ChuyenNganhID, TenChuyenNganh, Mota, NganhID;
	

	public ChuyenNganh(String chuyenNganhID, String tenChuyenNganh, String mota, String nganhID) {
		ChuyenNganhID = chuyenNganhID;
		TenChuyenNganh = tenChuyenNganh;
		Mota = mota;
		NganhID = nganhID;
	}
	public String getChuyenNganhID() {
		return ChuyenNganhID;
	}
	public void setChuyenNganhID(String chuyenNganhID) {
		ChuyenNganhID = chuyenNganhID;
	}
	public String getTenChuyenNganh() {
		return TenChuyenNganh;
	}
	public void setTenChuyenNganh(String tenChuyenNganh) {
		TenChuyenNganh = tenChuyenNganh;
	}
	public String getMota() {
		return Mota;
	}
	public void setMota(String mota) {
		Mota = mota;
	}
	public String getNganhID() {
		return NganhID;
	}
	public void setNganhID(String nganhID) {
		NganhID = nganhID;
	}
	@Override
	public String toString() {
		return "ChuyenNganh [ChuyenNganhID=" + ChuyenNganhID + ", TenChuyenNganh=" + TenChuyenNganh + ", Mota=" + Mota
				+ "]";
	}
	
	
}
