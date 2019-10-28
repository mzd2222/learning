package learning.Service;


import learning.Bean.Employee;
import learning.Mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp")  //指定全局参数
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * /@Cacheable将方法的运行结果进行缓存，再要相同的数据，直接从缓存中获取，不用从数据库中取；
     * <p>
     * CacheManager管理多个Cache组件，对缓存真正的操作在cache组件中。每一个缓存组件有自己唯一的名字；
     * 几个属性
     * cacheNames/value：指定缓存组件的名字；以数组的方式。可以指定多个；
     * <p>
     * key:缓存数据时使用的key，默认使用方法参数的值，这里是id值；
     * key可以使用SpEL表达式赋值
     * #id #root.args[0]参数id值
     * key = "#root.methodName + '[' + #id + ']'"  指定key的格式为getEmp[0]..
     * <p>
     * keyGenerator ：key生成器；可以自己指定key生成器的组件id；
     * key和keyGenerator二选一
     * <p>
     * cacheManager：指定缓存管理器
     * cacheResolver：指定缓存解析器    cacheManager二选一；
     * <p>
     * condition：指定符合条件的情况下，缓存
     * condition = "#id>0"
     * condition = "#a0>1"  传入的第一个参数大于1，才进行缓存
     * <p>
     * unless:unless指定条件为true，方法的返回值就不会被返回，和condition相反；
     * unless = "#result == null"
     * <p>
     * sync:是否使用异步；
     *
     * @param id
     * @return
     */

    @Cacheable(cacheNames = {"emp"})  //或者value
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "员工");
        Employee emp = employeeMapper.getEmployeeById(id);
        return emp;
    }


    /**
     * @CachePut:既调用方法也更新缓存 修改了数据库某个数据，同时更新缓存
     * <p>
     * 1.运行时机为：先调用目标方法，再将方法返回值缓存；
     * 没有指定key
     * key默认为 employee  而上面员工缓存使用的是员工id作为key，所以更新失败
     * 员工没有在缓存中更新
     * <p>
     * key = "#employee.id"或者
     * key = "#result.id"
     */

    @CachePut(value = "emp", key = "#employee.id")
    public Employee updateEmployee(Employee employee) {
        System.out.println("更新" + employee.getdId() + "员工");
        employeeMapper.updateEmployee(employee);
        return employee;
    }

    /**
     * @CacheEvict:清除缓存 通过key指定要清楚的数据
     * allEntries = true删除所有缓存
     * <p>
     * beforeInvocation 默认为false，是否在方法之前执行，默认是在方法执行之后执行，用于处理方法出现异常。
     */

    @CacheEvict(value = "emp")
    public void deleteEmp(Integer id) {
        System.out.println(id);
//        employeeMapper.deleteEmployee(id);
    }

    //定义复杂缓存注解
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp", key = "#lastName")},
            put = {
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp", key = "#result.email")}
    )
    public Employee getEmpBylastName(String lastName) {
        return employeeMapper.getEmpBylastName(lastName);
    }

}


