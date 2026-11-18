package com.library.dao;

import com.library.entity.*;
import com.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PhieuMuonDAO {
    public List<ChiTietPhieuMuon> getAllChiTiet() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM ChiTietPhieuMuon", ChiTietPhieuMuon.class).list();
        }
    }

    public boolean muonSach(PhieuMuon pm, ChiTietPhieuMuon ct) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Sach sach = session.get(Sach.class, ct.getId().getMaSach());
            if (sach == null || sach.getSoLuongTon() <= 0) return false;

            sach.setSoLuongTon(sach.getSoLuongTon() - 1);
            session.update(sach);
            session.saveOrUpdate(pm);
            session.save(ct);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean traSach(String maPhieu, String maSach) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            ChiTietPhieuMuonId id = new ChiTietPhieuMuonId(maPhieu, maSach);
            ChiTietPhieuMuon ct = session.get(ChiTietPhieuMuon.class, id);
            if (ct == null || "Đã trả".equals(ct.getTrangThai())) return false;

            Date homNay = new Date();
            ct.setNgayTraThucTe(homNay);
            ct.setTrangThai("Đã trả");

            if (homNay.after(ct.getNgayHenTra())) {
                long diff = homNay.getTime() - ct.getNgayHenTra().getTime();
                long soNgayTre = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                ct.setTienPhat(BigDecimal.valueOf(soNgayTre * 5000));
            }

            Sach sach = session.get(Sach.class, maSach);
            sach.setSoLuongTon(sach.getSoLuongTon() + 1);

            session.update(ct);
            session.update(sach);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
