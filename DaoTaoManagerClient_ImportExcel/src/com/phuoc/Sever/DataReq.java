package com.phuoc.Sever;

import java.io.Serializable;

public class DataReq  implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int GetKhoas = 0;
	public static final int GetHocPhans = 1;
	public static final int GetNganhs = 2;
	public static final int GetChuyenNganhs = 3;	
	public static final int GetKhoaHocs = 4;
	public static final int GetHocPhanTrongKhoaHocs = 5;
	public static final int UpdateKhoa = 6;
    public static final int UpdateHocPhan = 7;
    public static final int UpdateNganh = 8;
    public static final int UpdateChuyenNganh = 9;
    public static final int UpdateKhoaHoc = 10;
    public static final int UpdateHocPhanTrongKhoaHoc = 11;
    public static final int InsertKhoa = 12;
    public static final int InsertHocPhan = 13;
    public static final int InsertNganh = 14;
    public static final int InsertChuyenNganh = 15;
    public static final int InsertKhoaHoc = 16;
    public static final int InsertHocPhanTrongKhoaHoc= 17;
    public static final int DeleteKhoa = 18;
    public static final int DeleteHocPhan = 19;
    public static final int DeleteNganh = 20;
    public static final int DeleteChuyenNganh = 21;
    public static final int DeleteKhoaHoc = 22;
    public static final int DeleteHocPhanTrongKhoaHoc= 23;
    public static final int SearchKhoa = 24;
    public static final int SearchHocPhan = 25;
    public static final int SearchNganh = 26;
    public static final int SearchChuyenNganh = 27;
    public static final int SearchKhoaHoc = 28;
    public static final int SearchHocPhanTrongKhoaHoc= 29;
    public static final int Login = 30;
    public static final int GetChiNhanh = 31;
    
    private String username;
    
    private int state;
    
    private Object object;
    
    private String data;   
    
    private int port; 

	public DataReq(int state, Object object, String data, String username, int port) {
		this.username = username;
		this.state = state;
		this.object = object;
		this.data = data;
		this.port = port;
	}

	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}



	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	

	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}



	public static final String stateToString(int state) {
		switch(state) {
		case 0: return "GetKhoas"; 
		case 1: return "GetHocPhans"; 
		case 2: return "GetNganhs"; 
		case 3: return "GetChuyenNganhs"; 
		case 4: return "GetKhoaHocs";
		case 5: return "GetHocPhanTrongKhoaHocs";
		case 6: return "UpdateKhoa"; 
		case 7: return "UpdateHocPhan"; 
		case 8: return "UpdateNganh";
		case 9: return "UpdateChuyenNganh"; 
		case 10: return "UpdateKhoaHoc";
		case 11: return "UpdateHocPhanTrongKhoaHoc"; 
		case 12: return "InsertKhoa"; 
		case 13: return "InsertHocPhan"; 
		case 14: return "InsertNganh";
		case 15: return "InsertChuyenNganh"; 
		case 16: return "InsertKhoaHoc";
		case 17: return "InsertHocPhanTrongKhoaHoc"; 
		case 18: return "DeleteKhoa"; 
		case 19: return "DeleteHocPhan";
		case 20: return "DeleteNganh"; 
		case 21: return "DeleteChuyenNganh"; 
		case 22: return "DeleteKhoaHoc";
		case 23: return "DeleteHocPhanTrongKhoaHoc"; 
		case 24: return "SearchKhoa"; 
		case 25: return "SearchHocPhan"; 
		case 26: return "SearchNganh"; 
		case 27: return "SearchChuyenNganh"; 
		case 28: return "SearchKhoaHoc";
		case 29: return "SearchHocPhanTrongKhoaHoc"; 
		case 30: return "Login";
		case 31: return "GetChiNhanh"; 

		default:
			break;}
		return "Lenh La";
	}
    

   
    
    

}
