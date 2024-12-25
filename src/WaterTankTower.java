// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.resources.prop.WaterTankTowerResource;
// </editor-fold>

class WaterTankTower extends Prop {

    /* Construct new WaterTankTower */
    public WaterTankTower(WaterTankTowerResource resource) {
        super(resource);
    }

    public void setWaterTankTowerResource(WaterTankTowerResource waterTankTowerResource) {
        this.setJointedModelResource(waterTankTowerResource);
    }
}
