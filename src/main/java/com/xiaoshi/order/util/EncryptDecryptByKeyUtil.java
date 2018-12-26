package com.xiaoshi.order.util;

import com.xiaoshi.order.exception.OrderException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import static com.xiaoshi.order.constant.OrderCode.PWD_IS_FAIL;
import static com.xiaoshi.order.constant.OrderCode.PWD_NOT_SUCCESS;

/**
 * 加密解密工具类
*/
public class EncryptDecryptByKeyUtil {
    private final static String DES = "DES";
    private static boolean FLAG = true;

    /**
     * 生成MD5
     */
    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("生成md5失败！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    /**
     *  根据键值进行加密
     */
    public static String encrypt(String data) {
        try {
            String key = RandomSumUtil.getRandonString(8);
            byte[] bt = encrypt(data.getBytes(), key.getBytes());
            //注意：在加密和解密的时候使用sun的BASE64Encoder（）进行编码和解码不然会有乱码
            //网上查看了很多实例，都没有编码和解码，也说没有乱码问题，而我这里出现了乱码，所以使用BASE64Encoder（）进行了编码解码
            String strs = new BASE64Encoder().encode(bt);
            return key + "," + strs;
        } catch (Exception e) {
            if (FLAG) {
                try {
                    String key = RandomSumUtil.getRandonString(8);
                    byte[] bt = encrypt(data.getBytes(), key.getBytes());
                    //注意：在加密和解密的时候使用sun的BASE64Encoder（）进行编码和解码不然会有乱码
                    //网上查看了很多实例，都没有编码和解码，也说没有乱码问题，而我这里出现了乱码，所以使用BASE64Encoder（）进行了编码解码
                    String strs = new BASE64Encoder().encode(bt);
                    return key + "," + strs;
                } catch (Exception ex) {
                    throw new OrderException(PWD_NOT_SUCCESS);
                }
            } else {
                FLAG = false;
                throw new OrderException(PWD_NOT_SUCCESS);
            }
        }
    }


    /**
     *  根据键值进行加密
     */
    public static String encrypt(String data,String key) {
        try {
            byte[] bt = encrypt(data.getBytes(), key.getBytes());
            //注意：在加密和解密的时候使用sun的BASE64Encoder（）进行编码和解码不然会有乱码
            //网上查看了很多实例，都没有编码和解码，也说没有乱码问题，而我这里出现了乱码，所以使用BASE64Encoder（）进行了编码解码
            String strs = new BASE64Encoder().encode(bt);
            return strs;
        } catch (Exception e) {
            throw new OrderException(PWD_NOT_SUCCESS);
        }


    }
    /**
     *  根据键值进行解密
     */
    public static String decrypt(String data, String key) {
        if (data == null)
            return null;
        //注意：在加密和解密的时候使用sun的BASE64Encoder（）进行编码和解码不然会有乱码
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] buf = decoder.decodeBuffer(data);
            byte[] bt = decrypt(buf, key.getBytes());
            return new String(bt);
        }catch (Exception e){
            throw new  OrderException(PWD_IS_FAIL);
        }
    }

    /**
     *  根据键值进行加密
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        //正式执行加密操作
        return cipher.doFinal(data);
    }

    /**
     *  根据键值进行解密
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 正式进行解密操作
        return cipher.doFinal(data);
    }

}
