package com.stagedriving.modules.commons.ds.entities;
// Generated 12-giu-2020 14.48.36 by Hibernate Tools 3.4.0.CR1


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EventHasBookingId generated by hbm
 */
@Embeddable
public class EventHasBookingId  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYBOOKINGID = "EventHasBookingId.findByBookingId";
        public static final String FINDBYEVENTID = "EventHasBookingId.findByEventId";
    }




     private int bookingId;
     private int eventId;

    public EventHasBookingId() {


        //

        //

    }

    public EventHasBookingId(int bookingId, int eventId) {
       this.bookingId = bookingId;
       this.eventId = eventId;
    }
   

    @Column(name="booking_id", nullable=false)
    public int getBookingId() {
        return this.bookingId;
    }
    
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Column(name="event_id", nullable=false)
    public int getEventId() {
        return this.eventId;
    }
    
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EventHasBookingId) ) return false;
		 EventHasBookingId castOther = ( EventHasBookingId ) other; 
         
		 return (this.getBookingId()==castOther.getBookingId())
 && (this.getEventId()==castOther.getEventId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getBookingId();
         result = 37 * result + this.getEventId();
         return result;
   }   



}