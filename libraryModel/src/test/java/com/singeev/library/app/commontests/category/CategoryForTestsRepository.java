package com.singeev.library.app.commontests.category;

import com.singeev.library.app.category.model.Category;
import org.junit.Ignore;

/**
 * Created by singeev on 22/05/2017.
 */
@Ignore
public class CategoryForTestsRepository {

    public static Category create(String name) {
        return new Category(name);
    }
}
