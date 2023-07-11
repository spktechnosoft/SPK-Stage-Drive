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
 * EventHasInterest generated by hbm
 */
@Entity
@EntityListeners(value={
EventHasInterest.LastUpdateListener.class,
EventHasInterest.UidListener.class
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL, region="EventHasInterest")
@NamedQueries({
@NamedQuery(name = "EventHasInterest.findAll", query = "SELECT n FROM EventHasInterest n"),
@NamedQuery(name = "EventHasInterest.findByEvent", query = "SELECT n FROM EventHasInterest n WHERE n.event = :event"),
@NamedQuery(name = "EventHasInterest.findByUid", query = "SELECT n FROM EventHasInterest n WHERE n.uid = :uid"),
@NamedQuery(name = "EventHasInterest.findByAccountId", query = "SELECT n FROM EventHasInterest n WHERE n.accountId = :accountId"),
@NamedQuery(name = "EventHasInterest.findByBookingTicketId", query = "SELECT n FROM EventHasInterest n WHERE n.bookingTicketId = :bookingTicketId"),
@NamedQuery(name = "EventHasInterest.findByBookingId", query = "SELECT n FROM EventHasInterest n WHERE n.bookingId = :bookingId"),
@NamedQuery(name = "EventHasInterest.findByActionLikeId", query = "SELECT n FROM EventHasInterest n WHERE n.actionLikeId = :actionLikeId"),
@NamedQuery(name = "EventHasInterest.findByActionCommentId", query = "SELECT n FROM EventHasInterest n WHERE n.actionCommentId = :actionCommentId"),
@NamedQuery(name = "EventHasInterest.findByActionRideId", query = "SELECT n FROM EventHasInterest n WHERE n.actionRideId = :actionRideId"),
@NamedQuery(name = "EventHasInterest.findByCreated", query = "SELECT n FROM EventHasInterest n WHERE n.created = :created"),
@NamedQuery(name = "EventHasInterest.findByModified", query = "SELECT n FROM EventHasInterest n WHERE n.modified = :modified"),
})
@Table(name="event_has_interest"
/*
    ,catalog="stgdrv_2"
*/
    , uniqueConstraints = @UniqueConstraint(columnNames="uid") 
)
public class EventHasInterest  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "EventHasInterest.findById";
        public static final String FINDBYEVENT = "EventHasInterest.findByEvent";
        public static final String FINDBYUID = "EventHasInterest.findByUid";
        public static final String FINDBYACCOUNTID = "EventHasInterest.findByAccountId";
        public static final String FINDBYBOOKINGTICKETID = "EventHasInterest.findByBookingTicketId";
        public static final String FINDBYBOOKINGID = "EventHasInterest.findByBookingId";
        public static final String FINDBYACTIONLIKEID = "EventHasInterest.findByActionLikeId";
        public static final String FINDBYACTIONCOMMENTID = "EventHasInterest.findByActionCommentId";
        public static final String FINDBYACTIONRIDEID = "EventHasInterest.findByActionRideId";
        public static final String FINDBYCREATED = "EventHasInterest.findByCreated";
        public static final String FINDBYMODIFIED = "EventHasInterest.findByModified";
    }




     private Integer id;
     private Event event;
     private String uid;
     private int accountId;
     private Integer bookingTicketId;
     private Integer bookingId;
     private Integer actionLikeId;
     private Integer actionCommentId;
     private Integer actionRideId;
     private Date created;
     private Date modified;

    public EventHasInterest() {


        //

        //

        //
        //this.setUid(TokenUtils.generateUid());
        //

        //

        //

        //

        //

        //

        //
        this.setCreated(new Date());
        this.setModified(new Date());

        //

        //

    }

	
    public EventHasInterest(Event event, String uid, int accountId, Date created, Date modified) {
        this.event = event;
        this.uid = uid;
        this.accountId = accountId;
        this.created = created;
        this.modified = modified;
    }
    public EventHasInterest(Event event, String uid, int accountId, Integer bookingTicketId, Integer bookingId, Integer actionLikeId, Integer actionCommentId, Integer actionRideId, Date created, Date modified) {
       this.event = event;
       this.uid = uid;
       this.accountId = accountId;
       this.bookingTicketId = bookingTicketId;
       this.bookingId = bookingId;
       this.actionLikeId = actionLikeId;
       this.actionCommentId = actionCommentId;
       this.actionRideId = actionRideId;
       this.created = created;
       this.modified = modified;
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
    @JoinColumn(name="event_id", nullable=false)
    public Event getEvent() {
        return this.event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
    @Column(name="uid", unique=true, nullable=false, length=45)
    public String getUid() {
        return this.uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    @Column(name="accountId", nullable=false)
    public int getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    @Column(name="booking_ticket_id")
    public Integer getBookingTicketId() {
        return this.bookingTicketId;
    }
    
    public void setBookingTicketId(Integer bookingTicketId) {
        this.bookingTicketId = bookingTicketId;
    }
    
    @Column(name="booking_id")
    public Integer getBookingId() {
        return this.bookingId;
    }
    
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    
    @Column(name="action_like_id")
    public Integer getActionLikeId() {
        return this.actionLikeId;
    }
    
    public void setActionLikeId(Integer actionLikeId) {
        this.actionLikeId = actionLikeId;
    }
    
    @Column(name="action_comment_id")
    public Integer getActionCommentId() {
        return this.actionCommentId;
    }
    
    public void setActionCommentId(Integer actionCommentId) {
        this.actionCommentId = actionCommentId;
    }
    
    @Column(name="action_ride_id")
    public Integer getActionRideId() {
        return this.actionRideId;
    }
    
    public void setActionRideId(Integer actionRideId) {
        this.actionRideId = actionRideId;
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




    public class UidListener {
        /**
         * automatic property set before any database persistence
         */
        @PreUpdate
        @PrePersist
        public void setUid(EventHasInterest o) {
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
        public void setModified(EventHasInterest o) {
            if (o.getCreated() == null) {
                o.setCreated(new Date());
            }
            o.setModified(new Date());
        }
    }
}