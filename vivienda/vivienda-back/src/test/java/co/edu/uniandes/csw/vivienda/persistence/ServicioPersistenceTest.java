/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.AdministradorEntity;
import co.edu.uniandes.csw.vivienda.entities.ServicioEntity;
import java.util.ArrayList;
import java.util.List;
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
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author rj.gonzalez10
 */
@RunWith(Arquillian.class)
public class ServicioPersistenceTest {
      public ServicioPersistenceTest() {
    }
    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ServicioPersistence persistence;

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
    private List<ServicioEntity> data = new ArrayList<ServicioEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ServicioEntity.class.getPackage())
                .addPackage(ServicioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
    
    private void clearData() {
        em.createQuery("delete from ServicioEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ServicioEntity entity = factory.manufacturePojo(ServicioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Test of create method, of class ServicioPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        ServicioEntity newEntity = factory.manufacturePojo(ServicioEntity.class);
        ServicioEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        ServicioEntity entity = em.find(ServicioEntity.class, result.getPrecio());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Test of findByName method, of class ServicioPersistence.
     */
    @Test
    public void testFindByName() throws Exception {
        ServicioEntity entity = data.get(0);
        ServicioEntity newEntity = persistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Test of update method, of class ServicioPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        ServicioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ServicioEntity newEntity = factory.manufacturePojo(ServicioEntity.class);

        newEntity.setPrecio(entity.getPrecio());

        persistence.update(newEntity);

        ServicioEntity resp = em.find(ServicioEntity.class, entity.getPrecio());

        Assert.assertEquals(newEntity.getPrecio(), resp.getPrecio());
    }

    /**
     * Test of delete method, of class ServicioPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        ServicioEntity entity = data.get(0);
        persistence.delete(entity.getNombre());
        ServicioEntity deleted = em.find(ServicioEntity.class, entity.getNombre());
        Assert.assertNull(deleted);
    }

 

    /**
     * Test of findAll method, of class AdministradorPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<ServicioEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(ServicioEntity ent : list) {
            boolean found = false;
            for (ServicioEntity entity : data) {
                if (ent.getPrecio().equals(entity.getPrecio())) {
                    found = true;
                }
        }
        Assert.assertTrue(found);
    }
    }
    
}
