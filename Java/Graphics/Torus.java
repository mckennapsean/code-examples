// by Sean McKenna and last edited on April 25th, 2011
//
//Torus.java creates a series of rotating inner toruses, looks neat!
//

/**
 * All code except for makeWheel() method copied from
 * Sun's HelloUniverse program. And the remaining code
 * was copied and edited from Leon Tabak, plus my own
 * additions to create this file.
 */

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.util.ArrayList;
import java.util.Random;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Stripifier;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Light;
import javax.media.j3d.LineArray;
import javax.media.j3d.Link;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.SharedGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TriangleArray;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class Torus extends Applet{
  //how many triangles to approximate the torus
  private static final int MAJOR_STEPS = 80;
  private static final int MINOR_STEPS = 70;

  //between 0 and 1, forms inner and outer radii of torus
  private static final float MAJOR_RADIUS = 0.8f;
  private static final float MINOR_RADIUS = 0.1f;

  //between 0 and 1, look oval-ish
  private static final float ECCENTRICITY = 1.0f;

  //time for one rotation, in ms (smaller=faster)
  private static final int PERIOD = 1300;

  //set colors for scene
  private static final Color3f AMBIENT_COLOR = new Color3f(0.2f, 0.3f, 0.5f);
  private static final Color3f OBJECT_COLOR = new Color3f(0.4f, 0.1f, 0.9f);
  private static final Color3f EMISSIVE_COLOR = new Color3f(0.2f, 0.3f, 0.5f);
  private static final Color3f SPECULAR_COLOR = new Color3f(0.3f, 0.1f, 0.9f);

  //how many tori to create
  private static final int NUMB_TORI = 5;

  //some values to be stored
  private ArrayList<TransformGroup> tori = new ArrayList<TransformGroup>();
  private SimpleUniverse u = null;
  private Random rng = new Random();
  private int RANDOM = rng.nextInt(2);

  public BranchGroup createSceneGraph() {
    // Create the root of the branch graph
    BranchGroup objRoot = new BranchGroup();
    SharedGroup objShare = new SharedGroup();
    objShare.addChild(makeWheel(MAJOR_RADIUS, MINOR_RADIUS, ECCENTRICITY));

    //generates all objects, rotating
    this.createTori(objShare);
    for(TransformGroup tg:tori){
      objRoot.addChild(tg);
      this.generateSpin(objRoot, tg);
    }

    objRoot.addChild(this.createDirectionalLight());
    objRoot.addChild(this.createAmbientLight());

    // Have Java 3D perform optimizations on this scene graph.
    objRoot.compile();

    return objRoot;
  }

  private void createTori(SharedGroup sg){
    for(int i=0;i<NUMB_TORI;i++){
      TransformGroup obj = new TransformGroup();
      obj.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

      TransformGroup rot = new TransformGroup();
      float shrink = 1.0f - ((1.0f / NUMB_TORI) * i);
      float[] xzMatrix = {0f,0f,shrink,0f, 0f,shrink,0f,0f, shrink,0f,0f,0f, 0f,0f,0f,1f};
      Transform3D rotate = new Transform3D(xzMatrix);
      rot.setTransform(rotate);
      obj.addChild(rot);

      rot.addChild(new Link(sg));
      tori.add(obj);
    }
  }

  private void generateSpin(BranchGroup bg, TransformGroup tg){
    Transform3D yAxis = new Transform3D();
    Alpha rotationAlpha = new Alpha(-1, PERIOD);
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
    int rotBit = tori.indexOf(tg);
    float[] rotMatrix;

    if(rotBit == NUMB_TORI - 1){
      TransformGroup tg2 = (TransformGroup) tg.getChild(0);
      float shrink = 1.0f - ((1.0f / NUMB_TORI) * (NUMB_TORI - 1));
      float[] flipMatrix = {shrink,0f,0f,0f, 0f,shrink,0f,0f, 0f,0f,shrink,0f, 0f,0f,0f,1f};
      Transform3D rotate = new Transform3D(flipMatrix);
      tg2.setTransform(rotate);
      float[] matrix = {-1f,0f,0f,0f, 0f,0f,1f,0f, 0f,1f,0f,0f, 0f,0f,0f,1f};
      rotMatrix = matrix;
    }else if((rotBit + RANDOM)%2 == 1){
      float[] matrix = {0f,1f,0f,0f, 1f,0f,0f,0f, 0f,0f,-1f,0f, 0f,0f,0f,1f};
      rotMatrix = matrix;
    }else{
      float[] matrix = {1f,0f,0f,0f, 0f,1f,0f,0f, 0f,0f,1f,0f, 0f,0f,0f,1f};
      rotMatrix = matrix;
    }
    Transform3D axis = new Transform3D(rotMatrix);
    RotationInterpolator rotator = new RotationInterpolator(rotationAlpha, tg, axis, 0.0f, (float) Math.PI * 2.0f);      
    rotator.setSchedulingBounds(bounds);
    bg.addChild(rotator);
  }

  private Light createDirectionalLight(){
    DirectionalLight light = new DirectionalLight();
    BoundingSphere infinite = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),Double.MAX_VALUE);
    light.setInfluencingBounds(infinite);
    light.setColor(new Color3f(1.0f, 1.0f, 1.0f));
    light.setCapability(Light.ALLOW_STATE_WRITE);
    light.setDirection(new Vector3f(-1.0f, 0.0f, -1.0f));
    light.setEnable(true);

    return light;
  }

  private Light createAmbientLight(){
    AmbientLight light = new AmbientLight();
    BoundingSphere infinite = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),Double.MAX_VALUE);
    light.setInfluencingBounds(infinite);
    light.setColor(new Color3f(1.0f, 1.0f, 1.0f));
    light.setCapability(Light.ALLOW_STATE_WRITE);
    light.setEnable(true);

    return light;
  }

  private Appearance createAppearance(){
    Appearance appearance = new Appearance();
    ColoringAttributes ca = new ColoringAttributes();
    ca.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
    appearance.setColoringAttributes(ca);

    PolygonAttributes pa = new PolygonAttributes();
    pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
    appearance.setPolygonAttributes(pa);

    Color3f ambientColor = AMBIENT_COLOR;
    Color3f objectColor = OBJECT_COLOR;
    Color3f emissiveColor = EMISSIVE_COLOR;
    Color3f specularColor = SPECULAR_COLOR;

    Material material = new Material(ambientColor,emissiveColor,objectColor,specularColor,100.0f);
    material.setLightingEnable(true);
    appearance.setMaterial(material);

    return appearance;
  }

  public Node makeWheel(float majorRadius, float minorRadius,float eccentricity){
    int majorSteps = MAJOR_STEPS;
    int minorSteps = MINOR_STEPS;

    Point3f[] v = new Point3f[minorSteps];
    for(int j = 0; j < minorSteps; j++){
      float angle = (float) (2.0 * Math.PI * ((double) j) / minorSteps);
      float x = (float) (eccentricity * minorRadius * Math.cos(angle));
      float y = (float) (minorRadius * Math.sin(angle));
      v[j] = new Point3f(x, y + majorRadius, 0.0f);
    }

    Point3f[][] vertices = new Point3f[majorSteps][minorSteps];
    for(int i = 0; i < majorSteps; i++){
      float angle = (float) (2.0 * Math.PI * ((double) i) / majorSteps);
      Transform3D rotation = new Transform3D();
      AxisAngle4f axisAngle = new AxisAngle4f(1.0f, 0.0f, 0.0f, angle);
      rotation.setRotation(axisAngle);
      for(int j = 0; j < minorSteps; j++){
        vertices[i][j] = new Point3f();
        rotation.transform(v[j], vertices[i][j]);
      }
    }

    int numberOfLines = 2 * majorSteps * minorSteps;
    int code = GeometryArray.COORDINATES;
    LineArray lines = new LineArray(numberOfLines * 2, code);

    TriangleArray t = new TriangleArray(6 * majorSteps * minorSteps, GeometryArray.COORDINATES);

    int k = 0;
    int index = 0;
    for(int i = 0; i < majorSteps; i++){
      for(int j = 0; j < minorSteps; j++){
        t.setCoordinate(index++, vertices[i][j]);
        t.setCoordinate(index++, vertices[i][(j + 1) % minorSteps]);
        t.setCoordinate(index++, vertices[(i + 1) % majorSteps][j]);

        t.setCoordinate(index++, vertices[i][(j + 1) % minorSteps]);
        t.setCoordinate(index++, vertices[(i + 1) % majorSteps][(j + 1) % minorSteps]);
        t.setCoordinate(index++, vertices[(i + 1) % majorSteps][j]);

      }
    }

    GeometryInfo geometryInfo = new GeometryInfo(t);
    NormalGenerator normalGenerator = new NormalGenerator();
    normalGenerator.generateNormals(geometryInfo);
    Stripifier stripifier = new Stripifier();
    stripifier.stripify(geometryInfo);

    return new Shape3D(geometryInfo.getGeometryArray(), this.createAppearance());
  }

  public Torus(){}

  @Override
  public void init(){
    setLayout(new BorderLayout());
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D c = new Canvas3D(config);
    add("Center", c);

    // Create a simple scene and attach it to the virtual universe
    BranchGroup scene = createSceneGraph();
    u = new SimpleUniverse(c);

    // Moves the ViewPlatform back a bit so objects can be seen
    u.getViewingPlatform().setNominalViewingTransform();
    u.addBranchGraph(scene);
  }

  @Override
  public void destroy(){
    u.removeAllLocales();
  }

  private float avg(float a, float b, float t){
    return (1 - t) * a + t * b;
  }

  private Point3f avg(Point3f a, Point3f b, float t){
    float x = avg(a.x, b.x, t);
    float y = avg(a.y, b.y, t);
    float z = avg(a.z, b.z, t);
    return new Point3f(x, y, z);
  }

  private Point3f avg(Point3f a, Point3f b, Point3f c, float t){
    return avg(avg(a, b, t), avg(b, c, t), t);
  }

  private Point3f avg(Point3f a, Point3f b, Point3f c, Point3f d, float t){
    return avg(avg(a, b, c, t), avg(b, c, d, t), t);
  }

  //Allows application to also be run as an applet...
  public static void main(String[] args){
    new MainFrame(new Torus(), 800, 800);
  }
}
