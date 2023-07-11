package com.stagedriving.modules.commons.utils.event;

import com.google.inject.Inject;
import com.stagedriving.modules.commons.ds.daos.ItemHasBrandDAO;
import com.stagedriving.modules.commons.ds.daos.ItemHasCategoryDAO;
import com.stagedriving.modules.commons.ds.daos.ItemHasColorDAO;
import com.stagedriving.modules.commons.ds.entities.*;

import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class ItemUtils {

    @Inject
    ItemHasColorDAO itemHasColorDAO;
    @Inject
    ItemHasBrandDAO itemHasBrandDAO;
    @Inject
    ItemHasCategoryDAO itemHasCategoryDAO;

    @Inject
    public ItemUtils() {
    }

    public Item merge(Item oldItem, Item newItem) {

        oldItem.setName(newItem.getName() != null ? newItem.getName() : oldItem.getName());
        oldItem.setDescription(newItem.getDescription() != null ? newItem.getDescription() : oldItem.getDescription());
        oldItem.setMale(newItem.getMale());
        oldItem.setFemale(newItem.getFemale());
        oldItem.setChildren(newItem.getChildren());
        oldItem.setStatus(newItem.getStatus() != null ? newItem.getStatus() : oldItem.getStatus());
        oldItem.setTag(newItem.getTag() != null ? newItem.getTag() : oldItem.getTag());
        oldItem.setColor(newItem.getColor() != null ? newItem.getColor() : oldItem.getColor());
        oldItem.setPicture(newItem.getPicture() != null ? newItem.getPicture() : oldItem.getPicture());

        return oldItem;
    }

    public boolean itemHasThisColor(Color color, Item item){
        List<ItemHasColor> itemHasColors = itemHasColorDAO.findByItemColor(color, item);
        if(itemHasColors!=null && itemHasColors.size()>0) return true;
        return false;
    }

    public boolean itemHasThisBrand(Brand brand, Item item){
        List<ItemHasBrand> itemHasBrands = itemHasBrandDAO.findByItemBrand(brand, item);
        if(itemHasBrands!=null && itemHasBrands.size()>0) return true;
        return false;
    }

    public boolean itemHasThisCategory(ItemCategory category, Item item){
        ItemHasCategory itemHasCategory = itemHasCategoryDAO.findByItemCategoryByItem(category, item);
        if(itemHasCategory!=null) return true;
        return false;
    }
}


