package com.jun.my_mango.common.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Description: IO相关工具类
 * @author: Liusu
 * @date: 2022年11月11日13:22
 */
public class IOUtils {
    /**
     * 关闭对象，连接
     * @param closeable
     */
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            // ignore
        }
    }
}
