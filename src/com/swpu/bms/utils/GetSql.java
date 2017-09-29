package com.swpu.bms.utils;




import java.lang.reflect.Field;


import java.util.ArrayList;
import java.util.List;




import com.swpu.bms.annotation.Column;
import com.swpu.bms.annotation.ID;
import com.swpu.bms.annotation.Table;



@SuppressWarnings("unchecked")
public  class GetSql<T> {

	/**
	 * 将数据保存到数据库中
	 * 
	 * @param t
	 *            保存的数据的对象
	 *        
	 * @return
	 * @throws Exception
	 */
	 public final String getInsertSql(T t) throws Exception {
		// 目标的sql:inser into users () values(?,?,?)
		 StringBuilder buffer = null;
		Class<T> clazz = (Class<T>) t.getClass();
		// 判断是否加有Table注解
		boolean flag = clazz.isAnnotationPresent(Table.class);
		if (!flag) {// 没有注解则抛出异常
			throw new Exception(clazz.getName() + "没有添加Table注解");
		}
		// 有注解--->StringBuffer对象准备拼接sql语句
		buffer = new StringBuilder();
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
					//不处理没有打注解的属性
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
			
			
			return SetSql(buffer,values);
			
		}

		return String.valueOf(0);
	}

	public String SetSql(StringBuilder sql,List<Object> values)
	{
			if (values != null) {
				// 绑定数据
				int index=0;
				for(int i=0;i<sql.length();i++)
				{
					if('?'==sql.charAt(i))
					{
						sql.replace(i, i+1, "'"+values.get(index).toString()+"'");
						index++;
					}
				}
			}
	
			System.out.println(sql.toString());
			return sql.toString();
	}
	
	public final String GetUpdateByIDSql(T t) throws Exception {

		Field ID_field = null;
		Object ID_value = null;
		boolean isok = false;
		// 目标的sql:update 表名 set 列名= ？，列名=？ where id=?
		StringBuilder buffer = null;
		Class<T> clazz = (Class<T>) t.getClass();
		// 判断是否加有Table注解
		boolean flag = clazz.isAnnotationPresent(Table.class);
		if (!flag) // 没有注解则抛出异常
		{
			throw new Exception(clazz.getName() + "没有添加Table注解");
		}
		// 有注解--->StringBuffer对象准备拼接sql语句
		buffer = new StringBuilder();
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
	

		
			return SetSql(buffer,values);
			
			
		}
		return String.valueOf(0);
	}

	
	
	public final String GetDeleteSql(T t) throws Exception {
		// 目标sql：delete  from users where name=? and password=?
		StringBuilder buffer = null;
		Class<T> clazz = (Class<T>) t.getClass();
		boolean flag = clazz.isAnnotationPresent(Table.class);
		if (!flag) {
			throw new Exception(clazz.getName() + "没有添加Table注解");
		}
		buffer = new StringBuilder();
		buffer.append("delete  from ");
		// 确定查询的表
		Table table = clazz.getAnnotation(Table.class);
		buffer.append(table.name()+" where ");

		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0)
		{
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
		
				
			return SetSql(buffer,values);
		}
		return null;

		

	}

	

	


	public boolean isinit(Object value) {

		if (value.equals(0.0f) || value.equals(0)||value.equals(0.0d))
			return true;
		return false;
	}

}
