package priv.jesse.mall.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 属性获取静态变量赋值
 * @author ben
 *
 */
@Component
public class PropertySet {
	
    public static String uploadImagePath;
    
    public static String getImageUrl;

    @Value("${upload.image.path}")
     public void setUploadImagePath(String uploadImagePath) {
    	PropertySet.uploadImagePath = uploadImagePath;
    }
    
    @Value("${get.image.url}")
    public void setGetImageUrl(String getImageUrl) {
   	    PropertySet.getImageUrl = getImageUrl;
   }



}
