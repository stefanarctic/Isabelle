// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.resources.prop.WaterTankPillarResource;
// </editor-fold>

class WaterTankPillar extends Prop {

    /* Construct new WaterTankPillar */
    public WaterTankPillar(WaterTankPillarResource resource) {
        super(resource);
    }

    public void setWaterTankPillarResource(WaterTankPillarResource waterTankPillarResource) {
        this.setJointedModelResource(waterTankPillarResource);
    }
}
