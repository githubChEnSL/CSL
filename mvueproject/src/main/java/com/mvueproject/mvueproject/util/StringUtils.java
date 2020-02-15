package com.mvueproject.mvueproject.util;

/**
 * @ClassName StringUtils
 * @Description TODO
 * @Author 隔山海
 * @date 2020/2/12 1:11
 * @Version 1.0
 */
public class StringUtils {
    /**
     * 驼峰转下划线
     * @param param 字符串参数
     * @return 返回下划线组成的字符串
     */
    public static String upperCharToUnderLine(String param){
        StringBuilder sb = new StringBuilder(param);
        int temp = 0;
        if(!param.contains("_")){
            for (int i=0;i<param.length();i++){
                if(Character.isUpperCase(param.charAt(i))){
                    sb.insert(i+temp,"_");
                    temp+=1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }
}
