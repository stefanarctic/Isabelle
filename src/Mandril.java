// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.*;
import org.lgna.story.resources.biped.MandrilResource;
// </editor-fold>

class Mandril extends Biped {

    /* Construct new Mandril */
    public Mandril() {
        super(MandrilResource.DEFAULT);
    }

    public SJoint getLeftEar() {
        return this.getJoint(MandrilResource.LEFT_EAR);
    }

    public SJoint getRightEar() {
        return this.getJoint(MandrilResource.RIGHT_EAR);
    }

    public SJoint getTail() {
        return this.getJoint(MandrilResource.TAIL_0);
    }

    public SJoint[] getTailArray() {
        return this.getJointArray(MandrilResource.TAIL_ARRAY);
    }
}
