package learning.Mapper;

import learning.Bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMapper {

    @Select("select * from employee where id=#{id}")
    public Employee getEmployeeById(Integer id);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id=#{id}")
    public void updateEmployee(Employee employee);

    @Delete("delete from employee where id=#{id}")
    public void deleteEmployee(Integer id);

    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);

    @Select("select * from employee where lastName=#{lastName}")
    public Employee getEmpBylastName(String lastName);
}





