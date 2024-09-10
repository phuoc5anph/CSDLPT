package com.phuoc.models;

import java.io.Serializable;

/**
 *
 * @author Hide in home
 */
public class HocPhanTrongKhoaHoc implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String HocPhanTrongKhoaHocID, KhoaHocID, HocPhanID, HocKy;
	public HocPhanTrongKhoaHoc(String hocPhanTrongKhoaHocID, String khoaHocID, String hocPhanID, String hocKy) {
		HocPhanTrongKhoaHocID = hocPhanTrongKhoaHocID;
		KhoaHocID = khoaHocID;
		HocPhanID = hocPhanID;
		HocKy = hocKy;
	}
	public String getHocPhanTrongKhoaHocID() {
		return HocPhanTrongKhoaHocID;
	}
	public void setHocPhanTrongKhoaHocID(String hocPhanTrongKhoaHocID) {
		HocPhanTrongKhoaHocID = hocPhanTrongKhoaHocID;
	}
	public String getKhoaHocID() {
		return KhoaHocID;
	}
	public void setKhoaHocID(String khoaHocID) {
		KhoaHocID = khoaHocID;
	}
	public String getHocPhanID() {
		return HocPhanID;
	}
	public void setHocPhanID(String hocPhanID) {
		HocPhanID = hocPhanID;
	}
	public String getHocKy() {
		return HocKy;
	}
	public void setHocKy(String hocKy) {
		HocKy = hocKy;
	}
	@Override
	public String toString() {
		return "HocPhanTrongKhoaHoc [HocPhanTrongKhoaHocID=" + HocPhanTrongKhoaHocID + ", KhoaHocID=" + KhoaHocID
				+ ", HocPhanID=" + HocPhanID + ", HocKy=" + HocKy + "]";
	}
	
}
