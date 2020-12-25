package cn.cc.ccaudio.utils;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Properties;

/**
 * @author c.c.
 * @date 2020/12/25
 */
public class PropertitesUtil {
    public static Properties props;

    static {
        try {
            readPropertiesFile("/method.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties readPropertiesFile(String filePath) throws FileNotFoundException, IOException {
        try {
            ClassPathResource classPathResource = new ClassPathResource(filePath);
            InputStream inputStream = classPathResource.getInputStream();
            props = new Properties();
            props.load(new InputStreamReader(inputStream, "UTF-8"));
            return props;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPropertys(String key) {
        String methodName = props.getProperty(key);
        return methodName;
    }

}
