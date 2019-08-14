package org.mybatis.generator.ui;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//加载属性文件
public class AppProperties{

  static Properties pps = new Properties();

  public void init(){
    try {
     // Resource resource = new ClassPathResource("/application.properties");//
      //props = PropertiesLoaderUtils.loadProperties(resource);
      pps.load(this.getClass().getResourceAsStream("/application.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String getValue(String key){
    return pps.getProperty(key);
  }

}
