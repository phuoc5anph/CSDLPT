package com.phuoc.Sever;

import java.awt.EventQueue;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.phuoc.MySQL.ConnectSQL;
import com.phuoc.models.ChuyenNganh;
import com.phuoc.models.HocPhan;
import com.phuoc.models.HocPhanTrongKhoaHoc;
import com.phuoc.models.Khoa;
import com.phuoc.models.KhoaHoc;
import com.phuoc.models.MonitoringModel;
import com.phuoc.models.Nganh;
import com.phuoc.models.ThongTinChiNhanh;
import com.phuoc.models.UserAccount;
import com.phuoc.serverinterface.MonitoringFrame;

public class SeverSocket {

	private ServerSocket server;
	private MonitoringFrame frame;

	public static void main(String[] args) {
		new SeverSocket();
	}

	public SeverSocket() {
		
		

		try {
			server = new ServerSocket(ThongTinChiNhanh.Port);
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						frame = new MonitoringFrame();		
						frame.setVisible(true);
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			while (true) {
				System.out.println("S_Ok");
				ThreadUpdate tu = new ThreadUpdate(server.accept(), frame);
				System.out.println("S_Ok");
				tu.start();
				System.out.println("S_Ok_");

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "1" + e);
		}

	}
}

class ThreadUpdate extends Thread {

	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket socket;
	private MonitoringFrame frame;

	public ThreadUpdate(Socket socket, MonitoringFrame frame) {

		
		System.out.println(""+socket.getRemoteSocketAddress().toString());
		try {

			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			this.socket = socket;
			this.frame = frame;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void run() {
		System.out.println("S_OkRun");
		DataReq data;
		try {
			System.out.println("S_OkRun");
			data = (DataReq) ois.readObject();
			System.out.println("S_OkRun");
			synchronized (this) {
				Update(data, frame);
			}

			System.out.println("S_OkRun");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void Update(DataReq data, MonitoringFrame frame) throws IOException, SQLException {

		ConnectSQL cn = new ConnectSQL();
        DataResp dataResp = null;
        
		
		switch (data.getState()) {
		case DataReq.GetKhoas:
			try {
				ArrayList<Khoa> ks = new ArrayList<Khoa>(cn.GetKhoas());
				dataResp = new DataResp(DataResp.Success, ks, null);
				oos.writeObject(dataResp);			
			} catch (Exception e) {
				dataResp = new DataResp(DataResp.Fail, null, null);
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}
			break;
		case DataReq.GetHocPhans:
			try {
				ArrayList<HocPhan> hps = new ArrayList<HocPhan>(cn.GetHocPhans());
				dataResp = new DataResp(DataResp.Success, hps, null);
				oos.writeObject(dataResp);
			} catch (Exception e) {
				dataResp = new DataResp(DataResp.Fail, null, null);
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}
			break;
			
		case DataReq.GetNganhs:
			try {
				ArrayList<Nganh> ns = new ArrayList<Nganh>(cn.GetNganhs());
				dataResp = new DataResp(DataResp.Success, ns, null);
				oos.writeObject(dataResp);
			} catch (Exception e) {
				dataResp = new DataResp(DataResp.Fail, null, null);
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.GetChuyenNganhs:
			try {
				ArrayList<ChuyenNganh> cns = new ArrayList<ChuyenNganh>(cn.GetChuyenNganhs());
				dataResp = new DataResp(DataResp.Success, cns, null);
				oos.writeObject(dataResp);
			} catch (Exception e) {
				dataResp = new DataResp(DataResp.Fail, null, null);
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.GetKhoaHocs:
			try {
				ArrayList<KhoaHoc> khs = new ArrayList<KhoaHoc>(cn.GetKhoaHocs());
				dataResp = new DataResp(DataResp.Success, khs, null);
				oos.writeObject(dataResp);
			} catch (Exception e) {
				dataResp = new DataResp(DataResp.Fail, null, null);
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.GetHocPhanTrongKhoaHocs:
			try {
				ArrayList<HocPhanTrongKhoaHoc> khthps = new ArrayList<HocPhanTrongKhoaHoc>(cn.GetHocPhanTrongKhoaHocs());
				dataResp = new DataResp(DataResp.Success, khthps, null);
				oos.writeObject(dataResp);
			} catch (Exception e) {
				dataResp = new DataResp(DataResp.Fail, null, null);
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.UpdateKhoa:

			try {

				if (cn.UpdateKhoa((Khoa) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, ((Khoa) data.getObject()).toString());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, ((Khoa) data.getObject()).toString());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, ((Khoa) data.getObject()).toString());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.UpdateHocPhan:
			try {

				if (cn.UpdateHocPhan((HocPhan) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, ((HocPhan) data.getObject()).toString());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, ((HocPhan) data.getObject()).toString());
					oos.writeObject(dataResp);
				}

			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, ((HocPhan) data.getObject()).toString());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.UpdateNganh:
			try {

				if (cn.UpdateNganh((Nganh) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null,((Nganh) data.getObject()).toString());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, ((Nganh) data.getObject()).toString());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, ((Nganh) data.getObject()).toString());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.UpdateChuyenNganh:
			try {

				if (cn.UpdateChuyenNganh((ChuyenNganh) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null,((ChuyenNganh) data.getObject()).toString());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, ((ChuyenNganh) data.getObject()).toString());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, ((ChuyenNganh) data.getObject()).toString());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.UpdateKhoaHoc:
			try {

				if (cn.UpdateKhoaHoc((KhoaHoc) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null,((KhoaHoc) data.getObject()).toString());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, ((KhoaHoc) data.getObject()).toString());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, ((KhoaHoc) data.getObject()).toString());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
			
		case DataReq.UpdateHocPhanTrongKhoaHoc:
			try {

				if (cn.UpdateHocPhanTrongKhoaHoc((HocPhanTrongKhoaHoc) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null,((HocPhanTrongKhoaHoc) data.getObject()).toString());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, ((HocPhanTrongKhoaHoc) data.getObject()).toString());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, ((HocPhanTrongKhoaHoc) data.getObject()).toString());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.InsertKhoa:

			try {

				if (cn.InsertKhoa((Khoa) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, ((Khoa) data.getObject()).toString());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, ((Khoa) data.getObject()).toString());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, ((Khoa) data.getObject()).toString());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.InsertHocPhan:

			try {

				if (cn.InsertHocPhan((HocPhan) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, ((HocPhan) data.getObject()).toString());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, ((HocPhan) data.getObject()).toString());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, ((HocPhan) data.getObject()).toString());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
			
		case DataReq.InsertNganh:
			try {

				if (cn.InsertNganh((Nganh) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, ((Nganh) data.getObject()).toString());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, ((Nganh) data.getObject()).toString());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, ((Nganh) data.getObject()).toString());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.InsertChuyenNganh:
			try {

				if (cn.InsertChuyenNganh((ChuyenNganh) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, ((ChuyenNganh) data.getObject()).toString());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, ((ChuyenNganh) data.getObject()).toString());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, ((ChuyenNganh) data.getObject()).toString());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.InsertKhoaHoc:
			try {

				if (cn.InsertKhoaHoc((KhoaHoc) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, ((KhoaHoc) data.getObject()).toString());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, ((KhoaHoc) data.getObject()).toString());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, ((KhoaHoc) data.getObject()).toString());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.InsertHocPhanTrongKhoaHoc:
			try {

				if (cn.InsertHocPhanTrongKhoaHoc((HocPhanTrongKhoaHoc) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, ((HocPhanTrongKhoaHoc) data.getObject()).toString());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, ((HocPhanTrongKhoaHoc) data.getObject()).toString());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, ((HocPhanTrongKhoaHoc) data.getObject()).toString());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.DeleteKhoa:

			try {

				if (cn.DeleteKhoa((String) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, (String) data.getObject());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.DeleteHocPhan:
			try {

				if (cn.DeleteHocPhan((String) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, (String) data.getObject());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.DeleteNganh:
			try {

				if (cn.DeleteNganh((String) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, (String) data.getObject());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.DeleteChuyenNganh:
			try {

				if (cn.DeleteChuyenNganh((String) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, (String) data.getObject());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.DeleteKhoaHoc:
			try {

				if (cn.DeleteKhoaHoc((String) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, (String) data.getObject());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.DeleteHocPhanTrongKhoaHoc:
			try {

				if (cn.DeleteHocPhanTrongKhoaHoc((String) data.getObject()) != 0){
					dataResp = new DataResp(DataResp.Success, null, (String) data.getObject());
					oos.writeObject(dataResp);}
				else {
					dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
					oos.writeObject(dataResp);
				}
			} catch (Exception e) {

				dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.SearchKhoa:
			try {
				ArrayList<Khoa> ks = new ArrayList<Khoa>(cn.SearchKhoa((String) data.getObject()));
				dataResp = new DataResp(DataResp.Success, ks, (String) data.getObject());
				oos.writeObject(dataResp);
			} catch (Exception e) {
				dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}
			break;
		case DataReq.SearchHocPhan:
			try {
				ArrayList<HocPhan> hps = new ArrayList<HocPhan>(cn.SearchHocPhan((String) data.getObject()));
				dataResp = new DataResp(DataResp.Success, hps, (String) data.getObject());
				oos.writeObject(dataResp);
			} catch (Exception e) {
				dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}
			break;
		case DataReq.SearchChuyenNganh:
			try {
				ArrayList<ChuyenNganh> cns = new ArrayList<ChuyenNganh>(cn.SearchChuyenNganh((String) data.getObject()));
	            dataResp = new DataResp(DataResp.Success, cns, (String) data.getObject());
				oos.writeObject(dataResp);
			} catch (IOException e) {
				dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.SearchNganh:
			try {
				ArrayList<Nganh> ns = new ArrayList<Nganh>(cn.SearchNganh((String) data.getObject()));
	            dataResp = new DataResp(DataResp.Success, ns, (String) data.getObject());
				oos.writeObject(dataResp);
			} catch (IOException e) {
				dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.SearchHocPhanTrongKhoaHoc:
			try {
				ArrayList<HocPhanTrongKhoaHoc> hptkhs = new ArrayList<HocPhanTrongKhoaHoc>(cn.SearchHocPhanTrongKhoaHoc((String) data.getObject()));
	            dataResp = new DataResp(DataResp.Success, hptkhs, (String) data.getObject());
				oos.writeObject(dataResp);
			} catch (IOException e) {
				dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;
		case DataReq.SearchKhoaHoc:
			try {
				ArrayList<KhoaHoc> khs = new ArrayList<KhoaHoc>(cn.SearchKhoaHoc((String) data.getObject()));
	            dataResp = new DataResp(DataResp.Success, khs, (String) data.getObject());
				oos.writeObject(dataResp);
			} catch (IOException e) {
				dataResp = new DataResp(DataResp.Fail, null, (String) data.getObject());
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;


		case DataReq.Login:
			try {
				int UAID = cn.GetUserAccountIDByUserAccount((UserAccount)data.getObject());
	            if(UAID > 0) {
				dataResp = new DataResp(DataResp.Success, UAID, ((UserAccount)data.getObject()).toString());
				oos.writeObject(dataResp);}
	            else {
	            	dataResp = new DataResp(DataResp.Fail, null, null);
					oos.writeObject(dataResp);
	            	
	            }
			} catch (IOException | SQLException e) {
				dataResp = new DataResp(DataResp.Fail, null, null);
				oos.writeObject(dataResp);
				JOptionPane.showMessageDialog(null, e);
			}

			break;

		default:
			break;
		}
		
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();   
		frame.getMonitoringPanel().getMonitoringModels().add(new MonitoringModel(socket.getRemoteSocketAddress().toString(), data, dataResp, dtf.format(now)));
		frame.getMonitoringPanel().getModel().fireTableDataChanged();
		frame.getMonitoringPanel().getMonitoringLoader().serializeData();
		
	}
}
