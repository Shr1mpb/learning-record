package homework.h18;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Set;

public class MyClassFactory {
    private String path;
    private Properties properties;
    public MyClassFactory(String path) {
        this.path = path;
        try {
            init();
        }catch (Exception e) {
            throw new RuntimeException("初始化 " + getClass().getSimpleName() + " 类失败！");
        }
    }

    private void init() throws IOException {
        properties = new Properties();
        FileInputStream inStream = new FileInputStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(inStream, "UTF-8");
        properties.load(inputStreamReader);
        inStream.close();
    }

    public <T> T createInstance(Class<T> clazz) {

        T instance = null;
        try{
            if (clazz != null) {
                instance = clazz.newInstance();
                // 获取配置文件中已经有的属性和它的数值
                Set<String> strings = properties.stringPropertyNames();
                for (String string : strings) {
//                    com.huawei.classroom.student.h18.Car.price=200000
                    string = string.trim();
                    int lastDot = string.lastIndexOf(".");
                    int secondDot = string.lastIndexOf(".", lastDot - 1);
                    String className = string.substring(secondDot + 1, lastDot);
                    if (!className.equals(clazz.getSimpleName())) {
                        continue;
                    }
                    String fieldName = string.substring(lastDot + 1);
                    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    String property = properties.getProperty(string);
                    Method method;
                    if (property.startsWith("\"")) {
                        method = instance.getClass().getMethod(methodName, String.class);
                        property = property.substring(1, property.length() - 1);
                        method.invoke(instance, property);
                    }else{
                        method = instance.getClass().getDeclaredMethod(methodName,Integer.class);
                        method.invoke(instance, Integer.parseInt(property));
                    }

                }
            }
        }catch (Exception e) {
            throw new RuntimeException("创建 " + clazz.getClass() + " 类对象失败！");
//            e.printStackTrace();
        }
        return instance;
    }
}
