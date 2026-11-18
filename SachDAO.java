package com.library.dao;

import com.library.entity.Sach;
import com.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class SachDAO {
    public List<Sach> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Sach", Sach.class).list();
        }
    }

    public List<Sach> search(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Sach WHERE tenSach LIKE :kw OR tacGia LIKE :kw OR theLoai LIKE :kw";
            return session.createQuery(hql, Sach.class)
                    .setParameter("kw", "%" + keyword + "%")
                    .list();
        }
    }

    public boolean saveOrUpdate(Sach s) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(s);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String maSach) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Sach s = session.get(Sach.class, maSach);
            if (s != null) session.delete(s);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }
}
