// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.*;
import org.lgna.story.resources.automobile.HumveeResource;
// </editor-fold>

class Humvee extends Automobile {

    /* Construct new Humvee */
    public Humvee() {
        super(HumveeResource.HUMVEE);
    }

    public SJoint getBackLeftDoor() {
        return this.getJoint(HumveeResource.BACK_LEFT_DOOR);
    }

    public SJoint getFrontLeftDoor() {
        return this.getJoint(HumveeResource.FRONT_LEFT_DOOR);
    }

    public SJoint getBackRightDoor() {
        return this.getJoint(HumveeResource.BACK_RIGHT_DOOR);
    }

    public SJoint getFrontRightDoor() {
        return this.getJoint(HumveeResource.FRONT_RIGHT_DOOR);
    }
}
