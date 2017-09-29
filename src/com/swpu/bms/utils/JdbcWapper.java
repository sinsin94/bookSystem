package com.swpu.bms.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;





public abstract class JdbcWapper {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}



	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public JdbcWapper() {
		//默认的配置文件
		InputStream is = JdbcWapper.class.getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);

			// 加载驱动
			Class.forName(properties.getProperty("jdbc.driver"));
			// 获取数据库连接对象
			connection = DriverManager.getConnection(properties
					.getProperty("jdbc.url"), properties
					.getProperty("jdbc.user"), properties
					.getProperty("jdbc.password"));
			if(connection==null)
			{
				throw new Exception("数据库连接失败"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected int executeUpdate(String sql, List<Object> values) throws Exception {
		try {
			preparedStatement = connection.prepareStatement(sql);
			if (values != null) {
				// 绑定数据
				for (int i = 1; i <= values.size(); i++) {
					preparedStatement.setObject(i, values.get(i - 1));
				}
			}
			System.out.println(preparedStatement.toString());
		
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
		//	closeResouce();
		}
	}
	protected ResultSet executeQuery(String sql, List<Object> values) throws Exception {
		try {
			preparedStatement = connection.prepareStatement(sql);
			if (values != null) 
			{
				// 绑定数据
				for (int i = 1; i <= values.size(); i++) {
					preparedStatement.setObject(i, values.get(i - 1));
				}
			}
			System.out.println(preparedStatement.toString());
		
			
			return preparedStatement.executeQuery();
		} catch (Exception e) {
			throw e;
		} finally {
		//	closeResouce();
		}
	}

	protected void closeResouce() throws Exception {
		if (resultSet != null) {
			resultSet.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

}
