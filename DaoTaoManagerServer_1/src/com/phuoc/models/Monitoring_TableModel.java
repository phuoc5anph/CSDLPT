package com.phuoc.models;

import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import com.phuoc.Sever.DataReq;
import com.phuoc.Sever.DataResp;

public class Monitoring_TableModel extends AbstractTableModel{
	
	ArrayList<MonitoringModel> monitoringModels;

	
	private final String[] colunNames = { "IpAddress", "DataReqState", "DataReqParameter" ,"DataRespState", "DateTime" };
	private final Class[] mClasss = { String.class, String.class, String.class, String.class, String.class };
	
	public Monitoring_TableModel(ArrayList<MonitoringModel> monitoringModels) {
		this.monitoringModels = monitoringModels;	
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if(monitoringModels==null)return 0;
        else return monitoringModels.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		MonitoringModel mm = monitoringModels.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return mm.getIpAddress()+mm.getDataReq().getUsername();
		case 1:
			return DataReq.stateToString(mm.getDataReq().getState());
		case 2:
			return mm.getDataResp().getData();
		case 3:
			return DataResp.stateToString(mm.getDataResp().getState());
		case 4:
			return mm.getDateTime();

		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colunNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		return mClasss[columnIndex];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		if (columnIndex == 0) {
			return false;
		} else
			return true;
	}
	
	@Override
	public void fireTableDataChanged() {
		// TODO Auto-generated method stub
		super.fireTableDataChanged();
	}

	@Override
	public void fireTableStructureChanged() {
		// TODO Auto-generated method stub
		super.fireTableStructureChanged();
	}

	@Override
	public void fireTableRowsInserted(int firstRow, int lastRow) {
		// TODO Auto-generated method stub
		super.fireTableRowsInserted(firstRow, lastRow);
	}

	@Override
	public void fireTableRowsUpdated(int firstRow, int lastRow) {
		// TODO Auto-generated method stub
		super.fireTableRowsUpdated(firstRow, lastRow);
	}

	@Override
	public void fireTableRowsDeleted(int firstRow, int lastRow) {
		// TODO Auto-generated method stub
		super.fireTableRowsDeleted(firstRow, lastRow);
	}

	@Override
	public void fireTableCellUpdated(int row, int column) {
		// TODO Auto-generated method stub
		super.fireTableCellUpdated(row, column);
	}

	@Override
	public void fireTableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		super.fireTableChanged(e);
	}

	@Override
	public <T extends EventListener> T[] getListeners(Class<T> listenerType) {
		// TODO Auto-generated method stub
		return super.getListeners(listenerType);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	

	
}
