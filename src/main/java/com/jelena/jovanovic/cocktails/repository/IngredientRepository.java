package com.jelena.jovanovic.cocktails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jelena.jovanovic.cocktails.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
