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
 * AccountConnection generated by hbm
 */
@Entity
@EntityListeners(value={
AccountConnection.LastUpdateListener.class,
AccountConnection.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="AccountConnection")
@NamedQueries({
@NamedQuery(name = "AccountConnection.findAll", query = "SELECT n FROM AccountConnection n"),
@NamedQuery(name = "AccountConnection.findByAccount", query = "SELECT n FROM AccountConnection n WHERE n.account = :account"),
@NamedQuery(name = "AccountConnection.findByUid", query = "SELECT n FROM AccountConnection n WHERE n.uid = :uid"),
@NamedQuery(name = "AccountConnection.findByCreated", query = "SELECT n FROM AccountConnection n WHERE n.created = :created"),
@NamedQuery(name = "AccountConnection.findByModified", query = "SELECT n FROM AccountConnection n WHERE n.modified = :modified"),
@NamedQuery(name = "AccountConnection.findByProvider", query = "SELECT n FROM AccountConnection n WHERE n.provider = :provider"),
@NamedQuery(name = "AccountConnection.findByToken", query = "SELECT n FROM AccountConnection n WHERE n.token = :token"),
@NamedQuery(name = "AccountConnection.findByExpires", query = "SELECT n FROM AccountConnection n WHERE n.expires = :expires"),
@NamedQuery(name = "AccountConnection.findByRefresh", query = "SELECT n FROM AccountConnection n WHERE n.refresh = :refresh"),
@NamedQuery(name = "AccountConnection.findByCode", query = "SELECT n FROM AccountConnection n WHERE n.code = :code"),
})
@Table(name="account_connection"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = @UniqueConstraint(columnNames="uid") 
)
public class AccountConnection  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "AccountConnection.findById";
        public static final String FINDBYACCOUNT = "AccountConnection.findByAccount";
        public static final String FINDBYUID = "AccountConnection.findByUid";
        public static final String FINDBYCREATED = "AccountConnection.findByCreated";
        public static final String FINDBYMODIFIED = "AccountConnection.findByModified";
        public static final String FINDBYPROVIDER = "AccountConnection.findByProvider";
        public static final String FINDBYTOKEN = "AccountConnection.findByToken";
        public static final String FINDBYEXPIRES = "AccountConnection.findByExpires";
        public static final String FINDBYREFRESH = "AccountConnection.findByRefresh";
        public static final String FINDBYCODE = "AccountConnection.findByCode";
    }




     private String id;
     private Account account;
     private String uid;
     private Date created;
     private Date modified;
     private String provider;
     private String token;
     private Date expires;
     private String refresh;
     private String code;

    public AccountConnection() {


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

	
    public AccountConnection(String id, Account account, String uid, Date created, Date modified, String provider) {
        this.id = id;
        this.account = account;
        this.uid = uid;
        this.created = created;
        this.modified = modified;
        this.provider = provider;
    }
    public AccountConnection(String id, Account account, String uid, Date created, Date modified, String provider, String token, Date expires, String refresh, String code) {
       this.id = id;
       this.account = account;
       this.uid = uid;
       this.created = created;
       this.modified = modified;
       this.provider = provider;
       this.token = token;
       this.expires = expires;
       this.refresh = refresh;
       this.code = code;
    }
   
     @Id 
    
    @Column(name="id", unique=true, nullable=false)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
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
    
    @Column(name="provider", nullable=false)
    public String getProvider() {
        return this.provider;
    }
    
    public void setProvider(String provider) {
        this.provider = provider;
    }
    
    @Column(name="token", length=65535)
    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="expires", length=0)
    public Date getExpires() {
        return this.expires;
    }
    
    public void setExpires(Date expires) {
        this.expires = expires;
    }
    
    @Column(name="refresh", length=65535)
    public String getRefresh() {
        return this.refresh;
    }
    
    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }
    
    @Column(name="code", length=65535)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(AccountConnection o) {
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
        public void setModified(AccountConnection o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}