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
 * AccountGroup generated by hbm
 */
@Entity
@EntityListeners(value={
AccountGroup.LastUpdateListener.class,
AccountGroup.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="AccountGroup")
@NamedQueries({
@NamedQuery(name = "AccountGroup.findAll", query = "SELECT n FROM AccountGroup n"),
@NamedQuery(name = "AccountGroup.findByUid", query = "SELECT n FROM AccountGroup n WHERE n.uid = :uid"),
@NamedQuery(name = "AccountGroup.findByCreated", query = "SELECT n FROM AccountGroup n WHERE n.created = :created"),
@NamedQuery(name = "AccountGroup.findByModified", query = "SELECT n FROM AccountGroup n WHERE n.modified = :modified"),
@NamedQuery(name = "AccountGroup.findByName", query = "SELECT n FROM AccountGroup n WHERE n.name = :name"),
@NamedQuery(name = "AccountGroup.findByDescription", query = "SELECT n FROM AccountGroup n WHERE n.description = :description"),
@NamedQuery(name = "AccountGroup.findByStatus", query = "SELECT n FROM AccountGroup n WHERE n.status = :status"),
})
@Table(name="account_group"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = {@UniqueConstraint(columnNames="uid"), @UniqueConstraint(columnNames="name")} 
)
public class AccountGroup  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "AccountGroup.findById";
        public static final String FINDBYUID = "AccountGroup.findByUid";
        public static final String FINDBYCREATED = "AccountGroup.findByCreated";
        public static final String FINDBYMODIFIED = "AccountGroup.findByModified";
        public static final String FINDBYNAME = "AccountGroup.findByName";
        public static final String FINDBYDESCRIPTION = "AccountGroup.findByDescription";
        public static final String FINDBYSTATUS = "AccountGroup.findByStatus";
    }




     private Integer id;
     private String uid;
     private Date created;
     private Date modified;
     private String name;
     private String description;
     private String status;
     private List<AccountHasGroup> accountHasGroups = new ArrayList<AccountHasGroup>(0);

    public AccountGroup() {


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

    }

	
    public AccountGroup(String uid, Date created, Date modified, String name) {
        this.uid = uid;
        this.created = created;
        this.modified = modified;
        this.name = name;
    }
    public AccountGroup(String uid, Date created, Date modified, String name, String description, String status, List<AccountHasGroup> accountHasGroups) {
       this.uid = uid;
       this.created = created;
       this.modified = modified;
       this.name = name;
       this.description = description;
       this.status = status;
       this.accountHasGroups = accountHasGroups;
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
    
    @Column(name="name", unique=true, nullable=false)
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
    
    @Column(name="status", length=45)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
   @OneToMany(fetch=FetchType.LAZY, mappedBy="accountGroup")
   @Cascade(value=CascadeType.ALL)                    
   @Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="AccountGroup.AccountHasGroups")
   @Fetch(value=FetchMode.SUBSELECT)                    
    public List<AccountHasGroup> getAccountHasGroups() {
        return this.accountHasGroups;
    }
    
    public void setAccountHasGroups(List<AccountHasGroup> accountHasGroups) {
        this.accountHasGroups = accountHasGroups;
    }




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(AccountGroup o) {
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
        public void setModified(AccountGroup o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}
