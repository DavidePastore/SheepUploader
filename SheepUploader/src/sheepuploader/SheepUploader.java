package sheepuploader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTable;

/**
 * @author <a reef="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class SheepUploader extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6371424360886557840L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public SheepUploader() {
		setTitle("Sheep Uploader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		table = new JTable();
		panel.add(table);
	}

}
