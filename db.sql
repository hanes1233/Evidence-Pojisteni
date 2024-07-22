--
-- PostgreSQL database dump
--

-- Dumped from database version 12.16 (Ubuntu 12.16-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.16 (Ubuntu 12.16-0ubuntu0.20.04.1)

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

--
-- Name: id_sequence1; Type: SEQUENCE; Schema: public; Owner: pavel
--

CREATE SEQUENCE public.id_sequence1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_sequence1 OWNER TO pavel;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: addresses; Type: TABLE; Schema: public; Owner: pavel
--

CREATE TABLE public.addresses (
    id integer DEFAULT nextval('public.id_sequence1'::regclass) NOT NULL,
    user_id integer,
    street character varying(60),
    streetnum integer,
    city character varying(40),
    psc integer,
    country character varying(40) DEFAULT 'Česká republica'::character varying
);


ALTER TABLE public.addresses OWNER TO pavel;

--
-- Name: id_sequence; Type: SEQUENCE; Schema: public; Owner: pavel
--

CREATE SEQUENCE public.id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_sequence OWNER TO pavel;

--
-- Name: insurred; Type: TABLE; Schema: public; Owner: pavel
--

CREATE TABLE public.insurred (
    id integer NOT NULL,
    type character varying(100) NOT NULL,
    amount integer NOT NULL,
    subject character varying(100) NOT NULL,
    beggining date DEFAULT CURRENT_DATE NOT NULL,
    enddate date NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.insurred OWNER TO pavel;

--
-- Name: pojistency_id_seq; Type: SEQUENCE; Schema: public; Owner: pavel
--

CREATE SEQUENCE public.pojistency_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pojistency_id_seq OWNER TO pavel;

--
-- Name: pojistency_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pavel
--

ALTER SEQUENCE public.pojistency_id_seq OWNED BY public.insurred.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: pavel
--

CREATE TABLE public.users (
    id integer DEFAULT nextval('public.id_sequence'::regclass) NOT NULL,
    name character varying(50),
    lastname character varying(50),
    email character varying(50),
    dateofbirth date,
    isadmin boolean DEFAULT false,
    username character varying(60),
    password character varying(180)
);


ALTER TABLE public.users OWNER TO pavel;

--
-- Name: insurred id; Type: DEFAULT; Schema: public; Owner: pavel
--

ALTER TABLE ONLY public.insurred ALTER COLUMN id SET DEFAULT nextval('public.pojistency_id_seq'::regclass);


--
-- Data for Name: addresses; Type: TABLE DATA; Schema: public; Owner: pavel
--

COPY public.addresses (id, user_id, street, streetnum, city, psc, country) FROM stdin;
30	31	Letnanska	13	Praha	15000	Česká republica
31	32	Slovenska	25	Bratislava	94321	Slovensko
19	20	Lazarska	1516	Praha	12000	Česká republica
25	26	Brnanska	1234	Brno	14000	Česká republica
27	28	15	11	Vaclavske Namesti	11000	Česká republica
\.


--
-- Data for Name: insurred; Type: TABLE DATA; Schema: public; Owner: pavel
--

COPY public.insurred (id, type, amount, subject, beggining, enddate, user_id) FROM stdin;
9	Povinné ručení	10005	Porsche Turbo	2024-06-01	2025-01-31	20
23	Povinné ručení	25000	Nissan Passat	2024-06-01	2025-06-01	28
25	Povinné ručení	54321	Skoda Fabia	2024-06-01	2028-10-18	26
26	Cestovní pojištění	5000	Valentina Berger	2024-06-04	2024-08-31	31
27	Povinné ručení	40000	Mercedes Benz	2024-01-02	2028-01-31	31
3	Pojištění domácích mazličků	25000	Kocour	1994-02-28	2030-12-25	1
28	Pojištění nemovitosti	99992	Dům	1996-01-03	2046-12-06	20
29	Pojištění domácích mazličků	45521	Papoušek	2024-06-06	2028-11-16	32
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: pavel
--

COPY public.users (id, name, lastname, email, dateofbirth, isadmin, username, password) FROM stdin;
20	Jan	Novak	novak@seznam.cz	1980-07-23	f	novak123	\N
26	Honza	Rakusan	rakusan@rakkous@seznam.cz	1988-08-24	f	rakusan123	\N
28	Kuba	Malý	kuba1@seznam.cz	1997-03-13	f	kuba123	QPs2Kg18VfjPIjQ+qRFcuw==
31	Valentina	Berger	val@star.cz	2024-06-06	f	val123	ViKnSqfJ/mQIWB+55iK1Lw==
32	Jirka	Bilek	bilek@seznam.cz	1990-11-14	f	bilekJR123	nwKkKMk85tsyqBAv2MVO2g==
1	Pavel	Herasymov	pavelherasymov@seznam.cz	1994-02-28	t	pavel123	\N
\.


--
-- Name: id_sequence; Type: SEQUENCE SET; Schema: public; Owner: pavel
--

SELECT pg_catalog.setval('public.id_sequence', 32, true);


--
-- Name: id_sequence1; Type: SEQUENCE SET; Schema: public; Owner: pavel
--

SELECT pg_catalog.setval('public.id_sequence1', 31, true);


--
-- Name: pojistency_id_seq; Type: SEQUENCE SET; Schema: public; Owner: pavel
--

SELECT pg_catalog.setval('public.pojistency_id_seq', 29, true);


--
-- Name: addresses adresses_pkey; Type: CONSTRAINT; Schema: public; Owner: pavel
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT adresses_pkey PRIMARY KEY (id);


--
-- Name: insurred pojistency_pkey; Type: CONSTRAINT; Schema: public; Owner: pavel
--

ALTER TABLE ONLY public.insurred
    ADD CONSTRAINT pojistency_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: pavel
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users varchar; Type: CONSTRAINT; Schema: public; Owner: pavel
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT "varchar" UNIQUE (username);


--
-- Name: addresses usersidfkey; Type: FK CONSTRAINT; Schema: public; Owner: pavel
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT usersidfkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: insurred usersidfkey; Type: FK CONSTRAINT; Schema: public; Owner: pavel
--

ALTER TABLE ONLY public.insurred
    ADD CONSTRAINT usersidfkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

