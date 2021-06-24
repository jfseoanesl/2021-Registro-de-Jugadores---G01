package Vista;

import Modelo.Jugador;
import Negocio.GestionJugadores;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class VentanaRegistro extends JDialog {

    private JLabel lCedula, lNombre, lApellido, lPerfil, lPosicion, lAltura, lEdad, lValor;
    private JTextField tCedula, tNombre, tApellido, tAltura, tEdad, tValor;
    
    private JFormattedTextField  ftAltura, ftValor;
    
    private JSpinner sEdad;
    
    private JComboBox cPosicion;
    private JRadioButton rIzquierdo, rDerecho;
    private ButtonGroup grupoRadio;
    private JButton bGuardar, bCancelar, bNuevo, bBuscar, bEliminar;
    private JPanel panelDatos, panelBotones;
   
    private Container contenedor;

    private GestionJugadores gestor;

    public VentanaRegistro(JFrame frame, boolean bln) {
        super(frame, bln);
        this.gestor = new GestionJugadores();
        this.setTitle("Formulario Registro de Jugadores - V1");
        //this.setSize(400, 500);
        this.iniciarComponentes();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public void iniciarComponentes() {
        this.contenedor = this.getContentPane();
        this.contenedor.setLayout(new BorderLayout());
        this.iniciarPanelDatos();
        this.iniciarPanelBotones();

    }

    public void iniciarPanelDatos() {
        this.panelDatos = new JPanel();
        this.panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.panelDatos.setLayout(new GridLayout(9, 2, 5, 5));

        this.lCedula = new JLabel("No cedula: ");
        this.lNombre = new JLabel("Nombre: ");
        this.lApellido = new JLabel("Apellido: ");
        this.lPerfil = new JLabel("Perfil: ");
        this.lPosicion = new JLabel("Posicio: ");
        this.lEdad = new JLabel("Edad: ");
        this.lAltura = new JLabel("Altura: ");
        this.lValor = new JLabel("Valor: ");

        this.tCedula = new JTextField(null);
        this.tCedula.setEnabled(false);

        this.tNombre = new JTextField(null);
        this.tNombre.setEnabled(false);
        this.tApellido = new JTextField(null);
        this.tApellido.setEnabled(false);
        this.tEdad = new JTextField(null);
        this.tAltura = new JTextField(null);
        this.tValor = new JTextField(null);
        
        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(18, 7,100, 1);
        this.sEdad = new JSpinner();
        this.sEdad.setModel(modeloSpinner);
        this.sEdad.setEnabled(false);
        
        try {
            MaskFormatter mascara = new MaskFormatter("#.##");
            this.ftAltura = new JFormattedTextField(mascara);
            this.ftAltura.setValue("1.70");
            this.ftAltura.setEnabled(false);
        } catch (ParseException ex) {
            
        }
        
        NumberFormat formato = NumberFormat.getInstance();
        this.ftValor = new JFormattedTextField(formato);
        this.ftValor.setEnabled(false);
        
        this.cPosicion = new JComboBox();
        this.cPosicion.addItem("Portero");
        this.cPosicion.addItem("Defensa");
        this.cPosicion.addItem("Volante");
        this.cPosicion.addItem("Delantero");
        this.cPosicion.setEnabled(false);

        this.rIzquierdo = new JRadioButton("Izquierdo");
        this.rIzquierdo.setSelected(true);
        this.rIzquierdo.setEnabled(false);
        this.rDerecho = new JRadioButton("Derecho");
        this.rDerecho.setEnabled(false);
        this.grupoRadio = new ButtonGroup();
        this.grupoRadio.add(this.rIzquierdo);
        this.grupoRadio.add(this.rDerecho);
        

        this.bGuardar = new JButton("Guardar");
        this.bGuardar.addActionListener(new ClickBotonGuardar());
        this.bGuardar.setEnabled(false);
        this.bCancelar = new JButton("Cancelar");
        this.bCancelar.addActionListener(new ClickBotonCancelar());
        this.bCancelar.setEnabled(false);

        this.panelDatos.add(this.lCedula);
        this.panelDatos.add(this.tCedula);

        this.panelDatos.add(this.lNombre);
        this.panelDatos.add(this.tNombre);

        this.panelDatos.add(this.lApellido);
        this.panelDatos.add(this.tApellido);

        JPanel panelRadio = new JPanel();
        panelRadio.setLayout(new FlowLayout());

        panelRadio.add(this.rIzquierdo);
        panelRadio.add(this.rDerecho);

        this.panelDatos.add(this.lPerfil);
        this.panelDatos.add(panelRadio);

        this.panelDatos.add(this.lPosicion);
        this.panelDatos.add(this.cPosicion);

        this.panelDatos.add(this.lEdad);
        //this.panelDatos.add(this.tEdad);
        this.panelDatos.add(this.sEdad);

        this.panelDatos.add(this.lAltura);
        //this.panelDatos.add(this.tAltura);
        this.panelDatos.add(this.ftAltura);
        
        this.panelDatos.add(this.lValor);
        //this.panelDatos.add(this.tValor);
        this.panelDatos.add(this.ftValor);

        this.panelDatos.add(this.bGuardar);
        this.panelDatos.add(this.bCancelar);

        this.contenedor.add(this.panelDatos, BorderLayout.CENTER);

    }

    public void iniciarPanelBotones() {

        this.panelBotones = new JPanel();
        this.panelBotones.setLayout(new GridLayout(3, 1, 5, 5));

        this.bNuevo = new JButton("Nuevo");
        this.bNuevo.addActionListener(new clickBotonNuevo());

        this.bBuscar = new JButton("Buscar");
        this.bBuscar.addActionListener(new ClickBotonBuscar());
        this.bBuscar.setEnabled(false);
        this.bEliminar = new JButton("Eliminar");
        this.bEliminar.addActionListener(new ClickBotonEliminar());
        this.bEliminar.setEnabled(false);

        this.panelBotones.add(this.bNuevo);
        this.panelBotones.add(this.bBuscar);
        this.panelBotones.add(this.bEliminar);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10,10, 10, 10));
        panel.add(this.panelBotones);

        this.contenedor.add(panel, BorderLayout.EAST);

    }

    public void activarComponentes() {
        this.tCedula.setEnabled(true);
        this.tNombre.setEnabled(true);
        this.tApellido.setEnabled(true);
        this.cPosicion.setEnabled(true);
        this.rIzquierdo.setEnabled(true);
        this.rDerecho.setEnabled(true);
        this.sEdad.setEnabled(true);
        this.ftAltura.setEnabled(true);
        this.ftValor.setEnabled(true);
        this.bGuardar.setEnabled(true);
        this.bCancelar.setEnabled(true);
        this.bBuscar.setEnabled(true);
        //this.bEliminar.setEnabled(true);
        this.tCedula.grabFocus();
    }
    
    public void desactivarComponentes() {
        this.tCedula.setEnabled(false);
        this.tNombre.setEnabled(false);
        this.tApellido.setEnabled(false);
        this.cPosicion.setEnabled(false);
        this.rIzquierdo.setEnabled(false);
        this.rDerecho.setEnabled(false);
        this.sEdad.setEnabled(false);
        this.ftAltura.setEnabled(false);
        this.ftValor.setEnabled(false);
        this.bGuardar.setEnabled(false);
        this.bCancelar.setEnabled(false);
        this.bBuscar.setEnabled(false);
        this.bEliminar.setEnabled(false);
        
    }
    
    public void limpiarComponentes(){
        this.tCedula.setText(null);
        this.tNombre.setText(null);
        this.tApellido.setText(null);
        this.cPosicion.setSelectedIndex(0);
        this.rIzquierdo.setSelected(true);
        this.sEdad.setValue(18);
        this.ftAltura.setValue(null);
        this.ftValor.setValue(null);
    }

    public Jugador leerComponentes() {
      
            String cedula = this.tCedula.getText();
            String nombre = this.tNombre.getText();
            String apellido = this.tApellido.getText();
            String perfil = this.rDerecho.isSelected() ? "Derecho" : "Izquierdo";
            String posicion = this.cPosicion.getSelectedItem().toString();
            //double altura = Double.parseDouble(this.tAltura.getText());
            double altura = Double.parseDouble(this.ftAltura.getValue().toString());
            //int edad = Integer.parseInt(this.tEdad.getText());
            int edad = (int)this.sEdad.getValue();
           // double valor = Double.parseDouble(this.tValor.getText());
            Object objectValue = this.ftValor.getValue();
            double valor=Double.parseDouble(this.ftValor.getValue().toString());;
            
            Jugador jugador = new Jugador(cedula, nombre, apellido, posicion, perfil, altura, edad, valor);
            return jugador;
        

    }

    public void guardarJugador() {
        try {
            Jugador jugador = this.leerComponentes();
            this.gestor.registrarJugador(jugador);
            this.ventanaMsg("Datos guardados cone exito", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
            this.limpiarComponentes();
            this.tCedula.grabFocus();
        } catch (IOException | NumberFormatException | NullPointerException ex) {
            this.ventanaMsg(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
    
    public void actualizarValoresComponentes(Jugador jugador){
                this.tNombre.setText(jugador.getNombre());
                this.tApellido.setText(jugador.getApellido());
                this.cPosicion.setSelectedItem(this);
                if(jugador.getPerfil().equalsIgnoreCase("Izquierdo"))
                    this.rIzquierdo.setSelected(true);
                else
                    this.rDerecho.setSelected(true);
                this.sEdad.setValue(jugador.getEdad());
                this.ftAltura.setValue(jugador.getAltura());
                this.ftValor.setValue(jugador.getValor());
    }
    
    public void eliminarJugador(){
        try {
            String cedula = this.tCedula.getText();
            int confirma = JOptionPane.showConfirmDialog(this, "Desea confirmar la eliminacion del elemento", "Mensaje", JOptionPane.OK_CANCEL_OPTION);
            if(confirma==0){
               this.gestor.eliminarJugador(cedula);
               this.ventanaMsg("Datos eliminador con exito", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
               this.limpiarComponentes();
               this.bEliminar.setEnabled(false);
            }
        } catch (IOException ex) {
            this.limpiarComponentes();
            this.bEliminar.setEnabled(false);
            this.ventanaMsg(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void buscarJugador(){

        try {
            String cedula = this.tCedula.getText();
            Jugador jugador = this.gestor.buscarJugador(cedula);
            if(jugador!=null){
               this.actualizarValoresComponentes(jugador);
               //this.desactivarComponentes();
               this.bEliminar.setEnabled(true);
            }
            else{
                this.limpiarComponentes();
                this.bEliminar.setEnabled(true);
                this.tCedula.setText(cedula);
                this.bEliminar.setEnabled(false);
                this.ventanaMsg("El jugador no está registrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            this.limpiarComponentes();
            this.bEliminar.setEnabled(false);
            this.ventanaMsg(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ventanaMsg(String msg, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, msg, titulo, tipo);
    }

    class clickBotonNuevo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            activarComponentes();
        }
    }

    class ClickBotonGuardar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            guardarJugador();
        }

    }
    class ClickBotonCancelar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            limpiarComponentes();
            desactivarComponentes();
            tCedula.grabFocus();
        }

    }
    
    class ClickBotonBuscar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            buscarJugador();
        }

    }
    class ClickBotonEliminar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            eliminarJugador();
        }

    }

}
