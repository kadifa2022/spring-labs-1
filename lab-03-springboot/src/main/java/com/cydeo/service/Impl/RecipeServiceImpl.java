package com.cydeo.service.Impl;

import com.cydeo.enums.QuantityType;
import com.cydeo.enums.RecipeType;
import com.cydeo.model.Ingredient;
import com.cydeo.model.Recipe;
import com.cydeo.repository.RecipeRepository;
import com.cydeo.service.RecipeService;
import com.cydeo.service.ShareService;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ShareService shareService;
    private final Faker faker;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ShareService shareService, Faker faker) {
        this.recipeRepository = recipeRepository;
        this.shareService = shareService;
        this.faker = faker;//need to add  @Bean in configuration class because is 3rd party
    }




    @Override
    public boolean prepareRecipe() {


        for (int i = 0; i < 20; i++) {//for each recipe type

            //put in stream()
             Arrays.stream(RecipeType.values()).forEach(recipeType -> {//using consumer return type is void
                Recipe recipe = new Recipe();//object created
                recipe.setRecipeId(UUID.randomUUID());
                recipe.setName(faker.food().dish());
                recipe.setDuration(generateRandomValue());
                recipe.setPreparation(faker.lorem().paragraph(generateRandomValue()));
                recipe.setIngredients(prepareIngredient());
                recipe.setRecipeType(recipeType);//Enum
                recipeRepository.save(recipe);//methode to save  methode override in clas
                shareService.share(recipe);//methode to share on FB and Inst
            });
        }
        return true;
    }

    private List<Ingredient> prepareIngredient() {
        List<QuantityType> quantityTypes=List.of(QuantityType.values());//created list of quantity and ENUM
        List<Ingredient> ingredientList= new ArrayList<>();//list created for ingredients
        for (int i = 0; i < generateRandomValue(); i++) {//

            Ingredient ingredient = new Ingredient();//created object
            ingredient.setName(faker.food().ingredient());
            ingredient.setQuantity(generateRandomValue());//We use random because we have limitation
            ingredient.setQuantityType(quantityTypes.get(new Random().nextInt(3)));//
            ingredientList.add(ingredient);
        }
        return ingredientList;
    }

    private int generateRandomValue(){//created methode private because the methode will not be call outside the class
        return  new Random().nextInt(20);
    }
}
