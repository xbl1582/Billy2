package com.light.hexo.business.admin.component;

import com.light.hexo.business.admin.config.BlogProperty;
import com.light.hexo.business.admin.constant.ConfigEnum;
import com.light.hexo.business.admin.service.ConfigService;
import com.light.hexo.common.component.file.FileManageEnum;
import com.light.hexo.common.component.file.FileRequest;
import com.light.hexo.common.component.file.FileResponse;
import com.light.hexo.common.component.file.FileService;
import com.light.hexo.common.exception.GlobalException;
import com.light.hexo.common.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;

/**
 * @ClassName: LocalFileService
 * @ProjectName hexo-boot
 * @Description: 本地文件实现
 */
@Component
@Slf4j
public class LocalFileService implements FileService {

    @Autowired
    private ConfigService configService;

    @Autowired
    private BlogProperty blogProperty;

    @Autowired
    private Environment environment;

    @Override
    public FileResponse upload(FileRequest fileRequest) throws GlobalException {

        FileResponse fileResponse = new FileResponse();

        try {

            ByteArrayInputStream bis = new ByteArrayInputStream(fileRequest.getData());
            String uploadDir = this.getUploadDir();

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File dest = new File(uploadDir, fileRequest.getFilename());
            FileUtils.copyToFile(bis, dest);
            fileResponse.setSuccess(true)
                        .setPath(dest.getAbsolutePath())
                        .setUrl(this.getFileUrl(dest.getName()));
            return fileResponse;

        } catch (Exception e) {
            log.error("========【默认管理】文件 fileName: {} 文件上传失败=============", fileRequest.getFilename());
            e.printStackTrace();
        }

        return fileResponse;
    }

    @Override
    public FileResponse download(FileRequest fileRequest) throws GlobalException {
        FileResponse fileResponse = new FileResponse();
        try {
            File target = new File(this.getUploadDir() + fileRequest.getFilename());
            byte[] data = FileUtils.readFileToByteArray(target);
            fileResponse.setSuccess(true).setData(data);

        } catch (Exception e) {
            log.error("========【默认管理】文件 url: {} 文件下载失败=============", fileRequest.getFileUrl());
            e.printStackTrace();
        }

        return fileResponse;
    }

    private String getUploadDir() {
        String uploadDir = this.configService.getConfigValue(ConfigEnum.LOCAL_FILE_PATH.getName());
        return StringUtils.isBlank(uploadDir) ? this.blogProperty.getAttachmentDir() : uploadDir;
    }

    @Override
    public FileResponse remove(FileRequest fileRequest) throws GlobalException {
        FileResponse fileResponse = new FileResponse();
        File file = new File(fileRequest.getFilePath());
        if (!file.exists()) {
            return fileResponse;
        }

        try {
            FileUtils.forceDelete(file);
            fileResponse.setSuccess(true);
            return fileResponse;

        } catch (Exception e) {
            log.error("========【默认管理】文件 url: {} 文件删除失败=============", fileRequest.getFileUrl());
            e.printStackTrace();
        }

        return fileResponse;
    }

    @Override
    public String getFileUrl(String filename) throws GlobalException {
        String blogPage = this.configService.getConfigValue(ConfigEnum.HOME_PAGE.getName());
        return this.parseUrl((StringUtils.isNotBlank(blogPage) ? blogPage : IpUtil.getHostIp() + ":" + this.environment.getProperty("server.port")) + "/images/" + filename);
    }

    @Override
    public String getLocalPath(String filename) {
        String uploadDir = this.getUploadDir();
        File dest = new File(uploadDir, filename);
        if (!dest.exists()) {
            return "";
        }
        return dest.getAbsolutePath();
    }

    @Override
    public int getCode() {
        return FileManageEnum.LOCAL.getCode();
    }

}
