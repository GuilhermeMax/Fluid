'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Tempo = sequelize.define('Tempo',{
		tempo: {
			field: 'tempo',
			type: DataTypes.STRING
		}
	}, 
	{
		tableName: 'Acesso', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Tempo;
};
