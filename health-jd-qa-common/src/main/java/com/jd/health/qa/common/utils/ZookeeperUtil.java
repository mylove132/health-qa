package com.jd.health.qa.common.utils;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class ZookeeperUtil {


    private final String path = System.getProperty("user.dir") + "/conf/zk.properties";
    public void load(String serverName) {

        System.setProperty("projectName", serverName);
        Properties properties = new Properties();
        ZookeeperConfigGroup group2 = null;
        try {
            InputStream e = ZookeeperUtil.class.getClassLoader().getResourceAsStream("zk.properties");
            if (e != null) {
                properties.load(e);
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
                properties.load(bufferedReader);
            }
            String zkAddress = properties.getProperty("zk.address");
            String zkVersion = properties.getProperty("zk.version");
            ZookeeperConfigProfile zookeeperConfigProfile = new ZookeeperConfigProfile(zkAddress, "/xdf/"+serverName, zkVersion);
            group2 = new ZookeeperConfigGroup(zookeeperConfigProfile, "unchange");
            for (Map.Entry<String, String> entry : group2.exportProperties().entrySet()) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        } catch (Exception var2) {
            log.error("zkCLient: 未找到zk.properties配置文件", var2);
        } finally {
            if (group2 != null) {
                group2.close();
            }
        }

    }
}
