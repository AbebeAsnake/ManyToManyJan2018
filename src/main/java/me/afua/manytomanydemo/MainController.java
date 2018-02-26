package me.afua.manytomanydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;

@Controller
public class MainController {

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String showIndex(Model model)
    {
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("movies", movieRepository.findAll());
        return "index";
    }

    @GetMapping("/addmovie")
    public String addMovie(Model model)
    {
        model.addAttribute("movieobject",new Movie());
        return "addmovie";
    }

    @GetMapping("/addactor")
    public String addActor(Model model)
    {
        model.addAttribute("actorobject",new Actor());
        return "addactor";
    }

    @PostMapping("/addmovie")
    public String saveMovie(@ModelAttribute("movieobject") Movie movie, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "addmovie";
        }
        movieRepository.save(movie);
        return "redirect:/";
    }

    @PostMapping("/addactor")
    public String saveActor(@ModelAttribute("actorobject") Actor anActor, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "addactor";
        }
        actorRepository.save(anActor);
        return "redirect:/";
    }

    @GetMapping("/addactortomovie/{id}")
    public String addActorToMovie(Model model, @PathVariable("id") long id)
    {
        model.addAttribute("actorList",actorRepository.findAll());
        model.addAttribute("movieobject",movieRepository.findOne(id));
        return "addactorstomovie";
    }
}
