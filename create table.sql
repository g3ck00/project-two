-- public.pantalla definition

-- Drop table

-- DROP TABLE public.pantalla;

CREATE TABLE public.pantalla (
	id_pantalla int4 NOT NULL,
	pantalla_descripcion varchar(200) NOT NULL,
	CONSTRAINT pantalla_pkey PRIMARY KEY (id_pantalla)
);


-- public.rol definition

-- Drop table

-- DROP TABLE public.rol;

CREATE TABLE public.rol (
	id_rol int4 NOT NULL,
	nombre_rol varchar(50) NOT NULL,
	descripcion_rol varchar(100) NOT NULL,
	CONSTRAINT rol_pkey PRIMARY KEY (id_rol)
);


-- public.rol_pantalla definition

-- Drop table

-- DROP TABLE public.rol_pantalla;

CREATE TABLE public.rol_pantalla (
	id_rol int4 NOT NULL,
	id_pantalla int4 NOT NULL,
	CONSTRAINT rol_pantalla_pkey PRIMARY KEY (id_rol)
);


-- public.usuario definition

-- Drop table

-- DROP TABLE public.usuario;

CREATE TABLE public.usuario (
	id_usuario int4 NOT NULL,
	nombre_usuario varchar(50) NOT NULL,
	contrasenna varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	activo bool NOT NULL,
	fecha_creacion date NOT NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario)
);


-- public.usuario_rol definition

-- Drop table

-- DROP TABLE public.usuario_rol;

CREATE TABLE public.usuario_rol (
	id_usuario int4 NOT NULL,
	id_rol int4 NOT NULL,
	fecha_asignacion date NOT NULL,
	id_designador int4 NOT NULL,
	activo bool NOT NULL,
	CONSTRAINT usuario_rol_pkey PRIMARY KEY (id_usuario)
);