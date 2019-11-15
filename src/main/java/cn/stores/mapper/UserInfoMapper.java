package cn.stores.mapper;

import cn.stores.entity.UserInfo;
import cn.stores.entity.UserInfoExample;
import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String userid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}