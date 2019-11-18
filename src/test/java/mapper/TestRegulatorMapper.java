package mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.stores.entity.RegulatorInfo;
import cn.stores.mapper.RegulatorInfoMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})		//加载配置文件  

public class TestRegulatorMapper {

	@Autowired
	RegulatorInfoMapper mapper;
	
	@Test
	public void Test() {
		try {
			RegulatorInfo info=mapper.selectByPrimaryKey("5");
			System.out.println(info.getRegulatorname());
			System.out.println("获取成功");
		} catch (Exception e) {
			System.out.println("获取失败");
		}
	}
}
