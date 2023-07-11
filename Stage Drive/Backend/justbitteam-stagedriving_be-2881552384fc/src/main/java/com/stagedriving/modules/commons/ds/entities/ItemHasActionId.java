package com.stagedriving.modules.commons.ds.entities;
// Generated 12-giu-2020 14.48.36 by Hibernate Tools 3.4.0.CR1


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ItemHasActionId generated by hbm
 */
@Embeddable
public class ItemHasActionId  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYID = "ItemHasActionId.findById";
        public static final String FINDBYITEMID = "ItemHasActionId.findByItemId";
    }




     private int id;
     private int itemId;

    public ItemHasActionId() {


        //

        //

    }

    public ItemHasActionId(int id, int itemId) {
       this.id = id;
       this.itemId = itemId;
    }
   

    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="item_id", nullable=false)
    public int getItemId() {
        return this.itemId;
    }
    
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ItemHasActionId) ) return false;
		 ItemHasActionId castOther = ( ItemHasActionId ) other; 
         
		 return (this.getId()==castOther.getId())
 && (this.getItemId()==castOther.getItemId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getId();
         result = 37 * result + this.getItemId();
         return result;
   }   



}