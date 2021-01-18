package org.SlavaLenin.EassyBooking.app.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.SlavaLenin.EassyBooking.app.controller.ServerManagerController;
import org.SlavaLenin.EassyBooking.app.data.Flight;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import java.awt.Toolkit;

public class ServerManagerFrame extends JFrame {

	private final static Logger LOGGER = Logger.getLogger(ServerManagerFrame.class.getName());
	
	private ServerManagerController smcontroller;
	
	private JPanel contentPane;
	private JTextField textPassword;
	private JTextField textUsername;
	private JLabel lblUsernameActual, lblFlightNumberInfo;
	private JTextField textField;
	private JTextField flightIdSearch;
	private DefaultListModel<String> flightModel = new DefaultListModel<String>();

	/**
	 * Create the frame.
	 */
	public ServerManagerFrame(ServerManagerController smcontroller) {
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("myLogFile");
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		LOGGER.addHandler(fileHandler);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\whiwho\\Documents\\GitHub\\EassyBooking\\img\\pengu2.png"));
		
		setTitle("EassyBooking Server");
		this.smcontroller = smcontroller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblServerName = new JLabel("EassyBooking Server");
		lblServerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblServerName, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel LoginPanel = new JPanel();
		tabbedPane.addTab("Login", null, LoginPanel, null);
		LoginPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		LoginPanel.add(panel_3, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = smcontroller.login(textUsername.getText(), textPassword.getText());
				if(user == null) {
					lblUsernameActual.setText("%None%");
				}else {
					lblUsernameActual.setText(user);
				}
			}
		});
		panel_3.add(btnNewButton);
		
		JPanel panel_4 = new JPanel();
		LoginPanel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		JLabel lblUserName = new JLabel("Username");
		panel_5.add(lblUserName);
		
		textUsername = new JTextField();
		panel_5.add(textUsername);
		textUsername.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		JLabel lblPassword = new JLabel("Password");
		panel_6.add(lblPassword);
		
		textPassword = new JTextField();
		panel_6.add(textPassword);
		textPassword.setColumns(10);
		
		JPanel PaymentPanel = new JPanel();
		tabbedPane.addTab("Pagos", null, PaymentPanel, null);
		PaymentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel SouthPaymentPanel = new JPanel();
		PaymentPanel.add(SouthPaymentPanel, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Make Payment");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		SouthPaymentPanel.add(btnNewButton_1);
		
		JPanel CenterPaymentPanel = new JPanel();
		PaymentPanel.add(CenterPaymentPanel, BorderLayout.CENTER);
		CenterPaymentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel OptionCenterPaymentPanel = new JPanel();
		CenterPaymentPanel.add(OptionCenterPaymentPanel, BorderLayout.NORTH);
		
		JRadioButton rdbtnPaypal = new JRadioButton("Paypal");
		OptionCenterPaymentPanel.add(rdbtnPaypal);
		
		JRadioButton rdbtnCreditCard = new JRadioButton("CreditCard");
		OptionCenterPaymentPanel.add(rdbtnCreditCard);
		
		JPanel InfoCenterPaymentPanel = new JPanel();
		CenterPaymentPanel.add(InfoCenterPaymentPanel, BorderLayout.CENTER);
		
		JLabel lblPayAmount = new JLabel("PaymentAmount");
		InfoCenterPaymentPanel.add(lblPayAmount);
		
		textField = new JTextField();
		InfoCenterPaymentPanel.add(textField);
		textField.setColumns(10);
		
		JPanel FlightPanel = new JPanel();
		tabbedPane.addTab("Vuelos", null, FlightPanel, null);
		FlightPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel FlightCenterPanel = new JPanel();
		FlightPanel.add(FlightCenterPanel);
		FlightCenterPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JList FlightList = new JList(flightModel);
		FlightCenterPanel.add(FlightList);

		FlightList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                Flight f = smcontroller.getFlightFromSearch(FlightList.getSelectedIndex());
                lblFlightNumberInfo.setText(String.valueOf(f.getFlightNumber()));
            }
        });
		JPanel FlightInfoPanel = new JPanel();
		FlightCenterPanel.add(FlightInfoPanel);
		FlightInfoPanel.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		FlightInfoPanel.add(panel_1);
		
		JLabel lblFlightNumber = new JLabel("Flightnumber:");
		panel_1.add(lblFlightNumber);
		
		lblFlightNumberInfo = new JLabel("_");
		panel_1.add(lblFlightNumberInfo);
		
		JPanel panel_2 = new JPanel();
		FlightInfoPanel.add(panel_2);
		
		JPanel panel_8 = new JPanel();
		FlightInfoPanel.add(panel_8);
		
		JPanel panel_7 = new JPanel();
		FlightInfoPanel.add(panel_7);
		
		JPanel panel_9 = new JPanel();
		FlightInfoPanel.add(panel_9);
		
		JPanel FlightSearchPanel = new JPanel();
		FlightPanel.add(FlightSearchPanel, BorderLayout.NORTH);
		FlightSearchPanel.setLayout(new BorderLayout(0, 0));
		
		flightIdSearch = new JTextField();
		FlightSearchPanel.add(flightIdSearch);
		flightIdSearch.setColumns(10);
		
		JButton btnSearchFlight = new JButton("Buscar");
		btnSearchFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logger.getLogger(ServerManagerFrame.class.getName()).info("Ha clickado el boton buscar");
				List<Flight> flights = smcontroller.searchFlight(flightIdSearch.getText());
				for(Flight f : flights) {
					flightModel.addElement(f.toString());
				}
			}
		});
		FlightSearchPanel.add(btnSearchFlight, BorderLayout.EAST);
		
		JPanel serverInfoPanel = new JPanel();
		contentPane.add(serverInfoPanel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Usuario Actual:");
		serverInfoPanel.add(lblNewLabel);
		
		lblUsernameActual = new JLabel("%None%");
		serverInfoPanel.add(lblUsernameActual);
		setVisible(true);
	}

}
