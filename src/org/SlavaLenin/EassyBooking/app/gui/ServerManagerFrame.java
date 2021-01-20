package org.SlavaLenin.EassyBooking.app.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.SlavaLenin.EassyBooking.app.controller.ServerManagerController;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;

import java.awt.Toolkit;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.SystemColor;

public class ServerManagerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private ServerManagerController smcontroller;
	
	private JPanel contentPane;
	private JTextField textPassword;
	private JTextField textUsername;
	private JLabel lblUsernameActual, lblFlightNumber;
	private JTextField flightIdSearch;
	private DefaultListModel<String> flightModel = new DefaultListModel<String>();

	JLabel lblAirportDeparture, lblTotalSeats, lblRemainingSeats, lblDateDeparture;
	
	JLabel lblDateArrival, lblAirportArrival, lblAirline;
	
	Flight selectedFlight = null;
	
	/**
	 * Create the frame.
	 */
	public ServerManagerFrame(ServerManagerController smcontroller) {
		
		
		this.smcontroller = smcontroller;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\whiwho\\Documents\\GitHub\\EassyBooking\\img\\pengu2.png"));
		setTitle("EassyBooking Server Manager");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblServerName = new JLabel("EassyBooking Server Manager");
		lblServerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblServerName.setFont(new Font("Dubai", Font.BOLD, 18));
		contentPane.add(lblServerName, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBackground(SystemColor.menu);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel LoginPanel = new JPanel();
		tabbedPane.addTab("Login", null, LoginPanel, null);
		LoginPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel LobinButtonPanel = new JPanel();
		LobinButtonPanel.setBackground(new Color(255, 250, 250));
		LoginPanel.add(LobinButtonPanel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDTO user = smcontroller.login(textUsername.getText(), textPassword.getText());
				if(user == null) {
					lblUsernameActual.setText("%None%");
				}else {
					lblUsernameActual.setText(user.getUsername());
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("REgister");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDTO user = smcontroller.register(textUsername.getText(), textPassword.getText());
				if(user == null) {
					lblUsernameActual.setText("%None%");
				}else {
					lblUsernameActual.setText(user.getUsername());
				}
			}
		});
		LobinButtonPanel.add(btnNewButton_1);
		LobinButtonPanel.add(btnNewButton);
		
		JPanel LoginFieldsPanel = new JPanel();
		LoginPanel.add(LoginFieldsPanel, BorderLayout.CENTER);
		LoginFieldsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 250, 250));
		LoginFieldsPanel.add(panel_5);
		
		JLabel lblUserName = new JLabel("Username");
		panel_5.add(lblUserName);
		
		textUsername = new JTextField();
		panel_5.add(textUsername);
		textUsername.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 250, 250));
		LoginFieldsPanel.add(panel_6);
		
		JLabel lblPassword = new JLabel("Password");
		panel_6.add(lblPassword);
		
		textPassword = new JTextField();
		panel_6.add(textPassword);
		textPassword.setColumns(10);
		
		JPanel FlightPanel = new JPanel();
		tabbedPane.addTab("Flights", null, FlightPanel, null);
		FlightPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel FlightCenterPanel = new JPanel();
		FlightPanel.add(FlightCenterPanel);
		FlightCenterPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JList<String> FlightList = new JList<String>(flightModel);
		FlightList.setBorder(null);
		FlightCenterPanel.add(FlightList);

		FlightList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
            	System.out.println("Changeing the value of JList");
                Flight f = smcontroller.getFlightFromSearch(FlightList.getSelectedIndex());
                selectedFlight = f;
                lblFlightNumber.setText(String.valueOf(f.getFlightNumber()));
                lblTotalSeats.setText(String.valueOf(f.getTotalSeats()));
                lblRemainingSeats.setText(String.valueOf(f.getNumberRemainingSeats()));
                lblDateDeparture.setText(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(f.getDateDeparture()));
                lblAirportDeparture.setText(DBManager.getInstance().getAirport(f.getAirportDeparture()).getName());
                lblDateArrival.setText(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(f.getDateArrival()));
                lblAirportArrival.setText(DBManager.getInstance().getAirport(f.getAirportArrival()).getName());
                System.out.println("Cambiando el airline number" + f.getAirline());
            	lblAirline.setText(DBManager.getInstance().getAirline(AirlineEnum.getEnum(f.getAirline())).getName());
            	
            	
            }
        });
		JPanel FlightInfoPanel = new JPanel();
		FlightInfoPanel.setBorder(new MatteBorder(0, 1, 0, 0, new Color(0, 0, 0)));
		FlightCenterPanel.add(FlightInfoPanel);
		FlightInfoPanel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel FlightInfoFlightDataPanel = new JPanel();
		FlightInfoPanel.add(FlightInfoFlightDataPanel);
		FlightInfoFlightDataPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel FlightInfoFlightNumberPanel = new JPanel();
		FlightInfoFlightNumberPanel.setBackground(new Color(255, 250, 250));
		FlightInfoFlightDataPanel.add(FlightInfoFlightNumberPanel);
		
		JLabel lblFlightNumberInfo = new JLabel("Flightnumber:");
		FlightInfoFlightNumberPanel.add(lblFlightNumberInfo);
		
		lblFlightNumber = new JLabel("");
		FlightInfoFlightNumberPanel.add(lblFlightNumber);
		
		JPanel FlightInfoSeatsPanel = new JPanel();
		FlightInfoFlightDataPanel.add(FlightInfoSeatsPanel);
		FlightInfoSeatsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel FlightInfoTotalSeatsPanel = new JPanel();
		FlightInfoTotalSeatsPanel.setBackground(new Color(255, 250, 250));
		FlightInfoSeatsPanel.add(FlightInfoTotalSeatsPanel);
		
		JLabel lblTotalSeatsInfo = new JLabel("Seats:");
		FlightInfoTotalSeatsPanel.add(lblTotalSeatsInfo);
		
		lblTotalSeats = new JLabel("");
		FlightInfoTotalSeatsPanel.add(lblTotalSeats);
		
		JPanel FlightInfoRemainingSeatsPanel = new JPanel();
		FlightInfoRemainingSeatsPanel.setBackground(new Color(255, 250, 250));
		FlightInfoSeatsPanel.add(FlightInfoRemainingSeatsPanel);
		
		JLabel lblRemainingSeatsInfo = new JLabel("Remaining:");
		FlightInfoRemainingSeatsPanel.add(lblRemainingSeatsInfo);
		
		lblRemainingSeats = new JLabel("");
		FlightInfoRemainingSeatsPanel.add(lblRemainingSeats);
		
		JPanel FlightInfoDeparturePanel = new JPanel();
		FlightInfoDeparturePanel.setBorder(new MatteBorder(1, 0, 1, 0, new Color(0, 0, 0)));
		FlightInfoPanel.add(FlightInfoDeparturePanel);
		FlightInfoDeparturePanel.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel FlightInfoDepartureTitlePanel = new JPanel();
		FlightInfoDepartureTitlePanel.setBackground(new Color(255, 250, 250));
		FlightInfoDeparturePanel.add(FlightInfoDepartureTitlePanel);
		
		JLabel lblDepartureTitle = new JLabel("Departure");
		lblDepartureTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		FlightInfoDepartureTitlePanel.add(lblDepartureTitle);
		
		JPanel FlightInfoDateDeparturePanel = new JPanel();
		FlightInfoDateDeparturePanel.setBackground(new Color(255, 250, 250));
		FlightInfoDeparturePanel.add(FlightInfoDateDeparturePanel);
		
		JLabel lblDateDepartureInfo = new JLabel("Date:");
		FlightInfoDateDeparturePanel.add(lblDateDepartureInfo);
		
		lblDateDeparture = new JLabel(" ");
		FlightInfoDateDeparturePanel.add(lblDateDeparture);
		
		JPanel FlightInfoAirportDeparturePanel = new JPanel();
		FlightInfoAirportDeparturePanel.setBackground(new Color(255, 250, 250));
		FlightInfoDeparturePanel.add(FlightInfoAirportDeparturePanel);
		
		JLabel lblAirportDepartureInfo = new JLabel("Airport:");
		FlightInfoAirportDeparturePanel.add(lblAirportDepartureInfo);
		
		lblAirportDeparture = new JLabel("");
		FlightInfoAirportDeparturePanel.add(lblAirportDeparture);
		
		JPanel FlightInfoArrivalPanel = new JPanel();
		FlightInfoArrivalPanel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		FlightInfoPanel.add(FlightInfoArrivalPanel);
		FlightInfoArrivalPanel.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel FlightInfoArrivalTitlePanel = new JPanel();
		FlightInfoArrivalTitlePanel.setBackground(new Color(255, 250, 250));
		FlightInfoArrivalPanel.add(FlightInfoArrivalTitlePanel);
		
		JLabel FlightInfoArrivalTitle = new JLabel("Arrival");
		FlightInfoArrivalTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		FlightInfoArrivalTitlePanel.add(FlightInfoArrivalTitle);
		
		JPanel FlightInfoDateArrivalPanel = new JPanel();
		FlightInfoDateArrivalPanel.setBackground(new Color(255, 250, 250));
		FlightInfoArrivalPanel.add(FlightInfoDateArrivalPanel);
		
		JLabel lblDateArrivalInfo = new JLabel("Date:");
		FlightInfoDateArrivalPanel.add(lblDateArrivalInfo);
		
		lblDateArrival = new JLabel("");
		FlightInfoDateArrivalPanel.add(lblDateArrival);
		
		JPanel FlightInfoAirportArrivalPanel = new JPanel();
		FlightInfoAirportArrivalPanel.setBackground(new Color(255, 250, 250));
		FlightInfoArrivalPanel.add(FlightInfoAirportArrivalPanel);
		
		JLabel lblAirportArrivalInfo = new JLabel("Airport:");
		FlightInfoAirportArrivalPanel.add(lblAirportArrivalInfo);
		
		lblAirportArrival = new JLabel("");
		FlightInfoAirportArrivalPanel.add(lblAirportArrival);
		
		JPanel FlightInfoAirlinePanel = new JPanel();
		FlightInfoPanel.add(FlightInfoAirlinePanel);
		FlightInfoAirlinePanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel FlightInfoAirlineInfoPanel = new JPanel();
		FlightInfoAirlineInfoPanel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0)));
		FlightInfoAirlineInfoPanel.setBackground(new Color(255, 250, 250));
		FlightInfoAirlinePanel.add(FlightInfoAirlineInfoPanel);
		
		lblAirline = new JLabel("Airline");
		FlightInfoAirlineInfoPanel.add(lblAirline);
		
		JButton btnReserval = new JButton("Reservar");
		btnReserval.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Boton Reserbar");
				Flight f = smcontroller.getFlightFromSearch(FlightList.getSelectedIndex());
				System.out.println(f);
				System.out.println("La reserva sera de " + f.getFlightID());
				smcontroller.bookFlight(String.valueOf(f.getFlightID()));
			}
		});
		btnReserval.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnReserval.setForeground(Color.WHITE);
		btnReserval.setBackground(SystemColor.textHighlight);
		FlightInfoAirlinePanel.add(btnReserval);
		
		JPanel FlightSearchPanel = new JPanel();
		FlightPanel.add(FlightSearchPanel, BorderLayout.NORTH);
		FlightSearchPanel.setLayout(new BorderLayout(0, 0));
		
		flightIdSearch = new JTextField();
		FlightSearchPanel.add(flightIdSearch);
		flightIdSearch.setColumns(10);
		
		JButton btnSearchFlight = new JButton("Buscar");
		btnSearchFlight.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnSearchFlight.setForeground(Color.WHITE);
		btnSearchFlight.setBackground(SystemColor.textHighlight);
		btnSearchFlight.addActionListener(new ActionListener() {
			@Override
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
		serverInfoPanel.setBackground(Color.WHITE);
		contentPane.add(serverInfoPanel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Actual User:");
		serverInfoPanel.add(lblNewLabel);
		
		lblUsernameActual = new JLabel("None");
		serverInfoPanel.add(lblUsernameActual);
		setVisible(true);
	}

}
