package ${packageName}.dao;

import java.util.List;
import ${packageName}.model.${modelName};

/**
*
* @author systemCode
*/
public interface ${modelName}Dao {

    void add(${modelName} ${modelNameLower}) throws Exception;

    void update(${modelName} ${modelNameLower}) throws Exception;

    void delete(${modelName} ${modelNameLower}) throws Exception;

    List<${modelName}> get${modelName}List(${modelName} ${modelNameLower}) throws Exception;

}
