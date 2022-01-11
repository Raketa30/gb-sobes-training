--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9 (Debian 12.9-1.pgdg110+1)
-- Dumped by pg_dump version 12.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE IF EXISTS ONLY cinema.ticket DROP CONSTRAINT IF EXISTS session__fk;
ALTER TABLE IF EXISTS ONLY cinema.showtime DROP CONSTRAINT IF EXISTS film__fk;
DROP INDEX IF EXISTS cinema.session_start_at_uindex;
DROP INDEX IF EXISTS cinema.session_end_at_uindex;
ALTER TABLE IF EXISTS ONLY cinema.ticket DROP CONSTRAINT IF EXISTS ticket_pk;
ALTER TABLE IF EXISTS ONLY cinema.showtime DROP CONSTRAINT IF EXISTS session_pk;
ALTER TABLE IF EXISTS ONLY cinema.film DROP CONSTRAINT IF EXISTS film_pk;
ALTER TABLE IF EXISTS cinema.showtime ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS cinema.film ALTER COLUMN id DROP DEFAULT;
DROP TABLE IF EXISTS cinema.ticket;
DROP SEQUENCE IF EXISTS cinema.session_id_seq;
DROP TABLE IF EXISTS cinema.showtime;
DROP SEQUENCE IF EXISTS cinema.film_id_seq;
DROP TABLE IF EXISTS cinema.film;
SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: film; Type: TABLE; Schema: cinema; Owner: postgres
--

CREATE TABLE cinema.film (
                             id integer NOT NULL,
                             title character varying(50) NOT NULL,
                             length integer NOT NULL
);


ALTER TABLE cinema.film OWNER TO postgres;

--
-- Name: film_id_seq; Type: SEQUENCE; Schema: cinema; Owner: postgres
--

CREATE SEQUENCE cinema.film_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cinema.film_id_seq OWNER TO postgres;

--
-- Name: film_id_seq; Type: SEQUENCE OWNED BY; Schema: cinema; Owner: postgres
--

ALTER SEQUENCE cinema.film_id_seq OWNED BY cinema.film.id;


--
-- Name: showtime; Type: TABLE; Schema: cinema; Owner: postgres
--

CREATE TABLE cinema.showtime (
                                 id integer NOT NULL,
                                 show_date date NOT NULL,
                                 start_at time without time zone NOT NULL,
                                 end_at time without time zone NOT NULL,
                                 film_id integer NOT NULL,
                                 price integer NOT NULL
);


ALTER TABLE cinema.showtime OWNER TO postgres;

--
-- Name: session_id_seq; Type: SEQUENCE; Schema: cinema; Owner: postgres
--

CREATE SEQUENCE cinema.session_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cinema.session_id_seq OWNER TO postgres;

--
-- Name: session_id_seq; Type: SEQUENCE OWNED BY; Schema: cinema; Owner: postgres
--

ALTER SEQUENCE cinema.session_id_seq OWNED BY cinema.showtime.id;


--
-- Name: ticket; Type: TABLE; Schema: cinema; Owner: postgres
--

CREATE TABLE cinema.ticket (
                               seat integer NOT NULL,
                               session_id integer NOT NULL
);


ALTER TABLE cinema.ticket OWNER TO postgres;

--
-- Name: film id; Type: DEFAULT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.film ALTER COLUMN id SET DEFAULT nextval('cinema.film_id_seq'::regclass);


--
-- Name: showtime id; Type: DEFAULT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.showtime ALTER COLUMN id SET DEFAULT nextval('cinema.session_id_seq'::regclass);


--
-- Data for Name: film; Type: TABLE DATA; Schema: cinema; Owner: postgres
--

INSERT INTO cinema.film (id, title, length) VALUES (2, 'Фильм 2', 90);
INSERT INTO cinema.film (id, title, length) VALUES (4, 'Фильм 4', 60);
INSERT INTO cinema.film (id, title, length) VALUES (1, 'Фильм 1', 60);
INSERT INTO cinema.film (id, title, length) VALUES (3, 'Фильм 3', 120);


--
-- Data for Name: showtime; Type: TABLE DATA; Schema: cinema; Owner: postgres
--

INSERT INTO cinema.showtime (id, show_date, start_at, end_at, film_id, price) VALUES (12, '2022-01-08', '09:00:00', '10:00:00', 1, 100);
INSERT INTO cinema.showtime (id, show_date, start_at, end_at, film_id, price) VALUES (13, '2022-01-08', '10:30:00', '12:00:00', 2, 150);
INSERT INTO cinema.showtime (id, show_date, start_at, end_at, film_id, price) VALUES (14, '2022-01-08', '13:00:00', '15:00:00', 3, 200);
INSERT INTO cinema.showtime (id, show_date, start_at, end_at, film_id, price) VALUES (15, '2022-01-08', '16:30:00', '17:30:00', 4, 200);
INSERT INTO cinema.showtime (id, show_date, start_at, end_at, film_id, price) VALUES (19, '2022-01-08', '17:00:00', '18:30:00', 2, 200); --mistake
INSERT INTO cinema.showtime (id, show_date, start_at, end_at, film_id, price) VALUES (16, '2022-01-08', '18:00:00', '19:00:00', 1, 300);
INSERT INTO cinema.showtime (id, show_date, start_at, end_at, film_id, price) VALUES (17, '2022-01-08', '19:30:00', '21:00:00', 2, 400);
INSERT INTO cinema.showtime (id, show_date, start_at, end_at, film_id, price) VALUES (18, '2022-01-08', '21:30:00', '23:30:00', 3, 500);


--
-- Data for Name: ticket; Type: TABLE DATA; Schema: cinema; Owner: postgres
--

INSERT INTO cinema.ticket (seat, session_id) VALUES (5, 12);
INSERT INTO cinema.ticket (seat, session_id) VALUES (6, 12);
INSERT INTO cinema.ticket (seat, session_id) VALUES (7, 12);
INSERT INTO cinema.ticket (seat, session_id) VALUES (8, 12);
INSERT INTO cinema.ticket (seat, session_id) VALUES (9, 12);
INSERT INTO cinema.ticket (seat, session_id) VALUES (3, 13);
INSERT INTO cinema.ticket (seat, session_id) VALUES (8, 13);
INSERT INTO cinema.ticket (seat, session_id) VALUES (7, 13);
INSERT INTO cinema.ticket (seat, session_id) VALUES (9, 13);
INSERT INTO cinema.ticket (seat, session_id) VALUES (2, 13);
INSERT INTO cinema.ticket (seat, session_id) VALUES (6, 13);
INSERT INTO cinema.ticket (seat, session_id) VALUES (10, 13);
INSERT INTO cinema.ticket (seat, session_id) VALUES (1, 14);
INSERT INTO cinema.ticket (seat, session_id) VALUES (2, 14);
INSERT INTO cinema.ticket (seat, session_id) VALUES (3, 14);
INSERT INTO cinema.ticket (seat, session_id) VALUES (6, 14);
INSERT INTO cinema.ticket (seat, session_id) VALUES (5, 14);
INSERT INTO cinema.ticket (seat, session_id) VALUES (7, 14);
INSERT INTO cinema.ticket (seat, session_id) VALUES (8, 14);
INSERT INTO cinema.ticket (seat, session_id) VALUES (9, 14);
INSERT INTO cinema.ticket (seat, session_id) VALUES (3, 15);
INSERT INTO cinema.ticket (seat, session_id) VALUES (4, 15);
INSERT INTO cinema.ticket (seat, session_id) VALUES (5, 15);
INSERT INTO cinema.ticket (seat, session_id) VALUES (7, 15);
INSERT INTO cinema.ticket (seat, session_id) VALUES (8, 15);
INSERT INTO cinema.ticket (seat, session_id) VALUES (1, 15);
INSERT INTO cinema.ticket (seat, session_id) VALUES (2, 15);
INSERT INTO cinema.ticket (seat, session_id) VALUES (10, 15);
INSERT INTO cinema.ticket (seat, session_id) VALUES (1, 16);
INSERT INTO cinema.ticket (seat, session_id) VALUES (2, 16);
INSERT INTO cinema.ticket (seat, session_id) VALUES (3, 16);
INSERT INTO cinema.ticket (seat, session_id) VALUES (4, 16);
INSERT INTO cinema.ticket (seat, session_id) VALUES (5, 16);
INSERT INTO cinema.ticket (seat, session_id) VALUES (6, 16);
INSERT INTO cinema.ticket (seat, session_id) VALUES (7, 16);
INSERT INTO cinema.ticket (seat, session_id) VALUES (8, 16);
INSERT INTO cinema.ticket (seat, session_id) VALUES (1, 17);
INSERT INTO cinema.ticket (seat, session_id) VALUES (2, 17);
INSERT INTO cinema.ticket (seat, session_id) VALUES (3, 17);
INSERT INTO cinema.ticket (seat, session_id) VALUES (4, 17);
INSERT INTO cinema.ticket (seat, session_id) VALUES (5, 17);
INSERT INTO cinema.ticket (seat, session_id) VALUES (6, 17);
INSERT INTO cinema.ticket (seat, session_id) VALUES (7, 17);
INSERT INTO cinema.ticket (seat, session_id) VALUES (8, 17);
INSERT INTO cinema.ticket (seat, session_id) VALUES (9, 17);
INSERT INTO cinema.ticket (seat, session_id) VALUES (1, 18);
INSERT INTO cinema.ticket (seat, session_id) VALUES (2, 18);
INSERT INTO cinema.ticket (seat, session_id) VALUES (3, 18);
INSERT INTO cinema.ticket (seat, session_id) VALUES (4, 18);
INSERT INTO cinema.ticket (seat, session_id) VALUES (5, 18);
INSERT INTO cinema.ticket (seat, session_id) VALUES (6, 18);
INSERT INTO cinema.ticket (seat, session_id) VALUES (7, 18);
INSERT INTO cinema.ticket (seat, session_id) VALUES (8, 18);
INSERT INTO cinema.ticket (seat, session_id) VALUES (9, 18);
INSERT INTO cinema.ticket (seat, session_id) VALUES (10, 18);


--
-- Name: film_id_seq; Type: SEQUENCE SET; Schema: cinema; Owner: postgres
--

SELECT pg_catalog.setval('cinema.film_id_seq', 6, true);


--
-- Name: session_id_seq; Type: SEQUENCE SET; Schema: cinema; Owner: postgres
--

SELECT pg_catalog.setval('cinema.session_id_seq', 18, true);


--
-- Name: film film_pk; Type: CONSTRAINT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.film
    ADD CONSTRAINT film_pk PRIMARY KEY (id);


--
-- Name: showtime session_pk; Type: CONSTRAINT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.showtime
    ADD CONSTRAINT session_pk PRIMARY KEY (id);


--
-- Name: ticket ticket_pk; Type: CONSTRAINT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.ticket
    ADD CONSTRAINT ticket_pk UNIQUE (seat, session_id);


--
-- Name: session_end_at_uindex; Type: INDEX; Schema: cinema; Owner: postgres
--

CREATE UNIQUE INDEX session_end_at_uindex ON cinema.showtime USING btree (end_at);


--
-- Name: session_start_at_uindex; Type: INDEX; Schema: cinema; Owner: postgres
--

CREATE UNIQUE INDEX session_start_at_uindex ON cinema.showtime USING btree (start_at);


--
-- Name: showtime film__fk; Type: FK CONSTRAINT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.showtime
    ADD CONSTRAINT film__fk FOREIGN KEY (film_id) REFERENCES cinema.film(id);


--
-- Name: ticket session__fk; Type: FK CONSTRAINT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.ticket
    ADD CONSTRAINT session__fk FOREIGN KEY (session_id) REFERENCES cinema.showtime(id);


--
-- PostgreSQL database dump complete
--

