package com.epam.portal.repository;

import com.epam.portal.entity.News;
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
    public News getNewsById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.beginTransaction();

        News news = currentSession.get(News.class, id);

        currentSession.getTransaction().commit();

        currentSession.close();

        return news;
    }

    @Override
    public long saveOrUpdateNews(News news) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.beginTransaction();

        currentSession.saveOrUpdate(news);

        currentSession.getTransaction().commit();
        currentSession.close();

        return news.getId();
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

}
