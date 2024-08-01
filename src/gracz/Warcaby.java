/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gracz;

/**
 * Klasa Warcaby jest klasą startową aplikacji warcabowej.
 * Tworzy nową instancję Plansza przy inicjalizacji.
 * 
 * Aplikacja uruchamia się poprzez utworzenie obiektu klasy Warcaby.
 * @author maxpe
 */

public class Warcaby {

    /**
    * Konstruktor klasy Warcaby.
    * Tworzy nową instancję Plansza.
    */
	public Warcaby() {
		new Plansza();
	}

	/**
        * Metoda main, startująca aplikację.
        * Tworzy nowy obiekt klasy Warcaby, inicjując tym samym grę.
        */
	public static void main(String args[]) {
		new Warcaby();
	}
}