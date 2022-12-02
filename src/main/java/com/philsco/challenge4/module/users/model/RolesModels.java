package com.philsco.challenge4.module.users.model;

import com.philsco.challenge4.enumeration.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class RolesModels {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public RolesModels() {

    }
}

