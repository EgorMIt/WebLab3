
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ManagedBean
@ApplicationScoped
public class PointDateBean implements Serializable {

    UUID owner = UUID.randomUUID();

    private final ConnectToDB oracle_db = new ConnectToDB();

    private Point newPoint;

    public List<Point> getPointsTable(){
        return oracle_db.getPoints(this.owner.toString());
    }

    public PointDateBean() {
        this.newPoint = new Point();
    }

    public void setLastR(){
        if(getPointsTable().size() != 0)
            newPoint.setR(getPointsTable().get(0).getR());
    }

    public void doCode(){
        newPoint.setOwner(owner.toString());
        newPoint.check();
        oracle_db.addPointToTable(newPoint);
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
