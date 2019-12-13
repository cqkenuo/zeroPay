package com.zero.pay.Util;

public class Constants {
    /**
     * 动态斜杠，用于不同运行环境，Linux环境用左斜杠 Windows环境用右斜杠
     */
    public static String SLASH = "\"";
    /**
     * 左斜杠
     */
    public static String SLASH_LEFT = "/";
    /**
     * 图片根目录地址，附加在文件夹+文件名前
     */
    public static String IMG_PATH = "http://122.51.129.31/img";
    // 随机字符串字典
    private final static String GENERATE_STR = "0123456789qwertyuipasdfghjklzxcvbnmQWERTYUIPASDGFHJKLZXCVBNM";

    /** 随机数生成算法 */
    public static String randomStrGenerate(int length) {
        String randomStr = "";
        for (int i = 0; i < length; i++) {
            char ch = GENERATE_STR.charAt((int) (Math.random() * 59));
            randomStr += String.valueOf(ch);
        }
        return randomStr;
    }
    /**
     * 提供用户id+图片地址自动转义成图片路径
     */
    public static String imgToUrl(String directory, String imgName) {
        return Constants.SLASH_LEFT + directory + Constants.SLASH_LEFT + imgName;
    }
}
