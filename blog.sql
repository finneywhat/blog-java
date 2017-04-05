--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: comments; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE comments (
    id integer NOT NULL,
    content text,
    postid integer,
    userid integer
);


ALTER TABLE comments OWNER TO "Guest";

--
-- Name: comments_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE comments_id_seq OWNER TO "Guest";

--
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE comments_id_seq OWNED BY comments.id;


--
-- Name: posts; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE posts (
    id integer NOT NULL,
    title character varying,
    content text,
    userid integer
);


ALTER TABLE posts OWNER TO "Guest";

--
-- Name: posts_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE posts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE posts_id_seq OWNER TO "Guest";

--
-- Name: posts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE posts_id_seq OWNED BY posts.id;


--
-- Name: posts_tags; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE posts_tags (
    id integer NOT NULL,
    postid integer,
    tagid integer
);


ALTER TABLE posts_tags OWNER TO "Guest";

--
-- Name: posts_tags_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE posts_tags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE posts_tags_id_seq OWNER TO "Guest";

--
-- Name: posts_tags_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE posts_tags_id_seq OWNED BY posts_tags.id;


--
-- Name: tags; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE tags (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE tags OWNER TO "Guest";

--
-- Name: tags_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE tags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tags_id_seq OWNER TO "Guest";

--
-- Name: tags_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE tags_id_seq OWNED BY tags.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE users (
    id integer NOT NULL,
    email character varying
);


ALTER TABLE users OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY comments ALTER COLUMN id SET DEFAULT nextval('comments_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY posts ALTER COLUMN id SET DEFAULT nextval('posts_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY posts_tags ALTER COLUMN id SET DEFAULT nextval('posts_tags_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY tags ALTER COLUMN id SET DEFAULT nextval('tags_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY comments (id, content, postid, userid) FROM stdin;
\.


--
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('comments_id_seq', 1, false);


--
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY posts (id, title, content, userid) FROM stdin;
2	Chris and Caitlin Cross Country Road Trip	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam tincidunt est at risus eleifend, sed tincidunt ligula consequat. Maecenas maximus aliquet sem in tristique. Proin sollicitudin lacus velit. Etiam mollis vestibulum viverra. Aenean volutpat et massa nec dignissim. Suspendisse lacinia aliquam enim, eu condimentum nulla molestie quis. Donec eu leo tellus. Pellentesque dui libero, gravida ut tempor id, tincidunt sed augue. Sed purus diam, imperdiet at sapien ut, laoreet aliquam justo. In eu massa pellentesque, luctus tellus eu, malesuada augue. Integer sit amet scelerisque mi. Nulla cursus urna est, euismod vestibulum velit cursus vitae. Sed auctor sit amet enim vel aliquam. Proin hendrerit orci nec lacus finibus, quis lobortis augue sodales.\r\n\r\nNunc dictum lectus nec libero sagittis faucibus. Praesent mollis metus sit amet orci consectetur pharetra. Maecenas metus elit, fermentum nec cursus in, luctus nec dui. Proin sit amet blandit mauris, ut bibendum augue. Nulla facilisi. Curabitur eget magna dapibus risus dictum blandit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ullamcorper nulla et lacinia volutpat.\r\n\r\nNam sed diam nec erat semper commodo. Nulla ac eros cursus, tempor ex a, pretium lacus. Etiam in mi pharetra, malesuada risus vitae, aliquam sapien. Etiam ultrices, nulla nec vehicula viverra, risus nulla feugiat metus, at bibendum ante mi eu neque. Nullam finibus vestibulum finibus. Nulla ac malesuada erat, ac tincidunt lectus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vestibulum vestibulum orci a quam feugiat maximus. Aenean elementum tellus nisi, id porta neque cursus faucibus. Nullam pellentesque consequat mauris, eu faucibus nunc ullamcorper ut. Nulla ac enim non arcu elementum tincidunt vel nec dolor. Pellentesque sem lectus, feugiat non quam auctor, ultricies vulputate nisl. Vestibulum tincidunt leo vel erat pretium, vitae vehicula libero dignissim. Vestibulum convallis consequat felis vel dictum.\r\n\r\nCras malesuada, sapien nec porttitor convallis, purus nibh posuere dolor, a porta risus enim eu sapien. Curabitur tincidunt malesuada tellus, in tristique metus pellentesque non. Sed ex sapien, aliquet eget mi ac, facilisis laoreet ante. Curabitur at lectus mauris. Nullam viverra nibh quis facilisis pretium. Nunc hendrerit gravida magna at euismod. Integer eu est sed nunc rhoncus vulputate. Maecenas ut arcu in tellus luctus dictum faucibus quis ex. Cras maximus velit sagittis sem tristique, sit amet posuere justo placerat. Suspendisse consequat metus est, sit amet interdum quam sollicitudin sed. Proin consequat nisl id sem ultricies feugiat. Sed scelerisque dapibus felis, eu vulputate mi vulputate vel. Nam a enim eget est sodales tincidunt ac id lectus.\r\n\r\nPellentesque viverra molestie sem at imperdiet. Suspendisse feugiat ligula eget leo pretium facilisis. Maecenas porta lacus placerat est gravida placerat. Sed nec turpis id orci sollicitudin maximus. Pellentesque ut egestas justo, in feugiat ipsum. Nunc consequat lectus quis porttitor gravida. Ut bibendum, orci id feugiat dictum, sapien nulla semper ex, ut gravida mauris lorem id massa. Nulla et massa ut quam auctor volutpat eget ut mi. Fusce a massa porta, venenatis nulla vel, aliquam sapien. Morbi ut risus imperdiet, bibendum risus vel, rutrum erat. Nulla lacus odio, rhoncus a cursus a, tempor vel ante.	1
3	Eat healthy in the new year	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam tincidunt est at risus eleifend, sed tincidunt ligula consequat. Maecenas maximus aliquet sem in tristique. Proin sollicitudin lacus velit. Etiam mollis vestibulum viverra. Aenean volutpat et massa nec dignissim. Suspendisse lacinia aliquam enim, eu condimentum nulla molestie quis. Donec eu leo tellus. Pellentesque dui libero, gravida ut tempor id, tincidunt sed augue. Sed purus diam, imperdiet at sapien ut, laoreet aliquam justo. In eu massa pellentesque, luctus tellus eu, malesuada augue. Integer sit amet scelerisque mi. Nulla cursus urna est, euismod vestibulum velit cursus vitae. Sed auctor sit amet enim vel aliquam. Proin hendrerit orci nec lacus finibus, quis lobortis augue sodales.\r\n\r\nNunc dictum lectus nec libero sagittis faucibus. Praesent mollis metus sit amet orci consectetur pharetra. Maecenas metus elit, fermentum nec cursus in, luctus nec dui. Proin sit amet blandit mauris, ut bibendum augue. Nulla facilisi. Curabitur eget magna dapibus risus dictum blandit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ullamcorper nulla et lacinia volutpat.\r\n\r\nNam sed diam nec erat semper commodo. Nulla ac eros cursus, tempor ex a, pretium lacus. Etiam in mi pharetra, malesuada risus vitae, aliquam sapien. Etiam ultrices, nulla nec vehicula viverra, risus nulla feugiat metus, at bibendum ante mi eu neque. Nullam finibus vestibulum finibus. Nulla ac malesuada erat, ac tincidunt lectus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vestibulum vestibulum orci a quam feugiat maximus. Aenean elementum tellus nisi, id porta neque cursus faucibus. Nullam pellentesque consequat mauris, eu faucibus nunc ullamcorper ut. Nulla ac enim non arcu elementum tincidunt vel nec dolor. Pellentesque sem lectus, feugiat non quam auctor, ultricies vulputate nisl. Vestibulum tincidunt leo vel erat pretium, vitae vehicula libero dignissim. Vestibulum convallis consequat felis vel dictum.\r\n\r\nCras malesuada, sapien nec porttitor convallis, purus nibh posuere dolor, a porta risus enim eu sapien. Curabitur tincidunt malesuada tellus, in tristique metus pellentesque non. Sed ex sapien, aliquet eget mi ac, facilisis laoreet ante. Curabitur at lectus mauris. Nullam viverra nibh quis facilisis pretium. Nunc hendrerit gravida magna at euismod. Integer eu est sed nunc rhoncus vulputate. Maecenas ut arcu in tellus luctus dictum faucibus quis ex. Cras maximus velit sagittis sem tristique, sit amet posuere justo placerat. Suspendisse consequat metus est, sit amet interdum quam sollicitudin sed. Proin consequat nisl id sem ultricies feugiat. Sed scelerisque dapibus felis, eu vulputate mi vulputate vel. Nam a enim eget est sodales tincidunt ac id lectus.\r\n\r\nPellentesque viverra molestie sem at imperdiet. Suspendisse feugiat ligula eget leo pretium facilisis. Maecenas porta lacus placerat est gravida placerat. Sed nec turpis id orci sollicitudin maximus. Pellentesque ut egestas justo, in feugiat ipsum. Nunc consequat lectus quis porttitor gravida. Ut bibendum, orci id feugiat dictum, sapien nulla semper ex, ut gravida mauris lorem id massa. Nulla et massa ut quam auctor volutpat eget ut mi. Fusce a massa porta, venenatis nulla vel, aliquam sapien. Morbi ut risus imperdiet, bibendum risus vel, rutrum erat. Nulla lacus odio, rhoncus a cursus a, tempor vel ante.	1
4	Dogs are cool	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam tincidunt est at risus eleifend, sed tincidunt ligula consequat. Maecenas maximus aliquet sem in tristique. Proin sollicitudin lacus velit. Etiam mollis vestibulum viverra. Aenean volutpat et massa nec dignissim. Suspendisse lacinia aliquam enim, eu condimentum nulla molestie quis. Donec eu leo tellus. Pellentesque dui libero, gravida ut tempor id, tincidunt sed augue. Sed purus diam, imperdiet at sapien ut, laoreet aliquam justo. In eu massa pellentesque, luctus tellus eu, malesuada augue. Integer sit amet scelerisque mi. Nulla cursus urna est, euismod vestibulum velit cursus vitae. Sed auctor sit amet enim vel aliquam. Proin hendrerit orci nec lacus finibus, quis lobortis augue sodales.\r\n\r\nNunc dictum lectus nec libero sagittis faucibus. Praesent mollis metus sit amet orci consectetur pharetra. Maecenas metus elit, fermentum nec cursus in, luctus nec dui. Proin sit amet blandit mauris, ut bibendum augue. Nulla facilisi. Curabitur eget magna dapibus risus dictum blandit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ullamcorper nulla et lacinia volutpat.\r\n\r\nNam sed diam nec erat semper commodo. Nulla ac eros cursus, tempor ex a, pretium lacus. Etiam in mi pharetra, malesuada risus vitae, aliquam sapien. Etiam ultrices, nulla nec vehicula viverra, risus nulla feugiat metus, at bibendum ante mi eu neque. Nullam finibus vestibulum finibus. Nulla ac malesuada erat, ac tincidunt lectus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vestibulum vestibulum orci a quam feugiat maximus. Aenean elementum tellus nisi, id porta neque cursus faucibus. Nullam pellentesque consequat mauris, eu faucibus nunc ullamcorper ut. Nulla ac enim non arcu elementum tincidunt vel nec dolor. Pellentesque sem lectus, feugiat non quam auctor, ultricies vulputate nisl. Vestibulum tincidunt leo vel erat pretium, vitae vehicula libero dignissim. Vestibulum convallis consequat felis vel dictum.\r\n\r\nCras malesuada, sapien nec porttitor convallis, purus nibh posuere dolor, a porta risus enim eu sapien. Curabitur tincidunt malesuada tellus, in tristique metus pellentesque non. Sed ex sapien, aliquet eget mi ac, facilisis laoreet ante. Curabitur at lectus mauris. Nullam viverra nibh quis facilisis pretium. Nunc hendrerit gravida magna at euismod. Integer eu est sed nunc rhoncus vulputate. Maecenas ut arcu in tellus luctus dictum faucibus quis ex. Cras maximus velit sagittis sem tristique, sit amet posuere justo placerat. Suspendisse consequat metus est, sit amet interdum quam sollicitudin sed. Proin consequat nisl id sem ultricies feugiat. Sed scelerisque dapibus felis, eu vulputate mi vulputate vel. Nam a enim eget est sodales tincidunt ac id lectus.\r\n\r\nPellentesque viverra molestie sem at imperdiet. Suspendisse feugiat ligula eget leo pretium facilisis. Maecenas porta lacus placerat est gravida placerat. Sed nec turpis id orci sollicitudin maximus. Pellentesque ut egestas justo, in feugiat ipsum. Nunc consequat lectus quis porttitor gravida. Ut bibendum, orci id feugiat dictum, sapien nulla semper ex, ut gravida mauris lorem id massa. Nulla et massa ut quam auctor volutpat eget ut mi. Fusce a massa porta, venenatis nulla vel, aliquam sapien. Morbi ut risus imperdiet, bibendum risus vel, rutrum erat. Nulla lacus odio, rhoncus a cursus a, tempor vel ante.	1
5	Cats are jerks	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam tincidunt est at risus eleifend, sed tincidunt ligula consequat. Maecenas maximus aliquet sem in tristique. Proin sollicitudin lacus velit. Etiam mollis vestibulum viverra. Aenean volutpat et massa nec dignissim. Suspendisse lacinia aliquam enim, eu condimentum nulla molestie quis. Donec eu leo tellus. Pellentesque dui libero, gravida ut tempor id, tincidunt sed augue. Sed purus diam, imperdiet at sapien ut, laoreet aliquam justo. In eu massa pellentesque, luctus tellus eu, malesuada augue. Integer sit amet scelerisque mi. Nulla cursus urna est, euismod vestibulum velit cursus vitae. Sed auctor sit amet enim vel aliquam. Proin hendrerit orci nec lacus finibus, quis lobortis augue sodales.\r\n\r\nNunc dictum lectus nec libero sagittis faucibus. Praesent mollis metus sit amet orci consectetur pharetra. Maecenas metus elit, fermentum nec cursus in, luctus nec dui. Proin sit amet blandit mauris, ut bibendum augue. Nulla facilisi. Curabitur eget magna dapibus risus dictum blandit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ullamcorper nulla et lacinia volutpat.\r\n\r\nNam sed diam nec erat semper commodo. Nulla ac eros cursus, tempor ex a, pretium lacus. Etiam in mi pharetra, malesuada risus vitae, aliquam sapien. Etiam ultrices, nulla nec vehicula viverra, risus nulla feugiat metus, at bibendum ante mi eu neque. Nullam finibus vestibulum finibus. Nulla ac malesuada erat, ac tincidunt lectus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vestibulum vestibulum orci a quam feugiat maximus. Aenean elementum tellus nisi, id porta neque cursus faucibus. Nullam pellentesque consequat mauris, eu faucibus nunc ullamcorper ut. Nulla ac enim non arcu elementum tincidunt vel nec dolor. Pellentesque sem lectus, feugiat non quam auctor, ultricies vulputate nisl. Vestibulum tincidunt leo vel erat pretium, vitae vehicula libero dignissim. Vestibulum convallis consequat felis vel dictum.\r\n\r\nCras malesuada, sapien nec porttitor convallis, purus nibh posuere dolor, a porta risus enim eu sapien. Curabitur tincidunt malesuada tellus, in tristique metus pellentesque non. Sed ex sapien, aliquet eget mi ac, facilisis laoreet ante. Curabitur at lectus mauris. Nullam viverra nibh quis facilisis pretium. Nunc hendrerit gravida magna at euismod. Integer eu est sed nunc rhoncus vulputate. Maecenas ut arcu in tellus luctus dictum faucibus quis ex. Cras maximus velit sagittis sem tristique, sit amet posuere justo placerat. Suspendisse consequat metus est, sit amet interdum quam sollicitudin sed. Proin consequat nisl id sem ultricies feugiat. Sed scelerisque dapibus felis, eu vulputate mi vulputate vel. Nam a enim eget est sodales tincidunt ac id lectus.\r\n\r\nPellentesque viverra molestie sem at imperdiet. Suspendisse feugiat ligula eget leo pretium facilisis. Maecenas porta lacus placerat est gravida placerat. Sed nec turpis id orci sollicitudin maximus. Pellentesque ut egestas justo, in feugiat ipsum. Nunc consequat lectus quis porttitor gravida. Ut bibendum, orci id feugiat dictum, sapien nulla semper ex, ut gravida mauris lorem id massa. Nulla et massa ut quam auctor volutpat eget ut mi. Fusce a massa porta, venenatis nulla vel, aliquam sapien. Morbi ut risus imperdiet, bibendum risus vel, rutrum erat. Nulla lacus odio, rhoncus a cursus a, tempor vel ante.	1
\.


--
-- Name: posts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('posts_id_seq', 5, true);


--
-- Data for Name: posts_tags; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY posts_tags (id, postid, tagid) FROM stdin;
2	2	1
3	2	2
4	3	3
5	3	4
6	4	5
7	5	6
\.


--
-- Name: posts_tags_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('posts_tags_id_seq', 7, true);


--
-- Data for Name: tags; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY tags (id, name) FROM stdin;
1	Travel
2	Wanderlust
3	DIY
4	Cooking
5	Dogs
6	Cats
\.


--
-- Name: tags_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('tags_id_seq', 6, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, email) FROM stdin;
1	user@gmail.com
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 1, true);


--
-- Name: comments_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: posts_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (id);


--
-- Name: posts_tags_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY posts_tags
    ADD CONSTRAINT posts_tags_pkey PRIMARY KEY (id);


--
-- Name: tags_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

