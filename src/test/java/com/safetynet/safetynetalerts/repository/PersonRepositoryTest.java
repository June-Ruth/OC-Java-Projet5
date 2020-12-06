package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PersonRepositoryTest {

    @Test
    void convertJsonToJavaTestSuccess() {
        File file = new File("data-json-test");
        List<Person> personList;
        personList = PersonRepository.convertJsonToJava(file);
        assertThat(personList.get(0), instanceOf(Person.class));
        assertEquals(2, personList.size());
    }


}
