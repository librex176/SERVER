package backend.model;

import java.util.Date;

public class Pedido {
    private int id;
    private int userId;
    private Date fecha;

    public Pedido(int userId) {
        this.userId = userId;
        this.fecha = new java.sql.Date(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
