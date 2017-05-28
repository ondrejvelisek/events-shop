
export const WS_OPEN = "@ws/OPEN";
export const WS_MESSAGE = "@ws/MESSAGE";
export const WS_ERROR = "@ws/ERROR";
export const WS_CLOSE = "@ws/CLOSE";

export default function createWsMiddleware (socket, prefix = 'ws/') {

	const wsMiddleware = store => {

		let onOpenPromise = new Promise((resolve, reject) => {

			socket.onopen = function (event) {
				store.dispatch({type: WS_OPEN, data: event.data});
				resolve();
			};

		});

		socket.onerror = function (event) {
			store.dispatch({type: WS_ERROR, data: event.data});
		};
		socket.onclose = function (event) {
			store.dispatch({type: WS_CLOSE, data: event.data});
		};
		socket.onmessage = function (event) {
			let data = JSON.parse(event.data);
			if (data.type) {
				store.dispatch(data);
			} else {
				store.dispatch({type: WS_MESSAGE, data});
			}
		};


		return next => action => {

			if (action.type.startsWith(prefix)) {

				if (socket.readyState === WebSocket.OPEN) {
					socket.send(JSON.stringify(action));
				} else {
					onOpenPromise.then(() => {
						socket.send(JSON.stringify(action));
					});
				}

			}

			next(action);

		}

	};

	return wsMiddleware;
};
