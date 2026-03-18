package pro01;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Ventana extends JFrame {

    // Componentes globales para acceder a ellos desde las funciones
    private JComboBox<String> comboHabitacion;
    private JRadioButton rbSencilla, rbDoble, rbSuite, rbPresidencial;
    private JCheckBox cbDesayuno, cbWifi, cbSpa, cbTransporte;
    private JTextArea areaResumen;
    private JTextField txtNombre;
    private JLabel lblCosto, lblImagenUbicacion; 
    private double costoTotal = 0;

    public Ventana() {
        super("Hotel Grand Horizon - Practica de Eventos");
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        // --- PANEL SUPERIOR ---
        JPanel panelNav = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelNav.setBackground(new Color(44, 62, 80));
        JButton btnNueva = new JButton("Nueva Reservación");
        JButton btnLista = new JButton("Lista de Huéspedes");

        //Parte asignada a Guillermo
        //Agregar un listener para cambiar entre paneles si usas CardLayout
        
        panelNav.add(btnNueva);
        panelNav.add(btnLista);
        add(panelNav, BorderLayout.NORTH);

        // --- PANEL CENTRAL ---
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));
        panelCentral.setBackground(new Color(236, 240, 241));

        // Sub-panel Izquierdo
        JPanel panelIzq = new JPanel();
        panelIzq.setLayout(new BoxLayout(panelIzq, BoxLayout.Y_AXIS));
        panelIzq.setBackground(new Color(52, 152, 219));
        panelIzq.setBorder(new TitledBorder("Detalles de Estancia"));

        txtNombre = new JTextField();
        txtNombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        comboHabitacion = new JComboBox<>(new String[]{"Piso 1 - Jardín", "Piso 2 - Mar", "Piso 3 - VIP"});
        comboHabitacion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Parte asignada a: Tania Elias
        // Agregar un ActionListener a 'comboHabitacion' para cambiar la imagen
        // comboHabitacion.addActionListener(e -> { ... });

        lblImagenUbicacion = new JLabel("Imagen aquí");
        lblImagenUbicacion.setPreferredSize(new Dimension(150, 100));
        lblImagenUbicacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblImagenUbicacion.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        rbSencilla = new JRadioButton("Sencilla", true);
        rbDoble = new JRadioButton("Doble");
        rbSuite = new JRadioButton("Suite");
        rbPresidencial = new JRadioButton("Presidencial");
        ButtonGroup grupoHab = new ButtonGroup();
        grupoHab.add(rbSencilla); grupoHab.add(rbDoble); grupoHab.add(rbSuite); grupoHab.add(rbPresidencial);

        cbDesayuno = new JCheckBox("Desayuno Buffet");
        cbWifi = new JCheckBox("WiFi Premium");
        cbSpa = new JCheckBox("Acceso al Spa");
        cbTransporte = new JCheckBox("Transporte Aeropuerto");

        panelIzq.add(new JLabel("Nombre del Huésped:"));
        panelIzq.add(txtNombre);
        panelIzq.add(Box.createVerticalStrut(5));
        panelIzq.add(new JLabel("Ubicación:"));
        panelIzq.add(comboHabitacion);
        panelIzq.add(Box.createVerticalStrut(10));
        panelIzq.add(lblImagenUbicacion);
        panelIzq.add(Box.createVerticalStrut(10));
        panelIzq.add(new JLabel("Tipo de Habitación:"));
        panelIzq.add(rbSencilla); panelIzq.add(rbDoble); panelIzq.add(rbSuite); panelIzq.add(rbPresidencial);
        panelIzq.add(Box.createVerticalStrut(5));
        panelIzq.add(new JLabel("Servicios Extra:"));
        panelIzq.add(cbDesayuno); panelIzq.add(cbWifi); panelIzq.add(cbSpa); panelIzq.add(cbTransporte);

        // Sub-panel Derecho
        JPanel panelDer = new JPanel(new BorderLayout());
        areaResumen = new JTextArea();
        areaResumen.setEditable(false);
        lblCosto = new JLabel("Total: $0.00 MXN", SwingConstants.CENTER);
        lblCosto.setFont(new Font("Arial", Font.BOLD, 18));
        lblCosto.setOpaque(true);
        lblCosto.setBackground(new Color(39, 174, 96));
        lblCosto.setForeground(Color.WHITE);
        panelDer.add(lblCosto, BorderLayout.NORTH);
        panelDer.add(new JScrollPane(areaResumen), BorderLayout.CENTER);

        // Botones de acción
        JPanel panelBtns = new JPanel(new FlowLayout());
        JButton btnCalcular = new JButton("Calcular (Alt+C)");
        JButton btnRegistrar = new JButton("Registrar");
        btnCalcular.setMnemonic(KeyEvent.VK_C);

        //Parte asignada a: Luis Torres
        // Agregar el evento para calcular el costo
        // btnCalcular.addActionListener(e -> { llamar a metodo calcular });

        // Parte asignada a: Karol
        // Agregar el evento para guardar en la tabla
        btnRegistrar.addActionListener(e -> registrarReserva());

        panelBtns.add(btnCalcular);
        panelBtns.add(btnRegistrar);

        JPanel pContenedor = new JPanel(new BorderLayout(10,10));
        pContenedor.add(panelCentral, BorderLayout.CENTER);
        pContenedor.add(panelBtns, BorderLayout.SOUTH);

        add(pContenedor, BorderLayout.CENTER);
        panelCentral.add(panelIzq);
        panelCentral.add(panelDer);
    }

    // --- MÉTODOS PARA RELLENAR ---

    //Parte asignada a: Luis Torres
    private void calcularCosto() {
        // Aquí debes usar if/else para checar qué RadioButton está seleccionado
        // y sumar los Checkboxes
    }

    // Parte asignada a: Santiago Chacon
    private void registrarReserva() {
        // Aquí debes obtener el texto de txtNombre y los datos de los componentes
        // para agregarlos a un DefaultTableModel
    }
}
