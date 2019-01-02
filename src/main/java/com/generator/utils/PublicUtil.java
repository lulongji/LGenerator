package com.generator.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取本机ip
 *
 * @author LongJi.LU (lulongji2011@163.com)
 * @version 2016年4月26日 上午11:02:58
 */
public class PublicUtil {

    public static String getPorjectPath() {
        String nowpath = "";
        nowpath = System.getProperty("user.dir") + "/";
        /**
         * System.out.println("java版本号：" + System.getProperty("java.version"));
         * // java版本号 System.out.println("Java提供商名称：" +
         * System.getProperty("java.vendor")); // Java提供商名称
         * System.out.println("Java提供商网站：" +
         * System.getProperty("java.vendor.url")); // Java提供商网站
         * System.out.println("jre目录：" + System.getProperty("java.home")); //
         * Java，哦，应该是jre目录 System.out.println("Java虚拟机规范版本号：" +
         * System.getProperty("java.vm.specification.version")); // Java虚拟机规范版本号
         * System.out.println("Java虚拟机规范提供商：" +
         * System.getProperty("java.vm.specification.vendor")); // Java虚拟机规范提供商
         * System.out.println("Java虚拟机规范名称：" +
         * System.getProperty("java.vm.specification.name")); // Java虚拟机规范名称
         * System.out.println("Java虚拟机版本号：" +
         * System.getProperty("java.vm.version")); // Java虚拟机版本号
         * System.out.println("Java虚拟机提供商：" +
         * System.getProperty("java.vm.vendor")); // Java虚拟机提供商
         * System.out.println("Java虚拟机名称：" +
         * System.getProperty("java.vm.name")); // Java虚拟机名称
         * System.out.println("Java规范版本号：" +
         * System.getProperty("java.specification.version")); // Java规范版本号
         * System.out.println("Java规范提供商：" +
         * System.getProperty("java.specification.vendor")); // Java规范提供商
         * System.out.println("Java规范名称：" +
         * System.getProperty("java.specification.name")); // Java规范名称
         * System.out.println("Java类版本号：" +
         * System.getProperty("java.class.version")); // Java类版本号
         * System.out.println("Java类路径：" +
         * System.getProperty("java.class.path")); // Java类路径
         * System.out.println("Java lib路径：" +
         * System.getProperty("java.library.path")); // Java // lib路径
         * System.out.println("Java输入输出临时路径：" +
         * System.getProperty("java.io.tmpdir")); // Java输入输出临时路径
         * System.out.println("Java编译器：" + System.getProperty("java.compiler"));
         * // Java编译器 System.out.println("Java执行路径：" +
         * System.getProperty("java.ext.dirs")); // Java执行路径
         * System.out.println("操作系统名称：" + System.getProperty("os.name")); //
         * 操作系统名称 System.out.println("操作系统的架构：" +
         * System.getProperty("os.arch")); // 操作系统的架构
         * System.out.println("操作系统版本号：" + System.getProperty("os.version")); //
         * 操作系统版本号 System.out.println("文件分隔符：" +
         * System.getProperty("file.separator")); // 文件分隔符
         * System.out.println("路径分隔符：" + System.getProperty("path.separator"));
         * // 路径分隔符 System.out.println("直线分隔符：" +
         * System.getProperty("line.separator")); // 直线分隔符
         * System.out.println("操作系统用户名：" + System.getProperty("user.name")); //
         * 用户名 System.out.println("操作系统用户的主目录：" +
         * System.getProperty("user.home")); // 用户的主目录
         * System.out.println("当前程序所在目录：" + System.getProperty("user.dir")); //
         * 当前程序所在目录
         */
        return nowpath;
    }

    /**
     * 获取本机ip
     *
     * @return
     */
    public static String getIp() {
        String ip = "";
        try {
            InetAddress inet = InetAddress.getLocalHost();
            ip = inet.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ip;
    }

    /**
     * 把下划线后字母大写
     *
     * @param name
     * @param anotherName
     * @return
     */
    public static String getName(String name, String anotherName) {
        name = anotherName;
        // 如果最后一个是_ 不做转换
        if (name.indexOf("_") > 0 && name.length() != name.indexOf("_") + 1) {
            int lengthPlace = name.indexOf("_");
            name = name.replaceFirst("_", "");
            String s = name.substring(lengthPlace, lengthPlace + 1);
            s = s.toUpperCase();
            anotherName = name.substring(0, lengthPlace) + s + name.substring(lengthPlace + 1);
        } else {
            return anotherName;
        }
        return getName(name, anotherName);
    }

    /**
     * 把输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    public static String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }


}