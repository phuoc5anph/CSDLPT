package com.phuoc.models;

import java.io.Serializable;
import com.phuoc.Sever.DataReq;
import com.phuoc.Sever.DataResp;



/**
*
* @author Hide in home
*/
public class MonitoringModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ipAddress;
	private DataReq dataReq;
	private DataResp dataResp;
	private String dateTime;
	
	
	
	public MonitoringModel(String ipAddress, DataReq dataReq, DataResp dataResp, String dateTime) {
		this.ipAddress = ipAddress;
		this.dataReq = dataReq;
		this.dataResp = dataResp;
		this.dateTime = dateTime;
	}
	public DataReq getDataReq() {
		return dataReq;
	}
	public void setDataReq(DataReq dataReq) {
		this.dataReq = dataReq;
	}
	public DataResp getDataResp() {
		return dataResp;
	}
	public void setDataResp(DataResp dataResp) {
		this.dataResp = dataResp;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
		
}
