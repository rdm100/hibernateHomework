package db;


import models.Author;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBAuthor {

    private static Session session;
    private static Transaction transaction;

    public static void save(Author author){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    public static List<Author> getAuthors(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Author> results = null;
        try {
            String hql = "from Authors";
            results = session.createQuery(hql).list();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static void delete(Author author){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            session.delete(author);
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static Author find(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        Author result = null;
        try{
            String hql = "from Author where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            result = (Author) query.uniqueResult();
        } catch (HibernateException e ){
            e.printStackTrace();
        } finally {
            session.close();
        } return result;

    }

    public static void update(Author author){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(author);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
