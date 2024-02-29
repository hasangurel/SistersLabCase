package com.example.sisterslabapi.service;

import com.example.sisterslabapi.dto.converter.UserConverter;
import com.example.sisterslabapi.exception.Constant;
import com.example.sisterslabapi.exception.UserIdNotFoundException;
import com.example.sisterslabapi.model.User;
import com.example.sisterslabapi.repository.UserRepository;
import com.example.sisterslabapi.dto.request.user.CreateUserRequest;
import com.example.sisterslabapi.dto.request.user.UpdateUserRequest;
import com.example.sisterslabapi.dto.response.user.CreateUserResponse;
import com.example.sisterslabapi.dto.response.user.GetUserResponse;
import com.example.sisterslabapi.dto.response.user.UpdateUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserConverter converter;

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    public GetUserResponse getUser(Long id) {
        return converter.convertEntityToGetResponse(findById(id));
    }

    public List<GetUserResponse> getAll() {
        return converter.convertEntityToGetAllResponse(repository.findAll());
    }

    public UpdateUserResponse updateUserPassword(UpdateUserRequest request) {
        findById(request.id()).setPassword(request.password());
        return converter.convertEntityToUpdateResponse(repository.save(findById(request.id())));
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        return converter.convertEntityToCreateResponse(converter.convertCreateRequestToEntity(request));
    }

    public UpdateUserResponse updateUser(UpdateUserRequest request) {
        return converter.convertEntityToUpdateResponse(converter.convertUpdateRequestToEntity(request));
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserIdNotFoundException(Constant.USER_ID_NOT_FOUND));
    }
}
