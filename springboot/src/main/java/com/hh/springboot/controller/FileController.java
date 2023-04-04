package com.hh.springboot.controller;

import com.hh.springboot.common.Result;
import com.hh.springboot.entity.Files;
import com.hh.springboot.entity.User;
import com.hh.springboot.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws Exception {
        return fileService.upload(file);
    }

    @GetMapping("/{fileUUid}")
    public void downLoadFile(@PathVariable String fileUUid, HttpServletResponse response) throws Exception {
        fileService.downLoadFile(fileUUid, response);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam int pageNum,
                           @RequestParam int pageSize,
                           @RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String type) {
        return fileService.pageQuery(pageNum, pageSize, name, type);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Files file) {
        return fileService.deleteById(file);
    }

    @PostMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return fileService.deleteBatch(ids);
    }
}
