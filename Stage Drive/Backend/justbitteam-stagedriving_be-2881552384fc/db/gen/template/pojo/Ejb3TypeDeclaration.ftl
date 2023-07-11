<#if ejb3?if_exists>
<#if pojo.isComponent()>
@${pojo.importType("javax.persistence.Embeddable")}
<#else>
@${pojo.importType("javax.persistence.Entity")}
@${pojo.importType("javax.persistence.EntityListeners")}(value={
<#foreach field in pojo.getAllPropertiesIterator()>
     <#if field.name == "created">
${pojo.getDeclarationName()}.LastUpdateListener.class,
    </#if>
</#foreach>
<#foreach field in pojo.getAllPropertiesIterator()>
    <#if field.name == "uid">
${pojo.getDeclarationName()}.UidListener.class
    </#if>
</#foreach>
})
@${pojo.importType("javax.persistence.Cacheable")}
@${pojo.importType("org.hibernate.annotations.Cache")}(usage=${pojo.importType("org.hibernate.annotations.CacheConcurrencyStrategy")}.TRANSACTIONAL, region="${pojo.getDeclarationName()}")
@${pojo.importType("javax.persistence.NamedQueries")}({
@${pojo.importType("javax.persistence.NamedQuery")}(name = "${pojo.getDeclarationName()}.findAll", query = "SELECT n FROM ${pojo.getDeclarationName()} n"),
<#foreach field in pojo.getAllPropertiesIterator()> 
<#if !pojo.getJavaTypeName(field, jdk5)?contains("<") && pojo.getPropertyName(field) != "Id">
@NamedQuery(name = "${pojo.getDeclarationName()}.findBy${pojo.getPropertyName(field)}", query = "SELECT n FROM ${pojo.getDeclarationName()} n WHERE n.${field.name} = :${field.name}"),
</#if>
</#foreach>
})
@${pojo.importType("javax.persistence.Table")}(name="${clazz.table.name}"
<#if clazz.table.schema?exists>
    ,schema="${clazz.table.schema}"
</#if>/*<#if clazz.table.catalog?exists>
    ,catalog="${clazz.table.catalog}"
</#if>*/
<#assign uniqueConstraint=pojo.generateAnnTableUniqueConstraint()>
<#if uniqueConstraint?has_content>
    , uniqueConstraints = ${uniqueConstraint} 
</#if>)
</#if>
</#if>
