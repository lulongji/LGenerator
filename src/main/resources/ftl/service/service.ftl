package ${packageName}.service;

import java.util.List;
import ${packageName}.model.${modelName};

/**
* @author System
*/
public interface ${modelName}Service {

    /**
    * 添加数据
    *
    * @param ${modelNameLower}
    * @return
    * @author System
    */
    void add(${modelName} ${modelNameLower}) throws Exception;

    /**
    * 修改数据
    *
    * @param ${modelNameLower}
    * @return
    * @author System
    */
    void update(${modelName} ${modelNameLower}) throws Exception;

    /**
    * 删除数据
    *
    * @param ${modelNameLower}
    * @return
    * @author System
    */
    void delete(${modelName} ${modelNameLower}) throws Exception;

    /**
    * 查询单个数据
    *
    * @param ${modelNameLower}
    * @return
    * @author System
    */
    ${modelName} get(${modelName} ${modelNameLower}) throws Exception;

    /**
    * 查询所有数据
    *
    * @param ${modelNameLower}
    * @return
    * @author System
    */
    List<${modelName}> get${modelName}List(${modelName} ${modelNameLower}) throws Exception;

}
