package com.jelena.jovanovic.cocktails.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class CocktailIngredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cocktail_id")
	@JsonBackReference
	@ToString.Exclude
	private Cocktail cocktail;

	@ManyToOne
	@JoinColumn(name = "ingredient_id")
	@JsonBackReference
	@ToString.Exclude
	private Ingredient ingredient;

	private String measure;

	public CocktailIngredient(Cocktail cocktail, Ingredient ingredient, String measure) {
		super();
		this.cocktail = cocktail;
		this.ingredient = ingredient;
		this.measure = measure;
	}

}
