package com.smartjob.api.users.adapters.out.persistence.sql.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {

    private String name;


    private String username;


    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Phone> phones;


    private String token;

    @Column(name = "is_active")
    private boolean enabled;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;


    public void addPhone(Phone phone) {
        phones.add(phone);
        phone.setUser(this);
    }

    public void removePhone(Phone phone) {
        phones.remove(phone);
        phone.setUser(null);
    }

    @PrePersist
    protected void onCreate() {
        this.setCreatedAt(LocalDateTime.now());
        this.setModifiedAt(LocalDateTime.now());
        this.setLastLogin(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.setModifiedAt(LocalDateTime.now());
    }

}
