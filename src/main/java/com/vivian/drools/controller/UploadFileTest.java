package com.vivian.drools.controller;

import com.vivian.drools.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Eric Tseng
 * @description UploadFileTest
 * @since 2022/8/14 15:41
 */
@RestController
@RequestMapping(value = "/file")
public class UploadFileTest {
    @Autowired
    private UploadFileService fileService;

    @PostMapping(value = "/1")
    public Object test1(@RequestBody MultipartFile file) throws IOException {
        return fileService.test(file);
    }
}
