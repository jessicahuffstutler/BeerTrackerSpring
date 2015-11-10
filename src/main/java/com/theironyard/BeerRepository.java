package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jessicahuffstutler on 11/10/15.
 */
//for every Entity, you need a repository
public interface BeerRepository extends CrudRepository<Beer, Integer> { //Integer is what type of Id we are using
    //nothing has to be inside of here because it's pulling in functionality from the CrudRepository
}
