package com.jupan.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jupan.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

    // create session
    Session session = factory.getCurrentSession();

    try {     
      // create a 3 student objects
      System.out.println("Creating 3 new student objects...");
      Student tempStudent1 = new Student("Ju", "Pan", "pjokk722@gmail.com");
      Student tempStudent2 = new Student("Yao", "Ming", "yaoming@gmail.com");
      Student tempStudent3 = new Student("Zhou", "Qi", "zhouqi@gmail.com");

      // start a transaction
      session.beginTransaction();

      // save the student object
      System.out.println("Saving the students...");
      session.save(tempStudent1);
      session.save(tempStudent2);
      session.save(tempStudent3);
      
      // commit transaction
      session.getTransaction().commit();

      System.out.println("Done!");
    } finally {
      factory.close();
    }
    
  }

}
