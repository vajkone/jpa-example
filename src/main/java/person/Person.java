package person;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Person {

    public static enum  Gender {
        FEMALE, MALE;
    }


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private Gender gender;

    @Embedded
    private Address address;

    private String email;

    private String profession;

    public static Person randomPerson(){

        Person rp = new Person();

        Faker faker = new Faker();

        rp.name = faker.name().fullName();
        rp.dob = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        rp.gender = faker.options().option(Gender.FEMALE,Gender.MALE);


        rp.address= new Address().builder()
                .country(faker.address().country())
                .state(faker.address().state())
                .city(faker.address().city())
                .streetAddress(faker.address().streetAddress())
                .zip(faker.address().zipCode())
                .build();

        rp.email=faker.internet().emailAddress();
        rp.profession=faker.company().profession();














        String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449


        return rp;

    }

}


