package com.splitwise.app.splitwise.controller;

import com.splitwise.app.splitwise.dto.GroupDto;
import com.splitwise.app.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/create-group")
    public void createGroup(@RequestBody GroupDto groupDto) {
        groupService.createGroup(groupDto.getUsers(), groupDto.getName(), groupDto.getType());
    }
}
