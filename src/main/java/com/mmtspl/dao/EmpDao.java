package com.mmtspl.dao;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;  
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;

import com.mmtspl.beans.Emp;  
  
public class EmpDao {  
JdbcTemplate template;  
  
public void setTemplate(JdbcTemplate template) {  
    this.template = template;  
}  
public int save(Emp p){  
    String sql="insert into employee_master(name,salary,age) values('"+p.getName()+"',"+p.getSalary()+",'"+p.getAge()+"')";  
    return template.update(sql);  
}  
public int update(Emp p){  
    String sql="update employee_master set name='"+p.getName()+"', salary="+p.getSalary()+",age='"+p.getAge()+"' where id="+p.getId()+"";  
    return template.update(sql);  
}  
public int delete(int id){  
    String sql="delete from employee_master where id="+id+"";  
    return template.update(sql);  
}  
public Emp getEmpById(int id){  
    String sql="select * from employee_master where id=?";  
    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Emp>(Emp.class));  
}  
public List<Emp> getEmployees(){  
    return template.query("select * from employee_master",new RowMapper<Emp>(){  
        public Emp mapRow(ResultSet rs, int row) throws SQLException {  
            Emp e=new Emp();  
            e.setId(rs.getInt(1));  
            e.setName(rs.getString(2));  
            e.setSalary(rs.getFloat(3));  
            e.setAge(rs.getString(4));  
            return e;  
        }  
    });  
}  
}  