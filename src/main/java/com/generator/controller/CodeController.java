package com.generator.controller;

import com.generator.utils.DelAllFile;
import com.generator.utils.GenEntityMysql;
import com.generator.utils.PublicUtil;
import com.generator.utils.freemaker.Freemarker;
import com.yuntongxun.base.bean.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 代码生成
 * @Author: lulongji
 * @Date: Created in 14:53 2018/11/20
 */
@RestController
public class CodeController {

    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);

    /**
     * 生成代码路径
     */
    @Value("${rootPath}")
    String rootPath;
    /**
     * 表名
     */
    @Value("${dbTableName}")
    String dbTableName;
    /**
     * 包名
     */
    @Value("${packageName}")
    String packageName;
    /**
     * 实体名称
     */
    @Value("${domainName}")
    String domainName;
    /**
     * 连接地址
     */
    @Value("${spring.datasource.url}")
    String dbUrl;
    /**
     * 数据库用户名
     */
    @Value("${spring.datasource.username}")
    String dbName;
    /**
     * 数据库密码
     */
    @Value("${spring.datasource.password}")
    String dbPass;
    /**
     * 数据库驱动
     */
    @Value("${spring.datasource.driver-class-name}")
    String dbDriver;


    /**
     * 代码生成
     *
     * @return
     */
    @RequestMapping("/createcode")
    public Result CreateCode() {
        Result result = Result.success();
        try {
            // 生成代码前,先清空之前生成的代码
            DelAllFile.delFolder(rootPath);

            /* 生成 model 代码 */
            new GenEntityMysql(dbUrl, dbName, dbPass, dbDriver, dbTableName, domainName, packageName, rootPath);

            Map<String, Object> map = mapData();
            /* 生成service代码 */
            Freemarker.printFilePath("service.ftl", map, rootPath + packageName.replace(".", "/") + "/service/", domainName + "Service.java", "ftl/service");
            Freemarker.printFilePath("serviceImpl.ftl", map, rootPath + packageName.replace(".", "/") + "/service/impl/", domainName + "ServiceImpl.java", "ftl/service");

			/* 生成dao代码 */
            Freemarker.printFilePath("dao.ftl", map, rootPath + packageName.replace(".", "/") + "/dao/", domainName + "Dao.java", "ftl/dao");

			/* 生成xml代码 */
            Freemarker.printFilePath("mapper.ftl", map, rootPath + packageName.replace(".", "/") + "/dao/", domainName + "Mapper.xml", "ftl/dao");

        } catch (Exception e) {
            result = Result.failure();
            e.printStackTrace();
        }
        return result;
    }


    private Map<String, Object> mapData() {
        List<String[]> fieldList = GenEntityMysql.getColumn(dbUrl, dbName, dbPass, dbDriver, dbTableName);
        List<String> fieldListLower = new ArrayList<>();
        Map<String, String> up = new HashMap<>(16);
        for (String[] s : fieldList) {
            for (String st : s) {
                System.out.println(PublicUtil.toLowerCaseFirstOne(PublicUtil.getName(st.toLowerCase(), st.toLowerCase())));
                fieldListLower.add(PublicUtil.toLowerCaseFirstOne(PublicUtil.getName(st.toLowerCase(), st.toLowerCase())));
                up.put(st, PublicUtil.toLowerCaseFirstOne(PublicUtil.getName(st.toLowerCase(), st.toLowerCase())));
            }
        }
        Map<String, Object> map = new HashMap<>(20);
        map.put("modelName", domainName);
        map.put("modelNameLower", PublicUtil.toLowerCaseFirstOne(domainName));

        map.put("dbTableName", dbTableName);
        map.put("fieldList", fieldList);
        map.put("fieldListLower", fieldListLower);
        map.put("packageName", packageName);
        map.put("upda", up);
        return map;
    }

}
