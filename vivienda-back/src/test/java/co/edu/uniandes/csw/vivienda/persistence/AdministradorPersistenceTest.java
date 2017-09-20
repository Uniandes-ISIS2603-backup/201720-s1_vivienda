/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.AdministradorEntity;
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
 * @author da.ramirezv
 */

@RunWith(Arquillian.class)
public class AdministradorPersistenceTest {
    
    public AdministradorPersistenceTest() {
    }
    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private AdministradorPersistence persistence;

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
    private List<AdministradorEntity> data = new ArrayList<AdministradorEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
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
        em.createQuery("delete from AdministradorEntity").executeUpdate();
    }


    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Test of create method, of class AdministradorPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        AdministradorEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        AdministradorEntity entity = em.find(AdministradorEntity.class, result.getDocumento());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Test of findByName method, of class AdministradorPersistence.
     */
    @Test
    public void testFindByName() throws Exception {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity newEntity = persistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Test of update method, of class AdministradorPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        AdministradorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);

        newEntity.setDocumento(entity.getDocumento());

        persistence.update(newEntity);

        AdministradorEntity resp = em.find(AdministradorEntity.class, entity.getDocumento());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    /**
     * Test of delete method, of class AdministradorPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        AdministradorEntity entity = data.get(0);
        persistence.delete(entity.getDocumento());
        AdministradorEntity deleted = em.find(AdministradorEntity.class, entity.getDocumento());
        Assert.assertNull(deleted);
    }

    /**
     * Test of findByID method, of class AdministradorPersistence.
     */
    @Test
    public void testFindByID() throws Exception {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity newEntity = persistence.findByID(entity.getDocumento());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Test of findAll method, of class AdministradorPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<AdministradorEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(AdministradorEntity ent : list) {
            boolean found = false;
            for (AdministradorEntity entity : data) {
                if (ent.getDocumento().equals(entity.getDocumento())) {
                    found = true;
                }
        }
        Assert.assertTrue(found);
    }
    }
    
}
