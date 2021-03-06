package GUI;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Logic.Cliente.Cliente;
import Logic.Cliente.Observer;
import Logic.Mensajes.M_Compartir_Archivo;
import Logic.Mensajes.M_PedirFichero;

public class ClientWindow extends JFrame implements Observer {

	
	private Cliente cliente;
	private JFileChooser fileChooser;
	private TM_DownloadFiles TM_download;
    private TM_MyFiles TM_myFiles;  
    private TM_Historial TM_historial;
	
    /**
     * Creates new form NewJFrame
     */
    public ClientWindow(Cliente cliente) {
		super("Cliente");
    	this.cliente = cliente;
    	cliente.addObserver(this);
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(""));
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
    	
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);;

        mainPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DownloadTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        MyFileTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        HistorialTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        UserTable = new javax.swing.JTable();
        ActButton = new javax.swing.JButton();
        DownloadButton = new javax.swing.JButton();
        UploadButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        HistorialLabel = new javax.swing.JLabel();
        
        TM_download = new TM_DownloadFiles(cliente);
        TM_myFiles = new TM_MyFiles(cliente);   
        TM_historial = new TM_Historial(cliente);
        

        setBackground(new java.awt.Color(102, 153, 0));
        setPreferredSize(new java.awt.Dimension(1134, 675));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setOpaque(false);

        DownloadTable.setModel(TM_download);
        DownloadTable.setGridColor(new java.awt.Color(0, 0, 0));
        DownloadTable.setName(""); // NOI18N
        DownloadTable.setOpaque(false);
        jScrollPane2.setViewportView(DownloadTable);

        MyFileTable.setModel(TM_myFiles);
        MyFileTable.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(MyFileTable);

        HistorialTable.setModel(TM_historial);

        HistorialTable.setDefaultRenderer(Object.class, new HistorialRenderer());
        jScrollPane4.setViewportView(HistorialTable);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        UserTable.setModel(new TM_Usuarios(cliente));
        UserTable.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(UserTable);

        ActButton.setText("Actualizar");
        ActButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActButtonActionPerformed(evt);
            }
        });

        DownloadButton.setText("Descargar");
        DownloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DownloadButtonActionPerformed(evt);
            }
        });

        UploadButton.setText("Subir");
        UploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadButtonActionPerformed(evt);
            }
        });

        DeleteButton.setText("Eliminar");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        HistorialLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        HistorialLabel.setText("Historial de descargas");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ActButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addGap(257, 257, 257)
                                .addComponent(DownloadButton)
                                .addGap(293, 293, 293)
                                .addComponent(UploadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)))))
                .addGap(60, 60, 60))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(HistorialLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ActButton)
                    .addComponent(DownloadButton)
                    .addComponent(UploadButton)
                    .addComponent(DeleteButton))
                .addGap(18, 18, 18)
                .addComponent(HistorialLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent windowEvent) {
            	SwingUtilities.invokeLater(new Runnable() {
        			public void run () {
        				cliente.desconectaDelServidor();
        			}
        		});
            }
        });
        
        
        this.setLocation(400, 200);
        this.setResizable(false);
        this.setVisible(true);
        pack();  
    }// </editor-fold>                        

    private void DownloadButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    	int[] rows  = DownloadTable.getSelectedRows();
    	
    	for (int i = 0; i < rows.length; i++) {
    		String archivo, usuarioDestino;
    		usuarioDestino = (String) DownloadTable.getValueAt(rows[i], 0);
    		archivo = TM_download.getFullPath(rows[i]);
    		cliente.descargarArchivo(usuarioDestino, archivo, TM_historial.getNextIdDescarga());
    		try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	cliente.actualizaGUI();
    }                                              

    private void ActButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	cliente.actualizaGUI();
    }                                         

    private void UploadButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	int selection = fileChooser.showOpenDialog(this);
		if (selection == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if (file.isFile()) cliente.compartirArchivo(file.getAbsolutePath());
		}
    	cliente.actualizaGUI();
    }                                            

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    	int[] rows  = MyFileTable.getSelectedRows();
    	
    	for (int i = 0; i < rows.length; i++) {
    		String archivo;
    		archivo = TM_myFiles.getFullPath(rows[i]);
    		cliente.eliminarArchivo(archivo);
    		try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	cliente.actualizaGUI();
    }   

	@Override
	public void onRegister() {}
	@Override
	public void onUpdateUsuarios(ArrayList<String> listusuarios) {}	
	@Override
	public void onUpdateArchivos(ArrayList<ArrayList<String>> listaArchivos) {}
	@Override
	public void onUpload() {}
	@Override
	public void onDelete() {}
	@Override
	public void onStartDownload(int id, String propietario, String file) {}
	@Override
	public void onPackageRecieve(int idDescarga, double progreso) {}
	@Override
	public void onFinishDownload(int id) {}	
	@Override
	public void onTransmisionError(int idDescarga) {}
	
	@Override
	public void onException(String exMsg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run () {
				JOptionPane.showMessageDialog(null, exMsg, "Exception", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

    // Variables declaration - do not modify                     
    private javax.swing.JButton ActButton;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton DownloadButton;
    private javax.swing.JTable DownloadTable;
    private javax.swing.JLabel HistorialLabel;
    private javax.swing.JTable HistorialTable;
    private javax.swing.JTable MyFileTable;
    private javax.swing.JButton UploadButton;
    private javax.swing.JTable UserTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration           




}
