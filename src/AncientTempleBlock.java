// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.resources.prop.AncientTempleBlockResource;
// </editor-fold>

class AncientTempleBlock extends Prop {

    /* Construct new AncientTempleBlock */
    public AncientTempleBlock(AncientTempleBlockResource resource) {
        super(resource);
    }

    public void setAncientTempleBlockResource(AncientTempleBlockResource ancientTempleBlockResource) {
        this.setJointedModelResource(ancientTempleBlockResource);
    }
}
