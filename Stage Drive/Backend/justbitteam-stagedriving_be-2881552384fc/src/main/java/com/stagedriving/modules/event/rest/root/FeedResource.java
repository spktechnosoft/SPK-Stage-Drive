/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.event.rest.root;

import com.codahale.metrics.annotation.Metered;
import com.google.inject.Inject;
import com.stagedriving.modules.commons.utils.DateUtils;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.*;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.ItemDAO;
import com.stagedriving.modules.commons.ds.daos.ItemHasActionDAO;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.*;
import com.stagedriving.modules.commons.mapper.decorators.AccountMapperDecorator;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/feeds")
@Api(value = "feeds", description = "Feed resource")
public class FeedResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FeedResource.class);

    @Inject
    ItemMapperImpl itemMapper;
    @Inject
    EventMapperImpl eventMapper;
    @Inject
    BrandMapperImpl brandMapper;
    @Inject
    ItemHasActionDAO itemHasActionDAO;
    @Inject
    AccountMapperDecorator accountMapper;
    @Inject
    AccountDAO accountDAO;
    @Inject
    ItemDAO itemDAO;
    @Inject
    ColorMapperImpl colorMapper;

    private AppConfiguration configuration;

    @Inject
    public FeedResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve feeds",
            notes = "Retrieves feeds",
            response = FeedDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getFeeds(@Restricted(required = true) Account me,
                             @QueryParam("limit") String limit,
                             @QueryParam("page") String page) {

        int pageQuery = 0;
        if (page != null) {
            pageQuery = Integer.valueOf(page);
        }

        int limitQuery = 1000;
        if (limit != null) {
            limitQuery = Integer.valueOf(limit);
        }

        ArrayList<FeedDTO> feedDtos = new ArrayList<>();

        List<Item> items;
        if(me.getRole().equalsIgnoreCase(StgdrvData.AccountRoles.ADMIN)){
            items = itemDAO.findByVisible(true);
        }else{
            items = itemDAO.findByStatusByVisible(StgdrvData.ItemStatus.APPROVED, true);
        }
        for (Item item : items) {
            ItemDTO itemDto = itemMapper.itemToItemDto(item);

            ArrayList<BrandDTO> brandDtos = new ArrayList<>();
            ArrayList<ColorDTO> colorDtos = new ArrayList<>();
            ArrayList<ItemCategoryDTO> categorieDtos = new ArrayList<>();

            //Brands
            if (item.getItemHasBrands() != null && !item.getItemHasBrands().isEmpty()) {
                item.getItemHasBrands().stream().forEach((itemHasBrand) -> {
                    Brand brand = itemHasBrand.getBrand();
                    if (brand != null) {
                        brandDtos.add(brandMapper.brandToBrandDto(brand));
                    }
                    itemDto.setBrands(brandDtos);
                });
            }
            //Categories
            if (item.getItemHasCategories() != null && !item.getItemHasCategories().isEmpty()) {
                item.getItemHasCategories().stream().forEach((itemHasCategories) -> {
                    ItemCategory category = itemHasCategories.getItemCategory();
                    if (category != null) {
                        ItemCategoryDTO categoryDto = new ItemCategoryDTO();
                        categoryDto.setId(category.getUid());
                        categoryDto.setName(category.getName());
                        categoryDto.setDescription(category.getDescription());
                        categoryDto.setTag(category.getTag());

                        categorieDtos.add(categoryDto);
                    }
                    itemDto.setCategories(categorieDtos);
                });
            }
            //Colors
            if (item.getItemHasColors() != null && !item.getItemHasColors().isEmpty()) {
                item.getItemHasColors().stream().forEach((itemHasColor) -> {
                    Color color = itemHasColor.getColor();
                    if (color != null) {
                        colorDtos.add(colorMapper.colorToColorDto(color));
                    }
                    itemDto.setColors(colorDtos);
                });
            }
            //TODO
            EventDTO eventDto = new EventDTO();
            //EventDTO eventDto = eventMapper.eventToEventDto(item.getCatalogHasItems().get(0).getCatalog().getEventHasCatalogs().get(0).getEvent());

            FeedDTO feedDto = new FeedDTO();
            feedDto.setItem(itemDto);
            feedDto.setEvent(eventDto);
            feedDto.setNcomment(item.getNcomment());
            feedDto.setNlike(item.getNlike());
            feedDto.setContent(item.getPicture());
            feedDto.setCreated(DateUtils.dateToString(item.getCreated()));
            feedDto.setModified(DateUtils.dateToString(item.getModified()));

            feedDto.setLike(false);
            List<ItemHasAction> itemHasActions = itemHasActionDAO.findByTaxonomyByAccountIdByItem(pageQuery, limitQuery, StgdrvData.IteractionTypes.LIKEUP, me.getId(), item);
            if (itemHasActions != null && !itemHasActions.isEmpty()) {
                feedDto.setLike(true);
            }

            feedDto.setBookmark(false);
            itemHasActions = itemHasActionDAO.findByTaxonomyByAccountIdByItem(pageQuery, limitQuery, StgdrvData.IteractionTypes.BOOKMARKUP, me.getId(), item);
            if (itemHasActions != null && !itemHasActions.isEmpty()) {
                feedDto.setBookmark(true);
            }

            ArrayList<CommentDTO> commentDtos = new ArrayList<>();
            itemHasActions = itemHasActionDAO.findByTaxonomyByAccountIdByItem(0, 1000, StgdrvData.IteractionTypes.COMMENTUP, me.getId(), item);
            if (itemHasActions != null && !itemHasActions.isEmpty()) {
                CommentDTO commentDto = new CommentDTO();
                for (ItemHasAction itemHasAction : itemHasActions) {
                    commentDto.setAccount(accountMapper.accountToAccountDto(accountDAO.findById(itemHasAction.getAccountid())));
                    commentDto.setContent(itemHasAction.getContent());
                    commentDtos.add(commentDto);
                }
                feedDto.setComments(commentDtos);
            }
            if(itemDto.getTag()==null || (itemDto.getTag()!=null && !itemDto.getTag().equalsIgnoreCase(StgdrvData.ItemTag.GHOST)))
                feedDtos.add(feedDto);
        }

        Collections.reverse(feedDtos);

        return Response.ok(feedDtos).build();
    }
}
