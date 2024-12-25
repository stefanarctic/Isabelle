// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.resources.prop.WaterTankResource;
// </editor-fold>

class WaterTank extends Prop {

    /* Construct new WaterTank */
    public WaterTank(WaterTankResource resource) {
        super(resource);
    }

    public void setWaterTankResource(WaterTankResource waterTankResource) {
        this.setJointedModelResource(waterTankResource);
    }
}
