package edu.kata.spring_boot_security.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

import edu.kata.spring_boot_security.auth.Role;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER) // Роли загружаются сразу с пользователем
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles;

    @OneToOne
    @JoinColumn(name = "user_data_id")
    private UserData userData;

    public User() {
        setUserData(new UserData());
    }

    public User(String username, String password, List<String> roles, UserData userData) {
        setUsername(username);
        setPassword(password);
        setRoles(roles);
        setUserData(userData);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public List<Role> getAuthorities() {
        return getRoles()
                .stream()
                .map(role_string ->
                        (role_string.startsWith("ROLE_") ? role_string.substring(5) : role_string)
                )
                .map(Role::valueOf)
                .collect(Collectors.toList());
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
        userData.setUser(this);
    }

    public String getNickname() {
        return userData.getNickname();
    }

    public void setNickname(String nickname) {
        userData.setNickname(nickname);
    }

    public String getFirstName() {
        return userData.getFirstname();
    }

    public void setFirstName(String firstName) {
        userData.setFirstname(firstName);
    }

    public String getLastName() {
        return userData.getLastname();
    }

    public void setLastName(String lastName) {
        userData.setLastname(lastName);
    }
}
