package liming.maven.example.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import liming.maven.example.dao.UserInfoMapper;
import liming.maven.example.model.UserInfo;
import liming.maven.example.service.UserService;



@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了�?
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserInfoMapper mapper;

	public UserInfoMapper getMapper() {
		return mapper;
	}

	public void setMapper(UserInfoMapper mapper) {
		this.mapper = mapper;
	}

	public boolean delete(int id) {
		
		return mapper.delete(id);
	}

	public List<UserInfo> findAll() {
		List<UserInfo> findAllList = mapper.findAll();
		return findAllList;
	}

	public UserInfo findById(int id) {

		UserInfo userinfo = mapper.findById(id);
		
		return userinfo;
	}

	public void save(UserInfo userinfo) {

		mapper.save(userinfo);
	}

	public boolean update(UserInfo userinfo) {

		return mapper.update(userinfo);
	}
	
	

}
