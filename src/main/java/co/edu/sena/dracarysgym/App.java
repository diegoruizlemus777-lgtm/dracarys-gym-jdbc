package co.edu.sena.dracarysgym;

import co.edu.sena.dracarysgym.ui.SocioFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/** Punto de entrada del módulo de gestión de socios. */
public final class App {
    private App() { }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Se utiliza la apariencia predeterminada de Swing.
            }
            new SocioFrame().setVisible(true);
        });
    }
}
