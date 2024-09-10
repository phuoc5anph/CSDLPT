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
import com.phuoc.models.HocPhan;
import com.phuoc.models.HocPhan_TableModel;



import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HocPhanPanel extends JPanel {
	private JTextField textFieldSearch;
	private JTextField tf_HocPhanID;
	private JTextField tf_TenHocPhan;
	private JTextField tf_MaHocPhan;
	private JTextField tf_MoTa;
	private JTextField tf_KhoaID;
	private JTextField tf_LoaiHocPhan;
	private JTextField tf_SoTinChi;
	private JTable table;
	private HocPhan_TableModel model;
    private CacheData cacheData;
	//ConnectSQL cn = new ConnectSQL();

	/**
	 * Create the panel.
	 */

	public HocPhanPanel(CacheData cacheData) {
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
		ArrayList<HocPhan> hocPhans=null;
		 //if(req.getDataResp().getState()==DataResp.Success)	{
		hocPhans = getHocPhans();//(ArrayList<NhanVien>)req.getDataResp().getObject();}
		
		model=new HocPhan_TableModel(hocPhans, cacheData);
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
				Req req = new Req(new DataReq(DataReq.SearchHocPhan,
						textFieldSearch.getText() , null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
				ArrayList<HocPhan> hps = (ArrayList<HocPhan>) req.getDataResp().getObject();
		    
			    model.setHps(hps);
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

		tf_TenHocPhan = new JTextField();
		tf_TenHocPhan.setColumns(10);
		tf_TenHocPhan.setBounds(768, 62, 158, 32);
		add(tf_TenHocPhan );

		tf_MaHocPhan = new JTextField();
		tf_MaHocPhan.setColumns(10);
		tf_MaHocPhan.setBounds(768, 126, 158, 32);
		add(tf_MaHocPhan);

		tf_MoTa = new JTextField();
		tf_MoTa.setColumns(10);
		tf_MoTa.setBounds(768, 182, 158, 32);
		add(tf_MoTa);
		
		tf_KhoaID = new JTextField();
		tf_KhoaID.setColumns(10);
		tf_KhoaID.setBounds(768, 244, 158, 32);
		add(tf_KhoaID);
		
		tf_LoaiHocPhan = new JTextField();
		tf_LoaiHocPhan.setColumns(10);
		tf_LoaiHocPhan.setBounds(768, 302, 158, 32);
		add(tf_LoaiHocPhan);
		
		tf_SoTinChi = new JTextField();
		tf_SoTinChi.setColumns(10);
		tf_SoTinChi.setBounds(768, 362, 158, 32);
		add(tf_SoTinChi);

		JLabel lblTenHocPhan = new JLabel("TenHocPhan:");
		lblTenHocPhan.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenHocPhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenHocPhan.setBounds(633, 62, 125, 32);
		add(lblTenHocPhan);

		JLabel lblMaHocPhan = new JLabel("MaHocPhan:");
		lblMaHocPhan.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaHocPhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaHocPhan.setBounds(633, 124, 125, 32);
		add(lblMaHocPhan);

		JLabel lblMoTa = new JLabel("MoTa:");
		lblMoTa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoTa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMoTa.setBounds(633, 172, 125, 32);
		add(lblMoTa);
		
		JLabel lblKhoaID = new JLabel("KhoaID:");
		lblKhoaID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKhoaID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKhoaID.setBounds(633, 242, 125, 32);
		add(lblKhoaID);
		
		JLabel lblLoaiHocPhan = new JLabel("LoaiHocPhan:");
		lblLoaiHocPhan.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoaiHocPhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoaiHocPhan.setBounds(633, 302, 125, 32);
		add(lblLoaiHocPhan);
		
		JLabel lblSoTinChi = new JLabel("SoTinChi:");
		lblSoTinChi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoTinChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoTinChi.setBounds(633, 362, 125, 32);
		add(lblSoTinChi);
		

		JButton btnThemHP = new JButton("Them HocPhan");
		btnThemHP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				HocPhan hp = new HocPhan(null, tf_TenHocPhan.getText(), tf_MaHocPhan.getText(),
						tf_MoTa.getText(), tf_KhoaID.getText(), tf_LoaiHocPhan.getText(), tf_SoTinChi.getText());
				/*ConnectSQL cn = new ConnectSQL();
			 if (cn.InsertNhanVien(nv) != 0)
					model.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getSDT(), nv.getDiaChi() });
			     */   
				Req req = new Req(new DataReq(DataReq.InsertHocPhan, hp, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
				if(req.getDataResp().getState()==DataResp.Success) {
					//model.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getSDT(), nv.getDiaChi(), nv.getMaCN() });
					
					ArrayList<HocPhan> hps = getHocPhans();
				    
				    model.setHps(hps);
				}
			        model.fireTableDataChanged();
			}
		});
		btnThemHP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemHP.setBounds(768, 421, 158, 32);
		add(btnThemHP);

		JButton btnXoaHP = new JButton("Xoa Hoc Phan");
		btnXoaHP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoaHP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				while (table.getSelectedRowCount() != 0) {
					//cn.DeleteNhanVien(model.getValueAt(table.getSelectedRow(), 0).toString());
					
                    Req req = new Req(new DataReq(DataReq.DeleteHocPhan, model.getValueAt(table.getSelectedRow(), 0).toString(), null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
					
					if(req.getDataResp().getState()==DataResp.Success)
					model.removeRow(table.getSelectedRow());
					model.fireTableDataChanged();
                }

			}
		});
		btnXoaHP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaHP.setBounds(379, 408, 158, 32);
		add(btnXoaHP);
		
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
		            XSSFSheet sheet = workbook.createSheet("HocPhans");
		            ArrayList<HocPhan> hps = getHocPhans();
		            
		            
		            int row = 0;
		            XSSFRow excelRow;
		            XSSFCell cell;
		            //headerrow
		            excelRow = sheet.createRow(row);
	                cell = excelRow.createCell(0);
	                cell.setCellValue("HocPhanID");
	                cell = excelRow.createCell(1);
	                cell.setCellValue("TenHocPHan");
	                cell = excelRow.createCell(2);
	                cell.setCellValue("MaHocPhan");
	                cell = excelRow.createCell(3);
	                cell.setCellValue("MoTa");
	                cell = excelRow.createCell(4);
	                cell.setCellValue("KhoaID");
	                cell = excelRow.createCell(5);
	                cell.setCellValue("LoaiHocPhan");
	                cell = excelRow.createCell(6);
	                cell.setCellValue("SoTinChi");
	                
		            for (HocPhan hp : hps) {
		            	row++;
		                excelRow = sheet.createRow(row);
		                cell = excelRow.createCell(0);
		                cell.setCellValue(hp.getHocPhanID());
		                cell = excelRow.createCell(1);
		                cell.setCellValue(hp.getTenHocPhan());
		                cell = excelRow.createCell(2);
		                cell.setCellValue(hp.getMaHocPhan());
		                cell = excelRow.createCell(3);
		                cell.setCellValue(hp.getMota());
		                cell = excelRow.createCell(4);
		                cell.setCellValue(hp.getKhoaID());
		                cell = excelRow.createCell(5);
		                cell.setCellValue(hp.getLoaiHocPhan());
		                cell = excelRow.createCell(6);
		                cell.setCellValue(hp.getSoTinChi());
		                
		            }
		            
		            try {
		                FileOutputStream fileOut = new FileOutputStream(selectedDirectory+"\\hocphans.xlsx");
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

		           	    Sheet sheet = workbook.getSheet("HocPhans"); 
		           	    //ArrayList<HocPhan> hps = new ArrayList<HocPhan>();
		           	    HocPhan hp;
		           	    
		           	    for (Row row : sheet) {
		           	        if (row.getRowNum() == 0) {
		           	            continue;
		           	        }
		           	        String hocPhanID = getCellValue(row.getCell(0)).toString();
		           	        String tenHocPhan = getCellValue(row.getCell(1)).toString();
		           	        String maHocPhan = getCellValue(row.getCell(2)).toString();
		           	        String mota = getCellValue(row.getCell(3)).toString();
		           	        String khoaID = getCellValue(row.getCell(4)).toString();
		           	        String loaiHocPhan = getCellValue(row.getCell(5)).toString();
		           	        String soTinChi = getCellValue(row.getCell(6)).toString();

		           	        hp = new HocPhan(hocPhanID, tenHocPhan, maHocPhan, mota, khoaID, loaiHocPhan, soTinChi);
		           	        Req req = new Req(new DataReq(DataReq.InsertHocPhan, hp, null,cacheData.getUseraccount().getUsername(), cacheData.getPost()));
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
	
	public ArrayList<HocPhan> getHocPhans() {
		
		 Req req = new Req(new DataReq(DataReq.GetHocPhans, null, null, cacheData.getUseraccount().getUsername(), cacheData.getPost()));
		 ArrayList<HocPhan> hocPhans=null;
		 if(req.getDataResp().getState()==DataResp.Success)	{
		 hocPhans= (ArrayList<HocPhan>)req.getDataResp().getObject();}
		 return hocPhans;
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
