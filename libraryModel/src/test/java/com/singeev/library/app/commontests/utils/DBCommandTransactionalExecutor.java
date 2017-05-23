package com.singeev.library.app.commontests.utils;

import org.junit.Ignore;

import javax.persistence.EntityManager;

import static com.singeev.library.app.commontests.category.CategoryForTestsRepository.create;

/**
 * Created by singeev on 23/05/2017.
 */
@Ignore
public class DBCommandTransactionalExecutor {
    private EntityManager entityManager;

    public DBCommandTransactionalExecutor(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T> T executeCommand(DBCommand<T> dbCommand){
        try {
            entityManager.getTransaction().begin();
            T toReturn = dbCommand.execute();
            entityManager.getTransaction().commit();
            entityManager.clear();
            return toReturn;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new IllegalStateException(e);
        }
    }
}
