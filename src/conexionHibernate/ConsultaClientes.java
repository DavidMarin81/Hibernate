/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionHibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author David
 */
public class ConsultaClientes {

    public static void main(String[] args) {

        SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();
        Session miSession = miFactory.openSession();

        try {
            //Comenzar sesion
            miSession.beginTransaction();
            //Consultar clientes
            System.out.println("Mostrar Clientes");
            consultarClientes(miSession);
            //Consulta apellido concreto (SELECT con WHERE)
            System.out.println("Mostrar apellido con WHERE");
            consultarApellido(miSession);
            //Consulta con operadores logicos (AND / OR)
            System.out.println("Mostrar varios datos con WHERE");
            consultasOperadoresLogicos(miSession);

            //commit
            miSession.getTransaction().commit();
            //cerrar sesion
            miSession.close();

        } finally {
            miFactory.close();
        }
    }

    public static void consultarClientes(Session miSession) {
        //Consulta de Clientes
        List<Clientes> losClientes = miSession.createQuery("from Clientes").getResultList();

        consultarDatos(losClientes);
    }

    public static void consultarApellido(Session miSession) {
        //Consulta: dame los marin
        //apellidos es el campo de la clase Clientes, no de la base de datos
        List<Clientes> losClientes = miSession.createQuery("from Clientes cl where cl.nombre='patricia'").getResultList();
        //Mostrar los marin
        consultarDatos(losClientes);
    }

    public static void consultasOperadoresLogicos(Session miSession) {
        //Consulta con operador logico OR
        List<Clientes> losClientes = miSession.createQuery("from Clientes cl where cl.apellidos='marin' "
                + " or cl.direccion='coia'").getResultList();
        consultarDatos(losClientes);
    }
    
        public static void consultarDatos(List<Clientes> losClientes) {
        //Mostrar los clientes
        for (Clientes c : losClientes) {
            System.out.println(c);
        }
    }

}
