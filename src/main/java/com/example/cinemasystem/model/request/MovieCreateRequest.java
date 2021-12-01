package com.example.cinemasystem.model.request;

import com.example.cinemasystem.Enums.Genre;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class MovieCreateRequest {

    protected int id;


    protected   String title;

    protected   String description;

    protected  Double length;

    protected Genre genre;

    protected   Double rating;

    protected   String director;

    protected String trailer;
}
