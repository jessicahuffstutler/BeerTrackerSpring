package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jessicahuffstutler on 11/10/15.
 */
//annotation for the entire class that tells it it's an object you want to create a table for
// it will create a table with the values below as fields in the table and will convert them into the correct type (i.e. varchar)
@Entity
public class Beer {
    //tells it that it's the primary key, "id", encodes this field as primary key
    @Id
    @GeneratedValue
    Integer id; //it wants the class not the primitive type

    String name;
    String type;
}
