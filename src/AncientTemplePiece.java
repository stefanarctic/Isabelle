// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.resources.prop.AncientTemplePieceResource;
// </editor-fold>

class AncientTemplePiece extends Prop {

    /* Construct new AncientTemplePiece */
    public AncientTemplePiece(AncientTemplePieceResource resource) {
        super(resource);
    }

    public void setAncientTemplePieceResource(AncientTemplePieceResource ancientTemplePieceResource) {
        this.setJointedModelResource(ancientTemplePieceResource);
    }
}
