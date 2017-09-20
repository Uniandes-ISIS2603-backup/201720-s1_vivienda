/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.TorreEntity;
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
 * @author da.solano1
 */
@RunWith(Arquillian.class)
public class TorrePersistenceTest {

    /**
     * Inyección de la dependencia a la clase TorrePersistence cuyos métodos se
     * van a probar.
     */
    @Inject
    private TorrePersistence persistence;

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
    private List<TorreEntity> data = new ArrayList<TorreEntity>();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Torre, el descriptor de la base
     * de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TorreEntity.class.getPackage())
                .addPackage(TorrePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public TorrePersistenceTest() {
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
                e1.printStackTrace();;
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from TorreEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TorreEntity entity = factory.manufacturePojo(TorreEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @After
    public void tearDown() {
    }
    public void createTorreEntityTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TorreEntity newEntity = factory.manufacturePojo(TorreEntity.class);
        TorreEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        TorreEntity entity = em.find(TorreEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    @Test
    public void getTorresTest() {
        List<TorreEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TorreEntity ent : list) {
            boolean found = false;
            for (TorreEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
public void getTorreTest() {
    TorreEntity entity = data.get(0);
    TorreEntity newEntity = persistence.find(entity.getId());
    Assert.assertNotNull(newEntity);
    Assert.assertEquals(entity.getId(), newEntity.getId());
}
@Test
public void updateTorreTest() {
    TorreEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    TorreEntity newEntity = factory.manufacturePojo(TorreEntity.class);

    newEntity.setId(entity.getId());

    persistence.update(newEntity);

    TorreEntity resp = em.find(TorreEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getId(), resp.getId());
}
@Test
public void deleteTorreTest() {
    TorreEntity entity = data.get(0);
    persistence.delete(entity.getId());
    TorreEntity deleted = em.find(TorreEntity.class, entity.getId());
    Assert.assertNull(deleted);
}

}
