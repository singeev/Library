package com.singeev.library.app.category.repository;

import com.singeev.library.app.category.model.Category;

import javax.persistence.EntityManager;

/**
 * Created by singeev on 22/05/2017.
 */
public class CategoryRepository {

    EntityManager entityManager;

    public Category add(final Category category){
        entityManager.persist(category);
        return category;
    }

    public Category findByID(final Long id) {
        return entityManager.find(Category.class, id);
    }
}
