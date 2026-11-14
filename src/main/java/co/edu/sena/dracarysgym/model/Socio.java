package co.edu.sena.dracarysgym.model;

/** Entidad del módulo de socios. */
public class Socio {
    private Integer id;
    private String documento;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String plan;
    private boolean activo;

    public Socio(Integer id, String documento, String nombre, String apellido,
                 String correo, String telefono, String plan, boolean activo) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.plan = plan;
        this.activo = activo;
    }

    public Integer getId() { return id; }
    public String getDocumento() { return documento; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
    public String getPlan() { return plan; }
    public boolean isActivo() { return activo; }
}
