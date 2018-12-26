package com.xiaoshi.order.web.controller.api;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.entity.Customer;
import com.xiaoshi.order.pojo.entity.Picture;
import com.xiaoshi.order.pojo.entity.Store;
import com.xiaoshi.order.service.*;
import com.xiaoshi.order.util.FileUtils;
import com.xiaoshi.order.util.ParameterCalibrationUtil;
import com.xiaoshi.order.util.ResultUtil;
import com.xiaoshi.order.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 商店Controller
 */
@RestController
@RequestMapping("/v1")
@EnableTransactionManagement
public class ReviewController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SalesSettingService salesSettingService;
    @Autowired
    private StoreAddressAssociationService storeAddressAssociationService;
    @Autowired
    private StorePictureAssociationService storePictureAssociationService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewPictureAssociationService reviewPictureAssociationService;
    private static String VERRIFICATIONCODE = "VERRIFICATIONCODE";

    /**
     * 编号：9-0-001,获取我的基本信息
     */
    @PostMapping("/review/upload")
    public ResponseEntity reviewUpload(@RequestParam(value = "token", required = true) String token,
                                       @RequestParam("picture") MultipartFile picture) {
        ParameterCalibrationUtil.IsEmpty(token);
        TokenUtil.getTokenModel(token);
        try {
            Picture picturetemp=new Picture();
            picturetemp.setPictureUrl(FileUtils.upload(picture, picture.getOriginalFilename()));
            pictureService.insertPicture(picturetemp);
            return ResultUtil.success("pictureId:"+picturetemp.getPictureId());
        }catch (Exception e){
            throw new OrderException(OrderCode.UPLOAD_NOT_SUCCESS);
        }
    }
}
