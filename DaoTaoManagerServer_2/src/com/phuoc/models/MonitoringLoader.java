package com.phuoc.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MonitoringLoader {
	
	private ArrayList<MonitoringModel> monitoringModels;
	public MonitoringLoader(ArrayList<MonitoringModel> monitoringModels) {
		this.monitoringModels = monitoringModels;
	}
	
	public void serializeData() {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {

			fout = new FileOutputStream(ThongTinChiNhanh.URLMonitoringSystemDataSave);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(this.monitoringModels);
			System.out.println("Xong!");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void readData() {
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		try {

			File file1 = new File(ThongTinChiNhanh.URLMonitoringSystemDataSave);
			
			if(file1.length()==0) {
				this.monitoringModels = new	ArrayList<MonitoringModel>();	
				
			}else {
			fin = new FileInputStream(ThongTinChiNhanh.URLMonitoringSystemDataSave);
			ois = new ObjectInputStream(fin);
			this.monitoringModels = (ArrayList<MonitoringModel>)ois.readObject();
			System.out.println("Xong!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ArrayList<MonitoringModel> getMonitoringModels() {
		return monitoringModels;
	}

	public void setMonitoringModels(ArrayList<MonitoringModel> monitoringModels) {
		this.monitoringModels = monitoringModels;
	}
	
	



}
