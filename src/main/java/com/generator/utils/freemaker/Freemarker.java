package com.generator.utils.freemaker;

import com.generator.utils.PathUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * freemaker 模版
 *
 * @author lu
 */
public class Freemarker {

    /**
     * 打印到控制台(测试用)
     *
     * @param ftlName
     */
    public static void print(String ftlName, Map<String, Object> root, String ftlPath) throws Exception {
        try {
            Template temp = getTemplate(ftlName, ftlPath);
            temp.process(root, new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出到输出到文件
     *
     * @param ftlName  ftl文件名
     * @param root     传入的map
     * @param outFile  输出后的文件全部路径
     * @param filePath 输出前的文件上部路径
     */
    public static void printFile(String ftlName, Map<String, Object> root, String filePath, String outFile, String ftlPath) throws Exception {
        try {
            File file = new File(PathUtil.getClasspath() + filePath + outFile);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            Template template = getTemplate(ftlName, ftlPath);
            template.process(root, out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出到指定文件
     *
     * @param ftlName ftl文件名
     * @param root    传入的map
     * @param outFile 输出后的文件全部路径
     */
    public static void printFilePath(String ftlName,
                                     Map<String, Object> root,
                                     String rootPath,
                                     String outFile,
                                     String ftlPath) throws Exception {
        try {
            File file = new File(rootPath + outFile);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            Template template = getTemplate(ftlName, ftlPath);
            template.process(root, out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过文件名加载模版
     *
     * @param ftlName
     */
    @SuppressWarnings("deprecation")
    public static Template getTemplate(String ftlName, String ftlPath) throws Exception {
        try {
            Configuration cfg = new Configuration();
            cfg.setEncoding(Locale.CHINA, "utf-8");
            cfg.setDirectoryForTemplateLoading(new File(PathUtil.getClassResources() + ftlPath));
            Template temp = cfg.getTemplate(ftlName);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
