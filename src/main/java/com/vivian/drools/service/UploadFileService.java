package com.vivian.drools.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import com.vivian.drools.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Eric Tseng
 * @description UploadFileService
 * @since 2022/8/14 15:42
 */
@Slf4j
@Service
public class UploadFileService {
    public Object test(MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        /**
         * 第一种：
         * 读取数据且转化为lisy
         * 要求：列名必须和实体字段一样
         */
//        List<UserEntity> list = ExcelUtil.getReader(in, 0).readAll(UserEntity.class);

        /**
         * 从第二行开始读，忽略列名
         *
         */
        List<List<Object>> lists = ExcelUtil.getReader(in, 0).read(1);
        List<UserEntity> entityList = CollUtil.newArrayList();
        for (List<Object> e : lists) {
            if (e.size() >= 2 && e.get(0) != null && e.get(1) != null) {
                entityList.add(new UserEntity(e.get(0).toString(), e.get(1).toString()));
            }
        }
        return entityList;
    }
}
