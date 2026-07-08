import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Cylinder;
import javafx.util.Duration;
import java.net.URL;
import java.util.*;

public class mango implements Initializable {
    public ArrayList<Cylinder> ray1;
    public ArrayList<Cylinder> ray2;
    public ArrayList<Cylinder> ray3;
    public ArrayList<Integer> solveray1;
    public ArrayList<Integer> solveray2;
    public ArrayList<Integer> solveray3;
    @FXML private AnchorPane ray1fx;
    @FXML private AnchorPane ray2fx;
    @FXML private AnchorPane ray3fx;
    @FXML private AnchorPane selectHrdnsMenu;
    @FXML private Button backBtn;
    @FXML private AnchorPane winLabel;
    @FXML private ImageView back;
    @FXML private ImageView solve;
    @FXML private ImageView table;
    private boolean handMode = false;
    private ColourCylinder inHand = null;
    private int from = 0;
    private int hardness;
    List<int[]> moves = new ArrayList<>();

    public void initialize(URL location, ResourceBundle resources) {
        backBtn.setVisible(false);
        winLabel.setVisible(true);
        back.setImage(new Image("./template/images/back.png"));
        solve.setImage(new Image("./template/images/solve.png"));
        table.setImage(new Image("./template/images/table.png"));
    }
    public void impossible() {setHardness(7);}
    public void hard() {setHardness(5);}
    public void medium() {setHardness(4);}
    public void easy() {setHardness(3);}
    public void setHardness(int cnt) {
        ray1 = new ArrayList<Cylinder>();
        ray2 = new ArrayList<Cylinder>();
        ray3 = new ArrayList<Cylinder>();
        solveray1 = new ArrayList<Integer>();
        solveray2 = new ArrayList<Integer>();
        solveray3 = new ArrayList<Integer>();
        selectHrdnsMenu.setVisible(false);
        hardness = cnt;
        backBtn.setVisible(true);
        for (int i = cnt; i > 0; i--) {
            addToRay1(new ColourCylinder((i + 2) * 10, 20));
            solveray1.add(i);
        }
    }
    public void fxClndrCreator(ColourCylinder cylinder, ArrayList<Cylinder> ray, int to) {
        Color color = cylinder.getColor();
        cylinder.setHeight(20);
        cylinder.setLayoutX(100);
        int lastY = 400 - ray.size() * 20 - 350;
        cylinder.setLayoutY(lastY);
        cylinder.setRotationAxis(new Point3D(1, 0, 0));
        cylinder.setRotate(10);
        cylinder.getStyleClass().add("myCy");
        if (from == 0) {
            if (to == 1) {
                ray1fx.getChildren().add(cylinder);
                EasyTransition.t4(cylinder).play();
            } else if (to == 2) {
                ray2fx.getChildren().add(cylinder);
                EasyTransition.t4(cylinder).play();
            } else if (to == 3) {
                ray3fx.getChildren().add(cylinder);
                EasyTransition.t4(cylinder).play();
            }
        } else if (from == 1) {
            if (to == 1)
                EasyTransition.t2(cylinder).play();
            else if (to == 2) {
                ParallelTransition t = EasyTransition.t3(cylinder);
                double r = cylinder.getRadius();
                t.setOnFinished(e -> {ray1fx.getChildren().removeLast();});
                t.play();
                from = 0;
                fxClndrCreator(new ColourCylinder(r, 20, color), ray2, 2);
            } else if (to == 3) {
                cylinder.setOpacity(1);
                ParallelTransition t = EasyTransition.t3(cylinder);
                double r = cylinder.getRadius();
                t.setOnFinished(e -> {ray1fx.getChildren().removeLast();});
                t.play();
                from = 0;
                fxClndrCreator(new ColourCylinder(r, 20, color), ray3, 3);
            }
        } else if (from == 2) {
            if (to == 2)
                EasyTransition.t2(cylinder).play();
            else if (to == 1) {
                ParallelTransition t = EasyTransition.t3(cylinder);
                double r = cylinder.getRadius();
                t.setOnFinished(e -> {ray2fx.getChildren().removeLast();});
                t.play();
                from = 0;
                fxClndrCreator(new ColourCylinder(r, 20, color), ray1, 1);
            } else if (to == 3) {
                ParallelTransition t = EasyTransition.t3(cylinder);
                double r = cylinder.getRadius();
                t.setOnFinished(e -> {ray2fx.getChildren().removeLast();});
                t.play();
                from = 0;
                fxClndrCreator(new ColourCylinder(r, 20, color), ray3, 3);
            }
        } else if (from == 3) {
            if (to == 3)
                EasyTransition.t2(cylinder).play();
            else if (to == 1) {
                cylinder.setOpacity(1);
                ParallelTransition t = EasyTransition.t3(cylinder);
                double r = cylinder.getRadius();
                t.setOnFinished(e -> {ray3fx.getChildren().removeLast();});
                t.play();
                from = 0;
                fxClndrCreator(new ColourCylinder(r, 20, color), ray1, 1);
            } else if (to == 2) {
                ParallelTransition t = EasyTransition.t3(cylinder);
                double r = cylinder.getRadius();
                t.setOnFinished(e -> {ray3fx.getChildren().removeLast();});
                t.play();
                from = 0;
                fxClndrCreator(new ColourCylinder(r, 20, color), ray2, 2);
            }
        }
    }
    public boolean addToRay1(ColourCylinder cylinder){
        if ((ray1.isEmpty()) || (ray1.getLast().getRadius() > cylinder.getRadius())) {
            fxClndrCreator(cylinder, ray1, 1);
            ray1.add(cylinder);
            return true;
        }
        return false;
    }
    public ColourCylinder removeFromRay1() {
        if (!ray1.isEmpty()) {
            EasyTransition.t1(ray1fx.getChildren().getLast()).play();
            from = 1;
            ray1.removeLast();
            if (ray1fx.getChildren().getLast() instanceof ColourCylinder c)
                return c;
            return null;
        }return null;
    }
    public boolean addToRay2(ColourCylinder cylinder) {
        if ((ray2.isEmpty()) || (ray2.getLast().getRadius() > cylinder.getRadius())) {
            fxClndrCreator(cylinder, ray2, 2);
            ray2.add(cylinder);
            return true;
        }return false;
    }
    public ColourCylinder removeFromRay2() {
        if (!ray2.isEmpty()) {
            EasyTransition.t1(ray2fx.getChildren().getLast()).play();
            from = 2;
            ray2.removeLast();
            if (ray2fx.getChildren().getLast() instanceof ColourCylinder c)
                return c;
            return null;
        }return null;
    }
    public boolean addToRay3(ColourCylinder cylinder) {
        if ((ray3.isEmpty()) || (ray3.getLast().getRadius() > cylinder.getRadius())) {
            fxClndrCreator(cylinder, ray3, 3);
            ray3.add(cylinder);
            return true;
        }return false;
    }
    public ColourCylinder removeFromRay3() {
        if (!ray3.isEmpty()) {
            EasyTransition.t1(ray3fx.getChildren().getLast()).play();
            from = 3;
            ray3.removeLast();
            if (ray3fx.getChildren().getLast() instanceof ColourCylinder c)
                return c;
            return null;
        }return null;
    }
    public void btn1() {
        if (!handMode) {
            if (!ray1.isEmpty()) {
                inHand = removeFromRay1();
                handMode = true;
                from = 1;
            }
        } else
            if (addToRay1(inHand)) handMode = false;
    }
    public void btn2() {
        if (!handMode) {
            if (!ray2.isEmpty()) {
                inHand = removeFromRay2();
                handMode = true;
            }
        } else
            if (addToRay2(inHand)) {
                if (ray2fx.getChildren().size() >= hardness)
                    EasyTransition.t4(winLabel).play();
                handMode = false;
            }
    }
    public void btn3() {
        if (!handMode) {
            if (!ray3.isEmpty()) {
                inHand = removeFromRay3();
                handMode = true;
            }
        } else
            if (addToRay3(inHand)) {
                handMode = false;
                if (ray3fx.getChildren().size() >= hardness)
                    EasyTransition.t4(winLabel).play();
            }
    }
    public void backToMnu(ActionEvent event) {
        backBtn.setVisible(false);
        selectHrdnsMenu.setVisible(true);
        EasyTransition.t1(winLabel).play();
        ray1.clear();
        ray2.clear();
        ray3.clear();
        ray1fx.getChildren().clear();
        ray2fx.getChildren().clear();
        ray3fx.getChildren().clear();
        inHand = null;
        from = 0;
        handMode = false;
    }
    public void solve(ActionEvent e) {
        moves.clear();
        solveAction(hardness, 3);
        playMoves();
    }
    public void solveAction(int a, int to) {
        if (a == 1) {
            moves.add(new int[]{find(a), to});
            int temp = 0;
            switch (find(a)) {
                case 1: temp = solveray1.removeLast();break;
                case 2: temp = solveray2.removeLast();break;
                case 3: temp = solveray3.removeLast();break;}
            switch (to) {
                case 1: solveray1.add(temp);break;
                case 2: solveray2.add(temp);break;
                case 3: solveray3.add(temp);break;}
        } else {
            int i;
            for (i = 1; (i == to) || (i == find(a)); i++);
            solveAction(a - 1, i);
            moves.add(new int[]{find(a), to});
            int temp = 7;
            switch (find(a)) {
                case 1: temp = solveray1.removeLast();break;
                case 2: temp = solveray2.removeLast();break;
                case 3: temp = solveray3.removeLast();break;}
            switch (to) {
                case 1: solveray1.add(temp);break;
                case 2: solveray2.add(temp);break;
                case 3: solveray3.add(temp);break;}
            solveAction(a - 1, to);
        }
    }
    public int find(int a) {
        for (int i = 0; i < solveray1.size(); i++)
            if (solveray1.get(i) == a)
                return 1;
        for (int i = 0; i < solveray2.size(); i++)
            if (solveray2.get(i) == a)
                return 2;
        for (int i = 0; i < solveray3.size(); i++)
            if (solveray3.get(i) == a)
                return 3;
        return 0;
    }
    public void transfer(int from, int to) {
        int temp = 0;
        switch (from) {
            case 1: btn1();break;
            case 2: btn2();break;
            case 3: btn3();break;}
        switch (to) {
            case 1: btn1();solveray1.add(temp);break;
            case 2: btn2();solveray2.add(temp);break;
            case 3: btn3();solveray3.add(temp);break;}
    }
    public void playMoves() {
        Timeline tl = new Timeline();
        int delayIndex = 0;
        for (int[] m : moves) {
            int fromt = m[0];
            int tot   = m[1];
            KeyFrame kf = new KeyFrame(Duration.seconds(delayIndex * 0.35), e -> {transfer(fromt, tot);});
            tl.getKeyFrames().add(kf);
            delayIndex++;
        }
        tl.play();
    }
}