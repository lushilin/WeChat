package com.tcps.java.common.dao;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import oracle.jdbc.OracleTypes;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "jdbcDAO")
public class JdbcDAOImpl implements IBaseDAO {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public int update(String sql, Object... args) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			return 0;
		}
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public List<? extends Object> findListBySql(final String sql) throws Exception{
		if (sql == null || sql.trim().equals("")) {
			return new ArrayList<Object>();
		}
		return this.jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<? extends Object> findListBySql(String sql, RowMapper<? extends Object> rowMapper, Object... args) throws Exception{
		if (sql == null || sql.trim().equals("")) {
			return new ArrayList<Object>();
		}
		return this.jdbcTemplate.query(sql, args, rowMapper);
	}

	@Override
	public List<? extends Object> findListBySql(String sql, int offset, int pageSize) throws Exception{
		if (sql == null || sql.trim().equals("")) {
			return new ArrayList<Object>();
		}
		String querySql = "select * from (select row_.*,rownum rownum_ from (" + sql + ") row_ where rownum <= "
				+ (offset + pageSize) + ") where rownum_ > " + offset;
		return this.jdbcTemplate.queryForList(querySql);
	}

	@Override
	public Long countTotalBySql(final String sql) throws Exception{
		if (sql != null && !sql.trim().equals("")) {
			return Long.parseLong(Integer.toString(jdbcTemplate.queryForObject(sql, Integer.class)));
		}
		return 0L;
	}

	@Override
	public void executeSql(String sql) throws Exception {
		if (sql != null && !sql.trim().equals("")) {
			jdbcTemplate.execute(sql);
		}
	}

	@Override
	public String executeProc(final String proc, final String data) throws Exception {
		return jdbcTemplate.execute(   
		     new CallableStatementCreator() {   
		        public CallableStatement createCallableStatement(Connection con) throws SQLException {   
		           CallableStatement cs = con.prepareCall(proc);   
		           cs.setString(1, data);// 设置输入参数的值   
		           cs.registerOutParameter(2, OracleTypes.VARCHAR);// 注册输出参数的类型   
		           return cs;   
		        }   
		     }, new CallableStatementCallback<Object>() {   
		         public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {   
			         cs.execute();   
			         return cs.getString(2);// 获取输出参数的值   
		     }   
		  }).toString();   
	}
}
