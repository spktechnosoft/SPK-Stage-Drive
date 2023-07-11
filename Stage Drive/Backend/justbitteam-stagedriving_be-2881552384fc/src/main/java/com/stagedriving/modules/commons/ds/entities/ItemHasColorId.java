package com.stagedriving.modules.commons.ds.entities;
// Generated 12-giu-2020 14.48.36 by Hibernate Tools 3.4.0.CR1


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ItemHasColorId generated by hbm
 */
@Embeddable
public class ItemHasColorId  implements java.io.Serializable {


    public class NamedQueries {
        public static final String FINDBYITEMID = "ItemHasColorId.findByItemId";
        public static final String FINDBYCOLORID = "ItemHasColorId.findByColorId";
    }




     private int itemId;
     private int colorId;

    public ItemHasColorId() {


        //

        //

    }

    public ItemHasColorId(int itemId, int colorId) {
       this.itemId = itemId;
       this.colorId = colorId;
    }
   

    @Column(name="item_id", nullable=false)
    public int getItemId() {
        return this.itemId;
    }
    
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Column(name="color_id", nullable=false)
    public int getColorId() {
        return this.colorId;
    }
    
    public void setColorId(int colorId) {
        this.colorId = colorId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ItemHasColorId) ) return false;
		 ItemHasColorId castOther = ( ItemHasColorId ) other; 
         
		 return (this.getItemId()==castOther.getItemId())
 && (this.getColorId()==castOther.getColorId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getItemId();
         result = 37 * result + this.getColorId();
         return result;
   }   



}
