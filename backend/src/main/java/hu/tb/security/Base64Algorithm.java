package hu.tb.security;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Tivadar Bocz on 2016.09.06..
 */
public class Base64Algorithm {
    private static Logger logger = LoggerFactory.getLogger(Base64Algorithm.class);

    public static String decode(String str){
        try {
            return new String(Base64.decode(str.getBytes()));
        } catch (Base64DecodingException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static String encode(String str){
        return new String(Base64.encode(str.getBytes()));
    }
}
