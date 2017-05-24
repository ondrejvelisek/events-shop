import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, combineReducers, applyMiddleware, compose } from 'redux'
import { Provider } from 'react-redux'
import { Router, Route, browserHistory, IndexRoute } from 'react-router'
import { syncHistoryWithStore, routerReducer, routerMiddleware as createRouterMiddleware } from 'react-router-redux'
import thunk from 'redux-thunk';
import createOidcMiddleware from 'redux-oidc';
import { OidcProvider, reducer as authReducer } from 'redux-oidc';

import App from './components/App';
import Callback from './components/Callback';
import Home from './components/Home';
import Categories from './components/Categories';
import Category from './components/Category';
import categoriesReducer from './reducers/categories';
import 'bootstrap/dist/css/bootstrap.css';
import './styles/index.css';
import EventsShop from "./api/EventsShop";
import {userManager} from "./userManager";



const reducer = combineReducers({
	categoriesState: categoriesReducer,
	routing: routerReducer,
	auth: authReducer
});

const api = new EventsShop(userManager, "http://localhost:8080/events-shop-rest/api/v0.1");

const oidcMiddleware = createOidcMiddleware(userManager, null, false, '/callback');

const routerMiddleware = createRouterMiddleware(browserHistory);

const store = createStore(
	reducer,
	compose(
		applyMiddleware(thunk.withExtraArgument({api}), oidcMiddleware, routerMiddleware),
		window.devToolsExtension ? window.devToolsExtension() : f => f
	)
);

const history = syncHistoryWithStore(browserHistory, store);


ReactDOM.render(
	<Provider store={store}>
		<OidcProvider store={store} userManager={userManager}>
			<Router history={history}>
				<Route path="/" component={App}>
					<IndexRoute component={Home}/>
					<Route path="callback" component={Callback}/>
					<Route path="categories" component={Categories}>
						<Route path=":id" component={Category}/>
					</Route>
				</Route>
			</Router>
		</OidcProvider>
	</Provider>,
	document.getElementById('root')
);
