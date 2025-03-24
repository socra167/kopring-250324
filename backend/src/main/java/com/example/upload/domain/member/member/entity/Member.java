package com.example.upload.domain.member.member.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.upload.global.entity.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseTime {
    @Column(length = 100, unique = true)
    private String username;

    @Column(length = 100)
    private String password;
    @Column(length = 100, unique = true)
    private String apiKey;
    @Column(length = 100)
    private String nickname;
    private String profileImgUrl;

    public Member(String username, String password, String apiKey, String nickname, String profileImgUrl) {
        this.username = username;
        this.password = password;
        this.apiKey = apiKey;
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
    }

    public Member() {

    }

    public String getUsername() {
        return username;
    }

    public Member setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getNickname() {
        return nickname;
    }

    public Member setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public boolean isAdmin() {
        return username.equals("admin");
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getMemberAuthoritesAsString()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

    }

    public List<String> getMemberAuthoritesAsString() {

        List<String> authorities = new ArrayList<>();

        if(isAdmin()) {
            authorities.add("ROLE_ADMIN");
        }

        return authorities;
    }

    public void update(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileImgUrlOrDefaultUrl() {
        return (profileImgUrl == null || profileImgUrl.isBlank()) ? "https://placehold.co/640x640?text=O_O" : this.profileImgUrl;
    }
}