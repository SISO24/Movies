package com.movies.demo.entity;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="movies")
public class MovieEntity {

    @Id
    private Integer id;
    private String name;
    private String director;
    private Double rating; 
    private Integer year_released;
    private String industry;
    
}
