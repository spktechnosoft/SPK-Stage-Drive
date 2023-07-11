
    public class NamedQueries {
<#foreach field in pojo.getAllPropertiesIterator()> 
<#if !pojo.getJavaTypeName(field, jdk5)?contains("<")>
        public static final String ${("findBy"+pojo.getPropertyName(field))?upper_case} = "${pojo.getDeclarationName()}.findBy${pojo.getPropertyName(field)}";
</#if>
</#foreach>
    }

<#-- // Fields -->

${pojo.importType("java.util.List")?replace("List","")}
${pojo.importType("java.util.ArrayList")?replace("ArrayList","")}
<#foreach field in pojo.getAllPropertiesIterator()><#if pojo.getMetaAttribAsBool(field, "gen-property", true)> <#if pojo.hasMetaAttribute(field, "field-description")>    /**
     ${pojo.getFieldJavaDoc(field, 0)}
     */
 </#if>    ${pojo.getFieldModifiers(field)} ${pojo.getJavaTypeName(field, jdk5)?replace("Set<","List<")} ${field.name}<#if pojo.hasFieldInitializor(field, jdk5)> = ${pojo.getFieldInitialization(field, jdk5)?replace("HashSet<","ArrayList<")}</#if>;
</#if>
</#foreach>
