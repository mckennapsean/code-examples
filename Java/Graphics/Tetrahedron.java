
import java.awt.BorderLayout ;
import java.awt.Color ;
import java.awt.Container ;
import java.awt.GraphicsConfiguration ;

import java.awt.event.WindowAdapter ;
import java.awt.event.WindowEvent ;

import javax.media.j3d.Alpha ;
import javax.media.j3d.AmbientLight ;
import javax.media.j3d.Appearance ;
import javax.media.j3d.Background ;
import javax.media.j3d.BoundingSphere ;
import javax.media.j3d.BranchGroup ;
import javax.media.j3d.Canvas3D ;
import javax.media.j3d.ColoringAttributes ;
import javax.media.j3d.DirectionalLight ;
import javax.media.j3d.Geometry ;
import javax.media.j3d.GeometryArray ;
import javax.media.j3d.IndexedTriangleArray ;
import javax.media.j3d.Light ;
import javax.media.j3d.Material ;
import javax.media.j3d.Node ;
import javax.media.j3d.PolygonAttributes ;
import javax.media.j3d.RotationInterpolator ;
import javax.media.j3d.Shape3D ;
import javax.media.j3d.Transform3D ;
import javax.media.j3d.TransformGroup ;

import javax.swing.JFrame ;

import javax.vecmath.Color3f ;
import javax.vecmath.Point3d ;
import javax.vecmath.Point3f ;
import javax.vecmath.Vector3f ;

import com.sun.j3d.utils.geometry.GeometryInfo ;
import com.sun.j3d.utils.geometry.NormalGenerator ;
import com.sun.j3d.utils.universe.SimpleUniverse ;

public class Tetrahedron extends JFrame {

    public static void main( String [ ] arguments ) {
	Tetrahedron tetrahedron = new Tetrahedron( "Tetrahedron", 512, 512 ) ;
    } // main ( String [ ] )

    public Tetrahedron( String title, int width, int height ) {
	this.setSize( width, height ) ;
	this.setTitle( title ) ;
	this.setBackground( Color.blue ) ;
	this.show() ;

	this.addWindowListener (
	  new WindowAdapter() {
	      public void windowClosing( WindowEvent e ) {
		  if( Tetrahedron.this.u != null ) {
		      Tetrahedron.this.u.removeAllLocales() ;
		  } // if

		  e.getWindow().dispose() ;
		  System.exit( 0 ) ;
	      } // windowClosing( WindowEvent )
	  } // WindowAdapter()
          ) ;

	GraphicsConfiguration configuration = 
              SimpleUniverse.getPreferredConfiguration() ;

	Canvas3D canvas = new Canvas3D( configuration ) ;
	canvas.setBackground( Color.black ) ;

	Container contentPane = this.getContentPane() ;
	contentPane.setLayout( new BorderLayout() ) ;
	canvas.setSize( contentPane.getSize() ) ;
	contentPane.add( "Center", canvas ) ;

	this.u = new SimpleUniverse( canvas ) ;
	this.u.getViewingPlatform().setNominalViewingTransform() ;
	this.u.addBranchGraph( this.createSceneGraph() ) ;
    } // Tetrahedron( String, int, int )

    private BranchGroup createSceneGraph() {
	BranchGroup bg = new BranchGroup() ;
	TransformGroup tg = new TransformGroup() ;
	tg.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE ) ;
	bg.addChild( tg ) ;

	tg.addChild( this.createShape() ) ;

	bg.addChild( this.createRotator( tg ) ) ;
	bg.addChild( this.createDirectionalLight() ) ;
	bg.addChild( this.createAmbientLight() ) ;
	bg.addChild( new Background( new Color3f( 0.7f, 0.2f, 0.8f ) ) ) ;

	bg.compile() ;

	return bg ;
    } // createSceneGraph()

    private RotationInterpolator createRotator( TransformGroup tg ) {
	Transform3D yAxis = new Transform3D() ;
	Transform3D xAxis = new Transform3D() ;
	xAxis.rotX( 0.5 ) ;
	yAxis.add( xAxis ) ;

	Alpha rotationAlpha = new Alpha( -1, 8000 ) ;
	RotationInterpolator rotator = 
	    new RotationInterpolator( rotationAlpha,
				      tg,
				      yAxis,
				      0.0f,
				      (float) Math.PI * 2.0f ) ;

	BoundingSphere bounds = 
	    new BoundingSphere( new Point3d( 0.0, 0.0, 0.0 ), 
				100.0 ) ;
	rotator.setSchedulingBounds( bounds ) ;

	return rotator ;
    } // createRotator()

    private Light createDirectionalLight() {
	DirectionalLight light = new DirectionalLight() ;
	BoundingSphere infinite = 
	    new BoundingSphere( new Point3d( 0.0, 0.0, 0.0 ), 
				Double.MAX_VALUE ) ;
	light.setInfluencingBounds( infinite ) ;
	light.setColor( new Color3f( 1.0f, 1.0f, 1.0f ) ) ;
	light.setCapability( Light.ALLOW_STATE_WRITE ) ;
	light.setDirection( new Vector3f( -1.0f, 0.0f, -1.0f ) ) ;
	light.setEnable( true ) ;

	return light ;
    } // createDirectionalLight()

    private Light createAmbientLight() {
	AmbientLight light = new AmbientLight() ;
	BoundingSphere infinite = 
	    new BoundingSphere( new Point3d( 0.0, 0.0, 0.0 ), 
				Double.MAX_VALUE ) ;
	light.setInfluencingBounds( infinite ) ;
	light.setColor( new Color3f( 1.0f, 1.0f, 1.0f ) ) ;
	light.setCapability( Light.ALLOW_STATE_WRITE ) ;
	light.setEnable( true ) ;

	return light ;
    } // createAmbientLight()

    private Node createShape() {
	return new Shape3D( this.createGeometry(), 
			    this.createAppearance() ) ;
    } // createShape()

    private Geometry createGeometry() {
        float z = (float) (-1.0/3.0) ;

        float r = (float) Math.sqrt( 1.0 - z*z) ;

	float x0 = (float) (r * Math.cos( 0 * 2 * Math.PI / 3.0 )) ;
        float y0 = (float) (r * Math.sin( 0 * 2 * Math.PI / 3.0 )) ;

	float x1 = (float) (r * Math.cos( 1 * 2 * Math.PI / 3.0 )) ;
        float y1 = (float) (r * Math.sin( 1 * 2 * Math.PI / 3.0 )) ;

	float x2 = (float) (r * Math.cos( 2 * 2 * Math.PI / 3.0 )) ;
        float y2 = (float) (r * Math.sin( 2 * 2 * Math.PI / 3.0 )) ;


	Point3f [] vertices = new Point3f[4] ;
	vertices[0] = new Point3f( x0, y0, z ) ;
	vertices[1] = new Point3f( x1, y1, z ) ;
	vertices[2] = new Point3f( x2, y2, z ) ;
	vertices[3] = new Point3f( 0.0f, 0.0f, 1.0f ) ;

	int [] indices = { 2, 1, 0,
			   0, 1, 3,
			   1, 2, 3,
			   2, 0, 3 
	                   } ;

	GeometryInfo g = new GeometryInfo( GeometryInfo.TRIANGLE_ARRAY ) ;
	g.setCoordinates( vertices ) ;
	g.setCoordinateIndices( indices ) ;
	NormalGenerator ng = new NormalGenerator() ;
	ng.setCreaseAngle( Math.PI/4 ) ;
	ng.generateNormals( g ) ;
	GeometryArray octahedron = g.getGeometryArray() ;

	return octahedron ;
    } // createGeometry()

    private Appearance createAppearance() {
	Appearance appearance = new Appearance() ;
	ColoringAttributes ca = new ColoringAttributes() ;
	ca.setShadeModel( ColoringAttributes.SHADE_FLAT ) ;
	appearance.setColoringAttributes( ca ) ;

	PolygonAttributes pa = new PolygonAttributes() ;
	pa.setPolygonMode( PolygonAttributes.POLYGON_FILL ) ;
	appearance.setPolygonAttributes( pa ) ;

	Color3f ambientColor = new Color3f( 0.8f, 0.4f, 1.0f ) ;
	Color3f objectColor = new Color3f( 0.2f, 0.4f, 1.0f ) ;
	Color3f emissiveColor = new Color3f( 0.0f, 0.0f, 0.0f ) ;
	Color3f specularColor = new Color3f( 0.4f, 0.8f, 1.0f ) ;

	Material material = new Material( ambientColor,
					  emissiveColor,
					  objectColor,
					  specularColor,
					  100.0f ) ;
	material.setLightingEnable( true ) ;
	appearance.setMaterial( material ) ;

	return appearance ;
    } // createAppearance()

    private SimpleUniverse u ;
} // Tetrahedron
