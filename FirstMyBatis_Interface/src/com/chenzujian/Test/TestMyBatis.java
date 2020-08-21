package com.chenzujian.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.chenzujian.mapper.departmentMapper;
import com.chenzujian.mapper.personMapper;
import com.chenzujian.entity.Id;
import com.chenzujian.entity.Person;
import com.chenzujian.entity.PersonCard;
import com.chenzujian.entity.PersonDept;

public class TestMyBatis {

	public static SqlSession getSession() throws IOException {
		// 加载MyBatis配饰文件
		Reader reader = Resources.getResourceAsReader("config.xml");
		// sqlSessionFactory--connection
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		SqlSession session = sessionFactory.openSession();

		return session;
	}

	public static Person selectPersonById(int id) throws IOException {
		SqlSession session = getSession();

		personMapper personMapper = session.getMapper(personMapper.class);

		Person person = personMapper.selectPerson(id);
		// Person person = session.selectOne(statement, id);
		session.close();
		return person;

	}

	// 动态sql查询
	public static Person selectPersonByIdAndName(Person person) throws IOException {
		SqlSession session = getSession();

		personMapper personMapper = session.getMapper(personMapper.class);

		Person person1 = personMapper.queryPersonBySqlTag(person);
		// Person person = session.selectOne(statement, id);
		session.close();
		return person1;

	}

	// 动态sql查询,用对象传参 在mapper中用对象的List属性取值 使用foreach查询
	public static List<Person> selectPersonByIdInstance(List<Integer> listId) throws IOException {
		SqlSession session = getSession();
		personMapper personMapper = session.getMapper(personMapper.class);

		Id id = new Id();
		// 用id对象装list
		id.setId(listId);
		List<Person> person = personMapper.queryPersonByIdIns(id);
		// Person person = session.selectOne(statement, id);
		session.close();
		return person;

	}

	// 动态sql查询，使用数组传值，在mapper.xml中用foreach取值
	public static List<Person> selectPersonByIdList(List<Integer> idList) throws IOException {
		SqlSession session = getSession();
		personMapper personMapper = session.getMapper(personMapper.class);

		List<Person> person = personMapper.queryPersonByList(idList);
		session.close();
		return person;

	}

	// 动态sql查询，使用数组传值，在mapper.xml中用foreach取值
	public static List<Person> selectPersonByIdAaary(int[] idArray) throws IOException {
		SqlSession session = getSession();
		personMapper personMapper = session.getMapper(personMapper.class);

		List<Person> person = personMapper.queryPersonByIdArray(idArray);
		// Person person = session.selectOne(statement, id);
		session.close();
		return person;

	}

	// 动态sql查询，使用数组传值，在mapper.xml中用foreach取值
	public static List<Person> selectPersonByInsArray(Person[] insArray) throws IOException {
		SqlSession session = getSession();
		personMapper personMapper = session.getMapper(personMapper.class);

		List<Person> person = personMapper.queryPersonByInsArray(insArray);
		session.close();
		return person;

	}
	
	
	
	
	
	//一对一关联查询
	public static PersonCard queryPersonAndCardByIdWithOTO(int id) throws IOException {
		SqlSession session = getSession();

		personMapper personMapper = session.getMapper(personMapper.class);

		PersonCard personBusiness = personMapper.queryPersonAndCardByIdWithOTO(id);
		// Person person = session.selectOne(statement, id);
		session.close();
		return personBusiness;

	}
	
	//一对一关联查询使用resultMap
	public static Person queryPersonAndCardByIdWithOTO2(int id) throws IOException {
		SqlSession session = getSession();

		personMapper personMapper = session.getMapper(personMapper.class);

		Person person = personMapper.queryPersonAndCardByIdWithOTO2(id);
		// Person person = session.selectOne(statement, id);
		session.close();
		return person;

	}
	
	
	
	
	
	//一对多关联查询使用resultMap，查询人和部门
		public static PersonDept queryDpetAndPerson(int id) throws IOException {
			SqlSession session = getSession();

			personMapper personMapper = session.getMapper(personMapper.class);

			PersonDept persondept = personMapper.queryDpetAndPerson(id);
			// Person person = session.selectOne(statement, id);
			session.close();
			return persondept;

		}
		
		
		//一对一查询，使用延迟加载
		public static void queryPersonAndCardByIdLazyLoad() throws IOException {
			SqlSession session = getSession();

			personMapper personMapper = session.getMapper(personMapper.class);

			List<Person> person = personMapper.queryPersonAndCardByIdLazyLoad();
			for (Person person1 : person) {
				System.out.println(person1.getId() + person1.getName());

				System.out.println(person1.getCard().getCardId());
			}

			// Person person = session.selectOne(statement, id);
			session.close();
		//	return person;

		}
		
		
		//一对多查询，使用延迟加载
		public static void queryDpetAndPersonLazyLoad() throws IOException {
			SqlSession session = getSession();

			departmentMapper personMapper = session.getMapper(departmentMapper.class);

			List<PersonDept> personDept = personMapper.queryDeptAndPersonLazyLoad();
			for (PersonDept persondept : personDept) {
				System.out.println(persondept + "这是直接加载");
				for (Person person : persondept.getPerson()) {
					System.out.println("这是延迟加载");
					System.out.println(person.getId()+ " - " + person.getName() + " - " + person.getAge() );

				}

			}
			// Person person = session.selectOne(statement, id);
			session.close();

		}
		
		

	public static boolean insertPerson(Person person) throws IOException {
		SqlSession session = getSession();

		personMapper personMapper = session.getMapper(personMapper.class);

		int count = personMapper.addPerson(person);

		session.commit();// 使用JDBC方式，需要手动进行commit, rollback , close;
		session.close();
		return count > 0 ? true : false;

	}

	public static void deletePersonByIdWithProcedure(int id) throws IOException {
		SqlSession session = getSession();

		personMapper personMapper = session.getMapper(personMapper.class);

		Map<String, Object> params = new HashMap<>();

		params.put("id", id);

		personMapper.deletePersonByIdWithProcedure(params);
		session.commit();
		session.close();

	}

	public static boolean deletePerson(int id) throws IOException {
		SqlSession session = getSession();

		personMapper personMapper = session.getMapper(personMapper.class);
		int count = personMapper.deletePerson(id);
		session.commit();
		session.close();
		return count > 0 ? true : false;
	}

	public static boolean updatePerson(Person person) throws IOException {
		SqlSession session = getSession();

		String statement = "com.chenzujian.entity.personMapper.updatePerson";
		personMapper personMapper = session.getMapper(personMapper.class);
		int count = personMapper.updatePerson(person);
		session.commit();
		session.close();
		return count > 0 ? true : false;
	}

	public static List<Person> queryAllPerson() throws IOException {
		SqlSession session = getSession();
		personMapper personMapper = session.getMapper(personMapper.class);
		List<Person> personList = personMapper.queryAllPerson();
		session.commit();
		session.close();
		return personList;
	}

	public static void main(String[] args) throws IOException {

//		Person person = selectPersonById(1);
//		System.out.println(person);
//
//		Person person2 = new Person(5, "5ssd", 18);
//		insertPerson(person2);

//		 //boolean result = deletePerson(2);
//		
//
//		boolean result = updatePerson(new Person(1,"小明",40));
//		 System.out.println(result);
//		System.out.println(result);
//		List<Person> allPerson = queryAllPerson();
//		
//		deletePersonByIdWithProcedure(2);
//		List<Person> allPerson = queryAllPerson();
//		System.out.println(allPerson);

//		Person person = selectPersonByIdAndName(new Person(4, "xiaoning",0));
//		System.out.println(person);

//		List<Integer> listId = new ArrayList<>();
//		listId.add(1);
//		listId.add(2);
//		listId.add(3);
//		listId.add(4);
//		List<Person> person= selectPersonByIdInstance(listId);
//		System.out.println(person);

//		int[] idArray = {1,2,3,4};
//		List<Person> person = selectPersonByIdAaary(idArray);
//		System.out.println(person);

//		List<Integer> listId = new ArrayList<>();
//		listId.add(1);
//		listId.add(2);
//		listId.add(3);
//		listId.add(4);
//		List<Person> person = selectPersonByIdList(listId);
//		System.out.println(person);
//		Person[] insArray = new Person[4];
//		insArray[0] = new Person(1);
//		insArray[1] = new Person(2);
//		insArray[2] = new Person(3);
//		insArray[3] = new Person(4);
//		
//		List<Person> person = selectPersonByInsArray(insArray);
//		System.out.println(person);
		
		
//		PersonCard PersonCard = queryPersonAndCardByIdWithOTO(1);
//		System.out.println(PersonCard);

//		Person person = queryPersonAndCardByIdWithOTO2(2);
//		System.out.println(person);
		
//		PersonDept queryDpetAndPerson = queryDpetAndPerson(1);
//		for (Person person : queryDpetAndPerson.getPerson()) {
//			System.out.println(person);
//		}
//		System.out.println(queryDpetAndPerson);
		
		
	//	queryPersonAndCardByIdLazyLoad();

		
		
		queryDpetAndPersonLazyLoad();

		
	}

}
