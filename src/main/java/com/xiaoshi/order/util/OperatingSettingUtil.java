package com.xiaoshi.order.util;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.entity.FoodItemAndTemplateAssociation;
import com.xiaoshi.order.pojo.entity.Log;
import com.xiaoshi.order.pojo.entity.OperatingSetting;
import com.xiaoshi.order.service.FoodItemAndTemplateAssociationService;
import com.xiaoshi.order.service.LogService;
import com.xiaoshi.order.service.OperatingSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xiaoshi.order.constant.OrderCode.DATE_NOT_FORMAT;

/**
 * OperatingSetting工具类
 */
@Component
public class OperatingSettingUtil {
    @Autowired
    private OperatingSettingService operatingSettingService;
    private static OperatingSettingService staticoperatingSettingService;

    @Autowired
    private FoodItemAndTemplateAssociationService foodItemAndTemplateAssociationService;
    private static FoodItemAndTemplateAssociationService staticfoodItemAndTemplateAssociationService;
    @PostConstruct
    public void init() {
        staticoperatingSettingService = operatingSettingService;
        staticfoodItemAndTemplateAssociationService = foodItemAndTemplateAssociationService;
    }

    /**
     * 获取当日OperatingSetting
     */
    public static OperatingSetting getOperatingSetting(Date getDeliveryDate, Long storeId) {
        Map operatingsettingmap = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //判断套餐时间
            String frontDate = sdf.format(getDeliveryDate);
            operatingsettingmap.put("frontDate", frontDate + " 00:00:00");
            operatingsettingmap.put("afterDate", frontDate + " 23:59:59");
        } catch (Exception e) {
            throw new OrderException(DATE_NOT_FORMAT);
        }
        operatingsettingmap.put("storeId", storeId);
        List<OperatingSetting> operatingsettings = staticoperatingSettingService.getOperatingSettingByParameter(operatingsettingmap);
        if (operatingsettings == null)
            throw new OrderException(OrderCode.COMBO_NOT_DATE);
        if (operatingsettings.size() == 0)
            throw new OrderException(OrderCode.COMBO_NOT_DATE);
        return operatingsettings.get(0);
    }

    /**
     * 判断菜品是否在当日OperatingSetting中
     */
    public static void isfoodItemAndTemplateAssociations(Long currentFoodItemTemplateId,Long foodItemId) {
        Map foodItemAndTemplateAssociationmap = new HashMap();
        foodItemAndTemplateAssociationmap.put("foodItemTemplateId", currentFoodItemTemplateId);
        List<FoodItemAndTemplateAssociation> foodItemAndTemplateAssociations = staticfoodItemAndTemplateAssociationService.getFoodItemAndTemplateAssociationByParameter(foodItemAndTemplateAssociationmap);
        if (foodItemAndTemplateAssociations == null)
            throw new OrderException(OrderCode.FOODTEMP_NOT_EXISTS);
        if (foodItemAndTemplateAssociations.size() == 0)
            throw new OrderException(OrderCode.FOODTEMP_NOT_EXISTS);
        Boolean flag=false;
        for(FoodItemAndTemplateAssociation foodItemAndTemplateAssociation:foodItemAndTemplateAssociations){
            if(foodItemAndTemplateAssociation.getFoodItem().getFoodItemId()==foodItemId)
                flag=true;
        }
        if(!flag)
            throw new OrderException(OrderCode.FOOD_NOT_EXISTS);
    }
}
