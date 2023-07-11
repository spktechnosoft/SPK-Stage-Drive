${pojo.getPackageDeclaration()}
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign imp = pojo.importType(pojo.getPackageDeclaration()?replace("daos","entities")?replace("package ","")?replace(";","")?replace("model","")+"*")>/**
 * Home object for domain model class ${declarationName}.
 * @author Hibernate Tools
 */
 
 
 
public abstract class Abstract${declarationName}DAO extends ${pojo.importType("io.dropwizard.hibernate.AbstractDAO")}<${pojo.getDeclarationName()}> {

    private final ${pojo.importType("ch.qos.logback.classic.Logger")} log = new ${pojo.importType("ch.qos.logback.classic.LoggerContext")}().getLogger(Abstract${pojo.getDeclarationName()}DAO.class);

	public Abstract${declarationName}DAO(${pojo.importType("org.hibernate.SessionFactory")} session) {
		super(session);
	}

<#foreach field in pojo.getAllPropertiesIterator()> 
<#if !pojo.getJavaTypeName(field, jdk5)?contains("<") && pojo.getPropertyName(field) != "Id">
<#if pojo.generateAnnColumnAnnotation(field)?contains("unique=true")>
	public ${pojo.getDeclarationName()} findBy${pojo.getPropertyName(field)}(${pojo.getJavaTypeName(field, jdk5)} ${field.name}) {
		return uniqueResult(namedQuery("${pojo.getDeclarationName()}.findBy${pojo.getPropertyName(field)}").setParameter("${field.name}", ${field.name}).setCacheable(true));
<#else>
	public ${pojo.importType("java.util.List")+"<"+pojo.getDeclarationName()+">"} findBy${pojo.getPropertyName(field)}(${pojo.getJavaTypeName(field, jdk5)} ${field.name}) {
        org.hibernate.query.Query q = namedQuery("${pojo.getDeclarationName()}.findBy${pojo.getPropertyName(field)}");
		return list(q.setParameter("${field.name}", ${field.name}).setCacheable(true));
</#if>
	}
</#if>
</#foreach>


<#foreach field in pojo.getAllPropertiesIterator()>
<#if !pojo.getJavaTypeName(field, jdk5)?contains("<") && pojo.getPropertyName(field) != "Id">
<#if pojo.generateAnnColumnAnnotation(field)?contains("unique=true")>

<#else>
	public ${pojo.importType("java.util.List")+"<"+pojo.getDeclarationName()+">"} findBy${pojo.getPropertyName(field)}Paged(${pojo.getJavaTypeName(field, jdk5)} ${field.name}, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("${pojo.getDeclarationName()}.findBy${pojo.getPropertyName(field)}");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("${field.name}", ${field.name}).setCacheable(true));
	}

	public ${pojo.importType("java.util.List")+"<"+pojo.getDeclarationName()+">"} findBy${pojo.getPropertyName(field)}Paged(${pojo.getJavaTypeName(field, jdk5)} ${field.name}, Integer page, Integer limit, PagedResults<${pojo.getDeclarationName()}> results) {
        org.hibernate.query.Query q = namedQuery("${pojo.getDeclarationName()}.findBy${pojo.getPropertyName(field)}");
        q.setParameter("${field.name}", ${field.name}).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        ${pojo.importType("java.util.List")+"<"+pojo.getDeclarationName()+">"} res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }
</#if>

</#if>
</#foreach>


	public ${pojo.importType("java.util.List")+"<"+pojo.getDeclarationName()+">"} findAll() {
		return list(namedQuery("${pojo.getDeclarationName()}.findAll").setCacheable(true));
	}

	public ${pojo.importType("java.util.List")+"<"+pojo.getDeclarationName()+">"} findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("${pojo.getDeclarationName()}.findAll");
        q.setCacheable(true);

        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        ${pojo.importType("java.util.List")+"<"+pojo.getDeclarationName()+">"} res = list(q);

        return res;
    }

	public ${pojo.importType("java.util.List")+"<"+pojo.getDeclarationName()+">"} findAllPaged(Integer page, Integer limit, PagedResults<${pojo.getDeclarationName()}> results) {
        org.hibernate.query.Query q = namedQuery("${pojo.getDeclarationName()}.findAll");
        q.setCacheable(true);

        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        ${pojo.importType("java.util.List")+"<"+pojo.getDeclarationName()+">"} res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

<#if ejb3>

	public ${pojo.importType("org.hibernate.Session")} getCurrentSession() {
        return currentSession();
    }

	public void beginTransaction() {
        currentSession().beginTransaction();
    }

    public void endTransaction() {
        final ${pojo.importType("org.hibernate.Transaction")} txn = currentSession().getTransaction();
            if (txn != null && txn.getStatus().isOneOf(${pojo.importType("org.hibernate.resource.transaction.spi.TransactionStatus")}.ACTIVE)) {
                txn.commit();
            }
    }

	public void delete(${declarationName} transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(${declarationName} transientInstance) {
        //log.debug("editing ${declarationName} instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(${declarationName} transientInstance) {
        //log.debug("creating ${declarationName} instance");
        try {
            persist(transientInstance);
            //log.debug("create successful");
        }
        catch (RuntimeException re) {
            log.error("create failed", re);
            throw re;
        }
    }

    public int fillResults(${pojo.importType("com.stagedriving.modules.commons.ds.PagedResults")} results, ${pojo.importType("org.hibernate.Query")} query, String page, String limit) {
        if (results != null) {
            ${pojo.importType("org.hibernate.ScrollableResults")} scrollableResults = query.scroll(${pojo.importType("org.hibernate.ScrollMode")}.FORWARD_ONLY);
            scrollableResults.last();
            results.setSize(scrollableResults.getRowNumber() + 1);

            if (page != null) {
                results.setPage(Integer.valueOf(page));
            }
            if (limit != null) {
                results.setLimit(Integer.valueOf(limit));
            }

            return results.getSize();
        }

        return -1;
    }

    public int fillResults(${pojo.importType("com.stagedriving.modules.commons.ds.PagedResults")} results, ${pojo.importType("org.hibernate.Query")} query, Integer page, Integer limit) {
        if (results != null) {
            ${pojo.importType("org.hibernate.ScrollableResults")} scrollableResults = query.scroll(${pojo.importType("org.hibernate.ScrollMode")}.FORWARD_ONLY);
            scrollableResults.last();
            results.setSize(scrollableResults.getRowNumber() + 1);

            if (page != null) {
                results.setPage(page);
            }
            if (limit != null) {
                results.setLimit(limit);
            }

            return results.getSize();
        }

        return -1;
    }
    
<#if clazz.identifierProperty?has_content>    
    public ${declarationName} findById( ${pojo.getJavaTypeName(clazz.identifierProperty, jdk5)} id) {
        //log.debug("getting ${declarationName} instance with id: " + id);
        try {
            ${declarationName} instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
</#if>
<#else>    
    
    
    public void delete(${declarationName} persistentInstance) {
        log.debug("deleting ${declarationName} instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ${declarationName} merge(${declarationName} detachedInstance) {
        log.debug("merging ${declarationName} instance");
        try {
            ${declarationName} result = (${declarationName}) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    private long getRowCount(String queryName, final Object... params) {

        String rowCountQueryName = queryName;

        Query rowCountQuery = currentSession().createQuery(rowCountQueryName);
        rowCountQuery.setCacheable(true);

        setParameters(rowCountQuery, params);

        return rowCountQuery.list().size();

    }

    private void setParameters(final Query query, final Object... params) {

        for (int index = 0; index < params.length; index++) {

            query.setParameter(index, params[index]);
        }
    }
    
<#if clazz.identifierProperty?has_content>
    public ${declarationName} findById( ${c2j.getJavaTypeName(clazz.identifierProperty, jdk5)} id) {
        log.debug("getting ${declarationName} instance with id: " + id);
        try {
            ${declarationName} instance = get(id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
</#if>
    
<#if clazz.hasNaturalId()>
    public ${declarationName} findByNaturalId(${c2j.asNaturalIdParameterList(clazz)}) {
        log.debug("getting ${declarationName} instance by natural id");
        try {
            ${declarationName} instance = (${declarationName}) sessionFactory.getCurrentSession()
                    .createCriteria("${clazz.entityName}")
<#if jdk5>
                    .add( ${pojo.staticImport("org.hibernate.criterion.Restrictions", "naturalId")}()
<#else>
                   .add( ${pojo.importType("org.hibernate.criterion.Restrictions")}.naturalId()
</#if>                    
<#foreach property in pojo.getAllPropertiesIterator()>
<#if property.isNaturalIdentifier()>
                            .set("${property.name}", ${property.name})
</#if>
</#foreach>
                        )
                    .uniqueResult();
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("query failed", re);
            throw re;
        }
    }
</#if>    
<#if jdk5>
    public ${pojo.importType("java.util.List")}<${declarationName}> findByExample(${declarationName} instance) {
<#else>
    public ${pojo.importType("java.util.List")} findByExample(${declarationName} instance) {
</#if>
        log.debug("finding ${declarationName} instance by example");
        try {
<#if jdk5>
            ${pojo.importType("java.util.List")}<${declarationName}> results = (List<${declarationName}>) sessionFactory.getCurrentSession()
<#else>
            ${pojo.importType("java.util.List")} results = sessionFactory.getCurrentSession()
</#if>
                    .createCriteria("${clazz.entityName}")
<#if jdk5>
                    .add( ${pojo.staticImport("org.hibernate.criterion.Example", "create")}(instance) )
<#else>
                    .add(${pojo.importType("org.hibernate.criterion.Example")}.create(instance))
</#if>
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    } 
<#foreach queryName in cfg.namedQueries.keySet()>
<#if queryName.startsWith(clazz.entityName + ".")>
<#assign methname = c2j.unqualify(queryName)>
<#assign params = cfg.namedQueries.get(queryName).parameterTypes><#assign argList = c2j.asFinderArgumentList(params, pojo)>
<#if jdk5 && methname.startsWith("find")>
    public ${pojo.importType("java.util.List")}<${declarationName}> ${methname}(${argList}) {
<#elseif methname.startsWith("count")>
    public int ${methname}(${argList}) {
<#else>
    public ${pojo.importType("java.util.List")} ${methname}(${argList}) {
</#if>
        ${pojo.importType("org.hibernate.Query")} query = sessionFactory.getCurrentSession()
                .getNamedQuery("${queryName}");
<#foreach param in params.keySet()>
<#if param.equals("maxResults")>
		query.setMaxResults(maxResults);
<#elseif param.equals("firstResult")>
        query.setFirstResult(firstResult);
<#else>
        query.setParameter("${param}", ${param});
</#if>
</#foreach>
<#if jdk5 && methname.startsWith("find")>
        return (List<${declarationName}>) query.list();
<#elseif methname.startsWith("count")>
        return ( (Integer) query.uniqueResult() ).intValue();
<#else>
        return query.list();
</#if>
    }
</#if>
</#foreach></#if>
}
</#assign>

${pojo.generateImports()}
${classbody}
