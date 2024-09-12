package com.smartjob.api.users.adapters.out.persistence.sql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "phones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Phone extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    private String number;


    private String cityCode;


    private String countryCode;

    @PrePersist
    protected void onCreate() {
        this.setCreatedAt(LocalDateTime.now());
        this.setModifiedAt(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.setModifiedAt(LocalDateTime.now());
    }

}
