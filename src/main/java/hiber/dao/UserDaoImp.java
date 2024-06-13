package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("from User").list();
   }

   @Override
   public User getUserByCarModelSeries(String model, int series) {
      Query query = sessionFactory.getCurrentSession().createQuery("from User user where user.car.model = :model and user.car.series = :series");
      query
              .setParameter("model", model)
              .setParameter("series", series);
      return (User) query.getSingleResult();
   }
}
