package cn.chenxins;

import cn.chenxins.cms.model.entity.LinUser;
import cn.chenxins.cms.model.entity.mapper.LinUserMapper;
import cn.chenxins.utils.DesUtils;
import cn.chenxins.utils.JdateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 用于首次初始化配置生成super用户
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationCreateSuperUser {

	/**
	 * 实例化mapper
	 */
	@Autowired
	private LinUserMapper userMapper;

	@Test
	public void contextLoads() {
		try {
			LinUser user=new LinUser();
			user.setCreateTime(JdateUtils.getCurrentDate());
			user.setUpdateTime(JdateUtils.getCurrentDate());
			user.setNickname("admin");
			user.setPassword(DesUtils.GeneratePasswordHash("123456"));
			user.setIsSuper((short) 2);
			user.setEmail("12345@qq.com");
			user.setActive((short)1);
			userMapper.insert(user);
			System.out.println("用户:"+user.getNickname()+"创建完成！");
//			userService.CreateSuperAdmin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

