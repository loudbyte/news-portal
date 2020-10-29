package com.epam.portal.repository;

import com.epam.portal.entity.News;
import com.sun.istack.internal.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(News.class)
            .buildSessionFactory();

    @Override
    public News getNewsById(@NotNull final long id) {
        try (final Session currentSession = sessionFactory.getCurrentSession()) {

            currentSession.beginTransaction();
            News news = currentSession.get(News.class, id);
            currentSession.getTransaction().commit();
            currentSession.close();

            return news;
        }
    }

    @Override
    public News saveOrUpdateNews(@NotNull final News news) {
        try (final Session currentSession = sessionFactory.getCurrentSession()) {

            currentSession.beginTransaction();
            currentSession.saveOrUpdate(news);
            currentSession.getTransaction().commit();
            currentSession.close();

            return news;
        }
    }

    @Override
    public List<News> getAllNews() {
        try (final Session currentSession = sessionFactory.getCurrentSession()) {

            currentSession.beginTransaction();

            Query<News> query = currentSession.createQuery("FROM News", News.class);
            List<News> newsList = query.getResultList();

            currentSession.getTransaction().commit();
            currentSession.close();

            return newsList;
        }
    }

    @Override
    public void deleteNews(@NotNull final long id) {
        try (final Session currentSession = sessionFactory.getCurrentSession()) {

            currentSession.beginTransaction();

            News news = new News();
            news.setId(id);

            currentSession.delete(news);
            currentSession.getTransaction().commit();
            currentSession.close();
        }
    }
}
