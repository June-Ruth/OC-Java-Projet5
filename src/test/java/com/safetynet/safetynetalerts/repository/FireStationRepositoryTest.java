package com.safetynet.safetynetalerts.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class FireStationRepositoryTest {

    @Test
    void convertJsonToJavaTest() {
        // vérifie que retourne une liste contenant 2 instance de firestation
    }
}
