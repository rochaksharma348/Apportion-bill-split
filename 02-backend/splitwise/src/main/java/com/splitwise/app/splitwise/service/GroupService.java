package com.splitwise.app.splitwise.service;

import java.util.List;

public interface GroupService {

    public void createGroup(List<Long> users, String name, String type);

}
