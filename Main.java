import java.util.*;
import java.util.stream.Collectors;



public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Long count = persons.stream()
                .filter(Person -> Person.getAge() < 18)
                .count();

        System.out.println("Количество несовершеннолетних - " + count);

        List<String> familiesPerson = persons.stream()
                .filter(Person -> Person.getAge() < 27)
                .filter(Person -> Person.getAge() > 18)
                .map(Person -> Person.getFamily())
                .collect(Collectors.toList());

        System.out.println("Фамилия призывников: " + familiesPerson);

        List<Person> list = persons.stream()
                .filter(Person -> Person.getEducation() == Education.HIGHER)
                .filter(Person -> Person.getAge() > 18)
                .filter(Person -> Person.getSex() == Sex.MAN ? Person.getAge() < 65 : Person.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println(list);



    }
}
