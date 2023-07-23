package com.jelena.jovanovic.cocktails.dtos;

import java.util.Map;

import com.jelena.jovanovic.cocktails.entity.Image;
import com.jelena.jovanovic.cocktails.entity.enums.DrinkType;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CocktailDetailedResponse {

	private Long id;

	private String name;
	private String instructions;

	private DrinkType type;

	private Image image;
	Map<String, String> ingredients;

	private String modifiedDate;
}
