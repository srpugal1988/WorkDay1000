package com.niralcenter.jcmswebface.home;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.niralcenter.jcmswebface.model.User;

@Repository
public class HomepageRepository {

	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    
    public List<User> fetchAllUserInformationFromDb() {
		List<User> users=new ArrayList<User>();
		
		RowMapper<User> mapper=new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				User user=new User();
				user.setId(rs.getInt("id"));
			    user.setUserrefno(rs.getString("userrefno"));
			    user.setFirstname(rs.getString("firstname"));
			    user.setLastname(rs.getString("lastname"));
			    user.setFullname(rs.getString("fullname"));
			    user.setEmail(rs.getString("email"));
			    user.setContactnumber(rs.getString("contactnumber"));
			    user.setRoleid(rs.getInt("roleid"));
			    user.setRolename(rs.getString("rolename"));
			    user.setJobtitle(rs.getString("jobtitle"));
			    user.setUsername(rs.getString("username"));
			    user.setOnline(rs.getInt("online"));
				return user;
			}
		};
		
		users=jdbcTemplate.query("select * from sm_userdetails",mapper);
		
		return users;
	}
    
}
