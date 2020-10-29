import lombok.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ManagedBean(name = "point")
@SessionScoped

//Model
public class Point implements Serializable {

    private double x_value;
    private double y_value;
    private int r_value;
    private String res;
    private String jsessionid;

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String hit_result) {
        this.res = res;
    }

    public double getY_value() {
        return y_value;
    }

    public void setY_value(double y_value) {
        this.y_value = y_value;
    }

    public double getX_value() {
        return x_value;
    }

    public void setX_value(double x_value) {
        this.x_value = x_value;
    }

    public int getR_value() {
        return r_value;
    }

    public void setR_value(int r_value) {
        this.r_value = r_value;
    }

    @Override
    public String toString() {
        return "<tr>" +
                "<td>" + this.x_value + "</td>" +
                "<td>" + this.y_value + "</td>" +
                "<td>" + this.r_value + "</td>" +
                "<td>" + this.res + "</td>" +
                "</tr>";
    }
}
