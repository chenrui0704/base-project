package com.base.frame.entity.base;

import java.util.Date;

import com.base.frame.util.PageModel;
import lombok.Data;

@Data
public class TypeCode{

	private Integer id; // id
	private String node; // 类型节点
	private String name; // 类型名称
	private String code; // 类型代码
	private String description; // 类型描述

	private Date createTime; // 创建时间
	private Integer createId; // 创建人id
	private Date updateTime; // 更新时间
	private Integer updateId; // 更新人id
	private Integer state; // 状态: 0 = 作废, 1 = 有效

	private String nameOrCode; // 查询条件
	private String oldNode; // 更新根节点数据前的节点名称

}