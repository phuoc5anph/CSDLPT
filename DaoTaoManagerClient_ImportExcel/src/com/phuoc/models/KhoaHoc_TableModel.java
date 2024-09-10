package com.phuoc.models;

import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import com.phuoc.Sever.DataReq;
import com.phuoc.Sever.Req;
import com.phuoc.data.CacheData;
import com.phuoc.userinterface.AppFrame;



public class KhoaHoc_TableModel extends AbstractTableModel {

	private ArrayList<KhoaHoc> khs;
	private CacheData cacheData;
	private final Class[] mClasss = { String.class, String.class, String.class, String.class, String.class};

	private final String[] colunNames = { "KhoaHocID", "TenKhoaHoc", "KhoaID", "ChuyenNganhID", "NamHoc"};
	
	public KhoaHoc_TableModel(ArrayList<KhoaHoc> khs, CacheData cacheData) {
		this.khs=khs;
		this.cacheData = cacheData;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		 if(khs==null)return 0;
		 else return khs.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		KhoaHoc kh = khs.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return kh.getKhoaHocID();
		case 1:
			return kh.getTenKhoaHoc();
		case 2:
			return kh.getKhoaID();
		case 3:
			return kh.getChuyenNganhID();
		case 4:
			return kh.getNamHoc();
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
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		KhoaHoc kh = khs.get(rowIndex);

		switch (columnIndex) {
		case 0:
			kh.setKhoaHocID((String) aValue);
			break;
		case 1:
			kh.setTenKhoaHoc((String) aValue);
			break;
		case 2:
			kh.setKhoaID((String) aValue);
			break;
		case 3:
			kh.setChuyenNganhID((String) aValue);
			break;
		case 4:
			kh.setNamHoc((String) aValue);
			break;


		}

		//ConnectSQL cn=new ConnectSQL();
		//cn.UpdateKhachHang(kh);
		Req req = new Req(new DataReq(DataReq.UpdateKhoaHoc, kh, null,cacheData.getUseraccount().getUsername(), cacheData.getPost()));

	}

	public void removeRow(int selectedRow) {
		// TODO Auto-generated method stub
		khs.remove(selectedRow);
		
	}

	public void addRow(Object[] objects) {
		// TODO Auto-generated method stub
		khs.add(new KhoaHoc((String)objects[0], (String)objects[1], (String)objects[2],(String)objects[3],(String)objects[4]));
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

	public ArrayList<KhoaHoc> getKhs() {
		return khs;
	}

	public void setKhs(ArrayList<KhoaHoc> khs) {
		this.khs = khs;
	}

	

}
	
	

