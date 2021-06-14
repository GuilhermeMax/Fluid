var express = require('express');
var router = express.Router();
var sequelize = require('../models').sequelize;
var Usuario = require('../models').Usuario;
var Computador = require('../models').Computador;
var Dado = require('../models').Dado;
var Acesso = require('../models').Acesso;
var Media = require('../models').Media;
var Tempo = require('../models').Tempo;

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
									fkHardware>3 order by idDado desc`;

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
				select fkHardware from dado inner join computador on idComputador = fkComputador where idComputador = ${fkComputador} and fkHardware>3 order by idDado desc`;

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
		model: Usuario,
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

	const instrucaoSql = `select top 1 convert(varchar, horaAcesso,103) as tempo from [dbo].[Acesso] inner join [dbo].[Computador] on fkComputador = idComputador where fkComputador = ${fkComputador} order by horaAcesso desc;`

	sequelize.query(instrucaoSql, {
		model: Tempo
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
	
	var fkComputador = req.params.fkComputador;

	const instrucaoSql = `select CAST(AVG(usoEmPorcentagem) as decimal(10,0)) as media from [dbo].[Dado] where dadoDateTime BETWEEN DATEADD(WEEK,-1,GETDATE()) AND GETDATE() AND fkHardware = 1 and fkComputador = ${fkComputador};`;

	sequelize.query(instrucaoSql, {
		model: Media
	}).then(resultado => {
		console.log(`Encontrados: ${resultado}`);
			res.send(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	});
});

router.get('/mediaRAM/:fkComputador', function(req, res, next) {
	
	var fkComputador = req.params.fkComputador;

	const instrucaoSql = `select CAST(AVG(usoEmPorcentagem) as decimal(10,0)) as media from [dbo].[Dado] where dadoDateTime BETWEEN DATEADD(WEEK,-1,GETDATE()) AND GETDATE() AND fkHardware = 2 and fkComputador = ${fkComputador};`;

	sequelize.query(instrucaoSql, {
		model: Media
	}).then(resultado => {
		console.log(`Encontrados: ${resultado}`);
			res.send(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	});
});

router.get('/mediaGPU/:fkComputador', function(req, res, next) {
	
	var fkComputador = req.params.fkComputador;

	const instrucaoSql = `select CAST(AVG(temperatura) as decimal(10,0)) as media from [dbo].[Dado] where dadoDateTime BETWEEN DATEADD(WEEK,-1,GETDATE()) AND GETDATE() AND fkHardware = 3 and fkComputador = ${fkComputador};`;

	sequelize.query(instrucaoSql, {
		model: Media
	}).then(resultado => {
		console.log(`Encontrados: ${resultado}`);
			res.send(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	});
});

router.get('/mediaDISCO/:fkComputador/:fkDisco', function(req, res, next) {
	
	var fkComputador = req.params.fkComputador;
	var fkDisco = req.params.fkDisco;

	const instrucaoSql = `select CAST(AVG(usoEmPorcentagem) as decimal(10,0)) as media from [dbo].[Dado] where dadoDateTime BETWEEN DATEADD(WEEK,-1,GETDATE()) AND GETDATE() AND fkHardware = ${fkDisco} and fkComputador = ${fkComputador};`;

	sequelize.query(instrucaoSql, {
		model: Media
	}).then(resultado => {
		console.log(`Encontrados: ${resultado}`);
			res.send(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	});
});

router.get('/capturarHostname/:fkComputador', function(req, res, next) {
	
	var fkComputador = req.params.fkComputador;

	console.log(`Recuperando as ultimas informações de computadores`);
	
	const instrucaoSql = `select * from computador where idComputador = ${fkComputador}`;

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

router.get('/dadosGeralCPU/:fkComputador', function(req, res, next) {
	
	// quantas são as últimas leituras que quer? 8 está bom?
	const limite_linhas = 7;

	var fkComputador = req.params.fkComputador;

	console.log(`Recuperando as ultimas ${limite_linhas} leituras`);
	
	const instrucaoSql = `select top ${limite_linhas}
						usoEmPorcentagem,
						dadoDateTime,
						FORMAT(dadoDateTime,'HH:mm:ss') as dataRegistro_grafico
						from Dado
						where fkComputador = ${fkComputador} and fkHardware = 1
						order by idDado desc`;

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

router.get('/tempo-realCPU/:fkComputador', function(req, res, next) {
	console.log('Recuperando dados de cpu atuais');

	//var fksetor = req.body.fksetor; // depois de .body, use o nome (name) do campo em seu formulário de login
	var fkComputador = req.params.fkComputador;

	const instrucaoSql = `select top 1 usoEmPorcentagem, temperatura, FORMAT(dadoDateTime,'HH:mm:ss') as dataRegistro_grafico, fkComputador from dado where fkComputador = ${fkComputador} and fkHardware = 1 order by idDado desc`;

	sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
		.then(resultado => {
			res.json(resultado[0]);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
});

router.get('/dadosGeralGPU/:fkComputador', function(req, res, next) {
	
	// quantas são as últimas leituras que quer? 8 está bom?
	const limite_linhas = 7;

	var fkComputador = req.params.fkComputador;

	console.log(`Recuperando as ultimas ${limite_linhas} leituras`);
	
	const instrucaoSql = `select top ${limite_linhas}
						temperatura,
						dadoDateTime,
						FORMAT(dadoDateTime,'HH:mm:ss') as dataRegistro_grafico
						from Dado
						where fkComputador = ${fkComputador} and fkHardware = 3
						order by idDado desc`;

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

router.get('/tempo-realGPU/:fkComputador', function(req, res, next) {
	console.log('Recuperando dados de cpu atuais');

	//var fksetor = req.body.fksetor; // depois de .body, use o nome (name) do campo em seu formulário de login
	var fkComputador = req.params.fkComputador;

	const instrucaoSql = `select top 1 usoEmPorcentagem, temperatura, FORMAT(dadoDateTime,'HH:mm:ss') as dataRegistro_grafico, fkComputador from dado where fkComputador = ${fkComputador} and fkHardware = 3 order by idDado desc`;

	sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
		.then(resultado => {
			res.json(resultado[0]);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
});

router.get('/dadosGeralRAM/:fkComputador', function(req, res, next) {
	
	// quantas são as últimas leituras que quer? 8 está bom?
	const limite_linhas = 7;

	var fkComputador = req.params.fkComputador;

	console.log(`Recuperando as ultimas ${limite_linhas} leituras`);
	
	const instrucaoSql = `select top ${limite_linhas}
						usoEmPorcentagem,
						dadoDateTime,
						FORMAT(dadoDateTime,'HH:mm:ss') as dataRegistro_grafico
						from Dado
						where fkComputador = ${fkComputador} and fkHardware = 2
						order by idDado desc`;

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

router.get('/tempo-realRAM/:fkComputador', function(req, res, next) {
	console.log('Recuperando dados de cpu atuais');

	//var fksetor = req.body.fksetor; // depois de .body, use o nome (name) do campo em seu formulário de login
	var fkComputador = req.params.fkComputador;

	const instrucaoSql = `select top 1 usoEmPorcentagem, temperatura, FORMAT(dadoDateTime,'HH:mm:ss') as dataRegistro_grafico, fkComputador from dado where fkComputador = ${fkComputador} and fkHardware = 2 order by idDado desc`;

	sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
		.then(resultado => {
			res.json(resultado[0]);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
});

router.get('/dadosGraficoDisco/:fkComputador/:fkHardware', function(req, res, next) {
	
	// quantas são as últimas leituras que quer? 8 está bom?
	const limite_linhas = 7;

	var fkComputador = req.params.fkComputador;
	
	var fkHardware = req.params.fkHardware;

	console.log(`Recuperando as ultimas ${limite_linhas} leituras`);
	
	const instrucaoSql = `select top ${limite_linhas}
						usoEmPorcentagem,
						dadoDateTime,
						FORMAT(dadoDateTime,'HH:mm:ss') as dataRegistro_grafico
						from Dado
						where fkComputador = ${fkComputador} and fkHardware = ${fkHardware}
						order by idDado desc`;

	sequelize.query(instrucaoSql, {
		type: sequelize.QueryTypes.SELECT
	  })
	  .then(resultado => {
			console.log(`Encontrados: ${resultado.length}`);
			res.json(resultado);
	  }).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
	  });
});

router.get('/tempo-realDisco/:fkComputador/:fkHardware', function(req, res, next) {
	console.log('Recuperando dados de cpu atuais');

	//var fksetor = req.body.fksetor; // depois de .body, use o nome (name) do campo em seu formulário de login
	var fkComputador = req.params.fkComputador;

	var fkHardware = req.params.fkHardware;

	const instrucaoSql = `select top 1 usoEmPorcentagem, FORMAT(dadoDateTime,'HH:mm:ss') as dataRegistro_grafico, fkComputador from dado where fkComputador = ${fkComputador} and fkHardware = ${fkHardware} order by idDado desc`;

	sequelize.query(instrucaoSql, { type: sequelize.QueryTypes.SELECT })
		.then(resultado => {
			res.json(resultado[0]);
		}).catch(erro => {
			console.error(erro);
			res.status(500).send(erro.message);
		});
});

// router.get('/dadosRecentesCPU/:fkComputador', function(req, res, next) {
// 	console.log('Recuperando dados de cpu atuais');

// 	//var fksetor = req.body.fksetor; // depois de .body, use o nome (name) do campo em seu formulário de login
// 	var fkComputador = req.params.fkComputador;

// 	const instrucaoSql = `select * from dado where dadoDateTime BETWEEN DATEADD(MINUTE,-5,GETDATE()) AND GETDATE() and fkComputador = ${fkComputador} and fkHardware = 1;`;

// 	sequelize.query(instrucaoSql, {
// 		type: sequelize.QueryTypes.SELECT
// 	})
// 		.then(resultado => {
// 			res.json(resultado);
// 		}).catch(erro => {
// 			console.error(erro);
// 			res.status(500).send(erro.message);
// 		});
// });

module.exports = router;
