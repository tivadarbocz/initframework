package hu.tb.security;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Tivadar Bocz on 2016.09.06..
 */
public class Crypto {
    private static Logger logger = LoggerFactory.getLogger(Crypto.class);

    public static String base64Decode(String str){
        try {
            return new String(Base64.decode(str.getBytes()));
        } catch (Base64DecodingException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static String base64Encode(String str){
        return new String(Base64.encode(str.getBytes()));
    }

    public static String md5Encode(String str){
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
