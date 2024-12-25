// <editor-fold defaultstate="collapsed" desc="imports">

import org.lgna.story.*;
import org.lgna.story.event.TimeEvent;
import org.lgna.story.event.SceneActivationEvent;
import org.lgna.story.event.KeyEvent;
import org.lgna.story.event.EndCollisionEvent;
import org.lgna.story.resources.biped.AliceResource;
import org.lgna.story.event.StartCollisionEvent;
import org.lgna.story.SGround.SurfaceAppearance;
import static org.lgna.common.ThreadUtilities.doTogether;
import org.lgna.story.resources.prop.AncientTempleArchResource;
import org.lgna.story.resources.prop.AncientTempleBlockResource;
import org.lgna.story.resources.prop.AncientTemplePieceResource;
import org.lgna.story.resources.prop.AncientTemplePillarResource;
import org.lgna.story.resources.prop.AncientTempleTileResource;
import org.lgna.story.resources.prop.AncientTempleWallResource;
import org.lgna.story.resources.prop.TerrainResource;
import org.lgna.story.resources.prop.WaterTankPillarResource;
import org.lgna.story.resources.prop.WaterTankResource;
import org.lgna.story.resources.prop.WaterTankShrineResource;
import org.lgna.story.resources.prop.WaterTankTowerResource;
import org.lgna.story.resources.prop.WaterTankWallResource;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;
// </editor-fold>

class Scene extends SScene {

    /* Construct new Scene */
    public Scene() {
        super();
    }

    /* Event listeners */
    private void initializeEventListeners() {
        this.addSceneActivationListener((SceneActivationEvent event) -> {
            this.humvee.turnToFace(this.Isabelle, TurnToFace.duration(0.25));
            this.humvee.setVehicle(this.ground);
            this.mandril.turnToFace(this.Isabelle, TurnToFace.duration(0.0), TurnToFace.animationStyle(AnimationStyle.BEGIN_AND_END_ABRUPTLY));
        });
//        this.addTimeListener((TimeEvent event) -> {
//            this.humvee.turnToFace(this.Isabelle, TurnToFace.duration(0.0), TurnToFace.animationStyle(AnimationStyle.BEGIN_AND_END_ABRUPTLY));
//            this.humvee.moveToward(this.Isabelle, 0.25, MoveToward.animationStyle(AnimationStyle.BEGIN_AND_END_ABRUPTLY), MoveToward.duration(0.01));
//            this.mandril.setVehicle(this.humvee);
//
//            // Set humvee to always be on ground
//            Position humveePosition = this.humvee.getPositionRelativeToVehicle();
//            Position newPosition = new Position(humveePosition.getRight(), 0.0, humveePosition.getBackward());
//            this.humvee.setPositionRelativeToVehicle(newPosition, SetPositionRelativeToVehicle.duration(0.0));
//        }, 0.01);
        this.addTimeListener((TimeEvent event) -> {
            this.camera.setVehicle(this.Isabelle);
        }, 0.25);
        this.addKeyPressListener((KeyEvent event) -> {
            if (event.isKey(Key.W)) {
                if (this.can_move_forward) {
                    this.Isabelle.move(MoveDirection.FORWARD, 2.0, Move.duration(0.25), Move.animationStyle(AnimationStyle.BEGIN_AND_END_ABRUPTLY));
                } else {
                }
            } else {
                if (event.isKey(Key.A)) {
                    this.Isabelle.turn(TurnDirection.LEFT, 0.05, Turn.duration(0.25), Turn.animationStyle(AnimationStyle.BEGIN_AND_END_ABRUPTLY));
                } else {
                    if (event.isKey(Key.D)) {
                        this.Isabelle.turn(TurnDirection.RIGHT, 0.05, Turn.duration(0.25), Turn.animationStyle(AnimationStyle.BEGIN_AND_END_ABRUPTLY));
                    } else {
                        if (this.can_move_backward) {
                            if (event.isKey(Key.S)) {
                                this.Isabelle.move(MoveDirection.BACKWARD, 2.0, Move.duration(0.25), Move.animationStyle(AnimationStyle.BEGIN_AND_END_ABRUPTLY));
                            } else {
                            }
                        } else {
                        }
                    }
                }
            }
        }, AddKeyPressListener.heldKeyPolicy(HeldKeyPolicy.FIRE_MULTIPLE));
        this.addKeyPressListener((KeyEvent event) -> {
            if (event.isKey(Key.SPACE)) {
                this.Isabelle.move(MoveDirection.UP, 0.5, Move.duration(0.5), Move.animationStyle(AnimationStyle.BEGIN_ABRUPTLY_AND_END_GENTLY));
                this.Isabelle.move(MoveDirection.DOWN, 0.5, Move.duration(0.5), Move.animationStyle(AnimationStyle.BEGIN_AND_END_GENTLY));
            } else {
            }
        });
        this.addKeyPressListener((KeyEvent event) -> {
            if (event.isKey(Key.C)) {
                doTogether(() -> {
                    this.Isabelle.getRightShoulder().turn(TurnDirection.LEFT, 0.05, Turn.duration(0.5));
                }, () -> {
                    this.Isabelle.getRightShoulder().turn(TurnDirection.BACKWARD, 0.125, Turn.duration(0.5));
                }, () -> {
                    this.Isabelle.getRightIndexFinger().turn(TurnDirection.BACKWARD, 0.125, Turn.duration(0.5));
                }, () -> {
                    this.Isabelle.getRightThumb().turn(TurnDirection.FORWARD, 0.125, Turn.duration(0.5));
                });
                /* disabled
this.Isabelle.getRightWrist().roll(RollDirection.RIGHT,0.25);
                 */
                doTogether(() -> {
                    this.Isabelle.getRightIndexFinger().turn(TurnDirection.FORWARD, 0.125, Turn.duration(0.5));
                }, () -> {
                    this.Isabelle.getRightThumb().turn(TurnDirection.BACKWARD, 0.125, Turn.duration(0.5));
                }, () -> {
                    this.Isabelle.getRightShoulder().turn(TurnDirection.FORWARD, 0.125, Turn.duration(0.5));
                }, () -> {
                    this.Isabelle.getRightShoulder().turn(TurnDirection.RIGHT, 0.05, Turn.duration(0.5));
                });
            } else {
            }
        });
        
        
        this.addCollisionStartListener((StartCollisionEvent event) -> {
            this.Isabelle.move(MoveDirection.FORWARD, 7.0, Move.duration(0.25), Move.animationStyle(AnimationStyle.BEGIN_AND_END_ABRUPTLY), Move.asSeenBy(this.humvee));
            this.Isabelle.say("au");
        }, new SThing[]{this.Isabelle}, new SThing[]{this.humvee});
        
        // Check collision with all objects and stop movement if collided
        
        
        
        
        this.addCollisionEndListener((EndCollisionEvent event) -> {
        }, new SThing[]{this.Isabelle}, new SThing[]{this.humvee});
        
        
        
        this.addKeyPressListener((KeyEvent event) -> {
            if (event.isKey(Key.F)) {
                switch_camera_front();
            } else if (event.isKey(Key.B)) {
                switch_camera_back();
            }
        });
        this.addCollisionStartListener((StartCollisionEvent event) -> {
            this.can_move_forward = false;
        }, new SThing[]{this.Isabelle.getHead()}, new SThing[]{this.alice});
        this.addCollisionEndListener((EndCollisionEvent event) -> {
            this.can_move_forward = true;
        }, new SThing[]{this.Isabelle.getHead()}, new SThing[]{this.alice});
        this.addCollisionStartListener((StartCollisionEvent event) -> {
            this.can_move_backward = false;
        }, new SThing[]{this.Isabelle.getTail()}, new SThing[]{this.alice});
        this.addCollisionEndListener((EndCollisionEvent event) -> {
            this.can_move_backward = true;
        }, new SThing[]{this.Isabelle.getTail()}, new SThing[]{this.alice});
    }

    public List<SThing> getAllSceneObjects() {
      List<SThing> allObjects = new ArrayList<>();
      for (Field field : this.getClass().getDeclaredFields()) {
        try {
          field.setAccessible(true); // Make private fields accessible
          Object object = field.get(this);
          if (object instanceof SThing) { // Check if it's a scene object
            allObjects.add((SThing) object);
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
      return allObjects;
    }

    /* Procedures and functions for this scene */
    public void moveIsabelle() {
    }

    public Boolean switch_camera() {
        this.camera.orientTo(this.Isabelle);
        this.camera.moveTo(this.Isabelle);
        this.camera.move(MoveDirection.UP, 2.0);
        this.camera.move(MoveDirection.FORWARD, 10.0);
        this.camera.pointAt(this.Isabelle);
        this.camera.turn(TurnDirection.BACKWARD, 8.0);
        return true;
    }

    public void switch_camera_front() {
        this.camera.orientTo(this.Isabelle, OrientTo.duration(0.0));
        this.camera.moveTo(this.Isabelle, MoveTo.duration(0.0));
        this.camera.move(MoveDirection.UP, 2.0, Move.duration(0.0));
        this.camera.move(MoveDirection.FORWARD, 10.0, Move.duration(0.0));
        this.camera.pointAt(this.Isabelle, PointAt.duration(0.0));
        this.camera.turn(TurnDirection.BACKWARD, 8.0, Turn.duration(0.0));
    }

    public void switch_camera_back() {
        this.camera.orientTo(this.Isabelle, OrientTo.duration(0.0));
        this.camera.moveTo(this.Isabelle, MoveTo.duration(0.0));
        this.camera.move(MoveDirection.UP, 2.0, Move.duration(0.0));
        this.camera.move(MoveDirection.BACKWARD, 10.0, Move.duration(0.0));
        this.camera.pointAt(this.Isabelle, PointAt.duration(0.0));
        this.camera.turn(TurnDirection.BACKWARD, 8.0, Turn.duration(0.0));
    }
    /* End procedures and functions for this scene */

    // <editor-fold defaultstate="collapsed" desc="/* Scene fields */">
    private final SGround ground = new SGround();
    private final SCamera camera = new SCamera();
    private final Nagi Isabelle = new Nagi();
    private final BaobabTree baobabTree = new BaobabTree();
    private final Humvee humvee = new Humvee();
    private final Alice alice = new Alice(AliceResource.CARNEGIE_MELLON);
    private Boolean can_move_forward = true;
    private Boolean can_move_backward = true;
    private final Mandril mandril = new Mandril();
    private final SRoom room = new SRoom();
    
    private final ElephantStable elephantStable = new ElephantStable();
    private final AncientTempleTile ancientTempleTile2 = new AncientTempleTile(AncientTempleTileResource.GOLD);
    private final AncientTempleTile ancientTempleTile = new AncientTempleTile(AncientTempleTileResource.GOLD);
    private final AncientTempleTile ancientTempleTile3 = new AncientTempleTile(AncientTempleTileResource.GOLD);
    private final AncientTempleTile ancientTempleTile4 = new AncientTempleTile(AncientTempleTileResource.GOLD);
    private final AncientTempleTile ancientTempleTile5 = new AncientTempleTile(AncientTempleTileResource.GOLD);
    private final AncientTempleTile ancientTempleTile6 = new AncientTempleTile(AncientTempleTileResource.GOLD);
    private final AncientTempleTile ancientTempleTile7 = new AncientTempleTile(AncientTempleTileResource.GOLD);
    private final SandDunes sandDunes2 = new SandDunes(TerrainResource.BLOB_FOREST_FLOOR_BROWN);
    private final SandDunes sandDunes = new SandDunes(TerrainResource.SQUARE_DESERT);
    private final SandDunes sandDunes3 = new SandDunes(TerrainResource.SQUARE_DESERT);
    private final SandDunes sandDunes4 = new SandDunes(TerrainResource.SQUARE_DESERT);
    private final SandDunes sandDunes5 = new SandDunes(TerrainResource.SQUARE_DESERT);
    private final SandDunes sandDunes6 = new SandDunes(TerrainResource.OVAL_DESERT);
    private final SandDunes sandDunes7 = new SandDunes(TerrainResource.CRESCENT_DESERT);
    private final AncientTempleTile ancientTempleTile8 = new AncientTempleTile(AncientTempleTileResource.GOLD);
    private final WaterTank waterTank = new WaterTank(WaterTankResource.WATER_GOLD);
    private final AncientTempleWall ancientTempleWall = new AncientTempleWall(AncientTempleWallResource.GRAY);
    private final AncientTempleWall ancientTempleWall2 = new AncientTempleWall(AncientTempleWallResource.GRAY);
    private final AncientTempleWall ancientTempleWall3 = new AncientTempleWall(AncientTempleWallResource.GRAY);
    private final AncientTemplePillar ancientTemplePillar = new AncientTemplePillar(AncientTemplePillarResource.GRAY);
    private final AncientTempleWall ancientTempleWall4 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall5 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall6 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall7 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall8 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall9 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall10 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall11 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall12 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall13 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall14 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall15 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall16 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall17 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall18 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall19 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall20 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall21 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall22 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall23 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall24 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall25 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall26 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTempleWall ancientTempleWall27 = new AncientTempleWall(AncientTempleWallResource.GOLD);
    private final AncientTemplePillar ancientTemplePillar2 = new AncientTemplePillar(AncientTemplePillarResource.GOLD);
    private final AncientTempleTile ancientTempleTile9 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile10 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile11 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile12 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile13 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile14 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile15 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile16 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile17 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile18 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile19 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile20 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile21 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile22 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile23 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile24 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final FigTree figTree = new FigTree();
    private final FigTree figTree2 = new FigTree();
    private final FigTree figTree3 = new FigTree();
    private final WaterTankTower waterTankTower = new WaterTankTower(WaterTankTowerResource.RED);
    private final WaterTankShrine waterTankShrine = new WaterTankShrine(WaterTankShrineResource.GRAY);
    private final WaterTankPillar waterTankPillar = new WaterTankPillar(WaterTankPillarResource.RED);
    private final AncientTempleBlock ancientTempleBlock = new AncientTempleBlock(AncientTempleBlockResource.ARCHES_RED);
    private final AncientTempleArch ancientTempleArch = new AncientTempleArch(AncientTempleArchResource.RED);
    private final SCameraMarker cameraMarker1 = new SCameraMarker();
    private final AncientTempleTreeStump ancientTempleTreeStump = new AncientTempleTreeStump();
    private final WaterTankWall waterTankWall = new WaterTankWall(WaterTankWallResource.CIRCLE_GOLD);
    private final AncientTempleBlock ancientTempleBlock2 = new AncientTempleBlock(AncientTempleBlockResource.ARCHES_RED);
    private final AncientTempleBlock ancientTempleBlock3 = new AncientTempleBlock(AncientTempleBlockResource.SOLID_GRAY);
    private final AncientTempleBlock ancientTempleBlock4 = new AncientTempleBlock(AncientTempleBlockResource.SHELF_GOLD);
    private final AncientTempleWall ancientTempleWall28 = new AncientTempleWall(AncientTempleWallResource.SAND);
    private final AncientTempleWall ancientTempleWall29 = new AncientTempleWall(AncientTempleWallResource.SAND);
    private final AncientTempleWall ancientTempleWall30 = new AncientTempleWall(AncientTempleWallResource.SAND);
    private final AncientTempleWall ancientTempleWall31 = new AncientTempleWall(AncientTempleWallResource.SAND);
    private final AncientTempleWall ancientTempleWall32 = new AncientTempleWall(AncientTempleWallResource.SAND);
    private final AncientTempleWall ancientTempleWall33 = new AncientTempleWall(AncientTempleWallResource.SAND);
    private final AncientTempleWall ancientTempleWall34 = new AncientTempleWall(AncientTempleWallResource.SAND);
    private final AncientTempleWall ancientTempleWall35 = new AncientTempleWall(AncientTempleWallResource.SAND);
    private final AncientTempleBlock ancientTempleBlock5 = new AncientTempleBlock(AncientTempleBlockResource.PASSAGE_RED);
    private final AncientTempleArch ancientTempleArch2 = new AncientTempleArch(AncientTempleArchResource.RED);
    private final AncientTempleBlock ancientTempleBlock6 = new AncientTempleBlock(AncientTempleBlockResource.ARCHES_SAND);
    private final AncientTempleTile ancientTempleTile25 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile26 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile27 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile28 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile29 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile30 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile31 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile32 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTempleTile ancientTempleTile33 = new AncientTempleTile(AncientTempleTileResource.SAND);
    private final AncientTemplePiece ancientTemplePiece = new AncientTemplePiece(AncientTemplePieceResource.PLAZA_SAND);
    private final AncientTemplePiece ancientTemplePiece2 = new AncientTemplePiece(AncientTemplePieceResource.END_SAND);
    private final FigTree figTree4 = new FigTree();
    private final FigTree figTree5 = new FigTree();
    private final FigTree figTree6 = new FigTree();
    private final CypressTree cypressTree = new CypressTree();
    private final MangoTree mangoTree = new MangoTree();
    private final MangoTree mangoTree2 = new MangoTree();
    private final MangoTree mangoTree3 = new MangoTree();
    private final CypressTree cypressTree2 = new CypressTree();
    private final AncientTempleTreeStump ancientTempleTreeStump2 = new AncientTempleTreeStump();
    private final CypressTree cypressTree3 = new CypressTree();
    private final CypressTree cypressTree4 = new CypressTree();
    private final SandDunes sandDunes8 = new SandDunes(TerrainResource.OVAL_DESERT);
    private final SandDunes sandDunes9 = new SandDunes(TerrainResource.OVAL_DESERT);
    private final SandDunes sandDunes10 = new SandDunes(TerrainResource.OVAL_DESERT);
    private final CypressTree cypressTree5 = new CypressTree();
    private final MangoTree mangoTree4 = new MangoTree();
    private final MangoTree mangoTree5 = new MangoTree();
    private final MangoTree mangoTree6 = new MangoTree();
    private final MangoTree mangoTree7 = new MangoTree();
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/* Scene setup */">
    private void performCustomSetup() {
// Make adjustments to the starting scene, in a way not available in the Scene editor
        this.camera.orientTo(this.Isabelle);
        this.camera.moveTo(this.Isabelle);
        this.camera.move(MoveDirection.UP, 2.0);
        this.camera.move(MoveDirection.BACKWARD, 10.0);
        this.camera.pointAt(this.Isabelle);
        this.camera.turn(TurnDirection.BACKWARD, 8.0);
    }

    private void performGeneratedSetUp() {
// DO NOT EDIT
// This code is automatically generated.  Any work you perform in this method will be overwritten.
// DO NOT EDIT
        this.setAtmosphereColor(new Color(0.11, 0.133, 0.178));
        this.setFromAboveLightColor(Color.WHITE);
        this.setFromBelowLightColor(new Color(0.0, 0.118, 0.396));
        this.setFogDensity(0.0);
        this.setName("myScene");
        this.ground.setPaint(SurfaceAppearance.GRASS);
        this.ground.setOpacity(1.0);
        this.ground.setName("ground");
        this.ground.setVehicle(this);
        
        this.setAtmosphereColor(new Color(0.5, 0.5, 1.0));
        this.setFromAboveLightColor(Color.WHITE);
        this.setFromBelowLightColor(Color.BLACK);
        this.setFogDensity(0.0);
        this.setName("myScene");
        this.room.setFloorPaint(SRoom.FloorAppearance.REDWOOD);
        this.room.setWallPaint(SRoom.WallAppearance.YELLOW);
        this.room.setCeilingPaint(Color.WHITE);
        this.room.setOpacity(1.0);
        this.room.setName("room");
        this.room.setVehicle(this);
        
        this.camera.setName("camera");
        this.camera.setVehicle(this);
        this.camera.setOrientationRelativeToVehicle(new Orientation(-0.110549, 0.73074, 0.121932, 0.662519));
        this.camera.setPositionRelativeToVehicle(new Position(9.961, 4.426, -31.979));
        this.Isabelle.setPaint(Color.WHITE);
        this.Isabelle.setOpacity(1.0);
        this.Isabelle.setName("Isabelle");
        this.Isabelle.setVehicle(this);
        this.Isabelle.setOrientationRelativeToVehicle(new Orientation(0.0, 0.0, 0.0, 1.0));
        this.Isabelle.setPositionRelativeToVehicle(new Position(-0.061, 0.0, -4.06));
        this.baobabTree.setPaint(Color.WHITE);
        this.baobabTree.setOpacity(1.0);
        this.baobabTree.setName("baobabTree");
        this.baobabTree.setVehicle(this);
        this.baobabTree.setOrientationRelativeToVehicle(new Orientation(0.0, 0.996547, 0.0, 0.083031));
        this.baobabTree.setPositionRelativeToVehicle(new Position(14.811, 0.0, -18.901));
        this.humvee.setPaint(Color.WHITE);
        this.humvee.setOpacity(1.0);
        this.humvee.setName("humvee");
        this.humvee.setVehicle(this);
        this.humvee.setOrientationRelativeToVehicle(new Orientation(0.0, -0.999705, 0.0, 0.024288));
        this.humvee.setPositionRelativeToVehicle(new Position(0.0, 0.0, -35.0));
        this.alice.setPaint(Color.WHITE);
        this.alice.setOpacity(1.0);
        this.alice.setName("alice");
        this.alice.setVehicle(this);
        this.alice.setOrientationRelativeToVehicle(new Orientation(0.0, 0.999964, 0.0, 0.008485));
        this.alice.setPositionRelativeToVehicle(new Position(-5.391, 0.0, -23.761));
        this.mandril.setPaint(Color.WHITE);
        this.mandril.setOpacity(1.0);
        this.mandril.setName("mandril");
        this.mandril.setVehicle(this);
        this.mandril.setOrientationRelativeToVehicle(new Orientation(0.0, -0.057887, 0.0, 0.998323));
        this.mandril.setPositionRelativeToVehicle(new Position(0.5, 0.7, -34.5));
        this.mandril.setSize(new Size(0.595, 1.0, 0.594));

//        this.floor = new Floor();
//        this.floor.setPaint(SurfaceAppearance.WOOD);
//        this.floor.setOpacity(1.0);
//        this.floor.setName("floor");
//        this.floor.setVehicle(this);
//        addObject(this.floor, 0, 0, 0);

//        this.wallLeft = new Wall();
//        this.wallLeft.setPaint(Color.WHITE);
//        // ... (set other properties for the wall)
//        addObject(this.wallLeft, -5, 0, 0); // Place the wall to the left

        // ... (add more objects like right wall, furniture, etc.)
        // Adjust Alice's position
//        this.alice.setPositionRelativeToVehicle(new Position(0, 0, -5));



        this.setAtmosphereColor(new Color(0.886, 0.831, 0.51));
        this.setFromAboveLightColor(Color.WHITE);
        this.setFromBelowLightColor(Color.BLACK);
        this.setFogDensity(0.13);
        this.setName("myScene");
        this.ground.setPaint(SurfaceAppearance.DRY_GRASS);
        this.ground.setOpacity(1.0);
        this.ground.setName("ground");
        this.ground.setVehicle(this);
        this.camera.setName("camera");
        this.camera.setVehicle(this);
//        this.camera.setOrientationRelativeToVehicle(new Orientation(-0.072551, -0.665335, -0.065218, 0.740144));
//        this.camera.setPositionRelativeToVehicle(new Position(-151.47, 20.74, 9.095));
        this.elephantStable.setPaint(Color.WHITE);
        this.elephantStable.setOpacity(1.0);
        this.elephantStable.setName("elephantStable");
        this.elephantStable.setVehicle(this);
        this.elephantStable.setOrientationRelativeToVehicle(new Orientation(0.0, 0.309234, 0.0, 0.950986));
        this.elephantStable.setPositionRelativeToVehicle(new Position(-1.73, 0.0, 23.8));
        this.elephantStable.setSize(new Size(51.1, 14.6, 7.98));
        this.ancientTempleTile2.setPaint(Color.WHITE);
        this.ancientTempleTile2.setOpacity(1.0);
        this.ancientTempleTile2.setName("ancientTempleTile2");
        this.ancientTempleTile2.setVehicle(this);
        this.ancientTempleTile2.setOrientationRelativeToVehicle(new Orientation(0.0, -0.451756, 0.0, 0.892142));
        this.ancientTempleTile2.setPositionRelativeToVehicle(new Position(10.6, -0.2, 6.88));
        this.ancientTempleTile2.setSize(new Size(6.55, 0.674, 6.55));
        this.ancientTempleTile.setPaint(Color.WHITE);
        this.ancientTempleTile.setOpacity(1.0);
        this.ancientTempleTile.setName("ancientTempleTile");
        this.ancientTempleTile.setVehicle(this);
        this.ancientTempleTile.setOrientationRelativeToVehicle(new Orientation(0.0, -0.451756, 0.0, 0.892142));
        this.ancientTempleTile.setPositionRelativeToVehicle(new Position(5.35, -0.2, 10.7));
        this.ancientTempleTile.setSize(new Size(6.55, 0.674, 6.55));
        this.ancientTempleTile3.setPaint(Color.WHITE);
        this.ancientTempleTile3.setOpacity(1.0);
        this.ancientTempleTile3.setName("ancientTempleTile3");
        this.ancientTempleTile3.setVehicle(this);
        this.ancientTempleTile3.setOrientationRelativeToVehicle(new Orientation(0.0, -0.451756, 0.0, 0.892142));
        this.ancientTempleTile3.setPositionRelativeToVehicle(new Position(0.078, -0.2, 14.6));
        this.ancientTempleTile3.setSize(new Size(6.55, 0.674, 6.55));
        this.ancientTempleTile4.setPaint(Color.WHITE);
        this.ancientTempleTile4.setOpacity(1.0);
        this.ancientTempleTile4.setName("ancientTempleTile4");
        this.ancientTempleTile4.setVehicle(this);
        this.ancientTempleTile4.setOrientationRelativeToVehicle(new Orientation(0.0, -0.451756, 0.0, 0.892142));
        this.ancientTempleTile4.setPositionRelativeToVehicle(new Position(-5.18, -0.2, 18.5));
        this.ancientTempleTile4.setSize(new Size(6.55, 0.674, 6.55));
        this.ancientTempleTile5.setPaint(Color.WHITE);
        this.ancientTempleTile5.setOpacity(1.0);
        this.ancientTempleTile5.setName("ancientTempleTile5");
        this.ancientTempleTile5.setVehicle(this);
        this.ancientTempleTile5.setOrientationRelativeToVehicle(new Orientation(0.0, -0.451756, 0.0, 0.892142));
        this.ancientTempleTile5.setPositionRelativeToVehicle(new Position(-10.5, -0.2, 22.3));
        this.ancientTempleTile5.setSize(new Size(6.55, 0.674, 6.55));
        this.ancientTempleTile6.setPaint(Color.WHITE);
        this.ancientTempleTile6.setOpacity(1.0);
        this.ancientTempleTile6.setName("ancientTempleTile6");
        this.ancientTempleTile6.setVehicle(this);
        this.ancientTempleTile6.setOrientationRelativeToVehicle(new Orientation(0.0, -0.451756, 0.0, 0.892142));
        this.ancientTempleTile6.setPositionRelativeToVehicle(new Position(-15.7, -0.2, 26.2));
        this.ancientTempleTile6.setSize(new Size(6.55, 0.674, 6.55));
        this.ancientTempleTile7.setPaint(Color.WHITE);
        this.ancientTempleTile7.setOpacity(1.0);
        this.ancientTempleTile7.setName("ancientTempleTile7");
        this.ancientTempleTile7.setVehicle(this);
        this.ancientTempleTile7.setOrientationRelativeToVehicle(new Orientation(0.0, -0.451756, 0.0, 0.892142));
        this.ancientTempleTile7.setPositionRelativeToVehicle(new Position(-21.0, -0.2, 30.1));
        this.ancientTempleTile7.setSize(new Size(6.55, 0.674, 6.55));
        this.sandDunes2.setPaint(Color.WHITE);
        this.sandDunes2.setOpacity(1.0);
        this.sandDunes2.setName("sandDunes2");
        this.sandDunes2.setVehicle(this);
        this.sandDunes2.setOrientationRelativeToVehicle(new Orientation(0.0, 0.832791, 0.0, 0.553588));
        this.sandDunes2.setPositionRelativeToVehicle(new Position(-6.54, 0.0, 58.0));
        this.sandDunes2.setSize(new Size(49.7, 15.7, 94.1));
        this.sandDunes.setPaint(Color.WHITE);
        this.sandDunes.setOpacity(1.0);
        this.sandDunes.setName("sandDunes");
        this.sandDunes.setVehicle(this);
        this.sandDunes.setOrientationRelativeToVehicle(new Orientation(0.0, -0.06233, 0.0, 0.998056));
        this.sandDunes.setPositionRelativeToVehicle(new Position(30.7, 0.0, -5.99));
        this.sandDunes.setSize(new Size(66.4, 8.32, 66.4));
        this.sandDunes3.setPaint(Color.WHITE);
        this.sandDunes3.setOpacity(1.0);
        this.sandDunes3.setName("sandDunes3");
        this.sandDunes3.setVehicle(this);
        this.sandDunes3.setOrientationRelativeToVehicle(new Orientation(0.0, -0.06233, 0.0, 0.998056));
        this.sandDunes3.setPositionRelativeToVehicle(new Position(38.9, 0.0, -71.5));
        this.sandDunes3.setSize(new Size(66.4, 8.32, 66.4));
        this.sandDunes4.setPaint(Color.WHITE);
        this.sandDunes4.setOpacity(1.0);
        this.sandDunes4.setName("sandDunes4");
        this.sandDunes4.setVehicle(this);
        this.sandDunes4.setOrientationRelativeToVehicle(new Orientation(0.0, -0.06233, 0.0, 0.998056));
        this.sandDunes4.setPositionRelativeToVehicle(new Position(105.0, 0.0, -63.2));
        this.sandDunes4.setSize(new Size(66.4, 8.32, 66.4));
        this.sandDunes5.setPaint(Color.WHITE);
        this.sandDunes5.setOpacity(1.0);
        this.sandDunes5.setName("sandDunes5");
        this.sandDunes5.setVehicle(this);
        this.sandDunes5.setOrientationRelativeToVehicle(new Orientation(0.0, -0.06233, 0.0, 0.998056));
        this.sandDunes5.setPositionRelativeToVehicle(new Position(96.5, 0.0, 2.27));
        this.sandDunes5.setSize(new Size(66.4, 8.32, 66.4));
        this.sandDunes6.setPaint(Color.WHITE);
        this.sandDunes6.setOpacity(1.0);
        this.sandDunes6.setName("sandDunes6");
        this.sandDunes6.setVehicle(this);
        this.sandDunes6.setOrientationRelativeToVehicle(new Orientation(0.0, 0.459463, 0.0, 0.888197));
        this.sandDunes6.setPositionRelativeToVehicle(new Position(81.3, 0.0, 77.1));
        this.sandDunes6.setSize(new Size(118.0, 21.1, 148.0));
        this.sandDunes7.setPaint(Color.WHITE);
        this.sandDunes7.setOpacity(1.0);
        this.sandDunes7.setName("sandDunes7");
        this.sandDunes7.setVehicle(this);
        this.sandDunes7.setOrientationRelativeToVehicle(new Orientation(0.0, -0.981223, 0.0, 0.192877));
        this.sandDunes7.setPositionRelativeToVehicle(new Position(78.9, 0.0, -51.4));
        this.sandDunes7.setSize(new Size(172.0, 18.5, 110.0));
        this.ancientTempleTile8.setPaint(Color.WHITE);
        this.ancientTempleTile8.setOpacity(1.0);
        this.ancientTempleTile8.setName("ancientTempleTile8");
        this.ancientTempleTile8.setVehicle(this);
        this.ancientTempleTile8.setOrientationRelativeToVehicle(new Orientation(0.0, -0.451756, 0.0, 0.892142));
        this.ancientTempleTile8.setPositionRelativeToVehicle(new Position(15.9, -0.2, 2.94));
        this.ancientTempleTile8.setSize(new Size(6.55, 0.674, 6.55));
        this.waterTank.setPaint(Color.WHITE);
        this.waterTank.setOpacity(1.0);
        this.waterTank.setName("waterTank");
        this.waterTank.setVehicle(this);
        this.waterTank.setOrientationRelativeToVehicle(new Orientation(0.0, 0.319987, 0.0, 0.947422));
        this.waterTank.setPositionRelativeToVehicle(new Position(-47.3, 0.0, 30.8));
        this.waterTank.setSize(new Size(36.4, 16.5, 35.8));
        this.ancientTempleWall.setPaint(Color.WHITE);
        this.ancientTempleWall.setOpacity(1.0);
        this.ancientTempleWall.setName("ancientTempleWall");
        this.ancientTempleWall.setVehicle(this);
        this.ancientTempleWall.setOrientationRelativeToVehicle(new Orientation(0.0, 0.31819, 0.0, 0.948027));
        this.ancientTempleWall.setPositionRelativeToVehicle(new Position(22.2, 0.0, 10.8));
        this.ancientTempleWall.setSize(new Size(20.7, 9.26, 2.79));
        this.ancientTempleWall2.setPaint(Color.WHITE);
        this.ancientTempleWall2.setOpacity(1.0);
        this.ancientTempleWall2.setName("ancientTempleWall2");
        this.ancientTempleWall2.setVehicle(this);
        this.ancientTempleWall2.setOrientationRelativeToVehicle(new Orientation(0.0, 0.313607, 0.0, 0.949553));
        this.ancientTempleWall2.setPositionRelativeToVehicle(new Position(10.2, 0.0, 20.0));
        this.ancientTempleWall2.setSize(new Size(20.7, 9.26, 2.79));
        this.ancientTempleWall3.setPaint(Color.WHITE);
        this.ancientTempleWall3.setOpacity(1.0);
        this.ancientTempleWall3.setName("ancientTempleWall3");
        this.ancientTempleWall3.setVehicle(this);
        this.ancientTempleWall3.setOrientationRelativeToVehicle(new Orientation(0.0, 0.313607, 0.0, 0.949553));
        this.ancientTempleWall3.setPositionRelativeToVehicle(new Position(-5.44, 0.0, 31.4));
        this.ancientTempleWall3.setSize(new Size(20.7, 9.26, 2.79));
        this.ancientTemplePillar.setPaint(Color.WHITE);
        this.ancientTemplePillar.setOpacity(1.0);
        this.ancientTemplePillar.setName("ancientTemplePillar");
        this.ancientTemplePillar.setVehicle(this);
        this.ancientTemplePillar.setOrientationRelativeToVehicle(new Orientation(0.0, -0.948152, 0.0, 0.317817));
        this.ancientTemplePillar.setPositionRelativeToVehicle(new Position(-20.6, 0.0, 42.6));
        this.ancientTemplePillar.setSize(new Size(2.79, 9.26, 2.79));
        this.ancientTempleWall4.setPaint(Color.WHITE);
        this.ancientTempleWall4.setOpacity(1.0);
        this.ancientTempleWall4.setName("ancientTempleWall4");
        this.ancientTempleWall4.setVehicle(this);
        this.ancientTempleWall4.setOrientationRelativeToVehicle(new Orientation(0.0, 0.311804, 0.0, 0.950146));
        this.ancientTempleWall4.setPositionRelativeToVehicle(new Position(14.1, -0.515, 0.106));
        this.ancientTempleWall4.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall5.setPaint(Color.WHITE);
        this.ancientTempleWall5.setOpacity(1.0);
        this.ancientTempleWall5.setName("ancientTempleWall5");
        this.ancientTempleWall5.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall5.setOrientationRelativeToVehicle(new Orientation(0.0, 0.0, 0.0, 1.0));
        this.ancientTempleWall5.setPositionRelativeToVehicle(new Position(-5.99, 0.0, -0.059));
        this.ancientTempleWall5.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall6.setPaint(Color.WHITE);
        this.ancientTempleWall6.setOpacity(1.0);
        this.ancientTempleWall6.setName("ancientTempleWall6");
        this.ancientTempleWall6.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall6.setOrientationRelativeToVehicle(new Orientation(0.0, 0.0, 0.0, 1.0));
        this.ancientTempleWall6.setPositionRelativeToVehicle(new Position(-12.0, 0.0, -0.107));
        this.ancientTempleWall6.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall7.setPaint(Color.WHITE);
        this.ancientTempleWall7.setOpacity(1.0);
        this.ancientTempleWall7.setName("ancientTempleWall7");
        this.ancientTempleWall7.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall7.setOrientationRelativeToVehicle(new Orientation(0.0, 0.0, 0.0, 1.0));
        this.ancientTempleWall7.setPositionRelativeToVehicle(new Position(-18.0, 0.0, -0.105));
        this.ancientTempleWall7.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall8.setPaint(Color.WHITE);
        this.ancientTempleWall8.setOpacity(1.0);
        this.ancientTempleWall8.setName("ancientTempleWall8");
        this.ancientTempleWall8.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall8.setOrientationRelativeToVehicle(new Orientation(0.0, -0.698213, 0.0, 0.71589));
        this.ancientTempleWall8.setPositionRelativeToVehicle(new Position(-24.0, 0.0, -0.106));
        this.ancientTempleWall8.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall9.setPaint(Color.WHITE);
        this.ancientTempleWall9.setOpacity(1.0);
        this.ancientTempleWall9.setName("ancientTempleWall9");
        this.ancientTempleWall9.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall9.setOrientationRelativeToVehicle(new Orientation(0.0, -0.698213, 0.0, 0.71589));
        this.ancientTempleWall9.setPositionRelativeToVehicle(new Position(-24.2, 0.0, -6.15));
        this.ancientTempleWall9.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall10.setPaint(Color.WHITE);
        this.ancientTempleWall10.setOpacity(1.0);
        this.ancientTempleWall10.setName("ancientTempleWall10");
        this.ancientTempleWall10.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall10.setOrientationRelativeToVehicle(new Orientation(0.0, -0.698213, 0.0, 0.71589));
        this.ancientTempleWall10.setPositionRelativeToVehicle(new Position(-24.4, 0.0, -12.1));
        this.ancientTempleWall10.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall11.setPaint(Color.WHITE);
        this.ancientTempleWall11.setOpacity(1.0);
        this.ancientTempleWall11.setName("ancientTempleWall11");
        this.ancientTempleWall11.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall11.setOrientationRelativeToVehicle(new Orientation(0.0, -0.698213, 0.0, 0.71589));
        this.ancientTempleWall11.setPositionRelativeToVehicle(new Position(-24.5, 0.0, -18.1));
        this.ancientTempleWall11.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall12.setPaint(Color.WHITE);
        this.ancientTempleWall12.setOpacity(1.0);
        this.ancientTempleWall12.setName("ancientTempleWall12");
        this.ancientTempleWall12.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall12.setOrientationRelativeToVehicle(new Orientation(0.0, -0.999922, 0.0, 0.01249));
        this.ancientTempleWall12.setPositionRelativeToVehicle(new Position(-24.6, 0.0, -24.2));
        this.ancientTempleWall12.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall13.setPaint(Color.WHITE);
        this.ancientTempleWall13.setOpacity(1.0);
        this.ancientTempleWall13.setName("ancientTempleWall13");
        this.ancientTempleWall13.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall13.setOrientationRelativeToVehicle(new Orientation(0.0, -0.999922, 0.0, 0.01249));
        this.ancientTempleWall13.setPositionRelativeToVehicle(new Position(-18.6, 0.0, -24.4));
        this.ancientTempleWall13.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall14.setPaint(Color.WHITE);
        this.ancientTempleWall14.setOpacity(1.0);
        this.ancientTempleWall14.setName("ancientTempleWall14");
        this.ancientTempleWall14.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall14.setOrientationRelativeToVehicle(new Orientation(0.0, -0.999922, 0.0, 0.01249));
        this.ancientTempleWall14.setPositionRelativeToVehicle(new Position(-12.6, 0.0, -24.5));
        this.ancientTempleWall14.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall15.setPaint(Color.WHITE);
        this.ancientTempleWall15.setOpacity(1.0);
        this.ancientTempleWall15.setName("ancientTempleWall15");
        this.ancientTempleWall15.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall15.setOrientationRelativeToVehicle(new Orientation(0.0, -0.999922, 0.0, 0.01249));
        this.ancientTempleWall15.setPositionRelativeToVehicle(new Position(-12.2, 0.0, -12.3));
        this.ancientTempleWall15.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall16.setPaint(Color.WHITE);
        this.ancientTempleWall16.setOpacity(1.0);
        this.ancientTempleWall16.setName("ancientTempleWall16");
        this.ancientTempleWall16.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall16.setOrientationRelativeToVehicle(new Orientation(0.0, 0.715889, 0.0, 0.698214));
        this.ancientTempleWall16.setPositionRelativeToVehicle(new Position(-0.411, 0.0, -24.8));
        this.ancientTempleWall16.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall17.setPaint(Color.WHITE);
        this.ancientTempleWall17.setOpacity(1.0);
        this.ancientTempleWall17.setName("ancientTempleWall17");
        this.ancientTempleWall17.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall17.setOrientationRelativeToVehicle(new Orientation(0.0, 0.712602, 0.0, 0.701569));
        this.ancientTempleWall17.setPositionRelativeToVehicle(new Position(-0.292, 0.0, -18.6));
        this.ancientTempleWall17.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall18.setPaint(Color.WHITE);
        this.ancientTempleWall18.setOpacity(1.0);
        this.ancientTempleWall18.setName("ancientTempleWall18");
        this.ancientTempleWall18.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall18.setOrientationRelativeToVehicle(new Orientation(0.0, 0.710002, 0.0, 0.7042));
        this.ancientTempleWall18.setPositionRelativeToVehicle(new Position(-0.13, 0.0, -12.3));
        this.ancientTempleWall18.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall19.setPaint(Color.WHITE);
        this.ancientTempleWall19.setOpacity(1.0);
        this.ancientTempleWall19.setName("ancientTempleWall19");
        this.ancientTempleWall19.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall19.setOrientationRelativeToVehicle(new Orientation(0.0, 0.71266, 0.0, 0.70151));
        this.ancientTempleWall19.setPositionRelativeToVehicle(new Position(-0.117, 0.0, -6.09));
        this.ancientTempleWall19.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall20.setPaint(Color.WHITE);
        this.ancientTempleWall20.setOpacity(1.0);
        this.ancientTempleWall20.setName("ancientTempleWall20");
        this.ancientTempleWall20.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall20.setOrientationRelativeToVehicle(new Orientation(0.0, -0.698213, 0.0, 0.71589));
        this.ancientTempleWall20.setPositionRelativeToVehicle(new Position(-18.4, 0.0, -18.2));
        this.ancientTempleWall20.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall21.setPaint(Color.WHITE);
        this.ancientTempleWall21.setOpacity(1.0);
        this.ancientTempleWall21.setName("ancientTempleWall21");
        this.ancientTempleWall21.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall21.setOrientationRelativeToVehicle(new Orientation(0.0, 0.018468, 0.0, 0.999829));
        this.ancientTempleWall21.setPositionRelativeToVehicle(new Position(-12.3, 0.0, -18.5));
        this.ancientTempleWall21.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall22.setPaint(Color.WHITE);
        this.ancientTempleWall22.setOpacity(1.0);
        this.ancientTempleWall22.setName("ancientTempleWall22");
        this.ancientTempleWall22.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall22.setOrientationRelativeToVehicle(new Orientation(0.0, 0.010627, 0.0, 0.999944));
        this.ancientTempleWall22.setPositionRelativeToVehicle(new Position(-6.34, 0.0, -18.6));
        this.ancientTempleWall22.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall23.setPaint(Color.WHITE);
        this.ancientTempleWall23.setOpacity(1.0);
        this.ancientTempleWall23.setName("ancientTempleWall23");
        this.ancientTempleWall23.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall23.setOrientationRelativeToVehicle(new Orientation(0.0, -0.698213, 0.0, 0.71589));
        this.ancientTempleWall23.setPositionRelativeToVehicle(new Position(-6.11, 0.0, -12.4));
        this.ancientTempleWall23.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall24.setPaint(Color.WHITE);
        this.ancientTempleWall24.setOpacity(1.0);
        this.ancientTempleWall24.setName("ancientTempleWall24");
        this.ancientTempleWall24.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall24.setOrientationRelativeToVehicle(new Orientation(0.0, -0.999997, 0.0, 0.002449));
        this.ancientTempleWall24.setPositionRelativeToVehicle(new Position(-18.1, 0.0, -12.3));
        this.ancientTempleWall24.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall25.setPaint(Color.WHITE);
        this.ancientTempleWall25.setOpacity(1.0);
        this.ancientTempleWall25.setName("ancientTempleWall25");
        this.ancientTempleWall25.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall25.setOrientationRelativeToVehicle(new Orientation(0.0, -0.698213, 0.0, 0.71589));
        this.ancientTempleWall25.setPositionRelativeToVehicle(new Position(-18.0, 0.0, -6.28));
        this.ancientTempleWall25.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall26.setPaint(Color.WHITE);
        this.ancientTempleWall26.setOpacity(1.0);
        this.ancientTempleWall26.setName("ancientTempleWall26");
        this.ancientTempleWall26.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall26.setOrientationRelativeToVehicle(new Orientation(0.0, 0.712601, 0.0, 0.70157));
        this.ancientTempleWall26.setPositionRelativeToVehicle(new Position(-12.1, 0.0, -6.29));
        this.ancientTempleWall26.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall27.setPaint(Color.WHITE);
        this.ancientTempleWall27.setOpacity(1.0);
        this.ancientTempleWall27.setName("ancientTempleWall27");
        this.ancientTempleWall27.setVehicle(this.ancientTempleWall4);
        this.ancientTempleWall27.setOrientationRelativeToVehicle(new Orientation(0.0, -0.698213, 0.0, 0.71589));
        this.ancientTempleWall27.setPositionRelativeToVehicle(new Position(-5.93, 0.0, -6.28));
        this.ancientTempleWall27.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTemplePillar2.setPaint(Color.WHITE);
        this.ancientTemplePillar2.setOpacity(1.0);
        this.ancientTemplePillar2.setName("ancientTemplePillar2");
        this.ancientTemplePillar2.setVehicle(this.ancientTempleWall4);
        this.ancientTemplePillar2.setOrientationRelativeToVehicle(new Orientation(0.0, -0.704299, 0.0, 0.709903));
        this.ancientTemplePillar2.setPositionRelativeToVehicle(new Position(-6.32, 0.0, -24.7));
        this.ancientTemplePillar2.setSize(new Size(0.866, 2.88, 0.866));
        this.ancientTempleTile9.setPaint(Color.WHITE);
        this.ancientTempleTile9.setOpacity(1.0);
        this.ancientTempleTile9.setName("ancientTempleTile9");
        this.ancientTempleTile9.setVehicle(this);
        this.ancientTempleTile9.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile9.setPositionRelativeToVehicle(new Position(-15.8, -0.2, -4.15));
        this.ancientTempleTile9.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile10.setPaint(Color.WHITE);
        this.ancientTempleTile10.setOpacity(1.0);
        this.ancientTempleTile10.setName("ancientTempleTile10");
        this.ancientTempleTile10.setVehicle(this);
        this.ancientTempleTile10.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile10.setPositionRelativeToVehicle(new Position(-12.1, -0.2, 0.51));
        this.ancientTempleTile10.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile11.setPaint(Color.WHITE);
        this.ancientTempleTile11.setOpacity(1.0);
        this.ancientTempleTile11.setName("ancientTempleTile11");
        this.ancientTempleTile11.setVehicle(this);
        this.ancientTempleTile11.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile11.setPositionRelativeToVehicle(new Position(-7.29, -0.2, -3.29));
        this.ancientTempleTile11.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile12.setPaint(Color.WHITE);
        this.ancientTempleTile12.setOpacity(1.0);
        this.ancientTempleTile12.setName("ancientTempleTile12");
        this.ancientTempleTile12.setVehicle(this);
        this.ancientTempleTile12.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile12.setPositionRelativeToVehicle(new Position(-2.49, -0.2, -7.0));
        this.ancientTempleTile12.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile13.setPaint(Color.WHITE);
        this.ancientTempleTile13.setOpacity(1.0);
        this.ancientTempleTile13.setName("ancientTempleTile13");
        this.ancientTempleTile13.setVehicle(this);
        this.ancientTempleTile13.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile13.setPositionRelativeToVehicle(new Position(-11.0, -0.2, -7.95));
        this.ancientTempleTile13.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile14.setPaint(Color.WHITE);
        this.ancientTempleTile14.setOpacity(1.0);
        this.ancientTempleTile14.setName("ancientTempleTile14");
        this.ancientTempleTile14.setVehicle(this);
        this.ancientTempleTile14.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile14.setPositionRelativeToVehicle(new Position(-6.16, -0.2, -11.7));
        this.ancientTempleTile14.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile15.setPaint(Color.WHITE);
        this.ancientTempleTile15.setOpacity(1.0);
        this.ancientTempleTile15.setName("ancientTempleTile15");
        this.ancientTempleTile15.setVehicle(this);
        this.ancientTempleTile15.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile15.setPositionRelativeToVehicle(new Position(-1.4, -0.2, -15.1));
        this.ancientTempleTile15.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile16.setPaint(Color.WHITE);
        this.ancientTempleTile16.setOpacity(1.0);
        this.ancientTempleTile16.setName("ancientTempleTile16");
        this.ancientTempleTile16.setVehicle(this);
        this.ancientTempleTile16.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile16.setPositionRelativeToVehicle(new Position(2.33, -0.2, -10.2));
        this.ancientTempleTile16.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile17.setPaint(Color.WHITE);
        this.ancientTempleTile17.setOpacity(1.0);
        this.ancientTempleTile17.setName("ancientTempleTile17");
        this.ancientTempleTile17.setVehicle(this);
        this.ancientTempleTile17.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile17.setPositionRelativeToVehicle(new Position(6.08, -0.2, -5.56));
        this.ancientTempleTile17.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile18.setPaint(Color.WHITE);
        this.ancientTempleTile18.setOpacity(1.0);
        this.ancientTempleTile18.setName("ancientTempleTile18");
        this.ancientTempleTile18.setVehicle(this);
        this.ancientTempleTile18.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile18.setPositionRelativeToVehicle(new Position(9.58, -0.2, -0.546));
        this.ancientTempleTile18.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile19.setPaint(Color.WHITE);
        this.ancientTempleTile19.setOpacity(1.0);
        this.ancientTempleTile19.setName("ancientTempleTile19");
        this.ancientTempleTile19.setVehicle(this);
        this.ancientTempleTile19.setOrientationRelativeToVehicle(new Orientation(0.0, -0.451756, 0.0, 0.892142));
        this.ancientTempleTile19.setPositionRelativeToVehicle(new Position(-4.7, -0.2, 9.76));
        this.ancientTempleTile19.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile20.setPaint(Color.WHITE);
        this.ancientTempleTile20.setOpacity(1.0);
        this.ancientTempleTile20.setName("ancientTempleTile20");
        this.ancientTempleTile20.setVehicle(this);
        this.ancientTempleTile20.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile20.setPositionRelativeToVehicle(new Position(-8.41, -0.2, 5.07));
        this.ancientTempleTile20.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile21.setPaint(Color.WHITE);
        this.ancientTempleTile21.setOpacity(1.0);
        this.ancientTempleTile21.setName("ancientTempleTile21");
        this.ancientTempleTile21.setVehicle(this);
        this.ancientTempleTile21.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile21.setPositionRelativeToVehicle(new Position(-3.49, -0.2, 1.65));
        this.ancientTempleTile21.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile22.setPaint(Color.WHITE);
        this.ancientTempleTile22.setOpacity(1.0);
        this.ancientTempleTile22.setName("ancientTempleTile22");
        this.ancientTempleTile22.setVehicle(this);
        this.ancientTempleTile22.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile22.setPositionRelativeToVehicle(new Position(0.191, -0.2, 6.22));
        this.ancientTempleTile22.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile23.setPaint(Color.WHITE);
        this.ancientTempleTile23.setOpacity(1.0);
        this.ancientTempleTile23.setName("ancientTempleTile23");
        this.ancientTempleTile23.setVehicle(this);
        this.ancientTempleTile23.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile23.setPositionRelativeToVehicle(new Position(4.78, -0.2, 3.03));
        this.ancientTempleTile23.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile24.setPaint(Color.WHITE);
        this.ancientTempleTile24.setOpacity(1.0);
        this.ancientTempleTile24.setName("ancientTempleTile24");
        this.ancientTempleTile24.setVehicle(this);
        this.ancientTempleTile24.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile24.setPositionRelativeToVehicle(new Position(1.38, -0.2, -1.95));
        this.ancientTempleTile24.setSize(new Size(6.1, 0.627, 6.1));
        this.figTree.setPaint(new Color(1.0, 0.859, 0.341));
        this.figTree.setOpacity(1.0);
        this.figTree.setName("figTree");
        this.figTree.setVehicle(this);
        this.figTree.setOrientationRelativeToVehicle(new Orientation(0.0, 0.091774, 0.0, 0.99578));
        this.figTree.setPositionRelativeToVehicle(new Position(-22.1, -1.05, 24.2));
        this.figTree.setSize(new Size(4.83, 7.22, 4.65));
        this.figTree2.setPaint(new Color(1.0, 0.859, 0.341));
        this.figTree2.setOpacity(1.0);
        this.figTree2.setName("figTree2");
        this.figTree2.setVehicle(this);
        this.figTree2.setOrientationRelativeToVehicle(new Orientation(0.0, -0.500944, 0.0, 0.86548));
        this.figTree2.setPositionRelativeToVehicle(new Position(-10.3, -1.05, 15.2));
        this.figTree2.setSize(new Size(4.83, 7.22, 4.65));
        this.figTree3.setPaint(new Color(1.0, 0.859, 0.341));
        this.figTree3.setOpacity(1.0);
        this.figTree3.setName("figTree3");
        this.figTree3.setVehicle(this);
        this.figTree3.setOrientationRelativeToVehicle(new Orientation(0.0, -0.968863, 0.0, 0.247597));
        this.figTree3.setPositionRelativeToVehicle(new Position(-16.1, -1.05, 19.9));
        this.figTree3.setSize(new Size(4.83, 7.22, 4.65));
        this.waterTankTower.setPaint(Color.WHITE);
        this.waterTankTower.setOpacity(1.0);
        this.waterTankTower.setName("waterTankTower");
        this.waterTankTower.setVehicle(this);
        this.waterTankTower.setOrientationRelativeToVehicle(new Orientation(0.0, 0.320403, 0.0, 0.947281));
        this.waterTankTower.setPositionRelativeToVehicle(new Position(-45.8, 0.0, 51.1));
        this.waterTankTower.setSize(new Size(10.0, 23.9, 9.99));
        this.waterTankShrine.setPaint(Color.WHITE);
        this.waterTankShrine.setOpacity(1.0);
        this.waterTankShrine.setName("waterTankShrine");
        this.waterTankShrine.setVehicle(this);
        this.waterTankShrine.setOrientationRelativeToVehicle(new Orientation(0.0, 0.320403, 0.0, 0.947281));
        this.waterTankShrine.setPositionRelativeToVehicle(new Position(-46.3, 16.7, 51.5));
        this.waterTankShrine.setSize(new Size(4.58, 9.44, 4.31));
        this.waterTankPillar.setPaint(Color.WHITE);
        this.waterTankPillar.setOpacity(1.0);
        this.waterTankPillar.setName("waterTankPillar");
        this.waterTankPillar.setVehicle(this);
        this.waterTankPillar.setOrientationRelativeToVehicle(new Orientation(0.0, -0.086238, 0.0, 0.996275));
        this.waterTankPillar.setPositionRelativeToVehicle(new Position(-33.5, 0.0, 45.0));
        this.waterTankPillar.setSize(new Size(7.38, 14.2, 7.15));
        this.ancientTempleBlock.setPaint(Color.WHITE);
        this.ancientTempleBlock.setOpacity(1.0);
        this.ancientTempleBlock.setName("ancientTempleBlock");
        this.ancientTempleBlock.setVehicle(this);
        this.ancientTempleBlock.setOrientationRelativeToVehicle(new Orientation(0.0, 0.308537, 0.0, 0.951212));
        this.ancientTempleBlock.setPositionRelativeToVehicle(new Position(17.6, 0.0, -3.7));
        this.ancientTempleBlock.setSize(new Size(5.64, 4.24, 5.64));
        this.ancientTempleArch.setPaint(Color.WHITE);
        this.ancientTempleArch.setOpacity(1.0);
        this.ancientTempleArch.setName("ancientTempleArch");
        this.ancientTempleArch.setVehicle(this);
        this.ancientTempleArch.setOrientationRelativeToVehicle(new Orientation(0.0, 0.900857, 0.0, 0.434116));
        this.ancientTempleArch.setPositionRelativeToVehicle(new Position(9.7, 0.0, -14.1));
        this.ancientTempleArch.setSize(new Size(9.33, 4.21, 1.26));
        this.cameraMarker1.setName("cameraMarker1");
        this.cameraMarker1.setVehicle(this);
        this.cameraMarker1.setOrientationRelativeToVehicle(new Orientation(-0.051884, -0.717854, -0.074202, 0.690281));
        this.cameraMarker1.setPositionRelativeToVehicle(new Position(-88.1, 15.3, 21.2));
        this.cameraMarker1.setColorId(Color.RED);
        this.ancientTempleTreeStump.setPaint(new Color(0.949, 0.729, 0.631));
        this.ancientTempleTreeStump.setOpacity(1.0);
        this.ancientTempleTreeStump.setName("ancientTempleTreeStump");
        this.ancientTempleTreeStump.setVehicle(this);
        this.ancientTempleTreeStump.setOrientationRelativeToVehicle(new Orientation(0.0, 0.287959, 0.0, 0.957643));
        this.ancientTempleTreeStump.setPositionRelativeToVehicle(new Position(-40.3, 2.63, 14.7));
        this.ancientTempleTreeStump.setSize(new Size(6.67, 6.21, 8.51));
        this.waterTankWall.setPaint(Color.WHITE);
        this.waterTankWall.setOpacity(1.0);
        this.waterTankWall.setName("waterTankWall");
        this.waterTankWall.setVehicle(this);
        this.waterTankWall.setOrientationRelativeToVehicle(new Orientation(0.0, 0.299907, 0.0, 0.953968));
        this.waterTankWall.setPositionRelativeToVehicle(new Position(-44.3, 2.51, 15.4));
        this.waterTankWall.setSize(new Size(1.93, 4.4, 4.89));
        this.ancientTempleBlock2.setPaint(Color.WHITE);
        this.ancientTempleBlock2.setOpacity(1.0);
        this.ancientTempleBlock2.setName("ancientTempleBlock2");
        this.ancientTempleBlock2.setVehicle(this);
        this.ancientTempleBlock2.setOrientationRelativeToVehicle(new Orientation(0.0, 0.886474, 0.0, 0.462778));
        this.ancientTempleBlock2.setPositionRelativeToVehicle(new Position(19.3, 0.0, -4.4));
        this.ancientTempleBlock2.setSize(new Size(9.72, 7.3, 9.72));
        this.ancientTempleBlock3.setPaint(Color.WHITE);
        this.ancientTempleBlock3.setOpacity(1.0);
        this.ancientTempleBlock3.setName("ancientTempleBlock3");
        this.ancientTempleBlock3.setVehicle(this);
        this.ancientTempleBlock3.setOrientationRelativeToVehicle(new Orientation(0.0, 0.886477, 0.0, 0.462773));
        this.ancientTempleBlock3.setPositionRelativeToVehicle(new Position(18.3, 13.1, -1.54));
        this.ancientTempleBlock3.setSize(new Size(4.14, 3.91, 4.14));
        this.ancientTempleBlock4.setPaint(Color.WHITE);
        this.ancientTempleBlock4.setOpacity(1.0);
        this.ancientTempleBlock4.setName("ancientTempleBlock4");
        this.ancientTempleBlock4.setVehicle(this);
        this.ancientTempleBlock4.setOrientationRelativeToVehicle(new Orientation(0.0, 0.886474, 0.0, 0.462778));
        this.ancientTempleBlock4.setPositionRelativeToVehicle(new Position(17.3, 6.65, 4.8));
        this.ancientTempleBlock4.setSize(new Size(7.52, 7.11, 7.52));
        this.ancientTempleWall28.setPaint(Color.WHITE);
        this.ancientTempleWall28.setOpacity(1.0);
        this.ancientTempleWall28.setName("ancientTempleWall28");
        this.ancientTempleWall28.setVehicle(this);
        this.ancientTempleWall28.setOrientationRelativeToVehicle(new Orientation(0.0, -0.94566, 0.0, 0.325157));
        this.ancientTempleWall28.setPositionRelativeToVehicle(new Position(-43.8, 0.0, 6.18));
        this.ancientTempleWall28.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall29.setPaint(Color.WHITE);
        this.ancientTempleWall29.setOpacity(1.0);
        this.ancientTempleWall29.setName("ancientTempleWall29");
        this.ancientTempleWall29.setVehicle(this);
        this.ancientTempleWall29.setOrientationRelativeToVehicle(new Orientation(0.0, -0.94566, 0.0, 0.325157));
        this.ancientTempleWall29.setPositionRelativeToVehicle(new Position(-38.8, 0.0, 2.34));
        this.ancientTempleWall29.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall30.setPaint(Color.WHITE);
        this.ancientTempleWall30.setOpacity(1.0);
        this.ancientTempleWall30.setName("ancientTempleWall30");
        this.ancientTempleWall30.setVehicle(this);
        this.ancientTempleWall30.setOrientationRelativeToVehicle(new Orientation(0.0, -0.94566, 0.0, 0.325157));
        this.ancientTempleWall30.setPositionRelativeToVehicle(new Position(-33.9, 0.0, -1.52));
        this.ancientTempleWall30.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall31.setPaint(Color.WHITE);
        this.ancientTempleWall31.setOpacity(1.0);
        this.ancientTempleWall31.setName("ancientTempleWall31");
        this.ancientTempleWall31.setVehicle(this);
        this.ancientTempleWall31.setOrientationRelativeToVehicle(new Orientation(0.0, -0.94566, 0.0, 0.325157));
        this.ancientTempleWall31.setPositionRelativeToVehicle(new Position(-28.9, 0.0, -5.43));
        this.ancientTempleWall31.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall32.setPaint(Color.WHITE);
        this.ancientTempleWall32.setOpacity(1.0);
        this.ancientTempleWall32.setName("ancientTempleWall32");
        this.ancientTempleWall32.setVehicle(this);
        this.ancientTempleWall32.setOrientationRelativeToVehicle(new Orientation(0.0, -0.94566, 0.0, 0.325157));
        this.ancientTempleWall32.setPositionRelativeToVehicle(new Position(-23.9, 0.0, -9.34));
        this.ancientTempleWall32.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall33.setPaint(Color.WHITE);
        this.ancientTempleWall33.setOpacity(1.0);
        this.ancientTempleWall33.setName("ancientTempleWall33");
        this.ancientTempleWall33.setVehicle(this);
        this.ancientTempleWall33.setOrientationRelativeToVehicle(new Orientation(0.0, -0.94566, 0.0, 0.325157));
        this.ancientTempleWall33.setPositionRelativeToVehicle(new Position(-19.0, 0.0, -13.2));
        this.ancientTempleWall33.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall34.setPaint(Color.WHITE);
        this.ancientTempleWall34.setOpacity(1.0);
        this.ancientTempleWall34.setName("ancientTempleWall34");
        this.ancientTempleWall34.setVehicle(this);
        this.ancientTempleWall34.setOrientationRelativeToVehicle(new Orientation(0.0, -0.94566, 0.0, 0.325157));
        this.ancientTempleWall34.setPositionRelativeToVehicle(new Position(-14.0, 0.0, -17.0));
        this.ancientTempleWall34.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleWall35.setPaint(Color.WHITE);
        this.ancientTempleWall35.setOpacity(1.0);
        this.ancientTempleWall35.setName("ancientTempleWall35");
        this.ancientTempleWall35.setVehicle(this);
        this.ancientTempleWall35.setOrientationRelativeToVehicle(new Orientation(0.0, -0.94566, 0.0, 0.325157));
        this.ancientTempleWall35.setPositionRelativeToVehicle(new Position(-9.04, 0.0, -20.7));
        this.ancientTempleWall35.setSize(new Size(6.43, 2.88, 0.866));
        this.ancientTempleBlock5.setPaint(Color.WHITE);
        this.ancientTempleBlock5.setOpacity(1.0);
        this.ancientTempleBlock5.setName("ancientTempleBlock5");
        this.ancientTempleBlock5.setVehicle(this);
        this.ancientTempleBlock5.setOrientationRelativeToVehicle(new Orientation(0.0, 0.324302, 0.0, 0.945954));
        this.ancientTempleBlock5.setPositionRelativeToVehicle(new Position(2.66, 0.0, -23.7));
        this.ancientTempleBlock5.setSize(new Size(6.22, 4.69, 6.22));
        this.ancientTempleArch2.setPaint(Color.WHITE);
        this.ancientTempleArch2.setOpacity(1.0);
        this.ancientTempleArch2.setName("ancientTempleArch2");
        this.ancientTempleArch2.setVehicle(this);
        this.ancientTempleArch2.setOrientationRelativeToVehicle(new Orientation(0.0, 0.900857, 0.0, 0.434116));
        this.ancientTempleArch2.setPositionRelativeToVehicle(new Position(4.38, 0.0, -20.8));
        this.ancientTempleArch2.setSize(new Size(9.33, 4.21, 1.26));
        this.ancientTempleBlock6.setPaint(Color.WHITE);
        this.ancientTempleBlock6.setOpacity(1.0);
        this.ancientTempleBlock6.setName("ancientTempleBlock6");
        this.ancientTempleBlock6.setVehicle(this);
        this.ancientTempleBlock6.setOrientationRelativeToVehicle(new Orientation(0.0, 0.336905, 0.0, 0.941539));
        this.ancientTempleBlock6.setPositionRelativeToVehicle(new Position(-1.16, 0.0, -19.6));
        this.ancientTempleBlock6.setSize(new Size(6.4, 4.8, 6.4));
        this.ancientTempleTile25.setPaint(Color.WHITE);
        this.ancientTempleTile25.setOpacity(1.0);
        this.ancientTempleTile25.setName("ancientTempleTile25");
        this.ancientTempleTile25.setVehicle(this);
        this.ancientTempleTile25.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile25.setPositionRelativeToVehicle(new Position(-4.96, -0.2, -19.9));
        this.ancientTempleTile25.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile26.setPaint(Color.WHITE);
        this.ancientTempleTile26.setOpacity(1.0);
        this.ancientTempleTile26.setName("ancientTempleTile26");
        this.ancientTempleTile26.setVehicle(this);
        this.ancientTempleTile26.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile26.setPositionRelativeToVehicle(new Position(-9.93, -0.2, -16.5));
        this.ancientTempleTile26.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile27.setPaint(Color.WHITE);
        this.ancientTempleTile27.setOpacity(1.0);
        this.ancientTempleTile27.setName("ancientTempleTile27");
        this.ancientTempleTile27.setVehicle(this);
        this.ancientTempleTile27.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile27.setPositionRelativeToVehicle(new Position(-14.8, -0.2, -12.7));
        this.ancientTempleTile27.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile28.setPaint(Color.WHITE);
        this.ancientTempleTile28.setOpacity(1.0);
        this.ancientTempleTile28.setName("ancientTempleTile28");
        this.ancientTempleTile28.setVehicle(this);
        this.ancientTempleTile28.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile28.setPositionRelativeToVehicle(new Position(-19.5, -0.2, -8.87));
        this.ancientTempleTile28.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile29.setPaint(Color.WHITE);
        this.ancientTempleTile29.setOpacity(1.0);
        this.ancientTempleTile29.setName("ancientTempleTile29");
        this.ancientTempleTile29.setVehicle(this);
        this.ancientTempleTile29.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile29.setPositionRelativeToVehicle(new Position(-24.2, -0.2, -5.07));
        this.ancientTempleTile29.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile30.setPaint(Color.WHITE);
        this.ancientTempleTile30.setOpacity(1.0);
        this.ancientTempleTile30.setName("ancientTempleTile30");
        this.ancientTempleTile30.setVehicle(this);
        this.ancientTempleTile30.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile30.setPositionRelativeToVehicle(new Position(-29.0, -0.2, -1.38));
        this.ancientTempleTile30.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile31.setPaint(Color.WHITE);
        this.ancientTempleTile31.setOpacity(1.0);
        this.ancientTempleTile31.setName("ancientTempleTile31");
        this.ancientTempleTile31.setVehicle(this);
        this.ancientTempleTile31.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile31.setPositionRelativeToVehicle(new Position(-33.8, -0.2, 2.36));
        this.ancientTempleTile31.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile32.setPaint(Color.WHITE);
        this.ancientTempleTile32.setOpacity(1.0);
        this.ancientTempleTile32.setName("ancientTempleTile32");
        this.ancientTempleTile32.setVehicle(this);
        this.ancientTempleTile32.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile32.setPositionRelativeToVehicle(new Position(-38.6, -0.2, 6.03));
        this.ancientTempleTile32.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTempleTile33.setPaint(Color.WHITE);
        this.ancientTempleTile33.setOpacity(1.0);
        this.ancientTempleTile33.setName("ancientTempleTile33");
        this.ancientTempleTile33.setVehicle(this);
        this.ancientTempleTile33.setOrientationRelativeToVehicle(new Orientation(0.0, -0.440569, 0.0, 0.897719));
        this.ancientTempleTile33.setPositionRelativeToVehicle(new Position(-43.5, -0.2, 9.75));
        this.ancientTempleTile33.setSize(new Size(6.1, 0.627, 6.1));
        this.ancientTemplePiece.setPaint(Color.WHITE);
        this.ancientTemplePiece.setOpacity(1.0);
        this.ancientTemplePiece.setName("ancientTemplePiece");
        this.ancientTemplePiece.setVehicle(this);
        this.ancientTemplePiece.setOrientationRelativeToVehicle(new Orientation(0.0, -0.442948, 0.0, 0.896547));
        this.ancientTemplePiece.setPositionRelativeToVehicle(new Position(-76.9, -4.42, 31.0));
        this.ancientTemplePiece.setSize(new Size(37.9, 10.0, 57.2));
        this.ancientTemplePiece2.setPaint(Color.WHITE);
        this.ancientTemplePiece2.setOpacity(1.0);
        this.ancientTemplePiece2.setName("ancientTemplePiece2");
        this.ancientTemplePiece2.setVehicle(this);
        this.ancientTemplePiece2.setOrientationRelativeToVehicle(new Orientation(0.0, -0.444078, 0.0, 0.895988));
        this.ancientTemplePiece2.setPositionRelativeToVehicle(new Position(-44.2, -8.55, 5.65));
        this.ancientTemplePiece2.setSize(new Size(18.6, 9.84, 24.2));
        this.figTree4.setPaint(new Color(1.0, 0.859, 0.341));
        this.figTree4.setOpacity(1.0);
        this.figTree4.setName("figTree4");
        this.figTree4.setVehicle(this);
        this.figTree4.setOrientationRelativeToVehicle(new Orientation(0.0, 0.162092, 0.0, 0.986776));
        this.figTree4.setPositionRelativeToVehicle(new Position(-22.9, -1.05, 0.353));
        this.figTree4.setSize(new Size(4.83, 7.22, 4.65));
        this.figTree5.setPaint(new Color(1.0, 0.859, 0.341));
        this.figTree5.setOpacity(1.0);
        this.figTree5.setName("figTree5");
        this.figTree5.setVehicle(this);
        this.figTree5.setOrientationRelativeToVehicle(new Orientation(0.0, -0.998937, 0.0, 0.046096));
        this.figTree5.setPositionRelativeToVehicle(new Position(-28.4, -1.05, 5.0));
        this.figTree5.setSize(new Size(4.83, 7.22, 4.65));
        this.figTree6.setPaint(new Color(1.0, 0.859, 0.341));
        this.figTree6.setOpacity(1.0);
        this.figTree6.setName("figTree6");
        this.figTree6.setVehicle(this);
        this.figTree6.setOrientationRelativeToVehicle(new Orientation(0.0, -0.408305, 0.0, 0.912846));
        this.figTree6.setPositionRelativeToVehicle(new Position(-34.1, -1.05, 9.04));
        this.figTree6.setSize(new Size(4.83, 7.22, 4.65));
        this.cypressTree.setPaint(new Color(1.0, 0.71, 0.267));
        this.cypressTree.setOpacity(1.0);
        this.cypressTree.setName("cypressTree");
        this.cypressTree.setVehicle(this);
        this.cypressTree.setOrientationRelativeToVehicle(new Orientation(0.0, -0.736212, 0.0, 0.676751));
        this.cypressTree.setPositionRelativeToVehicle(new Position(12.9, 0.0, -7.88));
        this.cypressTree.setSize(new Size(17.5, 13.4, 17.2));
        this.mangoTree.setPaint(Color.WHITE);
        this.mangoTree.setOpacity(1.0);
        this.mangoTree.setName("mangoTree");
        this.mangoTree.setVehicle(this);
        this.mangoTree.setOrientationRelativeToVehicle(new Orientation(0.0, -0.102276, 0.0, 0.994756));
        this.mangoTree.setPositionRelativeToVehicle(new Position(-24.6, 0.0, 43.2));
        this.mangoTree.setSize(new Size(17.3, 12.9, 15.8));
        this.mangoTree2.setPaint(new Color(0.275, 0.455, 0.353));
        this.mangoTree2.setOpacity(1.0);
        this.mangoTree2.setName("mangoTree2");
        this.mangoTree2.setVehicle(this);
        this.mangoTree2.setOrientationRelativeToVehicle(new Orientation(0.0, -0.102276, 0.0, 0.994756));
        this.mangoTree2.setPositionRelativeToVehicle(new Position(-0.286, 0.209, 51.7));
        this.mangoTree2.setSize(new Size(22.0, 16.5, 20.2));
        this.mangoTree3.setPaint(new Color(0.275, 0.455, 0.353));
        this.mangoTree3.setOpacity(1.0);
        this.mangoTree3.setName("mangoTree3");
        this.mangoTree3.setVehicle(this);
        this.mangoTree3.setOrientationRelativeToVehicle(new Orientation(0.0, 0.819217, 0.0, 0.573484));
        this.mangoTree3.setPositionRelativeToVehicle(new Position(3.86, 0.209, 36.4));
        this.mangoTree3.setSize(new Size(19.5, 14.6, 17.9));
        this.cypressTree2.setPaint(new Color(1.0, 0.71, 0.267));
        this.cypressTree2.setOpacity(1.0);
        this.cypressTree2.setName("cypressTree2");
        this.cypressTree2.setVehicle(this);
        this.cypressTree2.setOrientationRelativeToVehicle(new Orientation(0.0, 0.994317, 0.0, 0.10646));
        this.cypressTree2.setPositionRelativeToVehicle(new Position(4.39, 0.0, -15.6));
        this.cypressTree2.setSize(new Size(15.8, 12.1, 15.4));
        this.ancientTempleTreeStump2.setPaint(new Color(0.949, 0.729, 0.631));
        this.ancientTempleTreeStump2.setOpacity(1.0);
        this.ancientTempleTreeStump2.setName("ancientTempleTreeStump2");
        this.ancientTempleTreeStump2.setVehicle(this);
        this.ancientTempleTreeStump2.setOrientationRelativeToVehicle(new Orientation(0.0, -0.43808, 0.0, 0.898936));
        this.ancientTempleTreeStump2.setPositionRelativeToVehicle(new Position(-1.54, 0.214, -21.5));
        this.ancientTempleTreeStump2.setSize(new Size(6.67, 6.21, 8.51));
        this.cypressTree3.setPaint(new Color(1.0, 0.71, 0.267));
        this.cypressTree3.setOpacity(1.0);
        this.cypressTree3.setName("cypressTree3");
        this.cypressTree3.setVehicle(this);
        this.cypressTree3.setOrientationRelativeToVehicle(new Orientation(0.0, 0.060013, 0.0, 0.998198));
        this.cypressTree3.setPositionRelativeToVehicle(new Position(7.96, 0.0, -35.3));
        this.cypressTree3.setSize(new Size(27.4, 21.0, 26.9));
        this.cypressTree4.setPaint(new Color(1.0, 0.71, 0.267));
        this.cypressTree4.setOpacity(1.0);
        this.cypressTree4.setName("cypressTree4");
        this.cypressTree4.setVehicle(this);
        this.cypressTree4.setOrientationRelativeToVehicle(new Orientation(0.0, 0.466247, 0.0, 0.884655));
        this.cypressTree4.setPositionRelativeToVehicle(new Position(-19.5, 0.0, -27.7));
        this.cypressTree4.setSize(new Size(11.5, 8.78, 11.2));
        this.sandDunes8.setPaint(Color.WHITE);
        this.sandDunes8.setOpacity(1.0);
        this.sandDunes8.setName("sandDunes8");
        this.sandDunes8.setVehicle(this);
        this.sandDunes8.setOrientationRelativeToVehicle(new Orientation(0.0, 0.673224, 0.0, 0.739439));
        this.sandDunes8.setPositionRelativeToVehicle(new Position(-42.4, 0.0, -13.7));
        this.sandDunes8.setSize(new Size(30.9, 5.51, 38.6));
        this.sandDunes9.setPaint(Color.WHITE);
        this.sandDunes9.setOpacity(1.0);
        this.sandDunes9.setName("sandDunes9");
        this.sandDunes9.setVehicle(this);
        this.sandDunes9.setOrientationRelativeToVehicle(new Orientation(0.0, 0.71524, 0.0, 0.698879));
        this.sandDunes9.setPositionRelativeToVehicle(new Position(-36.3, 0.0, -33.3));
        this.sandDunes9.setSize(new Size(30.6, 5.45, 38.2));
        this.sandDunes10.setPaint(Color.WHITE);
        this.sandDunes10.setOpacity(1.0);
        this.sandDunes10.setName("sandDunes10");
        this.sandDunes10.setVehicle(this);
        this.sandDunes10.setOrientationRelativeToVehicle(new Orientation(0.0, -0.689964, 0.0, 0.723844));
        this.sandDunes10.setPositionRelativeToVehicle(new Position(-57.6, 0.0, -37.1));
        this.sandDunes10.setSize(new Size(41.5, 7.4, 51.8));
        this.cypressTree5.setPaint(new Color(1.0, 0.71, 0.267));
        this.cypressTree5.setOpacity(1.0);
        this.cypressTree5.setName("cypressTree5");
        this.cypressTree5.setVehicle(this);
        this.cypressTree5.setOrientationRelativeToVehicle(new Orientation(0.0, -0.16421, 0.0, 0.986425));
        this.cypressTree5.setPositionRelativeToVehicle(new Position(-20.1, 0.0, -16.6));
        this.cypressTree5.setSize(new Size(11.5, 8.78, 11.2));
        this.mangoTree4.setPaint(new Color(0.275, 0.455, 0.353));
        this.mangoTree4.setOpacity(1.0);
        this.mangoTree4.setName("mangoTree4");
        this.mangoTree4.setVehicle(this);
        this.mangoTree4.setOrientationRelativeToVehicle(new Orientation(0.0, -0.102276, 0.0, 0.994756));
        this.mangoTree4.setPositionRelativeToVehicle(new Position(-2.16, -5.59, 81.2));
        this.mangoTree4.setSize(new Size(28.1, 21.0, 25.8));
        this.mangoTree5.setPaint(new Color(0.275, 0.455, 0.353));
        this.mangoTree5.setOpacity(1.0);
        this.mangoTree5.setName("mangoTree5");
        this.mangoTree5.setVehicle(this);
        this.mangoTree5.setOrientationRelativeToVehicle(new Orientation(0.0, -0.974403, 0.0, 0.224808));
        this.mangoTree5.setPositionRelativeToVehicle(new Position(-57.1, -1.09, 63.4));
        this.mangoTree5.setSize(new Size(28.1, 21.0, 25.8));
        this.mangoTree6.setPaint(Color.WHITE);
        this.mangoTree6.setOpacity(1.0);
        this.mangoTree6.setName("mangoTree6");
        this.mangoTree6.setVehicle(this);
        this.mangoTree6.setOrientationRelativeToVehicle(new Orientation(0.0, -0.937354, 0.0, 0.348378));
        this.mangoTree6.setPositionRelativeToVehicle(new Position(-36.2, 2.21, 53.6));
        this.mangoTree6.setSize(new Size(13.6, 10.2, 12.5));
        this.mangoTree7.setPaint(Color.WHITE);
        this.mangoTree7.setOpacity(1.0);
        this.mangoTree7.setName("mangoTree7");
        this.mangoTree7.setVehicle(this);
        this.mangoTree7.setOrientationRelativeToVehicle(new Orientation(0.0, 0.866199, 0.0, 0.499699));
        this.mangoTree7.setPositionRelativeToVehicle(new Position(-64.5, -2.51, 22.0));
        this.mangoTree7.setSize(new Size(12.4, 9.26, 11.3));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="/* Procedures and functions to handle multiple scenes */">
    @Override
    protected void handleActiveChanged(Boolean isActive, Integer activationCount) {
        if (isActive) {
            if (activationCount == 1) {
                this.performGeneratedSetUp();
                this.performCustomSetup();
                this.initializeEventListeners();
            } else {
                this.restoreStateAndEventListeners();
            }
        } else {
            this.preserveStateAndEventListeners();
        }
    }

    public SGround getGround() {
        return this.ground;
    }

    public SCamera getCamera() {
        return this.camera;
    }

    public Nagi getIsabelle() {
        return this.Isabelle;
    }

    public BaobabTree getBaobabTree() {
        return this.baobabTree;
    }

    public Humvee getHumvee() {
        return this.humvee;
    }

    public Alice getAlice() {
        return this.alice;
    }

    public Boolean getCan_move_forward() {
        return this.can_move_forward;
    }

    public void setCan_move_forward(Boolean can_move_forward) {
        this.can_move_forward = can_move_forward;
    }

    public Boolean getCan_move_backward() {
        return this.can_move_backward;
    }

    public void setCan_move_backward(Boolean can_move_backward) {
        this.can_move_backward = can_move_backward;
    }

    public Mandril getMandril() {
        return this.mandril;
    }
    // </editor-fold>
}
