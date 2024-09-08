package org.example;

import Entidades.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gbPersistenceApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try{
            entityManager.getTransaction().begin();

            Factura fac1 = new Factura();
            DetalleFactura dfac1 = new DetalleFactura();
            DetalleFactura dfac2 = new DetalleFactura();

            Categoria cat1 = new Categoria("Lacteos");
            Categoria cat2 = new Categoria("Limpieza");
            Categoria cat3 = new Categoria("Pedecedero");

            Articulo art1 = new Articulo(5, "Leche", 300);
            Articulo art2 = new Articulo(10, "Jabon", 250);
            Articulo art3 = new Articulo(3, "Escoba", 600);
            Articulo art4 = new Articulo(8, "Yogurt", 100);

            Domicilio dom1 = new Domicilio("Le Parc", 122);
            Domicilio dom2 = new Domicilio("Rivadavia", 548);

            Cliente cli1 = new Cliente("Gabriel", "Badui", 45715164);
            Cliente cli2 = new Cliente("Alejo", "Carobolante", 45360753);

            dom1.setCliente(cli1);
            dom2.setCliente(cli2);

            cli1.setDomicilio(dom1);
            cli2.setDomicilio(dom2);

            art1.getCategorias().add(cat1);
            art1.getCategorias().add(cat3);
            art2.getCategorias().add(cat2);
            art3.getCategorias().add(cat2);
            art4.getCategorias().add(cat1);
            art4.getCategorias().add(cat3);

            cat1.getArticulos().add(art1);
            cat1.getArticulos().add(art4);
            cat2.getArticulos().add(art2);
            cat2.getArticulos().add(art3);
            cat3.getArticulos().add(art1);
            cat3.getArticulos().add(art4);

            art2.getDetalleFacturas().add(dfac1);

            fac1.setCliente(cli1);
            fac1.setFecha("28/09/2024");
            fac1.getDetalleFacturas().add(dfac1);
            fac1.getDetalleFacturas().add(dfac2);

            dfac1.setArticulo(art2);
            dfac1.setCantidad(20);
            dfac1.setSubtotal(100);

            dfac2.setArticulo(art1);
            dfac2.setCantidad(50);
            dfac2.setSubtotal(200);

            fac1.setTotal(300);

            entityManager.persist(fac1);

            entityManager.flush();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar las clases");
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}