'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Dado = sequelize.define('Dado',{
		id: {
			field: 'idDado',
			type: DataTypes.INTEGER,
			primaryKey: true,
			autoIncrement: true
		},		
		usoEmPorcentagem: {
			field: 'usoEmPorcentagem',
			type: DataTypes.FLOAT,
			allowNull: false
		},
		sistemaOperacional: {
			field: 'sistemaOperacional',
			type: DataTypes.FLOAT,
			allowNull: false
		},
		dadodate: {
			field: 'dadoDateTime',
			type: DataTypes.DATE,
			allowNull: false
		},
        hardware: {
            field: 'fkHardware',
            type: DataTypes.INTEGER,
            allowNull: false
        },
        computador: {
            field: 'fkComputador',
            type: DataTypes.INTEGER,
            allowNull: false
        }
	}, 
	{
		tableName: 'Dado', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Dado;
};
