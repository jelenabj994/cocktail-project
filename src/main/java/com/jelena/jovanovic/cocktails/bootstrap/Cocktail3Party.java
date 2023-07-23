package com.jelena.jovanovic.cocktails.bootstrap;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Cocktail3Party {

	private String idDrink;
	private String strDrink;
	private String strDrinkAlternate;
	private String strTags;
	private String strVideo;
	private String strCategory;
	private String strIBA;
	private String strAlcoholic;
	private String strGlass;
	private String strInstructions;
	private String strInstructionsES;
	private String strInstructionsDE;
	private String strInstructionsFR;
	private String strInstructionsIT;
	private String strInstructionsZHHans;
	private String strInstructionsZHHant;
	private String strDrinkThumb;
	private String strIngredient1;
	private String strIngredient2;
	private String strIngredient3;
	private String strIngredient4;
	private String strIngredient5;
	private String strIngredient6;
	private String strIngredient7;
	private String strIngredient8;
	private String strIngredient9;
	private String strIngredient10;
	private String strIngredient11;
	private String strIngredient12;
	private String strIngredient13;
	private String strIngredient14;
	private String strIngredient15;
	private String strMeasure1;
	private String strMeasure2;
	private String strMeasure3;
	private String strMeasure4;
	private String strMeasure5;
	private String strMeasure6;
	private String strMeasure7;
	private String strMeasure8;
	private String strMeasure9;
	private String strMeasure10;
	private String strMeasure11;
	private String strMeasure12;
	private String strMeasure13;
	private String strMeasure14;
	private String strMeasure15;
	private String strImageSource;
	private String strImageAttribution;
	private String strCreativeCommonsConfirmed;
	private String dateModified;

	public String getIngredientNum(int i) {
		String property = String.format("strIngredient%d", i);
		switch (property) {
		case "strIngredient1":
			return strIngredient1;
		case "strIngredient2":
			return strIngredient2;
		case "strIngredient3":
			return strIngredient3;
		case "strIngredient4":
			return strIngredient4;
		case "strIngredient5":
			return strIngredient5;
		case "strIngredient6":
			return strIngredient6;
		case "strIngredient7":
			return strIngredient7;
		case "strIngredient8":
			return strIngredient8;
		case "strIngredient9":
			return strIngredient9;
		case "strIngredient10":
			return strIngredient10;
		case "strIngredient11":
			return strIngredient11;
		case "strIngredient12":
			return strIngredient12;
		case "strIngredient13":
			return strIngredient13;
		case "strIngredient14":
			return strIngredient14;
		default:
			return strIngredient15;
		}
	}

	public String getMeasureNum(int i) {
		String property = String.format("strMeasure%d", i);
		switch (property) {
		case "strMeasure1":
			return strMeasure1;
		case "strMeasure2":
			return strMeasure2;
		case "strMeasure3":
			return strMeasure3;
		case "strMeasure4":
			return strMeasure4;
		case "strMeasure5":
			return strMeasure5;
		case "strMeasure6":
			return strMeasure6;
		case "strMeasure7":
			return strMeasure7;
		case "strMeasure8":
			return strMeasure8;
		case "strMeasure9":
			return strMeasure9;
		case "strMeasure10":
			return strMeasure10;
		case "strMeasure11":
			return strMeasure11;
		case "strMeasure12":
			return strMeasure12;
		case "strMeasure13":
			return strMeasure13;
		case "strMeasure14":
			return strMeasure14;
		default:
			return strMeasure15;
		}
	}

}
