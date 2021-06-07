'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Computador = sequelize.define('Computador',{
		id: {
			field: 'idComputador',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		hostname: {
			field: 'hostname',
			type: DataTypes.STRING,
			allowNull: false
		},
		sistemaOperacional: {
			field: 'sistemaOperacional',
			type: DataTypes.STRING,
			allowNull: false
		},
		empresa: {
			field: 'fkEmpresa',
			type: DataTypes.INTEGER,
			allowNull: false
		}
	}, 
	{
		tableName: 'Computador', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Computador;
};
