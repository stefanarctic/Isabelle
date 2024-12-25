// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.resources.prop.AncientTempleWallResource;
// </editor-fold>

class AncientTempleWall extends Prop {

    /* Construct new AncientTempleWall */
    public AncientTempleWall(AncientTempleWallResource resource) {
        super(resource);
    }

    public void setAncientTempleWallResource(AncientTempleWallResource ancientTempleWallResource) {
        this.setJointedModelResource(ancientTempleWallResource);
    }
}
