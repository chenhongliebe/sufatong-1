package product;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @Author : wangyz
 * @Description :
 * @Date :  2017/6/18
 */
public class MysbatisCNUtil extends DefaultCommentGenerator {
    private Properties properties;

    @Override
    public void addConfigurationProperties(Properties properties){
        super.addConfigurationProperties(properties);
        this.properties = new Properties();
        this.properties.putAll(properties);
    }


    /**
     * <p>
     * 添加copyright说明
     *
     */
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        compilationUnit.addFileCommentLine( "/**");
        compilationUnit.addFileCommentLine( " * ");

        String copyright = " * **";
        compilationUnit.addFileCommentLine( copyright );

        compilationUnit.addFileCommentLine( " * ");
        compilationUnit.addFileCommentLine( " * " + compilationUnit.getType().getShortNameWithoutTypeArguments() + ".java" );
        compilationUnit.addFileCommentLine( " * ");
        compilationUnit.addFileCommentLine( " */" );
    }


    /**
     * <p>
     * 为类生成注释
     *
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass,
                                     IntrospectedTable introspectedTable) {
        StringBuilder sb = new StringBuilder();

        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * <p>");

        String remarks = introspectedTable.getRemarks();
        if ( StringUtility.stringHasValue(remarks) ) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                topLevelClass.addJavaDocLine(" * " + remarkLine);
            }
            sb.append( " * ");
        }

        sb.append( " * 表 : "); sb.append( introspectedTable.getFullyQualifiedTable() );
        sb.append( "的 Model 类" );
        topLevelClass.addJavaDocLine(sb.toString());

        topLevelClass.addJavaDocLine(" * " );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日" );

        String author = "$author$";

        if( properties.containsKey( "author" ) )
            author = properties.getProperty("author");

        topLevelClass.addJavaDocLine(" * @author \t"+ author );
        topLevelClass.addJavaDocLine(" * @date \t" + sdf.format( new Date() ) );
        topLevelClass.addJavaDocLine(" */");
    }



    /**
     * <p>
     * 为字段生成注释
     *
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        /**
         * 这是个示例字段注释
         * 表字段：table.name
         */
        StringBuffer sb = new StringBuffer();
//		field.addJavaDocLine("");
//		field.addJavaDocLine("");

        sb.append( "/** " );
        sb.append("字段:");
        sb.append(introspectedColumn.getActualColumnName());

        String remarks = introspectedColumn.getRemarks();
        if ( StringUtility.stringHasValue(remarks) ) {
            sb.append( "，" ); sb.append( remarks );
        }

        sb.append(" */");
        field.addJavaDocLine(sb.toString());
    }


}
