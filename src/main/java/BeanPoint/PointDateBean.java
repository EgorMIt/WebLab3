package BeanPoint;


import BD.Connect_to_BD;
import Point.Point;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@ManagedBean
public class PointDateBean implements Serializable {

    //UUID session_id = UUID.randomUUID();

    private final Connect_to_BD bdClass = new Connect_to_BD();

    private Point newPoint;


    public List<Point> getPointsTable(){
        return bdClass.getPointList();
    }

    public PointDateBean() {
        this.newPoint = new Point();
    }

    public void setLastR(){
        if(getPointsTable().size() != 0)
            newPoint.setR(getPointsTable().get(0).getR());
    }

    public void doCode(){
       // newPoint.setSession_id(session_id.toString());
        if(newPoint.getR() == 0)
            newPoint.setR(1);
        newPoint.check();
        bdClass.addPoint(newPoint);
        newPoint = new Point();
        setLastR();
    }

    public void setNewPoint(Point newPoint) {
        this.newPoint = newPoint;
    }

    public Point getNewPoint() {
        return newPoint;
    }
}

