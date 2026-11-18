HƯỚNG DẪN CÀI ĐẶT VÀ CHẠY DỰ ÁN QUẢN LÝ THƯ VIỆN
=================================================
1. Cấu trúc thư mục:
   - Database_SQL/QuanLyThuVien.sql : File chạy tạo CSDL và dữ liệu mẫu trên SQL Server.
   - BaoCao_Word/BaoCao_QuanLyThuVien.docx : File báo cáo đồ án chuẩn chỉnh.
   - QuanLyThuVien_SourceCode/ : Project Java Maven (NetBeans / IntelliJ / Eclipse).

2. Các bước chạy chương trình:
   - Bước 1: Mở SQL Server Management Studio (SSMS), mở file QuanLyThuVien.sql và nhấn Execute.
   - Bước 2: Mở thư mục QuanLyThuVien_SourceCode bằng NetBeans (File -> Open Project).
   - Bước 3: Mở file src/main/resources/hibernate.cfg.xml, sửa lại user/password SQL Server của máy bạn (mặc định đang để sa / 123456).
   - Bước 4: Chuột phải vào project chọn Build with Dependencies để Maven tải thư viện Hibernate & JDBC.
   - Bước 5: Chạy file MainFrame.java để khởi động phần mềm.
