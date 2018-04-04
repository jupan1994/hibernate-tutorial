package com.jupan.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jupan.hibernate.demo.entity.Student;

public class QueryStudentDemo {

  public static void main(String[] args) {

    // create session factory
    SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
    
    // create session
    Session session = factory.getCurrentSession();
    
    try {     
      // start a transaction
      session.beginTransaction();
      
      // query students
      List<Student> theStudents = 
          session.createQuery("from Student").getResultList();
      
      // display students
      displayStudents(theStudents);
      
      // query students: lastName="Pan"
      theStudents = 
          session.createQuery("from Student s where s.lastName='Pan'").getResultList();

      // display the students
      System.out.println("\n\nStudents who have last name of Pan");
      displayStudents(theStudents);
      
      // query students: lastName='Pan' OR firstName='James'
      theStudents = 
          session.createQuery("from Student s where"
              + " s.lastName='Pan' OR s.firstName='James'").getResultList();
      
      // display the students
      System.out.println("\n\nStudents who have last name of Pan OR first name of James");
      displayStudents(theStudents);
      
      // query students where email LIKE '%gmail.com'
      theStudents = session.createQuery("from Student s where"
          + " s.email LIKE '%gmail.com'").getResultList();
      
      // display the students
      System.out.println("\n\nStudents whose email is like 'gmail.com'");
      displayStudents(theStudents);
      
      // commit transaction
      session.getTransaction().commit();
      
      System.out.println("Done!");
    } finally {
      factory.close();
    }
  }

  private static void displayStudents(List<Student> theStudents) {
    for (Student tmpStudent : theStudents) {
      System.out.println(tmpStudent);
    }
  }

}