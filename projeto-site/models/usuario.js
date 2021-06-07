	'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Usuario = sequelize.define('Usuario',{
		id: {
			field: 'idUsuario',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		nome: {
			field: 'nomeUsuario',
			type: DataTypes.STRING,
			allowNull: false
		},
		login: {
			field: 'username',
			type: DataTypes.STRING,
			allowNull: false
		},
		senha: {
			field: 'senha',
			type: DataTypes.STRING,
			allowNull: false
		},
		email: {
			field: 'emailUsuario',
			type: DataTypes.STRING,
			allowNull: false
		},
		empresa: {
			field: "fkEmpresa",
			type: DataTypes.INTEGER,
			allowNull: false
		},
		projeto: {
			field: "fkProjeto",
			type: DataTypes.INTEGER,
			allowNull: false
		},
		tipoUsuario: {
			field: "fkTipoUsuario",
			type: DataTypes.INTEGER,
			allowNull: false
		}
	}, 
	{
		tableName: 'Usuario', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Usuario;
};
