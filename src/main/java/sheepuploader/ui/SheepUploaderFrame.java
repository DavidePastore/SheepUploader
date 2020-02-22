package sheepuploader.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

import sheepuploader.log.SULogger;
import sheepuploader.translations.Messages;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JMenuItem;

import org.apache.logging.log4j.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 * The main frame.
 */
public class SheepUploaderFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6371424360886557840L;
	private JTable afterUploadTable;
	private JTable uploadingTable;
	private JFileChooser fileChooser;
	private Logger logger = SULogger.getLogger();

	/**
	 * Create the frame.
	 */
	public SheepUploaderFrame(String version) {
		setTitle("Sheep Uploader "+version); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 520);
		FormLayout formLayout = new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow(5)"),},
			new RowSpec[] {
				RowSpec.decode("20px"),
				RowSpec.decode("148px:grow(2)"),});
		getContentPane().setLayout(formLayout);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		getContentPane().add(toolBar_1, "1, 1, fill, fill");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SheepUploaderFrame.this.dispose();
			}
		});
		
		JMenuItem mntmAddFiles = new JMenuItem("Add files...");
		mntmAddFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(fileChooser == null){
					//Init here
					fileChooser = new JFileChooser();
					fileChooser.setMultiSelectionEnabled(true);
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				}
				int status = fileChooser.showOpenDialog(SheepUploaderFrame.this);
				
				if (status == JFileChooser.APPROVE_OPTION) {
					File[] files = fileChooser.getSelectedFiles();
					
					//Here we can call a common method for this operations
					
					for(int i = 0; i < files.length; i++){
						logger.debug("File "+i+": "+files[i].getName());
					}
				}
				
			}
		});
		mnFile.add(mntmAddFiles);
		mnFile.add(mntmExit);
		
		JMenu mnView = new JMenu("View");
		mnView.setMnemonic(KeyEvent.VK_V);
		menuBar.add(mnView);
		
		JMenuItem mntmList = new JMenuItem("List");
		mntmList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new ListDialog(SheepUploaderFrame.this);
			}
		});
		mnView.add(mntmList);
		
		JButton btnStart = new JButton("Start");
		toolBar_1.add(btnStart);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, "1, 2, fill, fill");
		
		JPanel afterUploadPanel = new JPanel();
		splitPane.setRightComponent(afterUploadPanel);
		afterUploadPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		afterUploadPanel.add(scrollPane);
		
		afterUploadTable = new JTable();
		scrollPane.setRowHeaderView(afterUploadTable);
		
		JPanel uploadingPanel = new JPanel();
		splitPane.setLeftComponent(uploadingPanel);
		uploadingPanel.setLayout(new GridLayout(1, 1, 0, 0));
		
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.6);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(0, 0));
		uploadingPanel.add(scrollPane_1);
		
		uploadingTable = new JTable();
		scrollPane_1.setViewportView(uploadingTable);
		
		//scrollPane.setViewportView(uploadingTable);
		uploadingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		uploadingTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				Messages.getString("SheepUploaderFrame.filename"), Messages.getString("SheepUploaderFrame.filesize") , Messages.getString("SheepUploaderFrame.username"), Messages.getString("SheepUploaderFrame.host"), Messages.getString("SheepUploaderFrame.speed"), Messages.getString("SheepUploaderFrame.progress") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			}
		));
		
		uploadingTable.setDragEnabled(true);
		uploadingTable.setFillsViewportHeight(true);
		uploadingTable.setTransferHandler(new TransferHandler(){
		
			/**
			 * 
			 */
			private static final long serialVersionUID = -2131544179132487200L;

			public boolean canImport(TransferHandler.TransferSupport support){
				// we only import Files
                if (support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
	                //support.ge
                    return true;
                }
                return false;
			}
			
			
			public boolean importData(TransferHandler.TransferSupport support){
				if (!support.isDrop()) {
	                return false;
	            }
				
				// Get the fileList that is being dropped.
	            Transferable t = support.getTransferable();
	            List<File> data;
	            
	            try {
	                data = (List<File>)t.getTransferData(DataFlavor.javaFileListFlavor);
	            } catch (Exception e) {
	            	return false;
	            }
	            
	            //Call a new window with the choice of the hosts
	            
	            DefaultTableModel model = (DefaultTableModel) uploadingTable.getModel();
	            for (File file : data) {
	                model.addRow(new Object[]{file.getName(), file.length(), "utente", "Pingas.com", 13 });
	            }
	            return true;
			}
			
		});
		setVisible(true);
		
	}
	
	
	private void addRows(File[] files){
		
	}

}
