import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, combineReducers, applyMiddleware, compose } from 'redux'
import { Provider } from 'react-redux'
import { Router, Route, browserHistory, IndexRoute } from 'react-router'
import { syncHistoryWithStore, routerReducer } from 'react-router-redux'
import thunk from 'redux-thunk';

import App from './components/App';
import Home from './components/Home';
import Categories from './components/Categories';
import Category from './components/Category';
import categoriesReducer from './reducers/categories';
import 'bootstrap/dist/css/bootstrap.css';
import './styles/index.css';


const reducer = combineReducers({
	categoriesState: categoriesReducer,
	routing: routerReducer
});

const store = createStore(
	reducer,
	compose(
		applyMiddleware(thunk),
		window.devToolsExtension ? window.devToolsExtension() : f => f
	)
);

const history = syncHistoryWithStore(browserHistory, store);


ReactDOM.render(
	<Provider store={store}>
		<Router history={history}>
			<Route path="/" component={App}>
				<IndexRoute component={Home}/>
				<Route path="categories" component={Categories}>
					<Route path=":id" component={Category}/>
				</Route>
			</Route>
		</Router>
	</Provider>,
	document.getElementById('root')
);
