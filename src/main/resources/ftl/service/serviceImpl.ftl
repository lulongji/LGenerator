package ${packageName}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${packageName}.dao.${modelName}Dao;
import ${packageName}.model.${modelName};
import ${packageName}.service.${modelName}Service;

/**
*
* @author System
*/
@Service
public class ${modelName}ServiceImpl implements ${modelName}Service {

    @Autowired
    private ${modelName}Dao ${modelNameLower}Dao;

    @Override
    public void add(${modelName} ${modelNameLower}) throws Exception{
        ${modelNameLower}Dao.add(${modelNameLower});
    }

    @Override
    public void update(${modelName} ${modelNameLower}) throws Exception{
        ${modelNameLower}Dao.update(${modelNameLower});
    }

    @Override
    public void delete(${modelName} ${modelNameLower}) throws Exception{
        ${modelNameLower}Dao.delete(${modelNameLower});
    }

    @Override
    public ${modelName} get(${modelName} ${modelNameLower}) throws Exception{
        return ${modelNameLower}Dao.get${modelName}List(${modelNameLower}) == null?null:${modelNameLower}Dao.get${modelName}List(${modelNameLower}).get(0);
    }

    @Override
    public List<${modelName}> get${modelName}List(${modelName} ${modelNameLower}) throws Exception {
        return ${modelNameLower}Dao.get${modelName}List(${modelNameLower});
    }
}
