package test;

import com.ab.entity.StudentInfo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtility;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionMappingInsertTest {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtility.getSessionFactory();
        Session session = HibernateUtility.getSession();
        Transaction transaction = null;

        try(factory;session) {
            transaction = session.beginTransaction();
            StudentInfo studentInfo1 = new StudentInfo();
            studentInfo1.setSname("Abhishek");
            studentInfo1.setFriends(List.of("Suresh", "Ramesh", "Mahesh", "Raja"));
            studentInfo1.setVisitedPlaces(List.of("Jaipur","Agra","Delhi","Udaipur"));
            studentInfo1.setPhoneNumbers(Set.of("9828838807","12349890"));
            studentInfo1.setIdCertificates(Map.of("adharNo",234567));

            session.save(studentInfo1);

            transaction.commit();
        }catch (HibernateException he){
            he.printStackTrace();
            if(transaction != null || transaction.getStatus() !=null ||transaction.getRollbackOnly()){
                transaction.rollback();
                System.out.println("Problem in saving object");
            }
        }
    }
}
