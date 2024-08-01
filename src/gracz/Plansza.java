/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gracz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Klasa reprezentująca planszę do gry w warcaby.
 * Zawiera logikę gry, interakcje z użytkownikiem oraz implementację GUI.
 * 
 * @author maxpe
 * @version 1.0
 */
public class Plansza extends JFrame implements ActionListener {
	
	protected static int tablicaPionkow[][];
	private int szerokosc = 650;
	private int wysokosc = 600;
	private JButton local;
	private JButton wyjscie; 
	private JButton multiplayer;
	private JLabel napisGlowny;
	private JLabel napisWersja;
	private Plansza plansza;
	private Kafelek[][] kafelki;
	protected static Color kolorPlanszy1;
	protected static Color kolorPlanszy2;
	protected static Color kolorPionkow1;
	protected static Color kolorPionkow2;
	protected ServerSocket serverSocket;
	protected Socket socket;
	protected static ObjectInputStream ois;
	protected static ObjectOutputStream oos;
	protected static boolean multi;
	protected static boolean gra;

     /**
     * Konstruktor Plansza, inicjuje interfejs graficzny oraz pola planszy.
     */
        
	public Plansza() {
		super("Warcaby");
		tablicaPionkow = new int[8][8];
		kafelki = new Kafelek[8][8];
		multi = false;
		gra = false;

		setSize(szerokosc, wysokosc);
		setLocationRelativeTo(plansza);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		napisGlowny = new JLabel("Warcaby");
		napisGlowny.setBounds(535, 15, 140, 30);
		add(napisGlowny);

		napisWersja = new JLabel("Klient");
		napisWersja.setBounds(555, 55, 140, 20);
		napisWersja.setForeground(Color.RED);
		add(napisWersja);

		local = new JButton("Local");
		local.setBounds(530, 100, 100, 20);
		add(local);
		local.addActionListener(this);

		multiplayer = new JButton("Multiplayer");
		multiplayer.setBounds(530, 130, 100, 20);
		add(multiplayer);
		multiplayer.addActionListener(this);

		wyjscie = new JButton("Wyjście");
		wyjscie.setBounds(530, 190, 100, 20);
		add(wyjscie);
		wyjscie.addActionListener(this);

		kolorPlanszy1 = Color.BLACK;
                kolorPlanszy2 = Color.WHITE;
		kolorPionkow1 = Color.RED;
		kolorPionkow2 = Color.WHITE;
                
                for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				kafelki[i][j] = new Kafelek(this, i, j);
				add(kafelki[i][j]);
			}
		}
	
		setVisible(true);

	}

     /**
     * Metoda ustawiająca początkowe ustawienie pionków na planszy.
     * Ustawia pionki w odpowiednich polach dla rozpoczęcia gry.
     */
        
	protected void ustaw() {
		for (int j = 0; j < 8; j++)
			for (int i = 0; i < 8; i++) {
				tablicaPionkow[i][j] = 0;
			}
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 8; i++) {
				if ((i + j) % 2 == 0)
					tablicaPionkow[i][j] = 1;
			}

		for (int j = 5; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				if ((i + j) % 2 == 0)
					tablicaPionkow[i][j] = 2;
			}
		}

		gra = true;
	}

        
     /**
     * Metoda rysująca zawartość planszy.
     * Wykorzystuje obiekty Graphics do rysowania planszy oraz pionków.
     */
        
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(10, 40, 505, 505);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0)
					g2d.setColor(kolorPlanszy1);
				else
					g2d.setColor(kolorPlanszy2);
				g2d.fillRect(11 + 63 * i, 41 + 63 * j, 62, 62);
			}
		}
		for (int j = 0; j < 8; j++)
			for (int i = 0; i < 8; i++) {
				if (tablicaPionkow[i][j] == 3 || tablicaPionkow[i][j] == 4 || tablicaPionkow[i][j] == 5
						|| tablicaPionkow[i][j] == 8 || tablicaPionkow[i][j] == 9) {
					g2d.setColor(Color.YELLOW);
					g2d.drawRect(kafelki[i][j].x + 3, kafelki[i][j].y + 25, kafelki[i][j].width, kafelki[i][j].height);
				}
			}
		for (int j = 0; j < 8; j++)
			for (int i = 0; i < 8; i++) {
				if (tablicaPionkow[i][j] == 1 || tablicaPionkow[i][j] == 3 || tablicaPionkow[i][j] == 7
						|| tablicaPionkow[i][j] == 9)
					g2d.setColor(kolorPionkow1);
				else if (tablicaPionkow[i][j] == 2 || tablicaPionkow[i][j] == 4 || tablicaPionkow[i][j] == 6
						|| tablicaPionkow[i][j] == 8)
					g2d.setColor(kolorPionkow2);

				if (tablicaPionkow[i][j] == 1 || tablicaPionkow[i][j] == 2 || tablicaPionkow[i][j] == 3
						|| tablicaPionkow[i][j] == 4)
					g2d.fillOval(17 + 63 * i, 47 + 63 * j, 50, 50);
				if (tablicaPionkow[i][j] == 6 || tablicaPionkow[i][j] == 7 || tablicaPionkow[i][j] == 8
						|| tablicaPionkow[i][j] == 9)
					g2d.fillRect(17 + 63 * i, 47 + 63 * j, 50, 50);
			}

		g2d.setColor(Color.BLACK);


	}

        /**
        * Obsługuje zdarzenia akcji wywoływane przez interakcję użytkownika.
        * Steruje trybem gry, w zależności od wyboru gracza może on rozegrać
        * partię lokalnie na jednym komputerze, bądź połączyć się z kimś hostując
        * rozgrywkę (staje się on serwerem)
        */
        
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
                // Obsługa akcji przycisku "local"
		if (source == local) {
			ustaw();
			repaint();
		}
                // Obsługa akcji przycisku "multiplayer"
		if (source == multiplayer) {
			try {
                            // Nawiązanie połączenia przez gniazdo z lokalnym hostem na porcie 12345
                                socket = new Socket("localhost", 12345);
				JOptionPane.showMessageDialog(null, "Połączono z serwerem");
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				multi = true;
				ustaw();
				repaint();
                                // Uruchomienie nowego wątku do ciągłego odbierania aktualizacji od serwera
				new Thread() {
					public void run() {
						while (true) {
							try {
                                                            // Odczytanie obiektu z strumienia wejściowego (przyjmujemy, że to tablicaPionkow drugiego gracza)
								int tmp[][] = (int[][]) ois.readObject();
								if (!tmp.equals(tablicaPionkow)) {
									System.out.println("Zamieniono");
									tablicaPionkow = tmp;
									repaint();
								}
							} catch (ClassNotFoundException | IOException e) {
							}
						}
					}
				}.start();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Nie nawiązano połączenia");
			}
		}

                // Obsługa akcji przycisku "wyjscie"
		if (source == wyjscie) {
			int decyzja = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjść?", "Confirm Dialog",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if (decyzja == JOptionPane.YES_OPTION) {
				dispose();
				try {
                                    // Zamknięcie strumieni i gniazda
					ois.close();
					oos.close();
					socket.close();
					serverSocket.close();
				} catch (IOException e1) {

				}

			}
		}
	}
}