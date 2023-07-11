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
 * AccountDevice generated by hbm
 */
@Entity
@EntityListeners(value={
AccountDevice.LastUpdateListener.class,
AccountDevice.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="AccountDevice")
@NamedQueries({
@NamedQuery(name = "AccountDevice.findAll", query = "SELECT n FROM AccountDevice n"),
@NamedQuery(name = "AccountDevice.findByAccount", query = "SELECT n FROM AccountDevice n WHERE n.account = :account"),
@NamedQuery(name = "AccountDevice.findByUid", query = "SELECT n FROM AccountDevice n WHERE n.uid = :uid"),
@NamedQuery(name = "AccountDevice.findByCreated", query = "SELECT n FROM AccountDevice n WHERE n.created = :created"),
@NamedQuery(name = "AccountDevice.findByModified", query = "SELECT n FROM AccountDevice n WHERE n.modified = :modified"),
@NamedQuery(name = "AccountDevice.findByOs", query = "SELECT n FROM AccountDevice n WHERE n.os = :os"),
@NamedQuery(name = "AccountDevice.findByDeviceid", query = "SELECT n FROM AccountDevice n WHERE n.deviceid = :deviceid"),
})
@Table(name="account_device"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = @UniqueConstraint(columnNames="uid") 
)
public class AccountDevice  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "AccountDevice.findById";
        public static final String FINDBYACCOUNT = "AccountDevice.findByAccount";
        public static final String FINDBYUID = "AccountDevice.findByUid";
        public static final String FINDBYCREATED = "AccountDevice.findByCreated";
        public static final String FINDBYMODIFIED = "AccountDevice.findByModified";
        public static final String FINDBYOS = "AccountDevice.findByOs";
        public static final String FINDBYDEVICEID = "AccountDevice.findByDeviceid";
    }




     private Integer id;
     private Account account;
     private String uid;
     private Date created;
     private Date modified;
     private String os;
     private String deviceid;

    public AccountDevice() {


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

    }

	
    public AccountDevice(Account account, String uid, Date created, Date modified) {
        this.account = account;
        this.uid = uid;
        this.created = created;
        this.modified = modified;
    }
    public AccountDevice(Account account, String uid, Date created, Date modified, String os, String deviceid) {
       this.account = account;
       this.uid = uid;
       this.created = created;
       this.modified = modified;
       this.os = os;
       this.deviceid = deviceid;
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
    
    @Column(name="os", length=45)
    public String getOs() {
        return this.os;
    }
    
    public void setOs(String os) {
        this.os = os;
    }
    
    @Column(name="deviceid")
    public String getDeviceid() {
        return this.deviceid;
    }
    
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(AccountDevice o) {
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
        public void setModified(AccountDevice o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}