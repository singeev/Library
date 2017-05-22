package com.singeev.library.app.category.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static com.singeev.library.app.commontests.category.CategoryForTestsRepository.*;

import com.singeev.library.app.category.model.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by singeev on 22/05/2017.
 */
public class CategoryRepositoryUTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private CategoryRepository categoryRepository;

    @Before
    public void initTestCase() {
        entityManagerFactory = Persistence.createEntityManagerFactory("libraryPU");
        entityManager = entityManagerFactory.createEntityManager();
        categoryRepository = new CategoryRepository();
        categoryRepository.entityManager = entityManager;
    }

    @After
    public void closeEntityManager() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void addCategoryAndFindIt() {
        String categoryName = "Java";
        Long categoryAddedID = null;
        try {
            entityManager.getTransaction().begin();
            categoryAddedID = categoryRepository.add(create(categoryName)).getId();
            assertThat(categoryAddedID, is(notNullValue()));
            entityManager.getTransaction().commit();
            entityManager.clear();
        } catch (Exception e) {
            fail("I wasn't expect this exception!");
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

        Category category = categoryRepository.findByID(categoryAddedID);
        assertThat(category, is(notNullValue()));
        assertThat(category.getName(), is(equalTo(categoryName)));
    }
}
