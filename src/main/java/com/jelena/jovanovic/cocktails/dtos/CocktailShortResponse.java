package com.jelena.jovanovic.cocktails.dtos;

import com.jelena.jovanovic.cocktails.entity.enums.DrinkType;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CocktailShortResponse {

	private Long id;

	private String name;
	private DrinkType type;
}
