import lombok.NoArgsConstructor;
import org.hibernate.cfg.Configuration;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "resultBean", eager = true)
@SessionScoped
@NoArgsConstructor
public class PointBean implements Serializable {
    private Point point;
    private List<Point> points;
    private PointDao pointDao;

    @PostConstruct
    public void init() {
        point = new Point();
        points = new ArrayList<>();
        pointDao = new PointDao(new Configuration().configure().buildSessionFactory());
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void add() throws IOException {
        //TODO forward to db controller then get response and update *.xhtml
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (validate(point.getX_value(), point.getY_value(), point.getR_value())) {
            String res = check(point.getX_value(), point.getY_value(), point.getR_value()) ? "TRUE" : "FALSE";
            point.setRes(res);
            point.setJsessionid(facesContext.getExternalContext().getSessionId(false) + ".vp"); //этот метод проверяет, существовал ли уже сеанс для запроса или нет. Если он существовал, он вернет уже существующий сеанс. Если сеанс еще не существует для этого запроса, этот метод вернет NULL
            try {
                pointDao.create(point);
            } catch (Exception e) {
                e.printStackTrace();
            }
            points.add(point);
            point = new Point();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../Web3Lab-1.0/views/main.xhtml"); // тут надо сделать редирект на main, не уверен  что нужно /Web3Lab-1.0
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../Web3Lab-1.0/views/main.xhtml");
        }
    }

    public void remove() {
        //TODO use client session (through jsp session and manage bean)
        Iterator<Point> respIterator = points.iterator(); //получение определенного элемента
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String jsessionid = facesContext.getExternalContext().getSessionId(true); // получение сессии
        while (respIterator.hasNext()) {
            Point cur = respIterator.next();
            if (cur.getJsessionid().equals(jsessionid + ".vp")) {
                try {
                    pointDao.delete(cur); // получение и удаление элемента массива
                } catch (Exception e) {
                    e.printStackTrace();
                }
                respIterator.remove();
            }
        }

    }
    /*
     и еще проверка на попадание точки, тут лучше въебать замены "," на "." , как делалось в прошлых лабах
     */
    private boolean validate(Double x, Double y, int r) {
        return (x >= -3 && x <= 3 && y >= -3 && y <= 3 && y >= 1 && y <= 5);
    }
    /*
    Ниже просто огромная проверка условия
     */
    private boolean check(double x, double y, int r) {
        if ((x * x + y * y <= r * r && x <= 0 && y <= 0) ||
                (y - 2 * x >= -r / 2 && x >= 0 && y <= 0) ||
                (x <= 0 && y >= 0 && y <= r && x >= r / 2)) {
            return true;
        } else {
            return false;
        }
    }

}
