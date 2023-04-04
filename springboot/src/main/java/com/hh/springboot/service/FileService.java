package com.hh.springboot.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.springboot.common.Result;
import com.hh.springboot.entity.Files;
import com.hh.springboot.entity.User;
import com.hh.springboot.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class FileService extends ServiceImpl<FileMapper, Files> {

    @Autowired
    FileMapper fileMapper;

    @Value("${files.upload.path}")
    String fileUploadPath;

    public String upload(@RequestParam MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        String md5 = SecureUtil.md5(file.getInputStream());
        File parent = new File(fileUploadPath);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        String fileUUid = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUid);
        String url = "http://localhost:9090/manage/file/" + fileUUid;

        QueryWrapper<Files> wrapper = new QueryWrapper<>();
        wrapper.eq("md5", md5);
        List<Files> fileList = fileMapper.selectList(wrapper);
        if (fileList.isEmpty()) {
            file.transferTo(uploadFile);
        }else{
            url = fileList.get(0).getUrl();
        }

        Files files = new Files();
        files.setName(originalFilename);
        files.setSize(size);
        files.setType(type);
        files.setUrl(url);
        files.setMd5(md5);
        fileMapper.insert(files);
        return url;
    }

    public void downLoadFile(String fileUUid, HttpServletResponse response) throws Exception {
        File file = new File(fileUploadPath + fileUUid);
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUid, "UTF-8"));
        response.setContentType("application/octet-stream");
        outputStream.write(FileUtil.readBytes(file));
        outputStream.flush();
        outputStream.close();
    }

    public Result pageQuery(int pageNum, int pageSize, String name, String type) {
        QueryWrapper<Files> wrapper = new QueryWrapper<>();
        if (!"".equals(name)) {
            wrapper.like("name", name);
        }
        if (!"".equals(type)) {
            wrapper.like("type", type);
        }
        IPage<Files> pages = new Page<>(pageNum, pageSize);
        return new Result(Result.CODE_200, "", page(pages, wrapper));
    }

    public Result deleteById(Files file) {
        boolean remove = removeById(file);
        if (remove) {
            return new Result(Result.CODE_200, "删除文件成功", true);
        } else {
            return new Result(Result.CODE_500, "删除文件失败", false);
        }
    }

    public Result deleteBatch(List<Integer> ids) {
        boolean batch = removeBatchByIds(ids);
        if (batch) {
            return new Result(Result.CODE_200, "删除文件成功", true);
        } else {
            return new Result(Result.CODE_500, "删除文件失败", false);
        }
    }
}
