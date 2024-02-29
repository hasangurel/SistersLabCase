package com.example.sisterslabapi.service;

import com.example.sisterslabapi.dto.converter.WatchListConverter;
import com.example.sisterslabapi.dto.request.watchList.CreateWatchListRequest;
import com.example.sisterslabapi.dto.response.watchList.CreateWatchListResponse;
import com.example.sisterslabapi.dto.response.watchList.GetWatchListResponse;
import com.example.sisterslabapi.dto.response.watchList.UpdateWatchListResponse;
import com.example.sisterslabapi.exception.Constant;
import com.example.sisterslabapi.exception.UserHasNotWatchListError;
import com.example.sisterslabapi.exception.UserIdNotFoundException;
import com.example.sisterslabapi.exception.WatchListIdNotFound;
import com.example.sisterslabapi.model.User;
import com.example.sisterslabapi.model.WatchList;
import com.example.sisterslabapi.repository.UserRepository;
import com.example.sisterslabapi.repository.WatchListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchListService {
    private final WatchListRepository repository;
    private final WatchListConverter converter;
    private final UserRepository userRepository;

    public CreateWatchListResponse createWatchList(CreateWatchListRequest request) {
        return converter.convertEntityToResponse(converter.convertCreateRequestToEntity(request));
    }

    @Transactional
    public void deleteAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserIdNotFoundException(Constant.USER_ID_NOT_FOUND));
        repository.deleteAllByUser(user);
    }

    public UpdateWatchListResponse setAsWatched(Long id) {
        findById(id).setWatched(true);
        return converter.convertEntityToUpdateResponse(findById(id));
    }

    public UpdateWatchListResponse setAsNotWatched(Long id) {
        findById(id).setWatched(false);
        return converter.convertEntityToUpdateResponse(findById(id));
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    public List<GetWatchListResponse> getByUserID(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserIdNotFoundException(Constant.USER_ID_NOT_FOUND));
        if (user.getWatchLists().isEmpty()) {
            throw new UserHasNotWatchListError(Constant.USER_HAS_NOT_WATCH_LIST);
        }
        return converter.convertEntityToGetAllResponse(user.getWatchLists());
    }

    public WatchList findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new WatchListIdNotFound(Constant.WATCH_LIST_ID_NOT_FOUND));
    }
}

