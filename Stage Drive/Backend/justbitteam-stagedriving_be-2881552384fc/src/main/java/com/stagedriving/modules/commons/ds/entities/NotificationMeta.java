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
 * NotificationMeta generated by hbm
 */
@Entity
@EntityListeners(value={
NotificationMeta.LastUpdateListener.class,
NotificationMeta.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="NotificationMeta")
@NamedQueries({
@NamedQuery(name = "NotificationMeta.findAll", query = "SELECT n FROM NotificationMeta n"),
@NamedQuery(name = "NotificationMeta.findByNotification", query = "SELECT n FROM NotificationMeta n WHERE n.notification = :notification"),
@NamedQuery(name = "NotificationMeta.findByCreated", query = "SELECT n FROM NotificationMeta n WHERE n.created = :created"),
@NamedQuery(name = "NotificationMeta.findByModified", query = "SELECT n FROM NotificationMeta n WHERE n.modified = :modified"),
@NamedQuery(name = "NotificationMeta.findByVisible", query = "SELECT n FROM NotificationMeta n WHERE n.visible = :visible"),
@NamedQuery(name = "NotificationMeta.findByUid", query = "SELECT n FROM NotificationMeta n WHERE n.uid = :uid"),
@NamedQuery(name = "NotificationMeta.findByMetaKey", query = "SELECT n FROM NotificationMeta n WHERE n.metaKey = :metaKey"),
@NamedQuery(name = "NotificationMeta.findByMetaValue", query = "SELECT n FROM NotificationMeta n WHERE n.metaValue = :metaValue"),
})
@Table(name="notification_meta"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = {@UniqueConstraint(columnNames={"meta_key", "notification_id"}), @UniqueConstraint(columnNames="uid")} 
)
public class NotificationMeta  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "NotificationMeta.findById";
        public static final String FINDBYNOTIFICATION = "NotificationMeta.findByNotification";
        public static final String FINDBYCREATED = "NotificationMeta.findByCreated";
        public static final String FINDBYMODIFIED = "NotificationMeta.findByModified";
        public static final String FINDBYVISIBLE = "NotificationMeta.findByVisible";
        public static final String FINDBYUID = "NotificationMeta.findByUid";
        public static final String FINDBYMETAKEY = "NotificationMeta.findByMetaKey";
        public static final String FINDBYMETAVALUE = "NotificationMeta.findByMetaValue";
    }




     private Integer id;
     private Notification notification;
     private Date created;
     private Date modified;
     private Boolean visible;
     private String uid;
     private String metaKey;
     private String metaValue;

    public NotificationMeta() {


        //

        //
        this.setCreated(new Date());
        this.setModified(new Date());

        //

        //

        //

        //
        //this.setUid(TokenUtils.generateUid());
        //

        //

        //

    }

	
    public NotificationMeta(Notification notification, Date created, Date modified, String uid, String metaKey, String metaValue) {
        this.notification = notification;
        this.created = created;
        this.modified = modified;
        this.uid = uid;
        this.metaKey = metaKey;
        this.metaValue = metaValue;
    }
    public NotificationMeta(Notification notification, Date created, Date modified, Boolean visible, String uid, String metaKey, String metaValue) {
       this.notification = notification;
       this.created = created;
       this.modified = modified;
       this.visible = visible;
       this.uid = uid;
       this.metaKey = metaKey;
       this.metaValue = metaValue;
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
    @JoinColumn(name="notification_id", nullable=false)
    public Notification getNotification() {
        return this.notification;
    }
    
    public void setNotification(Notification notification) {
        this.notification = notification;
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
    
    @Column(name="uid", unique=true, nullable=false)
    public String getUid() {
        return this.uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    @Column(name="meta_key", nullable=false, length=45)
    public String getMetaKey() {
        return this.metaKey;
    }
    
    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }
    
    @Column(name="meta_value", nullable=false)
    public String getMetaValue() {
        return this.metaValue;
    }
    
    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
    }




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(NotificationMeta o) {
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
        public void setModified(NotificationMeta o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}
