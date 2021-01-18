package org.SlavaLenin.EassyBooking.app.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.SlavaLenin.EassyBooking.app.controller.ServerManagerController;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;

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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class ServerManagerFrame extends JFrame {

	private final static Logger LOGGER = Logger.getLogger(ServerManagerFrame.class.getName());
	
	private ServerManagerController smcontroller;
	
	private JPanel contentPane;
	private JTextField textPassword;
	private JTextField textUsername;
	private JLabel lblUsernameActual, lblFlightNumber;
	private JTextField textField;
	private JTextField flightIdSearch;
	private DefaultListModel<String> flightModel = new DefaultListModel<String>();

	JLabel lblAirportDeparture, lblTotalSeats, lblRemainingSeats, lblDateDeparture;
	
	JLabel lblAirline, lblDateArrival, lblAirportArrival;
	
	Flight selectedFlight = null;
	
	/**
	 * Create the frame.
	 */
	public ServerManagerFrame(ServerManagerController smcontroller) {
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("log/eassybooking/eassybookingserver");
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		LOGGER.addHandler(fileHandler);
		
		this.smcontroller = smcontroller;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\whiwho\\Documents\\GitHub\\EassyBooking\\img\\pengu2.png"));
		setTitle("EassyBooking Server");
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
                selectedFlight = f;
                lblFlightNumber.setText(String.valueOf(f.getFlightNumber()));
                
                lblTotalSeats.setText(String.valueOf(f.getTotalSeats()));
                lblRemainingSeats.setText(String.valueOf(f.getNumberRemainingSeats()));
                
                lblDateDeparture.setText(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(f.getDateDeparture()));
                // TODO Airport Functions
                lblAirportDeparture.setText(DBManager.getInstance().getAirport(f.getAirportDeparture()).getName());
                
                lblDateArrival.setText(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(f.getDateArrival()));
                lblAirportArrival.setText(DBManager.getInstance().getAirport(f.getAirportArrival()).getName());
            	
            	lblAirline.setText(DBManager.getInstance().getAirline(f.getAirline()).getName());
            	
                
            }
        });
		JPanel FlightInfoPanel = new JPanel();
		FlightCenterPanel.add(FlightInfoPanel);
		FlightInfoPanel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel FlightInfoFlightDataPanel = new JPanel();
		FlightInfoPanel.add(FlightInfoFlightDataPanel);
		FlightInfoFlightDataPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel FlightInfoFlightNumberPanel = new JPanel();
		FlightInfoFlightDataPanel.add(FlightInfoFlightNumberPanel);
		
		JLabel lblFlightNumberInfo = new JLabel("Flightnumber:");
		FlightInfoFlightNumberPanel.add(lblFlightNumberInfo);
		
		lblFlightNumber = new JLabel("");
		FlightInfoFlightNumberPanel.add(lblFlightNumber);
		
		JPanel FlightInfoSeatsPanel = new JPanel();
		FlightInfoFlightDataPanel.add(FlightInfoSeatsPanel);
		FlightInfoSeatsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel FlightInfoTotalSeatsPanel = new JPanel();
		FlightInfoSeatsPanel.add(FlightInfoTotalSeatsPanel);
		
		JLabel lblTotalSeatsInfo = new JLabel("Seats:");
		FlightInfoTotalSeatsPanel.add(lblTotalSeatsInfo);
		
		lblTotalSeats = new JLabel("");
		FlightInfoTotalSeatsPanel.add(lblTotalSeats);
		
		JPanel FlightInfoRemainingSeatsPanel = new JPanel();
		FlightInfoSeatsPanel.add(FlightInfoRemainingSeatsPanel);
		
		JLabel lblRemainingSeatsInfo = new JLabel("Remaining:");
		FlightInfoRemainingSeatsPanel.add(lblRemainingSeatsInfo);
		
		lblRemainingSeats = new JLabel("");
		FlightInfoRemainingSeatsPanel.add(lblRemainingSeats);
		
		JPanel FlightInfoDeparturePanel = new JPanel();
		FlightInfoDeparturePanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		FlightInfoPanel.add(FlightInfoDeparturePanel);
		FlightInfoDeparturePanel.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel FlightInfoDepartureTitlePanel = new JPanel();
		FlightInfoDeparturePanel.add(FlightInfoDepartureTitlePanel);
		
		JLabel lblDepartureTitle = new JLabel("Departure");
		lblDepartureTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		FlightInfoDepartureTitlePanel.add(lblDepartureTitle);
		
		JPanel FlightInfoDateDeparturePanel = new JPanel();
		FlightInfoDeparturePanel.add(FlightInfoDateDeparturePanel);
		
		JLabel lblDateDepartureInfo = new JLabel("Date:");
		FlightInfoDateDeparturePanel.add(lblDateDepartureInfo);
		
		lblDateDeparture = new JLabel(" ");
		FlightInfoDateDeparturePanel.add(lblDateDeparture);
		
		JPanel FlightInfoAirportDeparturePanel = new JPanel();
		FlightInfoDeparturePanel.add(FlightInfoAirportDeparturePanel);
		
		JLabel lblAirportDepartureInfo = new JLabel("Airport:");
		FlightInfoAirportDeparturePanel.add(lblAirportDepartureInfo);
		
		lblAirportDeparture = new JLabel("");
		FlightInfoAirportDeparturePanel.add(lblAirportDeparture);
		
		JPanel FlightInfoArrivalPanel = new JPanel();
		FlightInfoPanel.add(FlightInfoArrivalPanel);
		FlightInfoArrivalPanel.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel FlightInfoArrivalTitlePanel = new JPanel();
		FlightInfoArrivalPanel.add(FlightInfoArrivalTitlePanel);
		
		JLabel FlightInfoArrivalTitle = new JLabel("Arrival");
		FlightInfoArrivalTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		FlightInfoArrivalTitlePanel.add(FlightInfoArrivalTitle);
		
		JPanel FlightInfoDateArrivalPanel = new JPanel();
		FlightInfoArrivalPanel.add(FlightInfoDateArrivalPanel);
		
		JLabel lblDateArrivalInfo = new JLabel("Date:");
		FlightInfoDateArrivalPanel.add(lblDateArrivalInfo);
		
		lblDateArrival = new JLabel("");
		FlightInfoDateArrivalPanel.add(lblDateArrival);
		
		JPanel FlightInfoAirportArrivalPanel = new JPanel();
		FlightInfoArrivalPanel.add(FlightInfoAirportArrivalPanel);
		
		JLabel lblAirportArrivalInfo = new JLabel("Airport:");
		FlightInfoAirportArrivalPanel.add(lblAirportArrivalInfo);
		
		lblAirportArrival = new JLabel("");
		FlightInfoAirportArrivalPanel.add(lblAirportArrival);
		
		JPanel FlightInfoAirlinePanel = new JPanel();
		FlightInfoPanel.add(FlightInfoAirlinePanel);
		FlightInfoAirlinePanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel FlightInfoAirlineInfoPanel = new JPanel();
		FlightInfoAirlinePanel.add(FlightInfoAirlineInfoPanel);
		
		lblAirline = new JLabel("Airline");
		FlightInfoAirlineInfoPanel.add(lblAirline);
		
		JPanel FlightInfoButtonsPanel = new JPanel();
		FlightInfoAirlinePanel.add(FlightInfoButtonsPanel);
		
		JButton btnReserval = new JButton("Reservar");
		btnReserval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedFlight == null) 
					JOptionPane.showMessageDialog(new JFrame(), "You have no flight selected.");
				else
					smcontroller.bookFlight(String.valueOf(selectedFlight.getFlightNumber()), selectedFlight.getAirline());
			}
		});
		FlightInfoButtonsPanel.add(btnReserval);
		
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
