package com.theironyard.services;

import com.theironyard.entities.Beer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jessicahuffstutler on 11/10/15.
 */
//for every Entity, you need a repository
public interface BeerRepository extends CrudRepository<Beer, Integer> { //Integer is what type of Id we are using
    //nothing has to be inside of here because it's pulling in functionality from the CrudRepository
    //below will aid in filter, parse the method name and generate the sql query for us.
    List<Beer> findByType(String type);
    List<Beer> findByTypeAndCalories(String type, Integer calories);
    List<Beer> findByTypeAndCaloriesIsLessThanEqual(String type, Integer calories);

    Beer findFirstByType(String type);
    int countByType(String type);
    List<Beer> findByTypeOrderByNameAsc(String type);

    @Query("SELECT b FROM Beer b WHERE LOWER(name) LIKE '%' || LOWER(?) || '%'")
    List<Beer> searchByName(String name);
}
