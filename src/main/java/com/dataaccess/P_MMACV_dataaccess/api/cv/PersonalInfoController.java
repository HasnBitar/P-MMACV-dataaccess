package com.dataaccess.P_MMACV_dataaccess.api.cv;

import com.dataaccess.P_MMACV_dataaccess.dao.interfaces.PI;
import com.dataaccess.P_MMACV_dataaccess.dto.mysql.PersonalInfosDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/personal-info")
public class PersonalInfoController {

    private final PI pi;

    @GetMapping("/{id}")
    public ResponseEntity<PersonalInfosDTO> getPersonalInfoById(Long id) {
        return ResponseEntity.ok(pi.getPersonalInfoById(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonalInfosDTO>> getAllPersonalInfos() {
        return ResponseEntity.ok(pi.getAllPersonalInfos().orElse(List.of()));
    }

    @PostMapping
    public ResponseEntity<PersonalInfosDTO> savePersonalInfo(@RequestBody PersonalInfosDTO personalInfosDTO) {
        return ResponseEntity.ok(pi.savePersonalInfo(personalInfosDTO));
    }

    public ResponseEntity<PersonalInfosDTO> updatePersonalInfo(@RequestBody PersonalInfosDTO personalInfosDTO) {
        return ResponseEntity.ok(pi.updatePersonalInfo(personalInfosDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePersonalInfo(Long id) {
        return ResponseEntity.ok(pi.deletePersonalInfoById(id));
    }
}
