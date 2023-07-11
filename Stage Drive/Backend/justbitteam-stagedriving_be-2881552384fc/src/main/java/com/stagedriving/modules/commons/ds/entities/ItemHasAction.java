package com.stagedriving.modules.commons.ds.entities;
// Generated 12-giu-2020 14.48.36 by Hibernate Tools 3.4.0.CR1


import com.justbit.commons.TokenUtils;
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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * ItemHasAction generated by hbm
 */
@Entity
@EntityListeners(value={
ItemHasAction.LastUpdateListener.class,
ItemHasAction.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="ItemHasAction")
@NamedQueries({
@NamedQuery(name = "ItemHasAction.findAll", query = "SELECT n FROM ItemHasAction n"),
@NamedQuery(name = "ItemHasAction.findByItem", query = "SELECT n FROM ItemHasAction n WHERE n.item = :item"),
@NamedQuery(name = "ItemHasAction.findByCreated", query = "SELECT n FROM ItemHasAction n WHERE n.created = :created"),
@NamedQuery(name = "ItemHasAction.findByModified", query = "SELECT n FROM ItemHasAction n WHERE n.modified = :modified"),
@NamedQuery(name = "ItemHasAction.findByVisible", query = "SELECT n FROM ItemHasAction n WHERE n.visible = :visible"),
@NamedQuery(name = "ItemHasAction.findByAccountid", query = "SELECT n FROM ItemHasAction n WHERE n.accountid = :accountid"),
@NamedQuery(name = "ItemHasAction.findByTaxonomy", query = "SELECT n FROM ItemHasAction n WHERE n.taxonomy = :taxonomy"),
@NamedQuery(name = "ItemHasAction.findByUid", query = "SELECT n FROM ItemHasAction n WHERE n.uid = :uid"),
@NamedQuery(name = "ItemHasAction.findByContent", query = "SELECT n FROM ItemHasAction n WHERE n.content = :content"),
})
@Table(name="item_has_action"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = @UniqueConstraint(columnNames="uid") 
)
public class ItemHasAction  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "ItemHasAction.findById";
        public static final String FINDBYITEM = "ItemHasAction.findByItem";
        public static final String FINDBYCREATED = "ItemHasAction.findByCreated";
        public static final String FINDBYMODIFIED = "ItemHasAction.findByModified";
        public static final String FINDBYVISIBLE = "ItemHasAction.findByVisible";
        public static final String FINDBYACCOUNTID = "ItemHasAction.findByAccountid";
        public static final String FINDBYTAXONOMY = "ItemHasAction.findByTaxonomy";
        public static final String FINDBYUID = "ItemHasAction.findByUid";
        public static final String FINDBYCONTENT = "ItemHasAction.findByContent";
    }




     private ItemHasActionId id;
     private Item item;
     private Date created;
     private Date modified;
     private Boolean visible;
     private Integer accountid;
     private String taxonomy;
     private String uid;
     private String content;

    public ItemHasAction() {


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
        //this.setUid(TokenUtils.generateUid());
        //

        //

    }

	
    public ItemHasAction(ItemHasActionId id, Item item, Date created, Date modified, String uid) {
        this.id = id;
        this.item = item;
        this.created = created;
        this.modified = modified;
        this.uid = uid;
    }
    public ItemHasAction(ItemHasActionId id, Item item, Date created, Date modified, Boolean visible, Integer accountid, String taxonomy, String uid, String content) {
       this.id = id;
       this.item = item;
       this.created = created;
       this.modified = modified;
       this.visible = visible;
       this.accountid = accountid;
       this.taxonomy = taxonomy;
       this.uid = uid;
       this.content = content;
    }
   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="id", column=@Column(name="id", nullable=false) ), 
        @AttributeOverride(name="itemId", column=@Column(name="item_id", nullable=false) ) } )
    public ItemHasActionId getId() {
        return this.id;
    }
    
    public void setId(ItemHasActionId id) {
        this.id = id;
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
    
    @Column(name="accountid")
    public Integer getAccountid() {
        return this.accountid;
    }
    
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }
    
    @Column(name="taxonomy", length=45)
    public String getTaxonomy() {
        return this.taxonomy;
    }
    
    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }
    
    @Column(name="uid", unique=true, nullable=false, length=45)
    public String getUid() {
        return this.uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    @Column(name="content", length=65535)
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(ItemHasAction o) {
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
        public void setModified(ItemHasAction o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}
