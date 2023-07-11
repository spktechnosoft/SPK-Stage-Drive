/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.catalog.rest.root;

import com.google.inject.Inject;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.ItemCategoryDTO;
import com.stagedriving.commons.v1.resources.ItemFamilyDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.ItemCategoryDAO;
import com.stagedriving.modules.commons.ds.daos.ItemFamilyDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/families")
@Api(value = "families", description = "Families resources")
public class FamilyResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FamilyResource.class);

    @Inject
    ItemFamilyDAO itemFamilyDAO;
    @Inject
    ItemCategoryDAO itemCategoryDAO;

    private AppConfiguration configuration;

    @Inject
    public FamilyResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    @ApiOperation(value = "Retrieve families",
            notes = "Retrieves families",
            response = ItemFamilyDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getFamilies() {

        ArrayList<ItemFamilyDTO> familyDtos = new ArrayList<>();

        itemFamilyDAO.findAll().stream().forEach((family) -> {

            ItemFamilyDTO familyDto = new ItemFamilyDTO();
            familyDto.setName(family.getName());
            familyDto.setDescription(family.getDescription());
            familyDto.setNormalUri(family.getNormalUri());
            familyDto.setTag(family.getTag());
            familyDto.setPosition(family.getPosition());
            familyDto.setCreated(family.getCreated().toString());
            familyDto.setModified(family.getModified().toString());
            familyDto.setId(family.getUid());

            ArrayList<ItemCategoryDTO> categoryDtos = new ArrayList<>();
            if(family.getItemCategories()!=null && family.getItemCategories().size()>0){

                family.getItemCategories().stream().forEach((category) -> {

                    ItemCategoryDTO categoryDto = new ItemCategoryDTO();
                    categoryDto.setName(category.getName());
                    categoryDto.setDescription(category.getDescription());
                    categoryDto.setTag(category.getTag());
                    categoryDto.setPosition(category.getPosition());
                    categoryDto.setCreated(category.getCreated().toString());
                    categoryDto.setModified(category.getModified().toString());
                    categoryDto.setId(category.getUid());
                    categoryDtos.add(categoryDto);
                });

                familyDto.setCategories(categoryDtos);
            }

            familyDtos.add(familyDto);
        });

        return Response.ok(familyDtos).build();
    }
}
