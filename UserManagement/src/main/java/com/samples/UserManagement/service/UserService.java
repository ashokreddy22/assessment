package com.samples.UserManagement.service;

import com.samples.UserManagement.Entity.UserData;

public interface UserService {
int save(UserData user);

UserData readdata(int i);


void updateData(UserData user);
}
