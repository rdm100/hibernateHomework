package db;

import models.Book;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBBook {

    private static Session session;
    private static Transaction transaction;

    public static void save(Book book){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    public static List<Book> getBooks(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Book> results = null;
        try {
            String hql = "from Books";
            results = session.createQuery(hql).list();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static void delete(Book book){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            session.delete(book);
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static Book find(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        Book result = null;
        try{
            String hql = "from Book where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            result = (Book) query.uniqueResult();
        } catch (HibernateException e ){
            e.printStackTrace();
        } finally {
            session.close();
        } return result;

    }

    public static void update(Book book){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}
