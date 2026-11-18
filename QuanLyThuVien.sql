CREATE DATABASE QuanLyThuVien;
GO
USE QuanLyThuVien;
GO

CREATE TABLE NhanVien (
    MaNV VARCHAR(10) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    NgaySinh DATE NULL,
    SoDienThoai VARCHAR(15) UNIQUE NOT NULL,
    Email VARCHAR(100) UNIQUE,
    ChucVu NVARCHAR(50) NOT NULL
);

CREATE TABLE DocGia (
    MaDG VARCHAR(10) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    NgaySinh DATE NULL,
    DiaChi NVARCHAR(200) NULL,
    SoDienThoai VARCHAR(15) UNIQUE,
    NgayTaoThe DATE DEFAULT GETDATE(),
    NgayHetHan DATE NOT NULL
);

CREATE TABLE Sach (
    MaSach VARCHAR(10) PRIMARY KEY,
    TenSach NVARCHAR(200) NOT NULL,
    TacGia NVARCHAR(100) NOT NULL,
    TheLoai NVARCHAR(50) NULL,
    NhaXuatBan NVARCHAR(100) NULL,
    NamXuatBan INT CHECK (NamXuatBan > 1900),
    SoLuongTon INT DEFAULT 0 CHECK (SoLuongTon >= 0),
    DonGia DECIMAL(18,2) CHECK (DonGia >= 0)
);

CREATE TABLE PhieuMuon (
    MaPhieu VARCHAR(10) PRIMARY KEY,
    MaDG VARCHAR(10) NOT NULL FOREIGN KEY REFERENCES DocGia(MaDG),
    MaNV VARCHAR(10) NOT NULL FOREIGN KEY REFERENCES NhanVien(MaNV),
    NgayMuon DATE DEFAULT GETDATE()
);

CREATE TABLE ChiTietPhieuMuon (
    MaPhieu VARCHAR(10) FOREIGN KEY REFERENCES PhieuMuon(MaPhieu),
    MaSach VARCHAR(10) FOREIGN KEY REFERENCES Sach(MaSach),
    NgayHenTra DATE NOT NULL,
    NgayTraThucTe DATE NULL,
    TrangThai NVARCHAR(30) DEFAULT N'Đang mượn',
    TienPhat DECIMAL(18,2) DEFAULT 0,
    PRIMARY KEY (MaPhieu, MaSach)
);
GO

INSERT INTO NhanVien VALUES 
('NV01', N'Nguyễn Văn An', '1995-05-12', '0901234567', 'an.nv@library.edu.vn', N'Admin'),
('NV02', N'Trần Thị Bình', '1998-08-20', '0912345678', 'binh.tt@library.edu.vn', N'Thủ thư');

INSERT INTO DocGia VALUES 
('DG01', N'Lê Hoàng Nam', '2004-03-15', N'Hà Nội', '0987654321', '2026-01-10', '2027-01-10'),
('DG02', N'Phạm Minh Thư', '2005-11-22', N'Hải Phòng', '0976543210', '2026-02-01', '2027-02-01');

INSERT INTO Sach VALUES 
('S001', N'Lập trình Java cơ bản', N'Phạm Văn Ất', N'Công nghệ thông tin', N'NXB Giáo Dục', 2022, 15, 85000),
('S002', N'Cơ sở dữ liệu SQL Server', N'Nguyễn Thị Lan', N'Công nghệ thông tin', N'NXB Khoa Học', 2023, 10, 110000),
('S003', N'Nhà Giả Kim', N'Paulo Coelho', N'Văn học', N'NXB Hội Nhà Văn', 2020, 20, 79000);

INSERT INTO PhieuMuon VALUES ('PM01', 'DG01', 'NV02', '2026-11-10');
INSERT INTO ChiTietPhieuMuon VALUES ('PM01', 'S001', '2026-11-17', NULL, N'Đang mượn', 0);
GO
