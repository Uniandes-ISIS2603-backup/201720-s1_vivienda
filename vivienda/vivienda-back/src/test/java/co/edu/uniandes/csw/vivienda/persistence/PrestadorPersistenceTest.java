/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;


import co.edu.uniandes.csw.vivienda.entities.PrestadorEntity;
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
public class PrestadorPersistenceTest {
      public PrestadorPersistenceTest() {
    }
    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private PrestadorPersistence persistence;

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
    private List<PrestadorEntity> data = new ArrayList<PrestadorEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PrestadorEntity.class.getPackage())
                .addPackage(PrestadorPersistence.class.getPackage())
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
        em.createQuery("delete from PrestadorEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PrestadorEntity entity = factory.manufacturePojo(PrestadorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Test of create method, of class PrestadorPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        PrestadorEntity newEntity = factory.manufacturePojo(PrestadorEntity.class);
        PrestadorEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        PrestadorEntity entity = em.find(PrestadorEntity.class, result.getDocumento());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Test of findByName method, of class PrestadorPersistence.
     */
    @Test
    public void testFindByName() throws Exception {
        PrestadorEntity entity = data.get(0);
        PrestadorEntity newEntity = persistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Test of update method, of class PrestadorPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        PrestadorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PrestadorEntity newEntity = factory.manufacturePojo(PrestadorEntity.class);

        newEntity.setDocumento(entity.getDocumento());

        persistence.update(newEntity);

        PrestadorEntity resp = em.find(PrestadorEntity.class, entity.getDocumento());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    /**
     * Test of delete method, of class PrestadorPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        PrestadorEntity entity = data.get(0);
        persistence.delete(entity.getDocumento());
        PrestadorEntity deleted = em.find(PrestadorEntity.class, entity.getDocumento());
        Assert.assertNull(deleted);
    }

    /**
     * Test of findByID method, of class PrestadorPersistence.
     */
    @Test
    public void testFindByID() throws Exception {
        PrestadorEntity entity = data.get(0);
        PrestadorEntity newEntity = persistence.findByID(entity.getDocumento());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Test of findAll method, of class PrestadorPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<PrestadorEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(PrestadorEntity ent : list) {
            boolean found = false;
            for (PrestadorEntity entity : data) { /*
                if (ent.getDocumento().equals(entity.getDocumento())) {
                    */
                 if (ent.getDocumento() == (entity.getDocumento())) {
                    found = true;
                }
        }
        Assert.assertTrue(found);
    }
    }
    
}
