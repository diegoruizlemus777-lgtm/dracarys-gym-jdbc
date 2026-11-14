package co.edu.sena.dracarysgym.ui;

import co.edu.sena.dracarysgym.dao.SocioDaoJdbc;
import co.edu.sena.dracarysgym.model.Socio;
import co.edu.sena.dracarysgym.service.SocioService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/** Interfaz de escritorio para insertar, consultar, actualizar y eliminar socios. */
public class SocioFrame extends JFrame {
    private final SocioService servicio = new SocioService(new SocioDaoJdbc());
    private final JTextField documento = new JTextField();
    private final JTextField nombre = new JTextField();
    private final JTextField apellido = new JTextField();
    private final JTextField correo = new JTextField();
    private final JTextField telefono = new JTextField();
    private final JComboBox<String> plan = new JComboBox<>(new String[] {"Mensual", "Trimestral", "Anual"});
    private final JComboBox<String> estado = new JComboBox<>(new String[] {"Activo", "Inactivo"});
    private final JTextField busqueda = new JTextField(22);
    private final DefaultTableModel modelo = new DefaultTableModel(
        new String[] {"ID", "Documento", "Nombre", "Apellido", "Correo", "Teléfono", "Plan", "Estado"}, 0) {
        @Override public boolean isCellEditable(int fila, int columna) { return false; }
    };
    private final JTable tabla = new JTable(modelo);
    private Integer idSeleccionado;

    public SocioFrame() {
        super("DRACARYS GYM | Gestión de socios (JDBC)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 650);
        setLocationRelativeTo(null);
        construirInterfaz();
        // La consulta inicial es opcional: los errores se muestran sin cerrar la interfaz.
        cargarSocios("");
    }

    private void construirInterfaz() {
        Color amarillo = new Color(245, 196, 43);
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        JPanel cabecera = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("DRACARYS GYM · Módulo de socios");
        titulo.setOpaque(true);
        titulo.setBackground(Color.BLACK);
        titulo.setForeground(amarillo);
        titulo.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        cabecera.add(titulo, BorderLayout.CENTER);
        principal.add(cabecera, BorderLayout.NORTH);

        JPanel contenido = new JPanel(new BorderLayout(10, 10));
        JPanel formulario = new JPanel(new GridLayout(0, 2, 9, 9));
        formulario.setBorder(BorderFactory.createTitledBorder("Datos del socio"));
        agregarCampo(formulario, "Documento", documento);
        agregarCampo(formulario, "Nombre", nombre);
        agregarCampo(formulario, "Apellido", apellido);
        agregarCampo(formulario, "Correo", correo);
        agregarCampo(formulario, "Teléfono", telefono);
        agregarCampo(formulario, "Plan", plan);
        agregarCampo(formulario, "Estado", estado);
        contenido.add(formulario, BorderLayout.NORTH);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton guardar = new JButton("Insertar");
        JButton editar = new JButton("Actualizar");
        JButton eliminar = new JButton("Eliminar");
        JButton limpiar = new JButton("Limpiar");
        acciones.add(guardar);
        acciones.add(editar);
        acciones.add(eliminar);
        acciones.add(limpiar);
        guardar.addActionListener(e -> ejecutar(() -> {
            int id = servicio.registrar(leerFormulario(null));
            mostrar("Socio registrado con ID " + id);
            limpiarFormulario();
            cargarSocios("");
        }));
        editar.addActionListener(e -> ejecutar(() -> {
            if (servicio.actualizar(leerFormulario(idSeleccionado))) {
                mostrar("Información actualizada.");
                limpiarFormulario();
                cargarSocios("");
            }
        }));
        eliminar.addActionListener(e -> {
            if (idSeleccionado == null) { mostrar("Seleccione primero un registro."); return; }
            int opcion = JOptionPane.showConfirmDialog(this, "¿Eliminar el socio seleccionado?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                ejecutar(() -> {
                    if (servicio.eliminar(idSeleccionado)) {
                        mostrar("Registro eliminado.");
                        limpiarFormulario();
                        cargarSocios("");
                    }
                });
            }
        });
        limpiar.addActionListener(e -> limpiarFormulario());

        JPanel lista = new JPanel(new BorderLayout(8, 8));
        lista.setBorder(BorderFactory.createTitledBorder("Consulta de socios"));
        JPanel filtro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton buscar = new JButton("Buscar");
        JButton todos = new JButton("Mostrar todos");
        filtro.add(new JLabel("Documento, nombre o apellido:"));
        filtro.add(busqueda);
        filtro.add(buscar);
        filtro.add(todos);
        buscar.addActionListener(e -> cargarSocios(busqueda.getText()));
        todos.addActionListener(e -> { busqueda.setText(""); cargarSocios(""); });
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() >= 0) seleccionarFila();
        });
        lista.add(filtro, BorderLayout.NORTH);
        lista.add(new JScrollPane(tabla), BorderLayout.CENTER);
        JPanel inferior = new JPanel(new BorderLayout());
        inferior.add(acciones, BorderLayout.NORTH);
        inferior.add(lista, BorderLayout.CENTER);
        contenido.add(inferior, BorderLayout.CENTER);
        principal.add(contenido, BorderLayout.CENTER);
        setContentPane(principal);
    }

    private void agregarCampo(JPanel formulario, String etiqueta, java.awt.Component componente) {
        formulario.add(new JLabel(etiqueta + ":"));
        formulario.add(componente);
    }

    private Socio leerFormulario(Integer id) {
        return new Socio(id, documento.getText().trim(), nombre.getText().trim(),
            apellido.getText().trim(), correo.getText().trim(), telefono.getText().trim(),
            (String) plan.getSelectedItem(), "Activo".equals(estado.getSelectedItem()));
    }

    private void seleccionarFila() {
        int fila = tabla.convertRowIndexToModel(tabla.getSelectedRow());
        idSeleccionado = (Integer) modelo.getValueAt(fila, 0);
        documento.setText((String) modelo.getValueAt(fila, 1));
        nombre.setText((String) modelo.getValueAt(fila, 2));
        apellido.setText((String) modelo.getValueAt(fila, 3));
        correo.setText((String) modelo.getValueAt(fila, 4));
        telefono.setText((String) modelo.getValueAt(fila, 5));
        plan.setSelectedItem(modelo.getValueAt(fila, 6));
        estado.setSelectedItem(modelo.getValueAt(fila, 7));
    }

    private void cargarSocios(String filtro) {
        ejecutar(() -> {
            List<Socio> socios = servicio.buscar(filtro);
            modelo.setRowCount(0);
            for (Socio socio : socios) {
                modelo.addRow(new Object[] {socio.getId(), socio.getDocumento(), socio.getNombre(),
                    socio.getApellido(), socio.getCorreo(), socio.getTelefono(), socio.getPlan(),
                    socio.isActivo() ? "Activo" : "Inactivo"});
            }
        });
    }

    private void limpiarFormulario() {
        idSeleccionado = null;
        tabla.clearSelection();
        for (JTextField campo : new JTextField[] {documento, nombre, apellido, correo, telefono}) {
            campo.setText("");
        }
        plan.setSelectedIndex(0);
        estado.setSelectedIndex(0);
    }

    private void ejecutar(Accion accion) {
        try {
            accion.ejecutar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + ex.getMessage(),
                "Conexión / SQL", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrar(String mensaje) { JOptionPane.showMessageDialog(this, mensaje); }

    @FunctionalInterface
    private interface Accion { void ejecutar() throws SQLException; }
}
