/**
 * 
 */
package sheepuploader.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.Logger;

import sheepuploader.lists.FileSizeLimit;
import sheepuploader.lists.Host;
import sheepuploader.lists.HostsList;
import sheepuploader.log.SULogger;

/**
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class ListDialog extends JDialog {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5521253196879249927L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Logger logger = SULogger.getLogger();

	/**
	 * Create the dialog.
	 */
	public ListDialog(JFrame parent) {
		super(parent);
		setModal(true);
		setFocusTraversalPolicyProvider(true);
		setTitle("Hosts list");
		setBounds(100, 100, 705, 384);
		setLocationRelativeTo(parent);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Host", "Address", "Supported", "Login Support", "Private file support", "TOS address" 
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		addRows();
		setVisible(true);
	}
	
	/**
	 * This method add the rows to the JTable, with all the hosts.
	 */
	private void addRows(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Host host;
		FileSizeLimit fsl;
		logger.debug("addRows called");
		for(int i = 0; i < HostsList.HostsList.size(); i++){
			host = HostsList.HostsList.get(i);
			fsl = host.getFileSizeLimit();
			model.addRow(new Object[]{host.getName(), host.getAddress(), host.getSupported(), host.getLoginSupport(), host.getPrivateSupport(), host.getTOSAddress()});
		}
	}

}
