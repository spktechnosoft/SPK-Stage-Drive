<#if ejb3>
<#if pojo.hasIdentifierProperty()>
<#if property.equals(clazz.identifierProperty)>
 ${pojo.generateAnnIdGenerator()}
<#-- if this is the id property (getter)-->
<#-- explicitly set the column name for this property-->
</#if>
</#if>
<#if c2h.isManyToOne(property)>
<#--TODO support @OneToOne true and false-->    
${pojo.generateManyToOneAnnotation(property)}
<#--TODO support optional and targetEntity-->    
${pojo.generateJoinColumnsAnnotation(property, cfg)}
<#elseif c2h.isCollection(property)>
   ${pojo.generateCollectionAnnotation(property, cfg)}
   @${pojo.importType("org.hibernate.annotations.Cascade")}(value=${pojo.importType("org.hibernate.annotations.CascadeType")}.ALL)                    
   @${pojo.importType("org.hibernate.annotations.Cache")}(usage=${pojo.importType("org.hibernate.annotations.CacheConcurrencyStrategy")}.TRANSACTIONAL, region="${pojo.getDeclarationName()}.${pojo.getPropertyName(property)}")
   @${pojo.importType("org.hibernate.annotations.Fetch")}(value=${pojo.importType("org.hibernate.annotations.FetchMode")}.SUBSELECT)                    
<#else> 
${pojo.generateBasicAnnotation(property)}
${pojo.generateAnnColumnAnnotation(property)}
</#if>
</#if>
