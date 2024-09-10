package com.phuoc.Sever;

import java.io.Serializable;

public class DataResp implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int Success = 0;
	public static final int Fail = 1;
	
	private int state;
	
	
	private Object object;
    private String data;
    
    
    
    
	public DataResp(int state, Object object, String data) {
		this.state = state;
		this.object = object;
		this.data = data;
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
	
    public static final String stateToString(int state) {
		switch(state) {
		case 0: return "Success"; 
		case 1: return "Fail"; 
		
		default:
			break;}
		return "Lenh La";
	}
    
    
	

}
