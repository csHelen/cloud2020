package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.AccountDao;
import com.atguigu.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @program: cloud2020
 * @ClassName StorageServiceImpl
 * @description:
 * @author: 许
 * @create: 2020-04-03 00:33
 * @Version 1.0
 **/
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("--------------->>>account-service中扣减余额开始");
        accountDao.decrease(userId,money);
        log.info("--------------->>>account-service中扣减余额结果");
    }
}
