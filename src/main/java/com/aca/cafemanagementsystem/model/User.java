package com.aca.cafemanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@NamedQuery(name = "User.updateStatus",query = "update User u set u.status=:status where u.id=:id" )
@NamedQuery(name = "User.findByEmailId",query = "select u from User u where u.email=:email")
@NamedQuery(name="User.getAllWaiter",query = "select u.email from User u where u.role='waiter'")

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="status")
    private String status;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;


}
