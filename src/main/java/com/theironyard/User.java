package com.theironyard;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jessicahuffstutler on 11/11/15.
 */
@Entity
//you cant call a table user in postgreSQL
//customize table name below
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    Integer id;

    String name;
    String password;

    //storing the list of beers that a user created
    //"user" is the name of the field in the beer class (the @ManyToOne field)
    @OneToMany(mappedBy = "user")
    List<Beer> beers;
}
