package com.movies.demo.controller;
import com.movies.demo.entity.MovieEntity;
import com.movies.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping()
    public List<MovieEntity> getAllMovies(){
        return movieService.getAllMovies();
    }
    @GetMapping("/id")
    public MovieEntity getMovieById(@PathVariable Integer id){
return movieService.getMovieById(id);
    }
    
}
