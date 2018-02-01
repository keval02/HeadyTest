package com.heady.test.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keval on 2/2/2018.
 */

public class ProductDetailsModel implements Serializable {

    String id = "";
    String name = "";
    String date_added = "";

    List<Variants> variants = new ArrayList<>();

    Tax tax = new Tax();


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate_added() {
        return date_added;
    }

    public List<Variants> getVariants() {
        return variants;
    }

    public Tax getTax() {
        return tax;
    }

    public class Variants implements Serializable {

        String id = "";
        String color = "";
        String size = "";
        String price = "";


        public String getId() {
            return id;
        }

        public String getColor() {
            return color;
        }

        public String getSize() {
            return size;
        }

        public String getPrice() {
            return price;
        }
    }


    public class Tax implements Serializable {

        String name = "";
        String value = "";

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }


}
