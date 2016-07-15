package com.tcps.java.common.dao;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

/**
 * 所有数据访问服务的接口
 */
public interface IBaseDAO {

	
	/**
	 * 执行JDBC新增、修改、删除等语句
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws ServiceException
	 */
	public int update(String sql, Object... args) throws Exception;
	
	/**
	 * sql查询
	 * @return List 数据集
	 * @throws ServiceException
	 */
	public List<? extends Object> findListBySql(final String sql) throws Exception;
	
	/**
	 * sql查询
	 * 
	 * @param sql
	 * @param rowMapper
	 * @param args
	 * @return
	 */
	public List<? extends Object> findListBySql(String sql, RowMapper<? extends Object> rowMapper, Object... args) throws Exception;

	/**
	 * 分页进行sql查询
	 * @param offset 起始记录（page*pageSize+1）
	 * @param pageSize 每页显示最大记录数
	 * @return List  数据集
	 * @throws ServiceException
	 */
	public List<? extends Object> findListBySql(final String sql, final int offset, final int pageSize) throws Exception;
	
	/**
	 * sql查询总数
	 * @return Long 记录总数
	 * @throws ServiceException
	 */
	public Long countTotalBySql(String sql) throws Exception;
	
	/**
	 * 执行sql
	 * @throws ServiceException
	 */
	public void executeSql(String sql) throws Exception;
	
	/**
	 * 执行存储过程,输入1个参数,输出1个参数
	 * @throws ServiceException
	 */
	public String executeProc(String proc, String data) throws Exception;
	
}

