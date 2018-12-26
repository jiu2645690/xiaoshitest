package com.xiaoshi.order.util;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.exception.OrderException;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

/**
 * 文件上传工具包
 */
public class FileUtils {
    /**
     *上传文件
     */
    public static String upload(MultipartFile file, String fileName){
        try {
        File pathtemp = new File(ResourceUtils.getURL("classpath:").getPath());
        File upload = new File(pathtemp.getAbsolutePath(),"review/upload/");
        String realPath = upload.getAbsolutePath() + "\\" + UUIDUtils.getUUID()+fileName;
        File dest = new File(realPath);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        //保存文件
        file.transferTo(dest);
        return realPath;
        } catch (Exception e) {
            throw new OrderException(OrderCode.UPLOAD_NOT_SUCCESS);
        }
    }
}
