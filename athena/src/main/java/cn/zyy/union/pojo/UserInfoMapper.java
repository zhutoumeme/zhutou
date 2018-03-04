package main\java\cn\zyy\union\pojo;

import main\java\cn\zyy\union\mapper.UserInfo;

public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);
}