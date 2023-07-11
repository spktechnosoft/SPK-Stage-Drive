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
 * Catalog generated by hbm
 */
@Entity
@EntityListeners(value={
Catalog.LastUpdateListener.class,
Catalog.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="Catalog")
@NamedQueries({
@NamedQuery(name = "Catalog.findAll", query = "SELECT n FROM Catalog n"),
@NamedQuery(name = "Catalog.findByUid", query = "SELECT n FROM Catalog n WHERE n.uid = :uid"),
@NamedQuery(name = "Catalog.findByCreated", query = "SELECT n FROM Catalog n WHERE n.created = :created"),
@NamedQuery(name = "Catalog.findByModified", query = "SELECT n FROM Catalog n WHERE n.modified = :modified"),
@NamedQuery(name = "Catalog.findByVisible", query = "SELECT n FROM Catalog n WHERE n.visible = :visible"),
@NamedQuery(name = "Catalog.findByCardinality", query = "SELECT n FROM Catalog n WHERE n.cardinality = :cardinality"),
@NamedQuery(name = "Catalog.findByCategory", query = "SELECT n FROM Catalog n WHERE n.category = :category"),
@NamedQuery(name = "Catalog.findByName", query = "SELECT n FROM Catalog n WHERE n.name = :name"),
@NamedQuery(name = "Catalog.findByDescription", query = "SELECT n FROM Catalog n WHERE n.description = :description"),
@NamedQuery(name = "Catalog.findByEventid", query = "SELECT n FROM Catalog n WHERE n.eventid = :eventid"),
})
@Table(name="catalog"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = {@UniqueConstraint(columnNames="uid"), @UniqueConstraint(columnNames="name")} 
)
public class Catalog  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "Catalog.findById";
        public static final String FINDBYUID = "Catalog.findByUid";
        public static final String FINDBYCREATED = "Catalog.findByCreated";
        public static final String FINDBYMODIFIED = "Catalog.findByModified";
        public static final String FINDBYVISIBLE = "Catalog.findByVisible";
        public static final String FINDBYCARDINALITY = "Catalog.findByCardinality";
        public static final String FINDBYCATEGORY = "Catalog.findByCategory";
        public static final String FINDBYNAME = "Catalog.findByName";
        public static final String FINDBYDESCRIPTION = "Catalog.findByDescription";
        public static final String FINDBYEVENTID = "Catalog.findByEventid";
    }




     private Integer id;
     private String uid;
     private Date created;
     private Date modified;
     private Boolean visible;
     private Integer cardinality;
     private String category;
     private String name;
     private String description;
     private String eventid;
     private List<CatalogHasBrand> catalogHasBrands = new ArrayList<CatalogHasBrand>(0);
     private List<CatalogHasItem> catalogHasItems = new ArrayList<CatalogHasItem>(0);

    public Catalog() {


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

    }

	
    public Catalog(String uid, Date created, Date modified, String name) {
        this.uid = uid;
        this.created = created;
        this.modified = modified;
        this.name = name;
    }
    public Catalog(String uid, Date created, Date modified, Boolean visible, Integer cardinality, String category, String name, String description, String eventid, List<CatalogHasBrand> catalogHasBrands, List<CatalogHasItem> catalogHasItems) {
       this.uid = uid;
       this.created = created;
       this.modified = modified;
       this.visible = visible;
       this.cardinality = cardinality;
       this.category = category;
       this.name = name;
       this.description = description;
       this.eventid = eventid;
       this.catalogHasBrands = catalogHasBrands;
       this.catalogHasItems = catalogHasItems;
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
    
    @Column(name="cardinality")
    public Integer getCardinality() {
        return this.cardinality;
    }
    
    public void setCardinality(Integer cardinality) {
        this.cardinality = cardinality;
    }
    
    @Column(name="category", length=45)
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    @Column(name="name", unique=true, nullable=false, length=100)
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
    
    @Column(name="eventid")
    public String getEventid() {
        return this.eventid;
    }
    
    public void setEventid(String eventid) {
        this.eventid = eventid;
    }
   @OneToMany(fetch=FetchType.LAZY, mappedBy="catalog")
   @Cascade(value=CascadeType.ALL)                    
   @Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="Catalog.CatalogHasBrands")
   @Fetch(value=FetchMode.SUBSELECT)                    
    public List<CatalogHasBrand> getCatalogHasBrands() {
        return this.catalogHasBrands;
    }
    
    public void setCatalogHasBrands(List<CatalogHasBrand> catalogHasBrands) {
        this.catalogHasBrands = catalogHasBrands;
    }
   @OneToMany(fetch=FetchType.LAZY, mappedBy="catalog")
   @Cascade(value=CascadeType.ALL)                    
   @Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="Catalog.CatalogHasItems")
   @Fetch(value=FetchMode.SUBSELECT)                    
    public List<CatalogHasItem> getCatalogHasItems() {
        return this.catalogHasItems;
    }
    
    public void setCatalogHasItems(List<CatalogHasItem> catalogHasItems) {
        this.catalogHasItems = catalogHasItems;
    }




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(Catalog o) {
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
        public void setModified(Catalog o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}