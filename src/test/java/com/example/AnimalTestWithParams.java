package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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

    @Test
    public void testGetFoodForHerbivore() throws Exception {
        Animal animal = new Animal();
        List<String> expectedFood = List.of("Трава", "Различные растения");
        List<String> actualFood = animal.getFood("Травоядное");
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetFoodForPredator() throws Exception {
        Animal animal = new Animal();
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        List<String> actualFood = animal.getFood("Хищник");
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetFoodForUnknownAnimalKindThrowsException() {
        Animal animal = new Animal();
        Exception exception = assertThrows(Exception.class, () -> {
            animal.getFood("Неизвестный вид");
        });
        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", exception.getMessage());
    }

    @Test
    public void testGetFamily() {
        Animal animal = new Animal();
        String expectedFamily = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
        String actualFamily = animal.getFamily();
        assertEquals(expectedFamily, actualFamily);
    }

}