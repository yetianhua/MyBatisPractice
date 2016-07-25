package interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import entity.Page;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements Interceptor {

	/**
	 * 拦截器所做的操作 Invocation 对象提供三个参数:target(目标类)、method(目标方法)、args(目标方法的参数)
	 */
	public Object intercept(Invocation invocation) throws Throwable {

		RoutingStatementHandler target = (RoutingStatementHandler) invocation.getTarget();

		String sql = target.getBoundSql().getSql();

		BoundSql boundSql = target.getBoundSql();
		System.out.println("boundSql before" + boundSql);

		MetaObject statementHandlerMetaObject = MetaObject.forObject(target, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
				SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);

		MappedStatement mappedStatement = (MappedStatement) statementHandlerMetaObject
				.getValue("delegate.mappedStatement");

		if (Pattern.matches(".+Page$", mappedStatement.getId())) {

			ParameterHandler parameterHandler = target.getParameterHandler();
			Map paramMap = (Map) parameterHandler.getParameterObject();

			Page page = (Page) paramMap.get("page");

			// 先设置page的totalNumber
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement preparedStatement = connection
					.prepareStatement("select count(*) from (" + boundSql.getSql() + ") a");
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}

			// 然后设置boundSql
			String limitSql = new StringBuilder(sql).append("  order by id limit ").append(page.getDbIndex())
					.append(",").append(page.getDbNumber()).toString();

			MetaObject boundSqlMetaObject = MetaObject.forObject(boundSql, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
					SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);

			boundSqlMetaObject.setValue("sql", limitSql);

			System.out.println("boundSql after" + limitSql);
		}

		return invocation.proceed();
	}

	/**
	 * Mybatis调用拦截器的地方会调用该方法并返回一个动态的代理类,该代理类的代理方式调用Object intercept(Invocation
	 * invocation)方法
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}

}
