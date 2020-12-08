package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.Person;
import org.codehaus.plexus.util.IOUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PersonRepositoryTest {

    /*@Test
    void convertJsonToJavaTestSuccess() throws IOException {
        File file = new File(getClass().getClassLoader().getResource("data.json").getFile());
        List<Person> personList;
        personList = PersonRepository.convertJsonToJava(file);
        assertThat(personList.get(0), instanceOf(Person.class));
        assertEquals(2, personList.size());
    }*/


}
