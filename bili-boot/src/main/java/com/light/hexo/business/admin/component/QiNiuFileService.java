package com.light.hexo.business.admin.component;

import com.light.hexo.business.admin.constant.ConfigEnum;
import com.light.hexo.business.admin.constant.HexoExceptionEnum;
import com.light.hexo.business.admin.service.ConfigService;
import com.light.hexo.common.component.file.FileManageEnum;
import com.light.hexo.common.component.file.FileRequest;
import com.light.hexo.common.component.file.FileResponse;
import com.light.hexo.common.component.file.FileService;
import com.light.hexo.common.exception.GlobalException;
import com.light.hexo.common.util.ExceptionUtil;
import com.light.hexo.common.util.JsonUtil;
import com.qiniu.common.Region;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: QiNiuFileService
 * @ProjectName hexo-boot
 * @Description: 七牛云文件管理，参考：https://developer.qiniu.com/kodo/sdk/1239/java#5
 */
@Component
@Slf4j
public class QiNiuFileService implements FileService {

    private static final Integer RETRY_NUM = 3;

    @Autowired
    private ConfigService configService;

    @Override
    public FileResponse upload(FileRequest fileRequest) throws GlobalException {
        FileResponse fileResponse = new FileResponse();

        byte[] data = fileRequest.getData();
        String filename = fileRequest.getFilename();

        try {
            Configuration cfg = this.createConfiguration();
            UploadManager uploadManager = new UploadManager(cfg);
            Auth auth = this.createAuth();
            String upToken = auth.uploadToken(this.getBucket());

            Response response = uploadManager.put(data, filename, upToken);
            int retry = 0;
            while(response.needRetry() && retry < RETRY_NUM) {
                response = uploadManager.put(data, filename, upToken);
                retry++;
            }

            if (!response.isOK()) {
                return fileResponse;
            }

            fileResponse = JsonUtil.string2Obj(response.bodyString(), FileResponse.class);
            fileResponse.setSuccess(true).setUrl(this.getFileUrl(filename));

        } catch (Exception e) {
            log.error("========【七牛云管理】文件 fileName: {} 文件上传失败=============", filename);
            e.printStackTrace();
        }

        return fileResponse;
    }

    @Override
    public FileResponse download(FileRequest fileRequest) throws GlobalException {
        FileResponse fileResponse = new FileResponse();

        String urlStr = fileRequest.getFileUrl();

        try {
            URL url = new URL(urlStr);
            byte[] data = IOUtils.toByteArray(url.openStream());
            fileResponse.setSuccess(true).setData(data);

        } catch (Exception e) {
            log.error("========【七牛云管理】文件 url: {} 文件下载失败=============", urlStr);
            e.printStackTrace();
        }
        return fileResponse;
    }

    @Override
    public FileResponse remove(FileRequest fileRequest) throws GlobalException {
        FileResponse fileResponse = new FileResponse();

        String fileKey = fileRequest.getFilename();

        try {
            Configuration cfg = this.createConfiguration();
            Auth auth = this.createAuth();
            BucketManager bucketManager = new BucketManager(auth, cfg);

            String bucket = this.getBucket();
            Response response = bucketManager.delete(bucket, fileKey);
            int retry = 0;
            while(response.needRetry() && retry < RETRY_NUM) {
                response = bucketManager.delete(bucket, fileKey);
                retry++;
            }

            if (!response.isOK()) {
                return fileResponse;
            }

            fileResponse.setSuccess(true);
            return fileResponse;

        } catch (GlobalException e) {
            throw e;

        } catch (Exception ex) {
            log.error("========【七牛云管理】文件 fileName: {} 文件删除失败=============", fileRequest.getFilename());
            ex.printStackTrace();
        }

        return fileResponse;
    }

    @Override
    public String getFileUrl(String filename) throws GlobalException {
        return this.parseUrl(this.getDomain() + "/" + filename);
    }

    @Override
    public int getCode() {
        return FileManageEnum.QI_NIU.getCode();
    }


    private static final Map<String, Region> REGION_MAP = new HashMap<>();

    static {
        // 华东
        REGION_MAP.put("0", Region.region0());
        // 华北
        REGION_MAP.put("1", Region.region1());
        // 华南
        REGION_MAP.put("2", Region.region2());
        // 北美
        REGION_MAP.put("3", Region.regionNa0());
        // 东南亚
        REGION_MAP.put("4", Region.regionAs0());
    }

    private Configuration createConfiguration() {
        Map<String, String> configMap = this.configService.getConfigMap();
        String regionKey = configMap.get(ConfigEnum.QN_REGION.getName());
        return new Configuration(REGION_MAP.get(regionKey));
    }

    /**
     * 创建认证
     * @return
     */
    private Auth createAuth() throws GlobalException {
        Map<String, String> configMap = this.configService.getConfigMap();

        String accessKey = configMap.get(ConfigEnum.QN_ACCESS_KEY.getName());
        String secretKey = configMap.get(ConfigEnum.QN_SECRET_KEY.getName());

        if (StringUtils.isBlank(accessKey) || StringUtils.isBlank(secretKey)) {
            ExceptionUtil.throwEx(HexoExceptionEnum.ERROR_QN_CONFIG_IS_EMPTY);
        }

        Auth auth = Auth.create(accessKey, secretKey);

        return auth;
    }

    /**
     * 获取 bucket
     * @return
     */
    private String getBucket() throws GlobalException {
        Map<String, String> configMap = this.configService.getConfigMap();

        String bucket = configMap.get(ConfigEnum.QN_BUCKET.getName());
        if (StringUtils.isBlank(bucket)) {
            ExceptionUtil.throwEx(HexoExceptionEnum.ERROR_QN_CONFIG_IS_EMPTY);
        }

        return bucket;
    }

    /**
     * 获取域名
     * @return
     */
    private String getDomain() throws GlobalException {
        Map<String, String> configMap = this.configService.getConfigMap();

        String domain = configMap.get(ConfigEnum.QN_DOMAIN.getName());
        if (StringUtils.isBlank(domain)) {
            ExceptionUtil.throwEx(HexoExceptionEnum.ERROR_QN_CONFIG_IS_EMPTY);
        }

        return domain;
    }

}
