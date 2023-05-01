package com.splitwise.app.splitwise.service;

import com.splitwise.app.splitwise.entity.User;

import java.util.List;

public interface UserSearchService {
    public List<User> searchUser(String query);
}
