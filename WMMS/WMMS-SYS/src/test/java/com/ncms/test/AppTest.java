package com.ncms.test;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ncms.model.sys.user.SysUser;
import com.ncms.service.user.SysUserService;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest 
    extends TestCase
{
	@Resource
    private SysUserService testService;

    /**
     * Rigourous Test :-)
     */
	@Test
    public void testApp()
    {
    	List<SysUser> list = testService.findAll();
    	System.out.println(list.toString());
    }
}
