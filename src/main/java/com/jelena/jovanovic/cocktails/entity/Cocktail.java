package com.jelena.jovanovic.cocktails.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jelena.jovanovic.cocktails.entity.enums.DrinkType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Cocktail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	@Lob
	private String instructions;
	private String modifiedDate;
	@Enumerated(EnumType.STRING)
	private DrinkType type;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Image image;

	@OneToMany(mappedBy = "cocktail", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JsonManagedReference
	@ToString.Exclude
	private List<CocktailIngredient> ingredients;

	public Cocktail(String name, String instructions, String modifiedDate, DrinkType type, Image image) {
		super();
		this.name = name;
		this.instructions = instructions;
		this.modifiedDate = modifiedDate;
		this.type = type;
		this.image = image;
	}

}
