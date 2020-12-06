package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.MedicalRecord;
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
class MedicalRecordRepositoryTest {

    @Test
    void convertJsonToJavaTestSuccess() {
        File file = new File("data-json-test");
        List<MedicalRecord> medicalRecordList;
        medicalRecordList = MedicalRecordRepository.convertJsonToJava(file);
        assertThat(medicalRecordList.get(0), instanceOf(MedicalRecord.class));
        assertEquals(2, medicalRecordList.size());
    }

}
