package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.stores.service.RegulatoreInfoService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestRegulatorInfoService {
	@Autowired
	RegulatoreInfoService service;
	
	@Test
	public void Test() {
		try {
			service.listAllObject(1);
			System.out.println("ÕýÈ·");
		} catch (Exception e) {
			System.out.println("´íÎó");
		}
		
	}
}
