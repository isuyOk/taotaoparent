package com.taotao.service.impl;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {
    @Value("${ftp_address}")
    private String ftp_address;

    @Value("${ftp_port}")
    private Integer ftp_port;

    @Value("${ftp_username}")
    private String ftp_username;

    @Value("${ftp_password}")
    private String ftp_password;

    @Value("${ftp_basepath}")
    private String ftp_basepath;

    @Value("${img_base_url}")
    private String img_base_url;

    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultMap = new HashMap<>();
        String oldName = uploadFile.getOriginalFilename();
        String newName = IDUtils.genImageName();
        newName = newName + oldName.substring(oldName.lastIndexOf("."));
        String datePath = new DateTime().toString("/yyyy/mm/dd");
        try {
            boolean result = FtpUtil.uploadFile(ftp_address, ftp_port, ftp_username, ftp_password, ftp_basepath,
                    datePath, newName, uploadFile.getInputStream());

            if (!result) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
                return resultMap;
            } else {
                resultMap.put("error", 0);
                resultMap.put("url", img_base_url + datePath + "/" + newName);
                return resultMap;
            }
        } catch (IOException e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传异常");
            return resultMap;
        }
    }
}
