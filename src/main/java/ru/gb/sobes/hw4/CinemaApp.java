package ru.gb.sobes.hw4;

public class CinemaApp {
    public static void main(String[] args) {
        CinemaDB cinemaDB = new CinemaDB();
        cinemaDB.findSessionsMistakesOrderedByStartTime();
        System.out.println();
        cinemaDB.findBreaksBeetwenShowtimesMoreThan(30);
        System.out.println();
        cinemaDB.findFilmsSumTicketsAndAvgTicketsByShowAndTotalProfit();
        System.out.println();
        cinemaDB.fintdTotalVisitorsByTimePeriod(9, 15);
        cinemaDB.fintdTotalVisitorsByTimePeriod(15, 18);
        cinemaDB.fintdTotalVisitorsByTimePeriod(18, 21);
    }
}
