/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.EstudianteEntity;
import co.edu.uniandes.csw.vivienda.entities.SugerenciaEntity;
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
public class SugerenciaPersistenceTest {
    

    /**
     * Inyección de la dependencia a la clase XYZPersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    private SugerenciaPersistence persistence;

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
    private List<SugerenciaEntity> data = new ArrayList<SugerenciaEntity>();

    public SugerenciaPersistenceTest() {

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SugerenciaEntity.class.getPackage())
                .addPackage(SugerenciaPersistence.class.getPackage())
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
        em.createQuery("delete from SugerenciaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            SugerenciaEntity entity = factory.manufacturePojo(SugerenciaEntity.class);

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
        SugerenciaEntity newEntity = factory.manufacturePojo(SugerenciaEntity.class);
        SugerenciaEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        SugerenciaEntity entity = em.find(SugerenciaEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getMensaje(), entity.getMensaje());

    }

    /**
     * Test of update method, of class EstudiantePersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        SugerenciaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SugerenciaEntity newEntity = factory.manufacturePojo(SugerenciaEntity.class);

        newEntity.setId(entity.getId());

        persistence.update(newEntity);

        SugerenciaEntity resp = em.find(SugerenciaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getMensaje(), resp.getMensaje());
    }

    /**
     * Test of delete method, of class EstudiantePersistence.
     */
    @Test
    public void testDelete() throws Exception {
        SugerenciaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        SugerenciaEntity deleted = em.find(SugerenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Test of find method, of class EstudiantePersistence.
     */
    @Test
    public void testFind() throws Exception {
        SugerenciaEntity entity = data.get(0);
        SugerenciaEntity newEntity = persistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getMensaje(), newEntity.getMensaje());
    }

    /**
     * Test of findAll method, of class EstudiantePersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        List<SugerenciaEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (SugerenciaEntity ent : list) {
            boolean found = false;
            for (SugerenciaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
}
