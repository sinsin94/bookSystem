package com.swpu.bms.dao;



import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;




import com.swpu.bms.annotation.Column;
import com.swpu.bms.annotation.ID;
import com.swpu.bms.annotation.Table;
import com.swpu.bms.utils.JdbcWapper;


@SuppressWarnings("unchecked")
public abstract class BaseDao<T> extends JdbcWapper {

	
	
	
	/**
	 * 将数据保存到数据库中
	 * 
	 * @param t
	 *            保存的数据的对象
	 *         
	 * @return
	 * @throws Exception
	 */
	 public final int getInsertSql(T t) throws Exception {
		// 目标的sql:inser into users () values(?,?,?)
		StringBuffer buffer = null;
		Class<T> clazz = (Class<T>) t.getClass();
		// 判断是否加有Table注解
		boolean flag = clazz.isAnnotationPresent(Table.class);
		if (!flag) {// 没有注解则抛出异常
			throw new Exception(clazz.getName() + "没有添加Table注解");
		}
		// 有注解--->StringBuffer对象准备拼接sql语句
		buffer = new StringBuffer();
		buffer.append("insert into ");
		// 获取表的注解
		Table table = clazz.getAnnotation(Table.class);
		buffer.append(table.name() + "(");
		// 拼接插入的列---->保存对应属性的值
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			List<Object> values = new ArrayList<Object>();
			for (Field field : fields) {
				// 设置为可以访问该属性的值
				field.setAccessible(true);
				// 判断是否为主键
				boolean isID = field.isAnnotationPresent(ID.class);
				if (isID) {
					ID id = field.getAnnotation(ID.class);
					if (!id.isAutoIncrement()) {// 非自增主键，需要在程序里面进行赋值
						// 1.先获取该属性的数据类型
						Class<?> DataType = field.getType();
						// 定义一个变量来存储ID值
						Object ID_value = null;
						ID_value = field.get(t);
						// 2.判断是否为基本数据类型
						if (DataType.isPrimitive()) {
							// 如果是默认值,则抛出异常
							if (ID_value.equals(0.0) || ID_value.equals(0))
								throw new Exception(clazz.getName() + " 没有ID值");
						} else {
							// 如果是默认值,则抛出异常
							if (ID_value == null)
								throw new Exception(clazz.getName() + " 没有ID值");
						}
						buffer.append(id.name() + ",");
						// 存储id的值
						values.add(ID_value);
					}
				} else // 普通列
				{
					boolean iscolumn = field.isAnnotationPresent(Column.class);
					if(iscolumn) //打了注解
					{
						Column column = field.getAnnotation(Column.class);
						buffer.append(column.name() + ",");
						// 存储值
						values.add(field.get(t));
					}
				
					//不处理没打注解的属性
					
				}
			}
			// 删除多余的","
			buffer.deleteCharAt(buffer.length() - 1).append(") values(");
			// 遍历的添加值的集合--->决定？个数
			for (int i = 0; i < values.size(); i++) {
				buffer.append("?").append(",");
			}
			// 删除最后一个逗号
			buffer.deleteCharAt(buffer.length() - 1).append(")");
			// jdbc里面的操作--->将数据保存到数据库
			
			int info=executeUpdate(buffer.toString(), values);
			return info;
		}

		return 0;
	}

	
	public final T Insert(T t) throws Exception {
		
		
		try {

			getInsertSql(t);
			// 目标的sql:SELECT LAST_INSERT_ID()
			StringBuffer buffer = null;
			Class<T> clazz = (Class<T>) t.getClass();

			buffer = new StringBuffer();
			buffer.append("SELECT LAST_INSERT_ID()");
			ResultSet result=executeQuery(buffer.toString(), null);		
			
			Field[] fields = clazz.getDeclaredFields();
			// 处理结果集
			while (result.next()) {
				// 转换每行的数据
				for (Field field : fields) {
					String lable = null;// 列名
					// 获取列名----> id
					boolean isID = field.isAnnotationPresent(ID.class);
					if (isID) {
						lable = field.getAnnotation(ID.class).name();
						if (lable != null) {// 找到id
							field.setAccessible(true);
							// 得到该属性在数据库中的值
							Object value = result.getObject(1);

							if(value instanceof Long)
							{
								value= ((Long) value).intValue();
								field.set(t, value);	
							}
							else if(value instanceof BigInteger)
							{
								value= ((BigInteger) value).intValue();
								field.set(t, value);	
							}
												
							
							return t;
						}
					} 
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			closeResouce();
		}
		
		return null;

	}

	public final int UpdateByID(T t) throws Exception {

		Field ID_field = null;
		Object ID_value = null;
		boolean isok = false;
		// 目标的sql:update 表名 set 列名= ？，列名=？ where id=?
		StringBuffer buffer = null;
		Class<T> clazz = (Class<T>) t.getClass();
		// 判断是否加有Table注解
		boolean flag = clazz.isAnnotationPresent(Table.class);
		if (!flag) // 没有注解则抛出异常
		{
			throw new Exception(clazz.getName() + "没有添加Table注解");
		}
		// 有注解--->StringBuffer对象准备拼接sql语句
		buffer = new StringBuffer();
		buffer.append("update ");
		// 获取表的注解
		Table table = clazz.getAnnotation(Table.class);
		buffer.append(table.name() + " set ");
		// 拼接插入的列---->保存对应属性的值
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			List<Object> values = new ArrayList<Object>();

			for (Field field : fields) // 处理属性
			{

				flag = field.isAnnotationPresent(Column.class);
				if (flag) {
					field.setAccessible(true);
					Object value = null;
					value = field.get(t);

					// 2.判断是否为基本数据类型
					if (value != null && !isinit(value)) {
						Column column = field.getAnnotation(Column.class);
						buffer.append(column.name() + "=?,");
						// 存储值
						values.add(value);
						isok = true;
					}
				} else {
					flag = field.isAnnotationPresent(ID.class);
					if (flag) {
						ID_field = field;
					}

				}

			}

			if (ID_field == null || !isok) {
				throw new Exception(clazz.getName() + " 没有属性值");
			}

			ID_field.setAccessible(true);
			ID_value = ID_field.get(t);
			Class<?> DataType = ID_field.getType();
			// 2.判断是否为基本数据类型
			if (DataType.isPrimitive()) {
				if (ID_value.equals(0.0) || ID_value.equals(0))
					throw new Exception(clazz.getName() + " 没有属性值");
			} else {
				if (ID_value == null)
					throw new Exception(clazz.getName() + " 没有属性值");
			}
			values.add(ID_value);
			// 删除最后一个逗号
			buffer.deleteCharAt(buffer.length() - 1).append(
					" where " + ID_field.getName() + " =?");
			// jdbc里面的操作--->将数据保存到数据库
	

			return executeUpdate(buffer.toString(), values);
			

			
		}
		return 0;
	}

	
	
	public final int Delete(T t) throws Exception {
		// 目标sql：delete  from users where name=? and password=?
		StringBuffer buffer = null;
		Class<T> clazz = (Class<T>) t.getClass();
		boolean flag = clazz.isAnnotationPresent(Table.class);
		if (!flag) {
			throw new Exception(clazz.getName() + "没有添加Table注解");
		}
		buffer = new StringBuffer();
		buffer.append("delete  from ");
		// 确定查询的表
		Table table = clazz.getAnnotation(Table.class);
		buffer.append(table.name()+" where ");

		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			List<Object> values = new ArrayList<Object>();
			for (Field field : fields) // 处理属性
			{
				
				flag = field.isAnnotationPresent(ID.class);
				if(flag)
				{
					field.setAccessible(true);
					Object value = null;
					value = field.get(t);

					// 2.判断是否为基本数据类型
					if (value != null && !isinit(value)) {
						ID id = field.getAnnotation(ID.class);
						buffer.append(id.name() + "=? and ");
						// 存储值
						values.add(value);
						break; //如果通过id删除，则可以直接跳出了
					}
				}
				else
				{
					flag = field.isAnnotationPresent(Column.class);
					if (flag) 
					{
						field.setAccessible(true);
						Object value = null;
						value = field.get(t);

						// 2.判断是否为基本数据类型
						if (value != null && !isinit(value)) {
							Column column = field.getAnnotation(Column.class);
							buffer.append(column.name() + "=? and ");
							// 存储值
							values.add(value);
							
						}
					} 
				}

				

			}
			// 删除最后一个and
			buffer.delete(buffer.length()-4, buffer.length());
			try {
				
				// 执行查询得到结果集
			
				int ret = this.executeUpdate(buffer.toString(), values);
				return ret;

				
				
				
			} catch (Exception e) {
				throw e;
			} finally {// 释放资源
				this.closeResouce();
			}
		} else {
			throw new Exception(clazz.getName() + "没有添加任何属性");
		}
		

	}

	
	//arg==null 表示全部查找
	//arg==对象 表示条件查找
	public final List<T> Find(T t,T arg) throws Exception {
		// 目标sql：select * from users where id=? and name=?
		StringBuffer buffer = null;
		Class<T> clazz = (Class<T>) t.getClass();
		boolean flag = clazz.isAnnotationPresent(Table.class);
		if (!flag) {
			throw new Exception(clazz.getName() + "没有添加Table注解");
		}
		buffer = new StringBuffer();
		
		
		buffer.append("select * from ");
		
		
		// 确定查询的表
		Table table = clazz.getAnnotation(Table.class);
		buffer.append(table.name());
		Field[] fields;
		if(arg!=null)
		{
			buffer.append(" where ");
			
			fields= clazz.getDeclaredFields();
			if (fields != null && fields.length > 0) {
				List<Object> values = new ArrayList<Object>();
				for (Field field : fields) // 处理属性
				{
					
					flag = field.isAnnotationPresent(ID.class);
					if(flag)
					{
						field.setAccessible(true);
						Object value = null;
						value = field.get(t);

						// 2.判断是否为基本数据类型
						if (value != null && !isinit(value)) {
							ID id = field.getAnnotation(ID.class);
							buffer.append(id.name() + "=? and ");
							// 存储值
							values.add(value);
							break; //通过id查询
						}
					}
					else
					{
						flag = field.isAnnotationPresent(Column.class);
						if (flag) 
						{
							field.setAccessible(true);
							Object value = null;
							value = field.get(t);

							// 2.判断是否为基本数据类型
							if (value != null && !isinit(value)) {
								Column column = field.getAnnotation(Column.class);
								
								buffer.append(column.name() + "=? and ");
								// 存储值
								
								
								values.add(value);
								
							}
						} 
					}
	

				}
				// 删除最后一个and
				buffer.delete(buffer.length()-4, buffer.length());
				try {
					
					// 执行查询得到结果集
				
					ResultSet resultSet = this.executeQuery(buffer.toString(), values);
					List<T> result = convertValues(clazz, resultSet);
					return result.isEmpty() ? null : result;

					
				} catch (Exception e) {
					throw e;
				} finally {// 释放资源
					this.closeResouce();
				}
			} else {
				throw new Exception(clazz.getName() + "没有添加任何属性");
			}		
		}
		fields = clazz.getDeclaredFields();
		ResultSet resultSet = null;
		if (fields != null && fields.length > 0) {
			try {
				
				// 执行查询得到结果集
				resultSet = this.executeQuery(buffer.toString(), null);
				List<T> result = convertValues(clazz, resultSet);
				return result.isEmpty() ? null : result;
			} catch (Exception e) {
				throw e;
			} finally {// 释放资源
				this.closeResouce();
			}
		} else {
			throw new Exception(clazz.getName() + "没有添加任何属性");
		}

	}
	
	
	public final int UserCheck(T t) throws Exception {
		// 目标sql：select * from users where name=?,password=?
		StringBuffer buffer = null;
		Class<T> clazz = (Class<T>) t.getClass();
		boolean flag = clazz.isAnnotationPresent(Table.class);
		if (!flag) {
			throw new Exception(clazz.getName() + "没有添加Table注解");
		}
		buffer = new StringBuffer();
		buffer.append("select * from ");
		// 确定查询的表
		Table table = clazz.getAnnotation(Table.class);
		buffer.append(table.name()+" where ");

		Field[] fields = clazz.getDeclaredFields();
		ResultSet resultSet = null;
		if (fields != null && fields.length > 0) {
			List<Object> values = new ArrayList<Object>();
			for (Field field : fields) // 处理属性
			{

				flag = field.isAnnotationPresent(Column.class);
				if (flag) {
					field.setAccessible(true);
					Object value = null;
					value = field.get(t);

					// 2.判断是否为基本数据类型
					if (value != null && !isinit(value)) {
						Column column = field.getAnnotation(Column.class);
						buffer.append(column.name() + "=? and ");
						// 存储值
						values.add(value);
						
					}
				} 

			}
			// 删除最后一个and
			buffer.delete(buffer.length()-4, buffer.length());
			try {
				
				// 执行查询得到结果集
				resultSet = this.executeQuery(buffer.toString(), values);
				if (!resultSet.next()) {
				    //resultSet is null
					return 0;
				} else {
				    // resultSet is not null
					return 1;
				}
				
				
			} catch (Exception e) {
				throw e;
			} finally {// 释放资源
				this.closeResouce();
			}
		} else {
			throw new Exception(clazz.getName() + "没有添加任何属性");
		}

	}
	
	

	// 处理结果集
	private List<T> convertValues(Class<T> clazz, ResultSet resultSet)
			throws Exception {
		// 存的集合
		List<T> result = new ArrayList<T>();
		T temp = null;
		Field[] fields = clazz.getDeclaredFields();
		// 处理结果集
		while (resultSet.next()) {
			temp = clazz.newInstance();
			// 转换每行的数据
			for (Field field : fields) {
				String lable = null;// 列名
				// 获取列名----> id colum
				boolean isID = field.isAnnotationPresent(ID.class);
				if (isID) {
					lable = field.getAnnotation(ID.class).name();
				} else {
					boolean isColum = field.isAnnotationPresent(Column.class);
					if (isColum) {
						lable = field.getAnnotation(Column.class).name();
						
					}
				}
				if (lable != null) {// 找到列名
					field.setAccessible(true);
					// 得到该属性在数据库中的值
					Object value = resultSet.getObject(lable);

					
					if(value instanceof Long)
					{
						value= ((Long) value).intValue();
						
					}
					else if(value instanceof BigInteger)
					{
						value= ((BigInteger) value).intValue();
						
					}
					
					if(value instanceof Timestamp)
					{
						DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
						value=sdf.format(value);
						
					}
		
					field.set(temp, value);
					
					
				}
			}
			result.add(temp);
			temp = null;
		}
		return result;
	}

	
	//自己写的方法，不同于FindAll
	private final List<T> findEntity(T t) throws Exception {
		Field ID_field = null;
		Object ID_value = null;
		boolean isok = false;
		// 目标的sql:select 列名或* where id=?
		StringBuffer buffer = null;
		Class<T> clazz = (Class<T>) t.getClass();
		List<Object> values = new ArrayList<Object>();
		// 判断是否加有Table注解
		boolean flag = clazz.isAnnotationPresent(Table.class);
		if (!flag) // 没有注解则抛出异常
		{
			throw new Exception(clazz.getName() + "没有添加Table注解");
		}
		// 有注解--->StringBuffer对象准备拼接sql语句
		buffer = new StringBuffer();
		buffer.append("select * from ");
		// 获取表的注解
		Table table = clazz.getAnnotation(Table.class);
		buffer.append(table.name());

		ResultSet result = executeQuery(buffer.toString(), values);

		List<T> ret = new ArrayList<T>();

		ResultSetMetaData rsmd = result.getMetaData(); // 获得元数据
		int columnCount = rsmd.getColumnCount(); // 获得查询的列数个数
		while (result.next()) {
			Object obj = clazz.newInstance();
			for (int i = 1; i <= columnCount; i++) { // 循环设置该类的set方法的相关属性

				// System.out.println(Class.forName(rsmd.getColumnClassName(i)));

				Method m = obj.getClass().getMethod(
						getSetMethodName(rsmd.getColumnName(i)),
						Class.forName(rsmd.getColumnClassName(i)));// 获得方法
				m.invoke(obj, result.getObject(rsmd.getColumnName(i)));// 设置set，此处用ResultSet.getObject(i)方法
			}
			ret.add((T) obj);

		}
		closeResouce();
		return ret;
	}

	private static String getSetMethodName(String columnName) {

		return "set" + columnName.substring(0, 1).toUpperCase()

		+ columnName.toLowerCase().substring(1);

	}

	public boolean isinit(Object value) {

		if (value.equals(0.0f) || value.equals(0)||value.equals(0.0d))
			return true;
		return false;
	}

}
