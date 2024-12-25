// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.resources.prop.TerrainResource;
// </editor-fold>

class SandDunes extends Prop {

    /* Construct new SandDunes */
    public SandDunes(TerrainResource resource) {
        super(resource);
    }

    public void setSandDunesResource(TerrainResource sandDunesResource) {
        this.setJointedModelResource(sandDunesResource);
    }
}
