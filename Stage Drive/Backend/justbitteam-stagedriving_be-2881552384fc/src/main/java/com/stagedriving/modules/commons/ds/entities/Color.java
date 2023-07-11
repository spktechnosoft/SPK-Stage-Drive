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
 * Color generated by hbm
 */
@Entity
@EntityListeners(value={
Color.LastUpdateListener.class,
Color.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="Color")
@NamedQueries({
@NamedQuery(name = "Color.findAll", query = "SELECT n FROM Color n"),
@NamedQuery(name = "Color.findByUid", query = "SELECT n FROM Color n WHERE n.uid = :uid"),
@NamedQuery(name = "Color.findByCode", query = "SELECT n FROM Color n WHERE n.code = :code"),
@NamedQuery(name = "Color.findByCreated", query = "SELECT n FROM Color n WHERE n.created = :created"),
@NamedQuery(name = "Color.findByModified", query = "SELECT n FROM Color n WHERE n.modified = :modified"),
@NamedQuery(name = "Color.findByName", query = "SELECT n FROM Color n WHERE n.name = :name"),
@NamedQuery(name = "Color.findByDescription", query = "SELECT n FROM Color n WHERE n.description = :description"),
@NamedQuery(name = "Color.findByTag", query = "SELECT n FROM Color n WHERE n.tag = :tag"),
@NamedQuery(name = "Color.findByPosition", query = "SELECT n FROM Color n WHERE n.position = :position"),
})
@Table(name="color"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = {@UniqueConstraint(columnNames="uid"), @UniqueConstraint(columnNames="name"), @UniqueConstraint(columnNames="code")} 
)
public class Color  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "Color.findById";
        public static final String FINDBYUID = "Color.findByUid";
        public static final String FINDBYCODE = "Color.findByCode";
        public static final String FINDBYCREATED = "Color.findByCreated";
        public static final String FINDBYMODIFIED = "Color.findByModified";
        public static final String FINDBYNAME = "Color.findByName";
        public static final String FINDBYDESCRIPTION = "Color.findByDescription";
        public static final String FINDBYTAG = "Color.findByTag";
        public static final String FINDBYPOSITION = "Color.findByPosition";
    }




     private Integer id;
     private String uid;
     private String code;
     private Date created;
     private Date modified;
     private String name;
     private String description;
     private String tag;
     private Integer position;
     private List<ItemHasColor> itemHasColors = new ArrayList<ItemHasColor>(0);

    public Color() {


        //

        //
        //this.setUid(TokenUtils.generateUid());
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

    }

	
    public Color(String uid, String code, Date created, Date modified, String name) {
        this.uid = uid;
        this.code = code;
        this.created = created;
        this.modified = modified;
        this.name = name;
    }
    public Color(String uid, String code, Date created, Date modified, String name, String description, String tag, Integer position, List<ItemHasColor> itemHasColors) {
       this.uid = uid;
       this.code = code;
       this.created = created;
       this.modified = modified;
       this.name = name;
       this.description = description;
       this.tag = tag;
       this.position = position;
       this.itemHasColors = itemHasColors;
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
    
    @Column(name="code", unique=true, nullable=false, length=45)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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
    
    @Column(name="name", unique=true, nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="tag", length=45)
    public String getTag() {
        return this.tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    @Column(name="position")
    public Integer getPosition() {
        return this.position;
    }
    
    public void setPosition(Integer position) {
        this.position = position;
    }
   @OneToMany(fetch=FetchType.LAZY, mappedBy="color")
   @Cascade(value=CascadeType.ALL)                    
   @Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="Color.ItemHasColors")
   @Fetch(value=FetchMode.SUBSELECT)                    
    public List<ItemHasColor> getItemHasColors() {
        return this.itemHasColors;
    }
    
    public void setItemHasColors(List<ItemHasColor> itemHasColors) {
        this.itemHasColors = itemHasColors;
    }




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(Color o) {
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
        public void setModified(Color o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}