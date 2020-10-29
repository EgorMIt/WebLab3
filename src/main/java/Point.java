import java.io.Serializable;

public class Point implements Serializable {

    private double x;
    private double y;
    private int r;
    private String res;
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String hit_result) {
        this.res = res;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "<tr>" +
                "<td>" + this.x + "</td>" +
                "<td>" + this.y + "</td>" +
                "<td>" + this.r + "</td>" +
                "<td>" + this.res + "</td>" +
                "</tr>";
    }

    public void check() {
        if ((x * x + y * y <= r * r && x <= 0 && y <= 0) ||
                (y - 2 * x >= -r / 2 && x >= 0 && y <= 0) ||
                (x <= 0 && y >= 0 && y <= r && x >= r / 2)) {
            res = "Входит";
        } else {
            res = "Не входит";
        }
    }
}
