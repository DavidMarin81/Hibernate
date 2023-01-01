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

public class ActualizaClientes {

    public static void main(String[] args) {
        SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();
        Session miSession = miFactory.openSession();
        
        try{
            // Id del cliente que vamos a actualizar
            int clienteId = 4;
            miSession.beginTransaction();
            
            //Primer parametro = clase a la que pertenece el objeto
            //Segundo parametro = el id del cliente que queremos actualizar
            Clientes miCliente = miSession.get(Clientes.class, clienteId);
            //Usamos el setter para modificar el nombre
            miCliente.setNombre("Patricia");
            miCliente.setApellidos("Cid");
            miCliente.setDireccion("Coia");
            
            miSession.getTransaction().commit();
            System.out.println("Registro actualizado correctamente");
            
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
