<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.dao.${modelName}Dao">

	<insert id="add" parameterType="${modelName}">
		INSERT INTO ${dbTableName}
		(
		<trim prefix="" suffixOverrides=",">
			<#list fieldList as var>
				${var[0]},
			</#list>
		</trim>
		)values (
		<trim prefix="" suffixOverrides=",">
			<#list fieldListLower as var>
				${r"#{"}${var}${r"}"},
			</#list>
		</trim>
		)
	</insert>

    <update id="update" parameterType="${modelName}">
        UPDATE ${dbTableName}
        <trim prefix="set" suffixOverrides=",">
            <#list upda?keys as key>
                <if test="null != ${upda["${key}"]} and '' != ${upda["${key}"]}" >
					${key} =${r"#{"}${upda["${key}"]}${r"}"}
                </if>
            </#list>
        </trim>
		<where>
			<#list upda?keys as key>
				<if test="null != ${upda["${key}"]} and '' != ${upda["${key}"]}" >
                    AND	${key} =${r"#{"}${upda["${key}"]}${r"}"}
				</if>
			</#list>
		</where>
    </update>

	<delete id="delete" parameterType="${modelName}">
		DELETE FROM ${dbTableName}
		<where>
			<#list upda?keys as key>
				<if test="null != ${upda["${key}"]} and '' != ${upda["${key}"]}" >
					AND	${key} =${r"#{"}${upda["${key}"]}${r"}"}
				</if>
			</#list>
		</where>
	</delete>

	<select id="get${modelName}List" parameterType="${modelName}" resultType="${modelName}">
		SELECT
        <trim prefix="" suffixOverrides=",">
			<#list fieldList as var>
				${var[0]},
			</#list>
        </trim>
		 FROM ${dbTableName}
		<where>
			<#list upda?keys as key>
				<if test="null != ${upda["${key}"]} and '' != ${upda["${key}"]}" >
					AND	${key} =${r"#{"}${upda["${key}"]}${r"}"}
				</if>
			</#list>
		</where>
	</select>

</mapper>