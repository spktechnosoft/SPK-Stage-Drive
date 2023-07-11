package com.stagedriving.modules.commons.ds.entities;
// Generated 12-giu-2020 14.48.36 by Hibernate Tools 3.4.0.CR1


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * ItemHasBrand generated by hbm
 */
@Entity
@EntityListeners(value={
ItemHasBrand.LastUpdateListener.class,
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="ItemHasBrand")
@NamedQueries({
@NamedQuery(name = "ItemHasBrand.findAll", query = "SELECT n FROM ItemHasBrand n"),
@NamedQuery(name = "ItemHasBrand.findByBrand", query = "SELECT n FROM ItemHasBrand n WHERE n.brand = :brand"),
@NamedQuery(name = "ItemHasBrand.findByItem", query = "SELECT n FROM ItemHasBrand n WHERE n.item = :item"),
@NamedQuery(name = "ItemHasBrand.findByCreated", query = "SELECT n FROM ItemHasBrand n WHERE n.created = :created"),
@NamedQuery(name = "ItemHasBrand.findByModified", query = "SELECT n FROM ItemHasBrand n WHERE n.modified = :modified"),
@NamedQuery(name = "ItemHasBrand.findByVisible", query = "SELECT n FROM ItemHasBrand n WHERE n.visible = :visible"),
})
@Table(name="item_has_brand"
/*
    ,catalog="stgdrv_2"
*/
)
public class ItemHasBrand  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "ItemHasBrand.findById";
        public static final String FINDBYBRAND = "ItemHasBrand.findByBrand";
        public static final String FINDBYITEM = "ItemHasBrand.findByItem";
        public static final String FINDBYCREATED = "ItemHasBrand.findByCreated";
        public static final String FINDBYMODIFIED = "ItemHasBrand.findByModified";
        public static final String FINDBYVISIBLE = "ItemHasBrand.findByVisible";
    }




     private ItemHasBrandId id;
     private Brand brand;
     private Item item;
     private Date created;
     private Date modified;
     private Boolean visible;

    public ItemHasBrand() {


        //

        //

        //
        this.setCreated(new Date());
        this.setModified(new Date());

        //

        //

        //

    }

	
    public ItemHasBrand(ItemHasBrandId id, Brand brand, Item item, Date created, Date modified) {
        this.id = id;
        this.brand = brand;
        this.item = item;
        this.created = created;
        this.modified = modified;
    }
    public ItemHasBrand(ItemHasBrandId id, Brand brand, Item item, Date created, Date modified, Boolean visible) {
       this.id = id;
       this.brand = brand;
       this.item = item;
       this.created = created;
       this.modified = modified;
       this.visible = visible;
    }
   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="itemId", column=@Column(name="item_id", nullable=false) ), 
        @AttributeOverride(name="brandId", column=@Column(name="brand_id", nullable=false) ) } )
    public ItemHasBrandId getId() {
        return this.id;
    }
    
    public void setId(ItemHasBrandId id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="brand_id", nullable=false, insertable=false, updatable=false)
    public Brand getBrand() {
        return this.brand;
    }
    
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="item_id", nullable=false, insertable=false, updatable=false)
    public Item getItem() {
        return this.item;
    }
    
    public void setItem(Item item) {
        this.item = item;
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
    
    @Column(name="visible")
    public Boolean getVisible() {
        return this.visible;
    }
    
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }





    public class LastUpdateListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setModified(ItemHasBrand o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}