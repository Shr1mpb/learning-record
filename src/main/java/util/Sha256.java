package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {
    public static final String passwordSalt = "wef-food-booking13579";
    public static String sha256(String input) {
        try {
            //加盐
            input += passwordSalt;
            // 创建SHA-256 MessageDigest实例
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // 计算哈希值
            byte[] hashBytes = digest.digest(input.getBytes());
            // 将字节数组转化为16进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {//确保每个字节都转为两位16进制数
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(sha256("123456"));
    }
}
