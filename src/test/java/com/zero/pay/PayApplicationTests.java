package com.zero.pay;

import com.zero.pay.Currency.DateUtil;
import com.zero.pay.Currency.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 读取赛事日历方法
     */
    @Test
    public void contextLoads() {

        redisUtil.hPut("test", "sss", "123");
        redisUtil.expireAt("test", DateUtil.minuteAddNum(new Date(),1));

    }
}
