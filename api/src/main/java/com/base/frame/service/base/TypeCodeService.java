package com.base.frame.service.base;

import com.base.frame.dao.base.TypeCodeMapper;
import com.base.frame.entity.base.TypeCode;
import com.base.frame.util.MyResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TypeCodeService {

    private String rootNodeName = "root";

    private Logger logger = LoggerFactory.getLogger(TypeCode.class);

    @Autowired
    TypeCodeMapper typeCodeMapper;

    public MyResult query(TypeCode typeCode){
        List<TypeCode> list = typeCodeMapper.query(typeCode);
        return list.size() > 0 ? MyResult.success(list): MyResult.error("暂无数据","1001");
    }

    public MyResult save(TypeCode typeCode){
        if(0 == typeCode.getId()){
            typeCode.setCreateTime(new Date());
            typeCodeMapper.insert(typeCode);
        }else{
            typeCode.setUpdateTime(new Date());
            if(typeCode.getNode().equals(rootNodeName)){
                typeCodeMapper.updateChildNode(typeCode);
            }
            typeCodeMapper.updateById(typeCode);
        }
        return MyResult.success();
    }

    public MyResult invalid(TypeCode typeCode){
        if(typeCode.getNode().equals(rootNodeName)){
            typeCodeMapper.invalidChildNode(typeCode);
        }
        typeCodeMapper.invalid(typeCode);
        return MyResult.success();
    }

}
