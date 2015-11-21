package com.clearnight.oa.filemanage.utils;

import com.clearnight.oa.base.util.LoginInfo;
import com.clearnight.oa.filemanage.bean.FileBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

public class FileManageUtils {
    /**
     * 根据文件获得文件对象信息
     * @param multipartFile 文件
     * @param fileUrl 文件路径
     * @return FileBean
     */
    public static FileBean setFileInfo(MultipartFile multipartFile,String fileUrl){
        FileBean fileBean = null;
        if(null!=multipartFile){
            String fileName = multipartFile.getName();
            fileBean = new FileBean();
            fileBean.setParentId(UUID.randomUUID().toString());
            fileBean.setUserId(LoginInfo.getLoginUser().getId());
            fileBean.setUploadTime(new Date());
            fileBean.setLastModifyTime(new Date());
            fileBean.setFileSize(multipartFile.getSize());
            fileBean.setFileName(fileName.substring(0,fileName.lastIndexOf(".")));
            fileBean.setMark("用户头像");
            fileBean.setFileUrl(fileUrl);
            fileBean.setFileType(fileName.substring(fileName.lastIndexOf("."),fileName.length()));
        }
        return fileBean;
    }
}
