package com.chenzujian.mapper;

import java.util.List;

import com.chenzujian.entity.PersonDept;

public interface departmentMapper {
	//一对多延迟加载
	List<PersonDept> queryDeptAndPersonLazyLoad();
}
