package uma.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class StringUtils {
    private StringUtils() {}
    
    public static String toSha512(String theString){
        return DigestUtils.sha512Hex(theString);
    }
}
