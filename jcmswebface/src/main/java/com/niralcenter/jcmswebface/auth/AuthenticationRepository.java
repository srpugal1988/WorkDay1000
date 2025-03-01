package com.niralcenter.jcmswebface.auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.niralcenter.jcmswebface.model.PrUser;
import com.niralcenter.jcmswebface.model.User;

@Repository
public class AuthenticationRepository {

	
	    @Autowired
	    private JdbcTemplate jdbcTemplate;
	    
	    
	    @Autowired
	    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	    
	    
	    public int count() {
	    	return jdbcTemplate.queryForObject("select count(*) from sm_userdetails;",Integer.class);
	    }
	    
	    
	    public boolean isUserFoundInDB(String username) {
	    	boolean isUserFound=false;
	    	@SuppressWarnings("deprecation")
			int count=jdbcTemplate.queryForObject("select count(*) from sm_userdetails where username=? and isactiveuser=1",new Object[]{username},Integer.class);
	    	if(count>0) {
	    		isUserFound=true;
	    	}
	    	return isUserFound;
	    }
	    
	    
	    public boolean isCredintialsCheckInDB(String username,String password) {
	    	boolean isUserValidationResult=false;
	    	@SuppressWarnings("deprecation")
			int count=jdbcTemplate.queryForObject("select count(*) from sm_userdetails where username=? and password=? and isactiveuser=1",new Object[] {username,password},Integer.class);
	    	if(count>0) {
	    		isUserValidationResult=true;
	    	}
	    	return isUserValidationResult;
	    }
	    
	    
	    
	    public User getUserInformation(String username,String password) {
	    	boolean isUserValidationResult=false;
	    	@SuppressWarnings("deprecation")

	        RowMapper<User> mapper = new RowMapper<User>() {
	        	@Override
	            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	        		User user = new User();
	        		user.setUserrefno(rs.getString("userrefno")+"");
	        		user.setFirstname(rs.getString("firstname"));
	        		user.setLastname(rs.getString("lastname"));
	        		user.setFullname(rs.getString("firstname")+" "+rs.getString("lastname"));
	        		user.setEmail(rs.getString("email"));
	        		user.setContactnumber(rs.getString("contactnumber"));
	        		user.setJobtitle(rs.getString("jobtitle"));
	        		user.setUsername(rs.getString("username"));
	        		user.setRoleid(rs.getInt("roleid"));
	        		user.setRolename(rs.getString("rolename"));
	                return user;
	            }
	        };
	        
	        User user=jdbcTemplate.queryForObject("select * from sm_userdetails where username=? and password=?",new Object[] {username,password},mapper);
	    	return user;
	    }
	    
	    
	    public User loadUserByname(String username) {
	    	boolean isUserValidationResult=false;
	    	@SuppressWarnings("deprecation")

	        RowMapper<User> mapper = new RowMapper<User>() {
	        	@Override
	            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	        		User user = new User();
	        		user.setUserrefno(rs.getString("userrefno")+"");
	        		user.setFirstname(rs.getString("firstname"));
	        		user.setLastname(rs.getString("lastname"));
	        		user.setFullname(rs.getString("firstname")+" "+rs.getString("lastname"));
	        		user.setEmail(rs.getString("email"));
	        		user.setContactnumber(rs.getString("contactnumber"));
	        		user.setJobtitle(rs.getString("jobtitle"));
	        		user.setUsername(rs.getString("username"));
	        		user.setRoleid(rs.getInt("roleid"));
	        		user.setRolename(rs.getString("rolename"));
	                return user;
	            }
	        };
	        
	        User user=jdbcTemplate.queryForObject("select * from sm_userdetails where username=?",new Object[] {username},mapper);
	    	return user;
	    }
	    
	    
	    public void loggingForLogin(User user) {
	    	String username=user.getUsername();
	    	Timestamp logintime = new Timestamp(System.currentTimeMillis());
	    	Date todaydate=new Date();
	    	int loginflag=jdbcTemplate.update("insert into sm_logindetails (name,logintime,dateadded) values(?,?,?)",new Object[] {username,logintime,todaydate});
	    	int onlineflag=jdbcTemplate.update("update sm_userdetails set online=? where username=?",new Object[] {1,username});
	    }
	    
	    
	    
	    public void loggingForLogout(User user) {
	    	String username=user.getUsername();
	    	Timestamp logintime = new Timestamp(System.currentTimeMillis());
	    	Date todaydate=new Date();
	    	int loginflag=jdbcTemplate.update("insert into sm_logindetails (name,logouttime,dateadded) values(?,?,?)",new Object[] {username,logintime,todaydate});
	       	int onlineflag=jdbcTemplate.update("update sm_userdetails set online=? where username=?",new Object[] {0,username});
	    }
	    
	    
	    
	    public List<PrUser> loadPrincipalUsers() {
	    	boolean isUserValidationResult=false;
	    	@SuppressWarnings("deprecation")

	        RowMapper<PrUser> mapper = new RowMapper<PrUser>() {
	        	@Override
	            public PrUser mapRow(ResultSet rs, int rowNum) throws SQLException {
	        		PrUser user = new PrUser();
	        		user.setUsername(rs.getString("username"));
	        		user.setPassword(rs.getString("password"));
	        		user.setRole(rs.getString("rolename"));
	                return user;
	            }
	        };
	        
	        List<PrUser> PrUserlist=jdbcTemplate.query("select * from sm_userdetails",mapper);
	    	return PrUserlist;
	    }
	    
	    
	   
}
