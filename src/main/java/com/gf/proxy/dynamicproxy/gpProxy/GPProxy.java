package com.gf.proxy.dynamicproxy.gpProxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GPProxy {

    public static final String In = "\r\n";

    public static Object newProxyInstance(GPClassLoader classLoader,Class<?>[] interfaces,GPInvocationHandler h){
        try{
            String src = generateSrc(interfaces);

            String filePath = GPProxy.class.getResource("").getPath();
//           System.out.println(filePath);
            File f = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            //3、把生成的.java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manage = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manage.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null,manage,null,null,null,iterable);
            task.call();
            manage.close();

            //4、编译生成的.class文件加载到JVM中来
            Class proxyClass =  classLoader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(GPInvocationHandler.class);
            f.delete();

            //5、返回字节码重组以后的新的代理对象
            return c.newInstance(h);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        String strName = null;
        for (Class clazz:interfaces){
            strName = clazz.getName()+",";

        }
        strName = strName.substring(0,strName.length()-1);
        sb.append("package com.gf.proxy.dynamicproxy.gpProxy;"+In);
        sb.append("import com.gf.proxy.Room;"+In);
        sb.append("import java.lang.reflect.*;"+In);
        sb.append("public class $Proxy0 implements "+strName+"{"+In);
        sb.append("GPInvocationHandler h;" + In);
        sb.append("public $Proxy0(GPInvocationHandler h) {"+In);
        sb.append("this.h = h;");
        sb.append("}"+In);
        StringBuilder paramsNames = new StringBuilder();
        StringBuffer paramValues = new StringBuffer();
        StringBuffer paramClasses = new StringBuffer();
        for (Class clazz:interfaces){
            for(Method method: clazz.getMethods()){
                for (Class param: method.getParameterTypes()){
                    String paramValue = toLowerCaseFirstOne(param.getSimpleName());
                    paramsNames.append(param.getName() + " "+toLowerCaseFirstOne(param.getSimpleName())+", ");
                    paramValues.append(paramValue);
                    paramClasses.append(param.getName() + ".class");
                }
                int i = method.getParameterTypes().length;
                if( method.getParameterTypes().length != 0) {
                    paramsNames.toString().substring(0, paramsNames.length() - 1);
                    paramValues.toString().substring(0, paramsNames.length() - 1);
                    paramClasses.toString().substring(0, paramsNames.length() - 1);
                }
                sb.append("public "+method.getReturnType().getName() +"  "+method.getName()+"("+paramsNames.toString()+")"+"{"+In);
                sb.append("try{" + In);
                sb.append("Method m = "+clazz.getName()+".class.getMethod(\""+ method.getName() + "\",new Class[]{" + paramClasses.toString() + "});" + In);
                sb.append((hasReturnValue(method.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})",method.getReturnType()) + ";" + In);
                sb.append("}catch(Error _ex) { }");
                sb.append("catch(Throwable e){" + In);
                sb.append("throw new UndeclaredThrowableException(e);" + In);
                sb.append("}");
                sb.append(getReturnEmptyCode(method.getReturnType()));
                sb.append("}");

            }

        }
        sb.append("}" + In);
        return sb.toString();
    }

    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    private static boolean hasReturnValue(Class<?> clazz){
        return clazz != void.class;
    }
    private static String getCaseCode(String code,Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "((" + mappings.get(returnClass).getName() +  ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code;
    }
    private static Map<Class,Class> mappings = new HashMap<Class, Class>();
    static {
        mappings.put(int.class,Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "return 0;";
        }else if(returnClass == void.class){
            return "";
        }else {
            return "return null;";
        }
    }
}
