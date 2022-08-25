package com.samples.UserManagement.dao;

import com.samples.UserManagement.Entity.UserData;

public interface UserDao {
int create(UserData user);
UserData read(int id); 
void update1(UserData user);
}
