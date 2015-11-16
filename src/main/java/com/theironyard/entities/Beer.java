package com.theironyard.entities;

import javax.persistence.*;

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
    @Column(nullable = false)
    int id; //it wants the class not the primitive type

    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    public String type;
    @Column(nullable = false)
    public Integer calories; //it will create a column for each field entered into this class

    //it wants to know if it's many to one, one to many or many to many
    //there are multiple beers per user
    @ManyToOne
    public User user;
}
