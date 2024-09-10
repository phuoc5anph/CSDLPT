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
import com.phuoc.models.Khoa;
import com.phuoc.models.Khoa_TableModel;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KhoaPanel extends JPanel {
	private JTextField textFieldSearch;
	private JTextField tf_KhoaID;
	private JTextField tf_TenKhoa;
	private JTextField tf_MoTa;
	private JTable table;
	private Khoa_TableModel model;
	//ConnectSQL cn=new ConnectSQL();
	private CacheData cacheData;

	/**
	 * Create the panel.
	 */
	
	public KhoaPanel(CacheData cacheData) {
		this.cacheData = cacheData;
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 80, 596, 301);
		add(scrollPane);
		
		//ArrayList<KhachHang> khs=cn.GetKhachHangs();
		
        //Req req = new Req(new DataReq(DataReq.GetKhachHangs, null, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
		
		ArrayList<Khoa> ks= getKhoas();
		
		
		model=new Khoa_TableModel(ks, cacheData);
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
				Req req = new Req(new DataReq(DataReq.SearchKhoa,
						textFieldSearch.getText() , null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
				ArrayList<Khoa> ks = (ArrayList<Khoa>) req.getDataResp().getObject();
		    
			    model.setKs(ks);
			    model.fireTableDataChanged();
			    
			

			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(494, 22, 125, 32);
		add(btnSearch);
		

		
		tf_TenKhoa = new JTextField();
		tf_TenKhoa.setColumns(10);
		tf_TenKhoa.setBounds(768, 72, 158, 32);
		add(tf_TenKhoa);
		
		tf_MoTa = new JTextField();
		tf_MoTa.setColumns(10);
		tf_MoTa.setBounds(768, 134, 158, 32);
		add(tf_MoTa);
		
		
		JLabel lblTenKhoa = new JLabel("TenKhoa:");
		lblTenKhoa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenKhoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKhoa.setBounds(633, 72, 125, 32);
		add(lblTenKhoa);
		
		
		
		JLabel lblMoTa = new JLabel("MoTa:");
		lblMoTa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoTa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMoTa.setBounds(633, 134, 125, 32);
		add(lblMoTa);
		
		
		JButton btnThemK = new JButton("Thêm Khoa");
		btnThemK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Khoa kh = new Khoa(null, tf_TenKhoa.getText(), tf_MoTa.getText());
				/*ConnectSQL cn = new ConnectSQL();
			     if (cn.InsertKhachHang(kh) != 0)
					model.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getSDT(), kh.getDiaChi(),kh.getMaCongTo() });
			     */
								
				Req req = new Req(new DataReq(DataReq.InsertKhoa, kh, null,cacheData.getUseraccount().getUsername(), cacheData.getPost()));
				if(req.getDataResp().getState()==DataResp.Success) {
					
					 if (req.getDataResp().getState()==DataResp.Success) {
						 ArrayList<Khoa> ks = getKhoas();
						 model.setKs(ks);
					 }
		
					       
				}
			        model.fireTableDataChanged();
			}
				
			
		});
		btnThemK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemK.setBounds(768, 204, 158, 32);
		add(btnThemK);
		
		JButton btnXoaKH = new JButton("X\u00F3a Khoa");
		btnXoaKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				while (table.getSelectedRowCount() != 0) {
					//cn.DeleteKhachHang(model.getValueAt(table.getSelectedRow(), 0).toString());
					Req req = new Req(new DataReq(DataReq.DeleteKhoa, model.getValueAt(table.getSelectedRow(), 0).toString(), null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
					
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
		            XSSFSheet sheet = workbook.createSheet("Khoas");
		            ArrayList<Khoa> ks = getKhoas();
		            
		            
		            int row = 0;
		            XSSFRow excelRow;
		            XSSFCell cell;
		            //headerrow
		            excelRow = sheet.createRow(row);
	                cell = excelRow.createCell(0);
	                cell.setCellValue("KhoaID");
	                cell = excelRow.createCell(1);
	                cell.setCellValue("TenKhoa");
	                cell = excelRow.createCell(2);
	                cell.setCellValue("MoTa");

	                
		            for (Khoa k : ks) {
		            	row++;
		                excelRow = sheet.createRow(row);
		                cell = excelRow.createCell(0);
		                cell.setCellValue(k.getKhoaID());
		                cell = excelRow.createCell(1);
		                cell.setCellValue(k.getTenKhoa());
		                cell = excelRow.createCell(2);
		                cell.setCellValue(k.getMota());

		            }
		            
		            try {
		                FileOutputStream fileOut = new FileOutputStream(selectedDirectory+"\\khoas.xlsx");
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

		           	    Sheet sheet = workbook.getSheet("Khoas"); 
		           	    //ArrayList<HocPhan> hps = new ArrayList<HocPhan>();
		           	    Khoa k;
		           	    
		           	    for (Row row : sheet) {
		           	        if (row.getRowNum() == 0) {
		           	            continue;
		           	        }
		           	        String khoaID = getCellValue(row.getCell(0)).toString();
		           	        String tenKhoa = getCellValue(row.getCell(1)).toString();
		           	        String moTa = getCellValue(row.getCell(2)).toString();


		           	        k = new Khoa(khoaID, tenKhoa, moTa);
		           	        Req req = new Req(new DataReq(DataReq.InsertKhoa, k, null,cacheData.getUseraccount().getUsername(), cacheData.getPost()));
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
	
	public ArrayList<Khoa> getKhoas() {
		
		  Req req = new Req(new DataReq(DataReq.GetKhoas, null, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
			
	      ArrayList<Khoa> ks= (ArrayList<Khoa>)req.getDataResp().getObject();
	      return ks;
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

