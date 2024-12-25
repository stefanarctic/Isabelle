// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.resources.prop.AncientTempleArchResource;
// </editor-fold>

class AncientTempleArch extends Prop {

    /* Construct new AncientTempleArch */
    public AncientTempleArch(AncientTempleArchResource resource) {
        super(resource);
    }

    public void setAncientTempleArchResource(AncientTempleArchResource ancientTempleArchResource) {
        this.setJointedModelResource(ancientTempleArchResource);
    }
}
