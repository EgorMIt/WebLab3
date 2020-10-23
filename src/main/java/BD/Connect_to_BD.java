package BD;

import Point.Point;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@ManagedBean
public class Connect_to_BD {
    static final List<Point> pointList = new ArrayList<>();

    public List<Point> getPointList() {
        return pointList;
    }

    public void addPoint(Point point) {
        pointList.add(0, point);
    }
}