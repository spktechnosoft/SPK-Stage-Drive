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
 * EventStuff generated by hbm
 */
@Entity
@EntityListeners(value={
EventStuff.LastUpdateListener.class,
EventStuff.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="EventStuff")
@NamedQueries({
@NamedQuery(name = "EventStuff.findAll", query = "SELECT n FROM EventStuff n"),
@NamedQuery(name = "EventStuff.findByEvent", query = "SELECT n FROM EventStuff n WHERE n.event = :event"),
@NamedQuery(name = "EventStuff.findByUid", query = "SELECT n FROM EventStuff n WHERE n.uid = :uid"),
@NamedQuery(name = "EventStuff.findByTaxonomy", query = "SELECT n FROM EventStuff n WHERE n.taxonomy = :taxonomy"),
@NamedQuery(name = "EventStuff.findByContent", query = "SELECT n FROM EventStuff n WHERE n.content = :content"),
@NamedQuery(name = "EventStuff.findByCreated", query = "SELECT n FROM EventStuff n WHERE n.created = :created"),
@NamedQuery(name = "EventStuff.findByModified", query = "SELECT n FROM EventStuff n WHERE n.modified = :modified"),
@NamedQuery(name = "EventStuff.findByVisible", query = "SELECT n FROM EventStuff n WHERE n.visible = :visible"),
})
@Table(name="event_stuff"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = @UniqueConstraint(columnNames="uid") 
)
public class EventStuff  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "EventStuff.findById";
        public static final String FINDBYEVENT = "EventStuff.findByEvent";
        public static final String FINDBYUID = "EventStuff.findByUid";
        public static final String FINDBYTAXONOMY = "EventStuff.findByTaxonomy";
        public static final String FINDBYCONTENT = "EventStuff.findByContent";
        public static final String FINDBYCREATED = "EventStuff.findByCreated";
        public static final String FINDBYMODIFIED = "EventStuff.findByModified";
        public static final String FINDBYVISIBLE = "EventStuff.findByVisible";
    }




     private EventStuffId id;
     private Event event;
     private String uid;
     private String taxonomy;
     private String content;
     private Date created;
     private Date modified;
     private Boolean visible;

    public EventStuff() {


        //

        //

        //
        //this.setUid(TokenUtils.generateUid());
        //

        //

        //
        this.setCreated(new Date());
        this.setModified(new Date());

        //

        //

        //

    }

	
    public EventStuff(EventStuffId id, Event event, String uid, String taxonomy, String content, Date created, Date modified) {
        this.id = id;
        this.event = event;
        this.uid = uid;
        this.taxonomy = taxonomy;
        this.content = content;
        this.created = created;
        this.modified = modified;
    }
    public EventStuff(EventStuffId id, Event event, String uid, String taxonomy, String content, Date created, Date modified, Boolean visible) {
       this.id = id;
       this.event = event;
       this.uid = uid;
       this.taxonomy = taxonomy;
       this.content = content;
       this.created = created;
       this.modified = modified;
       this.visible = visible;
    }
   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="id", column=@Column(name="id", nullable=false) ), 
        @AttributeOverride(name="eventId", column=@Column(name="event_id", nullable=false) ) } )
    public EventStuffId getId() {
        return this.id;
    }
    
    public void setId(EventStuffId id) {
        this.id = id;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="event_id", nullable=false, insertable=false, updatable=false)
    public Event getEvent() {
        return this.event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
    @Column(name="uid", unique=true, nullable=false)
    public String getUid() {
        return this.uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    @Column(name="taxonomy", nullable=false, length=45)
    public String getTaxonomy() {
        return this.taxonomy;
    }
    
    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }
    
    @Column(name="content", nullable=false, length=65535)
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
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




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(EventStuff o) {
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
        public void setModified(EventStuff o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}
