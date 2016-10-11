CREATE  TABLE pais 
(
	idpais SERIAL NOT NULL ,
	sigla 	VARCHAR(3) 	NOT NULL UNIQUE,
	nome 	VARCHAR(45) NOT NULL ,
	ativo   BOOLEAN ,
	
	primary key ( idpais )
);

CREATE  TABLE estado 
(
	idestado SERIAL NOT NULL ,
	sigla 	VARCHAR(2) 	NOT NULL UNIQUE,
	nome 	VARCHAR(45) NOT NULL ,
	ref_pais INT NOT NULL ,
	ativo   BOOLEAN ,
	
	primary key ( idestado ) ,
	foreign key ( ref_pais ) references pais ( idpais )
);

CREATE  TABLE cidade
(
	idcidade 	SERIAL 			NOT NULL ,
	nome 		VARCHAR(100) 	NOT NULL ,
	ref_estado 	INT 	 		NOT NULL ,
	ativo       BOOLEAN ,
	
	primary key ( idcidade ),
	foreign key ( ref_estado ) references estado ( idestado )
);

CREATE  TABLE atleta
(
	idatleta 	SERIAL NOT NULL ,
	nome 		VARCHAR(100) NOT NULL ,
	dtnasc 		DATE ,
	rg 			VARCHAR(15) ,
	cpf 		VARCHAR(15) ,
	tiposang 	VARCHAR(3) ,
	telefone 	VARCHAR(15) ,
	email 		VARCHAR(60) ,
	endereco 	VARCHAR(200) ,
	cep 		VARCHAR(10) ,
	parente 	VARCHAR(60) ,
	contatoParente VARCHAR(15) ,
	alergias 	VARCHAR(200) ,
	ref_cidade 	INT NOT NULL ,
	observacoes TEXT,
	login 		VARCHAR(20),
	senha		VARCHAR(20),
	ativo       BOOLEAN ,
	
	constraint pk_atleta_idatleta   primary key ( idatleta ),
	constraint fk_ref_cidade   		foreign key ( ref_cidade ) references cidade ( idcidade )
);

CREATE  TABLE competicao
(
	idcompeticao SERIAL NOT NULL ,
	nome VARCHAR(100) NOT NULL ,
	dia DATE ,
	status CHAR , -- p (planejada), r (realizada), n (suspensa)
	ref_cidade  INT,
	localidade VARCHAR(200),
	colocacao VARCHAR(50) ,
	premiacao VARCHAR(100) ,
	relato TEXT ,

	constraint pk_competicao_idcompeticao   primary key ( idcompeticao ),
	constraint fk_ref_cidade 			    foreign key ( ref_cidade ) references cidade ( idcidade )
);

CREATE  TABLE modalidades
(
	idmodalidades SERIAL NOT NULL ,
	nome VARCHAR(100) NOT NULL ,
	ativo   BOOLEAN ,
	
	constraint pk_modalidades_idmodalidades   primary key ( idmodalidades )
);

CREATE  TABLE equipe
(
	ref_competicao INT NOT NULL ,
	ref_atleta INT NOT NULL ,

	constraint pk_equipe			    primary key ( ref_competicao, ref_atleta ) ,
	constraint fk_ref_competicao		foreign key ( ref_competicao ) references competicao ( idcompeticao ),
	constraint fk_ref_atleta			foreign key ( ref_atleta ) references atleta ( idatleta )
);

CREATE  TABLE percurso
(
	ref_competicao INT NOT NULL ,
	ref_modalidades INT NOT NULL ,
	km numeric NOT NULL ,
	
	constraint pk_percurso				  	primary key ( ref_competicao, ref_modalidades ) ,
	constraint fk_ref_competicao 			foreign key ( ref_competicao ) references competicao ( idcompeticao ),
	constraint fk_ref_modalidades		  	foreign key ( ref_modalidades ) references modalidades ( idmodalidades )
);

CREATE  TABLE tipo_despesas 
(
	idtipo_despesas SERIAL NOT NULL ,
	nome VARCHAR(60) NOT NULL ,
	ativo   BOOLEAN ,

	constraint pk_tipo_despesas  primary key ( idtipo_despesas )
);

CREATE  TABLE despesas
(
	ref_competicao INT NOT NULL ,
	ref_tipo_despesas INT NOT NULL ,
	valor numeric NOT NULL ,
	observacao VARCHAR(200) NULL ,

	constraint pk_despesas			  	primary key ( ref_competicao, ref_tipo_despesas ) ,
	constraint fk_ref_competicao 		foreign key ( ref_competicao ) references competicao ( idcompeticao ),
	constraint fk_ref_despesas			foreign key ( ref_tipo_despesas ) references tipo_despesas ( idtipo_despesas )
);
