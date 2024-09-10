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
import com.phuoc.models.ChuyenNganh;
import com.phuoc.models.ChuyenNganh_TableModel;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChuyenNganhPanel extends JPanel {
	private JTextField textFieldSearch;
	private JTextField tf_ChuyenNganhID;
	private JTextField tf_TenChuyenNganh;
	private JTextField tf_MoTa;
	private JTextField tf_NganhID;
	private JTable table;
	private ChuyenNganh_TableModel model;
	//ConnectSQL cn=new ConnectSQL();
	private CacheData cacheData;

	/**
	 * Create the panel.
	 */
	
	public ChuyenNganhPanel(CacheData cacheData) {
		this.cacheData = cacheData;
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 80, 596, 301);
		add(scrollPane);
		
		//ArrayList<KhachHang> khs=cn.GetKhachHangs();
		
        //Req req = new Req(new DataReq(DataReq.GetKhachHangs, null, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
		
		ArrayList<ChuyenNganh> cns= getChuyenNganhs();
		
		
		model=new ChuyenNganh_TableModel(cns, cacheData);
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
				Req req = new Req(new DataReq(DataReq.SearchChuyenNganh,
						textFieldSearch.getText() , null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
				ArrayList<ChuyenNganh> cns = (ArrayList<ChuyenNganh>) req.getDataResp().getObject();
		    
			    model.setCns(cns);
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
		
		tf_TenChuyenNganh = new JTextField();
		tf_TenChuyenNganh.setColumns(10);
		tf_TenChuyenNganh.setBounds(768, 72, 158, 32);
		add(tf_TenChuyenNganh);
		
		tf_MoTa = new JTextField();
		tf_MoTa.setColumns(10);
		tf_MoTa.setBounds(768, 134, 158, 32);
		add(tf_MoTa);
		
		tf_NganhID = new JTextField();
		tf_NganhID.setColumns(10);
		tf_NganhID.setBounds(768, 206, 158, 32);
		add(tf_NganhID);
		
		
		JLabel lblTenChuyenNganh = new JLabel("TenChuyenNganh:");
		lblTenChuyenNganh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenChuyenNganh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenChuyenNganh.setBounds(633, 72, 125, 32);
		add(lblTenChuyenNganh);
		
		JLabel lblMoTa = new JLabel("MoTa:");
		lblMoTa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoTa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMoTa.setBounds(633, 132, 125, 32);
		add(lblMoTa);
		
		JLabel lblNganhID = new JLabel("NganhID:");
		lblNganhID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNganhID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNganhID.setBounds(633, 206, 125, 32);
		add(lblNganhID);
		
		JButton btnThemCN = new JButton("Th\u00EAm ChuyenNganh");
		btnThemCN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ChuyenNganh cn = new ChuyenNganh(null, tf_TenChuyenNganh.getText(), tf_MoTa.getText(), tf_NganhID.getText());
				/*ConnectSQL cn = new ConnectSQL();
			     if (cn.InsertKhachHang(kh) != 0)
					model.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getSDT(), kh.getDiaChi(),kh.getMaCongTo() });
			     */
								
				Req req = new Req(new DataReq(DataReq.InsertChuyenNganh, cn, null,cacheData.getUseraccount().getUsername(), cacheData.getPost()));
				if(req.getDataResp().getState()==DataResp.Success) {
					
					 if (req.getDataResp().getState()==DataResp.Success) {
						 ArrayList<ChuyenNganh> cns = getChuyenNganhs();
						 model.setCns(cns);
					 }
		
					       
				}
			        model.fireTableDataChanged();
			}
				
			
		});
		btnThemCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemCN.setBounds(768, 276, 158, 32);
		add(btnThemCN);
		
		JButton btnXoaCN = new JButton("Xoa Chuyen Nganh");
		btnXoaCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaCN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				while (table.getSelectedRowCount() != 0) {
					//cn.DeleteKhachHang(model.getValueAt(table.getSelectedRow(), 0).toString());
					Req req = new Req(new DataReq(DataReq.DeleteChuyenNganh, model.getValueAt(table.getSelectedRow(), 0).toString(), null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
					
					if(req.getDataResp().getState()==DataResp.Success)
					model.removeRow(table.getSelectedRow());
					model.fireTableDataChanged();
                }
				
			}
		});
		btnXoaCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaCN.setBounds(461, 407, 158, 32);
		add(btnXoaCN);
		
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
		            XSSFSheet sheet = workbook.createSheet("ChuyenNganhs");
		            ArrayList<ChuyenNganh> cns = getChuyenNganhs();
		            
		            
		            int row = 0;
		            XSSFRow excelRow;
		            XSSFCell cell;
		            //headerrow
		            excelRow = sheet.createRow(row);
	                cell = excelRow.createCell(0);
	                cell.setCellValue("ChuyenNganhID");
	                cell = excelRow.createCell(1);
	                cell.setCellValue("TenChuyenNganh");
	                cell = excelRow.createCell(2);
	                cell.setCellValue("MoTa");
	                cell = excelRow.createCell(3);
	                cell.setCellValue("NganhID");

	                
		            for (ChuyenNganh cn : cns) {
		            	row++;
		                excelRow = sheet.createRow(row);
		                cell = excelRow.createCell(0);
		                cell.setCellValue(cn.getChuyenNganhID());
		                cell = excelRow.createCell(1);
		                cell.setCellValue(cn.getTenChuyenNganh());
		                cell = excelRow.createCell(2);
		                cell.setCellValue(cn.getMota());
		                cell = excelRow.createCell(3);
		                cell.setCellValue(cn.getNganhID());

		            }
		            
		            try {
		                FileOutputStream fileOut = new FileOutputStream(selectedDirectory+"\\chuyennganhs.xlsx");
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

		           	    Sheet sheet = workbook.getSheet("ChuyenNganhs"); 
		           	    //ArrayList<HocPhan> hps = new ArrayList<HocPhan>();
		           	    ChuyenNganh cn;
		           	    
		           	    for (Row row : sheet) {
		           	        if (row.getRowNum() == 0) {
		           	            continue;
		           	        }
		           	        String chuyenNganhID = getCellValue(row.getCell(0)).toString();
		           	        String tenChuyenNganh = getCellValue(row.getCell(1)).toString();
		           	        String moTa = getCellValue(row.getCell(2)).toString();
		           	        String nganhID = getCellValue(row.getCell(3)).toString();

		           	        cn = new ChuyenNganh(chuyenNganhID, tenChuyenNganh, moTa, nganhID);
		           	        Req req = new Req(new DataReq(DataReq.InsertChuyenNganh, cn, null,cacheData.getUseraccount().getUsername(), cacheData.getPost()));
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
	
	public ArrayList<ChuyenNganh> getChuyenNganhs() {
		
		  Req req = new Req(new DataReq(DataReq.GetChuyenNganhs, null, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
			
	      ArrayList<ChuyenNganh> khs= (ArrayList<ChuyenNganh>)req.getDataResp().getObject();
	      return khs;
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
