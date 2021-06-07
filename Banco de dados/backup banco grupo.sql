-- Banco de Dados 
create database bdFluidTeste;
-- drop database bdFluidTeste;
use bdFluidTeste;
-- Tabela Licença
create table Licenca(
	idLicenca int primary key auto_increment,
    tipoLicenca varchar(45),
    quantComputadores int,
    dataAquisicao datetime,
    valor double
);

-- Tabela Empresa
create table Empresa(
	idEmpresa int primary key auto_increment,
    loginEmpresa varchar(45),
    senhaEmpresa varchar(45),
    cnpj char(14),
    email varchar(45),
    fkLicenca int,
    foreign key(fkLicenca) references licenca (idlicenca)
);

-- Tabela Projeto
create table Projeto(
	idProjeto int primary key auto_increment,
    nomeProjeto varchar(45),
    dataEntrega date,
    etapaProjeto varchar(20),
	fkEmpresa int,
    foreign key(fkEmpresa) references Empresa (idEmpresa)
);

-- Tabela Tipo Usuário
create table TipoUsuario(
	idTipoUsuario int primary key auto_increment,
    tipoUsuario varchar(45),
    permicaoAddUser bool,
    permicaoEditUser bool,
    permicaoDeleteUser bool,
    permicaoAcessDashboard bool
);

-- Tabela Usuario
create table Usuario(
	idUsuario int primary key auto_increment,
    username varchar(45),
    senha varchar(45),
    emailUsuario varchar(45),
    nomeUsuario varchar(45),
    fkEmpresa int,
    foreign key(fkEmpresa) references Empresa (idEmpresa),
    fkProjeto int,
    foreign key(fkProjeto) references Projeto (idProjeto),
    fkTipoUsuario int,
    foreign key(fkTipoUsuario) references TipoUsuario (idTipoUsuario)
);

-- Tabela Computador
create table Computador(
	idComputador int primary key auto_increment,
    hostname varchar(45),
    statusComputador varchar(20) default "ligado",
    check (statusComputador = 'ligado' or 'desligado' or 'renderizando'),
    sistemaOperacional varchar(20),
    fkEmpresa int,
    foreign key(fkEmpresa) references Empresa (idEmpresa)
);

-- Tabela Acesso 
create table Acesso(
	idAcesso int auto_increment,
    fkComputador int,
    foreign key(fkComputador) references Computador (idComputador),
    fkUsuario int,
    foreign key(fkUsuario) references Usuario (idUsuario),
    primary key(idAcesso, fkComputador, fkUsuario),
    horaAcesso timestamp null default current_timestamp
);

-- Tabela Processo
create table Processo(
	idProcesso int auto_increment,
    PID int,
    processoNome varchar(20),
    usoCPU double,
    usoMemoria double,
    usoGPU double,
    processoDateTime timestamp null default current_timestamp,
    fkComputador int,
    foreign key(fkComputador) references Computador (idComputador),
    primary key(idProcesso, fkComputador)
);

-- Tabela Hardware
create table Hardware(
	idHardware int primary key auto_increment,
    tipoHardware varchar(20)
);

-- Tabela Possui 
create table Possui(
	idPossui int auto_increment,
    fkHardware int,
    foreign key(fkHardware) references Hardware (idHardware),
    fkComputador int,
    foreign key(fkComputador) references Computador (idComputador),
    primary key(idPossui, fkHardware, fkComputador)
);

-- Tabela Dado
create table Log(
	idLog int auto_increment,
    usoEmPorcentagem double, 
    temperatura double,
    dadoDateTime timestamp null default current_timestamp,
    fkHardware int,
    foreign key(fkHardware) references Hardware (idHardware),
    fkComputador int,
    foreign key(fkComputador) references Computador (idComputador),
    primary key(idLog, fkHardware, fkComputador)
);

-- Valores para teste
insert into licenca(tipoLicenca, quantComputadores, dataAquisicao, valor) values 
	('premium',10,'2020-02-02 02:02:02', 1100.00);

insert into empresa(loginEmpresa,senhaEmpresa,cnpj,email,fkLicenca) values
	('fluids', '9292882', '92737475747374', 'fluid@fluid.com', 1);

insert into projeto(nomeProjeto,dataEntrega,etapaProjeto,fkEmpresa) values
	('Batman', '2020-02-02 02:02:02', 'Finalizado', 1);
    
insert into tipoUsuario(tipoUsuario,permicaoAddUser,permicaoEditUser,permicaoDeleteUser,permicaoAcessDashboard) values
	('adim',1,1,1,1);
    
insert into usuario(username,senha,emailUsuario,nomeUsuario,fkEmpresa,fkProjeto,fkTipoUsuario) values
	('miguel', 'miguel321', 'miguel.nunes@bandtec.com', 'Miguel Nunes', 1, 1, 1);
    
insert into computador(hostname,statusComputador,sistemaOperacional,fkEmpresa) values 
	('Desktop9992', 'ligado', 'Windows', 1),
    ('Desktop-023', 'ligado', 'Windows', 1);
    
insert into acesso(fkComputador,fkUsuario,horaAcesso) values
	(2, 1,'2020-02-02 02:02:02');
    
insert into processo(PID,processoNome,usoCPU,usoMemoria,processoDateTime,fkComputador) values 
	(928, 'chrome', 60,10,20,null, 1);
    
insert into hardware(tipoHardware) values
	('CPU'),
    ('RAM'),
    ('GPU');
    
insert into possui(fkHardware,fkComputador) values 
	(1,1),
    (2,1),
    (3,1);
    
-- Select 
select * from log;
select * from usuario;
select * from computador;
