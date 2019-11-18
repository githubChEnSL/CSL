package cn.stores.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.stores.entity.RegulatorInfo;
import cn.stores.service.RegulatoreInfoService;

@Service
public class RegulaInfoServiceImpl implements RegulatoreInfoService{

	public int addObject(RegulatorInfo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteObject(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateObject(RegulatorInfo entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public RegulatorInfo getObject(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RegulatorInfo> listAllObject(Object... param) {
		List<RegulatorInfo> list=new ArrayList<RegulatorInfo>();
		RegulatorInfo info=new RegulatorInfo();
		info.setPassword("1");
		info.setRegulator_role_id("1");
		info.setRegulatorid("1");
		info.setRegulatorname("2");
		list.add(info);
		
		return list;
	}

}
