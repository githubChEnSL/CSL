package cn.stores.mapper;

import cn.stores.entity.RegulatorInfo;
import cn.stores.entity.RegulatorInfoExample;
import java.util.List;

public interface RegulatorInfoMapper {
    int deleteByPrimaryKey(String regulatorid);

    int insert(RegulatorInfo record);

    int insertSelective(RegulatorInfo record);

    List<RegulatorInfo> selectByExample(RegulatorInfoExample example);

    RegulatorInfo selectByPrimaryKey(String regulatorid);

    int updateByPrimaryKeySelective(RegulatorInfo record);

    int updateByPrimaryKey(RegulatorInfo record);
}