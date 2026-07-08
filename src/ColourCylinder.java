import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;

public class ColourCylinder extends Cylinder {
    private Color color;
    ColourCylinder() {this(10, 10);}
    ColourCylinder(double a, double b) {this(a, b, Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));}
    ColourCylinder(double a, double b, Color c) {
        super(a, b);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(c);
        setColor(c);
        super.setMaterial(material);
    }
    public static Cylinder paint(Cylinder cylinder, Color color) {
        return new ColourCylinder(cylinder.getRadius(), cylinder.getHeight(), color);}
    public void setColor(Color c) {color = c;}
    public Color getColor() {return color;}
}
