package com.xiaoshi.order.util;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.xiaoshi.order.constant.OrderCode.*;

/**
 * 参数校验工具类
 **/
public class ParameterCalibrationUtil {
    @Autowired
    private CustomerService customerService;

    /**
     * 参数验证空
     */
    public static void IsEmpty(String str) {
        if (str == null) {
            throw new OrderException(PARM_NOT_EMPTY);
        }
        if (str.length() == 0) {
            throw new OrderException(PARM_NOT_EMPTY);
        }
    }
    /**
     * 参数List验证空
     */
    public static void IsEmpty(List list) {
        if (list == null) {
            throw new OrderException(PARM_NOT_EMPTY);
        }
        if (list.size()== 0) {
            throw new OrderException(PARM_NOT_EMPTY);
        }
    }

    /**
     * 参数验证空
     */
    public static void IsEmpty(Integer str) {
        if (str == null) {
            throw new OrderException(PARM_NOT_EMPTY);
        }
    }

    /**
     * 参数验证空
     */
    public static void IsTokenEmpty(String token) {
        //检查用户信息
        if (token == null) {
            if (token.equals("")) {
                throw new OrderException(OrderCode.USER_NOT_EXISTS);
            }
            throw new OrderException(OrderCode.USER_NOT_EXISTS);
        }
    }

    /**
     * 参数验证空
     */
    public static void IsEmpty(Long str) {
        if (str == null) {
            throw new OrderException(PARM_NOT_EMPTY);
        }
    }

    /**
     * 参数验证空
     */
    public static String IsDateEmpty(String str) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String returnStr=null;
        try {
            date = sdf.parse(str);
            String frontDate=sdf.format(date);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE,14);
            String afterDate=sdf.format(calendar.getTime());
            returnStr=frontDate+","+afterDate;
        } catch (ParseException e) {
           throw new OrderException(DATE_NOT_FORMAT);
        }
        return returnStr;
    }

    /**
     *  邮箱验证
     */
    public static void IsMail(String str) {
        if (!str.matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}")) {
            throw new OrderException(MAIL_NOT_FORMAT);
        }
    }

    /**
     *  邮箱手机
     */
    public static void IsPhone(String str) {
        if (!str.matches("^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")) {
            throw new OrderException(PHONE_NOT_FORMAT);
        }
    }

    /**
     *  判断评价类型reviewType
     */
    public static String  reviewType(Integer reviewType) {
        //1:全部 2:好评  3:中评 4:差评
        if (reviewType==2) {
           return "4.0-5";
        }else if (reviewType==3) {
           return "3.0-4.0";
        }else if (reviewType==4) {
           return "2.0-3.0";
        }else{
            return "0-100";
        }
    }

    /**
     * 邮箱相等
     */
    public static void IsEqual(String str1, String str2) {
        if (!str1.equals(str2)) {
            throw new OrderException(PWD_NOT_EQUAL);
        }
    }

    /**
     *  邮箱验证码相等
     */
    public static void IsEqualVerificationCode(String mailOrPhone, String yzm) {
        Boolean flag = false;
        if (RedisUtil.IsExist(mailOrPhone)) {
            if (RedisUtil.get(mailOrPhone).toString().equals(yzm)) {
                flag = true;
            }
        }
        if (!flag) {
            throw new OrderException(YZM_NOT_EQUAL);
        }
    }
}
