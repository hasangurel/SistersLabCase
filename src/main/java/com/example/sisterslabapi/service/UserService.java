package com.example.sisterslabapi.service;

import com.example.sisterslabapi.model.User;
import com.example.sisterslabapi.repository.UserRepository;
import com.example.sisterslabapi.dto.request.user.CreateUserRequest;
import com.example.sisterslabapi.dto.request.user.UpdateUserRequest;
import com.example.sisterslabapi.dto.response.user.CreateUserResponse;
import com.example.sisterslabapi.dto.response.user.GetUserResponse;
import com.example.sisterslabapi.dto.response.user.UpdateUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    public void delete(Long id) {
        repository.deleteById(id);
    }
    public GetUserResponse getUser(Long id) {
        User user = findById(id);
        return GetUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public List<GetUserResponse> getAll() {
        List<User> all = repository.findAll();
        List<GetUserResponse> responses = new ArrayList<>();

        for (User user : all) {
            GetUserResponse response = GetUserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();
            responses.add(response);
        }

        return responses;
    }
    public UpdateUserResponse updateUserPassword(String password, Long id) {
        User user = findById(id);
        user.setPassword(password);
        User save = repository.save(user);
        return UpdateUserResponse.builder()
                .id(save.getId())
                .name(save.getName())
                .username(save.getUsername())
                .email(save.getEmail())
                .password(save.getPassword())
                .build();
    }


    public CreateUserResponse createUser(CreateUserRequest request) {
            User user=new User();
            user.setName(request.name());
            user.setUsername(request.username());
            user.setEmail(request.email());
            user.setPassword(request.password());
            repository.save(user);
        return CreateUserResponse.builder()
                .name(request.name())
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .build();
    }
    public UpdateUserResponse updateUser(UpdateUserRequest request) {
        User user = findById(request.id());
        user.setEmail(request.email());
        user.setName(request.name());
        user.setPassword(request.password());
        user.setUsername(request.username());
        // save user
        User save = repository.save(user);
        return UpdateUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
    }

}
