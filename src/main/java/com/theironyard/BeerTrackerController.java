package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jessicahuffstutler on 11/10/15.
 */
@Controller
public class BeerTrackerController {
    //BeerRepository object
    @Autowired //spring will wire it up for us, create the object and make it available to use
    BeerRepository beers;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("beers", beers.findAll()); //called "beers" in mustache
        return "home";
    }

    @RequestMapping("/add-beer")
    public String addBeer(String beername, String beertype) { //String beername, String beertype from HTML
        Beer beer = new Beer();
        beer.name = beername;
        beer.type = beertype;
        beers.save(beer); //that's all the code we need to insert it into the database.
        return "redirect:/";
    }

    @RequestMapping("edit-beer")
    public String editBeer(Integer id, String name, String type) {
        Beer beer = beers.findOne(id); //pulls out beer from database
        beer.name = name;
        beer.type = type;
        beers.save(beer);
        return "redirect:/";
    }
}
