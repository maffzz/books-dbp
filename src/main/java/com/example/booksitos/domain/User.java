package com.example.booksitos.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Fav> favorites;

    @OneToMany(mappedBy = "user")
    private List<Book> books;

    private Boolean expired;

    private Boolean locked;

    private Boolean credentialsExpired;

    private Boolean enable;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;}

    @Override
    public String getUsername() {
        return email;}

    @Override
    public boolean isAccountNonExpired() {
        return !expired;}

    @Override
    public boolean isAccountNonLocked() {
        return !locked;}

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;}

    @Override
    public boolean isEnabled() {
        return enable;}}