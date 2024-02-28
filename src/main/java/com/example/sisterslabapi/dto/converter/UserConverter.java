package com.example.sisterslabapi.dto.converter;

import com.example.sisterslabapi.dto.request.user.CreateUserRequest;
import com.example.sisterslabapi.dto.request.user.UpdateUserRequest;
import com.example.sisterslabapi.dto.response.user.CreateUserResponse;
import com.example.sisterslabapi.dto.response.user.GetUserResponse;
import com.example.sisterslabapi.dto.response.user.UpdateUserResponse;
import com.example.sisterslabapi.model.User;
import com.example.sisterslabapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final UserRepository userRepository;

    public GetUserResponse convertEntityToGetResponse(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public List<GetUserResponse> convertEntityToGetAllResponse(List<User> users) {
        return users.stream()
                .map(this::convertEntityToGetResponse)
                .toList();
    }
    public UpdateUserResponse convertEntityToUpdateResponse(User user) {
        return UpdateUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public User convertUpdateRequestToEntity(UpdateUserRequest request) {
        User user = findById(request.id());
        user.setEmail(request.email());
        user.setName(request.name());
        user.setPassword(request.password());
        user.setUsername(request.username());
        return user;
    }
    public CreateUserResponse convertEntityToCreateResponse(User user) {
        userRepository.save(user);
        return CreateUserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public User convertCreateRequestToEntity(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.email());
        user.setName(request.name());
        user.setPassword(request.password());
        user.setUsername(request.username());
        return user;
    }
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
    }
}
