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



public class ChuyenNganh_TableModel extends AbstractTableModel {

	private ArrayList<ChuyenNganh> cns;
	private CacheData cacheData;
	
	private final Class[] mClasss = { String.class, String.class, String.class, String.class};

	private final String[] colunNames = { "ChuyenNganhID", "TenChuyenNganh", "Mota", "NganhID"};
	
	public ChuyenNganh_TableModel(ArrayList<ChuyenNganh> cns, CacheData cacheData) {
		this.cns=cns;
		this.cacheData = cacheData;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		 if(cns==null)return 0;
		 else return cns.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		ChuyenNganh cn = cns.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return cn.getChuyenNganhID();
		case 1:
			return cn.getTenChuyenNganh();
		case 2:
			return cn.getMota();
		case 3:
			return cn.getNganhID();
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
		ChuyenNganh cn = cns.get(rowIndex);

		switch (columnIndex) {
		case 0:
			cn.setChuyenNganhID((String) aValue);
			break;
		case 1:
			cn.setTenChuyenNganh((String) aValue);
			break;
		case 2:
			cn.setMota((String) aValue);
			break;
		case 3:
			cn.setNganhID((String) aValue);
			break;
		}

		//ConnectSQL cn=new ConnectSQL();
		//cn.UpdateKhachHang(kh);
		Req req = new Req(new DataReq(DataReq.UpdateChuyenNganh, cn, null,cacheData.getUseraccount().getUsername(), cacheData.getPost()));

	}

	public void removeRow(int selectedRow) {
		// TODO Auto-generated method stub
		cns.remove(selectedRow);
		
	}

	public void addRow(Object[] objects) {
		// TODO Auto-generated method stub
		cns.add(new ChuyenNganh((String)objects[0], (String)objects[1], (String)objects[2], (String)objects[3]));
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

	public ArrayList<ChuyenNganh> getCns() {
		return cns;
	}

	public void setCns(ArrayList<ChuyenNganh> cns) {
		this.cns = cns;
	}


	
	

}
	
	

