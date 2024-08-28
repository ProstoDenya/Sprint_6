package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AnimalTestWithParams {
    private Animal animal;
    private static final String HERBIVORE_ANIMAL = "Травоядное";
    private static final String PREDATOR_ANIMAL = "Хищник";
    private static final List<String> HERBIVORE_FOOD = List.of("Трава", "Различные растения");
    private static final List<String> PREDATOR_FOOD = List.of("Животные", "Птицы", "Рыба");

    private String kindAnimal;
    private List<String> foods;

    public AnimalTestWithParams(String animalKind, List<String> foods) {
        this.kindAnimal = animalKind;
        this.foods = foods;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {HERBIVORE_ANIMAL, HERBIVORE_FOOD},
                {PREDATOR_ANIMAL, PREDATOR_FOOD}
        };
    }

    @Before
    public void setUp() {
        animal = new Animal();
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> actual = animal.getFood(kindAnimal);
        assertEquals("Еда не соответствует виду животного",
                foods, actual);
    }
}