// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.*;
import org.lgna.story.resources.AutomobileResource;
// </editor-fold>

class Automobile extends Transport {

    /* Construct new Automobile */
    public Automobile(AutomobileResource resource) {
        super(resource);
    }

    public SJoint getBackWheels() {
        return this.getJoint(AutomobileResource.BACK_WHEELS);
    }

    public SJoint getFrontRightWheel() {
        return this.getJoint(AutomobileResource.FRONT_RIGHT_WHEEL);
    }

    public SJoint getFrontLeftWheel() {
        return this.getJoint(AutomobileResource.FRONT_LEFT_WHEEL);
    }
}
