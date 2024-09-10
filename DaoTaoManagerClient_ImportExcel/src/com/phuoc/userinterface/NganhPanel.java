package com.phuoc.userinterface;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import com.phuoc.models.Nganh_TableModel;
import com.phuoc.models.Nganh;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NganhPanel extends JPanel {
	private JTextField textFieldSearch;
	private JTextField tf_NganhID;
	private JTextField tf_TenNganh;
	private JTextField tf_MoTa;
	private JTextField tf_KhoaID;
	private JTable table;
	private Nganh_TableModel model;
	//ConnectSQL cn=new ConnectSQL();
	private CacheData cacheData;

	/**
	 * Create the panel.
	 */
	
	public NganhPanel(CacheData cacheData) {
		this.cacheData = cacheData;
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 80, 596, 301);
		add(scrollPane);
		
		//ArrayList<KhachHang> khs=cn.GetKhachHangs();
		
        //Req req = new Req(new DataReq(DataReq.GetKhachHangs, null, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
		
		ArrayList<Nganh> ns= getNganhs();
		
		
		model=new Nganh_TableModel(ns, cacheData);
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
				Req req = new Req(new DataReq(DataReq.SearchNganh,
						textFieldSearch.getText() , null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
				ArrayList<Nganh> ns = (ArrayList<Nganh>) req.getDataResp().getObject();
		    
			    model.setNs(ns);
			    model.fireTableDataChanged();
			    
			

			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(494, 22, 125, 32);
		add(btnSearch);
		
//		tf_MaKH = new JTextField();
//		tf_MaKH.setBounds(719, 24, 158, 32);
//		add(tf_MaKH);
//		tf_MaKH.setColumns(10);
//		
//		JLabel lblMaKH = new JLabel("MaKH:");
//		lblMaKH.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblMaKH.setBounds(565, 24, 125, 32);
//		add(lblMaKH);
		
		tf_TenNganh = new JTextField();
		tf_TenNganh.setColumns(10);
		tf_TenNganh.setBounds(768, 72, 158, 32);
		add(tf_TenNganh);
		
		tf_MoTa = new JTextField();
		tf_MoTa.setColumns(10);
		tf_MoTa.setBounds(768, 134, 158, 32);
		add(tf_MoTa);
		
		tf_KhoaID = new JTextField();
		tf_KhoaID.setColumns(10);
		tf_KhoaID.setBounds(768, 206, 158, 32);
		add(tf_KhoaID);
		
		
		JLabel lblTenNganh = new JLabel("TenNganh:");
		lblTenNganh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenNganh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenNganh.setBounds(633, 72, 125, 32);
		add(lblTenNganh);
		
		JLabel lblMoTa = new JLabel("MoTa:");
		lblMoTa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoTa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMoTa.setBounds(633, 132, 125, 32);
		add(lblMoTa);
		
		JLabel lblKhoaID = new JLabel("KhoaID:");
		lblKhoaID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKhoaID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKhoaID.setBounds(633, 206, 125, 32);
		add(lblKhoaID);
		
		JButton btnThemN = new JButton("Th\u00EAm Nganh");
		btnThemN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Nganh cn = new Nganh(null, tf_TenNganh.getText(), tf_MoTa.getText(), tf_KhoaID.getText());
				/*ConnectSQL cn = new ConnectSQL();
			     if (cn.InsertKhachHang(kh) != 0)
					model.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getSDT(), kh.getDiaChi(),kh.getMaCongTo() });
			     */
								
				Req req = new Req(new DataReq(DataReq.InsertNganh, cn, null,cacheData.getUseraccount().getUsername(), cacheData.getPost()));
				if(req.getDataResp().getState()==DataResp.Success) {
					
					 if (req.getDataResp().getState()==DataResp.Success) {
						 ArrayList<Nganh> cns = getNganhs();
						 model.setNs(cns);
					 }
		
					       
				}
			        model.fireTableDataChanged();
			}
				
			
		});
		btnThemN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemN.setBounds(768, 276, 158, 32);
		add(btnThemN);
		
		JButton btnXoaN = new JButton("Xoa Nganh");
		btnXoaN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				while (table.getSelectedRowCount() != 0) {
					//cn.DeleteKhachHang(model.getValueAt(table.getSelectedRow(), 0).toString());
					Req req = new Req(new DataReq(DataReq.DeleteNganh, model.getValueAt(table.getSelectedRow(), 0).toString(), null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
					
					if(req.getDataResp().getState()==DataResp.Success)
					model.removeRow(table.getSelectedRow());
					model.fireTableDataChanged();
                }
				
			}
		});
		btnXoaN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaN.setBounds(461, 407, 158, 32);
		add(btnXoaN);
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
		            XSSFSheet sheet = workbook.createSheet("Nganhs");
		            ArrayList<Nganh> hps = getNganhs();
		            
		            
		            int row = 0;
		            XSSFRow excelRow;
		            XSSFCell cell;
		            //headerrow
		            excelRow = sheet.createRow(row);
	                cell = excelRow.createCell(0);
	                cell.setCellValue("NganhID");
	                cell = excelRow.createCell(1);
	                cell.setCellValue("TenNganh");
	                cell = excelRow.createCell(2);
	                cell.setCellValue("MoTa");
	                cell = excelRow.createCell(3);
	                cell.setCellValue("KhoaID");

	                
		            for (Nganh n : ns) {
		            	row++;
		                excelRow = sheet.createRow(row);
		                cell = excelRow.createCell(0);
		                cell.setCellValue(n.getNganhID());
		                cell = excelRow.createCell(1);
		                cell.setCellValue(n.getTenNganh());
		                cell = excelRow.createCell(2);
		                cell.setCellValue(n.getMota());
		                cell = excelRow.createCell(3);
		                cell.setCellValue(n.getKhoaID());
		                
		            }
		            
		            try {
		                FileOutputStream fileOut = new FileOutputStream(selectedDirectory+"\\nganhs.xlsx");
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

		           	    Sheet sheet = workbook.getSheet("Nganhs"); 
		           	    //ArrayList<HocPhan> hps = new ArrayList<HocPhan>();
		           	    Nganh n;
		           	    
		           	    for (Row row : sheet) {
		           	        if (row.getRowNum() == 0) {
		           	            continue;
		           	        }
		           	        String nganhID = getCellValue(row.getCell(0)).toString();
		           	        String tenNganh = getCellValue(row.getCell(1)).toString();
		           	        String moTa = getCellValue(row.getCell(2)).toString();
		           	        String khoaID = getCellValue(row.getCell(3)).toString();
		           	        

		           	        n = new Nganh(nganhID, tenNganh, moTa, khoaID);
		           	        Req req = new Req(new DataReq(DataReq.InsertNganh, n, null,cacheData.getUseraccount().getUsername(), cacheData.getPost()));
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
	
	public ArrayList<Nganh> getNganhs() {
		
		  Req req = new Req(new DataReq(DataReq.GetNganhs, null, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
			
	      ArrayList<Nganh> ns= (ArrayList<Nganh>)req.getDataResp().getObject();
	      return ns;
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
