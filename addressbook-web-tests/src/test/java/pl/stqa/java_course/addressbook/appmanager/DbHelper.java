package pl.stqa.java_course.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.stqa.java_course.addressbook.model.ContactData;
import pl.stqa.java_course.addressbook.model.Contacts;
import pl.stqa.java_course.addressbook.model.GroupData;
import pl.stqa.java_course.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper (){

      // A SessionFactory is set up once for an application!
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups (){
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<GroupData> result = session.createQuery( "from GroupData" ).list();
      session.getTransaction().commit();
      session.close();
      return new Groups(result);
    }

    public Contacts contacts() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
      session.getTransaction().commit();
      session.close();
      return new Contacts (result);

    }

  public int getContactListSize() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    int result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list().size();
    session.close();
    return result;

  }

  public ContactData getContact(Integer id) {
    Session session=sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result =session.createQuery("from ContactData where id=:id").setParameter("id",id).list();
    session.getTransaction().commit();
    session.close();
    return result.get(0);
  }

  public GroupData getGroup(Integer id) {
    Session session=sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result =session.createQuery("from GroupData where id=:id").setParameter("id",id).list();
    session.getTransaction().commit();
    session.close();
    return result.get(0);
  }

}
