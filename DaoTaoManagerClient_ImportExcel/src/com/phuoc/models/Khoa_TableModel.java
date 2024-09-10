package com.phuoc.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import com.phuoc.Sever.DataReq;
import com.phuoc.Sever.Req;
import com.phuoc.data.CacheData;
import com.phuoc.userinterface.AppFrame;




public class Khoa_TableModel extends AbstractTableModel {

	private ArrayList<Khoa> ks;
	private CacheData cacheData;
	private final Class[] mClasss = { String.class, String.class, String.class};
	//rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getFloat(5),
	//rs.getFloat(6), rs.getFloat(7), rs.getDate(8)
	private final String[] colunNames = { "KhoaID", "TenKhoa", "MoTa"};
	
	public Khoa_TableModel(ArrayList<Khoa> ks, CacheData cacheData) {
		this.ks=ks;
		this.cacheData=cacheData;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		 if(ks==null)return 0;
		 else return ks.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Khoa k = ks.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return k.getKhoaID();
		case 1:
			return k.getTenKhoa();
		case 2:
			return k.getMota();

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

		if (columnIndex == 0||columnIndex == 6||columnIndex == 7) {
			return false;
		} else
			return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		
		Khoa k = ks.get(rowIndex);

		switch (columnIndex) {
		case 0:
			k.setKhoaID((String) aValue);
			break;
		case 1:
			k.setTenKhoa((String) aValue);
			break;
		case 2:
			k.setMota((String) aValue);
			break;

			
		}

		//ConnectSQL cn=new ConnectSQL();
		//cn.UpdateBienLai(bl);
		Req req = new Req(new DataReq(DataReq.UpdateKhoa, k, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));		
	}
	public void removeRow(int selectedRow) {
		// TODO Auto-generated method stub
		ks.remove(selectedRow);		
	}

	public void addRow(Object[] objects) {
		// TODO Auto-generated method stub
		ks.add(new Khoa((String)objects[0], (String)objects[1], (String)objects[2]));
	}

	@Override
	public int findColumn(String columnName) {
		// TODO Auto-generated method stub
		return super.findColumn(columnName);
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		super.addTableModelListener(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		super.removeTableModelListener(l);
	}

	@Override
	public TableModelListener[] getTableModelListeners() {
		// TODO Auto-generated method stub
		return super.getTableModelListeners();
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

	public ArrayList<Khoa> getKs() {
		return ks;
	}

	public void setKs(ArrayList<Khoa> ks) {
		this.ks = ks;
	}

}
	
	

