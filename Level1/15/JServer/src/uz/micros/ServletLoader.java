package uz.micros;

import uz.micros.servletapi.Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

public class ServletLoader {
    public Servlet loadServlet(File jarFile) {
        Servlet res = null;

        try {
            Class<?> servletClass = findClassInJar(Servlet.class, jarFile.getCanonicalPath());
            res = (Servlet)servletClass.newInstance();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return res;
    }

    private Class<?> findClassInJar(Class<Servlet> servletClass, String jarName) throws IOException {
        Class<?> res = null;

        if (!jarName.equals("")){
            ClassLoader loader = this.getClass().getClassLoader();
            String jarFullName = File.separator + jarName;
            URL url = new URL("jar:file:" + jarFullName + "!/");
            URLClassLoader ucl = new URLClassLoader(new URL[]{url}, loader);
            JarInputStream jarFile = new JarInputStream(new FileInputStream(jarFullName));
            JarEntry jarEntry;

            while(true){
                jarEntry = jarFile.getNextJarEntry();

                if (jarEntry == null) break;

                if (jarEntry.getName().endsWith(".class")){
                    String className = jarEntry.getName().replaceAll("/", "\\.");
                    className = className.substring(0, className.length() - 6);
                    if (!className.contains("$")){
                        try {
                            Class<?> myClass = Class.forName(className, true, ucl);
                            if (servletClass.isAssignableFrom(myClass)) {
                                res = myClass;
                                break;
                            }
                        } catch (ClassNotFoundException e) {
                            //e.printStackTrace();
                        }
                    }
                }
            }
        }

        return res;
    }
}
