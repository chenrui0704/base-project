package com.base.frame.dao.base;

import java.util.List;
import com.base.frame.entity.base.TypeCode;

public interface TypeCodeMapper  {

	List<TypeCode> query(TypeCode typeCode);

	void insert(TypeCode typeCode);

	void updateById(TypeCode typeCode);

	void updateChildNode(TypeCode typeCode);

	void invalid(TypeCode typeCode);

	void invalidChildNode(TypeCode typeCode);

}