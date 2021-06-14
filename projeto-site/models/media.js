'use strict';

/* 
lista e explicação dos Datatypes:
https://codewithhugo.com/sequelize-data-types-a-practical-guide/
*/

module.exports = (sequelize, DataTypes) => {
    let Media = sequelize.define('Media',{
		media: {
			field: 'media',
			type: DataTypes.NUMBER
		}
	}, 
	{
		tableName: 'Dado', 
		freezeTableName: true, 
		underscored: true,
		timestamps: false,
	});

    return Media;
};
