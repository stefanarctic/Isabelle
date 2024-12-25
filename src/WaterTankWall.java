// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.resources.prop.WaterTankWallResource;
// </editor-fold>

class WaterTankWall extends Prop {

    /* Construct new WaterTankWall */
    public WaterTankWall(WaterTankWallResource resource) {
        super(resource);
    }

    public void setWaterTankWallResource(WaterTankWallResource waterTankWallResource) {
        this.setJointedModelResource(waterTankWallResource);
    }
}
