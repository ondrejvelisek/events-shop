
/*

This file is just for documentation purposes.
It show example redux state which ilustrates its structure

*/

var state = {
	categories_state: {
		categories: [
			{
				id: 2,
				name: 'Food and drinks',
				description: 'Every host know that happiness goes through stomach of his guests'
			},
			{
				id: 3,
				name: 'Entertainment',
				description: 'Do you need music, cultural show or teambuilding activity?'
			}
		],
		updating: false
	},
	services_state: {
		services: [
			{
				id: 5,
				name: 'Coffee and tea',
				description: 'Coffee break including coffee, tea, water and cookies',
				price: 22,
				categoryId: 2,
				viewing: 2		// number of clients viewing this service
			},
			{
				id: 6,
				name: 'Laser game',
				description: 'Ideal way how to develope healthy relationships in your team',
				price: 180,
				categoryId: 3,
				viewing: 4		// number of clients viewing this service
			}
		],
		updating: false,
	},
	events_state: {
		events: [
			{
				id: 8,
				name: 'Teambuilding',
				date: '2017-02-11',
				eventServices: [
					{
						id: 2,
						serviceId: 5,
						count: 25
					},
					{
						id: 3,
						serviceId: 6,
						count: 20
					}
				]
			},
			{
				id: 9,
				name: 'Birthday party',
				date: '2017-03-22',
				eventServices: [
					{
						id: 2,
						serviceId: 5,
						count: 12
					}
				]
			}
		],
		updating: false,
	},
	auth_state: {
		user: {
			roles: ['USER', 'ADMIN'],
			id_token: 'tokenValue',
			profile: {
				sub: '105202389642828188450',
				email: 'ondrejvelisek@gmail.com',
				name: 'Ondřej Velíšek',
				picture: 'https://example.com/photo.jpg',
			},
			expires_at: 1495987183
		},
		isLoadingUser: false
	}
};