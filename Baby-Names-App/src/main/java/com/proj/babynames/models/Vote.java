package com.proj.babynames.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    // Temp code for many to many, revisit later

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    // @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baby_id")
    Baby baby;

    public Vote() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Baby getBaby() {
        return baby;
    }

    public void setBaby(Baby baby) {
        this.baby = baby;
    }

    public Long getId() {
        return id;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
