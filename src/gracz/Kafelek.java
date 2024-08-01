package gracz;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Klasa reprezentująca pojedynczy kafelek planszy do gry w warcaby.
 * Rozszerza JComponent i implementuje MouseListener, aby reagować na zdarzenia myszy.
 * Kafelek może reprezentować różne stany pionka lub pustego pola na planszy.
 * Klasa zawiera logikę obsługi kliknięć myszy do wykonania ruchów i bicie pionków.
 * Klasa jest częścią większego projektu gry w warcaby.
 * 
 * @author maxpe
 * @version 1.0
 */
public class Kafelek extends JComponent implements MouseListener {

    protected int x; // Współrzędna x kafelka na ekranie
    protected int y; // Współrzędna y kafelka na ekranie
    protected Integer width = 62; // Szerokość kafelka
    protected Integer height = 62; // Wysokość kafelka
    protected int _i; // Indeks i kafelka w tablicy planszy
    protected int _j; // Indeks j kafelka w tablicy planszy
    protected static JFrame parent; // Okno główne aplikacji
    protected static int iBicie; // Indeks i pionka do zbicia
    protected static int jBicie; // Indeks j pionka do zbicia

    /**
     * Konstruktor klasy Kafelek.
     * Inicjuje kafelek na podanej pozycji (i, j) na planszy.
     * Ustawia rozmiar i pozycję kafelka oraz dodaje MouseListener.
     */
    public Kafelek(JFrame f, int i, int j) {
        parent = f;
        x = 8 + 63 * i;
        y = 15 + 63 * j;
        _i = i;
        _j = j;
        setBounds(x, y, width, height);
        addMouseListener(this);
        setLayout(null);
        setDoubleBuffered(false);
        setVisible(true);
    }

    /**
     * Metoda obsługująca zdarzenie kliknięcia myszy.
     * W zależności od stanu planszy i klikniętego kafelka, wykonuje ruchy lub bicie pionków.
     * Aktualizuje stan planszy i wyświetla powiadomienia o zakończeniu gry.
     * 0 - puste pole 
     * 1 - czarny pionek
     * 2 - biały pionek 
     * 3 - wciśnięty czarny pionek
     * 4 - wciśnięty biały pionek
     * 5 - puste pole na które można wykonać legalny ruch
     * 6 - biała damka 
     * 7 - czarna damka
     * 8 - wciśnięta biała damka
     * 9 - wciśnięta czarna damka
     */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (Plansza.tablicaPionkow[_i][_j] != 5) {
			for (int j = 0; j < 8; j++)
				for (int i = 0; i < 8; i++) {
					if (Plansza.tablicaPionkow[i][j] == 3 || Plansza.tablicaPionkow[i][j] == 4 || Plansza.tablicaPionkow[i][j] == 8 || Plansza.tablicaPionkow[i][j] == 9) {
						Plansza.tablicaPionkow[i][j] -= 2;
					}
					if (Plansza.tablicaPionkow[i][j] == 5) {
						Plansza.tablicaPionkow[i][j] = 0;
					}
				}

			if (Plansza.tablicaPionkow[_i][_j] == 1 || Plansza.tablicaPionkow[_i][_j] == 2 || Plansza.tablicaPionkow[_i][_j] == 6 || Plansza.tablicaPionkow[_i][_j] == 7) {
				iBicie = -2;
				jBicie = -2;
				Plansza.tablicaPionkow[_i][_j] += 2;
				if (Plansza.tablicaPionkow[_i][_j] == 8 || Plansza.tablicaPionkow[_i][_j] == 9) {
					for (int i = 0; i < 4; i++) {
						int tmpX = _i;
						int tmpY = _j;
						while (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8) {
							if (i == 0) {
								tmpX--;
								tmpY--;
							} else if (i == 1) {
								tmpX--;
								tmpY++;
							} else if (i == 2) {
								tmpX++;
								tmpY++;
							} else if (i == 3) {
								tmpX++;
								tmpY--;
							}
							if (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8)
								if (Plansza.tablicaPionkow[tmpX][tmpY] == 0) {
									Plansza.tablicaPionkow[tmpX][tmpY] = 5;
								} else {
									if (((Plansza.tablicaPionkow[tmpX][tmpY] == 1 || Plansza.tablicaPionkow[tmpX][tmpY] == 7) && Plansza.tablicaPionkow[_i][_j] == 8)
											|| (Plansza.tablicaPionkow[tmpX][tmpY] == 2 || Plansza.tablicaPionkow[tmpX][tmpY] == 6) && Plansza.tablicaPionkow[_i][_j] == 9) {
										if (i == 0) {
											tmpX--;
											tmpY--;
										} else if (i == 1) {
											tmpX--;
											tmpY++;
										} else if (i == 2) {
											tmpX++;
											tmpY++;
										} else if (i == 3) {
											tmpX++;
											tmpY--;
										}
										if (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8)
											if (Plansza.tablicaPionkow[tmpX][tmpY] == 0) {
												Plansza.tablicaPionkow[tmpX][tmpY] = 5;
												if (i == 0) {
													tmpX++;
													tmpY++;
												} else if (i == 1) {
													tmpX++;
													tmpY--;
												} else if (i == 2) {
													tmpX--;
													tmpY--;
												} else if (i == 3) {
													tmpX--;
													tmpY++;
												}
												iBicie = tmpX;
												jBicie = tmpY;
											}
									}
									break;
								}
						}
					}
				}
				if (Plansza.tablicaPionkow[_i][_j] == 4)
				{
					if (_i != 0 && _i != 7) {
						if (Plansza.tablicaPionkow[_i - 1][_j - 1] == 0) {
							Plansza.tablicaPionkow[_i - 1][_j - 1] = 5;
						}
						if (Plansza.tablicaPionkow[_i + 1][_j - 1] == 0) {
							Plansza.tablicaPionkow[_i + 1][_j - 1] = 5;
						}

					} else if (_i == 0) {
						if (Plansza.tablicaPionkow[_i + 1][_j - 1] == 0)
							Plansza.tablicaPionkow[_i + 1][_j - 1] = 5;
					} else if (_i == 7) {
						if (Plansza.tablicaPionkow[_i - 1][_j - 1] == 0)
							Plansza.tablicaPionkow[_i - 1][_j - 1] = 5;
					}
					if (_j != 1)
						if (_i != 0 && _i != 1 && _i != 6 && _i != 7) {
							if (Plansza.tablicaPionkow[_i - 2][_j - 2] == 0 && (Plansza.tablicaPionkow[_i - 1][_j - 1] == 1 || Plansza.tablicaPionkow[_i - 1][_j - 1] == 7)) {
								Plansza.tablicaPionkow[_i - 2][_j - 2] = 5;
								iBicie = _i - 1;
								jBicie = _j - 1;
							}
							if (Plansza.tablicaPionkow[_i + 2][_j - 2] == 0 && (Plansza.tablicaPionkow[_i + 1][_j - 1] == 1 || Plansza.tablicaPionkow[_i - 1][_j - 1] == 7)) {
								Plansza.tablicaPionkow[_i + 2][_j - 2] = 5;
								iBicie = _i + 1;
								jBicie = _j - 1;
							}
						} else if (_i == 1 || _i == 0) {
							if (Plansza.tablicaPionkow[_i + 2][_j - 2] == 0 && (Plansza.tablicaPionkow[_i + 1][_j - 1] == 1 || Plansza.tablicaPionkow[_i + 1][_j - 1] == 7)) {
								Plansza.tablicaPionkow[_i + 2][_j - 2] = 5;
								iBicie = _i + 1;
								jBicie = _j - 1;
							}
						} else if (_i == 6 || _i == 7) {
							if (Plansza.tablicaPionkow[_i - 2][_j - 2] == 0 && (Plansza.tablicaPionkow[_i - 1][_j - 1] == 1 || Plansza.tablicaPionkow[_i - 1][_j - 1] == 7)) {
								Plansza.tablicaPionkow[_i - 2][_j - 2] = 5;
								iBicie = _i - 1;
								jBicie = _j - 1;
							}
						}
				}
				if (Plansza.tablicaPionkow[_i][_j] == 3) {
					iBicie = -2;
					jBicie = -2;
					if (_i != 0 && _i != 7) {
						if (Plansza.tablicaPionkow[_i - 1][_j + 1] == 0) {
							Plansza.tablicaPionkow[_i - 1][_j + 1] = 5;
						}
						if (Plansza.tablicaPionkow[_i + 1][_j + 1] == 0) {
							Plansza.tablicaPionkow[_i + 1][_j + 1] = 5;
						}
					} else if (_i == 0) {
						if (Plansza.tablicaPionkow[_i + 1][_j + 1] == 0)
							Plansza.tablicaPionkow[_i + 1][_j + 1] = 5;
					} else if (_i == 7) {
						if (Plansza.tablicaPionkow[_i - 1][_j + 1] == 0)
							Plansza.tablicaPionkow[_i - 1][_j + 1] = 5;
					}
					if (_j != 6) {
						if (_i != 0 && _i != 1 && _i != 6 && _i != 7) {

							if (Plansza.tablicaPionkow[_i - 2][_j + 2] == 0 && (Plansza.tablicaPionkow[_i - 1][_j + 1] == 2 || Plansza.tablicaPionkow[_i - 1][_j + 1] == 6)) {
								Plansza.tablicaPionkow[_i - 2][_j + 2] = 5;
								iBicie = _i - 1;
								jBicie = _j + 1;
							}
							if (Plansza.tablicaPionkow[_i + 2][_j + 2] == 0 && (Plansza.tablicaPionkow[_i + 1][_j + 1] == 2 || Plansza.tablicaPionkow[_i + 1][_j + 1] == 6)) {
								Plansza.tablicaPionkow[_i + 2][_j + 2] = 5;
								iBicie = _i + 1;
								jBicie = _j + 1;
							}
						} else if (_i == 1 || _i == 0) {
							if (Plansza.tablicaPionkow[_i + 2][_j + 2] == 0 && (Plansza.tablicaPionkow[_i + 1][_j + 1] == 2 || Plansza.tablicaPionkow[_i + 1][_j + 1] == 6)) {
								Plansza.tablicaPionkow[_i + 2][_j + 2] = 5;
								iBicie = _i + 1;
								jBicie = _j + 1;
							}
						} else if (_i == 6 || _i == 7) {
							if (Plansza.tablicaPionkow[_i - 2][_j + 2] == 0 && (Plansza.tablicaPionkow[_i - 1][_j + 1] == 2 || Plansza.tablicaPionkow[_i - 1][_j + 1] == 6)) {
								Plansza.tablicaPionkow[_i - 2][_j + 2] = 5;
								iBicie = _i - 1;
								jBicie = _j + 1;
							}
						}
					}
				}
			}
		}
		else {
			for (int j = 0; j < 8; j++)
				for (int i = 0; i < 8; i++) {
					if (Plansza.tablicaPionkow[i][j] == 4) {
						Plansza.tablicaPionkow[i][j] = 0;
						Plansza.tablicaPionkow[_i][_j] = 2;
					}
					if (Plansza.tablicaPionkow[i][j] == 3) {
						Plansza.tablicaPionkow[i][j] = 0;
						Plansza.tablicaPionkow[_i][_j] = 1;
					}
					if (Plansza.tablicaPionkow[i][j] == 8) {
						Plansza.tablicaPionkow[i][j] = 0;
						Plansza.tablicaPionkow[_i][_j] = 6;
					}
					if (Plansza.tablicaPionkow[i][j] == 9) {
						Plansza.tablicaPionkow[i][j] = 0;
						Plansza.tablicaPionkow[_i][_j] = 7;
					}
					if (Plansza.tablicaPionkow[i][j] == 5)
						Plansza.tablicaPionkow[i][j] = 0;
				}
			if ((_i == iBicie + 1 || _i == iBicie - 1) && (_j == jBicie - 1 || _j == jBicie + 1)) {
				Plansza.tablicaPionkow[iBicie][jBicie] = 0;
				iBicie = -2;
				jBicie = -2;
			}
			if (_j == 0 && Plansza.tablicaPionkow[_i][_j] == 2) {
				Plansza.tablicaPionkow[_i][_j] = 6;
			}
			if (_j == 7 && Plansza.tablicaPionkow[_i][_j] == 1) {
				Plansza.tablicaPionkow[_i][_j] = 7;
			}
			if (Plansza.multi) {
				try {
					Plansza.oos.writeObject(Plansza.tablicaPionkow);
					Plansza.oos.flush();
					Plansza.oos.reset();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (Plansza.gra) {
			int b = 0;
			int c = 0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (Plansza.tablicaPionkow[i][j] == 1 || Plansza.tablicaPionkow[i][j] == 3 || Plansza.tablicaPionkow[i][j] == 7 || Plansza.tablicaPionkow[i][j] == 9)
						c++;
					if (Plansza.tablicaPionkow[i][j] == 2 || Plansza.tablicaPionkow[i][j] == 4 || Plansza.tablicaPionkow[i][j] == 6 || Plansza.tablicaPionkow[i][j] == 8)
						b++;
				}
			}
			if (b == 0 || c == 0)
				JOptionPane.showMessageDialog(null, "Koniec gry!");
		}
		parent.repaint();
	}
        /**
     * Metoda interfejsu MouseListener. Nie jest implementowana.
     */
	@Override
	public void mouseExited(MouseEvent e) {}
        /**
     * Metoda interfejsu MouseListener. Nie jest implementowana.
     */
	@Override
	public void mousePressed(MouseEvent e) {}
        /**
     * Metoda interfejsu MouseListener. Nie jest implementowana.
     */
	@Override
	public void mouseReleased(MouseEvent e) {}
        /**
     * Metoda interfejsu MouseListener. Nie jest implementowana.
     */
        @Override
	public void mouseEntered(MouseEvent e) {}
}
