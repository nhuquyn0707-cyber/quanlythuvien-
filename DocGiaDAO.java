package com.library.dao;

import com.library.entity.DocGia;
import com.library.entity.NhanVien;
import com.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DocGiaDAO {
    public List<DocGia> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM DocGia", DocGia.class).list();
        }
    }

    public List<NhanVien> getAllNhanVien() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM NhanVien", NhanVien.class).list();
        }
    }

    public List<DocGia> search(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM DocGia WHERE hoTen LIKE :kw OR soDienThoai LIKE :kw", DocGia.class)
                    .setParameter("kw", "%" + keyword + "%")
                    .list();
        }
    }

    public boolean saveOrUpdate(DocGia dg) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(dg);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    public boolean delete(String maDG) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            DocGia dg = session.get(DocGia.class, maDG);
            if (dg != null) session.delete(dg);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }
}
