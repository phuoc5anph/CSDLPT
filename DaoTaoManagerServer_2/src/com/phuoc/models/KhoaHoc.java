package com.phuoc.models;

import java.io.Serializable;

/**
 *
 * @author Hide in home
 */
public class KhoaHoc implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String KhoaHocID, TenKhoaHoc, KhoaID, ChuyenNganhID, NamHoc;
	public KhoaHoc(String khoaHocID, String tenKhoaHoc, String khoaID, String chuyenNganhID, String namHoc) {
		KhoaHocID = khoaHocID;
		TenKhoaHoc = tenKhoaHoc;
		KhoaID = khoaID;
		ChuyenNganhID = chuyenNganhID;
		NamHoc = namHoc;
	}
	public String getKhoaHocID() {
		return KhoaHocID;
	}
	public void setKhoaHocID(String khoaHocID) {
		KhoaHocID = khoaHocID;
	}
	public String getTenKhoaHoc() {
		return TenKhoaHoc;
	}
	public void setTenKhoaHoc(String tenKhoaHoc) {
		TenKhoaHoc = tenKhoaHoc;
	}
	public String getKhoaID() {
		return KhoaID;
	}
	public void setKhoaID(String khoaID) {
		KhoaID = khoaID;
	}
	public String getChuyenNganhID() {
		return ChuyenNganhID;
	}
	public void setChuyenNganhID(String chuyenNganhID) {
		ChuyenNganhID = chuyenNganhID;
	}
	public String getNamHoc() {
		return NamHoc;
	}
	public void setNamHoc(String namHoc) {
		NamHoc = namHoc;
	}
	@Override
	public String toString() {
		return "KhoaHoc [KhoaHocID=" + KhoaHocID + ", TenKhoaHoc=" + TenKhoaHoc + ", KhoaID=" + KhoaID
				+ ", ChuyenNganhID=" + ChuyenNganhID + ", NamHoc=" + NamHoc + "]";
	}

	
}
