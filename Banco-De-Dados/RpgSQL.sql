create database rpg;

use rpg;

create table raca(
	idRaca int primary key auto_increment,
    nome varchar(50) not null
);

insert into raca (nome) values
("Humano"), ("Anão"), ("Elfo");

create table usuario (
	idUsuario int primary key auto_increment,
    nome varchar(50) not null,
    email varchar(100) not null,
    senha varchar(30) not null
);

create table personagem(
	idPersonagem int primary key auto_increment,
    idUsuario int,
    nome varchar(50),
    genero char,
    idRaca int,
    classe varchar(50),
    vigor int,
    mana int,
    estamina int,
    forca int,
    destreza int,
    inteligencia int,
    fe int,
    sorte int,
    foreign key (idUsuario) references usuario(idUsuario) ON DELETE CASCADE,
    foreign key (idRaca) references raca(idRaca)
);

create table armaBranca (
	idArma int primary key auto_increment,
    nome varchar(50) not null,
    pesada boolean not null,
    descr varchar(200)
);

insert into armaBranca (nome, pesada, descr) values
("Alabarda", True, "Arma de aste longa com lâmina em forma de meia lua com uma extensão pontiaguda"),
("Adaga", False, "Lâmina curta de peso leve"),
("Florete", False, "Espada de escrima"),
("Machado", True, "Arma de aste longa com lâmina curva e pesada na ponta"),
("Espada curta", False, "Espada de Lâmina curta, porém pesada"),
("Espada longa", True, "Espada de lâmina estendida, de alto peso e necessário técnica para ser utilizada"),
("Maça curta", False, "Arma medieval consistente de um cabo com uma cabeça de metal");

create table cajado (
	idCajado int primary key auto_increment,
    nome varchar(50) not null,
    proeficiencia varchar(50) not null,
    descr varchar(200)
);

insert into cajado (nome, proeficiencia, descr) values
("Cajado de fogo", "Fogo", "Cajado utilizado para a convocação de magias, em especial aquelas atreladas ao fogo"),
("Cajado de Gelo", "Gelo", "Cajado utilizado para a convocação de magias, em especial aquelas atreladas ao gelo"),
("Cajado de Terra", "Terra", "Cajado utilizado para a convocação de magias, em especial aquelas atreladas à terra");

create table talisman (
	idTalisman int primary key auto_increment,
    nome varchar(50) not null,
    proeficiencia varchar(50) not null,
    descr varchar(200)
);

insert into talisman (nome, proeficiencia, descr) values
("Talisman da cura", "Cura", "Artefato capaz de canalizar milagres, com enfoque em milagres de cura"),
("Talisman do trovão", "Elétrica", "Artefato capaz de canalizar milagres, com enfoque em milagres de eletricidade"),
("Talisman da repulsão", "Força", "Artefato capaz de canalizar milagres, com enfoque em milagres de força");

create table acessorio (
	idAcessorio int primary key auto_increment,
    nome varchar(50) not null,
    atributoAlterado varchar(20) not null,
    valorAlterado int not null,
    descr varchar(300) not null
);

insert into acessorio (nome, atributoAlterado, valorAlterado, descr) values
("Anel da vida", "vigor", 2, "Anel que auemnta a vitalidade do usuário (Vigor +2)"),
("Colar do crente", "fe", 3, "Colar pertencente a um antigo sacerdote (Fé +3)"),
("Bracelete da juventude", "estamina", 3, "Bracelete com propriedades mágicas de aumentar o fôlego do usuário (Estamina +3)"),
("Pingente de quatro-folhas", "sorte", 4, "Pingente pertencente a um antigo duende imbuído com uma magia a muito tempo esquecida (Sorte +4)"),
("Anel do sábio", "inteligencia", 4, "Anel de um antigo estudioso no qual o seu paradeiro é desconhecido até os dias atuais (Inteligência +4)");

create table guerreiro (
	idPersonagem int,
    idArmaPrimaria int,
    idArmaSecundaria int,
    idAcessorio int,
    foreign key (idPersonagem) references personagem(idPersonagem) ON DELETE CASCADE,
    foreign key (idArmaPrimaria) references armaBranca(idArma),
    foreign key (idArmaSecundaria) references armaBranca(idArma),
    foreign key (idAcessorio) references acessorio(idAcessorio)
);

create table clerigo (
	idPersonagem int,
    idArmaPrimaria int,
    idTalisman int,
    idAcessorio int,
    foreign key (idPersonagem) references personagem(idPersonagem) ON DELETE CASCADE,
    foreign key (idArmaPrimaria) references armaBranca(idArma),
    foreign key (idTalisman) references talisman(idTalisman),
    foreign key (idAcessorio) references acessorio(idAcessorio)
);


create table mago (
	idPersonagem int,
    idArmaPrimaria int,
    idCajado int,
    idAcessorio int,
    foreign key (idPersonagem) references personagem(idPersonagem) ON DELETE CASCADE,
    foreign key (idArmaPrimaria) references armaBranca(idArma),
    foreign key (idCajado) references cajado(idCajado),
    foreign key (idAcessorio) references acessorio(idAcessorio)
);

create table bandido (
	idPersonagem int,
    idArmaPrimaria int,
    idArmaSecundaria int,
    idAcessorio int,
    foreign key (idPersonagem) references personagem(idPersonagem) ON DELETE CASCADE,
    foreign key (idArmaPrimaria) references armaBranca(idArma),
	foreign key (idArmaSecundaria) references armaBranca(idArma),
    foreign key (idAcessorio) references acessorio(idAcessorio)
);

