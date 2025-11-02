package com.SalonAppoinment.salonAppoinment.dto;

public class UserResponseDTO {
    private Long id;
    private String username;
    private String role;
    private String name;

    public UserResponseDTO() {}

    public UserResponseDTO(Long id, String username, String role, String name) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
