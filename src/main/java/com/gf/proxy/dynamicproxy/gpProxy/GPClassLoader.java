package com.gf.proxy.dynamicproxy.gpProxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 重写classLoader加载新的类
 * 把类转换成文件 再把文件通过字节码读取 转换成类对象
 */
public class GPClassLoader extends ClassLoader{

    private File classPathFile;

    public GPClassLoader(){
        //获取类路径
        String classPath = GPClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String s) throws ClassNotFoundException {
        //寻找classname
       String className = GPClassLoader.class.getPackage().getName()+""+s;
       if(className != null){
           File classFile = new File(classPathFile,s.replace("\\.","/") + ".class");
           if(classFile.exists()){
               FileInputStream in = null;
               ByteArrayOutputStream out = null;
               try{
                   in = new FileInputStream(classFile);
                   out = new ByteArrayOutputStream();
                   byte [] buff = new byte[1024];
                   int len;
                   while ((len = in.read(buff)) != -1){
                       out.write(buff,0,len);
                   }
                   return defineClass(className,out.toByteArray(),0,out.size());
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
       }
       return null;

    }
}
