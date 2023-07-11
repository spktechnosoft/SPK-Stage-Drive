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
 * AccountBilling generated by hbm
 */
@Entity
@EntityListeners(value={
AccountBilling.LastUpdateListener.class,
AccountBilling.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="AccountBilling")
@NamedQueries({
@NamedQuery(name = "AccountBilling.findAll", query = "SELECT n FROM AccountBilling n"),
@NamedQuery(name = "AccountBilling.findByAccount", query = "SELECT n FROM AccountBilling n WHERE n.account = :account"),
@NamedQuery(name = "AccountBilling.findByUid", query = "SELECT n FROM AccountBilling n WHERE n.uid = :uid"),
@NamedQuery(name = "AccountBilling.findByCreated", query = "SELECT n FROM AccountBilling n WHERE n.created = :created"),
@NamedQuery(name = "AccountBilling.findByModified", query = "SELECT n FROM AccountBilling n WHERE n.modified = :modified"),
@NamedQuery(name = "AccountBilling.findByProvider", query = "SELECT n FROM AccountBilling n WHERE n.provider = :provider"),
@NamedQuery(name = "AccountBilling.findByCoordinate", query = "SELECT n FROM AccountBilling n WHERE n.coordinate = :coordinate"),
@NamedQuery(name = "AccountBilling.findByStatus", query = "SELECT n FROM AccountBilling n WHERE n.status = :status"),
@NamedQuery(name = "AccountBilling.findByVisible", query = "SELECT n FROM AccountBilling n WHERE n.visible = :visible"),
@NamedQuery(name = "AccountBilling.findByIban", query = "SELECT n FROM AccountBilling n WHERE n.iban = :iban"),
@NamedQuery(name = "AccountBilling.findBySwift", query = "SELECT n FROM AccountBilling n WHERE n.swift = :swift"),
@NamedQuery(name = "AccountBilling.findByNote", query = "SELECT n FROM AccountBilling n WHERE n.note = :note"),
})
@Table(name="account_billing"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = @UniqueConstraint(columnNames="uid") 
)
public class AccountBilling  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "AccountBilling.findById";
        public static final String FINDBYACCOUNT = "AccountBilling.findByAccount";
        public static final String FINDBYUID = "AccountBilling.findByUid";
        public static final String FINDBYCREATED = "AccountBilling.findByCreated";
        public static final String FINDBYMODIFIED = "AccountBilling.findByModified";
        public static final String FINDBYPROVIDER = "AccountBilling.findByProvider";
        public static final String FINDBYCOORDINATE = "AccountBilling.findByCoordinate";
        public static final String FINDBYSTATUS = "AccountBilling.findByStatus";
        public static final String FINDBYVISIBLE = "AccountBilling.findByVisible";
        public static final String FINDBYIBAN = "AccountBilling.findByIban";
        public static final String FINDBYSWIFT = "AccountBilling.findBySwift";
        public static final String FINDBYNOTE = "AccountBilling.findByNote";
    }




     private AccountBillingId id;
     private Account account;
     private String uid;
     private Date created;
     private Date modified;
     private String provider;
     private String coordinate;
     private String status;
     private Boolean visible;
     private String iban;
     private String swift;
     private String note;

    public AccountBilling() {


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

        //

        //

    }

	
    public AccountBilling(AccountBillingId id, Account account, String uid, Date created, Date modified, String provider) {
        this.id = id;
        this.account = account;
        this.uid = uid;
        this.created = created;
        this.modified = modified;
        this.provider = provider;
    }
    public AccountBilling(AccountBillingId id, Account account, String uid, Date created, Date modified, String provider, String coordinate, String status, Boolean visible, String iban, String swift, String note) {
       this.id = id;
       this.account = account;
       this.uid = uid;
       this.created = created;
       this.modified = modified;
       this.provider = provider;
       this.coordinate = coordinate;
       this.status = status;
       this.visible = visible;
       this.iban = iban;
       this.swift = swift;
       this.note = note;
    }
   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="id", column=@Column(name="id", nullable=false) ), 
        @AttributeOverride(name="accountId", column=@Column(name="account_id", nullable=false) ) } )
    public AccountBillingId getId() {
        return this.id;
    }
    
    public void setId(AccountBillingId id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id", nullable=false, insertable=false, updatable=false)
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
    
    @Column(name="provider", nullable=false, length=45)
    public String getProvider() {
        return this.provider;
    }
    
    public void setProvider(String provider) {
        this.provider = provider;
    }
    
    @Column(name="coordinate")
    public String getCoordinate() {
        return this.coordinate;
    }
    
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
    
    @Column(name="status", length=45)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="visible")
    public Boolean getVisible() {
        return this.visible;
    }
    
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    @Column(name="iban")
    public String getIban() {
        return this.iban;
    }
    
    public void setIban(String iban) {
        this.iban = iban;
    }
    
    @Column(name="swift")
    public String getSwift() {
        return this.swift;
    }
    
    public void setSwift(String swift) {
        this.swift = swift;
    }
    
    @Column(name="note")
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(AccountBilling o) {
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
        public void setModified(AccountBilling o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}