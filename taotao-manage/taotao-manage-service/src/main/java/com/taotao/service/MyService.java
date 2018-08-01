package com.taotao.service;

import com.taotao.pojo.TbItem;
import org.springframework.stereotype.Component;


public interface MyService {
    public TbItem findItem(long id)throws Exception;
}
