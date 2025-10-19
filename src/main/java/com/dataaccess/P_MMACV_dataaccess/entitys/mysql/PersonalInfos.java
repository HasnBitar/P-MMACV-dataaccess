package com.dataaccess.P_MMACV_dataaccess.entitys.mysql;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Personal_infos")
@Builder
public class PersonalInfos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column(nullable = false, name = "last_name")
    private String lastName;
    @Column(nullable = false, name = "address")
    private String address;
    @Column(nullable = false, name = "email")
    private String email;
    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

}
