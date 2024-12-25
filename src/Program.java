// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.*;
// </editor-fold>

class Program extends SProgram {

    /* Construct new Program */
    public Program() {
        super();
    }
    /* Create a scene, named myScene */
    private final Scene myScene = new Scene();

    public static void main(String[] args) {
// Create a runtime window, then display and activate myScene in the window
        final Program story = new Program();
        story.initializeInFrame(args);
        story.setActiveScene(story.getMyScene());
    }

    /* End main */


 /* Procedures and functions for this program */
    public Scene getMyScene() {
        return this.myScene;
    }
}
