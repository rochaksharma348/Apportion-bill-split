package com.splitwise.app.splitwise.controller;

import com.splitwise.app.splitwise.entity.User;
import com.splitwise.app.splitwise.service.UserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user-search")
public class UserSearchController {
    @Autowired
    private UserSearchService userSearchService;

    @GetMapping("/find-user")
    public List<User> searchUsers(@RequestParam("query") String query) {
        return this.userSearchService.searchUser(query);
    }
}
