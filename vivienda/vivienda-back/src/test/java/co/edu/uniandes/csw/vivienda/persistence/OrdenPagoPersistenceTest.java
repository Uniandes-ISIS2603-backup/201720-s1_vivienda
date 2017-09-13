/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.OrdenPagoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author e.reyesm
 */
@RunWith(Arquillian.class)
public class OrdenPagoPersistenceTest {
  /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    private OrdenPagoPersistence persistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     *
     */
    private List<OrdenPagoEntity> data = new ArrayList<OrdenPagoEntity>();

    public OrdenPagoPersistenceTest() {

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrdenPagoEntity.class.getPackage())
                .addPackage(OrdenPagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    private void clearData() {
        em.createQuery("delete from OrdenPagoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            OrdenPagoEntity entity = factory.manufacturePojo(OrdenPagoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class EstudiantePersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        OrdenPagoEntity newEntity = factory.manufacturePojo(OrdenPagoEntity.class);
        OrdenPagoEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        OrdenPagoEntity entity = em.find(OrdenPagoEntity.class, result.getIdPago());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getPrecio(),entity.getPrecio(),0.01);

    }

    /**
     * Test of update method, of class EstudiantePersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        OrdenPagoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        OrdenPagoEntity newEntity = factory.manufacturePojo(OrdenPagoEntity.class);

        newEntity.setIdPago(entity.getIdPago());

        persistence.update(newEntity);

        OrdenPagoEntity resp = em.find(OrdenPagoEntity.class, entity.getIdPago());
        
        Assert.assertEquals(newEntity.getPrecio(),resp.getPrecio(),0.01);
    }

    /**
     * Test of delete method, of class EstudiantePersistence.
     */
    @Test
    public void testDelete() throws Exception {
        OrdenPagoEntity entity = data.get(0);
        persistence.delete(entity.getIdPago());
        OrdenPagoEntity deleted = em.find(OrdenPagoEntity.class, entity.getIdPago());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class EstudiantePersistence.
     */
    @Test
    public void testFind() throws Exception {
        OrdenPagoEntity entity = data.get(0);
        OrdenPagoEntity newEntity = persistence.find(entity.getIdPago());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPrecio(),newEntity.getPrecio(), 0.01);
    }

    /**
     * Test of findAll method, of class EstudiantePersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<OrdenPagoEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (OrdenPagoEntity ent : list) {
            boolean found = false;
            for (OrdenPagoEntity entity : data) {
                if (ent.getIdPago().equals(entity.getIdPago())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
}
