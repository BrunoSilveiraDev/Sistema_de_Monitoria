drop database if exists monitime;
create database monitime;
use monitime;

-- drop table listasalas;
create table listasalas(
ltsid int primary key auto_increment,
ltsnome varchar(20) not null
);
insert into listasalas (ltsnome) values("LAB 1"),("LAB 2"),("LAB 3"),("LAB 4"),("LAB 5");
-- select * from listasalas;

-- drop table horario;
create table horario(
horid int primary key auto_increment,
horini time not null,
horfim time not null
);
insert into horario (horini, horfim) values("08:00:00","09:00:00"),("09:00:00","10:00:00"),("10:00:00","11:00:00"),("11:00:00","12:00:00"),("12:00:00","13:00:00"),("13:00:00","14:00:00"),("14:00:00","15:00:00"),("15:00:00","16:00:00"),("16:00:00","17:00:00"),("17:00:00","18:00:00"),("18:00:00","19:00:00"),("19:00:00","20:00:00"),("20:00:00","21:00:00"),("21:00:00","22:00:00");
-- select * from horario;

-- drop table diasemana;
create table diasemana(
ddsid int primary key auto_increment,
ddssigla varchar(3) not null,
ddsnome varchar(10) not null
);
insert into diasemana (ddssigla, ddsnome) values("SEG","Segunda"),("TER","Terça"),("QUA","Quarta"),("QUI","Quinta"),("SEX","Sexta"),("SAB","Sabado");
-- select * from diasemana;

-- drop table disciplina;
create table disciplina(
dissigla varchar(9) not null,
disnome varchar(70) not null,
primary key(dissigla,disnome)
);
insert into disciplina values("CTADS.002","ALGORITMO E TÉCNICAS DE PROGRAMAÇÃO"),("CTADS.005","ANÁLISE ORGANIZACIONAL E DE PROCESSOS"),("CTADS.001","CÁLCULO DIFERENCIAL E INTEGRAL"),("CTADS.003","INGLÊS INSTRUMENTAL"),("CTADS.004","LÓGICA MATEMÁTICA"),("CTADS.011","ESTRUTURA DE DADOS"),("CTADS.009","INTERFACE HOMEM MÁQUINA"),("CTADS.008","LINGUAGEM E PROGRAMAÇÃO ORIENTADA A OBJETOS"),("CTADS.007","ORGANIZAÇÃO DE COMPUTADORES"),("CTADS.006","PROBABILIDADE E ESTATÍSTICA"),("CTADS.010","PRODUÇÃO DE TEXTOS"),("CTADS.016","ENGENHARIA DE REQUISITOS E ANÁLISE DE SISTEMAS"),("CTADS.017","FILOSOFIA APLICADA À COMPUTAÇÃO"),("CTADS.012","MODELAGEM, PROJETO E IMPLEMENTAÇÃO DE BD"),("CTADS.014","PADRÕES DE PROJETO"),("CTADS.013","PROGRAMAÇÃO APLICADA"),("CTADS.015","PROJETO DE INTERFACE GRÁFICA"),("CTADS.023","ADMINISTRAÇÃO E PROGRAMAÇÃO EM BANCO DE BANCO DE DADOS"),("CTADS.018","DESENVOLVIMENTO RÁPIDO DE APLICAÇÕES"),("CTADS.020","METODOLOGIA DE PESQUISA APLICADA À COMPUTAÇÃO"),("CTADS.019","PROCESSOS DE DESENVOLVIMENTO DE SOFTWARE"),("CTADS.022","REDES DE COMPUTADORES"),("CTADS.021","SISTEMAS OPERACIONAIS"),("CTADS.024","DESENVOLVIMENTO WEB"),("CTADS.027","DIREITO EMPRESARIAL"),("CTADS.026","GERENCIAMENTO DE PROJETOS DE SOFTWARE"),("CTADS.025","PROJETO BASEADO EM COMPONENTES"),("CTADS.028","TRABALHO DE CONCLUSÃO DE CURSO I - PROJETO DE SOFTWARE"),("CTADS.031","DESENVOLVIMENTO DE APLICAÇÕES DISTRIBUÍDAS"),("CTADS.032","EMPREENDEDORISMO"),("CTADS.029","MARKETING DE SOFTWARE APLICADO À COMPUTAÇÃO"),("CTADS.030","TESTE DE SOFTWARE"),("CTADS.033","TRABALHO DE CONCLUSÃO DE CURSO II - DESENVOLVIMENTO DE SOFTWARE"),("CTADS.036","BANCO DE DADOS AVANÇADO"),("CTADS.037","FRAMEWORKS NO DESENVOLVIMENTO WEB"),("CTADS.43","GESTÃO DA QUALIDADE DE SOFTWARE"),("CTADS.038","INFORMÁTICA NA EDUCAÇÃO"),("CTADS.035","INTELIGÊNCIA ARTIFICIAL & SISTEMAS ESPECIALISTAS"),("CTADS.039","LIBRAS"),("CTADS.42","LINGUAGEM DE PROGRAMAÇÃO VISUAL"),("CTADS.034","PROGRAMAÇÃO EM LÓGICA"),("SEMI.TS","SEMINÁRIOS EM INFORMÁTICA");
-- select * from disciplina;

-- drop table usuario;
create table usuario(
usumatricula varchar(13) primary key not null,
usunome varchar(60) not null,
ususenha varchar (10) default '123',
usutipo varchar(3) default "alu"
);
insert into usuario values ("0000001","admin","timeadm","adm");
insert into usuario (usumatricula, usunome, ususenha, usutipo) values ("0000000000001","Teste Coordenador","cootime","coo");
insert into usuario (usumatricula, usunome, usutipo) values ("0000000000002","Teste Monitor","mon");
insert into usuario (usumatricula, usunome) values ("0000000000003","Teste Aluno"),("2017118970001","ADELSON FERNANDES JUNIOR"),("2017118970002","ÁDRIA ESTEFFANE DO ROSARIO COSTA"),("2017118970003","ANDRÉ LUIZ SOBREIRA ARRUDA"),("2017118970004","ARI LEAL MATOS"),("2017118970005","BRENO RICHARD NUNES DE ARAUJO"),("2017118970006","BRUNO SILVEIRA CARVALHO"),("2017118970007","CARLOS ROBERTO DE OLIVEIRA MACIEL"),("2017118970008","CESAR AUGUSTO SAMPAIO DOS SANTOS"),("2017118970009","DEMILSON PEREIRA DA COSTA"),("2017118970010","EDER JONES VALENTE CHAVES"),("2017118970011","EDUARDO GALAXE DE LIMA TAVARES"),("2017118970012","EDUARDO MATHEUS RODRIGUES DA SILVA"),("2017118970013","ELIÉZER RODRIGUES SILVA"),("2017118970014","FERNANDO ARAUJO GURGEL"),("2017118970015","GABRIEL VICTOR MAIA GOMES"),("2017118970016","GILMAR ALVES PEREIRA"),("2017118970017","HERIBERTO DA SILVA CORREA JUNIOR"),("2017118970018","HIGSON ANDREAZZA DE OLIVEIRA MORAES"),("2017118970019","IZALTINO VIANA NETO"),("2017118970020","JANAINA DOS SANTOS ARAUJO"),("2017118970021","JOHN KENNEDY SARMENTO DA SILVA"),("2017118970022","JONATHAN FEITOSA DE SOUZA"),("2017118970023","JOSEMARA SOUSA DE OLIVEIRA"),("2017118970024","KELLY SUSY MARTINS JATHAY"),("2017118970025","KLEITSON JOSÉ LIMA TENÓRIO"),("2017118970026","LARISSA PESSOA SÁ MENEZES"),("2017118970027","LEANDERSON DA SILVA SANTOS"),("2017118970028","LUIS FERNANDO CAUPER PEREIRA"),("2017118970029","LUIS HENRIQUE AMORIM RIBEIRO"),("2017118970030","MATHEUS ISACKSON DA SILVA GUIMARAES"),("2017118970031","MAURICIO DA SILVA SOARES"),("2017118970032","NEUZA MARIA GONÇALVES DO CARMO FELIX"),("2017118970033","RAMON DOS SANTOS SILVA"),("2017118970034","RENATO DE LIMA SILVA"),("2017118970035","RENATO PEREIRA FERREIRA"),("2017118970036","RICKSON WILLIAM DANTAS FERREIRA"),("2017118970037","RODNEY APARECIDO DE OLIVEIRA MENDONCA"),("2017118970038","RUY DE ASCENÇÃO NETO"),("2017118970039","SANDRO HAIDEN TEIXEIRA"),("2017118970040","THALISON HIAGO LIMA ALVES"),("2017118970041","THALLYS JULLES PAIVA MENEZES MOURA"),("2017118970042","THIAGO LIMA DE ANDRADE");
-- select * from usuario;

create table aluno(
alumatricula varchar(13) not null,
alunome varchar(60) not null,
alusenha varchar (10) default '123',
alustatus boolean default false,
primary key(alumatricula),
constraint aluno_fk_usuario foreign key (alumatricula) references usuario (usumatricula)
);
-- select * from aluno;

-- drop table sala;
create table sala(
salid varchar(30) not null,
saldiasemana varchar(20) not null,
salhoraini time not null,
salhorafim time not null,
salocupacao varchar(50),
primary key (salid,saldiasemana,salhoraini,salhorafim)
);

insert into sala values ("LAB 1","Segunda",'14:00:00','15:00:00',"livre");
insert into sala values ("LAB 2","Terça",'14:00:00','15:00:00',"livre");
insert into sala values ("LAB 3","Quarta",'14:00:00','15:00:00',"livre");
-- select * from sala;

create table coordenador(
coomatricula char(7) primary key not null,
coonome varchar(60) not null,
coosenha varchar (10) not null default '123'
);
-- select * from coordenador;

-- drop table monitorianova;
create table monitorianova(
mtndissigla varchar(9) not null,
mtndtinicio date not null,
mtndtfim date not null,
mtncoomatricula char(77) not null,
primary key(mtndissigla),
constraint monitoria_fk_dissigla foreign key (mtndissigla) references disciplina (dissigla),
constraint monitoria_fk_usuario foreign key (mtncoomatricula) references usuario (usumatricula)
);
insert into monitorianova values("CTADS.001", '2018-08-08', '2018-12-30',"0000000000001");
select * from monitorianova;

-- drop table monitoria;
create table monitoriafuncional(
mntdissigla varchar(9) not null,
mntusumatricula varchar(13) not null,
mntsalid varchar(30) not null,
mntsaldiasemana varchar(20) not null,
mntsalhoraini time not null,
mntsalhorafim time not null,
primary key(mntsalid,mntsaldiasemana,mntsalhoraini,mntsalhorafim),
constraint monitoria_fk_funcdissigla foreign key (mntdissigla) references disciplina (dissigla),
constraint monitoria_fk_usumatricula foreign key (mntusumatricula) references usuario (usumatricula),
constraint monitoria_fk_salid foreign key (mntsalid,mntsaldiasemana,mntsalhoraini,mntsalhorafim)
references sala (salid,saldiasemana,salhoraini,salhorafim)
);
#select * from monitoriafuncional;

-- drop table if exists monitor;
create table monitor(
monusumatricula varchar(13) not null,
monmtndissigla varchar(9) not null,
mondtinicio date not null,
mondtfim date not null,
primary key(monusumatricula,monmtndissigla),
constraint monitor_fk_usuario foreign key (monusumatricula) references usuario (usumatricula),
constraint monitor_fk_disciplina foreign key (monmtndissigla) references disciplina (dissigla)
);
INSERT INTO monitor VALUES ("0000000000002","CTADS.001",'2018-01-01','2018-12-01');
-- select * from monitor;

drop table if exists lotacao;
create table lotacao(
lotdissigla varchar(9) not null,
lotsalid varchar(30) not null,
lotsaldiasemana varchar(20) not null,
lotsalhoraini time not null,
lotsalhorafim time not null,
lotusumatricula varchar(13) not null,
primary key(lotdissigla,lotsalid,lotsaldiasemana,lotsalhoraini,lotsalhorafim,lotusumatricula),
constraint monitoria_fk_lotdissigla foreign key (lotdissigla) references disciplina (dissigla),
constraint monitoria_fk_lotsalid foreign key (lotsalid,lotsaldiasemana,lotsalhoraini,lotsalhorafim)
references sala (salid,saldiasemana,salhoraini,salhorafim)
);
-- select * from lotacao;
delimiter #

create procedure sp_emonitor (matricula varchar(13))
begin
select count(*) from monitoria where monalumatricula = matricula;
end #
-- tem que incluir a verificação da data atual curdate() e o retorno da disciplina

delimiter ;

-- call sp_emonitor('2017118970016');

drop procedure if exists sp_addMonitor;
delimiter #
create procedure sp_addMonitor (p_mat varchar(13),p_dissigla varchar(9), p_dateini date, p_datefim date)
begin
	DECLARE exit handler for sqlexception
	  BEGIN
		-- ERROR
	  ROLLBACK;
	END;
	START TRANSACTION;
		INSERT INTO monitor VALUES (p_mat,p_dissigla,p_dateini,p_datefim);
		update usuario set usutipo = "mon" where usumatricula = p_mat;
	commit;
end #

delimiter ;

drop procedure if exists sp_remMonitor;
delimiter #
create procedure sp_remMonitor (p_mat varchar(13))
begin
	DECLARE exit handler for sqlexception
	  BEGIN
		-- ERROR
	  ROLLBACK;
	END;
	START TRANSACTION;
		delete from monitor where monusumatricula=p_mat;
		update usuario set usutipo = "alu" where usumatricula = p_mat;
	commit;
end #

delimiter ;