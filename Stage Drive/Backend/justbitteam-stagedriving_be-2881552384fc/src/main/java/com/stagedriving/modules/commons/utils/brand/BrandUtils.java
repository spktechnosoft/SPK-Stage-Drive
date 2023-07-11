package com.stagedriving.modules.commons.utils.brand;

import com.google.inject.Inject;
import com.stagedriving.modules.commons.ds.entities.Brand;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class BrandUtils {

    @Inject
    public BrandUtils() {
    }

    public Brand merge(Brand oldBrand, Brand newBrand) {

        oldBrand.setName(newBrand.getName() != null ? newBrand.getName() : oldBrand.getName());
        oldBrand.setDescription(newBrand.getDescription() != null ? newBrand.getDescription() : oldBrand.getDescription());
        oldBrand.setUri(newBrand.getUri() != null ? newBrand.getUri() : oldBrand.getUri());

        return oldBrand;
    }
}


