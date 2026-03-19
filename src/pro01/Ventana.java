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
    private DefaultTableModel modeloTabla;
    private JTable tablaHuespedes;

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
        btnCalcular.addActionListener(e -> calcularCosto());

        // Parte asignada a: Karol
        // Agregar el evento para guardar en la tabla
        // btnRegistrar.addActionListener(e -> { llamar a metodo guardar });

          btnRegistrar.addActionListener(e -> {
            registrarReserva();
            txtNombre.setText(""); 
            txtNombre.requestFocus();
        });
        
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
    costoTotal = 0;
    String resumen = "";

    // Nombre huesped
    resumen = resumen + "Huésped: " + txtNombre.getText() + "\n";
    
    //Ubicacion
    int seleccion = comboHabitacion.getSelectedIndex();
    
    if(seleccion==0){
        costoTotal = costoTotal + 200;
        resumen = resumen + "Ubicacion; Jardin: 200\n";
    }
    if(seleccion==1){
        costoTotal = costoTotal + 500;
        resumen = resumen + "Ubicacion; Mar: 500\n";
    }
    if(seleccion==2){
        costoTotal = costoTotal + 1500;
        resumen = resumen + "Ubicacion; VIP: 1500\n";
    }
    

    // Tipo de habitación
    if (rbSencilla.isSelected()) {
        costoTotal = costoTotal + 500;
        resumen = resumen + "Habitación Sencilla: 500\n";
    }
    if (rbDoble.isSelected()) {
        costoTotal = costoTotal + 800;
        resumen = resumen + "Habitación Doble: 800\n";
    }
    if (rbSuite.isSelected()) {
        costoTotal = costoTotal + 2500;
        resumen = resumen + "Habitación Suite: 2500\n";
    }
    if (rbPresidencial.isSelected()) {
        costoTotal = costoTotal + 5000;
        resumen = resumen + "Habitación Presidencial: 5000\n";
    }

    // Servicios
    if (cbDesayuno.isSelected()) {
        costoTotal = costoTotal + 350;
        resumen = resumen + "Desayuno Buffet: 350\n";
    }
    if (cbWifi.isSelected()) {
        costoTotal = costoTotal + 100;
        resumen = resumen + "WiFi Premium: 100\n";
    }
    if (cbSpa.isSelected()) {
        costoTotal = costoTotal + 600;
        resumen = resumen + "Acceso al Spa: 600\n";
    }
    if (cbTransporte.isSelected()) {
        costoTotal = costoTotal + 400;
        resumen = resumen + "Transporte Aeropuerto: 400\n";
    }

    // Resultados
    resumen = resumen + "Total: " + costoTotal + " MXN";

    
    areaResumen.setText(resumen);
    lblCosto.setText("Total: " + costoTotal + " MXN");
    }

    // Parte asignada a: Santiago Chacon
    private void registrarReserva() {
        // Aquí debes obtener el texto de txtNombre y los datos de los componentes
        // para agregarlos a un DefaultTableModel

         // 1. Obtener los datos de los componentes
    String nombre = txtNombre.getText().trim();
    String ubicacion = comboHabitacion.getSelectedItem().toString();
    
    // Obtenemos el costo y limpiamos el texto "Total: " para dejar solo el número
    String costo = lblCosto.getText().replace("Total: ", ""); 

    // 2. Determinar qué tipo de habitación se seleccionó
    String tipo = "";
    if (rbSencilla.isSelected()) tipo = "Sencilla";
    else if (rbDoble.isSelected()) tipo = "Doble";
    else if (rbSuite.isSelected()) tipo = "Suite";
    else if (rbPresidencial.isSelected()) tipo = "Presidencial";

    // 3. Validación de seguridad: si el nombre está vacío, no registra nada
    if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre del huésped.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 4. AGREGAR A LA TABLA (Aquí se usa la variable global que creamos)
    Object[] nuevaFila = {nombre, tipo, ubicacion, costo};
    modeloTabla.addRow(nuevaFila);

    // 5. Mensaje de éxito y limpieza del campo
    JOptionPane.showMessageDialog(this, "Reservación registrada para: " + nombre);
    txtNombre.setText(""); 
    }
}
