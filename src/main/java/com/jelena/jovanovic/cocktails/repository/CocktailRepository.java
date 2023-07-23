package com.jelena.jovanovic.cocktails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jelena.jovanovic.cocktails.entity.Cocktail;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

}
