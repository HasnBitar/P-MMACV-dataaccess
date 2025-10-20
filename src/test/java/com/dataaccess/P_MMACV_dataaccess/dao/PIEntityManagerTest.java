package com.dataaccess.P_MMACV_dataaccess.dao;

import com.dataaccess.P_MMACV_dataaccess.entitys.mysql.PersonalInfos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PIEntityManagerTest {

    @Autowired TestEntityManager entityManager;

    @Test
    void getPersonalInfoByIdWhenSuccess() {

        PersonalInfos personalInfos = PersonalInfos.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .email("John.Doe@gmail.com")
                .phoneNumber("123-456-7890")
                .build();

        PersonalInfos savedPersonalInfos = entityManager.persist(personalInfos);
        PersonalInfos foundPersonalInfos = entityManager.find(PersonalInfos.class, savedPersonalInfos.getId());

        assertThat(foundPersonalInfos).isEqualTo(savedPersonalInfos);

    }

    @Test
    void getPersonalInfoByIdWhenNotFound() {

        PersonalInfos foundPersonalInfos = entityManager.find(PersonalInfos.class, 999L);
        assertThat(foundPersonalInfos).isNull();

    }

    @Test
    void getAllPersonalInfosWhenSuccess() {

        PersonalInfos personalInfos1 = PersonalInfos.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .email("John.Doe@gmail.com")
                .phoneNumber("123-456-7890")
                .build();

        entityManager.persist(personalInfos1);

        PersonalInfos personalInfos2 = PersonalInfos.builder()
                .firstName("Jane")
                .lastName("Smith")
                .address("456 Oak St")
                .email("John.Smith@gmail.com")
                .phoneNumber("098-765-4321")
                .build();

        entityManager.persist(personalInfos2);

        List<PersonalInfos> personalInfosList = entityManager.getEntityManager()
                .createQuery("FROM PersonalInfos p", PersonalInfos.class)
                .getResultList();

        assertThat(personalInfosList.size()).isEqualTo(2);

    }

    @Test
    void getAllPersonalInfosWhenFail() {

        List<PersonalInfos> personalInfosList = entityManager.getEntityManager()
                .createQuery("FROM PersonalInfos p", PersonalInfos.class)
                .getResultList();

        assertThat(personalInfosList.size()).isEqualTo(0);
    }


    @Test
    void savePersonalInfoWhenSuccess() {

        PersonalInfos personalInfos = PersonalInfos.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .email("John.Doe@gmail.com")
                .phoneNumber("123-456-7890")
                .build();

        PersonalInfos savedPersonalInfos = entityManager.persist(personalInfos);
        assertThat(entityManager.find(PersonalInfos.class, savedPersonalInfos.getId())).isEqualTo(personalInfos);

    }

    @Test
    void savePersonalInfoWhenFail() {

        PersonalInfos personalInfos1 = PersonalInfos.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .email("John.Doe@gmail.com")
                .phoneNumber("123-456-7890")
                .build();

        entityManager.persist(personalInfos1);

        PersonalInfos personalInfos2 = PersonalInfos.builder()
                .firstName("Jane")
                .lastName("Smith")
                .address("456 Oak St")
                .email("John.Doe@gmail.com") // Same email
                .phoneNumber("098-765-4321")
                .build();

        assertThatThrownBy(() -> entityManager.persist(personalInfos2))
                .isInstanceOf(Exception.class);

    }

    @Test
    void deletePersonalInfoWhenSuccess() {

        PersonalInfos personalInfos1 = PersonalInfos.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .email("John.Doe@gmail.com")
                .phoneNumber("123-456-7890")
                .build();

        PersonalInfos savedPersonalInfos = entityManager.persist(personalInfos1);

        entityManager.remove(savedPersonalInfos);

        PersonalInfos foundPersonalInfos = entityManager.find(PersonalInfos.class, savedPersonalInfos.getId());
        assertThat(foundPersonalInfos).isNull();
    }

    @Test
    void deletePersonalInfoWhenNotFound() {
        assertThatThrownBy(() -> {
            PersonalInfos personalInfos = entityManager.find(PersonalInfos.class, 999L);
            entityManager.remove(personalInfos);
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void updatePersonalInfoWhenSuccess() {

        PersonalInfos personalInfos = PersonalInfos.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .email("John.Doe@gmail.com")
                .phoneNumber("123-456-7890")
                .build();

        PersonalInfos savedPersonalInfos = entityManager.persist(personalInfos);

        savedPersonalInfos.setAddress("456 New Address");
        PersonalInfos updatedPersonalInfos = entityManager.merge(savedPersonalInfos);

        assertThat(updatedPersonalInfos.getAddress()).isEqualTo("456 New Address");
    }

    @Test
    void updatePersonalInfoWhenNotFound() {
        PersonalInfos personalInfos = PersonalInfos.builder()
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .email("John.Doe@gmail.com")
                .phoneNumber("123-456-7890")
                .build();

        PersonalInfos merged = entityManager.merge(personalInfos);
        entityManager.flush();

        assertThat(merged.getId()).isNotEqualTo(999L);
        assertThat(entityManager.find(PersonalInfos.class, 999L)).isNull();
    }

}