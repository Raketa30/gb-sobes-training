package ru.gb.sobes.hw4;

import java.sql.*;
import java.time.LocalTime;

public class CinemaDB {
    private final Connection connection;

    public CinemaDB() {
        this.connection = JdbcUtil.getConnection();
    }

    /*
        Ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
        Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность».
     */
    public void findSessionsMistakesOrderedByStartTime() {
        try (Statement statement = connection.createStatement()) {
            String query = " SELECT f.title , prev.start_at, f.length, nex.title, nex.start_at, f.length " +
                    "          FROM showtime AS prev " +
                    "               LEFT JOIN film f ON prev.film_id = f.id " +
                    "                    JOIN " +
                    "                        (SELECT s.id, f.title, s.start_at, s.end_at " +
                    "                           FROM showtime s" +
                    "                                LEFT JOIN film f on s.film_id = f.id) AS nex " +
                    "                                ON (nex.start_at BETWEEN prev.start_at AND prev.end_at) AND prev.id != nex.id " +
                    "        ORDER BY prev.start_at ";
            ResultSet result = statement.executeQuery(query);
            System.out.println("Mistakes in showtime scheldue");
            while (result.next()) {

                System.out.println(result.getString(1) + "    " + result.getTime(2) + "    " + result.getInt(3) + " <----> "
                        + result.getString(4) + "    " + result.getTime(5) + "    " + result.getInt(6));
            }

        } catch (SQLException throwables) {
            System.out.println("No found");
        }
    }

    /*
        Перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
        Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва».
     */
    public void findBreaksBeetwenShowtimesMoreThan(int minutes) {

        String query = "SELECT title, ss.start_at, ss.end_at, length, nex.start_at, DATE_PART('minute', nex.start_at - ss.end_at) AS br" +
                "  FROM showtime ss" +
                "       LEFT JOIN film f on ss.film_id = f.id" +
                "       JOIN (SELECT s.id - 1 AS id, s.start_at FROM showtime s) AS nex ON ss.id = nex.id" +
                " WHERE DATE_PART('minute', nex.start_at - ss.end_at) >= ?" +
                " ORDER BY br DESC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, minutes);
            ResultSet sessionSet = preparedStatement.executeQuery();
            System.out.println("Breaks between sowtimes");
            while (sessionSet.next()) {
                System.out.println(sessionSet.getString(1) + "   " + sessionSet.getTime(2) + "   " + sessionSet.getTime(3)
                        + "   " + sessionSet.getInt(4) + "   " + sessionSet.getTime(5) + "   " + sessionSet.getInt(6));
            }
        } catch (SQLException throwables) {
            System.out.println("No found");
        }
    }

    /*
        Cписок фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за
        сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
        Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу.
     */
    public void findFilmsSumTicketsAndAvgTicketsByShowAndTotalProfit() {
        try (Statement statement = connection.createStatement()) {
            String query = "(SELECT tbl.title," +
                    "        SUM(tbl.tickets_by_show)," +
                    "        AVG(tbl.tickets_by_show)," +
                    "        SUM(sum_by_show) AS total_by_film" +
                    " FROM (SELECT f.title," +
                    "              COUNT(t)     AS tickets_by_show," +
                    "              SUM(s.price) AS sum_by_show" +
                    "       FROM showtime s" +
                    "                JOIN film f ON f.id = s.film_id" +
                    "                JOIN ticket t ON s.id = t.session_id" +
                    "       GROUP BY f.title, s.id) tbl" +
                    " GROUP BY tbl.title" +
                    " ORDER BY total_by_film DESC)" +

                    "UNION ALL" +

                    "(SELECT 'Итого:'," +
                    "        SUM(tbl.tckts_by_show)," +
                    "        AVG(tbl.tckts_by_show)," +
                    "        SUM(sum_by_show)" +
                    " FROM (SELECT COUNT(t)           AS tckts_by_show," +
                    "              COUNT(t) * s.price AS sum_by_show" +
                    "       FROM showtime s" +
                    "                LEFT JOIN ticket t ON s.id = t.session_id" +
                    "       GROUP BY s.film_id, s.id) tbl)";
            ResultSet sessionSet = statement.executeQuery(query);

            System.out.println("Film sum tickets, avg tickets per show and total profit by show");
            while (sessionSet.next()) {
                System.out.println(sessionSet.getString(1) + "   " + sessionSet.getInt(2) + "   " + sessionSet.getFloat(3)
                        + "   " + sessionSet.getInt(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /*
       число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
       с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
    */
    public void fintdTotalVisitorsByTimePeriod(int from, int till) {
        String query = "SELECT CONCAT('from ', ?, ' till ', ?), " +
                "              SUM(tbl.tickts_count), " +
                "              SUM(tbl.total_profit)" +
                "         FROM (SELECT COUNT(t)     tickts_count," +
                "                      SUM(s.price) total_profit" +
                "               FROM showtime s" +
                "                    LEFT JOIN ticket t on s.id = t.session_id" +
                "               WHERE start_at >= ?" +
                "                     AND s.end_at <= ?" +
                "               GROUP BY s.start_at) tbl";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, from);
            preparedStatement.setInt(2, till);
            preparedStatement.setTime(3, Time.valueOf(LocalTime.of(from, 0)));
            preparedStatement.setTime(4, Time.valueOf(LocalTime.of(till, 0)));

            ResultSet sessionSet = preparedStatement.executeQuery();
            System.out.println("Total visitors and profit");
            while (sessionSet.next()) {
                System.out.println(sessionSet.getString(1) + "    " + sessionSet.getInt(2) + "    " + sessionSet.getInt(3));
            }
        } catch (SQLException throwables) {
            System.out.println("No found");
        }
    }

}
