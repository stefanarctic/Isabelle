// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.resources.prop.WaterTankShrineResource;
// </editor-fold>

class WaterTankShrine extends Prop {

    /* Construct new WaterTankShrine */
    public WaterTankShrine(WaterTankShrineResource resource) {
        super(resource);
    }

    public void setWaterTankShrineResource(WaterTankShrineResource waterTankShrineResource) {
        this.setJointedModelResource(waterTankShrineResource);
    }
}
