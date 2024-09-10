package com.phuoc.Sever;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.imageio.spi.ImageOutputStreamSpi;
import javax.swing.JOptionPane;

import com.phuoc.userinterface.AppFrame;

import java.io.*;

public class Req {
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private DataResp dataResp;
	
	public Req(DataReq dataReq){
		
				
		try {
			System.out.println("ConnectThanhCong");
			socket = new Socket("localhost", dataReq.getPort());
			System.out.println("ConnectThanhCong");
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			System.out.println("ConnectThanhCong");
			oos.writeObject(dataReq);
			dataResp=(DataResp)ois.readObject();
			System.out.println("ConnectThanhCong1");
			socket.close();
						
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
		} 
        
	}
	
	
	
		
		
	public DataResp getDataResp() {
		return dataResp;
	}

	public void setDataResp(DataResp dataResp) {
		this.dataResp = dataResp;
	}	
		
			
}
