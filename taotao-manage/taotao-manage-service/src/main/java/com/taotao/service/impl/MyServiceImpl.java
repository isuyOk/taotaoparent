package com.taotao.service.impl;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("myService")
public class MyServiceImpl implements MyService {

    @Autowired
    private TbItemMapper tbItemMapper;

    public void setTbItemMapper(TbItemMapper tbItemMapper) {
        this.tbItemMapper = tbItemMapper;
    }

    public TbItem findItem(long itemid) throws Exception {
        TbItem tbItem=tbItemMapper.selectByPrimaryKey(itemid);

        return tbItem;
    }
}
