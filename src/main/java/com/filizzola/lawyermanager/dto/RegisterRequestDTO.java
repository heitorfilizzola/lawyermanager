package com.filizzola.lawyermanager.dto;

import com.filizzola.lawyermanager.models.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class RegisterRequestDTO {
    @NotBlank(message = "O nome de usuário é obrigatório")
    @Column(unique = true)
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "O email é obrigatório")
    @Column(unique = true)
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "A senha não deve estar em branco")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User toEntity() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        return user;
    }

    public static RegisterRequestDTO fromEntity(User user) {
        RegisterRequestDTO dto = new RegisterRequestDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
