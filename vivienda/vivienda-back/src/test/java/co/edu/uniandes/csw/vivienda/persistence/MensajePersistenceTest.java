/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.MensajeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author da.ramirezv
 */
@RunWith(Arquillian.class)
public class MensajePersistenceTest {
    
    public MensajePersistenceTest() {
    }
    
    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private MensajePersistence persistence;

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
    private List<MensajeEntity> data = new ArrayList<MensajeEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MensajeEntity.class.getPackage())
                .addPackage(MensajePersistence.class.getPackage())
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
        em.createQuery("delete from MensajeEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MensajeEntity entity = factory.manufacturePojo(MensajeEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Test of create method, of class MensajePersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        MensajeEntity newEntity = factory.manufacturePojo(MensajeEntity.class);
        MensajeEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        MensajeEntity entity = em.find(MensajeEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getTitulo(), entity.getTitulo());
    }

    /**
     * Test of findByName method, of class MensajePersistence.
     */
    @Test
    public void testFindByName() throws Exception {
        MensajeEntity entity = data.get(0);
        MensajeEntity newEntity = persistence.findByName(entity.getTitulo());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getMensaje(), newEntity.getMensaje());
    }

    /**
     * Test of update method, of class MensajePersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        MensajeEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MensajeEntity newEntity = factory.manufacturePojo(MensajeEntity.class);

        newEntity.setTitulo(entity.getTitulo());

        persistence.update(newEntity);

        MensajeEntity resp = em.find(MensajeEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getTitulo(), resp.getTitulo());
    }

    /**
     * Test of delete method, of class MensajePersistence.
     */
    @Test
    public void testDelete() throws Exception {
        MensajeEntity entity = data.get(0);
        persistence.delete(entity.getId());
        MensajeEntity deleted = em.find(MensajeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of findByID method, of class MensajePersistence.
     */
    @Test
    public void testFindByID() throws Exception {
        MensajeEntity entity = data.get(0);
        MensajeEntity newEntity = persistence.findByID(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTitulo(), newEntity.getTitulo());
    }

    /**
     * Test of findAll method, of class MensajePersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        
    List<MensajeEntity> list = persistence.findAll();
    Assert.assertEquals(data.size(), list.size());
    for(MensajeEntity ent : list) {
        boolean found = false;
        for (MensajeEntity entity : data) {
            if (ent.getId().equals(entity.getId())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }
    }
    
}
