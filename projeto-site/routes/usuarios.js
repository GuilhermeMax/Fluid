var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Usuario = require('../models').Usuario;
var Computador = require('../models').Computador;
var Dado = require('../models').Dado;
var Acesso = require('../models').Acesso;

let sessoes = [];

/* Recuperar usuário por login e senha */
router.post('/autenticar', function(req, res, next) {
	console.log('Recuperando usuário por login e senha');

	var login = req.body.login; // depois de .body, use o nome (name) do campo em seu formulário de login
	var senha = req.body.senha; // depois de .body, use o nome (name) do campo em seu formulário de login	
	
	let instrucaoSql = `select * from Usuario where username='${login}' and senha='${senha}'`;
	console.log(instrucaoSql);

	sequelize.query(instrucaoSql, {
		model: Usuario
	}).then(resultado => {
		console.log(`Encontrados: ${resultado.length}`); 

		if (resultado.length == 1) {
			sessoes.push(resultado[0].dataValues.username);
			console.log('sessoes: ',sessoes);
			res.json(resultado[0]);
		} else if (resultado.length == 0) {
			res.status(403).send('Login e/ou senha inválido(s)');
		} else {
			res.status(403).send('Mais de um usuário com o mesmo login e senha!');
		}

	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
  	});
});

/* Cadastrar usuário */
router.post('/cadastrar', function(req, res, next) {
	console.log('Criando um usuário');
	
	Usuario.create({
		nome : req.body.nome,
		login : req.body.login,
		senha: req.body.senha,
		email: req.body.email,
		empresa: 1,
		projeto: 1,
		tipoUsuario: req.body.tipoUsuario
	}).then(resultado => {
		console.log(`Registro criado: ${resultado}`)
        res.send(resultado);
    }).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
  	});
});


/* Verificação de usuário */
router.get('/sessao/:username', function(req, res, next) {
	let login = req.params.username;
	console.log(`Verificando se o usuário ${login} tem sessão`);
	
	let tem_sessao = false;
	for (let u=0; u<sessoes.length; u++) {
		if (sessoes[u] == login) {
			tem_sessao = true;
			break;
		}
	}

	if (tem_sessao) {
		let mensagem = `Usuário ${login} possui sessão ativa!`;
		console.log(mensagem);
		res.send(mensagem);
	} else {
		res.sendStatus(403);
	}
	
});


/* Logoff de usuário */
router.get('/sair/:username', function(req, res, next) {
	let login = req.params.username;
	console.log(`Finalizando a sessão do usuário ${login}`);
	let nova_sessoes = []
	for (let u=0; u<sessoes.length; u++) {
		if (sessoes[u] != login) {
			nova_sessoes.push(sessoes[u]);
		}
	}
	sessoes = nova_sessoes;
	res.send(`Sessão do usuário ${login} finalizada com sucesso!`);
});


/* Recuperar todos os usuários */
router.get('/', function(req, res, next) {
	console.log('Recuperando todos os usuários');
	Usuario.findAndCountAll().then(resultado => {
		console.log(`${resultado.count} registros`);

		res.json(resultado.rows);
	}).catch(erro => {
		console.error(erro);
		res.status(500).send(erro.message);
  	});
});

router.get('/listarComputadores', function(req, res, next) {
	

	console.log(`Recuperando as ultimas informações de computadores`);
	
	const instrucaoSql = `select * from computador`;

	sequelize.query(instrucaoSql, {
		model: Computador,
		mapToModel: true 
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado.length}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});

router.get('/listarDadosDisco/:fkComputador', function(req, res, next) {
	
	var fkComputador = req.params.fkComputador;	
	
	const instrucaoSql = `
				select top 3
					usoEmPorcentagem, 
					fkHardware, 
					idComputador from 
						dado inner join 
							computador on 
								idComputador = fkComputador where
									idComputador = ${fkComputador} and
									fkhardware>3 order by idDado desc`;

	sequelize.query(instrucaoSql, {
		model: Dado,
		mapToModel: true 
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado.length}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });

});

router.get('/listarDiscos/:fkComputador', function(req, res, next) {
	
	var fkComputador = req.params.fkComputador;	
	
	const instrucaoSql = `
				select fkHardware from dado inner join computador on idComputador = fkComputador where idComputador = ${fkComputador} and fkhardware>3 order by idDado desc`;

	sequelize.query(instrucaoSql, {
		model: Dado,
		mapToModel: true 
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado.length}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });

});

router.get('/procurarCPU/:fkComputador', function(req, res, next) {
	
	var fkComputador = req.params.fkComputador;	
	
	const instrucaoSql = `select * from Dado where fkHardware = 2 and fkComputador = ${fkComputador}`;

	sequelize.query(instrucaoSql, {
		model: Dado,
		mapToModel: true 
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado.length}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });

});

router.get('/leituras/CPU/:fkComputador', function(req, res, next) {
	
	var fkComputador = req.params.fkComputador;

	var limiteLinhas = 7;

	const instrucaoSql = `select top ${limiteLinhas} from Dado where fkHardware = 2 and fkComputador = ${fkComputador}`

	sequelize.query(instrucaoSql, {
		model: Dado,
		mapToModel: true 
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado.length}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});

router.get('/acesso/:fkComputador', function(req, res, next) {
	
	var fkComputador = req.params.fkComputador;

	const instrucaoSql = `select top 1 * from [dbo].[Acesso] inner join [dbo].[Usuario] on fkUsuario = idUsuario where fkComputador = ${fkComputador} order by idAcesso desc ;`

	sequelize.query(instrucaoSql, {
		model: Dado,
		mapToModel: true 
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado.length}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});

router.get('/acessoTime/:fkComputador', function(req, res, next) {
	
	var fkComputador = req.params.fkComputador;

	const instrucaoSql = `select convert(varchar, horaAcesso, 3) from [dbo].[Acesso] inner join [dbo].[Usuario] on fkUsuario = idUsuario where fkComputador = ${fkComputador} order by idAcesso desc;`

	sequelize.query(instrucaoSql, {
		model: Acesso,
		mapToModel: true 
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado.length}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});

router.get('/mediaCPU/:fkComputador', function(req, res, next) {
	
	const instrucaoSql = `select CAST(AVG(usoEmPorcentagem) as decimal(10,0)) as media from [dbo].[Dado] where dadoDateTime BETWEEN DATEADD(WEEK,-1,GETDATE()) AND GETDATE() AND fkHardware = 1 and fkComputador = ${fkComputador};`;

	sequelize.query(instrucaoSql, {
		model: Dado,
		mapToModel: true
	}).then(resultado => {
		console.log(`Encontrados: ${resultado.length}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	});
});

module.exports = router;
