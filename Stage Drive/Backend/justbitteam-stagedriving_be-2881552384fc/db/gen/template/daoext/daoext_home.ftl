${pojo.getPackageDeclaration()}
// Porquoi?Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign imp = pojo.importType(pojo.getPackageDeclaration()?replace("package ","")?replace(";","")+".model.*")>/**
 * Home object for domain model class ${declarationName}.
 * @author Hibernate Tools
 */
 
@${pojo.importType("com.google.inject.Singleton")}
public class ${declarationName}DAO extends Abstract${declarationName}DAO {

    private final ${pojo.importType("ch.qos.logback.classic.Logger")} log = new ${pojo.importType("ch.qos.logback.classic.LoggerContext")}().getLogger(${pojo.getDeclarationName()}DAO.class);

	@${pojo.importType("com.google.inject.Inject")}
	public ${declarationName}DAO(/*@${pojo.importType("com.google.inject.name.Named")}("datastore1") */${pojo.importType("org.hibernate.SessionFactory")} session) {
		super(session);
	}

}
</#assign>

${pojo.generateImports()}
${classbody}
