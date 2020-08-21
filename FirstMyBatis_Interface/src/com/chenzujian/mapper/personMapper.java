package com.chenzujian.mapper;

import java.util.List;
import java.util.Map;

import com.chenzujian.entity.Id;
import com.chenzujian.entity.Person;
import com.chenzujian.entity.PersonCard;
import com.chenzujian.entity.PersonDept;

public interface personMapper {
	
	/**
	 * MyBatis接口开发，约定优于配置，约定如下：
	 * 	1.接口名就是mapper.xml映射文件的全类名
	 *  2.接口的方法名就是sql标签的id值，帮助定位唯一sql语句
	 *  3.方法的参数值类型，必须和该方法执行的sql语句的parameterType参数类型一致
	 *  4.方法的返回值类型，必须和该方法执行的sql语句的resultType（返回值）类型一致
	 * 
	 * */
	Person selectPerson(int id);
	
	int addPerson(Person person);
	
	int deletePerson(int id);
	
	int updatePerson(Person person);
	
	List<Person> queryAllPerson();
	
	void deletePersonByIdWithProcedure(Map<String, Object> params);
	
	//用sql标签动态查询
	Person queryPersonBySqlTag(Person person);
	//用对象传参 在mapper中用对象的List属性取值 使用foreach查询
	List<Person> queryPersonByIdIns(Id id);
	
	List<Person> queryPersonByIdArray(int[] idArray);
	
	List<Person> queryPersonByList(List<Integer> idList);
	List<Person> queryPersonByInsArray(Person[] insArray);
	
	//一对一关联查询
	PersonCard queryPersonAndCardByIdWithOTO(int id);
	//一对一关联查询 使用resultMap
	Person queryPersonAndCardByIdWithOTO2(int id);
	
	PersonDept queryDpetAndPerson(int deptid);
	
	//一对一延迟加载
	List<Person>queryPersonAndCardByIdLazyLoad();
	
	//一对多延迟加载
//	List<PersonDept>queryDpetAndPersonLazyLoad(int id);
	
}
