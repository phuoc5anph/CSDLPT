package com.phuoc.data;

import com.phuoc.models.ChiNhanh;
import com.phuoc.models.UserAccount;

public class CacheData {

	private String[] MaCNs= { "1","2"};
	private int[] Ports = {7001, 7002};
    private String[] ThongTinCNs = { "Van Phuoc 1","Van Phuoc 2"};;
	private String MaCN;
	private int Post;
	private String ThongTinCn;
	private UserAccount useraccount;
	private ChiNhanh chinhanh;
 
    
	public CacheData() {

	}
	public String[] getMaCNs() {
		return MaCNs;
	}
	public void setMaCNs(String[] maCNs) {
		MaCNs = maCNs;
	}
	public int[] getPorts() {
		return Ports;
	}
	public void setPorts(int[] ports) {
		Ports = ports;
	}
	public String[] getThongTinCNs() {
		return ThongTinCNs;
	}
	public void setThongTinCNs(String[] thongTinCNs) {
		ThongTinCNs = thongTinCNs;
	}
	public String getMaCN() {
		return MaCN;
	}
	public void setMaCN(String maCN) {
		MaCN = maCN;
	}
	public int getPost() {
		return Post;
	}
	public void setPost(int post) {
		Post = post;
	}
	public String getThongTinCn() {
		return ThongTinCn;
	}
	public void setThongTinCn(String thongTinCn) {
		ThongTinCn = thongTinCn;
	}
	public UserAccount getUseraccount() {
		return useraccount;
	}
	public void setUseraccount(UserAccount useraccount) {
		this.useraccount = useraccount;
	}
	public ChiNhanh getChinhanh() {
		return chinhanh;
	}
	public void setChinhanh(ChiNhanh chinhanh) {
		this.chinhanh = chinhanh;
	}
	
	
	
	
	

}
