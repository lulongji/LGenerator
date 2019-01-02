package com.generator.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库实体反射
 *
 * @author LongJi.LU (lulongji2011@163.com)
 * @version 2016年5月11日 下午6:26:17
 */
public class GenEntityMysql {

    String packageOutPath = "model.";

    String model = ".model";
    /**
     * 列名数组
     */
    private String[] colnames;
    /**
     * 列名类型数组
     */
    private String[] colTypes;
    /**
     * 列名大小数组
     */
    private int[] colSizes;
    /**
     * 是否需要导入包java.util.*
     */
    private boolean f_util = false;
    /**
     * 是否需要导入包java.sql.*
     */
    private boolean f_sql = false;


    public GenEntityMysql(String url, String username, String password, String driverClassName, String tablename,
                          String modelName, String packageName, String rootPath) {
        Connection con;
        String sql = "select * from " + tablename;
        PreparedStatement pStemt;
        try {
            try {
                Class.forName(driverClassName);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            con = DriverManager.getConnection(url, username, password);
            pStemt = con.prepareStatement(sql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);

                if (colTypes[i].equalsIgnoreCase("datetime")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image") ||
                        colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }

            String content = parse(modelName, packageName);

            try {
                String path = this.getClass().getResource("").getPath();
                System.out.println(path);
                System.out.println("src/?/" + path.substring(path.lastIndexOf("/com/", path.length())));
                String outputPath = rootPath + (packageName + model).replace(".", "/") + "/" + initcap(modelName) + ".java";
                System.out.println(outputPath);
                File file = new File(outputPath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                FileWriter fw = new FileWriter(outputPath);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(content);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 生成实体类主体代码
     *
     * @param modelName
     * @param packageName
     * @return
     */
    private String parse(String modelName, String packageName) {
        StringBuffer sb = new StringBuffer();
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("package " + (packageName) + ".model" + ";\r\n");
        sb.append("\r\npublic class " + initcap(modelName) + "{\r\n");
        processAllAttrs(sb);
        sb.append("\r\n");
        processAllMethod(sb);
        sb.append("}\r\n");
        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";\r\n");
        }
    }

    /**
     * 功能：生成所有方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + "){\r\n");
            sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
        }
    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }

        return null;
    }

    /**
     * @param url
     * @param username
     * @param password
     * @param driverClassName
     * @param tablename
     * @return
     */
    public static List<String[]> getColumn(String url, String username, String password, String driverClassName, String tablename) {
        List<String[]> fieldList = new ArrayList<>();
        try {
            Class.forName(driverClassName).newInstance();
            String sql = "select * from " + tablename;
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                fieldList.add(rsmd.getColumnName(i + 1).split(",lulongji,"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldList;
    }


}