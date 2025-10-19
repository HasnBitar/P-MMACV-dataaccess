package com.dataaccess.P_MMACV_dataaccess.dao;

import com.dataaccess.P_MMACV_dataaccess.dao.interfaces.PI;
import com.dataaccess.P_MMACV_dataaccess.dto.mysql.PersonalInfosDTO;
import com.dataaccess.P_MMACV_dataaccess.entitys.mysql.PersonalInfos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class PIEntityManager implements PI {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public PersonalInfosDTO getPersonalInfoById(Long id) {
        PersonalInfos personalInfos = entityManager.find(PersonalInfos.class, id);
        if (personalInfos != null) {
            return PersonalInfosDTO.builder()
                    .id(personalInfos.getId())
                    .firstName(personalInfos.getFirstName())
                    .lastName(personalInfos.getLastName())
                    .email(personalInfos.getEmail())
                    .address(personalInfos.getAddress())
                    .phoneNumber(personalInfos.getPhoneNumber())
                    .build();
        }
        return null;
    }

    @Override
    public Optional<List<PersonalInfosDTO>> getAllPersonalInfos() {
        List<PersonalInfos> personalInfos = entityManager.createQuery("From personal_info", PersonalInfos.class).getResultList();
        if (!personalInfos.isEmpty()) {
            List<PersonalInfosDTO> personalInfosDTOS = personalInfos.stream().map(pi -> PersonalInfosDTO.builder()
                    .id(pi.getId())
                    .firstName(pi.getFirstName())
                    .lastName(pi.getLastName())
                    .address(pi.getAddress())
                    .email(pi.getEmail())
                    .phoneNumber(pi.getPhoneNumber())
                    .build()).toList();

            return Optional.of(personalInfosDTOS);
        }
        return Optional.empty();
    }

    @Override
    public PersonalInfosDTO savePersonalInfo(PersonalInfosDTO personalInfosDTO) {

        PersonalInfos personalInfo = PersonalInfos.builder()
                .firstName(personalInfosDTO.getFirstName())
                .lastName(personalInfosDTO.getLastName())
                .address(personalInfosDTO.getAddress())
                .email(personalInfosDTO.getEmail())
                .phoneNumber(personalInfosDTO.getPhoneNumber())
                .build();

        entityManager.getTransaction().begin();
        entityManager.persist(personalInfo);
        entityManager.getTransaction().commit();

        return personalInfosDTO;
    }

    @Override
    public PersonalInfosDTO updatePersonalInfo(PersonalInfosDTO personalInfosDTO) {

        PersonalInfos personalInfo = PersonalInfos.builder()
                .firstName(personalInfosDTO.getFirstName())
                .lastName(personalInfosDTO.getLastName())
                .address(personalInfosDTO.getAddress())
                .email(personalInfosDTO.getEmail())
                .phoneNumber(personalInfosDTO.getPhoneNumber())
                .build();

        entityManager.getTransaction().begin();
        entityManager.merge(personalInfo);
        entityManager.getTransaction().commit();

        return personalInfosDTO;
    }

    @Override
    public boolean deletePersonalInfoById(Long id) {

        PersonalInfos personalInfo = entityManager.find(PersonalInfos.class, id);
        if (personalInfo != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(personalInfo);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }
}
