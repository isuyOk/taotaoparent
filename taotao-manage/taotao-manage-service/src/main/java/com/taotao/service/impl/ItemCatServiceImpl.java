package com.taotao.service.impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public List<EUTreeNode> getCatList(long parentId) {
        TbItemCatExample tbItemCatExample=new TbItemCatExample();
       Criteria criteria=tbItemCatExample.createCriteria();
       criteria.andParentIdEqualTo(parentId);
       List<TbItemCat> list=itemCatMapper.selectByExample(tbItemCatExample);
       List<EUTreeNode> nodeList=new ArrayList<>();
       for (TbItemCat itemCat:list){
           EUTreeNode node=new EUTreeNode();
           node.setId(itemCat.getId());
           node.setText(itemCat.getName());
           node.setState(itemCat.getIsParent()?"closed":"open");
           nodeList.add(node);
       }
        return nodeList;
    }
}
