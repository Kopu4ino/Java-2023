package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.assertj.core.api.Assertions;

class AnimalTest {
    final List<Animal> ANIMALS = List.of(
        new Animal("Pes", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, true),
        new Animal("Fifa", Animal.Type.DOG, Animal.Sex.M, 3, 110, 200, true),
        new Animal("UFC", Animal.Type.BIRD, Animal.Sex.M, 7, 20, 2, true)
    );

    @Test
    void testHeightSort() {
        Assertions.assertThat(StreamsForAnimals.heightSort1(ANIMALS))
            .hasSize(ANIMALS.size())
            .isSortedAccordingTo((a1, a2) -> (a1.height() - a2.height()));
    }

    @Test
    void testTopHeaviest() {
        Assertions.assertThat(StreamsForAnimals.topHeaviest2(ANIMALS, 2))
            .hasSize(2)
            .isSortedAccordingTo((a1, a2) -> (a2.weight() - a1.weight()));
    }

    @Test
    void testAnimalsCntByType() {
        Assertions.assertThat(StreamsForAnimals.animalsCntByType3(ANIMALS))
            .containsEntry(Animal.Type.CAT, 1L)
            .containsEntry(Animal.Type.DOG, 1L)
            .containsEntry(Animal.Type.BIRD, 1L);
    }

    @Test
    void testFindAnimalWithLongestName() {

        Animal animalWithLongestName = StreamsForAnimals.findAnimalWithLongestName4(ANIMALS);

        Assertions.assertThat(animalWithLongestName)
            .hasFieldOrPropertyWithValue("name", "Fifa");
    }

    @Test
    void testGetPrevailingSex() {
        Assertions.assertThat(StreamsForAnimals.getPrevailingSex5(ANIMALS)).isEqualTo(Animal.Sex.M);
    }

    @Test
    void testGetHeaviestAnimalEachType() {
        List<Animal> animals = List.of(
            new Animal("Pes", Animal.Type.CAT, Animal.Sex.F, 12, 10, 5, true),
            new Animal("Fifa", Animal.Type.DOG, Animal.Sex.M, 12, 100, 10, true),
            new Animal("BOB", Animal.Type.DOG, Animal.Sex.M, 12, 20, 15, true)
        );
        Assertions.assertThat(StreamsForAnimals.getHeaviestAnimalEachType6(animals))
            .containsEntry(
                Animal.Type.DOG,
                new Animal("BOB", Animal.Type.DOG, Animal.Sex.M, 12, 20, 15, true)
            )
            .containsEntry(
                Animal.Type.CAT,
                new Animal("Pes", Animal.Type.CAT, Animal.Sex.F, 12, 10, 5, true)
            );
    }

    @Test
    void testFindKthOldestAnimal() {
        Assertions.assertThat(StreamsForAnimals.findKthOldestAnimal7(ANIMALS, 2))
            .isPresent()
            .contains(new Animal("Fifa", Animal.Type.DOG, Animal.Sex.M, 3, 110, 200, true));

        Assertions.assertThat(StreamsForAnimals.findKthOldestAnimal7(ANIMALS, 100))
            .isEqualTo(Optional.empty());
    }

    @Test
    void testFindHeaviestAnimalBelowHeight() {
        Assertions.assertThat(StreamsForAnimals.findHeaviestAnimalBelowHeight8(ANIMALS, 50))
            .isPresent()
            .contains(
                new Animal("Pes", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, true)
            );

        Assertions.assertThat(StreamsForAnimals.findHeaviestAnimalBelowHeight8(ANIMALS, 1))
            .isEqualTo(Optional.empty());
    }

    @Test
    void testPawsCnt() {
        Assertions.assertThat(StreamsForAnimals.pawsCnt9(ANIMALS)).isEqualTo(4 + 4 + 2);
    }

    @Test
    void testFindAnimalsWithAgeNotMatchingPaws() {
        List<Animal> animals = List.of(
            new Animal("Pes", Animal.Type.CAT, Animal.Sex.F, 4, 10, 5, true),
            new Animal("Fifa", Animal.Type.DOG, Animal.Sex.M, 4, 100, 10, true),
            new Animal("BOB", Animal.Type.DOG, Animal.Sex.M, 12, 20, 15, true)
        );

        Assertions.assertThat(StreamsForAnimals.findAnimalsWithAgeNotMatchingPaws10(animals))
            .containsOnly(
                new Animal("BOB", Animal.Type.DOG, Animal.Sex.M, 12, 20, 15, true)
            );
    }

    @Test
    void testFindBitingAnimalsWithHeightAbove100() {
        Assertions.assertThat(StreamsForAnimals.findBitingAnimalsWithHeightAbove11(ANIMALS))
            .containsOnly(
                new Animal("Fifa", Animal.Type.DOG, Animal.Sex.M, 3, 110, 200, true)
            );
    }

    @Test
    void testCntAnimalWhereWeightAboveHeight() {
        Assertions.assertThat(StreamsForAnimals.cntAnimalWhereWeightAboveHeight12(ANIMALS)).isEqualTo(1L);
    }

    @Test
    void testFindAnimalsWithNameConsistMoreTwoWords() {
        List<Animal> animals = List.of(
            new Animal("Sponge Bob", Animal.Type.CAT, Animal.Sex.F, 4, 10, 5, true),
            new Animal("Fifa", Animal.Type.DOG, Animal.Sex.M, 4, 100, 10, true),
            new Animal("BOB", Animal.Type.DOG, Animal.Sex.M, 12, 20, 15, true)
        );
        Assertions.assertThat(StreamsForAnimals.findAnimalsWithNameConsistMoreTwoWords13(animals))
            .containsOnly(
                new Animal("Sponge Bob", Animal.Type.CAT, Animal.Sex.F, 4, 10, 5, true)
            );
    }

    @Test
    void testIsThereDogTallerThan() {
        Assertions.assertThat(StreamsForAnimals.isThereDogTallerThan14(ANIMALS, 20)).isTrue();
    }

    @Test
    void testFindWeightAnimalsEachTypeWithAgeBetween() {
        Assertions.assertThat(StreamsForAnimals.findWeightAnimalsEachTypeWithAgeBetween15(ANIMALS, 1, 6))
            .containsEntry(Animal.Type.CAT, 5)
            .containsEntry(Animal.Type.DOG, 200);
    }

    @Test
    void testGetListSortedAnimals() {
        List<Animal> animals = List.of(
            new Animal("Pes1", Animal.Type.CAT, Animal.Sex.M, 2, 10, 5, true),
            new Animal("UFC", Animal.Type.BIRD, Animal.Sex.F, 7, 20, 2, true),
            new Animal("Pes2", Animal.Type.CAT, Animal.Sex.M, 2, 10, 5, true),
            new Animal("Pes2", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, true),
            new Animal("Fifa", Animal.Type.DOG, Animal.Sex.F, 3, 110, 200, true)
        );

        Assertions.assertThat(StreamsForAnimals.getListSortedAnimals16(animals))
            .containsExactlyElementsOf(
                List.of(
                    new Animal("Pes1", Animal.Type.CAT, Animal.Sex.M, 2, 10, 5, true),
                    new Animal("Pes2", Animal.Type.CAT, Animal.Sex.M, 2, 10, 5, true),
                    new Animal("Pes2", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, true),
                    new Animal("Fifa", Animal.Type.DOG, Animal.Sex.F, 3, 110, 200, true),
                    new Animal("UFC", Animal.Type.BIRD, Animal.Sex.F, 7, 20, 2, true)
                )
            );
    }

    @Test
    void testFindHeaviestFishInLists() {
        List<Animal> list1 = List.of(
            new Animal("Pes", Animal.Type.CAT, Animal.Sex.M, 2, 10, 5, true),
            new Animal("Fifa", Animal.Type.DOG, Animal.Sex.F, 3, 110, 100, true),
            new Animal("UFC", Animal.Type.BIRD, Animal.Sex.M, 7, 20, 2, true),
            new Animal("DORY", Animal.Type.FISH, Animal.Sex.F, 7, 20, 20, true)
        );

        List<Animal> list2 = List.of(
            new Animal("Pes1", Animal.Type.CAT, Animal.Sex.M, 2, 10, 5, true),
            new Animal("Fifa1", Animal.Type.DOG, Animal.Sex.F, 3, 110, 200, true),
            new Animal("UFC1", Animal.Type.BIRD, Animal.Sex.M, 7, 20, 2, true),
            new Animal("HeavyDory", Animal.Type.FISH, Animal.Sex.F, 7, 20, 50, true)
        );

        Assertions.assertThat(StreamsForAnimals.findHeaviestFishInLists(List.of(list1, list2)))
            .isPresent()
            .contains(
                new Animal("HeavyDory", Animal.Type.FISH, Animal.Sex.F, 7, 20, 50, true)
            );
    }

    @Test
    public void testFindErrorAnimalName() {
        List<Animal> testAnimals = List.of(
            new Animal("", Animal.Type.CAT, Animal.Sex.M, 2, 10, 5, true),
            new Animal("1", Animal.Type.BIRD, Animal.Sex.F, 7, 20, 2, true),
            new Animal("Pes2", Animal.Type.CAT, Animal.Sex.M, -3, 10, 5, true),
            new Animal("Pes2", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, true),
            new Animal("Fifa", Animal.Type.DOG, Animal.Sex.F, 3, 110, 200, true)
        );

        Map<String, Set<ValidationError>> result = StreamsForAnimals.findErrorAnimalName19(testAnimals);

        Assertions.assertThat(result.get("")).containsOnly(
            new ValidationError(ErrorType.EMPTY_NAME),
            new ValidationError(ErrorType.SHORT_NAME)
        );
        Assertions.assertThat(result.get("1")).containsExactly(new ValidationError(ErrorType.SHORT_NAME));
    }

}
