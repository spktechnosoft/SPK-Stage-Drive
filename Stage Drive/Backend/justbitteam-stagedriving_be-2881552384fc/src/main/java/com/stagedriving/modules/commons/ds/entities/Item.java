package com.stagedriving.modules.commons.ds.entities;
// Generated 12-giu-2020 14.48.36 by Hibernate Tools 3.4.0.CR1


import com.justbit.commons.TokenUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Item generated by hbm
 */
@Entity
@EntityListeners(value={
Item.LastUpdateListener.class,
Item.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="Item")
@NamedQueries({
@NamedQuery(name = "Item.findAll", query = "SELECT n FROM Item n"),
@NamedQuery(name = "Item.findByUid", query = "SELECT n FROM Item n WHERE n.uid = :uid"),
@NamedQuery(name = "Item.findByName", query = "SELECT n FROM Item n WHERE n.name = :name"),
@NamedQuery(name = "Item.findByDescription", query = "SELECT n FROM Item n WHERE n.description = :description"),
@NamedQuery(name = "Item.findByCreated", query = "SELECT n FROM Item n WHERE n.created = :created"),
@NamedQuery(name = "Item.findByModified", query = "SELECT n FROM Item n WHERE n.modified = :modified"),
@NamedQuery(name = "Item.findByTag", query = "SELECT n FROM Item n WHERE n.tag = :tag"),
@NamedQuery(name = "Item.findByVisible", query = "SELECT n FROM Item n WHERE n.visible = :visible"),
@NamedQuery(name = "Item.findByColor", query = "SELECT n FROM Item n WHERE n.color = :color"),
@NamedQuery(name = "Item.findByStatus", query = "SELECT n FROM Item n WHERE n.status = :status"),
@NamedQuery(name = "Item.findByMale", query = "SELECT n FROM Item n WHERE n.male = :male"),
@NamedQuery(name = "Item.findByFemale", query = "SELECT n FROM Item n WHERE n.female = :female"),
@NamedQuery(name = "Item.findByChildren", query = "SELECT n FROM Item n WHERE n.children = :children"),
@NamedQuery(name = "Item.findByPicture", query = "SELECT n FROM Item n WHERE n.picture = :picture"),
@NamedQuery(name = "Item.findByOutfit", query = "SELECT n FROM Item n WHERE n.outfit = :outfit"),
@NamedQuery(name = "Item.findByPublished", query = "SELECT n FROM Item n WHERE n.published = :published"),
@NamedQuery(name = "Item.findByNlike", query = "SELECT n FROM Item n WHERE n.nlike = :nlike"),
@NamedQuery(name = "Item.findByNcomment", query = "SELECT n FROM Item n WHERE n.ncomment = :ncomment"),
})
@Table(name="item"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = @UniqueConstraint(columnNames="uid") 
)
public class Item  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "Item.findById";
        public static final String FINDBYUID = "Item.findByUid";
        public static final String FINDBYNAME = "Item.findByName";
        public static final String FINDBYDESCRIPTION = "Item.findByDescription";
        public static final String FINDBYCREATED = "Item.findByCreated";
        public static final String FINDBYMODIFIED = "Item.findByModified";
        public static final String FINDBYTAG = "Item.findByTag";
        public static final String FINDBYVISIBLE = "Item.findByVisible";
        public static final String FINDBYCOLOR = "Item.findByColor";
        public static final String FINDBYSTATUS = "Item.findByStatus";
        public static final String FINDBYMALE = "Item.findByMale";
        public static final String FINDBYFEMALE = "Item.findByFemale";
        public static final String FINDBYCHILDREN = "Item.findByChildren";
        public static final String FINDBYPICTURE = "Item.findByPicture";
        public static final String FINDBYOUTFIT = "Item.findByOutfit";
        public static final String FINDBYPUBLISHED = "Item.findByPublished";
        public static final String FINDBYNLIKE = "Item.findByNlike";
        public static final String FINDBYNCOMMENT = "Item.findByNcomment";
    }




     private Integer id;
     private String uid;
     private String name;
     private String description;
     private Date created;
     private Date modified;
     private String tag;
     private Boolean visible;
     private String color;
     private String status;
     private Boolean male;
     private Boolean female;
     private Boolean children;
     private String picture;
     private Boolean outfit;
     private Boolean published;
     private Integer nlike;
     private Integer ncomment;
     private List<CatalogHasItem> catalogHasItems = new ArrayList<CatalogHasItem>(0);
     private List<ItemHasBrand> itemHasBrands = new ArrayList<ItemHasBrand>(0);
     private List<ItemHasColor> itemHasColors = new ArrayList<ItemHasColor>(0);
     private List<ItemHasAction> itemHasActions = new ArrayList<ItemHasAction>(0);
     private List<ItemHasCategory> itemHasCategories = new ArrayList<ItemHasCategory>(0);

    public Item() {


        //

        //
        //this.setUid(TokenUtils.generateUid());
        //

        //

        //
        this.setCreated(new Date());
        this.setModified(new Date());

        //

        //

        //

        //

        //

        //

        //

        //

        //

        //

        //

        //

        //

        //

        //

        //

        //

        //

        //

    }

	
    public Item(String uid, String name, Date created, Date modified) {
        this.uid = uid;
        this.name = name;
        this.created = created;
        this.modified = modified;
    }
    public Item(String uid, String name, String description, Date created, Date modified, String tag, Boolean visible, String color, String status, Boolean male, Boolean female, Boolean children, String picture, Boolean outfit, Boolean published, Integer nlike, Integer ncomment, List<CatalogHasItem> catalogHasItems, List<ItemHasBrand> itemHasBrands, List<ItemHasColor> itemHasColors, List<ItemHasAction> itemHasActions, List<ItemHasCategory> itemHasCategories) {
       this.uid = uid;
       this.name = name;
       this.description = description;
       this.created = created;
       this.modified = modified;
       this.tag = tag;
       this.visible = visible;
       this.color = color;
       this.status = status;
       this.male = male;
       this.female = female;
       this.children = children;
       this.picture = picture;
       this.outfit = outfit;
       this.published = published;
       this.nlike = nlike;
       this.ncomment = ncomment;
       this.catalogHasItems = catalogHasItems;
       this.itemHasBrands = itemHasBrands;
       this.itemHasColors = itemHasColors;
       this.itemHasActions = itemHasActions;
       this.itemHasCategories = itemHasCategories;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="uid", unique=true, nullable=false)
    public String getUid() {
        return this.uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    @Column(name="name", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="description", length=65535)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created", nullable=false, length=0)
    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified", nullable=false, length=0)
    public Date getModified() {
        return this.modified;
    }
    
    public void setModified(Date modified) {
        this.modified = modified;
    }
    
    @Column(name="tag")
    public String getTag() {
        return this.tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    @Column(name="visible")
    public Boolean getVisible() {
        return this.visible;
    }
    
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    @Column(name="color", length=45)
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    @Column(name="status", length=45)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="male")
    public Boolean getMale() {
        return this.male;
    }
    
    public void setMale(Boolean male) {
        this.male = male;
    }
    
    @Column(name="female")
    public Boolean getFemale() {
        return this.female;
    }
    
    public void setFemale(Boolean female) {
        this.female = female;
    }
    
    @Column(name="children")
    public Boolean getChildren() {
        return this.children;
    }
    
    public void setChildren(Boolean children) {
        this.children = children;
    }
    
    @Column(name="picture", length=65535)
    public String getPicture() {
        return this.picture;
    }
    
    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    @Column(name="outfit")
    public Boolean getOutfit() {
        return this.outfit;
    }
    
    public void setOutfit(Boolean outfit) {
        this.outfit = outfit;
    }
    
    @Column(name="published")
    public Boolean getPublished() {
        return this.published;
    }
    
    public void setPublished(Boolean published) {
        this.published = published;
    }
    
    @Column(name="nlike")
    public Integer getNlike() {
        return this.nlike;
    }
    
    public void setNlike(Integer nlike) {
        this.nlike = nlike;
    }
    
    @Column(name="ncomment")
    public Integer getNcomment() {
        return this.ncomment;
    }
    
    public void setNcomment(Integer ncomment) {
        this.ncomment = ncomment;
    }
   @OneToMany(fetch=FetchType.LAZY, mappedBy="item")
   @Cascade(value=CascadeType.ALL)                    
   @Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="Item.CatalogHasItems")
   @Fetch(value=FetchMode.SUBSELECT)                    
    public List<CatalogHasItem> getCatalogHasItems() {
        return this.catalogHasItems;
    }
    
    public void setCatalogHasItems(List<CatalogHasItem> catalogHasItems) {
        this.catalogHasItems = catalogHasItems;
    }
   @OneToMany(fetch=FetchType.LAZY, mappedBy="item")
   @Cascade(value=CascadeType.ALL)                    
   @Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="Item.ItemHasBrands")
   @Fetch(value=FetchMode.SUBSELECT)                    
    public List<ItemHasBrand> getItemHasBrands() {
        return this.itemHasBrands;
    }
    
    public void setItemHasBrands(List<ItemHasBrand> itemHasBrands) {
        this.itemHasBrands = itemHasBrands;
    }
   @OneToMany(fetch=FetchType.LAZY, mappedBy="item")
   @Cascade(value=CascadeType.ALL)                    
   @Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="Item.ItemHasColors")
   @Fetch(value=FetchMode.SUBSELECT)                    
    public List<ItemHasColor> getItemHasColors() {
        return this.itemHasColors;
    }
    
    public void setItemHasColors(List<ItemHasColor> itemHasColors) {
        this.itemHasColors = itemHasColors;
    }
   @OneToMany(fetch=FetchType.LAZY, mappedBy="item")
   @Cascade(value=CascadeType.ALL)                    
   @Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="Item.ItemHasActions")
   @Fetch(value=FetchMode.SUBSELECT)                    
    public List<ItemHasAction> getItemHasActions() {
        return this.itemHasActions;
    }
    
    public void setItemHasActions(List<ItemHasAction> itemHasActions) {
        this.itemHasActions = itemHasActions;
    }
   @OneToMany(fetch=FetchType.LAZY, mappedBy="item")
   @Cascade(value=CascadeType.ALL)                    
   @Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="Item.ItemHasCategories")
   @Fetch(value=FetchMode.SUBSELECT)                    
    public List<ItemHasCategory> getItemHasCategories() {
        return this.itemHasCategories;
    }
    
    public void setItemHasCategories(List<ItemHasCategory> itemHasCategories) {
        this.itemHasCategories = itemHasCategories;
    }




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(Item o) {
            if (o.getUid() == null) {
                o.setUid(TokenUtils.generateUid());
            }
        }
    }

    public class LastUpdateListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setModified(Item o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}
