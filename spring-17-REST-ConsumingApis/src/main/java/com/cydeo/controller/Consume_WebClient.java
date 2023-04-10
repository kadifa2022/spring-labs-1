package com.cydeo.controller;

import com.cydeo.dto.GenreDTO;
import com.cydeo.dto.MovieCinemaDTO;
import com.cydeo.service.GenreService;
import com.cydeo.service.MovieCinemaService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class Consume_WebClient {
    private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();//want to consume my own api

    private final MovieCinemaService movieCinemaService;
    private final GenreService genreService;

    public Consume_WebClient(MovieCinemaService movieCinemaService, GenreService genreService) {
        this.movieCinemaService = movieCinemaService;
        this.genreService = genreService;
    }
     //creating Api's with reactive way
    @GetMapping("/flux-movie-cinemas")//
    public Flux<MovieCinemaDTO> readAllCinemaFlux(){//returning more than one

        return Flux.fromIterable(movieCinemaService.findAll());

    }

//    @GetMapping("/mono-movie-cinema/{id}")
//    public Mono<MovieCinemaDTO> readById(@PathVariable("id") Long id){
//
//        return Mono.just(movieCinemaService.findById(id));
//
//    }

    @GetMapping("/mono-movie-cinema/{id}")
    public ResponseEntity<Mono<MovieCinemaDTO>> readById(@PathVariable("id") Long id){//returning one object

        return ResponseEntity.ok(Mono.just(movieCinemaService.findById(id)));

    }

    @PostMapping("/create-genre")
    public Mono<GenreDTO> createGenre(@RequestBody GenreDTO genre){

        GenreDTO createdGenre = genreService.save(genre);

        return Mono.just(createdGenre);
//        return Mono.just(genreRepository.save(genre));

    }

    @DeleteMapping("/delete/genre/{id}")
    public Mono<Void> deleteGenre(@PathVariable("id") Long id){

        genreService.deleteById(id);

        return Mono.empty();
    }

//    ---------------------------WEBCLIENT---------------------------

    @GetMapping("/flux")//flux is returning multiply elements (list)
    public Flux<MovieCinemaDTO> readWithWebClient(){

        return webClient
                .get()
                .uri("/flux-movie-cinemas")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(MovieCinemaDTO.class);//to get multiply objects-bodyToFlux

    }

    @GetMapping("/mono/{id}")//mono is returning only one object
    public Mono<MovieCinemaDTO> readMonoWithWebClient(@PathVariable("id") Long id){

        return webClient
                .get()
                .uri("/mono-movie-cinema/{id}",id)
                .retrieve()
                .bodyToMono(MovieCinemaDTO.class);//to get one object

    }

}

