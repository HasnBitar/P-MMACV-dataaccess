package com.dataaccess.P_MMACV_dataaccess.entitys.mysql;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "PersonalInfos")
public class PersonalInfos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(nullable = false, name = "firstName")
    private String firstName;
    @Column(nullable = false, name = "lastName")
    private String lastName;
    @Column(nullable = false, name = "address")
    private String address;
    @Column(nullable = false, name = "email")
    private String email;
    @Column(nullable = false, name = "phoneNumber")
    private String phoneNumber;

}
