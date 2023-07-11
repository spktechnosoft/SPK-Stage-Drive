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
 * ItemFamily generated by hbm
 */
@Entity
@EntityListeners(value={
ItemFamily.LastUpdateListener.class,
ItemFamily.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="ItemFamily")
@NamedQueries({
@NamedQuery(name = "ItemFamily.findAll", query = "SELECT n FROM ItemFamily n"),
@NamedQuery(name = "ItemFamily.findByUid", query = "SELECT n FROM ItemFamily n WHERE n.uid = :uid"),
@NamedQuery(name = "ItemFamily.findByCreated", query = "SELECT n FROM ItemFamily n WHERE n.created = :created"),
@NamedQuery(name = "ItemFamily.findByModified", query = "SELECT n FROM ItemFamily n WHERE n.modified = :modified"),
@NamedQuery(name = "ItemFamily.findByName", query = "SELECT n FROM ItemFamily n WHERE n.name = :name"),
@NamedQuery(name = "ItemFamily.findByDescription", query = "SELECT n FROM ItemFamily n WHERE n.description = :description"),
@NamedQuery(name = "ItemFamily.findByVisible", query = "SELECT n FROM ItemFamily n WHERE n.visible = :visible"),
@NamedQuery(name = "ItemFamily.findBySmallUri", query = "SELECT n FROM ItemFamily n WHERE n.smallUri = :smallUri"),
@NamedQuery(name = "ItemFamily.findByNormalUri", query = "SELECT n FROM ItemFamily n WHERE n.normalUri = :normalUri"),
@NamedQuery(name = "ItemFamily.findByLargeUri", query = "SELECT n FROM ItemFamily n WHERE n.largeUri = :largeUri"),
@NamedQuery(name = "ItemFamily.findByPosition", query = "SELECT n FROM ItemFamily n WHERE n.position = :position"),
@NamedQuery(name = "ItemFamily.findByTag", query = "SELECT n FROM ItemFamily n WHERE n.tag = :tag"),
})
@Table(name="item_family"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = {@UniqueConstraint(columnNames="uid"), @UniqueConstraint(columnNames="name")} 
)
public class ItemFamily  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "ItemFamily.findById";
        public static final String FINDBYUID = "ItemFamily.findByUid";
        public static final String FINDBYCREATED = "ItemFamily.findByCreated";
        public static final String FINDBYMODIFIED = "ItemFamily.findByModified";
        public static final String FINDBYNAME = "ItemFamily.findByName";
        public static final String FINDBYDESCRIPTION = "ItemFamily.findByDescription";
        public static final String FINDBYVISIBLE = "ItemFamily.findByVisible";
        public static final String FINDBYSMALLURI = "ItemFamily.findBySmallUri";
        public static final String FINDBYNORMALURI = "ItemFamily.findByNormalUri";
        public static final String FINDBYLARGEURI = "ItemFamily.findByLargeUri";
        public static final String FINDBYPOSITION = "ItemFamily.findByPosition";
        public static final String FINDBYTAG = "ItemFamily.findByTag";
    }




     private Integer id;
     private String uid;
     private Date created;
     private Date modified;
     private String name;
     private String description;
     private Boolean visible;
     private String smallUri;
     private String normalUri;
     private String largeUri;
     private Integer position;
     private String tag;
     private List<ItemCategory> itemCategories = new ArrayList<ItemCategory>(0);

    public ItemFamily() {


        //

        //
        //this.setUid(TokenUtils.generateUid());
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

    }

	
    public ItemFamily(String uid, Date created, Date modified, String name) {
        this.uid = uid;
        this.created = created;
        this.modified = modified;
        this.name = name;
    }
    public ItemFamily(String uid, Date created, Date modified, String name, String description, Boolean visible, String smallUri, String normalUri, String largeUri, Integer position, String tag, List<ItemCategory> itemCategories) {
       this.uid = uid;
       this.created = created;
       this.modified = modified;
       this.name = name;
       this.description = description;
       this.visible = visible;
       this.smallUri = smallUri;
       this.normalUri = normalUri;
       this.largeUri = largeUri;
       this.position = position;
       this.tag = tag;
       this.itemCategories = itemCategories;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="uid", unique=true, nullable=false, length=100)
    public String getUid() {
        return this.uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
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
    
    @Column(name="name", unique=true, nullable=false, length=45)
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
    
    @Column(name="visible")
    public Boolean getVisible() {
        return this.visible;
    }
    
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    @Column(name="small_uri", length=65535)
    public String getSmallUri() {
        return this.smallUri;
    }
    
    public void setSmallUri(String smallUri) {
        this.smallUri = smallUri;
    }
    
    @Column(name="normal_uri", length=65535)
    public String getNormalUri() {
        return this.normalUri;
    }
    
    public void setNormalUri(String normalUri) {
        this.normalUri = normalUri;
    }
    
    @Column(name="large_uri", length=65535)
    public String getLargeUri() {
        return this.largeUri;
    }
    
    public void setLargeUri(String largeUri) {
        this.largeUri = largeUri;
    }
    
    @Column(name="position")
    public Integer getPosition() {
        return this.position;
    }
    
    public void setPosition(Integer position) {
        this.position = position;
    }
    
    @Column(name="tag", length=45)
    public String getTag() {
        return this.tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
   @OneToMany(fetch=FetchType.LAZY, mappedBy="itemFamily")
   @Cascade(value=CascadeType.ALL)                    
   @Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="ItemFamily.ItemCategories")
   @Fetch(value=FetchMode.SUBSELECT)                    
    public List<ItemCategory> getItemCategories() {
        return this.itemCategories;
    }
    
    public void setItemCategories(List<ItemCategory> itemCategories) {
        this.itemCategories = itemCategories;
    }




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(ItemFamily o) {
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
        public void setModified(ItemFamily o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}
