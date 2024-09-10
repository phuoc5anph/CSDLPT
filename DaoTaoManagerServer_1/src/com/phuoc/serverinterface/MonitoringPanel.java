package com.phuoc.serverinterface;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.phuoc.models.MonitoringLoader;
import com.phuoc.models.MonitoringModel;
import com.phuoc.models.Monitoring_TableModel;



public class MonitoringPanel extends JPanel{
	
	
	private JTable table;
	private ArrayList<MonitoringModel> monitoringModels;
	private Monitoring_TableModel model;
	private MonitoringLoader monitoringLoader;
	public MonitoringPanel() {			

		
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 80, 814, 301);
		add(scrollPane);
		
		monitoringLoader =  new MonitoringLoader(monitoringModels);
		monitoringLoader.readData();
		if(monitoringLoader.getMonitoringModels()==null) {
		      monitoringModels = new ArrayList<>();
		}else {
			monitoringModels = monitoringLoader.getMonitoringModels();
		}
		model = new Monitoring_TableModel(monitoringModels);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
	}
	public ArrayList<MonitoringModel> getMonitoringModels() {
		return monitoringModels;
	}
	public void setMonitoringModels(ArrayList<MonitoringModel> monitoringModels) {
		this.monitoringModels = monitoringModels;
	}
	public Monitoring_TableModel getModel() {
		return model;
	}
	public void setModel(Monitoring_TableModel model) {
		this.model = model;
	}
	public MonitoringLoader getMonitoringLoader() {
		return monitoringLoader;
	}
	public void setMonitoringLoader(MonitoringLoader monitoringLoader) {
		this.monitoringLoader = monitoringLoader;
	}
	
	
	

	
}
