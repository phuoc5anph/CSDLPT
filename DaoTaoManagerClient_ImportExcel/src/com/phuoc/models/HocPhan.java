package com.phuoc.models;

import java.io.Serializable;

/**
 *
 * @author Hide in home
 */
public class HocPhan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String HocPhanID, TenHocPhan, MaHocPhan, Mota, KhoaID, LoaiHocPhan, SoTinChi;


	
	public HocPhan(String hocPhanID, String tenHocPhan, String maHocPhan, String mota, String khoaID,
			String loaiHocPhan, String soTinChi) {
		HocPhanID = hocPhanID;
		TenHocPhan = tenHocPhan;
		MaHocPhan = maHocPhan;
		Mota = mota;
		KhoaID = khoaID;
		LoaiHocPhan = loaiHocPhan;
		SoTinChi = soTinChi;
	}



	public String getHocPhanID() {
		return HocPhanID;
	}



	public void setHocPhanID(String hocPhanID) {
		HocPhanID = hocPhanID;
	}



	public String getTenHocPhan() {
		return TenHocPhan;
	}



	public void setTenHocPhan(String tenHocPhan) {
		TenHocPhan = tenHocPhan;
	}



	public String getMaHocPhan() {
		return MaHocPhan;
	}



	public void setMaHocPhan(String maHocPhan) {
		MaHocPhan = maHocPhan;
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



	public String getLoaiHocPhan() {
		return LoaiHocPhan;
	}



	public void setLoaiHocPhan(String loaiHocPhan) {
		LoaiHocPhan = loaiHocPhan;
	}



	public String getSoTinChi() {
		return SoTinChi;
	}



	public void setSoTinChi(String soTinChi) {
		SoTinChi = soTinChi;
	}



	@Override
	public String toString() {
		return "HocPhan [HocPhanID=" + HocPhanID + ", TenHocPhan=" + TenHocPhan + ", MaHocPhan=" + MaHocPhan + ", Mota="
				+ Mota + ", KhoaID=" + KhoaID + ", LoaiHocPhan=" + LoaiHocPhan + ", SoTinChi=" + SoTinChi + "]";
	}
	
	
}

