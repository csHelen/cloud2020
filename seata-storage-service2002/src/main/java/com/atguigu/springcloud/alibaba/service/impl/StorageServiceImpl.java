package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.StorageDao;
import com.atguigu.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
public class StorageServiceImpl implements StorageService {


    @Resource
    private StorageDao storageDao;



    @Override
    public void decrease(Long productId, Integer count) {
        log.info("--------->storageService扣除库存开始");
        storageDao.decrease(productId,count);
        log.info("--------->storageService扣除库存结束");
    }
}
