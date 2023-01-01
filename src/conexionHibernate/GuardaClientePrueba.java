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
        comprobarConexion();
        
        SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();
        Session miSession = miFactory.openSession();
        
        try{
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
    
        public static void comprobarConexion() {
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;database=pruebasHibernate;encrypt=true;trustServerCertificate=true";
        String usuario = "user";
        String password = "abc123.";
        try {
            System.out.println("Intentando conectar con la base de datos");
            Connection miConexion = DriverManager.getConnection(jdbcUrl, usuario, password);
            System.out.println("Conexion exitosa");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
