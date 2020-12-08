package com.safetynet.safetynetalerts.datasource;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DataBaseManagerTest {

    DataBase dataBase;

    @Test
    void getInstanceTest() {
        dataBase =  DataBaseManager.INSTANCE.getDataBase();
        System.out.println(dataBase);
    }


}
