package com.phuoc.models;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelHocPhans {
	private String excelFilePath;
	private ArrayList<HocPhan> hps;

    public ReadExcelHocPhans(String excelFilePath) {
    	
    	this.excelFilePath = excelFilePath;

    	try (FileInputStream fis = new FileInputStream(excelFilePath);
    	     Workbook workbook = new XSSFWorkbook(fis)) { 

    	    Sheet sheet = workbook.getSheet("HocPhans"); 
    	    
    	    this.hps = new ArrayList<HocPhan>();

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

    	        HocPhan hocPhan = new HocPhan(hocPhanID, tenHocPhan, maHocPhan, mota, khoaID, loaiHocPhan, soTinChi);
    	        hps.add(hocPhan);
    	    }

    	} catch (Exception e) {
    	    e.printStackTrace();
    	}	
    	
		
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

	public String getExcelFilePath() {
		return excelFilePath;
	}

	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}

	public ArrayList<HocPhan> getHps() {
		return hps;
	}

	public void setHps(ArrayList<HocPhan> hps) {
		this.hps = hps;
	}
    
}
