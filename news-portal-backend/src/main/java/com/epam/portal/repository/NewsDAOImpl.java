package com.epam.portal.repository;

import com.epam.portal.entity.News;
import com.epam.portal.entity.Role;
import com.epam.portal.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(News.class)
//            .addAnnotatedClass(Role.class)
//            .addAnnotatedClass(User.class)
            .buildSessionFactory();

    @Override
    public News getNewsById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.beginTransaction();

        News news = currentSession.get(News.class, id);

        currentSession.getTransaction().commit();

        currentSession.close();

        return news;
    }

    @Override
    public News saveOrUpdateNews(News news) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.beginTransaction();

        currentSession.saveOrUpdate(news);

        currentSession.getTransaction().commit();
        currentSession.close();

        return news;
    }

    @Override
    public List<News> getAllNews() {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.beginTransaction();

        Query<News> query = currentSession.createQuery("FROM News", News.class);
        List<News> newsList = query.getResultList();

        currentSession.getTransaction().commit();
        currentSession.close();

        return newsList;

    }

    @Override
    public void deleteNews(long id) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.beginTransaction();

        News news = new News();
        news.setId(id);

        currentSession.delete(news);
        currentSession.getTransaction().commit();
        currentSession.close();

    }


    public static void main(String[] args) {
        Role role = new Role("TEST", new ArrayList<User>());
        role.setId(2L);
        System.out.println("role id: " + role.getId());
        User user = new User("TEST", "TEST", 2L, role);
        NewsDAOImpl newsDAO = new NewsDAOImpl();
//        newsDAO.saveOrUpdateUser(user);
//        newsDAO.saveOrUpdateRole(role);
        System.out.println(newsDAO.getAllRoles());
//        System.out.println(newsDAO.getAllUsers());
//        System.out.println(newsDAO.getAllUsers().get(0).getRole().getName());


    }


    public long saveOrUpdateUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();


        currentSession.beginTransaction();

        currentSession.saveOrUpdate(user);

        currentSession.getTransaction().commit();
        currentSession.close();

        return user.getId();
    }


    public List<User> getAllUsers() {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.beginTransaction();

        Query<User> query = currentSession.createQuery("FROM User", User.class);
        List<User> newsList = query.getResultList();

        currentSession.getTransaction().commit();
        currentSession.close();

        return newsList;

    }


    public long saveOrUpdateRole(Role user) {
        Session currentSession = sessionFactory.getCurrentSession();


        currentSession.beginTransaction();

        currentSession.saveOrUpdate(user);

        currentSession.getTransaction().commit();
        currentSession.close();

        return user.getId();
    }

    public List<Role> getAllRoles() {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.beginTransaction();

        Query<Role> query = currentSession.createQuery("FROM Role", Role.class);
        List<Role> newsList = query.getResultList();

        currentSession.getTransaction().commit();
        currentSession.close();

        return newsList;

    }



}
