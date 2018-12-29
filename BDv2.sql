drop database if exists monitime2;
create database monitime2;
use monitime2;

set global log_bin_trust_function_creators=true;


-- UTILITARIOS PARA COMBOBOX --------------------------------------------------------------------------------------------------

-- drop table listasalas;
create table listasalas(
ltsid int primary key auto_increment,
ltsnome varchar(20) not null
);
-- select * from listasalas;

-- drop table horario;
create table horario(
horid int primary key auto_increment,
horini time not null,
horfim time not null
);
-- select * from horario;

-- drop table diasemana;
create table diasemana(
 ddsid int primary key auto_increment,
 ddssigla varchar(3) not null,
 ddsnome varchar(10) not null
);
-- select * from diasemana;


-- CLASSES -------------------------------------------------------------------------------------------------------------------


-- drop table usuario;
create table usuario(
 usumatricula varchar(13) primary key not null,
 usunome varchar(60) not null,
 ususenha varchar (10) default '123',
 usutipo varchar(3) default 'alu'
);
-- select * from usuario;

-- drop table disciplina;
create table disciplina(
 dissigla varchar(9) primary key not null,
 disnome varchar(70) not null
);
-- select * from disciplina;

-- drop table sala;
create table sala(
 salid varchar(30) not null,
 saldiasemana varchar(20) not null,
 salhoraini time not null,
 salhorafim time not null,
 salmonitor varchar(50) default 'livre',
 primary key (salid,saldiasemana,salhoraini,salhorafim)
);
-- select * from sala;


-- RELACIONAMENTOS -----------------------------------------------------------------------------------------------------------

-- drop table monitor;
create table monitor (
 monusumatricula VARCHAR(13) not null,
 mondissigla VARCHAR(9) not null,
 mondtinicio DATE NOT NULL,
 mondtfim DATE NOT NULL,
 PRIMARY KEY (monusumatricula, mondissigla),
 CONSTRAINT monitor_fk_disciplina FOREIGN KEY (mondissigla) REFERENCES disciplina (dissigla),
 CONSTRAINT monitor_fk_usuario FOREIGN KEY (monusumatricula) REFERENCES usuario (usumatricula)
);
-- select * from monitor;

-- drop table monitoria;
create table monitoria(
 moaid INT NOT NULL AUTO_INCREMENT,
 moamonusumatricula VARCHAR(13) NOT NULL,
 moadissigla VARCHAR(9) NOT NULL,
 moasalid VARCHAR(30) NOT NULL,
 moasaldiasemana VARCHAR(20) NOT NULL,
 moasalhoraini TIME NOT NULL,
 moasalhorafim TIME NOT NULL,
 moaqtde int default 0,
 PRIMARY KEY (moaid, moamonusumatricula, moadissigla, moasalid, moasaldiasemana, moasalhoraini, moasalhorafim),
 CONSTRAINT monitoria_fk_lotdissigla FOREIGN KEY (moadissigla) REFERENCES disciplina (dissigla),
 CONSTRAINT monitoria_fk_lotsalid FOREIGN KEY (moasalid , moasaldiasemana , moasalhoraini , moasalhorafim) REFERENCES sala (salid, saldiasemana , salhoraini , salhorafim),
 CONSTRAINT monitoria_fk_monitor FOREIGN KEY (moamonusumatricula) REFERENCES monitor (monusumatricula)
);
-- select * from monitoria;

-- drop table lotacao;
 create table lotacao (
  lotmoaid INT NOT NULL,
  lotusumatricula VARCHAR(13) NOT NULL,
  PRIMARY KEY (lotmoaid, lotusumatricula),
  CONSTRAINT lotacao_fk_monitoria FOREIGN KEY (lotmoaid) REFERENCES monitoria (moaid),
  CONSTRAINT lotacao_fk_usuario FOREIGN KEY (lotusumatricula) REFERENCES usuario (usumatricula)
);
-- select * from lotacao;


-- TRIGGERS ------------------------------------------------------------------------------------------------------------------

-- Trigger da alteração de perfil
delimiter #
create trigger tg_alterausuario after insert on monitor
for each row
begin
update usuario
set usutipo = 'mon'
where usumatricula  = new.monusumatricula;
end;  #
delimiter ;

-- Trigger da quantidade de alunos

delimiter #
create trigger tg_aumentalotacao after insert on lotacao
for each row
begin
update monitoria
set moaqtde = moaqtde + 1
where moaid  = new.lotmoaid;
end;  #
delimiter ;

delimiter #
create trigger tg_diminuilotacao after delete on lotacao
for each row
begin
update monitoria
set moaqtde = moaqtde - 1
where moaid  = old.lotmoaid;
end;  #
delimiter ;


-- FUNÇÕES  ------------------------------------------------------------------------------------------------------------------

-- inclusão de lotação de aluno em monitoria
delimiter #
create function func_insert_lotacao(p_moaid int, p_usumatricula varchar(13)) returns varchar(300)
begin

	declare v1 int;
    
    set v1 = (select moaqtde from monitoria where moaid = p_moaid);

	if (v1 < 12) then
    	insert into lotacao values (p_moaid, p_usumatricula);
		return 'Registrado com sucesso';
    else
        return 'Lamento, o horário está cheio';
	end if;
    
end;#
delimiter ;

delimiter #
create function func_buscaid(d_matricula varchar(13), d_sigla varchar(9), d_sala varchar(30), d_dia varchar(20), d_horaini TIME) returns int
begin
	declare v1 int;
    set v1 = (select moaid from monitoria  where ((moamonusumatricula = d_matricula) 
													and (moadissigla = d_sigla) 
        											and (moasalid = d_sala) 
        											and (moasaldiasemana = d_dia) 
        											and (moasalhoraini=d_horaini)));
	return v1;
end #
delimiter ;




-- PROCEDURES  ----------------------------------------------------------------------------------------------------------------
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


-- Buscador de ID
delimiter #
create procedure sp_buscaid(d_matricula varchar(13), d_sigla varchar(9), d_sala varchar(30), d_dia varchar(20), d_horaini TIME)
begin
	select moaid 'Id'
    from monitoria
    where ((moamonusumatricula = d_matricula) 
		and (moadissigla = d_sigla) 
        and (moasalid = d_sala) 
        and (moasaldiasemana = d_dia) 
        and (moasalhoraini=d_horaini));
end #
delimiter ;

-- select *from monitoria;
-- call sp_buscaid('2017118970165', 'CTADS.013', 'LAB I', 'Segunda','14:00:00');

-- Lista de frequencia do monitor por id (para teste de banco)
delimiter #
create procedure sp_listaporid(d_moaid int)
begin
	select usunome 'Aluno'
    from usuario
    inner join lotacao on usumatricula = lotusumatricula  
    where lotmoaid = d_moaid;
end #
delimiter ;


-- call sp_listaporid(1);

-- Lista de frequencia do monitor
delimiter #
create procedure sp_listadefrequencia(d_matricula varchar(13), d_sigla varchar(9), d_sala varchar(30), d_dia varchar(20), d_horaini TIME)
begin
	declare v1 int;
    set v1 = (func_buscaid(d_matricula, d_sigla, d_sala, d_dia, d_horaini));
	select lotmoaid 'id', usunome 'Aluno'
    from usuario
    inner join lotacao on usumatricula = lotusumatricula  
    where lotmoaid = v1;
end #
delimiter ;

-- select func_buscaid('2017118970165', 'CTADS.013', 'LAB I', 'Segunda','14:00:00');
-- call sp_listaporid(1);
-- call sp_listadefrequencia('2017118970165', 'CTADS.013', 'LAB I', 'Segunda','14:00:00');



-- Deletador de Horario
delimiter #
create procedure sp_delhorario(d_matricula varchar(13), d_sigla varchar(9), d_sala varchar(30), d_dia varchar(20), d_horaini TIME)
begin
	declare v1 int;
    set v1 = (func_buscaid(d_matricula, d_sigla, d_sala, d_dia, d_horaini));
    delete from lotacao where lotmoaid = v1;
    delete from monitoria where moaid = v1;
end #
delimiter ;

-- call sp_delhorario('2017118970165', 'CTADS.013', 'LAB I', 'Segunda','14:00:00');
-- select *from monitoria;


-- INSERTS -------------------------------------------------------------------------------------------------------------------
-- Combobox
insert into listasalas (ltsnome) values("LAB I"),("LAB II"),("LAB III"),("LAB IV"),("LAB V"),("LAB VI"),("LAB VII");
insert into horario (horini, horfim) values("08:00:00","09:00:00"),("09:00:00","10:00:00"),("10:00:00","11:00:00"),("11:00:00","12:00:00"),("12:00:00","13:00:00"),("13:00:00","14:00:00"),("14:00:00","15:00:00"),("15:00:00","16:00:00"),("16:00:00","17:00:00"),("17:00:00","18:00:00"),("18:00:00","19:00:00"),("19:00:00","20:00:00"),("20:00:00","21:00:00"),("21:00:00","22:00:00");
insert into diasemana (ddssigla, ddsnome) values("SEG","Segunda"),("TER","Terça"),("QUA","Quarta"),("QUI","Quinta"),("SEX","Sexta"),("SAB","Sabado");

-- Usuarios
insert into usuario (usumatricula, usunome, usutipo) values ("000000001","Andrea Pereira Mendonca","coo"),("0000000000002","Teste Monitor","mon");
insert into usuario (usumatricula, usunome) values ("0000000000003","Teste Aluno"),("2017118970001","ADELSON FERNANDES JUNIOR"),("2017118970002","ÁDRIA ESTEFFANE DO ROSARIO COSTA"),("2017118970003","ANDRÉ LUIZ SOBREIRA ARRUDA"),("2017118970004","ARI LEAL MATOS"),("2017118970005","BRENO RICHARD NUNES DE ARAUJO"),("2015118970061","BRUNO SILVEIRA CARVALHO"),("2017118970007","CARLOS ROBERTO DE OLIVEIRA MACIEL"),("2017118970008","CESAR AUGUSTO SAMPAIO DOS SANTOS"),("2017118970009","DEMILSON PEREIRA DA COSTA"),("2017118970010","EDER JONES VALENTE CHAVES"),("2017118970011","EDUARDO GALAXE DE LIMA TAVARES"),("2017118970012","EDUARDO MATHEUS RODRIGUES DA SILVA"),("2017118970013","ELIÉZER RODRIGUES SILVA"),("2017118970014","FERNANDO ARAUJO GURGEL"),("2017118970015","GABRIEL VICTOR MAIA GOMES"),("2017118970165","GILMAR ALVES PEREIRA"),("2017118970017","HERIBERTO DA SILVA CORREA JUNIOR"),("2017118970018","HIGSON ANDREAZZA DE OLIVEIRA MORAES"),("2017118970019","IZALTINO VIANA NETO"),("2017118970020","JANAINA DOS SANTOS ARAUJO"),("2017118970181","JOHN KENNEDY SARMENTO DA SILVA"),("2017118970022","JONATHAN FEITOSA DE SOUZA"),("2017118970023","JOSEMARA SOUSA DE OLIVEIRA"),("2017118970024","KELLY SUSY MARTINS JATHAY"),("2017118970025","KLEITSON JOSÉ LIMA TENÓRIO"),("2017118970026","LARISSA PESSOA SÁ MENEZES"),("2017118970027","LEANDERSON DA SILVA SANTOS"),("2017118970028","LUIS FERNANDO CAUPER PEREIRA"),("2017118970029","LUIS HENRIQUE AMORIM RIBEIRO"),("2017118970030","MATHEUS ISACKSON DA SILVA GUIMARAES"),("2017118970031","MAURICIO DA SILVA SOARES"),("2017118970032","NEUZA MARIA GONÇALVES DO CARMO FELIX"),("2017118970033","RAMON DOS SANTOS SILVA"),("2017118970034","RENATO DE LIMA SILVA"),("2017118970035","RENATO PEREIRA FERREIRA"),("2017118970036","RICKSON WILLIAM DANTAS FERREIRA"),("2017118970037","RODNEY APARECIDO DE OLIVEIRA MENDONCA"),("2017118970038","RUY DE ASCENÇÃO NETO"),("2017118970039","SANDRO HAIDEN TEIXEIRA"),("2017118970040","THALISON HIAGO LIMA ALVES"),("2017118970041","THALLYS JULLES PAIVA MENEZES MOURA"),("2017118970042","THIAGO LIMA DE ANDRADE"),("2017118970173","HÉRMANI FIGUEREDO DE OLIVEIRA");
-- select * from usuario;
-- Disciplinas
insert into disciplina values("CTADS.002","ALGORITMO E TÉCNICAS DE PROGRAMAÇÃO"),("CTADS.005","ANÁLISE ORGANIZACIONAL E DE PROCESSOS"),("CTADS.001","CÁLCULO DIFERENCIAL E INTEGRAL"),("CTADS.003","INGLÊS INSTRUMENTAL"),("CTADS.004","LÓGICA MATEMÁTICA"),("CTADS.011","ESTRUTURA DE DADOS"),("CTADS.009","INTERFACE HOMEM MÁQUINA"),("CTADS.008","LINGUAGEM E PROGRAMAÇÃO ORIENTADA A OBJETOS"),("CTADS.007","ORGANIZAÇÃO DE COMPUTADORES"),("CTADS.006","PROBABILIDADE E ESTATÍSTICA"),("CTADS.010","PRODUÇÃO DE TEXTOS"),("CTADS.016","ENGENHARIA DE REQUISITOS E ANÁLISE DE SISTEMAS"),("CTADS.017","FILOSOFIA APLICADA À COMPUTAÇÃO"),("CTADS.012","MODELAGEM, PROJETO E IMPLEMENTAÇÃO DE BD"),("CTADS.014","PADRÕES DE PROJETO"),("CTADS.013","PROGRAMAÇÃO APLICADA"),("CTADS.015","PROJETO DE INTERFACE GRÁFICA"),("CTADS.023","ADMINISTRAÇÃO E PROGRAMAÇÃO EM BANCO DE BANCO DE DADOS"),("CTADS.018","DESENVOLVIMENTO RÁPIDO DE APLICAÇÕES"),("CTADS.020","METODOLOGIA DE PESQUISA APLICADA À COMPUTAÇÃO"),("CTADS.019","PROCESSOS DE DESENVOLVIMENTO DE SOFTWARE"),("CTADS.022","REDES DE COMPUTADORES"),("CTADS.021","SISTEMAS OPERACIONAIS"),("CTADS.024","DESENVOLVIMENTO WEB"),("CTADS.027","DIREITO EMPRESARIAL"),("CTADS.026","GERENCIAMENTO DE PROJETOS DE SOFTWARE"),("CTADS.025","PROJETO BASEADO EM COMPONENTES"),("CTADS.028","TRABALHO DE CONCLUSÃO DE CURSO I - PROJETO DE SOFTWARE"),("CTADS.031","DESENVOLVIMENTO DE APLICAÇÕES DISTRIBUÍDAS"),("CTADS.032","EMPREENDEDORISMO"),("CTADS.029","MARKETING DE SOFTWARE APLICADO À COMPUTAÇÃO"),("CTADS.030","TESTE DE SOFTWARE"),("CTADS.033","TRABALHO DE CONCLUSÃO DE CURSO II - DESENVOLVIMENTO DE SOFTWARE"),("CTADS.036","BANCO DE DADOS AVANÇADO"),("CTADS.037","FRAMEWORKS NO DESENVOLVIMENTO WEB"),("CTADS.43","GESTÃO DA QUALIDADE DE SOFTWARE"),("CTADS.038","INFORMÁTICA NA EDUCAÇÃO"),("CTADS.035","INTELIGÊNCIA ARTIFICIAL & SISTEMAS ESPECIALISTAS"),("CTADS.039","LIBRAS"),("CTADS.42","LINGUAGEM DE PROGRAMAÇÃO VISUAL"),("CTADS.034","PROGRAMAÇÃO EM LÓGICA"),("SEMI.TS","SEMINÁRIOS EM INFORMÁTICA");

-- Salas
insert into sala (salid, saldiasemana, salhoraini, salhorafim,salmonitor)  values ("LAB I","Segunda",'14:00:00','15:00:00','GILMAR ALVES PEREIRA'),("LAB I","Segunda",'15:00:00','16:00:00','GILMAR ALVES PEREIRA'),("LAB I","Segunda",'16:00:00','17:00:00','GILMAR ALVES PEREIRA');
insert into sala (salid, saldiasemana, salhoraini, salhorafim) values ("LAB II","Quarta",'14:00:00','15:00:00'),("LAB II","Quarta",'15:00:00','16:00:00'),("LAB III","Quinta",'14:00:00','15:00:00'),("LAB III","Quinta",'15:00:00','16:00:00'),("LAB III","Quinta",'16:00:00','17:00:00'),("LAB V","Sexta",'13:00:00','14:00:00'),("LAB V","Sexta",'14:00:00','15:00:00'),("LAB V","Sexta",'15:00:00','16:00:00');

-- Monitores
insert into monitor values ("0000000000002","CTADS.001",'2018-01-01','2018-12-01'),("2015118970061","CTADS.002",'2018-01-01','2018-12-01'),("2017118970165","CTADS.001",'2018-02-01','2018-12-01'),("2017118970181","CTADS.003",'2018-02-01','2018-12-01'),("2017118970034","CTADS.004",'2018-02-01','2018-11-01');
select * from monitor;
-- Monitorias

insert into monitoria(moamonusumatricula, moadissigla, moasalid, moasaldiasemana, moasalhoraini, moasalhorafim) values ("2017118970165","CTADS.001","LAB I","Segunda",'14:00:00','15:00:00'),("2017118970165","CTADS.001","LAB I","Segunda",'15:00:00','16:00:00'),("2017118970165","CTADS.001","LAB I","Segunda",'16:00:00','17:00:00');
select * from monitoria;
-- Lotações
insert into lotacao values(1, '2017118970181'),(1,'2015118970061');
-- select * from lotacao;

select * from usuario;

select * from sala;
-- VIEW ----------------------------------------------------------------------------------------------------------------------

-- Relatorios;

-- Horários procurados
create view vw_horariosprocurados as
select moasalhoraini 'Horaini', moasalhorafim 'Horafim', count(*) 'Alunos interessados'
from monitoria
inner join lotacao on moaid = lotmoaid
group by moasalhoraini;
-- select * from vw_horariosprocurados;

-- Dias procurados;
create view vw_diasprocurados as
select moasaldiasemana 'Dia', count(*) 'Alunos interessados'
from monitoria
inner join lotacao on moaid = lotmoaid
group by moasaldiasemana;
-- select * from vw_diasprocurados;

-- Total de alunos Inscritos;
create view vw_qtdealunos as
select count(*) 'Total de Alunos'
from lotacao;
-- select * from vw_qtdealunos;

-- Disciplinas mais procuradas
-- drop view vw_disciplinaprocuradas;
create view vw_disciplinaprocuradas as
select disnome 'Disciplina', count(*) 'Alunos interessados'
from disciplina
inner join monitoria on dissigla = moadissigla
inner join lotacao on moaid = lotmoaid
group by disnome;
-- select * from vw_disciplinaprocuradas;

-- Qtde de Alunos por monitor
create view vw_alunospormonitor as
select usunome 'Monitor', count(*) 'Alunos interessados'
from monitor
inner join monitoria on monusumatricula = moamonusumatricula
inner join lotacao on moaid = lotmoaid
inner join usuario on usumatricula = monusumatricula
group by usunome;
-- select * from vw_alunospormonitor;



