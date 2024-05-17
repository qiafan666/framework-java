package com.ning.web.jotato.common.utils;


import com.ning.web.jotato.base.enums.CharsetEnum;
import com.ning.web.jotato.base.utils.Exceptions;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 流工具类
 *
 * @author L.cm
 */
public class IoUtil extends org.springframework.util.StreamUtils {

    /**
     * closeQuietly
     *
     * @param closeable 自动关闭
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        if (closeable instanceof Flushable) {
            try {
                ((Flushable) closeable).flush();
            } catch (IOException ignored) {
                // ignore
            }
        }
        try {
            closeable.close();
        } catch (IOException ignored) {
            // ignore
        }
    }

    /**
     * InputStream to String utf-8
     *
     * @param input the <code>InputStream</code> to read from
     * @return the requested String
     */
    public static String readToString(InputStream input) throws Throwable {
        return readToString(input, CharsetEnum.UTF_8.get());
    }

    /**
     * InputStream to String
     *
     * @param input   the <code>InputStream</code> to read from
     * @param charset the <code>Charset</code>
     * @return the requested String
     */
    public static String readToString(InputStream input, Charset charset) throws Throwable {
        try {
            return IoUtil.copyToString(input, charset);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        } finally {
            IoUtil.closeQuietly(input);
        }
    }

    public static byte[] readToByteArray(InputStream input) throws Throwable {
        try {
            return IoUtil.copyToByteArray(input);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        } finally {
            IoUtil.closeQuietly(input);
        }
    }

    /**
     * Writes chars from a <code>String</code> to bytes on an
     * <code>OutputStream</code> using the specified character encoding.
     * <p>
     * This method uses {@link String#getBytes(String)}.
     * </p>
     * @param data     the <code>String</code> to write, null ignored
     * @param output   the <code>OutputStream</code> to write to
     * @param encoding the encoding to use, null means platform default
     * @throws NullPointerException if output is null
     * @throws IOException          if an I/O error occurs
     */
    public static void write(final String data, final OutputStream output, final Charset encoding) throws IOException {
        if (data != null) {
            output.write(data.getBytes(encoding));
        }
    }
}
