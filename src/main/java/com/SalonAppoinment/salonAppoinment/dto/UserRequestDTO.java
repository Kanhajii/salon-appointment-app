package com.SalonAppoinment.salonAppoinment.dto;

public class UserRequestDTO {
    // UserDTO or RegisterRequestDTO
    private String username;
    private String password;
    private String role;
    private String email;
    private String contactNumber;



    public UserRequestDTO() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

