package com.base.frame.controller.base;

import com.base.frame.entity.base.TypeCode;
import com.base.frame.service.base.TypeCodeService;
import com.base.frame.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/typeCode")
public class TypeCodeController {

    @Autowired
    TypeCodeService typeCodeService;

    @RequestMapping("/query")
    public MyResult query(@RequestBody TypeCode typeCode){
        return typeCodeService.query(typeCode);
    }

    @RequestMapping("/save")
    public MyResult save(@RequestBody TypeCode typeCode){
        return typeCodeService.save(typeCode);
    }

    @RequestMapping("/invalid")
    public MyResult invalid(@RequestBody TypeCode typeCode){
        return typeCodeService.invalid(typeCode);
    }


}
