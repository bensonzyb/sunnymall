package priv.jesse.mall.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;

public class FileUtil {
	

	/**
     * 淇濆瓨涓婁紶鐨勬枃浠�
     *
     * @param file
     * @return 鏂囦欢涓嬭浇鐨剈rl
     * @throws Exception
     */
    public static String saveFile(MultipartFile file) throws Exception {
    	System.out.println("uploadImagePath=="+PropertySet.uploadImagePath);
        if (file == null || file.isEmpty())
            return "";
        String uploadPath=PropertySet.uploadImagePath;// + File.separator;
        //File target = new File("file");
        File target = new File(uploadPath);
        if (!target.isDirectory()) {
            target.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(file.getBytes());
        String fileName = (Helper.bytesToHex(md.digest(),0,md.digest().length-1)) + "." + getPostfix(originalFilename);
        // File file1 = new File(target.getPath() + "/" + fileName);(鑰佺殑鍐欐硶)
        File file1 = new File(target.getPath() + File.separator + fileName);
        try {
        	Files.write(Paths.get(file1.toURI()), file.getBytes(), StandardOpenOption.CREATE_NEW);
        }catch(Exception e){//澶勭悊鏈嶅姟鍣ㄤ笂宸茬粡鏈夎鍥剧墖鐨勬儏鍐�
        	long  timeNew =  System.currentTimeMillis()/ 1000; 
        	fileName=timeNew+fileName;
            file1 = new File(target.getPath() + File.separator + fileName);
        	Files.write(Paths.get(file1.toURI()), file.getBytes(), StandardOpenOption.CREATE_NEW);
        }
        
        return PropertySet.getImageUrl + fileName;
    }

    /**
     * 鑾峰緱鏂囦欢鐨勫悗缂�鍚�
     *
     * @param fileName
     * @return
     */
    public static String getPostfix(String fileName) {
        if (fileName == null || "".equals(fileName.trim())) {
            return "";
        }
        if (fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        }
        return "";
    }

}
