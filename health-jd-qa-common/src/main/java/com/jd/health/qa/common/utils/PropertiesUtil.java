package com.jd.health.qa.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesUtil {

    /**
     * 将配置文件的内容放入系统变量中
     */
    public void loadProperties() {
        String confPath = getClass().getResource("/").getPath();
        File dir = new File(confPath);
        File[] files = dir.listFiles(
                new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".properties");
                    }
                }
        );

        for (File f: files) {
            Properties properties = loadFile(f);
            if (properties != null) {
                properties.keySet().forEach(
                        k -> {
                            String key = k.toString();
                            String value = properties.get(key) == null ? "" :  properties.get(key).toString();
                            setSystemProperties(key, value);
                        }
                );
            }
        }
    }

    private Properties loadFile (File file) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            log.error("文件："+file.getAbsolutePath() + "未找到");
            e.printStackTrace();
            return null;
        }
        return properties;
    }

    private void setSystemProperties(String key, String value) {
        log.info(String.format("配置key: %s, value: %s", key, value));
        System.setProperty(key, value);
    }
}
