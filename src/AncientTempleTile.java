// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.resources.prop.AncientTempleTileResource;
// </editor-fold>

class AncientTempleTile extends Prop {

    /* Construct new AncientTempleTile */
    public AncientTempleTile(AncientTempleTileResource resource) {
        super(resource);
    }

    public void setAncientTempleTileResource(AncientTempleTileResource ancientTempleTileResource) {
        this.setJointedModelResource(ancientTempleTileResource);
    }
}
