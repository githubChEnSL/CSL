package cn.stores.mapper;

import cn.stores.entity.StoreInfo;
import cn.stores.entity.StoreInfoExample;
import java.util.List;

public interface StoreInfoMapper {
    int deleteByPrimaryKey(String storeid);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    List<StoreInfo> selectByExample(StoreInfoExample example);

    StoreInfo selectByPrimaryKey(String storeid);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);
}