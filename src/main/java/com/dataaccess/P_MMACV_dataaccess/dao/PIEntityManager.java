package com.dataaccess.P_MMACV_dataaccess.dao;

import com.dataaccess.P_MMACV_dataaccess.dao.interfaces.PI;
import com.dataaccess.P_MMACV_dataaccess.dto.mysql.PersonalInfosDTO;

import java.util.List;
import java.util.Optional;

public class PIEntityManager implements PI {
    @Override
    public PersonalInfosDTO getPersonalInfoById(Long id) {
        return null;
    }

    @Override
    public Optional<List<PersonalInfosDTO>> getAllPersonalInfos() {
        return Optional.empty();
    }

    @Override
    public PersonalInfosDTO savePersonalInfo(PersonalInfosDTO personalInfosDTO) {
        return null;
    }

    @Override
    public PersonalInfosDTO updatePersonalInfo(PersonalInfosDTO personalInfosDTO) {
        return null;
    }

    @Override
    public boolean deletePersonalInfoById(Long id) {
        return false;
    }
}
