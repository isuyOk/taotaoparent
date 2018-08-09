package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    public void setItemMapper(TbItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

//    查询商品列表
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample itemExample=new TbItemExample();
        PageHelper.startPage(page,rows);
       List<TbItem> list= itemMapper.selectByExample(itemExample);

        EUDataGridResult result=new EUDataGridResult();
        result.setRows(list);
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem item) {
        long itemId=IDUtils.genItemId();
        item.setId(itemId);
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.insert(item);
        return TaotaoResult.ok();
    }
}
