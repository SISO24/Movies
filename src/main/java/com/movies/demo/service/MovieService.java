package com.movies.demo.service;
import com.movies.demo.entity.MovieEntity;
import com.movies.demo.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;

    public List<MovieEntity> getAllMovies(){
   return  movieRepo.findAll();
    }
public MovieEntity getMovieById(Integer id){
  return   movieRepo.findById(id).orElse(null);
}
    
}
