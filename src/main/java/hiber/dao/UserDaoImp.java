package hiber.dao;




import hiber.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      String hql = "FROM User user WHERE user.car.model = :model AND user.car.series = :series";
      TypedQuery<User> q = sessionFactory.getCurrentSession().createQuery(hql
              );
              q.setParameter("model", model);
              q.setParameter("series", series);
      return q.getSingleResult();
   }

}
