package com.phuoc.userinterface;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.phuoc.Sever.DataReq;
import com.phuoc.Sever.DataResp;
import com.phuoc.Sever.Req;
import com.phuoc.data.CacheData;
import com.phuoc.models.HocPhanTrongKhoaHoc;
import com.phuoc.models.HocPhanTrongKhoaHoc_TableModel;



import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class HocPhanTrongKhoaHocPanel extends JPanel {
	private JTextField textFieldSearch;
	private JTextField tf_HocPhanTrongKhoaHocID;
	private JTextField tf_KhoaHocID;
	private JTextField tf_HocPhanID;
	private JTextField tf_HocKy;
	private JTable table;
	private HocPhanTrongKhoaHoc_TableModel model;
    private CacheData cacheData;
	//ConnectSQL cn = new ConnectSQL();

	/**
	 * Create the panel.
	 */

	public HocPhanTrongKhoaHocPanel(CacheData cacheData) {
		this.cacheData = cacheData;
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		scrollPane.setBounds(23, 80, 596, 301);
		add(scrollPane);

		//ArrayList<NhanVien> nhanViens = cn.GetNhanViens();
		//Req req = new Req(new DataReq(DataReq.GetNhanViens, null, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
		ArrayList<HocPhanTrongKhoaHoc> hocPhanTrongKhoaHocs=null;
		 //if(req.getDataResp().getState()==DataResp.Success)	{
		hocPhanTrongKhoaHocs = getHocPhanTrongKhoaHocs();//(ArrayList<NhanVien>)req.getDataResp().getObject();}
		
		model=new HocPhanTrongKhoaHoc_TableModel(hocPhanTrongKhoaHocs, cacheData);
		table = new JTable(model);	
		scrollPane.setViewportView(table);
		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(23, 24, 445, 32);
		add(textFieldSearch);
		textFieldSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldSearch.getText();
				Req req = new Req(new DataReq(DataReq.SearchHocPhanTrongKhoaHoc,
						textFieldSearch.getText() , null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
				ArrayList<HocPhanTrongKhoaHoc> hptkhs = (ArrayList<HocPhanTrongKhoaHoc>) req.getDataResp().getObject();
			    model.setHptkhs(hptkhs);
			    model.fireTableDataChanged();
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(494, 22, 125, 32);
		add(btnSearch);

//		tf_MaNV = new JTextField();
//		tf_MaNV.setBounds(719, 24, 158, 32);
//		add(tf_MaNV);
//		tf_MaNV.setColumns(10);
//
//		JLabel lblNewLabel = new JLabel("MaNV:");
//		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblNewLabel.setBounds(565, 24, 125, 32);
//		add(lblNewLabel);


		tf_KhoaHocID = new JTextField();
		tf_KhoaHocID.setColumns(10);
		tf_KhoaHocID.setBounds(768, 62, 158, 32);
		add(tf_KhoaHocID);

		tf_HocPhanID = new JTextField();
		tf_HocPhanID.setColumns(10);
		tf_HocPhanID.setBounds(768, 126, 158, 32);
		add(tf_HocPhanID);
		
		tf_HocKy = new JTextField();
		tf_HocKy.setColumns(10);
		tf_HocKy.setBounds(768, 182, 158, 32);
		add(tf_HocKy);

		JLabel lblKhoaHocID = new JLabel("KhoaHocID:");
		lblKhoaHocID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKhoaHocID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKhoaHocID.setBounds(633, 62, 125, 32);
		add(lblKhoaHocID);

		JLabel lblHocPhanID = new JLabel("HocPhanID:");
		lblHocPhanID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHocPhanID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHocPhanID.setBounds(633, 124, 125, 32);
		add(lblHocPhanID);

		JLabel lblHocKy = new JLabel("HocKy:");
		lblHocKy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHocKy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHocKy.setBounds(633, 172, 125, 32);
		add(lblHocKy);
		
	
		JButton btnThemHPTKH = new JButton("Them HPTKH");
		btnThemHPTKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				HocPhanTrongKhoaHoc hp = new HocPhanTrongKhoaHoc(null, tf_KhoaHocID.getText(), tf_HocPhanID.getText(),
						tf_HocKy.getText());
				/*ConnectSQL cn = new ConnectSQL();
			 if (cn.InsertNhanVien(nv) != 0)
					model.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getSDT(), nv.getDiaChi() });
			     */   
				Req req = new Req(new DataReq(DataReq.InsertHocPhanTrongKhoaHoc, hp, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
				if(req.getDataResp().getState()==DataResp.Success) {
					//model.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getSDT(), nv.getDiaChi(), nv.getMaCN() });
					
					ArrayList<HocPhanTrongKhoaHoc> hptkhs = getHocPhanTrongKhoaHocs();
				    
				    model.setHptkhs(hptkhs);
				}
			        model.fireTableDataChanged();
			}
		});
		btnThemHPTKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemHPTKH.setBounds(768, 421, 158, 32);
		add(btnThemHPTKH);

		JButton btnXoaHPTKH = new JButton("Xoa HTKH");
		btnXoaHPTKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				while (table.getSelectedRowCount() != 0) {
					//cn.DeleteNhanVien(model.getValueAt(table.getSelectedRow(), 0).toString());
					
                    Req req = new Req(new DataReq(DataReq.DeleteHocPhanTrongKhoaHoc, model.getValueAt(table.getSelectedRow(), 0).toString(), null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
					
					if(req.getDataResp().getState()==DataResp.Success)
					model.removeRow(table.getSelectedRow());
					model.fireTableDataChanged();
                }

			}
		});
		btnXoaHPTKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaHPTKH.setBounds(379, 408, 158, 32);
		add(btnXoaHPTKH);
		
		JButton btnXuatExcel = new JButton("Xuất Excel");
		btnXuatExcel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXuatExcel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Chỉ cho phép chọn thư mục

		        int result = fileChooser.showOpenDialog(null); // Hiển thị hộp thoại chọn thư mục

		        if (result == JFileChooser.APPROVE_OPTION) {
		            // Người dùng đã chọn một thư mục
		            String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
		            System.out.println("Thư mục đã chọn: " + selectedDirectory);
		            XSSFWorkbook workbook = new XSSFWorkbook();
		            XSSFSheet sheet = workbook.createSheet("HocPhanTrongKhoaHocs");
		            ArrayList<HocPhanTrongKhoaHoc> hptkhs = getHocPhanTrongKhoaHocs();
		            
		            
		            int row = 0;
		            XSSFRow excelRow;
		            XSSFCell cell;
		            //headerrow
		            excelRow = sheet.createRow(row);
	                cell = excelRow.createCell(0);
	                cell.setCellValue("HocPhanTrongKhoaHocID");
	                cell = excelRow.createCell(1);
	                cell.setCellValue("KhoaHocID");
	                cell = excelRow.createCell(2);
	                cell.setCellValue("HocPhanID");
	                cell = excelRow.createCell(3);
	                cell.setCellValue("HocKy");
	                
		            for (HocPhanTrongKhoaHoc hptkh : hptkhs) {
		            	row++;
		                excelRow = sheet.createRow(row);
		                cell = excelRow.createCell(0);
		                cell.setCellValue(hptkh.getHocPhanTrongKhoaHocID());
		                cell = excelRow.createCell(1);
		                cell.setCellValue(hptkh.getKhoaHocID());
		                cell = excelRow.createCell(2);
		                cell.setCellValue(hptkh.getHocPhanID());
		                cell = excelRow.createCell(3);
		                cell.setCellValue(hptkh.getHocKy());
		                
		            }
		            
		            try {
		                FileOutputStream fileOut = new FileOutputStream(selectedDirectory+"\\hocphantrongkhoahocs.xlsx");
		                workbook.write(fileOut);
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            } finally {
		                try {
		                    workbook.close();
		                    JOptionPane.showMessageDialog(null, "Xuất file thành công");
		                } catch (Exception e1) {
		                    e1.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "Xuất file thất bại "+e1);
		                }
		            }
		  
		        } else {
		        	JOptionPane.showMessageDialog(null, "Không có thư mục nào");
		        }
			}
		});
		btnXuatExcel.setBounds(149, 415, 127, 23);
		add(btnXuatExcel);
		
		JButton btnNhapExcel = new JButton("Nhập Excel");
		btnNhapExcel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        JFileChooser fileChooser = new JFileChooser();

		        // Thiết lập bộ lọc tệp nếu bạn muốn hạn chế loại tệp được chọn
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp văn bản", "xlsx");
		        fileChooser.setFileFilter(filter);

		        int result = fileChooser.showOpenDialog(null); // Hiển thị hộp thoại chọn tệp

		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            String excelFilePath = selectedFile.getAbsolutePath();
		            System.out.println("Tệp đã chọn: " + excelFilePath);
		            
		            try (FileInputStream fis = new FileInputStream(excelFilePath);
		           	     Workbook workbook = new XSSFWorkbook(fis)) { 

		           	    Sheet sheet = workbook.getSheet("HocPhanTrongKhoaHocs"); 
		           	    //ArrayList<HocPhan> hps = new ArrayList<HocPhan>();
		           	    HocPhanTrongKhoaHoc hp;
		           	    
		           	    for (Row row : sheet) {
		           	        if (row.getRowNum() == 0) {
		           	            continue;
		           	        }
		           	        String hocPhanTrongKhoaHocID = getCellValue(row.getCell(0)).toString();
		           	        String khoaHocID = getCellValue(row.getCell(1)).toString();
		           	        String hocPhanID = getCellValue(row.getCell(2)).toString();
		           	        String hocKy = getCellValue(row.getCell(3)).toString();
		           	        

		           	        hp = new HocPhanTrongKhoaHoc(hocPhanTrongKhoaHocID, khoaHocID, hocPhanID, hocKy);
		           	        Req req = new Req(new DataReq(DataReq.InsertHocPhanTrongKhoaHoc, hp, null,cacheData.getUseraccount().getUsername(), cacheData.getPost()));
		           	        //hps.add(hocPhan);
		           	    }
		           	    
		           	    

		           	} catch (Exception e2) {
		           		JOptionPane.showMessageDialog(null, "Xuất hiện lổi khi nhập bằng excel"+e2);
		           	}	

		            JOptionPane.showMessageDialog(null, "Nhập bằng file excel thành công!");
		        } else {
		        	JOptionPane.showMessageDialog(null, "Không có tệp nào được chọn!");
		        }              

			}
		});
		btnNhapExcel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNhapExcel.setBounds(22, 413, 117, 23);
		add(btnNhapExcel);

	}

	public CacheData getCacheData() {
		return cacheData;
	}

	public void setCacheData(CacheData cacheData) {
		this.cacheData = cacheData;
	}
	
	public ArrayList<HocPhanTrongKhoaHoc> getHocPhanTrongKhoaHocs() {
		
		 Req req = new Req(new DataReq(DataReq.GetHocPhanTrongKhoaHocs, null, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
		 ArrayList<HocPhanTrongKhoaHoc> hocPhanTrongKhoaHocs=null;
		 if(req.getDataResp().getState()==DataResp.Success)	{
			 hocPhanTrongKhoaHocs= (ArrayList<HocPhanTrongKhoaHoc>)req.getDataResp().getObject();}
		 return hocPhanTrongKhoaHocs;
	}
	
	private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = (int)cell.getNumericCellValue();
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
        case BLANK:
        case ERROR:
            break;
        default:
            break;
        }
 
        return cellValue;
    }
	
}
