-- Xóa dữ liệu trong bảng HocPhanTrongKhoaHoc
Drop table HocPhanTrongKhoaHoc;

-- Xóa dữ liệu trong bảng KhoaHoc
Drop table KhoaHoc;

-- Xóa dữ liệu trong bảng HocPhan
Drop table HocPhan

-- Xóa dữ liệu trong bảng ChuyenNganh
Drop table ChuyenNganh;

-- Xóa dữ liệu trong bảng Nganh
Drop table Nganh;

-- Xóa dữ liệu trong bảng Khoa
Drop table Khoa;

-- Xóa dữ liệu trong bảng Khoa
Drop table UserAccount;

-- Bảng Khoa
CREATE TABLE Khoa (
    KhoaID INT IDENTITY(1,1) PRIMARY KEY,
    TenKhoa NVARCHAR(255),
    MoTa NVARCHAR(MAX),
    -- Khoa
);

-- Bảng Chuyên Ngành
CREATE TABLE Nganh (
    NganhID INT IDENTITY(1,1) PRIMARY KEY,
    TenNganh NVARCHAR(255),
    MoTa NVARCHAR(MAX),
	KhoaID INT,
	FOREIGN KEY (KhoaID) REFERENCES Khoa(KhoaID)

    -- Chuyên ngành
);

-- Bảng Chuyên Ngành
CREATE TABLE ChuyenNganh (
    ChuyenNganhID INT IDENTITY(1,1) PRIMARY KEY,
    TenChuyenNganh NVARCHAR(255),
    MoTa NVARCHAR(MAX),
	NganhID INT,
	FOREIGN KEY (NganhID) REFERENCES Nganh(NganhID)
    -- Chuyên ngành
);


-- Bảng Học Phần
CREATE TABLE HocPhan (
    HocPhanID INT IDENTITY(1,1) PRIMARY KEY,
    TenHocPhan NVARCHAR(255),
    MaHocPhan NVARCHAR(50),
    MoTa NVARCHAR(MAX),
    KhoaID INT,
    LoaiHocPhan NVARCHAR(50),
    SoTinChi INT,
	FOREIGN KEY (KhoaID) REFERENCES Khoa(KhoaID)
    -- Học phần
);



-- Bảng Khóa Học
CREATE TABLE KhoaHoc (
    KhoaHocID INT IDENTITY(1,1) PRIMARY KEY,
    TenKhoaHoc NVARCHAR(255),
    KhoaID INT,
    ChuyenNganhID INT,
    NamHoc INT,
	FOREIGN KEY (KhoaID) REFERENCES Khoa(KhoaID),
    FOREIGN KEY (ChuyenNganhID) REFERENCES ChuyenNganh(ChuyenNganhID)
    -- Khóa học
);

-- Bảng Học Phần Trong Khóa Học
CREATE TABLE HocPhanTrongKhoaHoc (
    HocPhanTrongKhoaHocID INT IDENTITY(1,1) PRIMARY KEY,
    KhoaHocID INT,
    HocPhanID INT,
    HocKy INT,
	FOREIGN KEY (KhoaHocID) REFERENCES KhoaHoc(KhoaHocID),
    FOREIGN KEY (HocPhanID) REFERENCES HocPhan(HocPhanID),
    -- Học phần trong khóa học
);

CREATE TABLE UserAccount (
    UserAccountID INT IDENTITY(1,1) PRIMARY KEY,
	username NVARCHAR(255) UNIQUE,
	password NVARCHAR(255)
);

-- Thêm dữ liệu vào bảng Khoa
INSERT INTO Khoa (TenKhoa, MoTa)
VALUES
    (N'Khoa Khoa Học Máy Tính', N'Khoa chuyên về công nghệ thông tin và máy tính.'),
    (N'Khoa Kinh Tế', N'Khoa chuyên về kinh tế và quản trị kinh doanh.'),
    (N'Khoa Ngoại Ngữ', N'Khoa chuyên về ngoại ngữ và văn hóa quốc tế.');

-- Thêm dữ liệu vào bảng Nganh
INSERT INTO Nganh (TenNganh, MoTa, KhoaID)
VALUES
    (N'Ngành Khoa Học Máy Tính', N'Ngành chuyên về công nghệ thông tin và máy tính.', 1),
    (N'Ngành Kinh Tế', N'Ngành chuyên về kinh tế và quản trị kinh doanh.', 2),
    (N'Ngành Ngoại Ngữ', N'Ngành chuyên về ngoại ngữ và văn hóa quốc tế.', 3);

-- Thêm dữ liệu cho các chuyên ngành cụ thể
INSERT INTO ChuyenNganh (TenChuyenNganh, MoTa, NganhID)
VALUES
    (N'Lập Trình Ứng Dụng Di Động', N'Chuyên ngành về phát triển ứng dụng di động.', 1),
    (N'Kế Toán', N'Chuyên ngành về kế toán và tài chính.', 2),
    (N'Ngôn Ngữ Anh', N'Chuyên ngành về ngôn ngữ Anh và văn hóa Anh Quốc.', 3);

-- Thêm dữ liệu vào bảng Học Phần
INSERT INTO HocPhan (TenHocPhan, MaHocPhan, MoTa, KhoaID, LoaiHocPhan, SoTinChi)
VALUES
    (N'Lập Trình C++', N'CSCI101', N'Khóa học lập trình C++ cơ bản.', 1, N'Bắt Buộc', 3),
    (N'Kinh Tế Mikro', N'ECON101', N'Khóa học về kinh tế mikro cơ bản.', 2, N'Bắt Buộc', 4),
    (N'Tiếng Anh Giao Tiếp', N'ENGL101', N'Khóa học về tiếng Anh giao tiếp.', 3, N'Bắt Buộc', 2);



-- Thêm dữ liệu vào bảng Khóa Học
INSERT INTO KhoaHoc (TenKhoaHoc, KhoaID, ChuyenNganhID, NamHoc)
VALUES
    (N'Khóa 2020', 1, 1, 2020),-- Khóa 2020: Khoa Khoa Học Máy Tính, Chuyên ngành Lập Trình Ứng Dụng Di Động
    (N'Khóa 2021', 2, 2, 2021),
    (N'Khóa 2022', 3, 3, 2022);

-- Thêm dữ liệu vào bảng Học Phần Trong Khóa Học
INSERT INTO HocPhanTrongKhoaHoc (KhoaHocID, HocPhanID, HocKy)
VALUES
    (1, 1, 1), -- Khóa 2020: Lập Trình C++, Học kỳ 1
    (1, 2, 2), -- Khóa 2020: Kinh Tế Mikro, Học kỳ 2
    (2, 3, 1), -- Khóa 2021: Tiếng Anh Giao Tiếp, Học kỳ 1
    (2, 1, 2), -- Khóa 2021: Lập Trình C++, Học kỳ 2
    (3, 2, 1), -- Khóa 2022: Kinh Tế Mikro, Học kỳ 1
    (3, 3, 4); -- Khóa 2022: Tiếng Anh Giao Tiếp, Học kỳ 4

INSERT INTO UserAccount (username, password)
VALUES ('account1', '123456'),
       ('account2', '123456');