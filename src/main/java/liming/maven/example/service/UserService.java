package liming.maven.example.service;

import java.util.List;

import liming.maven.example.model.UserInfo;


public interface UserService {
	void save(UserInfo userinfo);
	boolean update(UserInfo userinfo);
	boolean delete(int id);
	UserInfo findById(int id);
	List<UserInfo> findAll();
}
