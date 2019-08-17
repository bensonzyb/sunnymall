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
    
    public static String notify_phone;
    
    public static String  ACCOUNT_SID;
    
    public static String  AUTH_TOKEN;
    
    public static String  AppID;
    
    public static String  TEMPLATEID;
    
    public static String  send_max;
    
    
    @Value("${notify_phone}")
    public  void setNotify_phone(String notify_phone) {
		PropertySet.notify_phone = notify_phone;
	}

    @Value("${ACCOUNT_SID}")
	public  void setACCOUNT_SID(String aCCOUNT_SID) {
		PropertySet.ACCOUNT_SID = aCCOUNT_SID;
	}

    @Value("${AUTH_TOKEN}")
	public  void setAUTH_TOKEN(String aUTH_TOKEN) {
		PropertySet.AUTH_TOKEN = aUTH_TOKEN;
	}
	
	@Value("${AppID}")
	public  void setAppID(String appID) {
		PropertySet.AppID = appID;
	}
	
	@Value("${TEMPLATEID}")
	public  void setTEMPLATEID(String tEMPLATEID) {
		PropertySet.TEMPLATEID = tEMPLATEID;
	}

	@Value("${send_max}")
	public  void setSend_max(String send_max) {
		PropertySet.send_max = send_max;
	}

	@Value("${upload.image.path}")
     public void setUploadImagePath(String uploadImagePath) {
    	PropertySet.uploadImagePath = uploadImagePath;
    }
    
    @Value("${get.image.url}")
    public void setGetImageUrl(String getImageUrl) {
   	    PropertySet.getImageUrl = getImageUrl;
   }
    
  



}
