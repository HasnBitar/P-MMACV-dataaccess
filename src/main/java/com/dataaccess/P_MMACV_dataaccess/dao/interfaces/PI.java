package com.dataaccess.P_MMACV_dataaccess.dao.interfaces;

import com.dataaccess.P_MMACV_dataaccess.dto.mysql.PersonalInfosDTO;

import java.util.List;
import java.util.Optional;

public interface PI {

    PersonalInfosDTO getPersonalInfoById(Long id);
    Optional<List<PersonalInfosDTO>> getAllPersonalInfos();
    PersonalInfosDTO savePersonalInfo(PersonalInfosDTO personalInfosDTO);
    PersonalInfosDTO updatePersonalInfo(PersonalInfosDTO personalInfosDTO);
    boolean deletePersonalInfoById(Long id);

}
