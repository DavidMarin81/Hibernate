/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionHibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GuardaClientePrueba {

    public static void main(String[] args) {
        SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();
        Session miSession = miFactory.openSession();
        
        try{
            //Se crea un objeto cliente y se inserta en la BBDD
            Clientes cliente1 = new Clientes("Patricia", "Cid", "Coia");
            miSession.beginTransaction();
            miSession.save(cliente1);
            miSession.getTransaction().commit();
            System.out.println("Registro insertado correctamente");
            
            //Lectura de registro
            miSession.beginTransaction();
            System.out.println("Lectura del ID: " + cliente1.getId());
            
            miSession.close();
        }finally{
            miFactory.close();
        }
        
    }
    
}
