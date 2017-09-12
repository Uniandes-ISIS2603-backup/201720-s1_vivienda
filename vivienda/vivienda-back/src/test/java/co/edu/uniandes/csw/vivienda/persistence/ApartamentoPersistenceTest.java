/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.vivienda.persistence;

import co.edu.uniandes.csw.vivienda.entities.ApartamentoEntity;
import co.edu.uniandes.csw.vivienda.entities.PisoEntity;
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
public class ApartamentoPersistenceTest {

    /**
     * Inyección de la dependencia a la clase ApartamentoPersistence cuyos
     * métodos se van a probar.
     */
    @Inject
    private ApartamentoPersistence persistence;

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
    private List<ApartamentoEntity> data = new ArrayList<ApartamentoEntity>();

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
                .addPackage(ApartamentoEntity.class.getPackage())
                .addPackage(ApartamentoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    public ApartamentoPersistenceTest() {
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
        em.createQuery("delete from ApartamentoEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ApartamentoEntity entity = factory.manufacturePojo(ApartamentoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @After
    public void tearDown() {
    }

    public void createApartamentoEntityTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ApartamentoEntity newEntity = factory.manufacturePojo(ApartamentoEntity.class);
        ApartamentoEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);
        ApartamentoEntity entity = em.find(ApartamentoEntity.class, result.getNumApartamento());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getNumApartamento(), entity.getNumApartamento());
    }

    @Test
    public void getApartamentosTest() {
        List<ApartamentoEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ApartamentoEntity ent : list) {
            boolean found = false;
            for (ApartamentoEntity entity : data) {
                if (ent.getNumApartamento().equals(entity.getNumApartamento())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getApartamentoTest() {
        ApartamentoEntity entity = data.get(0);
        ApartamentoEntity newEntity = persistence.find(entity.getNumApartamento());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNumApartamento(), newEntity.getNumApartamento());
    }

    @Test
    public void updateApartamentoTest() {
        ApartamentoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ApartamentoEntity newEntity = factory.manufacturePojo(ApartamentoEntity.class);

        newEntity.setNumApartamento(entity.getNumApartamento());

        persistence.update(newEntity);

        ApartamentoEntity resp = em.find(ApartamentoEntity.class, entity.getNumApartamento());

        Assert.assertEquals(newEntity.getNumApartamento(), resp.getNumApartamento());
    }

    @Test
    public void deleteApartamentoTest() {
        ApartamentoEntity entity = data.get(0);
        persistence.delete(entity.getNumApartamento());
        ApartamentoEntity deleted = em.find(ApartamentoEntity.class, entity.getNumApartamento());
        Assert.assertNull(deleted);
    }

}
