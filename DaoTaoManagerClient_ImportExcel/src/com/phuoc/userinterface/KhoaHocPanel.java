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
import com.phuoc.models.KhoaHoc_TableModel;
import com.phuoc.models.KhoaHoc;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class KhoaHocPanel extends JPanel {
	private JTextField textFieldSearch;
	private JTextField tf_KhoaHocID;
	private JTextField tf_TenKhoaHoc;
	private JTextField tf_KhoaID;
	private JTextField tf_ChuyenNganhID;
	private JTextField tf_NamHoc;
	private JTable table;
	private KhoaHoc_TableModel model;
    private CacheData cacheData;
	//ConnectSQL cn = new ConnectSQL();

	/**
	 * Create the panel.
	 */

	public KhoaHocPanel(CacheData cacheData) {
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
		ArrayList<KhoaHoc> khoaHocs=null;
		 //if(req.getDataResp().getState()==DataResp.Success)	{
		khoaHocs = getKhoaHocs();//(ArrayList<NhanVien>)req.getDataResp().getObject();}
		
		model=new KhoaHoc_TableModel(khoaHocs, cacheData);
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
				Req req = new Req(new DataReq(DataReq.SearchKhoaHoc,
						textFieldSearch.getText() , null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
				ArrayList<KhoaHoc> khs = (ArrayList<KhoaHoc>) req.getDataResp().getObject();
		    
			    model.setKhs(khs);
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

		tf_TenKhoaHoc = new JTextField();
		tf_TenKhoaHoc.setColumns(10);
		tf_TenKhoaHoc.setBounds(768, 62, 158, 32);
		add(tf_TenKhoaHoc );

		tf_KhoaID = new JTextField();
		tf_KhoaID.setColumns(10);
		tf_KhoaID.setBounds(768, 126, 158, 32);
		add(tf_KhoaID);

		tf_ChuyenNganhID = new JTextField();
		tf_ChuyenNganhID.setColumns(10);
		tf_ChuyenNganhID.setBounds(768, 182, 158, 32);
		add(tf_ChuyenNganhID);
		
		tf_NamHoc = new JTextField();
		tf_NamHoc.setColumns(10);
		tf_NamHoc.setBounds(768, 244, 158, 32);
		add(tf_NamHoc);

		JLabel lblTenKhoaHoc = new JLabel("TenKhoaHoc:");
		lblTenKhoaHoc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenKhoaHoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKhoaHoc.setBounds(633, 62, 125, 32);
		add(lblTenKhoaHoc);

		JLabel lblKhoaID = new JLabel("KhoaID:");
		lblKhoaID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKhoaID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKhoaID.setBounds(633, 124, 125, 32);
		add(lblKhoaID);
		
		JLabel lblChuyenNganhID = new JLabel("ChuyenNganhID:");
		lblChuyenNganhID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChuyenNganhID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChuyenNganhID.setBounds(633, 172, 125, 32);
		add(lblChuyenNganhID);
		
		JLabel lblNamHoc = new JLabel("NamHoc:");
		lblNamHoc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNamHoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNamHoc.setBounds(633, 242, 125, 32);
		add(lblNamHoc);
		
		

		JButton btnThemKH = new JButton("Them KhoaHoc");
		btnThemKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				KhoaHoc kh = new KhoaHoc(null, tf_TenKhoaHoc.getText(), tf_KhoaID.getText(),
						tf_ChuyenNganhID.getText(), tf_NamHoc.getText());
				/*ConnectSQL cn = new ConnectSQL();
			 if (cn.InsertNhanVien(nv) != 0)
					model.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getSDT(), nv.getDiaChi() });
			     */   
				Req req = new Req(new DataReq(DataReq.InsertKhoaHoc, kh, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
				if(req.getDataResp().getState()==DataResp.Success) {
					//model.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getSDT(), nv.getDiaChi(), nv.getMaCN() });
					
					ArrayList<KhoaHoc> khs = getKhoaHocs();
				    
				    model.setKhs(khs);
				}
			        model.fireTableDataChanged();
			}
		});
		btnThemKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemKH.setBounds(768, 421, 158, 32);
		add(btnThemKH);

		JButton btnXoaKH = new JButton("Xoa Khoa Hoc");
		btnXoaKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				while (table.getSelectedRowCount() != 0) {
					//cn.DeleteNhanVien(model.getValueAt(table.getSelectedRow(), 0).toString());
					
                    Req req = new Req(new DataReq(DataReq.DeleteKhoaHoc, model.getValueAt(table.getSelectedRow(), 0).toString(), null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
					
					if(req.getDataResp().getState()==DataResp.Success)
					model.removeRow(table.getSelectedRow());
					model.fireTableDataChanged();
                }

			}
		});
		btnXoaKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaKH.setBounds(379, 408, 158, 32);
		add(btnXoaKH);
		
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
		            XSSFSheet sheet = workbook.createSheet("KhoaHocs");
		            ArrayList<KhoaHoc> khs = getKhoaHocs();
		            
		            
		            int row = 0;
		            XSSFRow excelRow;
		            XSSFCell cell;
		            //headerrow
		            excelRow = sheet.createRow(row);
	                cell = excelRow.createCell(0);
	                cell.setCellValue("KhoaHocID");
	                cell = excelRow.createCell(1);
	                cell.setCellValue("TenKhoaHoc");
	                cell = excelRow.createCell(2);
	                cell.setCellValue("KhoaID");
	                cell = excelRow.createCell(3);
	                cell.setCellValue("ChuyenNganhID");
	                cell = excelRow.createCell(4);
	                cell.setCellValue("NamHoc");
	                
		            for (KhoaHoc kh : khs) {
		            	row++;
		                excelRow = sheet.createRow(row);
		                cell = excelRow.createCell(0);
		                cell.setCellValue(kh.getKhoaHocID());
		                cell = excelRow.createCell(1);
		                cell.setCellValue(kh.getTenKhoaHoc());
		                cell = excelRow.createCell(2);
		                cell.setCellValue(kh.getKhoaID());
		                cell = excelRow.createCell(3);
		                cell.setCellValue(kh.getChuyenNganhID());
		                cell = excelRow.createCell(4);
		                cell.setCellValue(kh.getNamHoc());
		                
		            }
		            
		            try {
		                FileOutputStream fileOut = new FileOutputStream(selectedDirectory+"\\khoahocs.xlsx");
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

		           	    Sheet sheet = workbook.getSheet("KhoaHocs"); 
		           	    //ArrayList<HocPhan> hps = new ArrayList<HocPhan>();
		           	    KhoaHoc kh;
		           	    
		           	    for (Row row : sheet) {
		           	        if (row.getRowNum() == 0) {
		           	            continue;
		           	        }
		           	        String khoaHocID = getCellValue(row.getCell(0)).toString();
		           	        String tenKhoaHoc = getCellValue(row.getCell(1)).toString();
		           	        String khoaID = getCellValue(row.getCell(2)).toString();
		           	        String chuyenNganhID = getCellValue(row.getCell(3)).toString();
		           	        String namHoc = getCellValue(row.getCell(4)).toString();
		           	        

		           	        kh = new KhoaHoc(khoaHocID, tenKhoaHoc, khoaHocID, chuyenNganhID, namHoc);
		           	        Req req = new Req(new DataReq(DataReq.InsertKhoaHoc, kh, null,cacheData.getUseraccount().getUsername(), cacheData.getPost()));
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
	
	public ArrayList<KhoaHoc> getKhoaHocs() {
		
		 Req req = new Req(new DataReq(DataReq.GetKhoaHocs, null, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
		 ArrayList<KhoaHoc> khoaHocs=null;
		 if(req.getDataResp().getState()==DataResp.Success)	{
		 khoaHocs= (ArrayList<KhoaHoc>)req.getDataResp().getObject();}
		 return khoaHocs;
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
