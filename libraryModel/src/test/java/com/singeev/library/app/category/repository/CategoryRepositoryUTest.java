package com.singeev.library.app.category.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static com.singeev.library.app.commontests.category.CategoryForTestsRepository.*;

import com.singeev.library.app.category.model.Category;
import com.singeev.library.app.commontests.utils.DBCommand;
import com.singeev.library.app.commontests.utils.DBCommandTransactionalExecutor;
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
    private DBCommandTransactionalExecutor dbCommandTransactionalExecutor;

    @Before
    public void initTestCase() {
        entityManagerFactory = Persistence.createEntityManagerFactory("libraryPU");
        entityManager = entityManagerFactory.createEntityManager();
        categoryRepository = new CategoryRepository();
        categoryRepository.entityManager = entityManager;
        dbCommandTransactionalExecutor = new DBCommandTransactionalExecutor(entityManager);
    }

    @After
    public void closeEntityManager() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void addCategoryAndFindIt() {
        String categoryName = "Java";
        Long categoryAddedID = dbCommandTransactionalExecutor.executeCommand(() -> categoryRepository.add(create(categoryName)).getId());

        assertThat(categoryAddedID, is(notNullValue()));

        Category category = categoryRepository.findByID(categoryAddedID);
        assertThat(category, is(notNullValue()));
        assertThat(category.getName(), is(equalTo(categoryName)));
    }
}
