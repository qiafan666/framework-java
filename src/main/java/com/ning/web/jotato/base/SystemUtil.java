package com.ning.web.jotato.base;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

public class SystemUtil {
    public static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    public static final boolean IS_MAC = getOsName() != null && getOsName().toLowerCase().contains("mac");
    public static final boolean IS_WIN = getOsName() != null && (getOsName().toLowerCase().contains("win") || getOsName().toLowerCase().contains("nt"));
    public static final boolean IS_LINUX;
    public static final String FILE_SEPARATOR;
    public static final String LINE_SEPARATOR;

    private SystemUtil() {
    }

    public static String getJavaVMName() {
        return System.getProperty("java.vm.name");
    }

    public static String getJavaVMVersion() {
        return System.getProperty("java.vm.version");
    }

    public static String getJavaVMVendor() {
        return System.getProperty("java.vm.vendor");
    }

    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }

    public static String getTempDir() {
        return System.getProperty("java.io.tmpdir");
    }

    public static String getOsName() {
        return System.getProperty("os.name");
    }

    public static String getOsVersion() {
        return System.getProperty("os.version");
    }

    public static String getUserName() {
        return System.getProperty("user.name");
    }

    public static String getJavaHome() {
        return System.getProperty("java.home");
    }

    public static String getArchDataModel() {
        return System.getProperty("sun.arch.data.model");
    }

    public static String getUserLanguage() {
        return System.getProperty("user.language");
    }

    public static int getProcessorCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    public static void printAllStackTrack() {
        Map<Thread, StackTraceElement[]> stMap = Thread.getAllStackTraces();
        StackTraceElement[] elements = null;
        Iterator var2 = stMap.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<Thread, StackTraceElement[]> entry = (Map.Entry)var2.next();
            Thread thread = (Thread)entry.getKey();
            elements = (StackTraceElement[])entry.getValue();
            System.out.println(thread);
            StackTraceElement[] var5 = elements;
            int var6 = elements.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                StackTraceElement element = var5[var7];
                System.out.println("\t" + element);
            }
        }

    }

    public static long getJavaVMUptime() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return runtimeMXBean != null ? runtimeMXBean.getUptime() : -1L;
    }

    public static long getJavaVMStartTime() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return runtimeMXBean != null ? runtimeMXBean.getStartTime() : -1L;
    }

    static {
        IS_LINUX = !IS_MAC && !IS_WIN;
        FILE_SEPARATOR = System.getProperty("file.separator");
        LINE_SEPARATOR = System.getProperty("line.separator");
    }
}
