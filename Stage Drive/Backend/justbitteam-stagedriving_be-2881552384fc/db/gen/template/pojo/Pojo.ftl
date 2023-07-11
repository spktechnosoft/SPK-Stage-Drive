${pojo.getPackageDeclaration()}
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#include "PojoTypeDeclaration.ftl"/> {

<#if !pojo.isInterface()>
<#include "PojoFields.ftl"/>

<#include "PojoConstructors.ftl"/>
   
<#include "PojoPropertyAccessors.ftl"/>

<#include "PojoToString.ftl"/>

<#include "PojoEqualsHashcode.ftl"/>

<#else>
<#include "PojoInterfacePropertyAccessors.ftl"/>

</#if>
<#include "PojoExtraClassCode.ftl"/>

<#foreach field in pojo.getAllPropertiesIterator()>
     <#if field.name == "uid">
    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @${pojo.importType("javax.persistence.PreUpdate")}
        @${pojo.importType("javax.persistence.PrePersist")}
        public void setUid(${pojo.getDeclarationName()} o) {
            if (o.getUid() == null) {
                o.setUid(${pojo.importType("com.justbit.commons.TokenUtils")}.generateUid());
            }
        }
    }
     </#if>
</#foreach>

<#foreach field in pojo.getAllPropertiesIterator()>
     <#if field.name == "created">
    public class LastUpdateListener {
        /**
         * automatic property set before any database persistence
         */
        @${pojo.importType("javax.persistence.PreUpdate")}
        @${pojo.importType("javax.persistence.PrePersist")}
        public void setModified(${pojo.getDeclarationName()} o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
     </#if>
</#foreach>
}
</#assign>

${pojo.generateImports()}
${classbody}