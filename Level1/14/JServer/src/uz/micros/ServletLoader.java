package uz.micros;

import uz.micros.servletapi.Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ServletLoader {
    public Servlet loadServlet(File jarFile) {
        Servlet res = null;

        try {
            Class<?> servletClass = findClassesInJar(Servlet.class, jarFile.getCanonicalPath());
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

    private  Class<?> findClassesInJar(final Class<?> baseInterface, final String jarName) throws IOException {
        final List<String> classesTobeReturned = new ArrayList<String>();
        if (!jarName.equals("")) {
            final String jarFullPath = File.separator + jarName;
            final ClassLoader classLoader = this.getClass().getClassLoader();
            JarInputStream jarFile = null;
            URLClassLoader ucl = null;
            final URL url = new URL("jar:file:" + jarFullPath + "!/");
            ucl = new URLClassLoader(new URL[]{url}, classLoader);
            jarFile = new JarInputStream(new FileInputStream(jarFullPath));
            JarEntry jarEntry;
            while (true) {
                jarEntry = jarFile.getNextJarEntry();
                if (jarEntry == null)
                    break;
                if (jarEntry.getName().endsWith(".class")) {
                    String classname = jarEntry.getName().replaceAll("/", "\\.");
                    classname = classname.substring(0, classname.length() - 6);
                    if (!classname.contains("$")) {
                        try {
                            final Class<?> myLoadedClass = Class.forName(classname, true, ucl);
                            if (baseInterface.isAssignableFrom(myLoadedClass)) {
                                return myLoadedClass;
                            }
                        } catch (final ClassNotFoundException e) {

                        }
                    }
                }
            }
            return null;
        }

        return null;
    }
}
