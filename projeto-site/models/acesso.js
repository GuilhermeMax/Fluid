'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Acesso = sequelize.define('Acesso',{
		id: {
			field: 'idAcesso',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		computador: {
			field: 'fkComputador',
			type: DataTypes.INTEGER,
			allowNull: false
		},
		usuario: {
			field: 'fkUsuario',
			type: DataTypes.INTEGER,
			allowNull: false
		},
		horaAcesso: {
			field: 'horaAcesso',
			type: DataTypes.STRING,
			allowNull: false
		}
	}, 
	{
		tableName: 'Acesso', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Acesso;
};
