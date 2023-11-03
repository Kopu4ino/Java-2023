package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsForAnimals {
    private StreamsForAnimals() {
    }

    static List<Animal> heightSort1(List<Animal> animals) {
        return animals.stream().sorted((a, b) -> a.height() - b.height()).toList();
    }

    static List<Animal> topHeaviest2(List<Animal> animals, int cnt) {
        if (cnt <= 0) {
            throw new IllegalArgumentException("Ожидалось положительное число");
        }
        return animals.stream().sorted((a1, a2) -> a2.weight() - a1.weight()).limit(cnt).toList();
    }

    static Map<Animal.Type, Long> animalsCntByType3(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    static Animal findAnimalWithLongestName4(List<Animal> animals) {
        return animals.stream().max(Comparator.comparing(a -> a.name().length())).orElse(null);
    }

    static Animal.Sex getPrevailingSex5(List<Animal> animals) {
        Map<Animal.Sex, Long> sexCnt = animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));

        Long maleCnt = sexCnt.getOrDefault(Animal.Sex.M, 0L);
        Long femaleCnt = sexCnt.getOrDefault(Animal.Sex.F, 0L);

        if (maleCnt > femaleCnt) {
            return Animal.Sex.M;
        } else {
            return Animal.Sex.F;
        }
    }

    static Map<Animal.Type, Animal> getHeaviestAnimalEachType6(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(Animal::type, animal -> animal, (a1, a2) -> a1.weight() > a2.weight() ? a1 : a2));
    }

    static Optional<Animal> findKthOldestAnimal7(List<Animal> animals, Integer k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst();
    }

    static Optional<Animal> findHeaviestAnimalBelowHeight8(List<Animal> animals, int heightTopBound) {
        return animals.stream()
            .filter(animal -> animal.height() < heightTopBound)
            .max(Comparator.comparingInt(Animal::weight));
    }

    static int pawsCnt9(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    static List<Animal> findAnimalsWithAgeNotMatchingPaws10(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    static List<Animal> findBitingAnimalsWithHeightAbove11(List<Animal> animals) {
        final int HEIGHT_TOP_BOUND = 100;
        return animals.stream()
            .filter(Animal::bites)
            .filter(a -> a.height() > HEIGHT_TOP_BOUND)
            .toList();
    }

    static long cntAnimalWhereWeightAboveHeight12(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.weight() > a.height())
            .count();
    }

    static List<Animal> findAnimalsWithNameConsistMoreTwoWords13(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.name().contains(" "))
            .toList();
    }

    static boolean isThereDogTallerThan14(List<Animal> animals, Integer k) {
        return animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.height() > k)
            .count() > 0;
    }

    static Map<Animal.Type, Integer> findWeightAnimalsEachTypeWithAgeBetween15(
        List<Animal> animals,
        Integer lowAge,
        Integer topAge
    ) {
        return animals.stream()
            .filter(a -> a.age() >= lowAge && a.age() <= topAge)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    static List<Animal> getListSortedAnimals16(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    // Возвращаем false, так как данных для ответа недостаточно
    // Если задание сформулированно правильно, то ответ всегда false
    // Иначе я не понимаю какое должно быть условие
    static boolean doSpidersBiteMoreThanDogs() {
        return false;
    }

    static Optional<Animal> findHeaviestFishInLists17(List<List<Animal>> animalLists) {
        List<Animal> allFish = new ArrayList<>();

        animalLists.forEach(allFish::addAll);

        return allFish.stream()
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight));
    }

    static Map<String, Set<ValidationError>> findErrorAnimalName19(List<Animal> animals) {
        Map<String, Set<ValidationError>> errorsMap = new HashMap<>();

        for (Animal animal : animals) {
            Set<ValidationError> errors = Stream.of(
                    validateName(animal),
                    validateAge(animal)
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

            if (!errors.isEmpty()) {
                errorsMap.put(animal.name(), errors);
            }
        }

        return errorsMap;
    }

    private static ValidationError validateName(Animal animal) {
        if (animal.name() == null || animal.name().isEmpty()) {
            return new ValidationError(ErrorType.EMPTY_NAME);
        }
        return null;
    }

    private static ValidationError validateAge(Animal animal) {
        final int MIN_NAME_LENGTH = 3;

        if (animal.name().length() < MIN_NAME_LENGTH) {
            return new ValidationError(ErrorType.SHORT_NAME);
        }
        return null;
    }

    // Принимает результат работы findErrorAnimalName19 и возвращает Map<String, String>
    public static Map<String, String> representationWrapper20(Map<String, Set<ValidationError>> errorsMap) {
        Map<String, String> formattedErrors = new HashMap<>();

        for (Map.Entry<String, Set<ValidationError>> entry : errorsMap.entrySet()) {
            String animalName = entry.getKey();
            Set<ValidationError> errors = entry.getValue();

            List<String> errorFields = errors.stream()
                .map(error -> error.type().toString())
                .collect(Collectors.toList());

            formattedErrors.put(animalName, String.join(", ", errorFields));
        }

        return formattedErrors;
    }

//    public static void main(String[] args) {
//        List<Animal> testAnimals = List.of(
//            new Animal("", Animal.Type.CAT, Animal.Sex.M, 2, 10, 5, true),
//            new Animal("1", Animal.Type.BIRD, Animal.Sex.F, 7, 20, 2, true),
//            new Animal("Pes2", Animal.Type.CAT, Animal.Sex.M, -3, 10, 5, true),
//            new Animal("Pes2", Animal.Type.CAT, Animal.Sex.F, 2, 10, 5, true),
//            new Animal("Fifa", Animal.Type.DOG, Animal.Sex.F, 3, 110, 200, true)
//        );
//
//        System.out.println(findErrorAnimalName19(testAnimals));
//        System.out.println(representationWrapper20(findErrorAnimalName19(testAnimals)));
//    }

}
