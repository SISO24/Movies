package com.movies.demo.entity;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true, nullable=false)
    private String username;
    @Column(nullable=false)
    private String password;

    
}
