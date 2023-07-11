package com.stagedriving.modules.catalog.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.CatalogDAO;
import com.stagedriving.modules.commons.ds.entities.Catalog;
import com.stagedriving.modules.commons.ds.entities.Item;

@Singleton
public class CatalogController {

    @Inject
    private CatalogDAO catalogDAO;

    public Item getCatalogFirstItem(String catalogName) {
        Catalog catalog = catalogDAO.findByName(catalogName);
        if (catalog != null) {
            if (!catalog.getCatalogHasItems().isEmpty()) {
                return catalog.getCatalogHasItems().get(0).getItem();
            }
        }

        return null;
    }

    public String getCatalogFirstItemName(String catalogName) {
        Item item = getCatalogFirstItem(catalogName);
        if (item != null) {
            return item.getName();
        }

        return null;
    }

    public String getCatalogFirstItemDescription(String catalogName) {
        Item item = getCatalogFirstItem(catalogName);
        if (item != null) {
            return item.getDescription();
        }

        return null;
    }

}
