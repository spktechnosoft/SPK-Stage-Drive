package com.stagedriving.modules.commons.ds.entities;
// Generated 12-giu-2020 14.48.36 by Hibernate Tools 3.4.0.CR1


import com.justbit.commons.TokenUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
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
 * AccountVehicle generated by hbm
 */
@Entity
@EntityListeners(value={
AccountVehicle.LastUpdateListener.class,
AccountVehicle.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="AccountVehicle")
@NamedQueries({
@NamedQuery(name = "AccountVehicle.findAll", query = "SELECT n FROM AccountVehicle n"),
@NamedQuery(name = "AccountVehicle.findByAccount", query = "SELECT n FROM AccountVehicle n WHERE n.account = :account"),
@NamedQuery(name = "AccountVehicle.findByUid", query = "SELECT n FROM AccountVehicle n WHERE n.uid = :uid"),
@NamedQuery(name = "AccountVehicle.findByCreated", query = "SELECT n FROM AccountVehicle n WHERE n.created = :created"),
@NamedQuery(name = "AccountVehicle.findByModified", query = "SELECT n FROM AccountVehicle n WHERE n.modified = :modified"),
@NamedQuery(name = "AccountVehicle.findByStatus", query = "SELECT n FROM AccountVehicle n WHERE n.status = :status"),
@NamedQuery(name = "AccountVehicle.findByManufacturer", query = "SELECT n FROM AccountVehicle n WHERE n.manufacturer = :manufacturer"),
@NamedQuery(name = "AccountVehicle.findByName", query = "SELECT n FROM AccountVehicle n WHERE n.name = :name"),
@NamedQuery(name = "AccountVehicle.findByDescription", query = "SELECT n FROM AccountVehicle n WHERE n.description = :description"),
@NamedQuery(name = "AccountVehicle.findByFeatures", query = "SELECT n FROM AccountVehicle n WHERE n.features = :features"),
})
@Table(name="account_vehicle"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = @UniqueConstraint(columnNames="uid") 
)
public class AccountVehicle  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "AccountVehicle.findById";
        public static final String FINDBYACCOUNT = "AccountVehicle.findByAccount";
        public static final String FINDBYUID = "AccountVehicle.findByUid";
        public static final String FINDBYCREATED = "AccountVehicle.findByCreated";
        public static final String FINDBYMODIFIED = "AccountVehicle.findByModified";
        public static final String FINDBYSTATUS = "AccountVehicle.findByStatus";
        public static final String FINDBYMANUFACTURER = "AccountVehicle.findByManufacturer";
        public static final String FINDBYNAME = "AccountVehicle.findByName";
        public static final String FINDBYDESCRIPTION = "AccountVehicle.findByDescription";
        public static final String FINDBYFEATURES = "AccountVehicle.findByFeatures";
    }




     private Integer id;
     private Account account;
     private String uid;
     private Date created;
     private Date modified;
     private String status;
     private String manufacturer;
     private String name;
     private String description;
     private String features;

    public AccountVehicle() {


        //

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

    }

	
    public AccountVehicle(Account account, String uid, Date created, Date modified, String status, String manufacturer, String name, String features) {
        this.account = account;
        this.uid = uid;
        this.created = created;
        this.modified = modified;
        this.status = status;
        this.manufacturer = manufacturer;
        this.name = name;
        this.features = features;
    }
    public AccountVehicle(Account account, String uid, Date created, Date modified, String status, String manufacturer, String name, String description, String features) {
       this.account = account;
       this.uid = uid;
       this.created = created;
       this.modified = modified;
       this.status = status;
       this.manufacturer = manufacturer;
       this.name = name;
       this.description = description;
       this.features = features;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id", nullable=false)
    public Account getAccount() {
        return this.account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
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
    
    @Column(name="status", nullable=false, length=15)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="manufacturer", nullable=false, length=60)
    public String getManufacturer() {
        return this.manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    @Column(name="name", nullable=false)
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
    
    @Column(name="features", nullable=false)
    public String getFeatures() {
        return this.features;
    }
    
    public void setFeatures(String features) {
        this.features = features;
    }




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(AccountVehicle o) {
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
        public void setModified(AccountVehicle o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}
