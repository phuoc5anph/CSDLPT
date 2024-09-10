package com.phuoc.models;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import com.phuoc.Sever.DataReq;
import com.phuoc.Sever.DataResp;
import com.phuoc.Sever.Req;
import com.phuoc.data.CacheData;
import com.phuoc.userinterface.ImportExcelHocPhansPanel;

public class ImportHocPhansByExcel {
	private  ReadExcelHocPhans rhps;
	private CacheData cacheData;
	private ArrayList<Integer> stateList;
	public ImportHocPhansByExcel(String excelFilePath,JProgressBar progressBar,JTextArea textArea, JLabel lblTienDo, CacheData cacheData) {
		this.rhps =  new ReadExcelHocPhans(excelFilePath);
		this.cacheData = cacheData;
		this.stateList = new ArrayList<>();
		for (HocPhan hp : rhps.getHps()) {
			System.out.println(hp.toString());
			Req req = new Req(new DataReq(DataReq.InsertHocPhan, hp, null,cacheData.getUseraccount().getUsername(), cacheData.getPost()));
			stateList.add(req.getDataResp().getState());
			progressBar.setValue((int)(100*this.stateList.size()/this.rhps.getHps().size()));
			textArea.append(DataResp.stateToString(req.getDataResp().getState())+req.getDataResp().getData()+"\n");
			lblTienDo.setText(this.stateList.size()+"/"+this.rhps.getHps().size());
		}
		System.out.println(stateList.toString());
	}
	public ReadExcelHocPhans getRhps() {
		return rhps;
	}
	public void setRhps(ReadExcelHocPhans rhps) {
		this.rhps = rhps;
	}
	public ArrayList<Integer> getStateList() {
		return stateList;
	}
	public void setStateList(ArrayList<Integer> stateList) {
		this.stateList = stateList;
	}
	
	
}
