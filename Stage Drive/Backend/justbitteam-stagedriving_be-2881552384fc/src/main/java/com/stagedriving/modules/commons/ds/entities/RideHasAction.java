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
 * RideHasAction generated by hbm
 */
@Entity
@EntityListeners(value={
RideHasAction.LastUpdateListener.class,
RideHasAction.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="RideHasAction")
@NamedQueries({
@NamedQuery(name = "RideHasAction.findAll", query = "SELECT n FROM RideHasAction n"),
@NamedQuery(name = "RideHasAction.findByRide", query = "SELECT n FROM RideHasAction n WHERE n.ride = :ride"),
@NamedQuery(name = "RideHasAction.findByCreated", query = "SELECT n FROM RideHasAction n WHERE n.created = :created"),
@NamedQuery(name = "RideHasAction.findByModified", query = "SELECT n FROM RideHasAction n WHERE n.modified = :modified"),
@NamedQuery(name = "RideHasAction.findByVisible", query = "SELECT n FROM RideHasAction n WHERE n.visible = :visible"),
@NamedQuery(name = "RideHasAction.findByStatus", query = "SELECT n FROM RideHasAction n WHERE n.status = :status"),
@NamedQuery(name = "RideHasAction.findByAccountid", query = "SELECT n FROM RideHasAction n WHERE n.accountid = :accountid"),
@NamedQuery(name = "RideHasAction.findByUid", query = "SELECT n FROM RideHasAction n WHERE n.uid = :uid"),
@NamedQuery(name = "RideHasAction.findByTaxonomy", query = "SELECT n FROM RideHasAction n WHERE n.taxonomy = :taxonomy"),
@NamedQuery(name = "RideHasAction.findByContent", query = "SELECT n FROM RideHasAction n WHERE n.content = :content"),
})
@Table(name="ride_has_action"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = @UniqueConstraint(columnNames="uid") 
)
public class RideHasAction  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "RideHasAction.findById";
        public static final String FINDBYRIDE = "RideHasAction.findByRide";
        public static final String FINDBYCREATED = "RideHasAction.findByCreated";
        public static final String FINDBYMODIFIED = "RideHasAction.findByModified";
        public static final String FINDBYVISIBLE = "RideHasAction.findByVisible";
        public static final String FINDBYSTATUS = "RideHasAction.findByStatus";
        public static final String FINDBYACCOUNTID = "RideHasAction.findByAccountid";
        public static final String FINDBYUID = "RideHasAction.findByUid";
        public static final String FINDBYTAXONOMY = "RideHasAction.findByTaxonomy";
        public static final String FINDBYCONTENT = "RideHasAction.findByContent";
    }




     private RideHasActionId id;
     private Ride ride;
     private Date created;
     private Date modified;
     private Boolean visible;
     private String status;
     private int accountid;
     private String uid;
     private String taxonomy;
     private String content;

    public RideHasAction() {


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

        //

    }

	
    public RideHasAction(RideHasActionId id, Ride ride, Date created, Date modified, int accountid, String uid) {
        this.id = id;
        this.ride = ride;
        this.created = created;
        this.modified = modified;
        this.accountid = accountid;
        this.uid = uid;
    }
    public RideHasAction(RideHasActionId id, Ride ride, Date created, Date modified, Boolean visible, String status, int accountid, String uid, String taxonomy, String content) {
       this.id = id;
       this.ride = ride;
       this.created = created;
       this.modified = modified;
       this.visible = visible;
       this.status = status;
       this.accountid = accountid;
       this.uid = uid;
       this.taxonomy = taxonomy;
       this.content = content;
    }
   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="id", column=@Column(name="id", nullable=false) ), 
        @AttributeOverride(name="rideId", column=@Column(name="ride_id", nullable=false) ) } )
    public RideHasActionId getId() {
        return this.id;
    }
    
    public void setId(RideHasActionId id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ride_id", nullable=false, insertable=false, updatable=false)
    public Ride getRide() {
        return this.ride;
    }
    
    public void setRide(Ride ride) {
        this.ride = ride;
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
    
    @Column(name="status", length=45)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="accountid", nullable=false)
    public int getAccountid() {
        return this.accountid;
    }
    
    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }
    
    @Column(name="uid", unique=true, nullable=false, length=45)
    public String getUid() {
        return this.uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    @Column(name="taxonomy", length=45)
    public String getTaxonomy() {
        return this.taxonomy;
    }
    
    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
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
        public void setUid(RideHasAction o) {
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
        public void setModified(RideHasAction o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}
