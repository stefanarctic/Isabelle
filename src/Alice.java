// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.*;
import org.lgna.story.resources.biped.AliceResource;
// </editor-fold>

class Alice extends Biped {

    /* Construct new Alice */
    public Alice(AliceResource resource) {
        super(resource);
    }

    public SJoint getSkirtBase() {
        return this.getJoint(AliceResource.SKIRT_BASE);
    }

    public SJoint getSkirtFront() {
        return this.getJoint(AliceResource.SKIRT_FRONT);
    }

    public SJoint getSkirtFrontTip() {
        return this.getJoint(AliceResource.SKIRT_FRONT_TIP);
    }

    public SJoint getSkirtBack() {
        return this.getJoint(AliceResource.SKIRT_BACK);
    }

    public SJoint getSkirtBackTip() {
        return this.getJoint(AliceResource.SKIRT_BACK_TIP);
    }

    public SJoint getSkirtLeft() {
        return this.getJoint(AliceResource.SKIRT_LEFT);
    }

    public SJoint getSkirtLeftTip() {
        return this.getJoint(AliceResource.SKIRT_LEFT_TIP);
    }

    public SJoint getSkirtRight() {
        return this.getJoint(AliceResource.SKIRT_RIGHT);
    }

    public SJoint getSkirtRightTip() {
        return this.getJoint(AliceResource.SKIRT_RIGHT_TIP);
    }

    public void setAliceResource(AliceResource aliceResource) {
        this.setJointedModelResource(aliceResource);
    }
}
