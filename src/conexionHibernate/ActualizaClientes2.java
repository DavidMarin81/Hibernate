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

public class ActualizaClientes2 {

    public static void main(String[] args) {
        SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();
        Session miSession = miFactory.openSession();
        
        try{
            miSession.beginTransaction();
            
            //Se usa HQL
            //Se cambian los apellidos que empiezan por c por marin
            miSession.createQuery("update Clientes set apellidos='cid' where "
                    + "apellidos LIKE 'm%'").executeUpdate();
            
            miSession.getTransaction().commit();
            System.out.println("Registros actualizados correctamente");
            
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
