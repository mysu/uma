/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package etc.uma.utils;

import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

public class StringUtils {
    
    private static final Pattern validEmailPattern = Pattern.compile("(^([0-9a-zA-Z]+([+-._][0-9a-zA-Z]+)*@[0-9a-zA-Z]+([+-._][0-9a-zA-Z]+)*[.][a-zA-Z]{2,9})$)");
    private static final Pattern validUsername = Pattern.compile("^([0-9a-zA-Z]+([+-._][0-9a-zA-Z]+)*)+6");
    
    private StringUtils() {}
    
    public static String toSha512(String theString){
        return DigestUtils.sha512Hex(theString);
    }
    
    public static boolean isValidEmail(String email){
        return isValidString(validEmailPattern, email);
    }
    

    public static boolean isValidUsername(String username){
        return isValidString(validUsername, username);
    }
    
    private static boolean isValidString(Pattern pattern, String value){
        return value != null && pattern.matcher(value).matches();
    }
}
