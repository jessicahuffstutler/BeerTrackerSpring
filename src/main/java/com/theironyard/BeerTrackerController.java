package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by jessicahuffstutler on 11/10/15.
 */
@Controller
public class BeerTrackerController {
    //BeerRepository object
    @Autowired //spring will wire it up for us, create the object and make it available to use
    BeerRepository beers;

    @Autowired
    UserRepository users;

    // automatically create a name at the beginning, you could also put test data for beers in it.
    @PostConstruct
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user = users.findOneByName("Jess");
        if (user == null) {
            user = new User();
            user.name = "TestUser";
            user.password = PasswordHash.createHash("hunter2");
            users.save(user);
        }
    }

    @RequestMapping("/")
    public String home(
            Model model,
            String type,
            Integer calories,
            String search,
            HttpServletRequest request,
            String showMine
    ) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "login";
        }

        if (showMine != null) {
            model.addAttribute("beers", users.findOneByName(username).beers); //querying database for user and then pulling out all of that username's beers
        } else if (search != null) {
            model.addAttribute("beers", beers.searchByName(search));
        } else if (type != null && calories != null) {
            model.addAttribute("beers", beers.findByTypeAndCaloriesIsLessThanEqual(type, calories));
        } else if (type != null) {
            model.addAttribute("beers", beers.findByTypeOrderByNameAsc(type));
        } else {
            model.addAttribute("beers", beers.findAll()); //called "beers" in mustache
        }
        return "home";
    }

    @RequestMapping("/add-beer")
    public String addBeer(String beername, String beertype, Integer beercalories, HttpServletRequest request) { //String beername, String beertype from HTML
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        User user = users.findOneByName(username);

        Beer beer = new Beer();
        beer.name = beername;
        beer.type = beertype;
        beer.calories= beercalories;
        beer.user = user;
        beers.save(beer); //that's all the code we need to insert it into the database.
        return "redirect:/";
    }

    @RequestMapping("/edit-beer")
    public String editBeer(Integer id, String name, String type) {
        Beer beer = beers.findOne(id); //pulls out beer from database
        beer.name = name;
        beer.type = type;
        beers.save(beer);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        User user = users.findOneByName(username);
        if (user == null) {
            user = new User();
            user.name = username;
            user.password = PasswordHash.createHash(password);
            users.save(user);
        } else if (!PasswordHash.validatePassword(password, user.password)) {
            //throw and error in spring is just throwing an exception instead of spark 403
            throw new Exception("Wrong password");
        }
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
