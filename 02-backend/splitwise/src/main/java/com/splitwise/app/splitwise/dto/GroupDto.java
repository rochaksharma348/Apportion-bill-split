package com.splitwise.app.splitwise.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupDto {

    private String name;

    private String type;

    private List<Long> users;
}
