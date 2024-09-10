package com.phuoc.MySQL;


import java.util.ArrayList;
import java.sql.*;

import javax.swing.JOptionPane;

import com.phuoc.models.ChuyenNganh;
import com.phuoc.models.HocPhan;
import com.phuoc.models.HocPhanTrongKhoaHoc;
import com.phuoc.models.Khoa;
import com.phuoc.models.KhoaHoc;
import com.phuoc.models.Nganh;
import com.phuoc.models.ThongTinChiNhanh;
import com.phuoc.models.UserAccount;

/**
 *
 * @author Hide in home
 */
public class ConnectSQL {
	private Connection cn;


	/*
	 * public static void main(String[] args) {
	 * 
	 * ConnectSQL c=new ConnectSQL(); c.DeleteNhanVien("0"); c.DeleteKhachHang("0");
	 * c.DeleteBienLai("0"); c.InsertNhanVien(new NhanVien("0", "Phuoc",
	 * "0120121312", "Hue")); c.InsertKhachHang(new KhachHang("0", "Phuoc",
	 * "0120121312", "Hue", "0")); c.InsertBienLai(new BienLai("0", "0", "0", 0, 10,
	 * 10));
	 * 
	 * c.UpdateNhanVien(new NhanVien("0", "Phuoca", "0120121312", "Hue"));
	 * c.UpdateKhachHang(new KhachHang("0", "Phuocb", "0120121312", "Hue", "0"));
	 * c.UpdateBienLai(new BienLai("0", "0", "0", 0, 11, 10));
	 * 
	 * ArrayList<NhanVien> nvs=c.GetNhanViens(); ArrayList<KhachHang>
	 * khs=c.GetKhachHangs(); ArrayList<BienLai> bls=c.GetBienLais();
	 * 
	 * 
	 * 
	 * 
	 * 
	 * System.out.println(nvs.get(0).getTenNV());
	 * System.out.println(khs.get(0).getTenKH());
	 * System.out.println(bls.get(0).getDonGia());
	 * 
	 * 
	 * }
	 */
	public static void main(String[] args) {
		System.out.println(0);
		ConnectSQL c=new ConnectSQL();
		try {
			System.out.println(1);
			System.out.println(c.SearchHocPhanTrongKhoaHoc("1"));
			ArrayList<Khoa> k = c.GetKhoas();
		    ArrayList<ChuyenNganh> cn = c.GetChuyenNganhs();
			System.out.println(2);
			System.out.println(k.toString());
			System.out.println(cn.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ConnectSQL() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			cn = DriverManager.getConnection("jdbc:sqlserver://"+ThongTinChiNhanh.HostDatabase+";DatabaseName="+ThongTinChiNhanh.DatabaseName+";encrypt=true;trustServerCertificate=true;", ThongTinChiNhanh.DatabaseLogin, ThongTinChiNhanh.DatabasePassword);
			
		
		} catch (SQLException ex) {
			
			JOptionPane.showMessageDialog(null, "Kết nối thất bại !"+ ex);
			System.out.println(ex);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Không tìm tháy Driver");
		}

	}
	
	public ArrayList<Khoa> GetKhoas() throws SQLException {
		ArrayList<Khoa> arrKhoas = null;
		String sql = "Select * From Khoa";
	    try {
		java.sql.Statement st = cn.createStatement();

		ResultSet rs = st.executeQuery(sql);
		arrKhoas = new ArrayList<Khoa>();
		while (rs.next()) {
			Khoa k = new Khoa(rs.getString(1), rs.getString(2), rs.getString(3));
			arrKhoas.add(k);
		}
	    } catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "getKhoas không thành công!");
		}
		return arrKhoas;
	}

	public ArrayList<HocPhan> GetHocPhans() throws SQLException {
		ArrayList<HocPhan> arrHocPhans = null;
		String sql = "Select * From HocPhan";
	    try {
		java.sql.Statement st = cn.createStatement();

		ResultSet rs = st.executeQuery(sql);
		arrHocPhans = new ArrayList<HocPhan>();
		while (rs.next()) {
			HocPhan hp = new HocPhan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			arrHocPhans.add(hp);
		}
	    } catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "getKhoas không thành công!");
		}
		return arrHocPhans;
	}
	
	public ArrayList<ChuyenNganh> GetChuyenNganhs() throws SQLException {
		ArrayList<ChuyenNganh> arrChuyenNganhs = null;
		String sql = "Select * From ChuyenNganh";
	    try {
		java.sql.Statement st = cn.createStatement();

		ResultSet rs = st.executeQuery(sql);
		arrChuyenNganhs = new ArrayList<ChuyenNganh>();
		while (rs.next()) {
			ChuyenNganh cn = new ChuyenNganh(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			arrChuyenNganhs.add(cn);
		}
	    } catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "getChuyenNganhs không thành công!");
		}
		return arrChuyenNganhs;
	}
	
	public ArrayList<Nganh> GetNganhs() throws SQLException {
		ArrayList<Nganh> arrNganhs = null;
		String sql = "Select * From Nganh";
	    try {
		java.sql.Statement st = cn.createStatement();

		ResultSet rs = st.executeQuery(sql);
		arrNganhs = new ArrayList<Nganh>();
		while (rs.next()) {
			Nganh n = new Nganh(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			arrNganhs.add(n);
		}
	    } catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "getNganhs không thành công!");
		}
		return arrNganhs;
	}
	
	public ArrayList<KhoaHoc> GetKhoaHocs() throws SQLException {
		ArrayList<KhoaHoc> arrKhoaHocs = null;
		String sql = "Select * From KhoaHoc";
	    try {
		java.sql.Statement st = cn.createStatement();

		ResultSet rs = st.executeQuery(sql);
		arrKhoaHocs = new ArrayList<KhoaHoc>();
		while (rs.next()) {
			KhoaHoc kh = new KhoaHoc(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			arrKhoaHocs.add(kh);
		}
	    } catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "getKhoas không thành công!");
		}
		return arrKhoaHocs;
	}
	
	public ArrayList<HocPhanTrongKhoaHoc> GetHocPhanTrongKhoaHocs() throws SQLException {
		ArrayList<HocPhanTrongKhoaHoc> arrHocPhanTrongKhoaHocs = null;
		String sql = "Select * From HocPhanTrongKhoaHoc";
	    try {
		java.sql.Statement st = cn.createStatement();

		ResultSet rs = st.executeQuery(sql);
		arrHocPhanTrongKhoaHocs = new ArrayList<HocPhanTrongKhoaHoc>();
		while (rs.next()) {
			HocPhanTrongKhoaHoc hptkh= new HocPhanTrongKhoaHoc(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			arrHocPhanTrongKhoaHocs.add(hptkh);
		}
	    } catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "getKhoas không thành công!");
		}
		return arrHocPhanTrongKhoaHocs;
	}

	public int UpdateKhoa(Khoa k) {
		int update = 0;
		String sql = "Update Khoa set TenKhoa=?, MoTa=? where KhoaID=?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, k.getTenKhoa());
			ps.setString(2, k.getMota());
			ps.setString(3, k.getKhoaID());
			update = ps.executeUpdate();  

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, update + "Update Khoa không thành công !" + ex);
		}
		return update;
	}
	
	public int UpdateHocPhan(HocPhan k) {
		int update = 0;
		String sql = "Update HocPhan set TenHocPhan=?, MaHocPhan=?, MoTa=?, KhoaID=?, LoaiHocPhan=?, SoTinChi=? where HocPhanID=?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, k.getTenHocPhan());
			ps.setString(2, k.getMaHocPhan());
			ps.setString(3, k.getMota());
			ps.setString(4, k.getKhoaID());
			ps.setString(5, k.getLoaiHocPhan());
			ps.setString(6, k.getSoTinChi());
			ps.setString(7, k.getHocPhanID());
			update = ps.executeUpdate();  
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, update + "Update HocPhan không thành công !" + ex);
		}
		return update;
	}
	
	public int UpdateChuyenNganh(ChuyenNganh k) {
		int update = 0;
		String sql = "Update ChuyenNganh set TenChuyenNganh=?, MoTa=?, NganhID=? where ChuyenNganhID=?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, k.getTenChuyenNganh());
			ps.setString(2, k.getMota());
			ps.setString(3, k.getNganhID());
			ps.setString(4, k.getChuyenNganhID());
			update = ps.executeUpdate();  

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, update + "Update ChuyenNganh không thành công !" + ex);
		}
		return update;
	}
	
	public int UpdateNganh(Nganh k) {
		int update = 0;
		String sql = "Update Nganh set TenNganh=?, MoTa=?, KhoaID=? where NganhID=?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, k.getTenNganh());
			ps.setString(2, k.getMota());
			ps.setString(3, k.getKhoaID());
			ps.setString(4, k.getNganhID());
			update = ps.executeUpdate();  

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, update + "Update Nganh không thành công !" + ex);
		}
		return update;
	}
	
	
	public int UpdateKhoaHoc(KhoaHoc k) {
		int update = 0;
		String sql = "Update KhoaHoc set TenKhoaHoc=?, KhoaID=?, ChuyenNganhID=?, NamHoc=? where KhoaHocID=?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, k.getTenKhoaHoc());
			ps.setString(2, k.getKhoaID());
			ps.setString(3, k.getChuyenNganhID());
			ps.setString(4, k.getNamHoc());
			ps.setString(5, k.getKhoaHocID());
			update = ps.executeUpdate();  
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, update + "Update KhoaHoc không thành công !" + ex);
		}
		return update;
	}
	
	public int UpdateHocPhanTrongKhoaHoc(HocPhanTrongKhoaHoc k) {
		int update = 0;
		String sql = "Update HocPhanTrongKhoaHoc set KhoaHocID=?, HocPhanID=?, HocKy=? where HocPhanTrongKhoaHocID=?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, k.getKhoaHocID());
			ps.setString(2, k.getHocPhanID());
			ps.setString(3, k.getHocKy());
			ps.setString(4, k.getHocPhanTrongKhoaHocID());
			update = ps.executeUpdate();  
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, update + "Update HocPhanTrongKhoaHoc không thành công !" + ex);
		}
		return update;
	}
	
	public int InsertKhoa(Khoa k) {
		int insert = 0;
		String sql = "INSERT INTO Khoa (TenKhoa, MoTa) VALUES(?, ?)";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, k.getTenKhoa());
			ps.setString(2, k.getMota());
			insert = ps.executeUpdate();  
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Insert Khoa thất bại!" + ex);
		}
		return insert;
	}
	
	public int InsertHocPhan(HocPhan k) {
		int insert = 0;
		String sql = "INSERT INTO HocPhan (TenHocPhan, MaHocPhan, MoTa, KhoaID, LoaiHocPhan, SoTinChi) VALUES(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, k.getTenHocPhan());
			ps.setString(2, k.getMaHocPhan());
			ps.setString(3, k.getMota());
			ps.setString(4, k.getKhoaID());
			ps.setString(5, k.getLoaiHocPhan());
			ps.setString(6, k.getSoTinChi());
			insert = ps.executeUpdate();  
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Insert HocPhan thất bại!" + ex);
		}
		return insert;
	}
	
	public int InsertChuyenNganh(ChuyenNganh k) {
		int insert = 0;
		String sql = "INSERT INTO ChuyenNganh (TenChuyenNganh, MoTa, NganhID) VALUES(?, ?, ?)";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, k.getTenChuyenNganh());
			ps.setString(2, k.getMota());
			ps.setString(3, k.getNganhID());
			insert = ps.executeUpdate();  
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Insert ChuyenNganh thất bại!" + ex);
		}
		return insert;
	}
	
	public int InsertNganh(Nganh k) {
		int insert = 0;
		String sql = "INSERT INTO Nganh (TenNganh, MoTa, KhoaID) VALUES(?, ?, ?)";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, k.getTenNganh());
			ps.setString(2, k.getMota());
			ps.setString(3, k.getKhoaID());
			insert = ps.executeUpdate();  
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Insert Nganh thất bại!" + ex);
		}
		return insert;
	}
	
	public int InsertKhoaHoc(KhoaHoc k) {
		int insert = 0;
		String sql = "INSERT INTO KhoaHoc (TenKhoaHoc, KhoaID, ChuyenNganhID, NamHoc) VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, k.getTenKhoaHoc());
			ps.setString(2, k.getKhoaID());
			ps.setString(3, k.getChuyenNganhID());
			ps.setString(4, k.getNamHoc());
			insert = ps.executeUpdate();  
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Insert Khoa Hoc thất bại!" + ex);
		}
		return insert;
	}
	
	public int InsertHocPhanTrongKhoaHoc(HocPhanTrongKhoaHoc k) {
		int insert = 0;
		String sql = "INSERT INTO HocPhanTrongKhoaHoc (KhoaHocID, HocPhanID, HocKy) VALUES(?, ?, ?)";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, k.getKhoaHocID());
			ps.setString(2, k.getHocPhanID());
			ps.setString(3, k.getHocKy());
			insert = ps.executeUpdate();   
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Insert HocPhanTrongKhoaHoc thất bại!" + ex);
		}
		return insert;
	}
	
	public int DeleteKhoa(String KhoaID) {
		int check = 0;
		String sql = "Delete From Khoa Where KhoaID = ?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, KhoaID);
			check = ps.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Delete thất bại!" + ex);
		}
		return check;
	}
	
	public int DeleteHocPhan(String HocPhanID) {
		int check = 0;
		String sql = "Delete From HocPhan Where HocPhanID = ?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, HocPhanID);
			check = ps.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Delete thất bại!" + ex);
		}
		return check;
	}
	
	public int DeleteNganh(String NganhID) {
		int check = 0;
		String sql = "Delete From Nganh Where NganhID = ?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, NganhID);
			check = ps.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Delete thất bại!" + ex);
		}
		return check;
	}
	
	public int DeleteChuyenNganh(String ChuyenNganhID) {
		int check = 0;
		String sql = "Delete From ChuyenNganh Where ChuyenNganhID = ?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, ChuyenNganhID);
			check = ps.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Delete thất bại!" + ex);
		}
		return check;
	}
	
	public int DeleteKhoaHoc(String KhoaHocID) {
		int check = 0;
		String sql = "Delete From KhoaHoc Where KhoaHocID = ?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, KhoaHocID);
			check = ps.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Delete thất bại!" + ex);
		}
		return check;
	}
	
	public int DeleteHocPhanTrongKhoaHoc(String HocPhanTrongKhoaHocID) {
		int check = 0;
		String sql = "Delete From HocPhanTrongKhoaHoc Where HocPhanTrongKhoaHocID = ?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, HocPhanTrongKhoaHocID);
			check = ps.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Delete thất bại!" + ex);
		}
		return check;
	}
	
	public ArrayList<Khoa> SearchKhoa(String text) throws SQLException {

		ArrayList<Khoa> arrKhoas = null;
		String sql = "Select * From Khoa Where KhoaID LIKE ? or MoTa LIKE ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, "%" + text + "%");
		ps.setString(2, "%" + text + "%");
		ResultSet rs = ps.executeQuery();
	    arrKhoas = new ArrayList<Khoa>();
	    while (rs.next()) {
	    	Khoa k = new Khoa(rs.getString(1), rs.getString(2), rs.getString(3));
	    	System.out.println(k.toString());
	    	arrKhoas.add(k);
	    }
		return arrKhoas;
	}
	
	public ArrayList<HocPhan> SearchHocPhan(String text) throws SQLException {

		ArrayList<HocPhan> arrHocPhans = null;
		String sql = "Select * From HocPhan Where HocPhanID LIKE ? or MoTa LIKE ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, "%" + text + "%");
		ps.setString(2, "%" + text + "%");
		ResultSet rs = ps.executeQuery();
	    arrHocPhans = new ArrayList<HocPhan>();
	    while (rs.next()) {
	    	HocPhan k = new HocPhan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
	    	System.out.println(k.toString());
	    	arrHocPhans.add(k);
	    }
		return arrHocPhans;
	}
	
	public ArrayList<ChuyenNganh> SearchChuyenNganh(String text) throws SQLException {

		ArrayList<ChuyenNganh> arrChuyenNganhs = null;
		String sql = "Select * From ChuyenNganh Where ChuyenNganhID LIKE ? or MoTa LIKE ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, "%" + text + "%");
		ps.setString(2, "%" + text + "%");
		ResultSet rs = ps.executeQuery();
	    arrChuyenNganhs = new ArrayList<ChuyenNganh>();
	    while (rs.next()) {
	    	ChuyenNganh k = new ChuyenNganh(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
	    	System.out.println(k.toString());
	    	arrChuyenNganhs.add(k);
	    }
		return arrChuyenNganhs;
	}
	
	public ArrayList<Nganh> SearchNganh(String text) throws SQLException {

		ArrayList<Nganh> arrNganhs = null;
		String sql = "Select * From Nganh Where NganhID LIKE ? or MoTa LIKE ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, "%" + text + "%");
		ps.setString(2, "%" + text + "%");
		ResultSet rs = ps.executeQuery();
	    arrNganhs = new ArrayList<Nganh>();
	    while (rs.next()) {
	    	Nganh k = new Nganh(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
	    	System.out.println(k.toString());
	    	arrNganhs.add(k);
	    }
		return arrNganhs;
	}
	
	public ArrayList<KhoaHoc> SearchKhoaHoc(String text) throws SQLException {

		ArrayList<KhoaHoc> arrKhoaHocs = null;
		String sql = "Select * From KhoaHoc Where KhoaHocID LIKE ? or TenKhoaHoc LIKE ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, "%" + text + "%");
		ps.setString(2, "%" + text + "%");
		ResultSet rs = ps.executeQuery();
	    arrKhoaHocs = new ArrayList<KhoaHoc>();
	    while (rs.next()) {
	    	KhoaHoc k = new KhoaHoc(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
	    	System.out.println(k.toString());
	    	arrKhoaHocs.add(k);
	    }
		return arrKhoaHocs;
	}

	public ArrayList<HocPhanTrongKhoaHoc> SearchHocPhanTrongKhoaHoc(String text) throws SQLException {

		ArrayList<HocPhanTrongKhoaHoc> arrHocPhanTrongKhoaHocs = null;
		String sql = "Select * From HocPhanTrongKhoaHoc Where HocPhanTrongKhoaHocID LIKE ? or HocKy LIKE ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setString(1, "%" + text + "%");
		ps.setString(2, "%" + text + "%");
		ResultSet rs = ps.executeQuery();
	    arrHocPhanTrongKhoaHocs = new ArrayList<HocPhanTrongKhoaHoc>();
	    while (rs.next()) {
	    	HocPhanTrongKhoaHoc k = new HocPhanTrongKhoaHoc(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
	    	System.out.println(k.toString());
	    	arrHocPhanTrongKhoaHocs.add(k);
	    }
		return arrHocPhanTrongKhoaHocs;
	}
	
	//UserAccountID > 0
	public int GetUserAccountIDByUserAccount(UserAccount ua) throws SQLException {
		
		String sql = "Select * From useraccount where username='" + ua.getUsername() +"' and password = '"+ua.getPassword()+"'";
		try {
		java.sql.Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi !" + ex);	
			return 0;
		}
	}
	


	
	

}
