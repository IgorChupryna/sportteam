package com.sportteam.spring.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Security")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Security {
    @Id
    @GeneratedValue
    @Column
    private long id;
    @Column
    private String login;
    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column
    private String email;
    @Column
    private String phone;

    public Security(String login, String password, UserRole role, String email, String phone) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
    }
}
