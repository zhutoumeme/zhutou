package union.pojo;

import union.mapper.UserInfo;

public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);
}