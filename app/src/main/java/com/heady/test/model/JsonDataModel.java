package com.heady.test.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keval on 2/2/2018.
 */

public class JsonDataModel {

    List<Categories> categories = new ArrayList<>();

    public List<Categories> getCategories() {
        return categories;
    }

    public class Categories implements Serializable {

        String id = "";
        String name = "";

        List<ProductDetailsModel> products = new ArrayList<>();

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<ProductDetailsModel> getProducts() {
            return products;
        }
    }

}
